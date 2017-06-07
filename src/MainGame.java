import org.newdawn.slick.*;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;

/**
 * Created by Andrew on 6/6/2017.
 */
public class MainGame extends BasicGameState {

    public static final int ID = 1;
    private StateBasedGame game;

    private Image background;
    private int[][] lawn;
    private Tower[][] plants;
    private ArrayList<Zombie> zombies;
    private ArrayList<Projectile> projectiles;
    private ArrayList<Sound> sounds;
    private Shop shop;
    private int money;
    private Mouse mouse;
    private int GameTime = 0;

    public int getID() {
        return ID;
    }

    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        this.game = stateBasedGame;
        background = new Image("res/floor.png");
        lawn = new int[6][10];
        money = 100;
        plants = new Tower[6][10];
        zombies = new ArrayList<Zombie>();
        projectiles = new ArrayList<Projectile>();
        mouse = new Mouse();
        shop = new Shop(gameContainer);
        sounds = new ArrayList<Sound>();
        sounds.add(new Sound("res/Sounds/pop2.wav"));

    }

    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        //floor
        graphics.drawImage(background, 100, 100);

        //render all the plants
        for (int i = 0; i < plants.length; i++) {
            for (int j = 0; j < plants[i].length; j++) {
                if (plants[i][j] != null) {
                    graphics.drawImage(plants[i][j].getPic(), plants[i][j].getX() * 100 + 100, plants[i][j].getY() * 100 + 100);
                }
            }
        }
        //render all the projectiles
        for (Projectile a: projectiles) {
            graphics.drawImage(a.getImage(), a.getX(), a.getY());

        }

        //render all the zombies
        for (Zombie a : zombies) {
            graphics.drawImage(a.getPic(), a.getX(), a.getY());
        }

        //render shop
        for (MouseOverArea a : shop.getShop()) {
            a.render(gameContainer, graphics);
        }

        //render money
        graphics.setColor(Color.white);
        graphics.drawString("money: " + money, 10, 30);
    }

    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        Input input = gameContainer.getInput();

        GameTime++;

        genZombies();

        if (input.isMousePressed(0)){
            //check if mouse is clicked on playing field
            if (input.getMouseX()>100 && input.getMouseX()<1200 && input.getMouseY()>100 && input.getMouseY()<700){
                System.out.println("mouse on playing field");

                //check if mouse is in placing state for each tower, if true, take money, place tower and set placement to false
                if (mouse.isPlaceMarkerLauncher()) {
                    System.out.println("placed tower");
                    money -= 20;
                    mouse.setPlaceMarkerLauncher(false);
                    plants[input.getMouseY() / 100 - 1][input.getMouseX() / 100 - 1] = new MarkerLauncher(input.getMouseX() / 100 - 1, input.getMouseY() / 100 - 1);
                }

                else if (mouse.isPlaceMoneyTree()) {
                    money -= 10;
                    mouse.setPlaceMoneyTree(false);
                    plants[input.getMouseY() / 100 - 1][input.getMouseX() / 100 - 1] = new MoneyTree(input.getMouseX() / 100 - 1, input.getMouseY() / 100 - 1);
                }

                else if (mouse.isPlaceQuiz()) {
                    money -= 30;
                    mouse.setPlaceQuiz(false);
                    plants[input.getMouseY() / 100 - 1][input.getMouseX() / 100 - 1] = new Quiz(input.getMouseX() / 100 - 1, input.getMouseY() / 100 - 1);
                }
            }

            //check if mouse is over any of the shop areas, if so and have enough money set mouse state to placing that tower
            else if (shop.getCell(0).isMouseOver()){
                if (money - 10 >= 0) {//check if player has enough money
                    System.out.println("Tower Bought");

                    mouse.setPlaceMoneyTree(true);
                    mouse.setPlaceMarkerLauncher(false);
                    mouse.setPlaceQuiz(false);
                    sounds.get(0).play();
//                    projectiles.add(new Projectile(100, 250, new Image("res/bullet.png"), 5, 2));
                }
            }

            else if (shop.getCell(1).isMouseOver()){
                if (money - 20 >= 0) {
                    System.out.println("Tower Bought");

                    mouse.setPlaceMarkerLauncher(true);
                    mouse.setPlaceMoneyTree(false);
                    mouse.setPlaceQuiz(false);
                    sounds.get(0).play();
                }
            }

            else if (shop.getCell(2).isMouseOver()){
                if (money - 30 >= 0) {
                    System.out.println("Tower Bought");

                    mouse.setPlaceQuiz(true);
                    mouse.setPlaceMoneyTree(false);
                    mouse.setPlaceMarkerLauncher(false);
                    sounds.get(0).play();
                }
            }
        }
        /**
         * Starting hit detection part of update()
         * Part 1: Towers
         *      Call each tower's attack() method
         */
        for (int j = 0; j < plants.length; j++) {
            for (int k = 0; k < plants[0].length; k++){
                Tower p = plants[j][k];

                if(p != null && p.isOffensive()){ //if there's a plant in that square
                    Projectile proj = p.attack();
                    if(proj != null)
                        projectiles.add(proj); //launching projectile
                }
                if (p != null && p instanceof MoneyTree && ((MoneyTree) p).genMoney()){
                    money += 30;
                }
            }
        }
        /**
         * move the projectiles
         *
         */
        for (Projectile a:projectiles) {
            a.move();
        }

        /**
         * Part 2: Zombies
         * For each zombie:
         *      Check if hit by projectile
         *      Check if intersects a plant
         *          If hit plant, do damage
         *          Else move
         */
        for(int l = 0; l < zombies.size(); l++){
            Zombie z = zombies.get(l);

            for(int m = 0; m < projectiles.size(); m++){

                if(z.isHit(projectiles.get(m))){ //check for collisions with any projectiles
                    z.takeDamage(projectiles.get(m).getDamage()); //take damage if hit
                    projectiles.remove(m); //remove projectile from projectile list

                    //check if z is dead-dead (dead for the second time)
                    if(z.dead()){
                        zombies.remove(z);
                    }
                    if(projectiles.get(m).getX() > 1200)
                        projectiles.remove(m);
                }
            }

            if (plants[z.getY()/100-1][z.getX()/100-1] != null){ //if square is occupied

                Tower p = plants[z.getY()/100-1][z.getX()/100-1];
                p.takeDamage(z.getDamage()); //do damage

                //check if plant is dead
                if(p.getHealth() <= 0){
                    plants[z.getY()/100-1][z.getX()/100-1] = null; //remove
                }
            }
            else{
                z.move(); //if nothing in the way, move forward
            }
        }
    }

    public void enter(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        System.out.println("Entered MainGame State");
        genZombies();
    }

    public void genZombies() throws SlickException{
        if(GameTime % 200 == 0) {
            zombies.add(new Zombie(1000, (int)((Math.random()*6) + 1)*100, new Image("res/TestZombie.png"), 1, 100, 2));
        }
    }

    public void leave(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {

    }


    public void mouseClicked(int button, int x, int y, int i){
        System.out.println("Mouse clicked");

//        //check if mouse is clicked on playing field
//        if (input.getMouseX()>100 && input.getMouseX()<1200 && input.getMouseY()>100 && input.getMouseY()<700){
//            System.out.println("mouse on playing field");
//
//            //check if mouse is in placing state for each tower, if true, take money, place tower and set placement to false
//            if (mouse.isPlaceMarkerLauncher()) {
//                System.out.println("placed tower");
//                money -= 20;
//                mouse.setPlaceMarkerLauncher(false);
//                plants[input.getMouseY() / 100 - 1][input.getMouseX() / 100 - 1] = new MarkerLauncher(input.getMouseX() / 100 - 1, input.getMouseY() / 100 - 1);
//            }
//
//            else if (mouse.isPlaceMoneyTree()) {
//                money -= 10;
//                mouse.setPlaceMoneyTree(false);
//                plants[input.getMouseY() / 100 - 1][input.getMouseX() / 100 - 1] = new MoneyTree(input.getMouseX() / 100 - 1, input.getMouseY() / 100 - 1);
//            }
//
//            else if (mouse.isPlaceQuiz()) {
//                money -= 30;
//                mouse.setPlaceQuiz(false);
//                plants[input.getMouseY() / 100 - 1][input.getMouseX() / 100 - 1] = new Quiz(input.getMouseX() / 100 - 1, input.getMouseY() / 100 - 1);
//            }
//        }
//
//        //check if mouse is over any of the shop areas, if so and have enough money set mouse state to placing that tower
//        else if (shop.getCell(0).isMouseOver()){
//            if (money - 10 >= 0) {//check if player has enough money
//                System.out.println("Tower Bought");
//
//                mouse.setPlaceMoneyTree(true);
//                mouse.setPlaceMarkerLauncher(false);
//                mouse.setPlaceQuiz(false);
//                sounds.get(0).play();
////                    projectiles.add(new Projectile(100, 250, new Image("res/bullet.png"), 5, 2));
//            }
//        }
//
//        else if (shop.getCell(1).isMouseOver()){
//            if (money - 20 >= 0) {
//                System.out.println("Tower Bought");
//
//                mouse.setPlaceMarkerLauncher(true);
//                mouse.setPlaceMoneyTree(false);
//                mouse.setPlaceQuiz(false);
//                sounds.get(0).play();
//            }
//        }
//
//        else if (shop.getCell(2).isMouseOver()){
//            if (money - 30 >= 0) {
//                System.out.println("Tower Bought");
//
//                mouse.setPlaceQuiz(true);
//                mouse.setPlaceMoneyTree(false);
//                mouse.setPlaceMarkerLauncher(false);
//                sounds.get(0).play();
//            }
//        }
    }

}
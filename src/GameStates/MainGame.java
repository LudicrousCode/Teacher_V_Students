package GameStates;

import org.newdawn.slick.*;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import java.util.ArrayList;

import Towers.*;
import Zombies.*;
import Setup.*;

/**
 * Created by Andrew on 6/6/2017.
 */
public class MainGame extends BasicGameState {

    public static final int ID = 1;
    private StateBasedGame game;

    private Image background;
    private Tower[][] plants;
    private Tower[] towers;
    private ArrayList<FallingMoney> fm;
    private ArrayList<Zombie> zombies;
    private ArrayList<Projectile> projectiles;
    private ArrayList<Sound> sounds;
    private Shop shop;
    private int money, dCount;
    private Mouse mouse;
    private int GameTime = 0;
    private boolean enterPause;

    public int getID() {
        return ID;
    }

    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        this.game = stateBasedGame;
        background = new Image("res/floor.png");
        money = 50;
        dCount = 0;

        plants = new Tower[6][10];
        zombies = new ArrayList<Zombie>();
        projectiles = new ArrayList<Projectile>();
        mouse = new Mouse();
        shop = new Shop(gameContainer);

        sounds = new ArrayList<Sound>();
        sounds.add(new Sound("res/Sounds/pop2.wav")); //clicking an item in the shop
        sounds.add(new Sound("res/Sounds/Pling.wav")); //when collecting money
        sounds.add(new Sound("res/Sounds/ZombieBite.wav")); //when zombies are attacking a plant
        sounds.add(new Sound("res/Sounds/ZombieDeath.wav")); //when a zombie dies
        sounds.add(new Sound("res/Sounds/zombiesOnYourLawn1.wav")); //when you win
        sounds.add(new Sound("res/Sounds/zombotany1.wav")); //playing the game
        sounds.add(new Sound("res/Sounds/ZombieDies1.wav")); //when a zombie dies
        sounds.add(new Sound("res/Sounds/ZombieDies2.wav")); //when a zombie dies
        gameContainer.setShowFPS(false);
        towers = new Tower[10];
        fm = new ArrayList<>();

//        sounds.get(5).play();

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
        towers[0] = new MoneyTree(100, 700);
        towers[1] = new MarkerLauncher(200, 700);
        for (int i = 1; i < 11; i++) {
            graphics.setColor(Color.white);
            graphics.drawRect(i*100, 700, 100, 100);
            graphics.drawRect(i*100+60, 700, 40, 28);
            if(towers[i-1] != null)
                graphics.drawString(towers[i-1].getPrice() + "", i*100+65, 705);
        }

        //render falling money
        for(FallingMoney f: fm) {
            graphics.drawImage(new Image("res/money.png"), f.getX(), f.getY());
        }


        //render money
        graphics.setColor(Color.white);
        graphics.drawString("money: " + money, 10, 10);

        //pause button
        graphics.setColor(Color.blue);
        graphics.setLineWidth(5);
        graphics.drawRect(1120, 10, 60, 30);
        graphics.drawString("MENU", 1132, 15);
    }

    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        Input input = gameContainer.getInput();

        GameTime++;
        genFallingMoney();
        genZombies();

        if (input.isMousePressed(0)){

            //check if clicked on falling money
            for (int j = 0; j < fm.size(); j++) {
                FallingMoney f = fm.get(j);
                if(input.getMouseX() >= f.getX() && input.getMouseX() <= f.getX()+f.getPic().getWidth() && input.getMouseY() >= f.getY() && input.getMouseY() <= f.getY()+f.getPic().getHeight()){
                    System.out.println("clicked on falling money");
                    money += 25;
                    fm.remove(j);
                    j--;
                }
            }

            //check if clicked on menu button
            if(input.getMouseX() >= 1120 && input.getMouseX() <= 1180 && input.getMouseY() >= 10 && input.getMouseY() <= 30) {
                enterPause = true;
                game.enterState(4);
            }

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
                    sounds.get(1).play();
                    money += 30;
                }
            }
        }
        /**
         * move the projectiles
         *
         */
        for (Projectile a: projectiles) {
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
//            System.out.println("zombie: " + l);
            Zombie z = zombies.get(l);

            for(int m = 0; m < projectiles.size(); m++){
                if(projectiles.get(m).getX() > z.getX()-80 && projectiles.get(m).getX() < z.getX()+180
                    && projectiles.get(m).getY() == z.getY() && !(projectiles.get(m).getX() > 1100)){
                    System.out.println("z hit by projectile");
                    z.takeDamage(projectiles.get(m).getDamage()); //take damage if hit
                    projectiles.remove(m); //remove projectile from projectile list
                    m--;
                }

                else if(projectiles.size() > 0 && projectiles.get(m).getX() > 1100) {
                    System.out.println("projectile removed");
                    projectiles.remove(m);
                    m--;
                }

//                if(z.isHit(projectiles.get(m))){ //check for collisions with any projectiles
//                    System.out.println("z hit by projectile");
//                    z.takeDamage(projectiles.get(m).getDamage()); //take damage if hit
//                    projectiles.remove(m); //remove projectile from projectile list

                    //check if z is dead-dead (dead for the second time)
                    if(z.dead()){
                        int random = (int)(Math.random()*3);
                        if(random == 0){
                            sounds.get(3).play();
                        }
                        if(random == 1){
                            sounds.get(6).play();
                        }
                        if(random == 2){
                            sounds.get(7).play();
                        }
                        zombies.remove(z);

                        if(z.getPic().equals(new Image("res/zombies/drew.png")))
                            game.enterState(5);
                    }
//                }
            }
            if(z.getX()/100-1 >-1 && z.getX()<1100) {
                if (plants[z.getY() / 100 - 1][z.getX() / 100 - 1] != null) { //if square is occupied
                    System.out.println("z in occupied square");

                    if(dCount % 50 == 0) {
                        Tower p = plants[z.getY() / 100 - 1][z.getX() / 100 - 1];
                        p.takeDamage(z.getDamage()); //do damage
                        if (z.bite())
                            sounds.get(2).play();

                        //check if plant is dead
                        if (p.getHealth() <= 0) {
                            plants[z.getY() / 100 - 1][z.getX() / 100 - 1] = null; //remove
                        }
                    }
                } else {
                    z.move(); //if nothing in the way, move forward
                }
            }
            else if (z.getX()/100-1<-1){
//                GameState target = game.getState(4);
//                final long start = System.currentTimeMillis();
//                CrossStateTransition t = new CrossStateTransition(target) {
//                    @Override
//                    public boolean isComplete() {
//                        return (System.currentTimeMillis() - start > 2000);
//                    }
//
//                    @Override
//                    public void init(GameState gameState, GameState gameState1) {
//                    }
//                };
                game.enterState(2, new FadeOutTransition(Color.black,400), new FadeInTransition(Color.red, 2000));
//                game.enterState(4, t, new EmptyTransition());
            }
            else
                z.move();
        }

        //falling money
        for (int j = 0; j < fm.size(); j++) {
            FallingMoney f = fm.get(j);
            f.move();
            if(f.getX()+f.getPic().getHeight() >= 700){
                fm.remove(j);
                j--;
            }
        }

    }

    public void enter(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        System.out.println("Entered MainGame State");
        enterPause = false;
        sounds.get(5).loop();

//        genZombies();
    }

    public void genFallingMoney() throws SlickException{
        if(GameTime % 200 == 0)
            fm.add(new FallingMoney((int)(Math.random()*1100), 0, 2));
    }

    public void genZombies() throws SlickException{
        if(GameTime > 500 && GameTime % 200 == 0) {
            int rand = (int)(Math.random()*100);
            if(rand < 50) {
                zombies.add(new Drew(1200, (int) ((Math.random() * 6) + 1) * 100));
            }
            else{
                zombies.add(new Caffeinated(1200, (int) ((Math.random() * 6) + 1) * 100));
            }
        }
        if(GameTime == 2500){
            //spawn zombie boss characterized to each level
        }
//        if (GameTime >2500 && zombies.size() == 0)
//            game.enterState(4);
    }

    public void leave(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        if(!enterPause) {
            System.out.println("Left MainGame");
            GameTime = 0;
            fm.clear();

//        for (int i = 0; i < plants.length; i++) {
//            for (int j = 0; j < plants[i].length; j++) {
//                if(plants[i][j] != null) {
//                    plants[i][j] = null;
//                    System.out.println("plant removed");
//                }
//            }
//        }
            plants = new Tower[6][10];
            zombies.clear();
            projectiles.clear();
            money = 50;
        }
        sounds.get(5).stop();
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

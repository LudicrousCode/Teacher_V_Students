package Setup;

import GameStates.EndGame;
import GameStates.MainGame;
import GameStates.Title;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

import GameStates.*;

/**
 * Created by andrew_briasco on 5/8/17.
 */

/**
 * TODO update shop with pics and more towers?
 * Potentially change the game to be a stateBasedGame, then use states like menu and game and death/win screen
 */
public class Main extends StateBasedGame {

    public static final String gamename = "Hopps V Students";

    public Main(){
        super("Hopps V Students");
    }

//    private Image background;;
//    private int[][] lawn;
//    private Tower[][] plants;
//    private ArrayList<Zombie> zombies;
//    private ArrayList<Projectile> projectiles;
//    private ArrayList<Sound> sounds;
//
//    private Shop shop;
//    private int money;
//    private boolean intro;
//    private Mouse mouse;

    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
        this.addState(new Title());//0
        this.addState(new MainGame());//1
        this.addState(new EndGame());//2
        this.addState(new Logo());//3
        this.addState(new Pause());//4
        this.addState(new Win());//5
        gameContainer.setMinimumLogicUpdateInterval(20);
        gameContainer.setMaximumLogicUpdateInterval(20);
//        this.enterState(0);
    }
//    public Main(String gamename){
//        super(gamename);
//    }

//    @Override
//    public void mousePressed(int button, int x, int y){
//        //MOVED?
//        //next button
//        if(x >= 550 && x <= 650 && y >= 600 && y <= 650)
//            intro = false;
//    }


//    @Override
//    public void init(GameContainer gameContainer) throws SlickException {
//        //MOVED
////        lawn = new int[6][10];
////        money = 30;
////        intro = true;
////        plants = new Tower[6][10];
////        zombies = new ArrayList<Zombie>();
////        projectiles = new ArrayList<Projectile>();
////        mouse = new Mouse();
////        shop = new Shop(gameContainer);
////
////
////        //initalize the sound array list and fill with sounds
////        sounds = new ArrayList<Sound>();
////        sounds.add(new Sound("res/Sounds/pop2.wav"));
//
//
//    }


//    @Override
//    //MOVED
//    public void update(GameContainer gameContainer, int i) throws SlickException {
//////MOVED
////        //gets input from user
////        Input input = gameContainer.getInput();
////
////        //check if mouse clicked
////        if(input.isMousePressed(0)){
////            System.out.println("Mouse clicked");
////
////            //check if mouse is clicked on playing field
////            if (input.getMouseX()>100 && input.getMouseX()<1200 && input.getMouseY()>100 && input.getMouseY()<700){
////                System.out.println("mouse on playing field");
////
////                //check if mouse is in placing state for each tower, if true, take money, place tower and set placement to false
////                if (mouse.isPlaceMarkerLauncher()) {
////                    System.out.println("placed tower");
////                    money -= 20;
////                    mouse.setPlaceMarkerLauncher(false);
////                    plants[input.getMouseY() / 100 - 1][input.getMouseX() / 100 - 1] = new MarkerLauncher(input.getMouseX() / 100 - 1, input.getMouseY() / 100 - 1);
////                }
////
////                else if (mouse.isPlaceMoneyTree()) {
////                    money -= 10;
////                    mouse.setPlaceMoneyTree(false);
////                    plants[input.getMouseY() / 100 - 1][input.getMouseX() / 100 - 1] = new MoneyTree(input.getMouseX() / 100 - 1, input.getMouseY() / 100 - 1);
////                }
////
////                else if (mouse.isPlaceQuiz()) {
////                    money -= 30;
////                    mouse.setPlaceQuiz(false);
////                    plants[input.getMouseY() / 100 - 1][input.getMouseX() / 100 - 1] = new Quiz(input.getMouseX() / 100 - 1, input.getMouseY() / 100 - 1);
////                }
////            }
////
////            //check if mouse is over any of the shop areas, if so and have enough money set mouse state to placing that tower
////            else if (shop.getCell(0).isMouseOver()){
////                if (money - 10 >= 0) {//check if player has enough money
////                    System.out.println("Tower Bought");
////
////                    mouse.setPlaceMoneyTree(true);
////                    mouse.setPlaceMarkerLauncher(false);
////                    mouse.setPlaceQuiz(false);
////                    sounds.get(0).play();
//////                    projectiles.add(new Projectile(100, 250, new Image("res/bullet.png"), 5, 2));
////                }
////            }
////
////            else if (shop.getCell(1).isMouseOver()){
////                if (money - 20 >= 0) {
////                    System.out.println("Tower Bought");
////
////                    mouse.setPlaceMarkerLauncher(true);
////                    mouse.setPlaceMoneyTree(false);
////                    mouse.setPlaceQuiz(false);
////                    sounds.get(0).play();
////                }
////            }
////
////            else if (shop.getCell(2).isMouseOver()){
////                if (money - 30 >= 0) {
////                    System.out.println("Tower Bought");
////
////                    mouse.setPlaceQuiz(true);
////                    mouse.setPlaceMoneyTree(false);
////                    mouse.setPlaceMarkerLauncher(false);
////                    sounds.get(0).play();
////                }
////            }
////        }
////
////        /**
////         * Starting hit detection part of update()
////         * Part 1: Towers
////         *      Call each tower's attack() method
////         */
////        for (int j = 0; j < plants.length; j++) {
////            for (int k = 0; k < plants[0].length; k++){
////                Tower p = plants[j][k];
////
////                if(p != null && p.isOffensive()){ //if there's a plant in that square
////                    Projectile proj = p.attack();
////                    if(proj != null)
////                        projectiles.add(proj); //launching projectile
////                }
////            }
////        }
////        /**
////         * move the projectiles
////         *
////         */
////        for (Projectile a:projectiles) {
////            a.move();
////
////        }
////
////        /**
////         * Part 2: Zombies
////         * For each zombie:
////         *      Check if hit by projectile
////         *      Check if intersects a plant
////         *          If hit plant, do damage
////         *          Else move
////         */
////        for(int l = 0; l < zombies.size(); l++){
////            Zombie z = zombies.get(l);
////
////            for(int m = 0; m < projectiles.size(); m++){
////
////                if(z.isHit(projectiles.get(m))){ //check for collisions with any projectiles
////                    z.takeDamage(projectiles.get(m).getDamage()); //take damage if hit
////                    projectiles.remove(m); //remove projectile from projectile list
////
////                    //check if z is dead-dead (dead for the second time)
////                    if(z.dead()){
////                        zombies.remove(z);
////                    }
////                    if(projectiles.get(m).getX() > 1200)
////                        projectiles.remove(m);
////                }
////            }
////
////            if (plants[z.getY()/100-1][z.getX()/100-1] != null){ //if square is occupied
////
////                Tower p = plants[z.getY()/100-1][z.getX()/100-1];
////                p.takeDamage(z.getDamage()); //do damage
////
////                //check if plant is dead
////                if(p.getHealth() <= 0){
////                    plants[z.getY()/100-1][z.getX()/100-1] = null; //remove
////                }
////            }
////            else{
////                z.move(); //if nothing in the way, move forward
////            }
////        }
//    }


//    @Override
//    //MOVED
//    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
////        //MOVED
////
////        if(intro) {
////            graphics.setColor(Color.black);
////            graphics.fillRect(0, 0, 1200, 800);
////            graphics.setColor(Color.white);
////
////            graphics.drawString("On a warm and sunny April Fools Day, Mr. Hopps decides to pull an epic prank on the students.", 100, 100);
////            graphics.drawString("The only problem is, it's a Saturday, so there's no school. What should he do?", 100, 150);
////            graphics.drawString("Hopps creates a computer virus that will spam all the student computers with comp sci memes.", 100, 200);
////            graphics.drawString("The student computers are infected with the virus, which goes into effect soon after.", 100, 250);
////            graphics.drawString("There are so many memes that most of the computers either crash, or the memes block all other windows.", 100, 300);
////            graphics.drawString("The students are angry and confused, but when there are memes roasting the students they guess who did it.", 100, 350);
////            graphics.drawString("On the next Monday morning, as Hopps walks to his classroom, he hears the students talking about the virus.", 100, 400);
////            graphics.drawString("One of them mentions that Hopps did it. They all turn to see Hopps, and begin chasing after him.", 100, 450);
////            graphics.drawString("Luckily, Hopps has enough of a headstart to blockade himself in his classroom before all the students attack him...", 100, 500);
////
////            graphics.setColor(Color.black);
////            graphics.fillRect(550, 600, 100, 50);
////            graphics.setColor(Color.blue);
////            graphics.setLineWidth(5);
////            graphics.drawRect(550, 600, 100, 50);
////            graphics.drawString("NEXT", 581, 617);
//////            graphics.drawImage(new Image("res/bullet.png"), 500, 500);
//////            graphics.fillRect(100,100,100,100);
//////            graphics.drawImage(a.getPic(), a.getX(), a.getY());
////        }
////
////        else {
////            //floor
////            graphics.drawImage(new Image("res/floor.png"), 100, 100);
////            //render all the plants
////            for (int i = 0; i < plants.length; i++) {
////                for (int j = 0; j < plants[i].length; j++) {
////                    if (plants[i][j] != null) {
////                        graphics.drawImage(plants[i][j].getPic(), plants[i][j].getX() * 100 + 100, plants[i][j].getY() * 100 + 100);
////                    }
////                }
////            }
////            //render all the projectiles
////            for (Projectile a: projectiles) {
////                graphics.drawImage(a.getImage(), a.getX(), a.getY());
////
////            }
////
////            //render all the zombies
////            for (Zombie a : zombies) {
////                graphics.drawImage(a.getPic(), a.getX(), a.getY());
////            }
////
////            //render shop
////            for (MouseOverArea a : shop.getShop()) {
////                a.render(gameContainer, graphics);
////            }
////
////            //render money
////            graphics.setColor(Color.white);
////            graphics.drawString("money: " + money, 10, 30);
////        }
//
//    }


    public static void main(String[] args) throws SlickException{
        AppGameContainer app = new AppGameContainer(new Main());
        app.setDisplayMode(1200, 800, false);
        app.start();

    }

}
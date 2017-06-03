import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.gui.MouseOverArea;

import java.util.ArrayList;

/**
 * Created by andrew_briasco on 5/8/17.
 */

/**
 * TODO update shop with pics and more towers?
 * Potentially change the game to be a stateBasedGame, then use states like menu and game and death/win screen
 */
public class Main extends BasicGame{

    private Image background;
    private int[][] lawn;
    private Tower[][] plants;
    private ArrayList<Zombie> zombies;
    private ArrayList<Projectile> projectiles;
//    private ArrayList<MouseOverArea> shop;
    private Shop shop;
    private int money, gameTime;
    private boolean intro;
    private Mouse mouse;
    //test zombie
    private Zombie a;

    public Main(String title) throws SlickException {
        super(title);
//
//        lawn = new int[10][6];
//        money = 0;
//        intro = true;
//        plants = new Tower[10][6];
//        zombies = new ArrayList<Zombie>();
//        projectiles = new ArrayList<Projectile>();
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        lawn = new int[6][10];
        money = 30;
        intro = false;
        plants = new Tower[6][10];
        zombies = new ArrayList<Zombie>();
        projectiles = new ArrayList<Projectile>();
        mouse = new Mouse();
        shop = new Shop(gameContainer);
        gameTime = 0;
        //test zombie
//        a = new Zombie(500, 500, new Image("res/marker.png"), 0, 100, 2);

    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        //update the game time
        gameTime++;
        //gets input from user
        Input input = gameContainer.getInput();
        //check if mouse clicked
        if(input.isMousePressed(0)){
            //check if mouse is clicked on playing field
            if (input.getMouseX()>100 && input.getMouseX()<700 && input.getMouseY()>100 && input.getMouseY()<1100){
                //check if mouse is in placing state for each tower, it true, take money, place tower and set placement to false
                if (mouse.isPlaceMarkerLauncher()) {
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
            //check if mouse is over one of the shop areas, if so and have enough money set mouse state to placing that tower
            else if (shop.getCell(0).isMouseOver()){
                if (money - 10 >= 0) {//check if player has enough money
                    System.out.println("Tower Bought");
                    mouse.setPlaceMoneyTree(true);
                    mouse.setPlaceMarkerLauncher(false);
                    mouse.setPlaceQuiz(false);
                }
            }
            else if (shop.getCell(1).isMouseOver()){
                if (money - 20 >= 0) {
                    System.out.println("Tower Bought");
                    mouse.setPlaceMarkerLauncher(true);
                    mouse.setPlaceMoneyTree(false);
                    mouse.setPlaceQuiz(false);
                }
            }
            else if (shop.getCell(2).isMouseOver()){
                if (money - 30 >= 0) {
                    System.out.println("Tower Bought");
                    mouse.setPlaceQuiz(true);
                    mouse.setPlaceMoneyTree(false);
                    mouse.setPlaceMarkerLauncher(false);
                }
            }
        }
        /**
         *
         * hit detection
         *     if collision, zombies attack
         * zombies move
         */

        /**
         * For buying and clicking:
         * mousePressed() / MouseReleased to detect a click withing a certain area.
         * Also could use mouseDragged with oldX,oldY and newX,newY.
         * make sure to check that you can place a plant there (or just let it override what's there)
         */
        for (int j = 0; j < plants.length; j++) {
            for (int k = 0; k < plants[0].length; k++){
                Tower p = plants[j][k];
                if(p != null)
                    p.attack(); //launch projectile if applicable

//                for (Zombie z : zombies){
//                    if(p.isHit(z)){ //check for collisions with any zombies
////                        p.takeDamage(z); //take damage if hit by zombie
//                    }
//                }

            }
        }
        /**
         * Update the zombie array list
         * 1: check if the zombie is hit by a projectile
         * 2: move the zombie
         * 3: check if the zombie has hit a plant
         */
        for(int l = 0; l < zombies.size(); l++){
            Zombie z = zombies.get(l);
            for(int m = 0; m < projectiles.size(); m++){
                if(z.isHit(projectiles.get(m))){ //check for collisions with any projectiles
                    z.takeDamage(projectiles.get(m).getDamage()); //take damage if hit
                    //TODO check if the zombie's helath <0 and remove it if true
                }
            }
            z.move();
            if (plants[z.getY()/100-1][z.getX()/100-1] != null){
                plants[z.getY()/100-1][z.getX()/100-1].takeDamage(z.getDamage());
                //TODO check if the plant's health <0 and remove if true
                //TODO remove the projectile once it hits the zombie
            }
        }

    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        if(intro) {
            graphics.setColor(Color.black);
            graphics.fillRect(0, 0, 1200, 800);
            graphics.setColor(Color.white);
            graphics.drawString("On a warm and sunny April Fools Day, Mr. Hopps decides to pull an epic prank on the students.", 100, 100);
            graphics.drawString("The only problem is, it's a Saturday, so there's no school. What should he do?", 100, 150);
            graphics.drawString("Hopps creates a computer virus that will spam all the student computers with comp sci memes.", 100, 200);
            graphics.drawString("The student computers are infected with the virus, which goes into effect.", 100, 250);
            graphics.drawString("There are so many memes that most of the computers either crash, or block all other windows.", 100, 300);
            graphics.drawString("The students are angry and confused, but when there are memes roasting the students they guess who did it.", 100, 350);
            graphics.drawString("On the next Monday morning, as Hopps walks to his classroom he hears the students talking about the virus.", 100, 400);
            graphics.drawString("One of them mentions that Hopps did it. They all turn to see Hopps, and begin chasing after him.", 100, 450);
            graphics.drawString("Luckily, Hopps has enough of a headstart to blockade himself in his classroom before all the students attack him...", 100, 500);
//            graphics.drawImage(a.getPic(), a.getX(), a.getY());
        }
        //draw temp gameboard:
        for (int i = 100; i <800 ; i+=100) {
            graphics.drawLine(100, i, 1100, i);
        }
        for (int i = 100; i < 1200; i+=100) {
            graphics.drawLine(i,100, i, 700);
        }
        //render all the plants
        for (int i = 0; i < plants.length; i++) {
            for (int j = 0; j < plants[i].length; j++) {
                if (plants[i][j] != null){
                    graphics.drawImage(plants[i][j].getPic(),plants[i][j].getX()*100+100,plants[i][j].getY()*100+100);
                }
            }
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
        graphics.drawString("money: " + money, 10, 30);



    }

    public static void main(String[] args) throws SlickException{
        AppGameContainer app = new AppGameContainer(new Main("Teacher V Students"));
        app.setDisplayMode(1200, 800, false);
        app.start();
    }
}

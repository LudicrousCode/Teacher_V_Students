import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import java.util.ArrayList;

/**
 * Created by andrew_briasco on 5/8/17.
 */
public class Main extends BasicGame{

    private Image background;
    private int[][] lawn;
    private Tower[][] plants;
    private ArrayList<Zombies> zombies;
    private ArrayList<Projectile> projectiles;
    private int money;
    private boolean intro;

    public Main(String title) throws SlickException {
        super(title);

        lawn = new int[10][6];
        money = 0;
        intro = true;
        plants = new Tower[10][6];
        zombies = new ArrayList<Zombies>();
        projectiles = new ArrayList<Projectile>();
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {

    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        /**
         *
         * hit detection
         *     if collision, zombies attack
         * zombies move
         */

        for (int j = 0; j < plants.length; j++) {
            for (int k = 0; k < plants[0].length; k++){
                Tower p = plants[j][k];
                if(p != null)
                    p.attack(); //launch projectile if applicable

                for (Zombies z : zombies){
                    if(p.isHit(z)){ //check for collisions with any zombies
//                        p.takeDamage(z); //take damage if hit by zombie
                    }
                }

            }
        }
        for(int l = 0; l < zombies.size(); l++){
            Zombies z = zombies.get(l);
            for(int m = 0; m < projectiles.size(); m++){
                if(z.isHit(projectiles.get(m))){ //check for collisions with any projectiles
//                    z.takeDamage(projectiles.get(m)); //take damage if hit
                }
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
        }
        for (int i = 0; i < plants.length; i++) {
            for (int j = 0; j < plants[i].length; j++) {
                if (plants[i][j] != null){
//                    plants[i][j].draw
                }
            }
        }

    }

    public static void main(String[] args) throws SlickException{
        AppGameContainer app = new AppGameContainer(new Main("Test"));
        app.setDisplayMode(1200, 800, false);
        app.start();
    }
}

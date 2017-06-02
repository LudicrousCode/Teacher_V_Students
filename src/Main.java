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

    public Main(String title) throws SlickException {
        super(title);

        lawn = new int[10][6];
        money = 0;

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
         * plants attack
         * hit detection
         *     if collision, zombies attack
         * zombies move
         */

        for (int j = 0; j < plants.length; j++) {
            for (int k = 0; k < plants[0].length; k++){
                plants[j][k].shoot();
            }
        }
//        for(int l = 0; l < zombies.size(); l++){
//            for(int m = 0; m < projectiles.size(); m++)
//        }

    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, gameContainer.getWidth(), gameContainer.getHeight());
        graphics.setColor(Color.white);
        graphics.drawString("On a warm and sunny April Fools Day, Mr. Hopps is in a good mood.", 400, 400);
        graphics.drawString("He decides to pull a prank on the students of Wayland High School.", 400, 450);

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

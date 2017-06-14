package Towers;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by andrew_briasco on 6/2/17.
 */
public class MarkerLauncher extends Tower {


    public MarkerLauncher(int x, int y)throws SlickException{
        super(50, 25, new Image("res/Tower/markerLauncher.png"), x, y, 100, true);
//        int price, int damage, Image pic, int x, int y, int health, boolean b
        setCooldown(40);
    }
}

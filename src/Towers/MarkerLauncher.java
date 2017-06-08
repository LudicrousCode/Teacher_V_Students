package Towers;

import Towers.Tower;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by andrew_briasco on 6/2/17.
 */
public class MarkerLauncher extends Tower {


    public MarkerLauncher(int x, int y)throws SlickException{
        super(20, 0, new Image("res/Tower/square.png"), x, y, 50, true);
        //int price, int damage, Image pic, int x, int y

        setCooldown(200);
    }
}

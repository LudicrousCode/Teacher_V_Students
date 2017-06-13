package Towers;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by andrew_briasco on 6/2/17.
 */
public class MoneyTree extends Tower {


    public MoneyTree(int x, int y)throws SlickException{
        super(10, 0, new Image("res/Tower/moneytree.png"), x, y, 80, false);
//        int price, int damage, Image pic, int x, int y, int health, boolean b

        setCooldown(500);
    }


    public boolean genMoney(){
        if(getCooldown() == 0){
            setCooldown(500);
            return true;
        }
        else{
            setCooldown(getCooldown()-1);
            return false;
        }
    }

}

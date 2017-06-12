package Setup;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import GameStates.*;
import Towers.*;
import Zombies.*;

import java.awt.*;

/**
 * Created by andrew_briasco on 6/2/17.
 */
public class Projectile extends Sprite {

    private int damage, speed;
    private Image pic;


    public Projectile(int x, int y, Image pic, int damage, int s) throws SlickException{
        super(x, y);
        this.setPic(pic);
        speed = s;
        this.pic = pic;
        this.damage = damage;

    }

    public int getDamage() {
        return damage;
    }

    public void move(){
        this.setX(this.getX()+speed);
        resetBoundRect(new Rectangle(getX()*100+100, getY()*100+100, getPic().getWidth(), getPic().getHeight()));
    }
    public Image getImage (){
        return pic;
    }
}

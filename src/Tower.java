import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.awt.*;

/**
 * Created by clarissa_briasco on 6/1/17.
 */
public class Tower extends Sprite{

    /**
     * This class will serve as the base class for all defense towers (passive and active)
     * IDK if it'll be abstract or not
     * This class will extend Sprite
     */

    private int price, damage, health, cooldown;
    private Rectangle boundRect;

    public Tower(int price, int damage, Image pic, int x, int y, int health) throws SlickException {
        super(x, y);
        this.setPic(pic);
        this.price = price;
        this.damage = damage;
        this.health = health;
        boundRect = new Rectangle(x*100, y*100, getPic().getWidth(), getPic().getHeight());
        cooldown = 0;

    }

    public void render(){
        
    }

    public Projectile attack() throws SlickException{//check if the cooldown on launching a projectile is at zero, if true attack
        if (cooldown == 0) {
            cooldown = 100;
            return new Projectile(getX() + 25, getY() + 25, new Image("res/bullet.png"), damage);
        }
        else {
            cooldown--;
            return null;
        }
    }

    public int getPrice() {
        return price;
    }

    public int getDamage() {
        return damage;
    }

    public int getHealth() {
        return health;
    }

    public Rectangle getBoundRect() {
        return boundRect;
    }

    public void takeDamage(int d){
        health -= d;
    }
}

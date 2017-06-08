package Towers;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.awt.*;

/**
 * Created by clarissa_briasco on 6/1/17.
 */
public class Tower extends Sprite {

    /**
     * This class will serve as the base class for all defense towers (passive and active)
     * This class will extend Sprite
     */

    private int price, damage, health, cooldown;
    private Rectangle boundRect;
    private boolean isOffensive;


    public Tower(int price, int damage, Image pic, int x, int y, int health, boolean b) throws SlickException {
        super(x, y);
        this.setPic(pic);
        this.price = price;
        this.damage = damage;
        this.health = health;
        boundRect = new Rectangle(x*100, y*100, getPic().getWidth(), getPic().getHeight());
        cooldown = 200;
        isOffensive = b;

    }

    /**
     * Method for Tower attacks
     * @return Projectile to be shot, or null if cooldown isn't done
     * @throws SlickException if Image for Projectile isn't found
     */
    public Projectile attack() throws SlickException{//check if the cooldown on launching a projectile is at zero, if true attack
        if (cooldown == 0) {
            cooldown = 200;
            return new Projectile( (getX()+1) * 100 + 25, (getY()+1) * 100 + 25, new Image("res/bullet.png"), damage, 4);
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

    public boolean isOffensive(){ return isOffensive; }

    public void setOffensive(boolean b){ isOffensive = b; }

    public int getHealth() {
        return health;
    }

    /**
     * Useful for hit detection
     * @return a Rectangle that encloses this Tower
     */
    public Rectangle getBoundRect() {
        return boundRect;
    }

    /**
     * Apply damage to this Tower
     * @param d Amount of damage to be taken
     */
    public void takeDamage(int d){
        health -= d;
    }

    public void setCooldown(int c) { cooldown = c; }

    public int getCooldown() { return cooldown; }
}

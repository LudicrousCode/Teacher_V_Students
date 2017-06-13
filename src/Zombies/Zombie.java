package Zombies; /**
 * Created by rachel_chau on 6/2/17.
 */
import Setup.Sprite;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import GameStates.*;
import Setup.*;
import Towers.*;

import java.awt.*;

public class Zombie extends Sprite {

    /**
     * Base class for all students / enemies
     */

    private int speed, HP, damage, sBite;

    public Zombie(int x, int y, Image pic, int speed, int HP, int damage) throws SlickException {
        super(x, y);
        this.setPic(pic);
        this.speed = speed;
        this.HP = HP;
        this.damage = damage;
    }

    public void move(){
        setX(getX() - speed);
        resetBoundRect(new Rectangle(getX()*100+100, getY()*100+100, getPic().getWidth(), getPic().getHeight()));

    }

    public boolean dead(){
       return (HP <= 0);
    }

    /**
     * Apply damage to this Zombie
     * @param d Amount of damage to be taken
     */
    public void takeDamage(int d){
        System.out.println("Z damaged");
        HP-= d;
    }

    public int getDamage(){
        return damage;
    }

    public void getSlower(int s){ speed -= s;}

    public boolean bite(){
        if(sBite == 0) {
            sBite = 40;
            return true;
        }
        else
            sBite --;
        return false;

    }
}


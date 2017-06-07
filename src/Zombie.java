/**
 * Created by rachel_chau on 6/2/17.
 */
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Zombie extends Sprite{

    /**
     * Base class for all students / enemies
     */

    private int speed, HP, damage;

    public Zombie(int x, int y, Image pic, int speed, int HP, int damage) throws SlickException {
        super(x, y);
        this.setPic(pic);
        this.speed = speed;
        this.HP = HP;
        this.damage = damage;
    }

    public void move(){
        setX(getX() - speed);
    }

    public boolean dead(){
       return (HP <= 0);
    }

    /**
     * Apply damage to this Zombie
     * @param d Amount of damage to be taken
     */
    public void takeDamage(int d){
        HP-= d;
    }

    public int getDamage(){
        return damage;
    }

    public void getSlower(int s){ speed -= s;}


    }


/**
 * Created by rachel_chau on 6/2/17.
 */
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Zombies extends Sprite{


    private int speed, HP, lane;

    public Zombies(int x, int y, String path, int speed, int HP, int lane) throws SlickException {
        super(x, y);
        this.speed = speed;
        this.HP = HP;
        this.setPic(path);
        this.lane = lane;
    }

    public void draw(){

        getPic().draw(getX(), getY());

    }

    public void update(){

        //while if not touching a plan/tower
        setX(getX()-speed);
        //else
        attack();
    }

    public void attack(){

        //if touching a plant/tower then swtich costumes
    }

    public void slow(){
        //if hit by thing that slows things down
        int c = 5;
        setSpeed(getSpeed()-5);

        while(c > 0){
            c--;
        }
        if(c == 0){
            setSpeed(getSpeed()+5);
        }
    }

    public boolean dead(){
        if(HP <= 0){
            return true;
        }
        return false;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}

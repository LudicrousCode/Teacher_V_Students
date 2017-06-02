import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.awt.*;

/**
 * Created by andrew_briasco on 6/1/17.
 */
public class Sprite {

    private int x, y;
    private Image pic;
    private Rectangle boundRect;

    public Sprite(int x, int y, Image pic)throws SlickException{
        this.pic = pic;
        this.x = x;
        this.y = y;
        boundRect = new Rectangle(x*100, y*100, pic.getWidth(), pic.getHeight());
    }
    public void setPic(Image img)throws SlickException{
        pic = img;
    }



    public Rectangle getBounds(){
        return boundRect;
    }

    public boolean isHit(Sprite other){
        return this.getBounds().intersects(other.getBounds());
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Image getPic(){ return pic; }

}

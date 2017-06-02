import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by andrew_briasco on 6/2/17.
 */
public class MoneyTree extends Tower {


    public MoneyTree(int x, int y)throws SlickException{
        super(10, 0, new Image("res/Tower/square.png"), x, y, 20);
        //int price, int damage, Image pic, int x, int y
    }
}

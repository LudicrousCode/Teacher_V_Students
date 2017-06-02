import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by andrew_briasco on 6/2/17.
 */
public class Quiz extends Tower {


    public Quiz(int x, int y)throws SlickException{
        super(20, 0, new Image("res/Tower/quiz.png"), x, y, 100);
        //int price, int damage, Image pic, int x, int y

    }
}

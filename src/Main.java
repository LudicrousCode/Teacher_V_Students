import org.newdawn.slick.*;

/**
 * Created by andrew_briasco on 5/8/17.
 */
public class Main extends BasicGame{

private Image background;

    public Main(String title){
        super(title);
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        System.out.println("It works");

    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {

    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {

    }

    public static void main(String[] args) throws SlickException{
        AppGameContainer app = new AppGameContainer(new Main("Test"));
        app.setDisplayMode(400, 100, false);
        app.start();
    }
}

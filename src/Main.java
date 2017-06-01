import org.newdawn.slick.*;

/**
 * Created by andrew_briasco on 5/8/17.
 */
public class Main extends BasicGame{

    private Image background;
    private int[][] lawn;
    private int money;

    public Main(String title){
        super(title);

        lawn = new int[10][6];
        money = 0;
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
        app.setDisplayMode(1200, 800, false);
        app.start();
    }
}

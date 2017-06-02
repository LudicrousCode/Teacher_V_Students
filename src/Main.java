import org.newdawn.slick.*;

/**
 * Created by andrew_briasco on 5/8/17.
 */
public class Main extends BasicGame{

    private Image background;
    private int[][] lawn;
    private Tower[][] plants;
    private int money;

    Zombies a = new Zombies(500, 500, "blank.png", 5, 100);

    public Main(String title) throws SlickException {
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
        for (int i = 0; i < plants.length; i++) {
            for (int j = 0; j < plants[i].length; j++) {
                if (plants[i][j] != null){
//                    plants[i][j].
                }
            }
        }

    }

    public static void main(String[] args) throws SlickException{
        AppGameContainer app = new AppGameContainer(new Main("Test"));
        app.setDisplayMode(1200, 800, false);
        app.start();
    }
}

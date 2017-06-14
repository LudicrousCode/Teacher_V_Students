package Setup;

import org.newdawn.slick.*;
import org.newdawn.slick.Image;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.StateBasedGame;
import java.awt.*;
import java.util.ArrayList;

import GameStates.*;
import Towers.*;
import Zombies.*;

/**
 * Created by Andrew on 6/2/2017.
 */
public class Shop{
    private ArrayList<MouseOverArea> shop;

    public Shop(GameContainer c)throws SlickException{
        shop = new ArrayList<MouseOverArea>();
        shop.add(new MouseOverArea(c, new Image("res/tower/moneyTree.png"), 100, 700, 100, 100));
        shop.add(new MouseOverArea(c, new Image("res/tower/markerLauncher.png"), 200, 700, 100, 100));
        shop.add(new MouseOverArea(c, new Image("res/tower/quiz.png"), 300, 700, 100, 100));
        shop.add(new MouseOverArea(c, new Image("res/blank.png"), 400, 700, 100, 100));
        shop.add(new MouseOverArea(c, new Image("res/blank.png"), 500, 700, 100, 100));
        shop.add(new MouseOverArea(c, new Image("res/blank.png"), 600, 700, 100, 100));

    }
    //
    public MouseOverArea getCell(int cell){
        return shop.get(cell);
    }

    public ArrayList<MouseOverArea> getShop() {
        return shop;
    }
}

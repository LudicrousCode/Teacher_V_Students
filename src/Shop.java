import org.newdawn.slick.*;
import org.newdawn.slick.Image;
import org.newdawn.slick.gui.MouseOverArea;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Andrew on 6/2/2017.
 */
public class Shop {
    private ArrayList<MouseOverArea> shop;

    public Shop(GameContainer c)throws SlickException{
        shop = new ArrayList<MouseOverArea>();
        shop.add(new MouseOverArea(c, new Image("res/square.png"), 100, 700, 100, 100));
        shop.add(new MouseOverArea(c, new Image("res/square.png"), 200, 700, 100, 100));
        shop.add(new MouseOverArea(c, new Image("res/square.png"), 300, 700, 100, 100));
        shop.add(new MouseOverArea(c, new Image("res/square.png"), 400, 700, 100, 100));
        shop.add(new MouseOverArea(c, new Image("res/square.png"), 500, 700, 100, 100));
        shop.add(new MouseOverArea(c, new Image("res/square.png"), 600, 700, 100, 100));

    }
    public MouseOverArea getCell(int cell){
        return shop.get(cell);
    }

    public ArrayList<MouseOverArea> getShop() {
        return shop;
    }
}

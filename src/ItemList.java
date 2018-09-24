
import java.awt.image.BufferedImage;
import java.util.ArrayList;


/**
 *
 * @author Kyky
 */


public class ItemList {
    
    public String name;
    public int price;
    public BufferedImage img;
    
    public ItemList (String name, int price, BufferedImage img) {
        this.name = name;
        this.price = price;
        this.img = img;
    }
    public static ArrayList<ItemList> lists = new ArrayList<>();
    
}

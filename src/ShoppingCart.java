
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kyky
 */
public class ShoppingCart {
   public String name;
    public int price;
    public BufferedImage img;
    
    public ShoppingCart (String name, int price, BufferedImage img) {
        this.name = name;
        this.price = price;
        this.img = img;
    }
    public static ArrayList<ItemList> lists = new ArrayList<>();
    
}

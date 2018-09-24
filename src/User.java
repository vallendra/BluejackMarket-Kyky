
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


class User {
    public String username;
    public String password;
    
    public User (String username, String password) {
        this.username = username;
        this.password = password;
    }
    public static ArrayList<User> users = new ArrayList<>();
  
}

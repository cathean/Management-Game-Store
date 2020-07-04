/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managementgamestore;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ivans
 */
public class DBManager {
    public static String usr = "";
    public static String pwd = "";
    public static String host = "";
    public static String db = "";
    public static Connection conn = null;
    
    public static void initDB(String username, String pass, String hostname, String database) {
        usr = username;
        pwd = pass;
        hostname = host;
        db = database;

        // Initializing the driver
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        }
        catch(ClassNotFoundException ex) {
           System.out.println("Error: unable to load driver class!");
           System.exit(1);
        }
        catch(IllegalAccessException ex) {
           System.out.println("Error: access problem while loading!");
           System.exit(2);
        }
        catch(InstantiationException ex) {
           System.out.println("Error: unable to instantiate driver!");
           System.exit(3);
        }
        
        // Make connection to the database
        try {
            System.out.println("Connecting to a " + db + " database...");
            
            String url = "jdbc:mysql://" + host + ":3306/" + db;
            conn = DriverManager.getConnection(url, usr, pwd);
            
            System.out.println("Connected database successfully...");
            System.out.println("Inserting records into the table...");
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void saveGameData() {
        
    }
    
    public static void main(String[] args) {
        initDB("root", "", "localhost", "gamestore");
    }
}

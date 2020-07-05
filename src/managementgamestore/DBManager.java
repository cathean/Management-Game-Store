/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managementgamestore;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ivans
 */
class DBManager {
    public static String usr = "root";
    public static String pwd = "";
    public static String host = "localhost";
    public static String db = "gamestore";
    
    // These below are singleton application
    private static DBManager dbm;
    
    private DBManager() {};
    
    public static DBManager getInstance() {
        if(dbm == null)
            dbm = new DBManager();
        return dbm;
    }
    
    public static Connection getConnection(String username, String pass, String hostname, String database) {
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
            Connection conn = DriverManager.getConnection(url, usr, pwd);
            
            System.out.println("Connected database successfully...");   
            return conn;
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public ArrayList<Float> fetchPriceAndTaxGameByID(long id_game) {
        Connection conn = this.getConnection(usr, pwd, host, db);
        String query = "SELECT * FROM `gamestore`.`game` WHERE `game`.`id_game`=?";
        ArrayList<Float> arr = new ArrayList<Float>();
        
        try {
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            
            preparedStmt.setLong(1, id_game);
            ResultSet rs = preparedStmt.executeQuery();
            
            if(rs.next()) {
                System.out.println("The game already exist");
                arr.add(rs.getFloat("harga"));
                arr.add(rs.getFloat("pajak"));
            } else {
                System.out.println("The game not exist yet");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SaveStockWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return arr;
    }
    
    public void saveGameData(long id_game, float harga, String nama, String deskripsi, String genre, String platform, float pajak) {
        Connection conn = this.getConnection(usr, pwd, host, db);
        String query =
                "INSERT INTO `gamestore`.`game` "
                + "(`id_game`, `harga`, `nama_game`, `deskripsi`, `genre`, `platform`, `pajak`) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try {
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            
            preparedStmt.setLong(1, id_game);
            preparedStmt.setFloat(2, harga);
            preparedStmt.setString(3, nama);
            preparedStmt.setString(4, deskripsi);
            preparedStmt.setString(5, genre);
            preparedStmt.setString(6, platform);
            preparedStmt.setFloat(7, pajak);
            
            preparedStmt.execute();
            System.out.println("Succesfully save it into the game table!");
        } catch (SQLException ex) {
            Logger.getLogger(SaveStockWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void saveGameCode(ArrayList<String> game_code, long id_game) {
        Connection conn = this.getConnection(usr, pwd, host, db);
        String query = "INSERT INTO `gamestore`.`game_code` (`game_code`, `id_game`) VALUES (?, ?)";
        
        try {
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            
            for(int i = 0; i < game_code.size(); i++) {
                preparedStmt.setString(1, game_code.get(i));
                preparedStmt.setLong(2, id_game);
                
                preparedStmt.addBatch();
            }
            
            preparedStmt.execute();
            System.out.println("Succesfully save it into the game_code table!");
        } catch (SQLException ex) {
            Logger.getLogger(SaveStockWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) {
        //initDB("root", "", "localhost", "gamestore");
    }
}

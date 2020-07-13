/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managementgamestore;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Date;
import java.text.SimpleDateFormat;
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
    public static UserStruct admin = null;
    
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
            System.out.println("///////////////////////////////////"); 
            return conn;
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public ArrayList<Float> fetchPriceAndTaxGameByID(long id_game) {
        Connection conn = DBManager.getConnection(usr, pwd, host, db);
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
                System.out.println("The game not exist yet!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SaveStockWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return arr;
    }
    
    public int fetchGameStockCount(long id_game) {
        Connection conn = this.getConnection(usr, pwd, host, db);
        String query = "SELECT COUNT(*) as stock FROM `gamestore`.`game_code`, `gamestore`.`game` "
                + "WHERE `game_code`.id_game=`game`.`id_game` AND `game`.id_game=?";
        int count = 0;
        
        try {
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            
            preparedStmt.setLong(1, id_game);
            ResultSet rs = preparedStmt.executeQuery();
            
            if(rs.next()) {
                System.out.println("Count fetched id " + id_game + " : " + rs.getInt("stock"));
                count = rs.getInt("stock");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return count;
    }
    
    public ArrayList<GameStruct> fetchGameStock() {
        Connection conn = this.getConnection(usr, pwd, host, db);
        String query = "SELECT * FROM `gamestore`.`game`";
        ArrayList<GameStruct> gs = new ArrayList<GameStruct>();
        
        try {
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            
            ResultSet rs = preparedStmt.executeQuery();
            
            while(rs.next()) {
                gs.add(new GameStruct(rs.getLong("id_game")
                        , rs.getString("nama_game")
                        , rs.getString("deskripsi")
                        , rs.getFloat("harga")
                        , rs.getFloat("pajak")
                        , rs.getString("genre")
                        , rs.getString("platform")
                        , rs.getString("url")
                        , rs.getInt("id_diskon")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return gs;
    }
    
    public ArrayList<UserStruct> fetchAdmin() {
        Connection conn = this.getConnection(usr, pwd, host, db);
        String query = "SELECT * FROM `gamestore`.`admin`";
        ArrayList<UserStruct> adminList = new ArrayList<UserStruct>();
        
        try {
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            ResultSet rs = preparedStmt.executeQuery();
            
            while(rs.next()) {
                adminList.add(new UserStruct(
                        rs.getInt("id_admin"), 
                        rs.getString("Nama"), 
                        rs.getString("Kontak"), 
                        rs.getString("username"), 
                        rs.getString("password"), 
                        rs.getString("tipe")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return adminList;
    }
    
    public int fetchLatestOrderID() {
        Connection conn = this.getConnection(usr, pwd, host, db);
        String query = "SELECT MAX(id_pesanan) AS id FROM `gamestore`.`pemesanan`";
        int id = -1;
        
        try {
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            ResultSet rs = preparedStmt.executeQuery();
            
            if(rs.next())
                id = rs.getInt("id");
            else
                System.out.println("There's is no latest order!");
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return id;
    }
    
        public int fetchLatestCustomerID() {
        Connection conn = this.getConnection(usr, pwd, host, db);
        String query = "SELECT MAX(id_pembeli) AS id FROM `gamestore`.`pembeli`";
        int id = -1;
        
        try {
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            ResultSet rs = preparedStmt.executeQuery();
            
            if(rs.next())
                id = rs.getInt("id");
            else
                System.out.println("There's is no latest customer!");
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return id;
    }
    
    public Timestamp fetchTimeStampOrderByID(int id_pesanan) {
        Connection conn = this.getConnection(usr, pwd, host, db);
        String query = "SELECT `tgl_pembelian` FROM `pemesanan` WHERE `id_pesanan`=?";
        
        try {
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            
            preparedStmt.setInt(1, id_pesanan);
            ResultSet rs = preparedStmt.executeQuery();
            
            if(rs.next())
                return rs.getTimestamp("tgl_pembelian");
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public FinalOrderStruct fetchFinalOrder() {
        Connection conn = this.getConnection(usr, pwd, host, db);
        String query = "SELECT `total_harga`, `kuantitas_game`, `kuantitas_voucher`,`id_voucher`, `id_game` "
                + "FROM `detail_produk` WHERE `id_pesanan`=?";
        ArrayList<Long> id_gameList = new ArrayList<Long>();
        ArrayList<Integer> qty_gameList = new ArrayList<Integer>();
        ArrayList<Integer> id_vouchList = new ArrayList<Integer>();
        ArrayList<Integer> qty_vouchList = new ArrayList<Integer>();
        ArrayList<FinalProductStruct> product = new ArrayList<FinalProductStruct>();
        
        try {
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            
            preparedStmt.setInt(1, fetchLatestOrderID());
            ResultSet rs = preparedStmt.executeQuery();
            
            while(rs.next()) {
                // Get integer value from column id_game
                rs.getInt("id_game");
                
                // If it's null do something to voucher
                if(rs.wasNull()) {
                    // Manage voucher
                    id_vouchList.add(rs.getInt("id_voucher"));
                    qty_vouchList.add(rs.getInt("kuantitas_voucher"));
                    System.out.println("id : " + id_vouchList.get(id_vouchList.size() - 1) + " / qty : " + qty_vouchList.get(qty_vouchList.size() - 1));
                } else {
                    // Manage game
                    id_gameList.add(rs.getLong("id_game"));
                    qty_gameList.add(rs.getInt("kuantitas_game"));
                    System.out.println("id : " + id_gameList.get(id_gameList.size() - 1) + " / qty : " + qty_gameList.get(qty_gameList.size() - 1));
                }
            }
            
            // Make new query for game
            for(int i = 0; i < id_gameList.size(); i++) {             
                query = "SELECT `nama_game`, `harga` FROM `game` WHERE `id_game`=?";
                preparedStmt = conn.prepareStatement(query);

                preparedStmt.setLong(1, id_gameList.get(i));
                rs = preparedStmt.executeQuery();
                
                if(rs.next()) {
                    product.add(new FinalProductStruct(rs.getString("nama_game"), qty_gameList.get(i), rs.getFloat("harga"), qty_gameList.get(i) * rs.getFloat("harga")));
                    System.out.println("Product added! : " + product.get(product.size() - 1).nama_produk);
                }
            }
            
            // make new query for voucher
            for(int i = 0; i < id_vouchList.size(); i++) {             
                query = "SELECT `jenis_game`, `harga` FROM `voucher` WHERE `id_voucher`=?";
                preparedStmt = conn.prepareStatement(query);

                preparedStmt.setInt(1, id_vouchList.get(i));
                rs = preparedStmt.executeQuery();
                
                if(rs.next()) {
                    product.add(new FinalProductStruct(rs.getString("jenis_game"), qty_vouchList.get(i), rs.getFloat("harga"), qty_vouchList.get(i) * rs.getFloat("harga")));
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        float totalHarga = 0;
        
        for(int i = 0; i < product.size(); i++) {
            totalHarga = totalHarga + product.get(i).total;
        }
        
        return new FinalOrderStruct(fetchLatestOrderID(), 
                fetchTimeStampOrderByID(fetchLatestOrderID()),
                totalHarga,
                product);
    }
    
    public String[] fetchPaymentMethod() {
        Connection conn = this.getConnection(usr, pwd, host, db);
        String query = "SELECT `jenis` FROM `jenis_pembayaran`";
        ArrayList<String> payList = new ArrayList<String>();
        
        try {
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            ResultSet rs = preparedStmt.executeQuery();
            
            while(rs.next()) {
                payList.add(rs.getString("jenis"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return payList.toArray(new String[payList.size()]);
    }
    
    public int fetchPaymentMethodID(String jenis) {
        Connection conn = this.getConnection(usr, pwd, host, db);
        String query = "SELECT `id_pembayaran` FROM `jenis_pembayaran` WHERE `jenis`=?";
        int id_pembayaran = -1;
        
        try {
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            
            preparedStmt.setString(1, jenis);
            ResultSet rs = preparedStmt.executeQuery();
            
            if(rs.next()) {
                id_pembayaran = rs.getInt("id_pembayaran");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return id_pembayaran;
    }
    
    
    
    public void saveGameData(long id_game, float harga, String nama, String deskripsi, String genre, String platform, float pajak, String url) {
        Connection conn = this.getConnection(usr, pwd, host, db);
        String query =
                "INSERT INTO `gamestore`.`game` "
                + "(`id_game`, `harga`, `nama_game`, `deskripsi`, `genre`, `platform`, `pajak`, `url`) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try {
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            
            preparedStmt.setLong(1, id_game);
            preparedStmt.setFloat(2, harga);
            preparedStmt.setString(3, nama);
            preparedStmt.setString(4, deskripsi);
            preparedStmt.setString(5, genre);
            preparedStmt.setString(6, platform);
            preparedStmt.setFloat(7, pajak);
            preparedStmt.setString(8, url);
            
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
                
                preparedStmt.execute();
            }
            
            System.out.println("Succesfully save it into the game_code table!");
        } catch (SQLException ex) {
            Logger.getLogger(SaveStockWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void saveDetailProduct(float harga, int kuantitas_game, int kuantitas_voucher, int id_voucher, long id_game, int id_pesanan) {
        Connection conn = this.getConnection(usr, pwd, host, db);
        String query = "INSERT INTO `gamestore`.`detail_produk` "
                + "(`total_harga`, `kuantitas_game`, `kuantitas_voucher`, `id_voucher`, `id_game`, `id_pesanan`) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        
        try { 
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            
            preparedStmt.setFloat(1, harga);
            
            if(kuantitas_game != -1) 
                preparedStmt.setInt(2, kuantitas_game); 
            else 
                preparedStmt.setNull(2, Types.INTEGER);
            
            if(kuantitas_voucher != -1) 
                preparedStmt.setInt(3, kuantitas_voucher); 
            else 
                preparedStmt.setNull(3, Types.INTEGER);
                        
            if(id_voucher != -1) 
                preparedStmt.setInt(4, id_voucher); 
            else 
                preparedStmt.setNull(4, Types.INTEGER);
                        
            if(id_game != -1) 
                preparedStmt.setLong(5, id_game); 
            else 
                preparedStmt.setNull(5, Types.INTEGER);
            
            preparedStmt.setInt(6, id_pesanan);
            
            preparedStmt.execute();
            System.out.println("Succesfully save it into the detail_produk table!");
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void saveOrder(int id_admin) {
        Connection conn = this.getConnection(usr, pwd, host, db);
        String query = "INSERT INTO `gamestore`.`pemesanan` (`tgl_pembelian`, `id_admin`) VALUES (?, ?)";
        
        try {
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            
            preparedStmt.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            preparedStmt.setInt(2, id_admin);
            preparedStmt.execute();
            System.out.println("Succesfully save it into the pemesanan table!");
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void saveCustomer(String nama, String email, int id_pembayaran) {
        Connection conn = this.getConnection(usr, pwd, host, db);
        String query = "INSERT INTO `gamestore`.`pembeli` "
                + "(`nama`, `email`, `id_pembayaran`) "
                + "VALUES (?, ?, ?)";
        
         try {
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            
            preparedStmt.setString(1, nama);
            preparedStmt.setString(2, email);
            preparedStmt.setInt(3, id_pembayaran);
            preparedStmt.execute();
            System.out.println("Succesfully save it into the pembeli table!");
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
    
    public void saveAdmin(String nama, String kontak, String username, String password, String tipe) {
        Connection conn = this.getConnection(usr, pwd, host, db);
        String query = "INSERT INTO `gamestore`.`admin` "
                + "(`Nama`, `Kontak`, `username`, `password`, `tipe`) "
                + "VALUES (?, ?, ?, ?, ?)";
        
        try {
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            
            preparedStmt.setString(1, nama);
            preparedStmt.setString(2, kontak);
            preparedStmt.setString(3, username);
            preparedStmt.setString(4, password);
            preparedStmt.setString(5, tipe);
            preparedStmt.execute();
            System.out.println("Succesfully save it into the admin table!");
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateOrder(float jumlah_harga, int id_pembeli, int id_pesanan) {
        Connection conn = this.getConnection(usr, pwd, host, db);
        String query = "UPDATE `gamestore`.`pemesanan` SET `jumlah_harga` = ? , `id_pembeli` = ? WHERE `id_pesanan` = ?";
        try {
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            
            preparedStmt.setFloat(1, jumlah_harga);
            preparedStmt.setInt(2, id_pembeli);
            preparedStmt.setInt(3, id_pesanan);
            preparedStmt.execute();
            System.out.println("Order updated!");
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void revealGameCode(long id_game, int qty) {
        Connection conn = this.getConnection(usr, pwd, host, db);
        String query = "SELECT `id_game_code`, `game_code` FROM `game_code` WHERE `id_game`=? LIMIT ?";
        
        try {
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            
            preparedStmt.setLong(1, id_game);
            preparedStmt.setInt(2, qty);
            ResultSet rs = preparedStmt.executeQuery();
            
            while(rs.next()) {
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void login(String username, String pass) {
        Connection conn = this.getConnection(usr, pwd, host, db);
        String query = "SELECT * FROM `gamestore`.`admin` WHERE username=? AND PASSWORD=?";
        
        try {
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            
            preparedStmt.setString(1, username);
            preparedStmt.setString(2, pass);
            ResultSet rs = preparedStmt.executeQuery();
            
            if(rs.next()) {
                System.out.println("Login Succcess!");
                admin = new UserStruct(
                        rs.getInt("id_admin"), 
                        rs.getString("Nama"), 
                        rs.getString("Kontak"), 
                        rs.getString("username"), 
                        rs.getString("password"), 
                        rs.getString("tipe"));
            } else
                System.out.println("Login Failed!");
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) {
        //initDB("root", "", "localhost", "gamestore");
    }
}

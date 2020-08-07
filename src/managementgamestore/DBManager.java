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
    public static String usr = "kodingan_gamestore";
    public static String pwd = "comforteagle19";
    public static String host = "koding2an.web.id";
    public static String port = "3306";
    public static String db = "kodingan_gamestore";
    public static UserStruct admin;
    
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
            
            String url = "jdbc:mysql://" + host + ":" + port + "/" + db;
            Connection conn = DriverManager.getConnection(url, usr, pwd);
            
            System.out.println("Connected database successfully...");   
            System.out.println("///////////////////////////////////"); 
            return conn;
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public void createTrigGame() {
        try {
            Connection conn = DBManager.getConnection(usr, pwd, host, db);
            String query = "CREATE OR REPLACE TRIGGER update_game AFTER UPDATE on `game` FOR EACH ROW INSERT INTO history_game VALUES (OLD.id_game, OLD.nama_game, OLD.harga, OLD.pajak, '" + admin.name + "')";
            conn.createStatement().execute(query);
            System.out.println("Succesfully triggered history_game!");
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void createTrigUser(){
        try {
            Connection conn = DBManager.getConnection(usr, pwd, host, db);
            String query = "CREATE OR REPLACE TRIGGER update_user AFTER INSERT on `admin` FOR EACH ROW INSERT INTO history_admin VALUES (Id_admin, Nama, Kontak, '" + admin.name + "')";
            conn.createStatement().execute(query);
            System.out.println("Succesfully triggered history_user!");
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<Float> fetchPriceAndTaxGameByID(long id_game) {
        Connection conn = DBManager.getConnection(usr, pwd, host, db);
        String query = "SELECT * FROM game WHERE `game`.`id_game`=?";
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
        String query = "SELECT COUNT(*) as stock FROM game_code, game "
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
        String query = "SELECT * FROM game";
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
    
    public ArrayList<String> fetchCode(long id_game) {
        Connection conn = this.getConnection(usr, pwd, host, db);
        String query = "SELECT game_code FROM game_code WHERE `game_code`.`id_game`=?";
        ArrayList<String> s = new ArrayList<String>();
        
        try {
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            
            preparedStmt.setLong(1, id_game);
            ResultSet rs = preparedStmt.executeQuery();
            
            while(rs.next()) {
                s.add(rs.getString("game_code"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return s;
    }
    
    public ArrayList<UserStruct> fetchAdmin() {
        Connection conn = this.getConnection(usr, pwd, host, db);
        String query = "SELECT * FROM admin";
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
        String query = "SELECT MAX(id_pesanan) AS id FROM pemesanan";
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
        String query = "SELECT MAX(id_pembeli) AS id FROM pembeli";
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
        String query = "SELECT `total_harga`, `kuantitas_game`, `id_game` "
                + "FROM `detail_produk` WHERE `id_pesanan`=?";
        ArrayList<Long> id_gameList = new ArrayList<Long>();
        ArrayList<Integer> qty_gameList = new ArrayList<Integer>();
        ArrayList<FinalProductStruct> product = new ArrayList<FinalProductStruct>();
        
        try {
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            
            preparedStmt.setInt(1, fetchLatestOrderID());
            ResultSet rs = preparedStmt.executeQuery();
            
            while(rs.next()) {
                // Manage game
                id_gameList.add(rs.getLong("id_game"));
                qty_gameList.add(rs.getInt("kuantitas_game"));
                System.out.println("id : " + id_gameList.get(id_gameList.size() - 1) + " / qty : " + qty_gameList.get(qty_gameList.size() - 1));
            }
            
            // Make new query for game
            for(int i = 0; i < id_gameList.size(); i++) {             
                query = "SELECT `id_game`, `nama_game`, `harga` FROM `game` WHERE `id_game`=?";
                preparedStmt = conn.prepareStatement(query);

                preparedStmt.setLong(1, id_gameList.get(i));
                rs = preparedStmt.executeQuery();
                
                if(rs.next()) {
                    product.add(new FinalProductStruct(rs.getLong("id_game"), rs.getString("nama_game"), qty_gameList.get(i), rs.getFloat("harga"), qty_gameList.get(i) * rs.getFloat("harga")));
                    System.out.println("Product added! : " + product.get(product.size() - 1).nama_produk);
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
    
    public ArrayList<PaymentStruct> fetchPaymentMethodList() {
        Connection conn = this.getConnection(usr, pwd, host, db);
        String query = "SELECT * FROM `jenis_pembayaran`";
        ArrayList<PaymentStruct> ps = new ArrayList<PaymentStruct>();
        
        try {
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            ResultSet rs = preparedStmt.executeQuery();
            
            while(rs.next()) {
                ps.add(new PaymentStruct(rs.getInt("id_pembayaran"), rs.getString("jenis"), rs.getString("no_rek")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return ps;
    }
    
    public ArrayList<TransStruct> fetchTransLog() {
        Connection conn = this.getConnection(usr, pwd, host, db);
        String query = "SELECT * FROM `pemesanan`";
        ArrayList<TransStruct> ts = new ArrayList<TransStruct>();
        
        
        try {
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            ResultSet rs = preparedStmt.executeQuery();
            
            while(rs.next()) {
                ts.add(new TransStruct(rs.getInt("id_pesanan"),
                        rs.getTimestamp("tgl_pembelian").toString(), 
                        rs.getString("nama_pembeli"), 
                        rs.getString("email"), 
                        rs.getString("nama_karyawan"), 
                        rs.getFloat("jumlah_harga"), 
                        rs.getString("metode"), 
                        rs.getString("status"), 
                        rs.getInt("id_admin"), 
                        rs.getInt("id_pembeli")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return ts;
    }
    
    public void saveGameData(long id_game, float harga, String nama, String deskripsi, String genre, String platform, float pajak, String url) {
        Connection conn = this.getConnection(usr, pwd, host, db);
        String query =
                "INSERT INTO `game` "
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
        String query = "INSERT INTO `game_code` (`game_code`, `id_game`) VALUES (?, ?)";
        
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
    
    public void saveDetailProduct(float harga, int kuantitas_game, long id_game, int id_pesanan) {
        Connection conn = this.getConnection(usr, pwd, host, db);
        String query = "INSERT INTO `detail_produk` "
                + "(`total_harga`, `kuantitas_game`, `id_game`, `id_pesanan`) "
                + "VALUES (?, ?, ?, ?)";
        
        try { 
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            
            preparedStmt.setFloat(1, harga);
            
            if(kuantitas_game != -1) 
                preparedStmt.setInt(2, kuantitas_game); 
            else 
                preparedStmt.setNull(2, Types.INTEGER);
                        
            if(id_game != -1) 
                preparedStmt.setLong(3, id_game); 
            else 
                preparedStmt.setNull(3, Types.INTEGER);
            
            preparedStmt.setInt(4, id_pesanan);
            
            preparedStmt.execute();
            System.out.println("Succesfully save it into the detail_produk table!");
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void saveOrder(int id_admin) {
        Connection conn = this.getConnection(usr, pwd, host, db);
        String query = "INSERT INTO `pemesanan` "
                + "(`tgl_pembelian`, `nama_karyawan`, `status`, `id_admin`) "
                + "VALUES (?, ?, ?, ?); ";
        
        try {
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            
            preparedStmt.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            preparedStmt.setString(2, this.admin.name);
            preparedStmt.setString(3, "ONGOING");
            preparedStmt.setInt(4, id_admin);
            preparedStmt.execute();
            System.out.println("Succesfully save it into the pemesanan table!");
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void saveCustomer(String nama, String email, int id_pembayaran) {
        Connection conn = this.getConnection(usr, pwd, host, db);
        String query = "INSERT INTO `pembeli` "
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
        String query = "INSERT INTO `admin` "
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
    
    public void savePayment(String name, String no) {
        Connection conn = this.getConnection(usr, pwd, host, db);
        String query = "INSERT INTO `jenis_pembayaran` (`jenis`, `no_rek`) VALUES (?, ?)";
        
        try {
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            
            preparedStmt.setString(1, name);
            preparedStmt.setString(2, no);
            preparedStmt.execute();
            System.out.println("Succesfully save it into the pembayaran table!");
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void delAdmin(long id_admin){
        Connection conn = this.getConnection(usr, pwd, host, db);
        String query = "DELETE FROM admin WHERE `id_admin` = ?";
        try{
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setLong(1, id_admin);
            preparedStmt.execute();
            System.out.println("Succesfully deleted rows in admin list");
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void delDetailProduct(int id_pesanan) {
        Connection conn = this.getConnection(usr, pwd, host, db);
        String query = "DELETE FROM detail_produk WHERE `id_pesanan` = ?";
        try {
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, id_pesanan);
            preparedStmt.execute();
            System.out.println("Succesfully deleted rows in detail_produk");
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void delGameList(long id_game){
        Connection conn = this.getConnection(usr, pwd, host, db);
        String query = "DELETE FROM game WHERE `id_game` = ?";
        try{
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setLong(1, id_game);
            preparedStmt.execute();
            System.out.println("Succesfully deleted rows in game list");
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void delPayment(int id_payment) {
        Connection conn = this.getConnection(usr, pwd, host, db);
        String query = "DELETE FROM jenis_pembayaran WHERE `id_pembayaran` = ?";
        
        try {
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, id_payment);
            preparedStmt.execute();
            System.out.println("Succesfully delete rows in pembayaran");
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void findIdGame(String nama_game){
        Connection conn = this.getConnection(usr, pwd, host, db);
    }
    
    public void updateOrder(String nama_pembeli, String email, float jumlah_harga, String metode, int id_pembeli, int id_pesanan) {
        Connection conn = this.getConnection(usr, pwd, host, db);
        String query = "UPDATE `pemesanan` SET "
                + "`nama_pembeli` = ? , "
                + "`email` = ? , "
                + "`jumlah_harga` = ? , "
                + "`metode` = ? , "
                + "`status` = 'DONE' , "
                + "`id_pembeli` = ? "
                + "WHERE `id_pesanan` = ?; ";
        try {
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            
            preparedStmt.setString(1, nama_pembeli);
            preparedStmt.setString(2, email);
            preparedStmt.setFloat(3, jumlah_harga);
            preparedStmt.setString(4, metode);
            preparedStmt.setInt(5, id_pembeli);
            preparedStmt.setInt(6, id_pesanan);
            preparedStmt.execute();
            System.out.println("Order updated!");
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateGameCodeList(ArrayList<String> code_list, long id_game) {
        Connection conn = this.getConnection(usr, pwd, host, db);
        String query = "DELETE FROM `game_code` WHERE `id_game`=?";
        
        try {
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            
            preparedStmt.setLong(1, id_game);
            preparedStmt.execute();
            System.out.println("Succesfully delete all code list!");
            saveGameCode(code_list, id_game);
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updatePaymentMethod(int id_payment, String name, String norek) {
        Connection conn = this.getConnection(usr, pwd, host, db);
        String query = "UPDATE `jenis_pembayaran` SET "
                + "`jenis` = ? , `no_rek` = ? "
                + "WHERE `id_pembayaran` = ? ";
        
        try {
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            
            preparedStmt.setString(1, name);
            preparedStmt.setString(2, norek);
            preparedStmt.setInt(3, id_payment);
            preparedStmt.execute();
            System.out.println("Succesfully updated pembayaran!");
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updatePriceTaxGame(float harga, float pajak, long id_game) {
        Connection conn = this.getConnection(usr, pwd, host, db);
        String query = "UPDATE `game` SET `harga` = ? , `pajak` = ? WHERE `id_game` = ?";
        
        try {
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            
            preparedStmt.setFloat(1, harga);
            preparedStmt.setFloat(2, pajak);
            preparedStmt.setLong(3, id_game);
            preparedStmt.execute();
            System.out.println("Game details updated!");
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<String> revealGameCode(long id_game, int qty) {
        Connection conn = this.getConnection(usr, pwd, host, db);
        String query = "SELECT `id_game_code`, `game_code` FROM `game_code` WHERE `id_game`=? LIMIT ?";
        ArrayList<String> codeList = new ArrayList<String>();
        ArrayList<Integer> idList = new ArrayList<Integer>();
        
        try {
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            
            preparedStmt.setLong(1, id_game);
            preparedStmt.setInt(2, qty);
            ResultSet rs = preparedStmt.executeQuery();
            
            while(rs.next()) {
                codeList.add(rs.getString("game_code"));
                idList.add(rs.getInt("id_game_code"));
            }
            
            query = "DELETE FROM game_code WHERE `id_game_code` = ?";
            preparedStmt = conn.prepareStatement(query);
            
            for(int i = 0; i < idList.size(); i++) {
                preparedStmt.setInt(1, idList.get(i));
                
                preparedStmt.execute();
            }
            System.out.println("Succesfully reveal the code! and delete em");
            
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return codeList;
    }
    
    public void login(String username, String pass) {
        Connection conn = this.getConnection(usr, pwd, host, db);
        String query = "SELECT * FROM admin WHERE username=? AND PASSWORD=?";
        
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

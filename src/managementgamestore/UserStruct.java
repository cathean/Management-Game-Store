package managementgamestore;

/**
 *
 * @author ivans
 */
class UserStruct {

    public UserStruct(int id_admin, String name, String kontak, String username, String pass, String tipe) {
        this.id_admin = id_admin;
        this.name = name;
        this.kontak = kontak;
        this.username = username;
        this.pass = pass;
        this.tipe = tipe;
    }
    
    int id_admin;
    String name;
    String kontak;
    String username;
    String pass;
    String tipe;
}

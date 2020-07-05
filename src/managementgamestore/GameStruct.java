
package managementgamestore;

/**
 *
 * @author ivans
 */
class GameStruct {
    
    GameStruct(long id_game, String nama_game, String deskripsi, float harga, float pajak, String genre, String platform, String url, int id_diskon) {
        this.id_game = id_game;
        this.nama_game = nama_game;
        this.deskripsi = deskripsi;
        this.harga = harga;
        this.pajak = pajak;
        this.genre = genre;
        this.platform = platform;
        this.url = url;
        this.id_diskon = id_diskon;
    }
    
    long id_game;
    String nama_game;
    String deskripsi;
    float harga;
    float pajak;
    String genre;
    String platform;
    String url;
    int id_diskon = -1;
}

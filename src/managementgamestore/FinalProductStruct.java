package managementgamestore;

/**
 *
 * @author ivans
 */
class FinalProductStruct {
    
    public FinalProductStruct(long id_game, String nama_produk, int kuantitas, float harga, float total) {
        this.id_game = id_game;
        this.nama_produk = nama_produk;
        this.kuantitas = kuantitas;
        this.harga = harga;
        this.total = total;
    }
    
    long id_game;
    String nama_produk;
    int kuantitas;
    float harga;
    float total;
}

package managementgamestore;

/**
 *
 * @author ivans
 */
class FinalProductStruct {
    
    public FinalProductStruct(String nama_produk, int kuantitas, float harga, float total) {
        this.nama_produk = nama_produk;
        this.kuantitas = kuantitas;
        this.harga = harga;
        this.total = total;
    }
    
    String nama_produk;
    int kuantitas;
    float harga;
    float total;
}

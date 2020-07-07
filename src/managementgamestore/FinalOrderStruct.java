package managementgamestore;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author ivans
 */
class FinalOrderStruct {

    public FinalOrderStruct(int id_pesanan, Timestamp tgl_pembelian, float totalSemuaHarga, ArrayList<FinalProductStruct> produk) {
        this.id_pesanan = id_pesanan;
        this.tgl_pembelian = tgl_pembelian;
        this.totalSemuaHarga = totalSemuaHarga;
        this.produk = produk;
    }
    
    
    int id_pesanan;
    Timestamp tgl_pembelian;
    float totalSemuaHarga;
    ArrayList<FinalProductStruct> produk;
}

package managementgamestore;

/**
 *
 * @author ivans
 */
class TransStruct {

    public TransStruct(int id_pesanan, String tgl_pembelian, String nama_pembeli, String email, String nama_karyawan, float jumlah, String metode, String status, int id_admin, int id_pembeli) {
        this.id_pesanan = id_pesanan;
        this.tgl_pembelian = tgl_pembelian;
        this.nama_pembeli = nama_pembeli;
        this.email = email;
        this.nama_karyawan = nama_karyawan;
        this.jumlah = jumlah;
        this.metode = metode;
        this.status = status;
        this.id_admin = id_admin;
        this.id_pembeli = id_pembeli;
    }
    
    int id_pesanan;
    String tgl_pembelian;
    String nama_pembeli;
    String email;
    String nama_karyawan;
    float jumlah;
    String metode;
    String status;
    int id_admin;
    int id_pembeli;
}

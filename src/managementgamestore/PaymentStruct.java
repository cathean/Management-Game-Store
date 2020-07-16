package managementgamestore;

/**
 *
 * @author ivans
 */
public class PaymentStruct {
    PaymentStruct(int id_pembayaran, String jenis, String no_rek) {
        this.id_pembayaran = id_pembayaran;
        this.jenis = jenis;
        this.no_rek = no_rek;
    }
    
    int id_pembayaran;
    String jenis;
    String no_rek;
}

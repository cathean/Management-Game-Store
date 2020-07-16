/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managementgamestore;

/**
 *
 * @author ivans
 */
class VoucherStruct {
    VoucherStruct(int id_voucher, String jenis_game, float harga) {
        this.id_voucher = id_voucher;
        this.jenis_game = jenis_game;
        this.harga = harga;
    }
    
    int id_voucher;
    String jenis_game;
    float harga;
}

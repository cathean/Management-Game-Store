/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managementgamestore;

import com.formdev.flatlaf.FlatLightLaf;
import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import static org.mentaregex.Regex.match;

/**
 *
 * @author ivans
 */
public class SaveStockWindow extends javax.swing.JFrame {
    public int index = -1;
    private GameAPIRequest gameSearch = new GameAPIRequest();
    private DBManager dbm = DBManager.getInstance();
    private final int i;
    private long id_game = -1;
    private boolean isExist = false;
    /**
     * Creates new form SaveStockWindow
     * @param i
     */
    public SaveStockWindow(int i) {
        initComponents();
        this.i = i;
        
        ArrayList<Float> arr = new ArrayList<Float>();
        id_game = MakeGameUID(GameAPIRequest.infoGameResults.get(i).getAsJsonObject("result").get("title").getAsString());
        arr = dbm.fetchPriceAndTaxGameByID(id_game);
        
        if(arr.size() > 0) {
            isExist = true;
            jTextField1.setText(String.valueOf(arr.get(0)));
            jTextField2.setText(String.valueOf(arr.get(1)));
            
            System.out.println(String.valueOf(arr.get(0)));
            System.out.println(String.valueOf(arr.get(1)));
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Save");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null}
            },
            new String [] {
                "Game Code"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Delete");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Add More");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Add to Stock");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTextField1.setText("harga");

        jTextField2.setText("Pajak");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField1)
                    .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private long MakeGameUID(String name) {
        /*
        String[] matchesDate = match(d, "/([^\\s^,]+)/g" );
        
        Date date = new SimpleDateFormat("MMM", Locale.ENGLISH).parse(matchesDate[0]);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH) + 1;
        String gameID = (matchesName.trim()) + Integer.toString(month) + matchesDate[1] + matchesDate[2];
        System.out.println(gameID);
        */
        
        String matchesName = String.join("", match(name, "/(\\b[a-zA-Z])/g"));
        long id = Hashing.murmur3_32().newHasher().putString(matchesName, Charsets.UTF_8).hash().asInt();
        
        System.out.println("The game id is : " + id);
        return id;
    }
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.addRow(new Object[]{null});
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        if(jTable1.getSelectedRow() != -1)
            model.removeRow(jTable1.getSelectedRow());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        ArrayList<String> code_list = new ArrayList<String>();
        JOptionPane.showMessageDialog(null, "Game succesfully stocked!", "Message",JOptionPane.INFORMATION_MESSAGE);
        for(int i = 0; i < model.getRowCount(); i++) {
            if(!(model.getValueAt(i, 0) == null)) {
                code_list.add((String)model.getValueAt(i, 0));
                
                System.out.println("The codes are : " + code_list.get(i).toString());
            }
        }
        
        if(!isExist) {
            dbm.saveGameData(id_game,
                    Integer.parseInt(jTextField1.getText()),
                    GameAPIRequest.infoGameResults.get(i).getAsJsonObject("result").get("title").getAsString(),
                    GameAPIRequest.infoGameResults.get(i).getAsJsonObject("result").get("description").getAsString(),
                    String.join(",", GameUtils.JArrayToArray(GameAPIRequest.infoGameResults.get(i).getAsJsonObject("result").getAsJsonArray("genre"))),
                    String.join(",", GameUtils.JArrayToArray(GameAPIRequest.infoGameResults.get(i).getAsJsonObject("result").getAsJsonArray("alsoAvailableOn"))),
                    Integer.parseInt(jTextField2.getText()),
                    GameAPIRequest.infoGameResults.get(i).getAsJsonObject("result").get("image").getAsString());
        }
        
        try {
            Float.parseFloat(jTextField1.getText());
            Float.parseFloat(jTextField2.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error!, Please only use numbers!", "Message",JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        dbm.saveGameCode(code_list, id_game);
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            UIManager.setLookAndFeel( new FlatLightLaf() );
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>


    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}

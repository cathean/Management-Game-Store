package managementgamestore;

import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Component;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author ivans
 */
public class MainWindow extends javax.swing.JFrame {
    private Image image = null;
    private ImageIcon imageIcon = new ImageIcon();
    private JLabel imageLabel = new JLabel();
    private DefaultTableModel model1 = null;
    private DefaultTableModel model2 = null;
    private int curIndex = -1;
    private int lastIndex = -1;
    private DBManager dbm = DBManager.getInstance();
    ArrayList<GameStruct> gs = new ArrayList<GameStruct>();
    ArrayList<PaymentStruct> ps = new ArrayList<PaymentStruct>();
    
    // Game purchase variables
    public boolean isDoTrans = false;
    public int id_order = -1;

    public MainWindow() {
        initComponents();
        InitOtherComponent();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        btnDelete = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnShop = new javax.swing.JButton();
        btnUser = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel3 = new javax.swing.JLabel();
        btnLog = new javax.swing.JButton();
        btnDiscount = new javax.swing.JButton();
        btnVer = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnSetting = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        btnReceipt = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Management Game Store");
        setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title", "Price", "Tax", "Stock"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Float.class, java.lang.Float.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.getTableHeader().setResizingAllowed(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 608, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Game Stock", jPanel1);

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Payment Type", "No. Account"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable3.getTableHeader().setResizingAllowed(false);
        jTable3.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(jTable3);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 628, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 390, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Payment Method", jPanel3);

        btnDelete.setBackground(new java.awt.Color(242, 242, 242));
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uiassets/fi-xnsuxt-trash-bin.png"))); // NOI18N
        btnDelete.setToolTipText("Delete items.");
        btnDelete.setFocusable(false);
        btnDelete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDelete.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnEdit.setBackground(new java.awt.Color(242, 242, 242));
        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uiassets/fi-xnsuxt-edit-solid.png"))); // NOI18N
        btnEdit.setToolTipText("Edit Stock.");
        btnEdit.setFocusable(false);
        btnEdit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEdit.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnShop.setBackground(new java.awt.Color(242, 242, 242));
        btnShop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uiassets/fi-xwsuht-shopping-cart-solid.png"))); // NOI18N
        btnShop.setToolTipText("Do transaction to all selected game.");
        btnShop.setFocusable(false);
        btnShop.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnShop.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnShop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShopActionPerformed(evt);
            }
        });

        btnUser.setBackground(new java.awt.Color(242, 242, 242));
        btnUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uiassets/fi-xnsuxt-key-alt-solid.png"))); // NOI18N
        btnUser.setToolTipText("Open user manager.");
        btnUser.setFocusable(false);
        btnUser.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnUser.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserActionPerformed(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel2.setText("Total Stock :");

        jProgressBar1.setValue(35);

        jLabel3.setText("0");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        btnLog.setBackground(new java.awt.Color(242, 242, 242));
        btnLog.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uiassets/fi-cwluht-clock-wide.png"))); // NOI18N
        btnLog.setToolTipText("View transaction log.");
        btnLog.setFocusable(false);
        btnLog.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnLog.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnLog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogActionPerformed(evt);
            }
        });

        btnDiscount.setBackground(new java.awt.Color(242, 242, 242));
        btnDiscount.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uiassets/fi-xwluxt-percent-wide.png"))); // NOI18N
        btnDiscount.setToolTipText("Open discount event manager.");
        btnDiscount.setFocusable(false);
        btnDiscount.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDiscount.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDiscount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDiscountActionPerformed(evt);
            }
        });

        btnVer.setBackground(new java.awt.Color(242, 242, 242));
        btnVer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uiassets/fi-cnldxt-exclamation-mark.png"))); // NOI18N
        btnVer.setToolTipText("Show softaware information such as version, developer, etc.");
        btnVer.setFocusable(false);
        btnVer.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnVer.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerActionPerformed(evt);
            }
        });

        btnAdd.setBackground(new java.awt.Color(242, 242, 242));
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uiassets/fi-xwsuxt-plus-solid.png"))); // NOI18N
        btnAdd.setToolTipText("Add more items to the stock.");
        btnAdd.setFocusable(false);
        btnAdd.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAdd.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnSetting.setBackground(new java.awt.Color(242, 242, 242));
        btnSetting.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uiassets/fi-xnsuxt-setting-solid.png"))); // NOI18N
        btnSetting.setToolTipText("All setting options.");
        btnSetting.setFocusable(false);
        btnSetting.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSetting.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSetting.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSettingActionPerformed(evt);
            }
        });

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Game Details"));

        jTextArea1.setBackground(new java.awt.Color(240, 240, 240));
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane4.setViewportView(jTextArea1);

        jLabel5.setText("Platforms :");

        jLabel12.setText("Genre :");

        jButton1.setText("Add to Cart");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel13.setText("How many :");

        jTextField2.setText("1");

        jButton2.setText("Clear Cart");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jComboBox1, 0, 140, Short.MAX_VALUE)
                                .addComponent(jLabel5)
                                .addComponent(jComboBox3, 0, 140, Short.MAX_VALUE)
                                .addComponent(jLabel12)
                                .addComponent(jLabel13)
                                .addComponent(jTextField2))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2)))
                        .addGap(3, 3, 3)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2))))
                .addGap(14, 14, 14)
                .addComponent(jScrollPane4)
                .addContainerGap())
        );

        btnReceipt.setBackground(new java.awt.Color(242, 242, 242));
        btnReceipt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uiassets/fi-xwluxt-file-wide.png"))); // NOI18N
        btnReceipt.setToolTipText("Make new transaction.");
        btnReceipt.setFocusable(false);
        btnReceipt.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnReceipt.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnReceipt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReceiptActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnReceipt)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnShop)
                                .addGap(18, 18, 18)
                                .addComponent(btnAdd)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDelete)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEdit))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 633, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(10, 10, 10))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSeparator1)
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLog)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDiscount)
                .addGap(6, 6, 6)
                .addComponent(btnUser)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSetting)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnVer)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnSetting)
                        .addComponent(btnVer))
                    .addComponent(btnUser, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnLog, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnDiscount, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnShop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReceipt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public void refreshGameTable() {
        model1.setRowCount(0);
        gs = dbm.fetchGameStock();
        
        for(int i = 0; i < gs.size(); i++) {
        model1.addRow(new Object[] {
            gs.get(i).nama_game,
            gs.get(i).harga,
            gs.get(i).pajak, 
            dbm.fetchGameStockCount(gs.get(i).id_game)});
        }
    }
    
    public void refreshPaymentTable() {
        model2.setRowCount(0);
        ps = dbm.fetchPaymentMethodList();
        
        for(int i = 0; i < ps.size(); i++) {
            model2.addRow(new Object[] {ps.get(i).jenis, ps.get(i).no_rek});
        }
    }
    
    public void InitOtherComponent() {
        //jTable1.getColumn("Details").setCellRenderer(new CustomTableRenderer().new ButtonRenderer());
        //jTable1.getColumn("Details").setCellEditor(new CustomTableRenderer().new ButtonEditor(new JCheckBox()));
        dbm.createTrigGame();
        model1 = (DefaultTableModel) jTable1.getModel();
        model2 = (DefaultTableModel) jTable3.getModel();
        jTextArea1.setLineWrap(true);
        
        // Use ram cache for image IO
        ImageIO.setUseCache(false);
        
        // Load list of game and display it in table
        gs = dbm.fetchGameStock();
        
        refreshGameTable();
        
        // Load list of payment method
        ps = dbm.fetchPaymentMethodList();
        
        for(int i = 0; i < ps.size(); i++) {
            model2.addRow(new Object[] {ps.get(i).jenis, ps.get(i).no_rek});
        }
        
        jTabbedPane1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent ce) {
                if (ce.getSource() instanceof JTabbedPane) {
                            JTabbedPane pane = (JTabbedPane) ce.getSource();
                            System.out.println("Selected paneNo : " + pane.getSelectedIndex());
                            
                            // If tab pane is in 1, hide unnecessary things.
                            if(pane.getSelectedIndex() != 0) {
                                for (Component cp : jPanel5.getComponents())
                                    cp.setEnabled(false);
                            } else {
                                for (Component cp : jPanel5.getComponents())
                                    cp.setEnabled(true);
                            }
                }
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }
    
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        curIndex = jTable1.getSelectedRow();
        
        if(curIndex == lastIndex)
            return;
        
        System.out.println(curIndex);
        System.out.println(gs.get(curIndex).url);
        System.out.println(gs.get(curIndex).id_game);
        jComboBox1.removeAllItems();
        jComboBox3.removeAllItems();
        jTextArea1.setText("");
        
        URL url;
        try {
            url = new URL(gs.get(curIndex).url);
            image = ImageIO.read(url).getScaledInstance(160, 225, Image.SCALE_SMOOTH);
            jLabel4.setIcon(new ImageIcon(image));
            jComboBox1.setModel(new DefaultComboBoxModel(GameUtils.StringToArray(gs.get(curIndex).platform)));
            jComboBox3.setModel(new DefaultComboBoxModel(GameUtils.StringToArray(gs.get(curIndex).genre)));
            jTextArea1.setText(gs.get(curIndex).deskripsi);
        } catch (MalformedURLException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        lastIndex = curIndex;
    }//GEN-LAST:event_jTable1MouseClicked

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if(jTabbedPane1.getSelectedIndex() == 0) {
            new AddGameWindow().setVisible(true);
            this.dispose();
        } 
        else if(jTabbedPane1.getSelectedIndex() == 1) {
            this.dispose();
            new AddPaymentWindow(false, -1).setVisible(true);
            System.out.println();
            //new SaveStockVouchWindow(curIndex).setVisible(true);
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerActionPerformed
        new AboutWindow().setVisible(true);
    }//GEN-LAST:event_btnVerActionPerformed

    private void btnSettingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSettingActionPerformed
        new SettingWindow().setVisible(true);
    }//GEN-LAST:event_btnSettingActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Game's tab panel
        if(jTabbedPane1.getSelectedIndex() == 0) {
            
            if(id_order != -1) {
                // Game's Stock validation
                if(dbm.fetchGameStockCount(gs.get(curIndex).id_game) - Integer.parseInt(jTextField2.getText()) >= 0 &&
                        !((Integer)model1.getValueAt(curIndex, 3) <= 0)) {
                    model1.setValueAt((Integer)model1.getValueAt(curIndex, 3) - Integer.parseInt(jTextField2.getText()), curIndex, 3);
                    System.out.println("Current stock : " + (Integer)model1.getValueAt(curIndex, 3));

                    // Saving to the the table detail_produk
                    // Also the total price already cut by tax
                    dbm.saveDetailProduct((Float)model1.getValueAt(curIndex, 1) * Integer.parseInt(jTextField2.getText()) - (Float)model1.getValueAt(curIndex, 2), Integer.parseInt(jTextField2.getText()), gs.get(curIndex).id_game, id_order);
                    JOptionPane.showMessageDialog(null, "Added to cart!!");
                } else {
                    JOptionPane.showMessageDialog(null, "Not enough stock!");
                    System.out.println("Stock not enough!");
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please, press make new transaction first!");
                System.out.println("Press the new transaction button first!");
            }
        // Payment method's tab panel
        } else if(jTabbedPane1.getSelectedIndex() == 1) {
           
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        if(id_order != -1) {
            dbm.delDetailProduct(id_order);
            refreshGameTable();
        }
        
        System.out.println("All cart cleared!");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnShopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShopActionPerformed
        if(id_order == -1)
            JOptionPane.showMessageDialog(null, "Please, make new transaction first");
        else
            new TransWindow().setVisible(true);
    }//GEN-LAST:event_btnShopActionPerformed

    private void btnReceiptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReceiptActionPerformed
        if(!isDoTrans) {
            int option = JOptionPane.showConfirmDialog(null, "Make new transaction?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION){
                isDoTrans = true;
                dbm.saveOrder();
                id_order = dbm.fetchLatestOrderID();
                JOptionPane.showMessageDialog(null, "New transaction with number : " + id_order);
            }
        } else {
            System.out.println("Already make a transaction! with id : " + dbm.fetchLatestOrderID());
        }
    }//GEN-LAST:event_btnReceiptActionPerformed

    private void btnUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserActionPerformed
        new UserWindow().setVisible(true);
    }//GEN-LAST:event_btnUserActionPerformed

    private void btnLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogActionPerformed
        new TransLog().setVisible(true);
    }//GEN-LAST:event_btnLogActionPerformed

    private void btnDiscountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDiscountActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null,"Coming soon");
    }//GEN-LAST:event_btnDiscountActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        
        if(jTabbedPane1.getSelectedIndex() == 0) {
           curIndex = jTable1.getSelectedRow();
            int option = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION){
                long id_game = gs.get(curIndex).id_game;
                dbm.delGameList(id_game);
                refreshGameTable();
                JOptionPane.showMessageDialog(null, "Data succesfully deleted!");
            }
        } else if (jTabbedPane1.getSelectedIndex() == 1) {
            int option = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirmation", JOptionPane.YES_NO_OPTION);
            
            if (option == JOptionPane.YES_OPTION) {
                dbm.delPayment(ps.get(jTable3.getSelectedRow()).id_pembayaran);
                refreshPaymentTable();
                JOptionPane.showMessageDialog(null, "Data succesfully deleted!");
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        if(jTabbedPane1.getSelectedIndex() == 0) {
            new EditGameWindow(curIndex).setVisible(true);
        } else if (jTabbedPane1.getSelectedIndex() == 1){
            new AddPaymentWindow(true, jTable3.getSelectedRow()).setVisible(true);
        }
        
        this.dispose();
    }//GEN-LAST:event_btnEditActionPerformed

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
            /*
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
            */
            
            UIManager.setLookAndFeel( new FlatLightLaf() );
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
           
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);

            }
        });
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDiscount;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnLog;
    private javax.swing.JButton btnReceipt;
    private javax.swing.JButton btnSetting;
    private javax.swing.JButton btnShop;
    private javax.swing.JButton btnUser;
    private javax.swing.JButton btnVer;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.media.CannotRealizeException;
import javax.media.Manager;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kyky
 */
public final class MainPage extends javax.swing.JFrame implements MouseListener, MouseMotionListener  {

    /**
     * Creates new form MainPage
     */
    
    File file;
    Player player;
    Component controls;
    static public boolean initialInput = true; //biar booelannya gak berubah nilai
    boolean isDrag1 = false, isDrag2 = false, isDrag3 = false, isDrag4 = false; //validasi jika item sedang di drag
    int x = 62, y = 62; //poin tengahnya gambar
//    int xCart = 125, yCart = 125;
    DefaultTableModel table;
    int qty1 = 0,qty2 = 0,qty3 = 0, qty4 = 0; //inialisisasi jumlah barang yg dibeli
    int amount = 0; //total belanjaan

    void firstInput() {
        File mouseAdd = new File("Assets/mouse.png");
        File shoesAdd = new File("Assets/shoes.png");
        File tshirtAdd = new File("Assets/t-shirt.png");
        File caseAdd = new File("Assets/case.png");
        
        try {
            ItemList.lists.add(new ItemList("Notebook Case", 25000, ImageIO.read(caseAdd)));
        } catch (IOException ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ItemList.lists.add(new ItemList("Mouse", 125000, ImageIO.read(mouseAdd)));
        } catch (IOException ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ItemList.lists.add(new ItemList("Shoes", 300000, ImageIO.read(shoesAdd)));
        } catch (IOException ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ItemList.lists.add(new ItemList("T-Shirt", 75000, ImageIO.read(tshirtAdd)));
        } catch (IOException ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //mulai lagu
        try {
            file = new File("Assets/canon.wav");
            player = Manager.createRealizedPlayer(file.toURI().toURL());
            
        } catch (IOException ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoPlayerException ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CannotRealizeException ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        player.start();
    }

    
    public MainPage()  {
        initComponents(); 
        table = (DefaultTableModel) jTable1.getModel();
        if (initialInput){
            firstInput();
            initialInput = false;
        }
        //munculin gambar di label
        
        if (ItemList.lists.size() == 1) {
            jLabel7.setIcon(new ImageIcon(ItemList.lists.get(0).img.getScaledInstance(125, 125, Image.SCALE_SMOOTH)));
        }
        if (ItemList.lists.size() == 2) {
            jLabel7.setIcon(new ImageIcon(ItemList.lists.get(0).img.getScaledInstance(125, 125, Image.SCALE_SMOOTH)));
            jLabel8.setIcon(new ImageIcon(ItemList.lists.get(1).img.getScaledInstance(125, 125, Image.SCALE_SMOOTH)));
        }
        if (ItemList.lists.size() == 3) {
            jLabel7.setIcon(new ImageIcon(ItemList.lists.get(0).img.getScaledInstance(125, 125, Image.SCALE_SMOOTH)));
            jLabel8.setIcon(new ImageIcon(ItemList.lists.get(1).img.getScaledInstance(125, 125, Image.SCALE_SMOOTH)));
            jLabel9.setIcon(new ImageIcon(ItemList.lists.get(2).img.getScaledInstance(125, 125, Image.SCALE_SMOOTH)));
        }
        if (ItemList.lists.size() == 4) {
            jLabel7.setIcon(new ImageIcon(ItemList.lists.get(0).img.getScaledInstance(125, 125, Image.SCALE_SMOOTH)));
            jLabel8.setIcon(new ImageIcon(ItemList.lists.get(1).img.getScaledInstance(125, 125, Image.SCALE_SMOOTH)));
            jLabel9.setIcon(new ImageIcon(ItemList.lists.get(2).img.getScaledInstance(125, 125, Image.SCALE_SMOOTH)));
            jLabel10.setIcon(new ImageIcon(ItemList.lists.get(3).img.getScaledInstance(125, 125, Image.SCALE_SMOOTH)));
        }
    }
 
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D itemlist = (Graphics2D) g; 
        //munculin gambar pas di drag
        if(isDrag1 == true){
            itemlist.drawImage(ItemList.lists.get(0).img.getScaledInstance(125, 125, Image.SCALE_FAST), x, y, null);
        }else if(isDrag2 == true){
            itemlist.drawImage(ItemList.lists.get(1).img.getScaledInstance(125, 125, Image.SCALE_FAST), x, y, null);
        }else if(isDrag3 == true){
            itemlist.drawImage(ItemList.lists.get(2).img.getScaledInstance(125, 125, Image.SCALE_FAST), x, y, null);
        }else if(isDrag4 == true){
            itemlist.drawImage(ItemList.lists.get(3).img.getScaledInstance(125, 125, Image.SCALE_FAST), x, y, null);
        }
        
        Graphics2D g2 = (Graphics2D) g ;
        AffineTransform af = g2.getTransform();
        //big size
        if (jRadioButton1.isSelected()) {
            af.scale(1, 1);
            g2.setTransform(af);
            for (int i = 1; i <= ShoppingCart.lists.size(); i++) {
                if (i >= 1 && i <= 4) {
                    g2.drawImage(ShoppingCart.lists.get(i-1).img, 80+125*i, 100, null);
                } else if (i >= 5 && i <= 8) {
                    g2.drawImage(ShoppingCart.lists.get(i-1).img, 80+125*(i-4), 200, null);
                } else if (i >= 9 && i <= 12) {
                    g2.drawImage(ShoppingCart.lists.get(i-1).img, 80+125*(i-8), 300, null);
                } else if (i >= 13 && i <= 16) {
                    g2.drawImage(ShoppingCart.lists.get(i-1).img, 80+125*(i-12), 400, null);
                }
            }
        //small size
        } else if (jRadioButton2.isSelected()){
            af.scale(0.75, 0.75);
            g2.setTransform(af);
            for (int i = 1; i <= ShoppingCart.lists.size(); i++) {
                if (i >= 1 && i <= 4) {
                    g2.drawImage(ShoppingCart.lists.get(i-1).img, 150+125*i, 150, null);
                } else if (i >= 5 && i <= 8) {
                    g2.drawImage(ShoppingCart.lists.get(i-1).img, 150+125*(i-4), 300, null);
                } else if (i >= 9 && i <= 12) {
                    g2.drawImage(ShoppingCart.lists.get(i-1).img, 150+125*(i-8), 450, null);
                } else if (i >= 13 && i <= 16) {
                    g2.drawImage(ShoppingCart.lists.get(i-1).img, 150+125*(i-12), 600, null);
                }
            }
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1024, 768));

        jPanel1.setBackground(new java.awt.Color(51, 255, 0));

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 48)); // NOI18N
        jLabel1.setText("BLUEJACK MARKET");
        jPanel1.add(jLabel1);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setBackground(new java.awt.Color(255, 255, 204));
        jPanel2.setLayout(new java.awt.GridLayout(3, 1));

        jPanel5.setBackground(new java.awt.Color(255, 255, 204));
        jPanel5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel5.setPreferredSize(new java.awt.Dimension(300, 100));
        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 100));

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setSelected(true);
        jRadioButton1.setText("Big Size");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });
        jPanel5.add(jRadioButton1);
        jRadioButton1.getAccessibleContext().setAccessibleDescription("");

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("Small Size");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });
        jPanel5.add(jRadioButton2);

        jPanel2.add(jPanel5);

        jPanel6.setPreferredSize(new java.awt.Dimension(200, 200));
        jPanel6.setLayout(new javax.swing.BoxLayout(jPanel6, javax.swing.BoxLayout.LINE_AXIS));

        jScrollPane1.setPreferredSize(new java.awt.Dimension(200, 200));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item", "Qty", "Total Price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setPreferredSize(new java.awt.Dimension(300, 300));
        jScrollPane1.setViewportView(jTable1);

        jPanel6.add(jScrollPane1);

        jPanel2.add(jPanel6);

        jPanel7.setBackground(new java.awt.Color(255, 255, 204));
        jPanel7.setPreferredSize(new java.awt.Dimension(200, 200));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/edit.png"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        jPanel7.add(jLabel2);
        jPanel7.add(jLabel3);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/shop.png"))); // NOI18N
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        jPanel7.add(jLabel4);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/exit.png"))); // NOI18N
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        jPanel7.add(jLabel5);

        jPanel2.add(jPanel7);

        getContentPane().add(jPanel2, java.awt.BorderLayout.LINE_END);

        jPanel3.setBackground(new java.awt.Color(255, 204, 204));
        jPanel3.setPreferredSize(new java.awt.Dimension(200, 1347));
        jPanel3.setLayout(new java.awt.GridLayout(4, 1));

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jLabel7MouseDragged(evt);
            }
        });
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel7MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel7MouseReleased(evt);
            }
        });
        jPanel3.add(jLabel7);

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jLabel8MouseDragged(evt);
            }
        });
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel8MouseReleased(evt);
            }
        });
        jPanel3.add(jLabel8);

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jLabel9MouseDragged(evt);
            }
        });
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel9MouseReleased(evt);
            }
        });
        jPanel3.add(jLabel9);

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jLabel10MouseDragged(evt);
            }
        });
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel10MouseReleased(evt);
            }
        });
        jPanel3.add(jLabel10);

        getContentPane().add(jPanel3, java.awt.BorderLayout.LINE_START);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel4.setLayout(new java.awt.GridLayout(4, 4));

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel4.add(jLabel11);

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel4.add(jLabel12);

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel4.add(jLabel13);

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel4.add(jLabel14);

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel4.add(jLabel15);

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel4.add(jLabel16);

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel4.add(jLabel17);

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel4.add(jLabel18);

        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel4.add(jLabel19);

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel4.add(jLabel20);

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel4.add(jLabel21);

        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel4.add(jLabel22);

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel4.add(jLabel23);

        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel4.add(jLabel24);

        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel4.add(jLabel25);

        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel4.add(jLabel26);

        getContentPane().add(jPanel4, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        ShoppingCart.lists.clear();
        table.setNumRows(0);
        qty1 = 0;
        qty2 = 0;
        qty3 = 0;
        qty4 = 0;
        EditItem edit = new EditItem();
        MainPage.this.dispose();
        edit.setVisible(true);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel7MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseDragged
        // TODO add your handling code here:
        isDrag1 = true;
        x = (int) getMousePosition().getX() - 62;
        y = (int) getMousePosition().getY() - 62;
        repaint();
    }//GEN-LAST:event_jLabel7MouseDragged

    private void jLabel7MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MousePressed
        // TODO add your handling code here:

    }//GEN-LAST:event_jLabel7MousePressed

    private void jLabel7MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseReleased
        // TODO add your handling code here:
        isDrag1 = false;
        if(getMousePosition().getX() >= 200 && getMousePosition().getX() <= 700) {
            if (ShoppingCart.lists.size() == 16) {
                JOptionPane.showMessageDialog(this, "Item cart is full!");
            }
            else {
                ShoppingCart.lists.add(ItemList.lists.get(0));
                if (qty1 == 0) {
                    qty1++;
                    Object[] row = {ItemList.lists.get(0).name , qty1, ItemList.lists.get(0).price};
                    table.addRow(row);
                    
                } else {
                    qty1++;
                    for(int i = 0; i < table.getRowCount(); i++){
                        if(table.getValueAt(i,0) == ItemList.lists.get(0).name){
                            table.setValueAt(qty1, i, 1);
                            table.setValueAt(qty1*ItemList.lists.get(0).price, i, 2);
                        }
                    }
                }
            }
        }
        repaint();
    }//GEN-LAST:event_jLabel7MouseReleased

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
        repaint();
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
        repaint();
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jLabel8MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseDragged
        // TODO add your handling code here:
        isDrag2 = true;
        x = (int) getMousePosition().getX() - 62;
        y = (int) getMousePosition().getY() - 62;
        repaint();
    }//GEN-LAST:event_jLabel8MouseDragged

    private void jLabel8MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseReleased
        // TODO add your handling code here:
        isDrag2 = false;
        if(getMousePosition().getX() >= 200 && getMousePosition().getX() <= 700) {
            if (ShoppingCart.lists.size() == 16) {
                JOptionPane.showMessageDialog(this, "Item cart is full!");
            }
            else {
                ShoppingCart.lists.add(ItemList.lists.get(1));
                if (qty2 == 0) {
                    qty2++;
                    Object[] row = {ItemList.lists.get(1).name , qty2, ItemList.lists.get(1).price};
                    table.addRow(row);
                    
                } else {
                    qty2++;
                    for(int i = 0; i < table.getRowCount(); i++){
                        if(table.getValueAt(i,0) == ItemList.lists.get(1).name){
                            table.setValueAt(qty2, i, 1);
                            table.setValueAt(qty2*ItemList.lists.get(1).price, i, 2);
                        }
                    }
                }
            }
        }
        repaint();
    }//GEN-LAST:event_jLabel8MouseReleased

    private void jLabel9MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseDragged
        // TODO add your handling code here
        isDrag3 = true;
        x = (int) getMousePosition().getX() - 62;
        y = (int) getMousePosition().getY() - 62;
        repaint();
    }//GEN-LAST:event_jLabel9MouseDragged

    private void jLabel9MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseReleased
        // TODO add your handling code here:
        isDrag3 = false;
        
        //&& getMousePosition().getY()
        System.out.println(""+getMousePosition().getY());
        if(getMousePosition().getX() >= 200 && getMousePosition().getX() <= 700 && getMousePosition().getY() >=110 && getMousePosition().getY() <= 760 ) {
            if (ShoppingCart.lists.size() == 16) {
                JOptionPane.showMessageDialog(this, "Item cart is full!");
            }
            else {
                ShoppingCart.lists.add(ItemList.lists.get(2));
                if (qty3 == 0) {
                    qty3++;
                    Object[] row = {ItemList.lists.get(2).name , qty3, ItemList.lists.get(2).price};
                    table.addRow(row);
                    
                } else {
                    qty3++;
                    for(int i = 0; i < table.getRowCount(); i++){
                        if(table.getValueAt(i,0) == ItemList.lists.get(2).name){
                            table.setValueAt(qty3, i, 1);
                            table.setValueAt(qty3*ItemList.lists.get(2).price, i, 2);
                        }
                    }
                }
            }
        }
        repaint();
    }//GEN-LAST:event_jLabel9MouseReleased

    private void jLabel10MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseDragged
        // TODO add your handling code here:
        isDrag4 = true;
        x = (int) getMousePosition().getX() - 62;
        y = (int) getMousePosition().getY() - 62;
        repaint();
    }//GEN-LAST:event_jLabel10MouseDragged

    private void jLabel10MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseReleased
        // TODO add your handling code here:
        isDrag4 = false;
        if(getMousePosition().getX() >= 200 && getMousePosition().getX() <= 700) {
            if (ShoppingCart.lists.size() == 16) {
                JOptionPane.showMessageDialog(this, "Item cart is full!");
            }
            else {
                ShoppingCart.lists.add(ItemList.lists.get(3));
                if (qty4 == 0) {
                    qty4++;
                    Object[] row = {ItemList.lists.get(3).name , qty4, ItemList.lists.get(3).price};
                    table.addRow(row);
                    
                } else {
                    qty4++;
                    for(int i = 0; i < table.getRowCount(); i++){
                        if(table.getValueAt(i,0) == ItemList.lists.get(3).name){
                            table.setValueAt(qty4, i, 1);
                            table.setValueAt(qty4*ItemList.lists.get(3).price, i, 2);
                        }
                    }
                }
            }
        }
        repaint();
    }//GEN-LAST:event_jLabel10MouseReleased

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
        
        if (ShoppingCart.lists.isEmpty()) {
            JOptionPane.showMessageDialog(this, "There is no item!");
        }
        else {
            for (int i = 0; i <= ShoppingCart.lists.size()-1; i++) {
                amount = amount + ShoppingCart.lists.get(i).price;
            }
            int choice = JOptionPane.showConfirmDialog(this, "You will buy "+amount);
            
            if (choice == JOptionPane.OK_OPTION){
                ShoppingCart.lists.clear();
                table.setNumRows(0);
                qty1 = 0;
                qty2 = 0;
                qty3 = 0;
                qty4 = 0;
                repaint();
            }
        }
    }//GEN-LAST:event_jLabel4MouseClicked

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
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

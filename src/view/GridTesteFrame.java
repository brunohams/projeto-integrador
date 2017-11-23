package view;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;



public class GridTesteFrame extends javax.swing.JFrame {

    Connection conn;
   
   //Connection con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/BDados","root","");
    public void CONN() throws SQLException{
        
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BDados","root","");
        
    }
    // POPULAR A JTABLE COM O BANCO DE DADOS
    public void PopularJTable(String sql) throws ClassNotFoundException {
  try
  {
 
    //Connection con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/BDados","root","");
   PreparedStatement banco = (PreparedStatement)conn.prepareStatement(sql);
   banco.execute();
 
   ResultSet resultado = banco.executeQuery(sql);
 
   DefaultTableModel model =(DefaultTableModel) jTable1.getModel();
   model.setNumRows(0);
 
   while(resultado.next())
   {
       model.addRow(new Object[] 
       { 
          //retorna os dados da tabela do BD, cada campo e um coluna.
          resultado.getString("descricao"),
          resultado.getString("valor"),
          resultado.getString("vedesconto")
       }); 
  } 
   banco.close();
   conn.close();
  }
 catch (SQLException ex)
 {
    System.out.println("o erro foi " +ex);
  }
 }
    
    public void ADDREGISTRO(String sql) throws SQLException{

       PreparedStatement banco = (PreparedStatement)conn.prepareStatement(sql);
       banco.execute();

       ResultSet resultado = banco.executeQuery(sql);
 
        DefaultTableModel model =(DefaultTableModel) jTable1.getModel();
        
        model.setNumRows(0);
         
        while(resultado.next())
            {
                model.addRow(new Object[] 
                { 
                   //retorna os dados da tabela do BD, cada campo e um coluna.
                   resultado.getString("descricao"),
                   resultado.getString("valor"),
                   resultado.getString("vedesconto")
                }); 
           } 

    }
 
    public void AtualizarJTable(){
        try {
            this.PopularJTable("SELECT * FROM BDados.produtos");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GridTesteFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void InsertData(String sql){
             Connection con;
        try {
            con = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/BDados","root","");
             PreparedStatement banco = (PreparedStatement)con.prepareStatement(sql);
            banco.execute();
            banco.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(GridTesteFrame.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
    public void DeleteData(){
             Connection con;
        try {
            con = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/BDados","root","");
             PreparedStatement banco = (PreparedStatement)con.prepareStatement("DELETE FROM produtos WHERE '" + jTextField1.getText() + "' = descricao;"  );
            banco.execute();
            banco.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(GridTesteFrame.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
    public GridTesteFrame() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jTextField3 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jButton2.setForeground(new java.awt.Color(255, 0, 0));
        jButton2.setText("Apagar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonApagar(evt);
            }
        });

        jButton1.setText("Adicionar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonInsert(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Endereço", "Telefone"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTable1.setFocusable(false);
        jTable1.setGridColor(new java.awt.Color(153, 153, 153));
        jTable1.setName(""); // NOI18N
        jTable1.setShowGrid(true);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        jTable1.getAccessibleContext().setAccessibleDescription("");

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Cadastro");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Geral"));

        jLabel3.setText("Telefone");

        jLabel2.setText("Endereço");

        jLabel1.setText("Nome");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField1)
                    .addComponent(jTextField2)
                    .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(7, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jButton3.setForeground(new java.awt.Color(0, 51, 255));
        jButton3.setText("Atualizar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AtualizarTable(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(65, 65, 65)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 59, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(31, 31, 31)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addComponent(jScrollPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ButtonApagar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonApagar
        
        try {
            CONN();
            DeleteData();
            AtualizarJTable();
            System.out.println("DELETE FROM produtos WHERE '" + jTextField1.getText() + "' = descricao;" );
        } catch (SQLException ex) {
            Logger.getLogger(GridTesteFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
       
//        if(jButton2.getText() == "Cancelar")
//        {
//            jTextField1.setText("");
//            jTextField2.setText("");
//            jTextField3.setText("");
//            jButton2.setText("Apagar");
//            jButton1.setText("Adicionar");
//            jTextField1.requestFocus(rootPaneCheckingEnabled);
//        }
      
        //((DefaultTableModel) jTable1.getModel()).removeRow(jTable1.getSelectedRow());
       
//        if(jButton2.getText() == "Cancelar")
//        {
//            jTextField1.setText("");
//            jTextField2.setText("");
//            jTextField3.setText("");
//            jButton2.setText("Apagar");
//            jButton1.setText("Adicionar");
//            jTextField1.requestFocus(rootPaneCheckingEnabled);
//        }
      
        //((DefaultTableModel) jTable1.getModel()).removeRow(jTable1.getSelectedRow());

    }//GEN-LAST:event_ButtonApagar

    private void ButtonInsert(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonInsert

        // PEGA DADOS DOS CAMPOS
        String nome = jTextField1.getText();
        String email = jTextField2.getText();
        String telefone = jTextField3.getText();

        System.out.println("INSERT INTO produtos VALUES" + " ('" + jTextField1.getText() + "', '" + jTextField2.getText() + "', " + jTextField3.getText() + ")");
        String sql = "INSERT INTO produtos VALUES" + " ('" + jTextField1.getText() + "', '" + jTextField2.getText() + "', " + jTextField3.getText() + ")";
        
        try {
            CONN();
        } catch (SQLException ex) {
            Logger.getLogger(GridTesteFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        InsertData(sql);
        AtualizarJTable();

        // ZERA OS CAMPOS
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        float CENTER_ALIGNMENT1 = JTextField.CENTER_ALIGNMENT;

        jTextField1.requestFocus();
    }//GEN-LAST:event_ButtonInsert

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
    
        try {
            CONN();
        } catch (SQLException ex) {
            Logger.getLogger(GridTesteFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        AtualizarJTable();        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowOpened

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // Numero da linha
        System.out.println(jTable1.getValueAt(jTable1.getSelectedRow(), 0));
        
        Object coluna0 = jTable1.getValueAt(jTable1.getSelectedRow(), 0);
        String coluna1 = jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString();
        String coluna2 = jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString();
        // Object x = jTable1.getModel().getValueAt(jTable1.getSelectedRow(), 0);
       
        jTextField1.setText((String) coluna0); 
        jTextField2.setText(coluna1);
        jTextField3.setText(coluna2);
        
//        jButton2.setText("Cancelar");
//        jButton1.setText("Alterar");
        
//jButton2.setVisible(false);
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void AtualizarTable(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AtualizarTable
        try {
            CONN();
        } catch (SQLException ex) {
            Logger.getLogger(GridTesteFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        AtualizarJTable();        // TODO add your handling code here:
    }//GEN-LAST:event_AtualizarTable

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GridTesteFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}

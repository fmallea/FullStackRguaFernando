package insumos;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;

public class principal extends javax.swing.JFrame {

    String a,b,c,d,e,f;
    JOptionPane mensaje;
    private String par;
    private Connection con;
    private Statement stm;
    private ResultSet rs;
    /** Creates new form principal */

    public principal() {
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        conectar();
    }

       private void conectar(){
        try{
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        par="jdbc:odbc:Driver={SQL Server};server=NOMBRE_SERVIDOR;integrated Security=SSPI;database=BASE_DE_DATOS;";
        con = DriverManager.getConnection(par);
        System.out.println("CONEXION EXITOSA");
        }catch(SQLException e){
        System.out.println("ERROR SQL CONEXION: "+e.getMessage());
        }catch(Exception ex){
        System.out.println("ERROR JAVA CONEXION: "+ex.getMessage());
        }
    }

       /*
        * PROGRAMACION FORMULARIO INSERTAR INSUMOS
        */
       
    private void obtenerDatosFormularioInsertar(){
        a=txtcodins.getText();
        b=txtmodins.getText();
        c=Integer.toString(cbomarins.getSelectedIndex());
        d=txtdesins.getText();
        e=txtstoins.getText();
        f=txtpreins.getText();
    }

    private void insertarInsumo(){
        obtenerDatosFormularioInsertar();
        try{
        stm = con.createStatement();
        stm.execute("insert into productos values("+a+",'"+b+"',"+c+",'"+d+"',"+e+","+f+")");
        mensaje.showMessageDialog(insumosIngresar,"Datos almacenados","Almacenar Insumos",1);
        stm.close();
        limpiarInsertarInsumo();
        }catch(SQLException e){
        mensaje.showMessageDialog(insumosIngresar,"ERROR SQL ALMACENAR INSUMOS: "+e.getMessage(),"ERROR Almacenar Insumos",0);
        }catch(Exception ex){
        mensaje.showMessageDialog(insumosIngresar,"ERROR JAVA ALMACENAR INSUMOS: "+ex.getMessage(),"ERROR Almacenar Insumos",0);
        }
    }

    private void habilitarInsertarInsumo(){
        txtmodins.setEnabled(true);
        txtmodins.requestFocus();
        cbomarins.setEnabled(true);
        txtdesins.setEnabled(true);
        txtstoins.setEnabled(true);
        txtpreins.setEnabled(true);
        btnaceins.setEnabled(true);
        llenarCboInsertarInsumo();
    }

    private void limpiarInsertarInsumo(){
        cbomarins.removeAllItems();
        txtcodins.setText("");
        txtmodins.setEnabled(false);
        txtmodins.setText("");
        cbomarins.setEnabled(false);
        txtdesins.setText("");
        txtdesins.setEnabled(false);
        txtstoins.setText("");
        txtstoins.setEnabled(false);
        txtpreins.setText("");
        txtpreins.setEnabled(false);
        txtcodins.requestFocus();
    }

    private void llenarCboInsertarInsumo(){
        cbomarins.removeAllItems();
        try{
        stm = con.createStatement();
        rs = stm.executeQuery("select nommar from marcas");
        while(rs.next()){
            cbomarins.addItem(rs.getString("nommar"));
        }
        stm.close();
        }catch(SQLException e){
        mensaje.showMessageDialog(insumosIngresar,"ERROR SQL LLENAR CBO INSUMOS: "+e.getMessage(),"ERROR Almacenar Insumos",0);
        }catch(Exception ex){
        mensaje.showMessageDialog(insumosIngresar,"ERROR JAVA LLENAR CBO INSUMOS: "+ex.getMessage(),"ERROR Almacenar Insumos",0);
        }
    }

    private void buscarInsumo(){
        try{

            stm = con.createStatement();
            rs = stm.executeQuery("select * from productos where codpro="+txtcodins.getText()+"");
            if(rs.next()){
            mensaje.showMessageDialog(insumosIngresar,"Ya existe un producto con este codigo ","ERROR Buscar Insumos",0);
            txtcodins.setText("");
            }else{
            habilitarInsertarInsumo();
            }
            stm.close();

        }catch(SQLException e){
        mensaje.showMessageDialog(insumosIngresar,"Error SQL buscar: "+e.getMessage(),"ERROR Buscar Insumos",0);
        }catch(Exception ee){
        mensaje.showMessageDialog(insumosIngresar,"Error JAVA buscar: "+ee.getMessage(),"ERROR Buscar Insumos",0);
        }
    }

    /*
     * PROGRAMACION FORMULARIO ACTUALIZAR INSUMOS
     */

    private void obtenerDatosFormularioActualizar(){
        b=txtmodins2.getText();
        c=Integer.toString(cbomarins1.getSelectedIndex());
        d=txtdesins1.getText();
        e=txtstoins2.getText();
        f=txtpreins2.getText();
    }

    private void limpiarActualizarInsumo(){
        cbomarins1.removeAllItems();
        txtcodins2.setText("");
        txtcodins2.setEnabled(true);
        txtmodins2.setEnabled(true);
        txtmodins2.setText("");
        cbomarins1.setEnabled(true);    
        txtdesins1.setText("");
        txtdesins1.setEnabled(true);
        txtstoins2.setText("");
        txtstoins2.setEnabled(true);
        txtpreins2.setText("");
        txtpreins2.setEnabled(true);
        txtcodins2.requestFocus();
    }


    private void buscarModificarInsumo(String auxiliar){
        int aux=0;
        try{
        stm = con.createStatement();
        rs = stm.executeQuery("select * from productos where codpro="+auxiliar+"");
        if(rs.next()){
            txtmodins2.setText(rs.getString("modpro"));
            aux = rs.getInt("codmarpro");
            txtdesins1.setText(rs.getString("despro"));
            txtstoins2.setText(rs.getString("stopro"));
            txtpreins2.setText(rs.getString("prepro"));
        }else{
        mensaje.showMessageDialog(insumosConsultar,"No existe ese codigo en la BD","Error Consultar",0);
          txtcodins2.setText("");
        }
        cbomarins1.removeAllItems();
        rs = stm.executeQuery("select * from marcas");
        while(rs.next()){
            cbomarins1.addItem(rs.getString("nommar"));
        }
        cbomarins1.setSelectedIndex(aux);
        stm.close();
        }catch(SQLException e){
          mensaje.showMessageDialog(insumosConsultar,"Error sql consultar: "+e.getMessage(),"Error Consultar",0);
          txtcodins1.setText("");
        }catch(Exception ee){
          mensaje.showMessageDialog(insumosConsultar,"Error java consultar: "+ee.getMessage(),"Error Consultar",0);
          txtcodins1.setText("");
        }
    }

        private void modificarInsumo(){
        obtenerDatosFormularioActualizar();
        try{
            stm = con.createStatement();
            stm.execute("update productos set modpro='"+b+"', codmarpro="+c+", despro='"+d+"', stopro="+e+",prepro="+f+" where codpro="+txtcodins2.getText()+"");
            mensaje.showMessageDialog(insumosActualizar,"Datos Modificados","Actualizar Insumos",1);
            limpiarActualizarInsumo();
        }catch(SQLException e){
          mensaje.showMessageDialog(insumosActualizar,"Error sql actualizar: "+e.getMessage(),"Error actualizar",0);
          txtcodins1.setText("");
        }catch(Exception ee){
          mensaje.showMessageDialog(insumosActualizar,"Error java actualizar: "+ee.getMessage(),"Error actualizar",0);
          txtcodins1.setText("");
        }
        }

        /*
         * PROGRAMACION FORMULARIO CONSULTAR INSUMOS
         */

    private void consultarInsumo(){
        try{
        stm = con.createStatement();
        rs = stm.executeQuery("select * from productos where codpro="+txtcodins1.getText()+"");
        if(rs.next()){
            txtmodins1.setText(rs.getString("modpro"));
            txtdesins2.setText(rs.getString("despro"));
            txtstoins1.setText(rs.getString("stopro"));
            txtpreins1.setText(rs.getString("prepro"));
        }else{
            mensaje.showMessageDialog(insumosConsultar,"No existe este codigo en la BD","Error Consultar",2);
        }
        //CONSULTA PARA MOSTRAR EL NOMBRE DE LA MARCA, SEGUN EL CODIGO ALMACENADO
        rs = stm.executeQuery("select productos.codpro,productos.codmarpro,marcas.codmar,marcas.nommar from productos,marcas where productos.codmarpro=marcas.codmar and codpro="+txtcodins1.getText()+"");
        if(rs.next()){
        txtmarins2.setText(rs.getString("nommar"));
        }
        stm.close();
        }catch(SQLException e){
          mensaje.showMessageDialog(insumosConsultar,"Error sql consultar: "+e.getMessage(),"Error Consultar",0);
          txtcodins1.setText("");
        }catch(Exception ee){
          mensaje.showMessageDialog(insumosConsultar,"Error java consultar: "+ee.getMessage(),"Error Consultar",0);
          txtcodins1.setText("");
        }
    }

        private void eliminarInsumo(){
            try{
                stm = con.createStatement();
                stm.execute("delete from productos where codpro="+txtcodeli.getText()+"");
                mensaje.showMessageDialog(insumosEliminar,"Producto Eliminado","Informacion",1);
                txtcodeli.setText("");
                txtcodeli.requestFocus();
            }catch(SQLException e){
                mensaje.showMessageDialog(insumosEliminar,"Error sql eliminar: "+e.getMessage(),"Error",0);
            }catch(Exception ee){
                mensaje.showMessageDialog(insumosEliminar,"Error java eliminar: "+ee.getMessage(),"Error",0);
            }
        }

        private void llenarTabla(){
            int j=0;
            try{
             stm = con.createStatement();
             rs = stm.executeQuery("select * from productos");
             while(rs.next()){
             tabla.setValueAt(rs.getString("codpro"), j, 0);
             tabla.setValueAt(rs.getString("modpro"), j, 1);
            // quitar comentario para mostrar el codigo de la marca
            // tabla.setValueAt(rs.getString("codmarpro"), j, 2);
             tabla.setValueAt(rs.getString("despro"), j, 3);
             tabla.setValueAt(rs.getString("stopro"), j, 4);
             tabla.setValueAt(rs.getString("prepro"), j, 5);
             j++;
             }
             //inicio codigo para mostrar el nombre de la marca y no el codigo
             //la sentencia puede ser mas corta, usando inner join.
             //si se mostrara la marca a partir del codigo, se deben comentar
             //estas lineas.
             rs = stm.executeQuery("select productos.codmarpro,marcas.codmar,marcas.nommar from marcas,productos where productos.codmarpro=marcas.codmar");
             j=0;
             while(rs.next()){
               tabla.setValueAt(rs.getString("nommar"), j, 2);
               j++;
             }
             //fin del codigo para mostrar el nombre de la marca.
             stm.close();
            }catch(SQLException e){
 mensaje.showMessageDialog(null,"Error sql llenar tabla: "+e.getMessage(),"Error",0);
            }catch(Exception ee){
 mensaje.showMessageDialog(null,"Error java llenar tabla: "+ee.getMessage(),"Error",0);
            }
        }


        private void crearTabla(){
            int filas=0;
            try{
            stm = con.createStatement();
            rs = stm.executeQuery("select count(codpro) as cantidad from productos");
            if(rs.next()){
                filas=rs.getInt("cantidad");
            }
            tabla.setModel(new javax.swing.table.DefaultTableModel(
             new Object[filas][6],
             new String[] {"Codigo","Modelo","Marca","Descripcion","Stock","Precio"}));
             llenarTabla();
            }catch(SQLException e){
            mensaje.showMessageDialog(null,"Error sql crear tabla: "+e.getMessage(),"Error",0);
            }catch(Exception ee){
            mensaje.showMessageDialog(null,"Error sql crear tabla: "+ee.getMessage(),"Error",0);
            }
        }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        insumosIngresar = new javax.swing.JFrame();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtcodins = new javax.swing.JTextField();
        txtmodins = new javax.swing.JTextField();
        cbomarins = new javax.swing.JComboBox();
        txtpreins = new javax.swing.JTextField();
        txtstoins = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtdesins = new javax.swing.JTextArea();
        btnaceins = new javax.swing.JButton();
        btnbusins = new javax.swing.JButton();
        insumosConsultar = new javax.swing.JFrame();
        txtcodins1 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtpreins1 = new javax.swing.JTextField();
        txtstoins1 = new javax.swing.JTextField();
        txtmodins1 = new javax.swing.JTextField();
        btnbusins1 = new javax.swing.JButton();
        txtmarins2 = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtdesins2 = new javax.swing.JTextArea();
        insumosActualizar = new javax.swing.JFrame();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtpreins2 = new javax.swing.JTextField();
        txtstoins2 = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtdesins1 = new javax.swing.JTextArea();
        cbomarins1 = new javax.swing.JComboBox();
        txtmodins2 = new javax.swing.JTextField();
        txtcodins2 = new javax.swing.JTextField();
        btnaceins1 = new javax.swing.JButton();
        btnbusins2 = new javax.swing.JButton();
        insumosEliminar = new javax.swing.JFrame();
        jLabel22 = new javax.swing.JLabel();
        txtcodeli = new javax.swing.JTextField();
        btneli = new javax.swing.JButton();
        insumosListar = new javax.swing.JFrame();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        marcasInsertar = new javax.swing.JFrame();
        jLabel23 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel4.setText("Codigo:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel5.setText("Modelo:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel6.setText("Marca:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel7.setText("Descripción:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel8.setText("Stock:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel9.setText("Precio:");

        txtmodins.setEnabled(false);

        cbomarins.setEnabled(false);

        txtpreins.setEnabled(false);

        txtstoins.setEnabled(false);

        txtdesins.setColumns(20);
        txtdesins.setRows(5);
        txtdesins.setEnabled(false);
        jScrollPane1.setViewportView(txtdesins);

        btnaceins.setText("Aceptar");
        btnaceins.setEnabled(false);
        btnaceins.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaceinsActionPerformed(evt);
            }
        });

        btnbusins.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/help.png"))); // NOI18N
        btnbusins.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbusinsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtcodins, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(52, 52, 52)
                                .addComponent(btnbusins, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(cbomarins, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtmodins, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtpreins, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtstoins, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(154, 154, 154)
                        .addComponent(btnaceins)))
                .addContainerGap(84, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(txtcodins, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnbusins))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtmodins, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(cbomarins, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))))
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtstoins, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtpreins, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(btnaceins)
                .addContainerGap())
        );

        javax.swing.GroupLayout insumosIngresarLayout = new javax.swing.GroupLayout(insumosIngresar.getContentPane());
        insumosIngresar.getContentPane().setLayout(insumosIngresarLayout);
        insumosIngresarLayout.setHorizontalGroup(
            insumosIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        insumosIngresarLayout.setVerticalGroup(
            insumosIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel10.setText("Codigo a Buscar:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel11.setText("Modelo:");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel12.setText("Marca:");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel13.setText("Descripción:");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel14.setText("Stock:");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel15.setText("Precio:");

        txtpreins1.setEditable(false);

        txtstoins1.setEditable(false);

        txtmodins1.setEditable(false);

        btnbusins1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/help.png"))); // NOI18N
        btnbusins1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbusins1ActionPerformed(evt);
            }
        });

        txtmarins2.setEditable(false);

        txtdesins2.setColumns(20);
        txtdesins2.setEditable(false);
        txtdesins2.setRows(5);
        jScrollPane2.setViewportView(txtdesins2);

        javax.swing.GroupLayout insumosConsultarLayout = new javax.swing.GroupLayout(insumosConsultar.getContentPane());
        insumosConsultar.getContentPane().setLayout(insumosConsultarLayout);
        insumosConsultarLayout.setHorizontalGroup(
            insumosConsultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(insumosConsultarLayout.createSequentialGroup()
                .addGroup(insumosConsultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(insumosConsultarLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(insumosConsultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(insumosConsultarLayout.createSequentialGroup()
                                .addGroup(insumosConsultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel14))
                                .addGap(10, 10, 10)
                                .addGroup(insumosConsultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtmodins1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtmarins2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, insumosConsultarLayout.createSequentialGroup()
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(128, 128, 128))
                                    .addGroup(insumosConsultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtpreins1, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtstoins1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE))))
                            .addComponent(jLabel15)))
                    .addGroup(insumosConsultarLayout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(txtcodins1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(btnbusins1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        insumosConsultarLayout.setVerticalGroup(
            insumosConsultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(insumosConsultarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(insumosConsultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(insumosConsultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(txtcodins1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnbusins1))
                .addGap(11, 11, 11)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addGroup(insumosConsultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtmodins1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(insumosConsultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtmarins2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(insumosConsultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(insumosConsultarLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addGroup(insumosConsultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtstoins1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)))
                    .addComponent(jLabel13))
                .addGap(5, 5, 5)
                .addGroup(insumosConsultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtpreins1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(30, 30, 30))
        );

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel16.setText("Codigo:");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel17.setText("Modelo:");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel18.setText("Marca:");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel19.setText("Descripción:");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel20.setText("Stock:");

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel21.setText("Precio:");

        txtdesins1.setColumns(20);
        txtdesins1.setRows(5);
        jScrollPane3.setViewportView(txtdesins1);

        btnaceins1.setText("Aceptar");
        btnaceins1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaceins1ActionPerformed(evt);
            }
        });

        btnbusins2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/help.png"))); // NOI18N
        btnbusins2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbusins2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout insumosActualizarLayout = new javax.swing.GroupLayout(insumosActualizar.getContentPane());
        insumosActualizar.getContentPane().setLayout(insumosActualizarLayout);
        insumosActualizarLayout.setHorizontalGroup(
            insumosActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 328, Short.MAX_VALUE)
            .addGroup(insumosActualizarLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(insumosActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18)
                    .addComponent(jLabel20)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(insumosActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnaceins1)
                    .addGroup(insumosActualizarLayout.createSequentialGroup()
                        .addComponent(txtcodins2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addComponent(btnbusins2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(insumosActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(cbomarins1, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtmodins2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE))
                    .addGroup(insumosActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtpreins2, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtstoins2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        insumosActualizarLayout.setVerticalGroup(
            insumosActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(insumosActualizarLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(insumosActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(insumosActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel16)
                        .addComponent(txtcodins2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnbusins2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(insumosActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtmodins2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(insumosActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addGroup(insumosActualizarLayout.createSequentialGroup()
                        .addComponent(cbomarins1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addGroup(insumosActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19))))
                .addGap(19, 19, 19)
                .addGroup(insumosActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtstoins2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(insumosActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtpreins2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(btnaceins1))
        );

        jLabel22.setText("Digite el codigo del producto a eliminar");

        btneli.setText("Aceptar");
        btneli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout insumosEliminarLayout = new javax.swing.GroupLayout(insumosEliminar.getContentPane());
        insumosEliminar.getContentPane().setLayout(insumosEliminarLayout);
        insumosEliminarLayout.setHorizontalGroup(
            insumosEliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(insumosEliminarLayout.createSequentialGroup()
                .addGroup(insumosEliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(insumosEliminarLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel22))
                    .addGroup(insumosEliminarLayout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(btneli))
                    .addGroup(insumosEliminarLayout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(txtcodeli, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        insumosEliminarLayout.setVerticalGroup(
            insumosEliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(insumosEliminarLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel22)
                .addGap(18, 18, 18)
                .addComponent(txtcodeli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btneli))
        );

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(tabla);

        javax.swing.GroupLayout insumosListarLayout = new javax.swing.GroupLayout(insumosListar.getContentPane());
        insumosListar.getContentPane().setLayout(insumosListarLayout);
        insumosListarLayout.setHorizontalGroup(
            insumosListarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(insumosListarLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );
        insumosListarLayout.setVerticalGroup(
            insumosListarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(insumosListarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jLabel23.setText("Nombre:");

        jLabel24.setText("Descripcion:");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane5.setViewportView(jTextArea1);

        jButton1.setText("Aceptar");

        javax.swing.GroupLayout marcasInsertarLayout = new javax.swing.GroupLayout(marcasInsertar.getContentPane());
        marcasInsertar.getContentPane().setLayout(marcasInsertarLayout);
        marcasInsertarLayout.setHorizontalGroup(
            marcasInsertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(marcasInsertarLayout.createSequentialGroup()
                .addGroup(marcasInsertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(marcasInsertarLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(marcasInsertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(marcasInsertarLayout.createSequentialGroup()
                                .addComponent(jLabel24)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(marcasInsertarLayout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addGap(35, 35, 35)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(marcasInsertarLayout.createSequentialGroup()
                        .addGap(115, 115, 115)
                        .addComponent(jButton1)))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        marcasInsertarLayout.setVerticalGroup(
            marcasInsertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(marcasInsertarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(marcasInsertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(marcasInsertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(26, 26, 26))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/printer.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel2.setForeground(new java.awt.Color(0, 102, 51));
        jLabel2.setText("INSUMOS COMPUTACIONALES");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel3.setForeground(new java.awt.Color(0, 153, 153));
        jLabel3.setText("PIXEL QUEMADO S.A");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jLabel3)))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel1)))
                .addContainerGap(75, Short.MAX_VALUE))
        );

        jMenu1.setText("Insumos");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/add_item.png"))); // NOI18N
        jMenuItem1.setText("Ingresar");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/help.png"))); // NOI18N
        jMenuItem2.setText("Consultar");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/edit_item.png"))); // NOI18N
        jMenuItem3.setText("Actualizar");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/delete_item.png"))); // NOI18N
        jMenuItem4.setText("Eliminar");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuItem11.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/note_accept.png"))); // NOI18N
        jMenuItem11.setText("Listar");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem11);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Marcas");

        jMenuItem5.setText("jMenuItem5");
        jMenu2.add(jMenuItem5);

        jMenuItem6.setText("jMenuItem6");
        jMenu2.add(jMenuItem6);

        jMenuItem7.setText("jMenuItem7");
        jMenu2.add(jMenuItem7);

        jMenuItem8.setText("jMenuItem8");
        jMenu2.add(jMenuItem8);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Ayuda");

        jMenuItem9.setText("jMenuItem9");
        jMenu3.add(jMenuItem9);

        jMenuItem10.setText("jMenuItem10");
        jMenu3.add(jMenuItem10);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        insumosIngresar.setVisible(true);
        insumosIngresar.setSize(400, 380);
        insumosIngresar.setLocationRelativeTo(null);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void btnaceinsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaceinsActionPerformed
        insertarInsumo();
    }//GEN-LAST:event_btnaceinsActionPerformed

    private void btnbusinsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbusinsActionPerformed
        buscarInsumo();
    }//GEN-LAST:event_btnbusinsActionPerformed

    private void btnbusins1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbusins1ActionPerformed
        consultarInsumo();
    }//GEN-LAST:event_btnbusins1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        insumosConsultar.setVisible(true);
        insumosConsultar.setSize(400, 380);
        insumosConsultar.setLocationRelativeTo(null);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void btnaceins1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaceins1ActionPerformed
        modificarInsumo();
    }//GEN-LAST:event_btnaceins1ActionPerformed

    private void btnbusins2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbusins2ActionPerformed
        buscarModificarInsumo(txtcodins2.getText());
    }//GEN-LAST:event_btnbusins2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        insumosActualizar.setVisible(true);
        insumosActualizar.setSize(400, 380);
        insumosActualizar.setLocationRelativeTo(null);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        insumosEliminar.setVisible(true);
        insumosEliminar.setSize(270, 170);
        insumosEliminar.setLocationRelativeTo(null);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void btneliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliActionPerformed
        eliminarInsumo();
    }//GEN-LAST:event_btneliActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        insumosListar.setVisible(true);
        insumosListar.setSize(520, 350);
        insumosListar.setLocationRelativeTo(null);
        crearTabla();
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnaceins;
    private javax.swing.JButton btnaceins1;
    private javax.swing.JButton btnbusins;
    private javax.swing.JButton btnbusins1;
    private javax.swing.JButton btnbusins2;
    private javax.swing.JButton btneli;
    private javax.swing.JComboBox cbomarins;
    private javax.swing.JComboBox cbomarins1;
    private javax.swing.JFrame insumosActualizar;
    private javax.swing.JFrame insumosConsultar;
    private javax.swing.JFrame insumosEliminar;
    private javax.swing.JFrame insumosIngresar;
    private javax.swing.JFrame insumosListar;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JFrame marcasInsertar;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField txtcodeli;
    private javax.swing.JTextField txtcodins;
    private javax.swing.JTextField txtcodins1;
    private javax.swing.JTextField txtcodins2;
    private javax.swing.JTextArea txtdesins;
    private javax.swing.JTextArea txtdesins1;
    private javax.swing.JTextArea txtdesins2;
    private javax.swing.JTextField txtmarins2;
    private javax.swing.JTextField txtmodins;
    private javax.swing.JTextField txtmodins1;
    private javax.swing.JTextField txtmodins2;
    private javax.swing.JTextField txtpreins;
    private javax.swing.JTextField txtpreins1;
    private javax.swing.JTextField txtpreins2;
    private javax.swing.JTextField txtstoins;
    private javax.swing.JTextField txtstoins1;
    private javax.swing.JTextField txtstoins2;
    // End of variables declaration//GEN-END:variables

}

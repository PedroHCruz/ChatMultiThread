/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package aula_03_sockets_multicliente.view;

import aula_03_socket_multicliente.control.UsuarioControl;
import aula_03_sockets_multicliente.Cliente;
import aula_03_sockets_multicliente.Mensagem;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

/**
 *
 * @author 0068961
 */
public class FrameApp extends javax.swing.JFrame {

    private DefaultListModel<String> listaUsuario = new DefaultListModel<>();
    private DefaultListModel<String> listaMensagens = new DefaultListModel<>();
    UsuarioControl userControl;
    private Cliente usuario;
    private String nome;
    //private userS

    public FrameApp() {
        this.userControl = new UsuarioControl();
        initComponents();

        
        login();
        if (Lusuario.getSelectedIndex() != -1) {
            AtualizaMensagem();
        }

    }

    public void login() {
        try {
            usuario = new Cliente("10.90.37.112", 15500);
        } catch (Exception ex) {
            Logger.getLogger(FrameApp.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.nome = JOptionPane.showInputDialog(this, "Informe seu nome");

        if (listaUsuario.contains(this.nome)) {
            System.out.println("c ja existe mané");
        } else {
            try {
                System.out.println("cpf novo");
                usuario.enviar_mensagem("conectar");
                usuario.receber_mensagem();
                usuario.enviar_mensagem(this.nome);
                System.out.println(usuario.receber_mensagem());
                this.setTitle(this.nome);
            } catch (Exception ex) {
                System.err.println("Erro");
                ex.printStackTrace();
            }

        }
        SegundoPlanoOnline();
        AtualizaLista();
    }

    public void SegundoPlanoOnline() {

        SwingWorker AtualizaOnline = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                while (true) {
                    ArrayList<String> usuarios = new ArrayList<>();
                    usuario.enviar_mensagem("banco");
                    usuarios = (ArrayList) usuario.receber_mensagem();
                    listaUsuario.addAll(usuarios);
                    Lusuario.setModel(listaUsuario);
                    System.out.println(".");
                    Thread.sleep(1000);
                    listaUsuario.removeAllElements();
                    publish(2);
                }
            }

        };
        AtualizaOnline.execute();
    }

    public void AtualizaLista() {

        SwingWorker AtualizaMensagem = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                while (true) {
                    System.out.println("+");
                    String nomeDest = Lusuario.getSelectedValue();
                        ArrayList<String> mensagens = new ArrayList<>();
                        listaMensagens.removeAllElements();
                        System.out.println("selecionou");

                        usuario.enviar_mensagem("listaMensagemPrivado");
                        usuario.receber_mensagem();
                        usuario.enviar_mensagem(nomeDest);
                        mensagens = (ArrayList) usuario.receber_mensagem();
                        Lmensagem.setModel(listaMensagens);
                        listaMensagens.addAll(mensagens);
                        Thread.sleep(100);

                        publish(5);
                    

                }
            }

        };
        AtualizaMensagem.execute();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Lmensagem = new javax.swing.JList<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        Lusuario = new javax.swing.JList<>();
        FTexto = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(250, 250, 250));

        Lmensagem.setBackground(new java.awt.Color(204, 204, 204));
        jScrollPane1.setViewportView(Lmensagem);

        jButton1.setText("Particular");

        jButton2.setText("Geral");

        Lusuario.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(Lusuario);

        FTexto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FTextoActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Serif", 0, 24)); // NOI18N
        jButton3.setText(">");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(FTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(70, 70, 70))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 638, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(FTexto)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    
    private void AtualizaMensagem(){
        
    }
    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed

    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try {
            usuario.enviar_mensagem("desconectar");
            usuario.receber_mensagem();
            usuario.enviar_mensagem(this.nome);
            usuario.receber_mensagem();
        } catch (Exception ex) {
            System.err.println("Erro");
            ex.printStackTrace();
        }
    }//GEN-LAST:event_formWindowClosing

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        String txt = FTexto.getText();

    }//GEN-LAST:event_jButton3ActionPerformed

    private void FTextoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FTextoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FTextoActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrameApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameApp().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField FTexto;
    private javax.swing.JList<String> Lmensagem;
    private javax.swing.JList<String> Lusuario;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}

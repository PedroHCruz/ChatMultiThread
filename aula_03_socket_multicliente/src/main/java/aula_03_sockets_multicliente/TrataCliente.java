package aula_03_sockets_multicliente;

import aula_03_socket_multicliente.control.UsuarioControl;
import java.net.Socket;
import java.util.ArrayList;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class TrataCliente implements Runnable {

    private Socket soquete_cliente;
    private ObjectOutputStream saida;
    private ObjectInputStream entrada;
    private ArrayList<Mensagem> mensagens;
    private ArrayList<String> usuarios;
    private UsuarioControl usuarioControl;

    public TrataCliente(Socket soquete_cliente, ArrayList<Mensagem> mensagens, ArrayList<String> usuarios) throws Exception {
        super();
        this.soquete_cliente = soquete_cliente;
        this.saida = new ObjectOutputStream(this.soquete_cliente.getOutputStream());
        this.entrada = new ObjectInputStream(this.soquete_cliente.getInputStream());
        this.mensagens = mensagens;
        this.usuarios = usuarios;
        this.usuarioControl = new UsuarioControl();
    }

    public void enviar_mensagem(Object mensagem) throws Exception {
        this.saida.writeObject(mensagem);
    }

    public Object receber_mensagem() throws Exception {
        return this.entrada.readObject();
    }

    public void finalizar() throws IOException {
        this.soquete_cliente.close();
    }

    @Override
    public void run() {
        String user = soquete_cliente.getInetAddress().getHostAddress();
        System.out.println(user);
        String request;
        try {

            
            
            do {

                request = (String) receber_mensagem();
                System.out.println(request);
                
                    System.out.println("enviar banco");
                    
                    
                    enviar_mensagem(usuarioControl.ListaUsuario());
              

            } while (!request.equals("F"));
        
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

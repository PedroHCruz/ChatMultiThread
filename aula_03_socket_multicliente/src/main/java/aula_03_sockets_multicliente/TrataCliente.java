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
                if(request.equals("banco")){
                System.out.println(request);
                
                    System.out.println("enviar banco");
                    
                    
                    enviar_mensagem(usuarioControl.ListaUsuario());
                }
                
                if(request.equals("conectar")){
                    System.out.println("conectar usuario");
                    enviar_mensagem("informe o nome");
                    String nome = (String) receber_mensagem();
                    usuarioControl.CadastraUsuario(nome);
                    enviar_mensagem("conectado");
                }
              
                if(request.equals("desconectar")){
                    System.out.println("Desconectar usuario");
                    enviar_mensagem("desconectar usuario");
                    String nome = (String) receber_mensagem();
                    usuarioControl.DesconectaUsuario(nome);
                    enviar_mensagem("desconectado");
                }
                
                if(request.equals("listaMensagemPrivado")){
                    ArrayList<String> mensagens = new ArrayList<>();
                    System.out.println("ListaMensagemPrivada");
                    enviar_mensagem("Listar Mensagem");
                    String nomeDest = (String) receber_mensagem();
                    mensagens = usuarioControl.ListaMSGUser(nomeDest);
                    enviar_mensagem(mensagens);
                }

            } while (!request.equals("F"));
        
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

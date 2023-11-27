package aula_03_sockets_multicliente;

import aula_03_sockets_multicliente.view.FrameApp;
import java.net.Socket;
import java.util.ArrayList;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Cliente {

    private Socket soquete;
    private ObjectOutputStream saida;
    private ObjectInputStream entrada;
    private ArrayList<Mensagem> mensagens;
    private static ArrayList<String> usuarios;

    public Cliente(String endereco, int porta) throws Exception {
        super();
        this.soquete = new Socket(endereco, porta);
        this.saida = new ObjectOutputStream(this.soquete.getOutputStream());
        this.entrada = new ObjectInputStream(this.soquete.getInputStream());
        this.usuarios = new ArrayList<>();
    }

    public void enviar_mensagem(Object mensagem) throws Exception {
        this.saida.writeObject(mensagem);
    }

    public Object receber_mensagem() throws Exception {
        return this.entrada.readObject();
    }

    public void finalizar() throws IOException {
        this.soquete.close();
    }

    public static void main(String[] args) throws Exception {
        Scanner entrada = new Scanner(System.in);

        
        
    }

}
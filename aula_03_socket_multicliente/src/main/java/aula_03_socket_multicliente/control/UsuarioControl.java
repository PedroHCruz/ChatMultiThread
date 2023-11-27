/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aula_03_socket_multicliente.control;

import aula_03_socket_multicliente.dao.UsuarioDAO;
import java.util.ArrayList;

/**
 *
 * @author 0068961
 */
public class UsuarioControl {
    
    private UsuarioDAO dao;

    public UsuarioControl() {
        this.dao = new UsuarioDAO();
    }
    
    public boolean CadastraUsuario(String nome){
        return this.dao.CadastraUsuario(nome);
    }
    
    public ArrayList<String> ListaUsuario(){
        return this.dao.ListaUsuario();
    }
    
    public boolean CadastraMensagem(String msg, String dest, String remet){
        return this.dao.CadastraMensagem(msg, dest, remet);
    }
    public boolean DesconectaUsuario(String nome){
        return this.dao.desconectaUsuario(nome);
    }
    
    public ArrayList<String> ListaALL(){
        return this.dao.ReceberTodas();
    }
    
    public ArrayList<String> ListaMSGUser(String nome){
        return this.dao.ReceberMSGUser(nome);
    }
}

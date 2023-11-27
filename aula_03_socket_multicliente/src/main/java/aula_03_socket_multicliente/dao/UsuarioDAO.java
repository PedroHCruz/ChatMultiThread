/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aula_03_socket_multicliente.dao;

import aula_03_socket_multicliente.tools.FactoryDatabase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author 0068961
 */
public class UsuarioDAO {
    
    private Connection ConexaoBD;

    public UsuarioDAO() {
        this.ConexaoBD = FactoryDatabase.getConexao();
    }
    
    public boolean CadastraUsuario(String nome){
        String sql = "INSERT INTO redes.usuario (nome) VALUES (?);";
        
        try(PreparedStatement trans = this.ConexaoBD.prepareStatement(sql)){
            trans.setString(1, nome);
            trans.execute();
            return true;
        } catch (SQLException ex){
            System.err.println("Erro ao cadastrar usuario");
            ex.printStackTrace();
            return false;
        }
    }
    public ArrayList<String> ListaUsuario(){
        ArrayList<String> listaUsers = new ArrayList<>();
        
        String sql = "SELECT nome FROM redes.usuario ORDER BY nome ASC;";
        
        try(PreparedStatement trans = this.ConexaoBD.prepareStatement(sql)){
            ResultSet resultadoBD = trans.executeQuery();
            
            while(resultadoBD.next()){
                String nome = resultadoBD.getString("nome");
                if(listaUsers.contains(nome)){
                    
                } else {
                listaUsers.add(nome);
                }
            }
            return listaUsers;
            
        } catch (SQLException ex){
            System.err.println("Erro ao listar usuarios");
            ex.printStackTrace();
            return null;
        }
    }
    
    public boolean CadastraMensagem(String msg, String dest, String remetente){
        String sql = "INSERT INTO redes.mensagem (texto, destinatario, remetente) VALUES (?,?,?);";
        
        try(PreparedStatement trans = this.ConexaoBD.prepareStatement(sql)){
            trans.setString(1, msg);
            trans.setString(2, dest);
            trans.setString(3, remetente);
            trans.execute();
            return true;
        } catch (SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }
        public boolean desconectaUsuario(String nome){
        
        String sql = "DELETE FROM redes.usuario WHERE nome='" + nome + "';";
        try(PreparedStatement trans = this.ConexaoBD.prepareStatement(sql)) {
            ResultSet resultadoBD = trans.executeQuery();
            if(resultadoBD.next()){
                return true;
            } else {
                return false;
            } 
        } catch (SQLException ex){
            return false;
        }
    }
        
        public ArrayList<String> ReceberTodas(){
            ArrayList<String> mensagensALL = new ArrayList<>();
            String sql = "SELECT * FROM redes.mensagem WHERE destinatario = 'all'";
            try(PreparedStatement trans = this.ConexaoBD.prepareStatement(sql)){
                ResultSet resultadoBD = trans.executeQuery();
            
            while(resultadoBD.next()){
                String mens = resultadoBD.getString("texto");
                String remetente = resultadoBD.getString("remetente");
                String result = mens.concat(" - " + remetente + " |");
                if(mensagensALL.contains(result)){
                    
                } else {
                mensagensALL.add(result);
                }
            }
            return mensagensALL;
            
        } catch (SQLException ex){
            System.err.println("Erro ao listar mensagens");
            ex.printStackTrace();
            return null;
        }
    }
        public ArrayList<String> ReceberMSGUser(String nome){
            ArrayList<String> mensagensALL = new ArrayList<>();
            String sql = "SELECT * FROM redes.mensagem WHERE destinatario = '" + nome + "';";
            try(PreparedStatement trans = this.ConexaoBD.prepareStatement(sql)){
                ResultSet resultadoBD = trans.executeQuery();
            
            while(resultadoBD.next()){
                String mens = resultadoBD.getString("texto");
                String remetente = resultadoBD.getString("remetente");
                String result = mens.concat(" - " + remetente + " |");
                if(mensagensALL.contains(result)){
                    
                } else {
                mensagensALL.add(result);
                }
            }
            return mensagensALL;
            
        } catch (SQLException ex){
            System.err.println("Erro ao listar mensagens");
            ex.printStackTrace();
            return null;
        }
    }
}


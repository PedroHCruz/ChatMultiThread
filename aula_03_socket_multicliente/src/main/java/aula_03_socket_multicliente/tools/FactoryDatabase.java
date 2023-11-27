/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aula_03_socket_multicliente.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author 0068961
 */
public class FactoryDatabase {
    
    private static Connection ConexaoBD;
    private static String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static String USER = "postgres";
    private static String PASS = "postgres";
    
    public static Connection getConexao(){
        if(ConexaoBD == null){
            try{
            ConexaoBD = DriverManager.getConnection(URL, USER, PASS);
            return ConexaoBD;
            } catch (SQLException ex){
                System.err.println("Erro conexao");
                return null;
            }
        }
        return ConexaoBD;
    }
    
}

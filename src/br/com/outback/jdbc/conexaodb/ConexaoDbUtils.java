/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.outback.jdbc.conexaodb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author rafael
 */
public class ConexaoDbUtils {
	
	//Nome do usu�rio do mysql
	   private static final String USERNAME = "root";
	 
	   //Senha do mysql
	   private static final String PASSWORD = "";
	   
	   //Dados de caminho, porta e nome da base de dados que ir� ser feita a conex�o
	   private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/outback";
	   
	   //* drive do mysql
       private String driverName = "com.mysql.jdbc.Driver";

    public Connection retornaConexaoBancoDeDados() throws ClassNotFoundException, SQLException {
             
        Class.forName(driverName); //Faz com que a classe seja carregada pela JVM
        
        //Cria a conex�o com o banco de dados
        Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
   
        return connection;
       
    }
}

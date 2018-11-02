/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.outback.jdbc.conexaodb;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author rafael
 */
public final class SingletonConexaoBanco {

	private static SingletonConexaoBanco singleton;
	private Connection con;
	// private String url;
	private static String senhaBanco;
	private static String usuarioBanco;
	private static String nomeBanco;
	private static String instancia;
	private static String ip;

	private SingletonConexaoBanco() throws ClassNotFoundException, SQLException {
		
		ConexaoDbUtils conUtils = new ConexaoDbUtils();

		con = conUtils.retornaConexaoBancoDeDados();

	}

	public static synchronized SingletonConexaoBanco getInstance() throws ClassNotFoundException, SQLException {
		
		if (singleton == null) {
			singleton = new SingletonConexaoBanco();
		}
		return singleton;
	}

	public Connection getConnection() throws ClassNotFoundException,SQLException {
		
		if (con.isClosed()) {
			ConexaoDbUtils conUtils = new ConexaoDbUtils();
			con = conUtils.retornaConexaoBancoDeDados();
		}
		return con;
	}
}

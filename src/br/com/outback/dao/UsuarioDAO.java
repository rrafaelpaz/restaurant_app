package br.com.outback.dao;

import java.util.HashMap;
import java.util.Map;

import br.com.outback.bean.Usuario;

public class UsuarioDAO  extends GenericDAO<Usuario>{
	
	private static final long serialVersionUID = 1L;
	private static final String CONSULTA_LOGIN = "Usuario.findUserByLogin";

	public UsuarioDAO() {
		super(Usuario.class);
	}

	public void delete(Usuario usuario) {
		super.delete(usuario.getId(), Usuario.class);
	}
	
	public Usuario existeUsuarioCadastrado(String nome, String senha){
		
		//* Cria o mapa com as colunas e seus respectivos valores para consulta
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("nome", nome);
		parametros.put("senha", senha);
		
		//* chama a consulta generica, juntamente com o sql de consulta e parametros
		Usuario user = (Usuario) super.buscaGenerica(CONSULTA_LOGIN, parametros);
		
		return user;
		
	}
	

}

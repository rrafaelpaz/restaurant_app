package br.com.outback.facade;

import java.io.Serializable;
import java.util.List;

import br.com.outback.bean.Usuario;
import br.com.outback.dao.UsuarioDAO;

public class UsuarioFacade implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private UsuarioDAO usuarioDAO = new UsuarioDAO();

	public Usuario isValidoLogin(String nome, String senha) {
		
		usuarioDAO.beginTransaction();
		Usuario user = usuarioDAO.existeUsuarioCadastrado(nome, senha);
		usuarioDAO.closeTransaction();
		return user;
		
	}
	
	public void createUsuario(Usuario user) {
		usuarioDAO.beginTransaction();
		usuarioDAO.save(user);
		usuarioDAO.commitAndCloseTransaction();
	}

	public void updateUsuario(Usuario user) {
		usuarioDAO.beginTransaction();
		Usuario persistedUser = usuarioDAO.find(user.getId());
		persistedUser.setNome(user.getNome());
		persistedUser.setSenha(user.getSenha());
		persistedUser.setIsFuncionario(user.getIsFuncionario());
		usuarioDAO.update(persistedUser);
		usuarioDAO.commitAndCloseTransaction();
	}

	public Usuario findUsuario(long userId) {
		usuarioDAO.beginTransaction();
		Usuario user = usuarioDAO.find(userId);
		usuarioDAO.closeTransaction();
		return user;
	}

	public List<Usuario> listAll() {
		usuarioDAO.beginTransaction();
		List<Usuario> result = usuarioDAO.findAll();
		usuarioDAO.closeTransaction();
		return result;
	}

	public void deleteUsuario(Usuario user) {
		usuarioDAO.beginTransaction();
		Usuario persistedUser = usuarioDAO.findReferenceOnly(user.getId());
		usuarioDAO.delete(persistedUser);
		usuarioDAO.commitAndCloseTransaction();
	}
}
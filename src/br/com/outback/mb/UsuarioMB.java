package br.com.outback.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.outback.bean.Usuario;
import br.com.outback.facade.UsuarioFacade;
import br.com.outback.util.MessagesController;

@ManagedBean
@SessionScoped
public class UsuarioMB implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private UsuarioFacade usuarioFacede = new UsuarioFacade();
	private Usuario usuario = new Usuario();
	
	@SuppressWarnings("unused")
	private List<Usuario> usuarios;
    private List<Usuario> filteredUsuarios;
	
	
	public void salvar(){
		//* coloquei esse  < 1, porque n�o sei o porque, mas os novos registros vem com o id == 0
		if (this.usuario.getId() == null || this.usuario.getId() < 1) {
			//* estou forcando o id == nul
			usuario.setId(null);
			usuarioFacede.createUsuario(usuario);
		} else {
			usuarioFacede.updateUsuario(usuario);
		}
		this.usuario = new Usuario();
	
		MessagesController.msgOperacaoRealizadaComSucesso();

		MessagesController.refresh();
	}
	
	public void remove(Usuario usuario) {
	    try {
	        String teste = "s";
	    	//actorService.remove(actor);
	        //actorList = actorService.searchAll();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	//* editar o datatable
	 
	public void editar(Usuario usuario) {
        
		this.usuario = usuario;
    }
	     
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	//* esses filteredUsuario � necess�rio, por�m n�o me ficou ainda claro o porque, sendo que � apenas um get e set
	public List<Usuario> getFilteredUsuarios() {
		return filteredUsuarios;
	}

	public void setFilteredUsuarios(List<Usuario> filteredUsuarios) {
		this.filteredUsuarios = filteredUsuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
    
	//* recupera os itens e joga no datatable
	public List<Usuario> getUsuarios() {
		List<Usuario> usuarios = usuarioFacede.listAll();
		return usuarios;
	}	
	
	
}

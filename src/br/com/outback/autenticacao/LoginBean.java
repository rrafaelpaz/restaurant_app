package br.com.outback.autenticacao;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.outback.bean.Usuario;
import br.com.outback.facade.UsuarioFacade;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 7765876811740798583L;
	
	private String username;
	private String password;
	
	private boolean loggedIn;

	@ManagedProperty(value="#{navigationBean}")
	private NavigationBean navigationBean;
	

	public String doLogin() {
		
		// Get every user from our sample database :)
		UsuarioFacade usuarioFacade = new UsuarioFacade();
		
		Usuario user = usuarioFacade.isValidoLogin(username, password);
		
			// Successful login
		if (null != user) {
			loggedIn = true;
			return navigationBean.redirectToWelcome();
		}
		
		// Set login ERROR
		FacesMessage msg = new FacesMessage("Usuário ou a Senha está errada!", "ERROR MSG");
        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage(null, msg);
		
		// To to login page
		return navigationBean.toLogin();
		
	}
	
	/**
	 * Logout operation.
	 * @return
	 */
	public String doLogout() {
		// Set the paremeter indicating that user is logged in to false
		loggedIn = false;
		
		// Set logout message
		FacesMessage msg = new FacesMessage("Logout success!", "INFO MSG");
		msg.setSeverity(FacesMessage.SEVERITY_INFO);
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
		return navigationBean.redirectToLogout();
	}

	// ------------------------------
	// Getters & Setters 
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public void setNavigationBean(NavigationBean navigationBean) {
		this.navigationBean = navigationBean;
	}
	
}

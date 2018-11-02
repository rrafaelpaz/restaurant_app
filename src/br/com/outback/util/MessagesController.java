package br.com.outback.util;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

public abstract class MessagesController {

	private static final String MSGSUCESSOOPERACAO = "Operação realizada com sucesso!";
	private static final String MSGSUCESSO = "Registro inserido com sucesso!";
	private static final String MSGSUCESSOALT = "Registro alterado com sucesso!";
	private static final String MSGSUCESSODEL = "Registro removido com sucesso!";
	private static final String MSGERRO = "Nao foi possível inserir o registro!";
	private static final String MSGERRODEL = "Nao foi possível remover o registro!";
	
	public static void refresh() {

		FacesContext context = FacesContext.getCurrentInstance();
		Application application = context.getApplication();
		ViewHandler viewHandler = application.getViewHandler();
		UIViewRoot viewRoot = viewHandler.createView(context, context.getViewRoot().getViewId());
		context.setViewRoot(viewRoot);
		context.renderResponse();

	}

	
	public static void msgOperacaoRealizadaComSucesso() {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				MSGSUCESSOOPERACAO, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public static void msgRegistroInserido() {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				MSGSUCESSO, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public static void msgRegistroAlterado() {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				MSGSUCESSOALT, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public static void msgRegistroRemovido() {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				MSGSUCESSODEL, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public static void msgErroInserirRegistro() {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				MSGERRO, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public static void msgErroRemoverRegistro() {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				MSGERRODEL, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

}

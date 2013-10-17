package br.com.caelum.notasfiscais.listener;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.inject.Inject;

import br.com.caelum.notasfiscais.mb.UsuarioLogadoBean;

public class Autorizador implements PhaseListener {
	@Inject
	private UsuarioLogadoBean usuarioLogadoBean;
	@Inject
	private FacesContext context;
	@Inject
	private NavigationHandler handler;

	@Override
	public void afterPhase(PhaseEvent event) {
		
		if ("/login.xhtml".equals(context.getViewRoot().getViewId())) {
			return;
		}
		
		if(!usuarioLogadoBean.isLogado()){
			handler.handleNavigation(context, null, "login?faces-redirect=true");
			context.renderResponse();
		}
		return;
	}

	@Override
	public void beforePhase(PhaseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}

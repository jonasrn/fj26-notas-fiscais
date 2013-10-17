package br.com.caelum.notasfiscais.util;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;

public class SSFProduces {
	
	@Produces @RequestScoped
	public FacesContext getFacesContext(){
		return FacesContext.getCurrentInstance();
	}
	@Produces @RequestScoped
	public NavigationHandler getNavigationHandler(FacesContext context){
		return context.getApplication().getNavigationHandler();
		
	}

}

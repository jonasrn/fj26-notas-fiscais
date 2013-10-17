package br.com.caelum.notasfiscais.listener;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import br.com.caelum.notasfiscais.modelo.Usuario;
import br.com.caelum.notasfiscais.util.EmailComercial;
import br.com.caelum.notasfiscais.util.EmailFinanceiro;

public class LoginListener {
	
	@Inject @EmailComercial
	private String emailComercial;
	@Inject @EmailFinanceiro
	private String emailFinanceiro;
	
	public void onLogin(@Observes Usuario usuario){
		System.out.println("Usuario logou no sistema:" +usuario.getLogin());
		System.out.println("Email comercial:" + emailComercial);
		System.out.println("Email financeiro:" + emailFinanceiro);
		
	}

}

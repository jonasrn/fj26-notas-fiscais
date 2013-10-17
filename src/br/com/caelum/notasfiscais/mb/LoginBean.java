package br.com.caelum.notasfiscais.mb;

import java.io.Serializable;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import br.com.caelum.notasfiscais.dao.UsuarioDao;
import br.com.caelum.notasfiscais.modelo.Usuario;

@Model
public class LoginBean implements Serializable{
	
	Usuario usuario = new Usuario();
	@Inject
	UsuarioDao usuarioDao;
	@Inject
	private UsuarioLogadoBean usuarioLogadoBean;
	@Inject
	private Event<Usuario> eventoLogin;
	
	public String efetuaLogin(){
		boolean loginValido = usuarioDao.existe(this.usuario);
		if(loginValido){
			eventoLogin.fire(usuario);
			usuarioLogadoBean.logar(usuario);
			return "produto?faces-redirect=true";
		} else {
			usuarioLogadoBean.deslogar();
			this.usuario = new Usuario();
			return "login";
		}
	}
	
	public Usuario getUsuario(){
		return this.usuario;
	}
	public void setUsuario(Usuario usuario){
		this.usuario = usuario;
	}
	
	public String logout(){
		usuarioLogadoBean.deslogar();
		return "login?faces-redirect=true";
	}

}

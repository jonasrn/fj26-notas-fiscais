package br.com.caelum.notasfiscais.mb;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.caelum.notasfiscais.dao.UsuarioDao;
import br.com.caelum.notasfiscais.modelo.Usuario;

@SessionScoped
@ManagedBean
public class LoginBean {
	
	Usuario usuario = new Usuario();
	
	public String efetuaLogin(){
		UsuarioDao usuarioDao = new UsuarioDao();
		boolean loginValido = usuarioDao.existe(this.usuario);
		if(loginValido){
			return "produto?faces-redirect=true";
		} else {
			this.usuario = new Usuario();
		}
		System.out.println("O login era valida "+ loginValido);
		return "login";
	}
	
	public Usuario getUsuario(){
		return this.usuario;
	}

}

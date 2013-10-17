package br.com.caelum.notasfiscais.util;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@RequestScoped
public class JPAUtil implements Serializable{
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("notas");

	@Produces @RequestScoped
	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
	
	public void close(@Disposes EntityManager manager){
		manager.close();
	}
}
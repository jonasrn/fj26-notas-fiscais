package br.com.caelum.notasfiscais.tx;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

@Interceptor
@Transactional
public class TransactionInterceptor implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private EntityManager manager;
	
	@AroundInvoke
	public Object intercept(InvocationContext context) throws Exception{
		
		System.out.println("ABRINDO A TRANSAÇÃO");
		manager.getTransaction().begin();
		
		Object resultado = context.proceed();
		
		manager.getTransaction().commit();
		
		System.out.println("Commitando transação");
		return resultado;
		
		
	}
	

}

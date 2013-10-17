package br.com.caelum.notasfiscais.mb;

import java.io.Serializable;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.notasfiscais.dao.NotaFiscalDao;
import br.com.caelum.notasfiscais.dao.ProdutoDao;
import br.com.caelum.notasfiscais.modelo.Item;
import br.com.caelum.notasfiscais.modelo.NotaFiscal;
import br.com.caelum.notasfiscais.modelo.Produto;
import br.com.caelum.notasfiscais.tx.Transactional;


@Named @ConversationScoped
public class NotaFiscalBean implements Serializable{

	private NotaFiscal notaFiscal = new NotaFiscal();
	@Inject
	private NotaFiscalDao notaFiscalDao;
	@Inject
	private ProdutoDao produtoDao;
	@Inject
	private Conversation conversation;
	
	private Item item = new Item();
	private Long idProduto;
	
	public String avancar(){
		if(conversation.isTransient()){
			conversation.begin();
		}
		return "item?faces-redirect=true";
	}

	@Transactional
	public String gravar() {
		System.out.println("Tamanhoooooooooooooooooo"+notaFiscal.getItens().size());
		this.notaFiscalDao.adiciona(notaFiscal);
		conversation.end();
		//this.notaFiscal = new NotaFiscal();
		return "notafiscal?faces-redirect=true";
	}

	public NotaFiscal getNotaFiscal() {
		return notaFiscal;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}
	
	@Transactional
	public void guardaItem(){
		Produto produto = produtoDao.buscaPorId(idProduto);
		item.setProduto(produto);
		item.setValorUnitario(produto.getPreco());
		
		notaFiscal.getItens().add(item);
		item.setNotaFiscal(notaFiscal);
		
		item = new Item();
	}

}

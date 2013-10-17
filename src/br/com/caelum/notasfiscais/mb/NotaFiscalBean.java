package br.com.caelum.notasfiscais.mb;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import br.com.caelum.notasfiscais.dao.NotaFiscalDao;
import br.com.caelum.notasfiscais.dao.ProdutoDao;
import br.com.caelum.notasfiscais.modelo.Item;
import br.com.caelum.notasfiscais.modelo.NotaFiscal;
import br.com.caelum.notasfiscais.modelo.Produto;
import br.com.caelum.notasfiscais.tx.Transactional;

@ViewScoped
public class NotaFiscalBean {

	private NotaFiscal notaFiscal = new NotaFiscal();
	@Inject
	private NotaFiscalDao notaFiscalDao;
	@Inject
	private ProdutoDao produtoDao;
	private Item item = new Item();
	private Long idProduto;

	@Transactional
	public void gravar() {
		this.notaFiscalDao.adiciona(notaFiscal);
		this.notaFiscal = new NotaFiscal();
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

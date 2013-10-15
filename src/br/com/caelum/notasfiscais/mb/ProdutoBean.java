package br.com.caelum.notasfiscais.mb;

import java.util.List;
import javax.faces.bean.ManagedBean;
import br.com.caelum.notasfiscais.dao.ProdutoDao;
import br.com.caelum.notasfiscais.modelo.Produto;

@javax.faces.bean.ViewScoped
@ManagedBean
public class ProdutoBean {
	private Produto produto = new Produto();
	private List<Produto> produtos;

	public Produto getProduto() {
		return this.produto;
	}

	public void grava() {
		ProdutoDao produtoDao = new ProdutoDao();

		if (produto.getId() == null) {
			produtoDao.adiciona(produto);

		} else {
			produtoDao.atualiza(produto);
		}
		this.produto = new Produto();
		this.produtos = produtoDao.listaTodos();
	}

	public List<Produto> getProdutos() {
		if (produtos == null) {
			System.out.println("Carregando produtos");
			produtos = new ProdutoDao().listaTodos();
		}
		return produtos;
	}

	public void remove(Produto produto) {
		ProdutoDao produtoDao = new ProdutoDao();
		produtoDao.remove(produto);
		this.produtos = produtoDao.listaTodos();
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

}

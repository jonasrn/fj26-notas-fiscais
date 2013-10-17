package br.com.caelum.notasfiscais.mb;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Model;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.notasfiscais.dao.ProdutoDao;
import br.com.caelum.notasfiscais.modelo.Produto;
import br.com.caelum.notasfiscais.tx.Transactional;

@Model
public class ProdutoBean {
	private Produto produto = new Produto();
	private List<Produto> produtos;
	@Inject
	private ProdutoDao produtoDao;

	public Produto getProduto() {
		return this.produto;
	}

	@Transactional
	public void grava() {
		System.out.println("grava-teste");
		if (produto.getId() == null) {
			produtoDao.adiciona(produto);

		} else {
			produtoDao.atualiza(produto);
		}
		this.produto = new Produto();
		this.produtos = produtoDao.listaTodos();
	}
	@Transactional
	public List<Produto> getProdutos() {
		if (produtos == null) {
			System.out.println("Carregando produtos");
			produtos = produtoDao.listaTodos();
		}
		return produtos;
	}
	@Transactional
	public void remove(Produto produto) {
		System.out.println("chegouuu");
		produtoDao.remove(produto);
		this.produtos = produtoDao.listaTodos();
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

}

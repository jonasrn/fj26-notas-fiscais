package br.com.caelum.notasfiscais.mb;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import org.primefaces.model.LazyDataModel;

import br.com.caelum.notasfiscais.dao.NotaFiscalDao;
import br.com.caelum.notasfiscais.modelo.NotaFiscal;
import br.com.caelum.notasfiscais.stereotypes.ViewModel;
import br.com.caelum.notasfiscais.tx.Transactional;

@ViewModel
public class ListaNotasFiscaisBean implements Serializable{
	@Inject
	private NotaFiscalDao notaFiscalDao;
	private List<NotaFiscal> notasFiscais;
	@Inject
	private LazyDataModel<NotaFiscal> dataModel;
	
	@Transactional
	public List<NotaFiscal> getNotasFiscais(){
		return notaFiscalDao.listaTodos();
	}
	
	@Transactional
	public LazyDataModel<NotaFiscal> getdataModel(){
		return dataModel;
	}
	

}

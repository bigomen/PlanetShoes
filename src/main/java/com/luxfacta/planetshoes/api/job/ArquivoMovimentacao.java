package com.luxfacta.planetshoes.api.job;

import com.opencsv.bean.CsvBindByPosition;

public class ArquivoMovimentacao {

	@CsvBindByPosition(position = 0)
	private String franqueadoCNPJ;
	@CsvBindByPosition(position = 1)
	private String lojaCNPJ;
	@CsvBindByPosition(position = 2)
	private int lojaCodigo = 0;
	
	@CsvBindByPosition(position = 3)
	private long semana = 0;
	@CsvBindByPosition(position = 4)
	private String dataInicio;
	@CsvBindByPosition(position = 5)
	private String dataFim;
	
	@CsvBindByPosition(position = 6)
	private String marca;
	@CsvBindByPosition(position = 7)
	private int modeloCodigo = 0;
	@CsvBindByPosition(position = 8)
	private String modeloNome;
	@CsvBindByPosition(position = 13)
	private String genero;
	@CsvBindByPosition(position = 14)
	private String EAN;
	@CsvBindByPosition(position = 15)
	private String SKU;
	
	@CsvBindByPosition(position = 9)
	private int corCodigo = 0;
	@CsvBindByPosition(position = 10)
	private String corNome;
	
	@CsvBindByPosition(position = 11)
	private int tamanhoCodigo = 0;
	@CsvBindByPosition(position = 12)
	private int tamanhoNumero = 0;
	
	@CsvBindByPosition(position = 16)
	private Long posicaoEstoque = (long)0;
	@CsvBindByPosition(position = 17)
	private Long saida = (long)0;
	@CsvBindByPosition(position = 18)
	private Long entrada = (long)0;
	@CsvBindByPosition(position = 20)
	private Long saidaOutra = (long)0;
	@CsvBindByPosition(position = 19)
	private Long entradaOutra = (long)0;
	
	public String getFranqueadoCNPJ() {
		return franqueadoCNPJ;
	}
	public void setFranqueadoCNPJ(String franqueadoCNPJ) {
		this.franqueadoCNPJ = franqueadoCNPJ;
	}
	public String getLojaCNPJ() {
		return lojaCNPJ;
	}
	public void setLojaCNPJ(String lojaCNPJ) {
		this.lojaCNPJ = lojaCNPJ;
	}
	public int getLojaCodigo() {
		return lojaCodigo;
	}
	public void setLojaCodigo(int lojaCodigo) {
		this.lojaCodigo = lojaCodigo;
	}
	public long getSemana() {
		return semana;
	}
	public void setSemana(long semana) {
		this.semana = semana;
	}
	public String getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}
	public String getDataFim() {
		return dataFim;
	}
	public void setDataFim(String dataFim) {
		this.dataFim = dataFim;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public int getModeloCodigo() {
		return modeloCodigo;
	}
	public void setModeloCodigo(int modeloCodigo) {
		this.modeloCodigo = modeloCodigo;
	}
	public String getModeloNome() {
		return modeloNome;
	}
	public void setModeloNome(String modeloNome) {
		this.modeloNome = modeloNome;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public String getEAN() {
		return EAN;
	}
	public void setEAN(String eAN) {
		EAN = eAN;
	}
	public String getSKU() {
		return SKU;
	}
	public void setSKU(String sKU) {
		SKU = sKU;
	}
	public int getCorCodigo() {
		return corCodigo;
	}
	public void setCorCodigo(int corCodigo) {
		this.corCodigo = corCodigo;
	}
	public String getCorNome() {
		return corNome;
	}
	public void setCorNome(String corNome) {
		this.corNome = corNome;
	}
	public int getTamanhoCodigo() {
		return tamanhoCodigo;
	}
	public void setTamanhoCodigo(int tamanhoCodigo) {
		this.tamanhoCodigo = tamanhoCodigo;
	}
	public int getTamanhoNumero() {
		return tamanhoNumero;
	}
	public void setTamanhoNumero(int tamanhoNumero) {
		this.tamanhoNumero = tamanhoNumero;
	}
	public Long getPosicaoEstoque() {
		return posicaoEstoque;
	}
	public void setPosicaoEstoque(Long posicaoEstoque) {
		this.posicaoEstoque = posicaoEstoque;
	}
	public Long getSaida() {
		return saida;
	}
	public void setSaida(Long saida) {
		this.saida = saida;
	}
	public Long getEntrada() {
		return entrada;
	}
	public void setEntrada(Long entrada) {
		this.entrada = entrada;
	}
	public Long getSaidaOutra() {
		return saidaOutra;
	}
	public void setSaidaOutra(Long saidaOutra) {
		this.saidaOutra = saidaOutra;
	}
	public Long getEntradaOutra() {
		return entradaOutra;
	}
	public void setEntradaOutra(Long entradaOutra) {
		this.entradaOutra = entradaOutra;
	}
	
	
	
}


package com.luxfacta.planetshoes.api.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.luxfacta.planetshoes.api.base.AuthenticatedController;
import com.luxfacta.planetshoes.api.constants.Constantes;
import com.luxfacta.planetshoes.api.exception.BusinessSecurityException;
import com.luxfacta.planetshoes.api.exception.NotFoundException;
import com.luxfacta.planetshoes.api.model.Artigo;
import com.luxfacta.planetshoes.api.model.ArtigoNumeracaoCor;
import com.luxfacta.planetshoes.api.model.Calculo;
import com.luxfacta.planetshoes.api.model.Familia;
import com.luxfacta.planetshoes.api.model.GradePadrao;
import com.luxfacta.planetshoes.api.model.GradeProjetada;
import com.luxfacta.planetshoes.api.model.Loja;
import com.luxfacta.planetshoes.api.model.Movimentacao;
import com.luxfacta.planetshoes.api.model.MovimentacaoSazonalidade;
import com.luxfacta.planetshoes.api.model.Sazonalidade;
import com.luxfacta.planetshoes.api.model.Semana;
import com.luxfacta.planetshoes.api.model.VendaProjetada;
import com.luxfacta.planetshoes.api.model.kunden.KundenNotaFiscalItemSemana;
import com.luxfacta.planetshoes.api.model.kunden.KundenPedidoAberto;
import com.luxfacta.planetshoes.api.model.views.MovimentacaoArtigoCor;
import com.luxfacta.planetshoes.api.repository.ArtigoNumeracaoCorRepository;
import com.luxfacta.planetshoes.api.repository.ArtigoRepository;
import com.luxfacta.planetshoes.api.repository.CalculoRepository;
import com.luxfacta.planetshoes.api.repository.FamiliaRepository;
import com.luxfacta.planetshoes.api.repository.GradePadraoRepository;
import com.luxfacta.planetshoes.api.repository.GradeProjetadaRepository;
import com.luxfacta.planetshoes.api.repository.KundenNotaFiscalItemSemanaRepository;
import com.luxfacta.planetshoes.api.repository.KundenPedidoAbertoRepository;
import com.luxfacta.planetshoes.api.repository.LojaRepository;
import com.luxfacta.planetshoes.api.repository.MovimentacaoArtigoCorRepository;
import com.luxfacta.planetshoes.api.repository.MovimentacaoRepository;
import com.luxfacta.planetshoes.api.repository.MovimentacaoSazonalidadeRepository;
import com.luxfacta.planetshoes.api.repository.SazonalidadeRepository;
import com.luxfacta.planetshoes.api.repository.SemanaRepository;
import com.luxfacta.planetshoes.api.repository.VendaProjetadaRepository;
import com.luxfacta.planetshoes.api.rest.RestCalculo;


@RestController
public class CalculoController extends AuthenticatedController {

	@Autowired
	private LojaRepository repositoryLoja;

	@Autowired
	private CalculoRepository repository;

	@Autowired
	private SazonalidadeRepository repositorySazonalidade;

	@Autowired
	private KundenNotaFiscalItemSemanaRepository repositoryKundenNotaFiscal;

	@Autowired
	private KundenPedidoAbertoRepository repositoryKundenPedidoAberto;

	@Autowired
	private FamiliaRepository repositoryFamilia;

	@Autowired
	private SemanaRepository repositorySemana;

	@Autowired
	private ArtigoRepository repositoryArtigo;

	@Autowired
	private ArtigoNumeracaoCorRepository repositoryArtigoNumeracaoCor;

	
	@Autowired
	private GradePadraoRepository repositoryGradePadrao;

	@Autowired
	private GradeProjetadaRepository repositoryGradeProjetada;

	@Autowired
	private MovimentacaoRepository repositoryMovimentacao;

	@Autowired
	private VendaProjetadaRepository repositoryVendaProjetada;

	
	@Autowired
	private MovimentacaoSazonalidadeRepository repositoryMovimentacaoSazonalidade;

	
	@Autowired
	private MovimentacaoArtigoCorRepository repositoryArtigoCorMovimentacao;

	Logger logger = LoggerFactory.getLogger(CalculoController.class);
	
	/*
	@Transactional
	@GetMapping(value = "publico/v1/calculo/processaAno/{ano}")
	public ResponseEntity<Integer> novoAno(@PathVariable int ano)  {
	
		repositorySazonalidade.deleteByAno(ano);

		repositorySazonalidade.deleteRedeByAno(ano);

		repositorySazonalidade.processaMovimentacao(ano);
		
		repositorySazonalidade.processaMovimentacaoRede(ano);
		
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	*/
	
	private List<Object[]> splitArray(Object[] array, int splitSize) {

		int numberOfArrays = array.length / splitSize;
		int remainder = array.length % splitSize;

		int start = 0;
		int end = 0;

		List<Object[]> list = new ArrayList<>();
		for (int i = 0; i < numberOfArrays; i++) {
		  end += splitSize;
		  list.add(Arrays.copyOfRange(array, start, end));
		  start = end;
		}

		if(remainder > 0) {
		  list.add(Arrays.copyOfRange(array, start, (start + remainder)));
		}
		return list;
	}

	@Transactional
	@GetMapping(value = "publico/v1/calculo/geraPedido")
	public ResponseEntity<Integer> geraPedidoLoja() throws BusinessSecurityException  {

		//Optional<Loja> optLoja = repositoryLoja.findById(Mapper.decryptId(lojId));
		/*
		long lojId = 525895L;
		Optional<Loja> optLoja = repositoryLoja.findById(lojId);
		
		
		if (optLoja.isPresent()) {
			
			geraPedido(new Loja[] { optLoja.get()}, 0);

		}
		*/
		
		// Sincroniza Loja
		repositoryLoja.sincronizaLojaKunden01();
		repositoryLoja.sincronizaLojaKunden02();
		repositoryLoja.sincronizaLojaKunden03();
		repositoryLoja.sincronizaLojaKunden04();
		
		// Sincroniza Artigos
		repositoryArtigo.sincronizaArtigoKunden01();
		repositoryArtigo.sincronizaArtigoKunden02();
		repositoryArtigo.sincronizaArtigoKunden03();
		repositoryArtigo.sincronizaArtigoKunden04();
		repositoryArtigo.sincronizaArtigoKunden05();
		repositoryArtigo.sincronizaArtigoKunden06();
		repositoryArtigo.sincronizaArtigoKunden07();
		repositoryArtigo.sincronizaArtigoKunden08();
		
		
		
		Calendar calendar = Calendar.getInstance();
		
		Optional<Semana> optSemanaAtual = repositorySemana.findByData(calendar.getTime());
		//Optional<Semana> optSemanaAtual = repositorySemana.findById(131L);
		if (optSemanaAtual.isPresent()) {
			Iterable<Calculo> itaCal = repository.findBySemIdDe(optSemanaAtual.get().getId());
			Iterator<Calculo> itCal = itaCal.iterator();
			while (itCal.hasNext()) {
				repository.delete(itCal.next());
			}
		}
		

		List<Loja> lstLoja = new ArrayList<>();
		Iterable<Loja> itLoja = repositoryLoja.findAll();
		itLoja.forEach(lstLoja::add);

		logger.info("CALCULO: Processando " + lstLoja.toArray().length + " lojas.");

		List<Object[]> lojaListThread = splitArray(lstLoja.toArray(),10);

		int i = 0;
		for (Object[] lar : lojaListThread) {
			Integer id = i++;
			Runnable runnable = new Runnable() {
	            @Override
	            public void run() {
	                geraPedido(lar, id);
	            }
	        };

	        Thread th = new Thread(runnable);
	        th.start();

		}
		
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	private void geraPedido(Object[] lojaList, Integer idThread) {
		
		logger.info("CALCULO: Thread " + idThread.intValue() + " recebeu " + lojaList.length + " lojas.");


		int numeroSemanasCalculoPassado = super.config.getProperty("planet.calculo.quantidade_semanas_passado", Integer.class);
		int numeroSemanasCalculoGrade = super.config.getProperty("planet.calculo.quantidade_semanas_grade", Integer.class);
		int numeroSemanasCalculoFuturo = super.config.getProperty("planet.calculo.quantidade_semanas_futuro", Integer.class);
		int numeroSemanasCalculoCobertura = super.config.getProperty("planet.calculo.quantidade_semanas_cobertura", Integer.class);
		String fatorPonderacaoArray = super.config.getProperty("planet.calculo.fator_ponderacao", String.class);
		
		
		Calendar calendar = Calendar.getInstance();
		
		Optional<Semana> optSemanaAtual = repositorySemana.findByData(calendar.getTime());
		//Optional<Semana> optSemanaAtual = repositorySemana.findById(129L);
		if (optSemanaAtual.isPresent()) {


			long ano = optSemanaAtual.get().getSemAno();
			
			List<Familia> lstFamilia = repositoryFamilia.findByFamGeraPedido(Constantes.SIM);
			for (Familia f : lstFamilia) {
				
				
				if (f.getFamQuantidadeSemanasAnalise() != null) {
					numeroSemanasCalculoPassado = f.getFamQuantidadeSemanasAnalise();
				}
				if (f.getFamQuantidadeSemanasPrevisao() != null) {
					numeroSemanasCalculoFuturo = f.getFamQuantidadeSemanasPrevisao();
				}
				if (f.getFamPonderacao() != null) {
					fatorPonderacaoArray = f.getFamPonderacao();
				}
				if (f.getFamQuantidadeSemanasGrade() != null) {
					numeroSemanasCalculoGrade = f.getFamQuantidadeSemanasGrade();
				}
				if (f.getFamQuantidadeSemanasCobertura() != null) {
					numeroSemanasCalculoCobertura = f.getFamQuantidadeSemanasCobertura();
				}
				
				
				List<Double> lstFatorPonderacao = new ArrayList<>();
				for (String x : fatorPonderacaoArray.split(",")) {
					lstFatorPonderacao.add(Double.parseDouble(x));
				}

				
				// Prepara semanas para analise passada
				List<Semana> lstSemanaAnalisePassado = new ArrayList<>();
				for (long x = optSemanaAtual.get().getSemNumero() - numeroSemanasCalculoPassado;  x < optSemanaAtual.get().getSemNumero(); x++) {
					
					if (x < 1) {
						
						Optional<Semana> optSemana = repositorySemana.findBySemAnoAndSemNumero(ano-1, x+52);
						if (optSemana.isPresent()) {
							lstSemanaAnalisePassado.add(optSemana.get());
						}
						
					} else {
						
						Optional<Semana> optSemana = repositorySemana.findBySemAnoAndSemNumero(ano, x);
						if (optSemana.isPresent()) {
							lstSemanaAnalisePassado.add(optSemana.get());
						}
						
					}
				}

				// Prepara semanas para analise da grade
				List<Semana> lstSemanaAnaliseGrade = new ArrayList<>();
				for (long x = optSemanaAtual.get().getSemNumero() - numeroSemanasCalculoGrade;  x < optSemanaAtual.get().getSemNumero(); x++) {
					
					if (x < 1) {
						
						Optional<Semana> optSemana = repositorySemana.findBySemAnoAndSemNumero(ano-1, x+52);
						if (optSemana.isPresent()) {
							lstSemanaAnaliseGrade.add(optSemana.get());
						}
						
					} else {
						
						Optional<Semana> optSemana = repositorySemana.findBySemAnoAndSemNumero(ano, x);
						if (optSemana.isPresent()) {
							lstSemanaAnaliseGrade.add(optSemana.get());
						}
						
					}
				}

				
				// Prepara semanas para calculo futuro
				List<Semana> lstSemanaAnaliseFuturo = new ArrayList<>();
				for (long x = optSemanaAtual.get().getSemNumero(); x <= optSemanaAtual.get().getSemNumero() + numeroSemanasCalculoFuturo - 1; x++) {
					
					if (x > 52) {
						
						Optional<Semana> optSemana = repositorySemana.findBySemAnoAndSemNumero(ano+1, x-52);
						if (optSemana.isPresent()) {
							lstSemanaAnaliseFuturo.add(optSemana.get());
						}
						
					} else {
						
						Optional<Semana> optSemana = repositorySemana.findBySemAnoAndSemNumero(ano, x);
						if (optSemana.isPresent()) {
							lstSemanaAnaliseFuturo.add(optSemana.get());
						}
						
					}
				}

				// Prepara semanas para calculo cobertura
				List<Semana> lstSemanaAnaliseCobertura = new ArrayList<>();
				for (long x = optSemanaAtual.get().getSemNumero() + numeroSemanasCalculoFuturo; x <= optSemanaAtual.get().getSemNumero() + numeroSemanasCalculoFuturo + numeroSemanasCalculoCobertura - 1; x++) {
					
					if (x > 52) {
						
						Optional<Semana> optSemana = repositorySemana.findBySemAnoAndSemNumero(ano+1, x-52);
						if (optSemana.isPresent()) {
							lstSemanaAnaliseCobertura.add(optSemana.get());
						}
						
					} else {
						
						Optional<Semana> optSemana = repositorySemana.findBySemAnoAndSemNumero(ano, x);
						if (optSemana.isPresent()) {
							lstSemanaAnaliseCobertura.add(optSemana.get());
						}
						
					}
				}
				
				
				
				for (Object lj : lojaList) {
					Long lojId = ((Loja)lj).getId();
					
					if ( Constantes.LOJA_BLOQUEADA.equals(((Loja)lj).getLojStatus())  ||   Constantes.LOJA_BLOQUEADA_ABASTECIMENTO.equals(((Loja)lj).getLojStatus()) ) {
						logger.info("CALCULO: Thread " + idThread.intValue() + " Loja bloqueada: " + lojId);
						continue;
					}

					logger.info("CALCULO: Thread " + idThread.intValue() + " processando loja " + lojId);
					
					
					// Prepara sazonalidade para calculo da cobertura
					List<Sazonalidade> lstSazonalidadeCobertura = new ArrayList<>();
					for (Semana s : lstSemanaAnaliseCobertura) {
						
						Optional<Sazonalidade> optSazonalidade = repositorySazonalidade.findByLojIdAndSemId(lojId, s.getId());
						if (optSazonalidade.isPresent()) {
							lstSazonalidadeCobertura.add(optSazonalidade.get());
						}
							
					}
	
					
					Calculo cal = new Calculo();
					cal.setCalData(new Date());
					cal.setLojId(lojId);
					cal.setSemIdDe(optSemanaAtual.get().getId());
					cal.setSemIdAte(lstSemanaAnaliseFuturo.get(lstSemanaAnaliseFuturo.size()-1).getId());
					repository.save(cal);
	
					
					List<Artigo> lstArtigo = repositoryArtigo.findByFamId(f.getId());
					/*
					List<Artigo> lstArtigo = new ArrayList<>();
					Optional<Artigo> optArt = repositoryArtigo.findById(9770L);
					if (optArt.isPresent()) lstArtigo.add(optArt.get());
					*/
					
					for (Artigo a : lstArtigo) {
						
						
						Map<Long, Map<Integer,Double>> vendasCorNumeracao = new HashMap<>();
						Set<Long> cores = new HashSet<>() ;
	
						// Calcula total vendido ponderado e dessazonalizado
						List<MovimentacaoSazonalidade> lstMovSazo = new ArrayList<>();
						int fatorPonderacaoCount = 0;
						for (Semana s : lstSemanaAnalisePassado) {
							List<MovimentacaoArtigoCor> lstArtigoCor =  repositoryArtigoCorMovimentacao.findByArtIdAndSemIdAndLojId(a.getId(), s.getId(), lojId);

							for (MovimentacaoArtigoCor mac : lstArtigoCor) {
								
								MovimentacaoSazonalidade ms = new MovimentacaoSazonalidade();
								ms.setCalId(cal.getId());
								ms.setArtId(a.getId());
								ms.setCorId(mac.getCorId());
								ms.setSemId(s.getId());
								
								ms.setMsaFatorSazonalidade(mac.getMctFatorSazonalidade());
								ms.setMsaFatorPonderacao(lstFatorPonderacao.get(fatorPonderacaoCount));
								ms.setMsaQuantidade(mac.getMctQuantidade());
								ms.setMsaQuantidadeSazonalizada(mac.getMctQuantidadeSazonalizada());
								ms.setMsaEstoque(mac.getMctEstoque());
								ms.setMsaEntrada(mac.getMctEntrada());
								
								lstMovSazo.add(ms);
								
								cores.add(mac.getCorId());
								
							}
							
							fatorPonderacaoCount++;
						}
						
						repositoryMovimentacaoSazonalidade.saveAll(lstMovSazo);
						
	
						// Calcula vendas projetadas sazonalizadas
						// Calcula vendas da ultima semana
						
						Map<Long,Double> quantidadeVendasUltimaSemanaCor = new HashMap<>();
						List<VendaProjetada> lstVendaProjetada = new ArrayList<>();
						
						for (Long corId : cores) {
							
							double quantidadePonderada = 0d;
							double fatorPonderacao = 0d;
							for (MovimentacaoSazonalidade ms : lstMovSazo) {
								if (ms.getCorId().equals(corId)) {
									quantidadePonderada += (ms.getMsaFatorPonderacao() * ms.getMsaQuantidadeSazonalizada());
									fatorPonderacao += ms.getMsaFatorPonderacao();
								}
							}
							
							if (fatorPonderacao > 0)
								quantidadePonderada = quantidadePonderada / fatorPonderacao;
							
							for (Semana s : lstSemanaAnaliseFuturo) {
								Optional<Sazonalidade> optSazo = repositorySazonalidade.findByLojIdAndSemId(lojId, s.getId());
								if (optSazo.isPresent()) {
									VendaProjetada vp = new VendaProjetada();
									vp.setArtId(a.getId());
									vp.setCalId(cal.getId());
									vp.setCorId(corId);
									vp.setSemId(s.getId());
									
									vp.setVepQuantidade(quantidadePonderada * optSazo.get().getSazPorcentagemAjuste());
									vp.setVepFatorSazonalidade(optSazo.get().getSazPorcentagemAjuste());
									lstVendaProjetada.add(vp);
	
									
									quantidadeVendasUltimaSemanaCor.put(vp.getCorId(), quantidadePonderada );
								}
							}
						}
						
						repositoryVendaProjetada.saveAll(lstVendaProjetada);
						
						
	
						// Define todos as numeracoes/cores fabricadas para o artigo e soma suas vendas
						Long[] semIdAr = new Long[lstSemanaAnaliseGrade.size()];
						for (int i = 0; i < lstSemanaAnaliseGrade.size(); i++) {
							semIdAr[i] = lstSemanaAnaliseGrade.get(i).getId();
						}
						List<Movimentacao> lstMovimentacao =  repositoryMovimentacao.findByLojIdAndArtIdAndSemIdIn(lojId, a.getId(), semIdAr);
						
						
						List<ArtigoNumeracaoCor> lstArtNumCor = repositoryArtigoNumeracaoCor.findByArtId(a.getId());
						for (ArtigoNumeracaoCor anc : lstArtNumCor) {
							Map<Integer,Double> vendaCor = vendasCorNumeracao.get(anc.getCorId());
							if (vendaCor == null) {
								vendaCor = new HashMap<>();
								vendaCor.put(anc.getAncNumeracao(), 0d);
								vendasCorNumeracao.put(anc.getCorId(), vendaCor);
							} 

							if (vendaCor.get(anc.getAncNumeracao()) == null) {
								vendaCor.put(anc.getAncNumeracao(), 0d);
							}
							
	
							for (Movimentacao m : lstMovimentacao) {
								if (m.getAncId().equals(anc.getId()) && m.getSaida()>0) {
									double qtde = vendaCor.get(anc.getAncNumeracao());
									vendaCor.put(anc.getAncNumeracao(), qtde+m.getSaida());
								}
							}
						}
	
						List<GradePadrao> lstGradePadrao = new ArrayList<>();
						for (Map.Entry<Long, Map<Integer,Double>> entryCor : vendasCorNumeracao.entrySet()) {
	
							double qtdeTotalNumeracao = 0d;
							for (Map.Entry<Integer,Double> entryNumeracao : vendasCorNumeracao.get(entryCor.getKey()).entrySet()) {
								qtdeTotalNumeracao += vendasCorNumeracao.get(entryCor.getKey()).get(entryNumeracao.getKey());
							}
	
	
							if (qtdeTotalNumeracao > 0) {
								for (Map.Entry<Integer,Double> entryNumeracao : vendasCorNumeracao.get(entryCor.getKey()).entrySet()) {
									GradePadrao gp = new GradePadrao();
									gp.setCalId(cal.getId());
									gp.setCorId(entryCor.getKey());
									gp.setArtId(a.getId());
									gp.setGrpNumeracao(entryNumeracao.getKey());
									gp.setGrpQuantidade(Math.round(vendasCorNumeracao.get(entryCor.getKey()).get(entryNumeracao.getKey())));
									gp.setGrpPercentual( (vendasCorNumeracao.get(entryCor.getKey()).get(entryNumeracao.getKey())) / (qtdeTotalNumeracao) );
									lstGradePadrao.add(gp);
								}						
							}
						}					
						
						repositoryGradePadrao.saveAll(lstGradePadrao);
						
						long ultimaSemanaId = lstSemanaAnalisePassado.get(lstSemanaAnalisePassado.size()-1).getId();
						
						
						List<Movimentacao> lstMovimentacaoUltima =  repositoryMovimentacao.findByLojIdAndArtIdAndSemIdIn(lojId, a.getId(), new Long[] {ultimaSemanaId});
						List<GradeProjetada> lstGradeProjetada = new ArrayList<>();

						Long[] semIdAnalisePassadaList = new Long[lstSemanaAnalisePassado.size()];
						for (int i = 0; i < lstSemanaAnalisePassado.size(); i++) {
							semIdAnalisePassadaList[i] = lstSemanaAnalisePassado.get(i).getId();
						}
						
						for (ArtigoNumeracaoCor anc : lstArtNumCor) {
								
							Movimentacao mov = null;
							for (Movimentacao m : lstMovimentacaoUltima) {
								if (m.getAncId().equals(anc.getId())) {
									mov = m;
									break;
								}
							}
	
							for (GradePadrao gp : lstGradePadrao) {
								
								//if (gp.getGrpPercentual() == 0d)
								//	continue;
							
								if (gp.getGrpNumeracao().equals(anc.getAncNumeracao()) && gp.getCorId().equals(anc.getCorId()) ) {
									

									long qtdeEstoqueTransito = 0;
									//for (Semana s : lstSemanaAnalisePassado) {
									List<KundenNotaFiscalItemSemana> lstNf = repositoryKundenNotaFiscal.findByLojIdAndSemIdInAndAncId(lojId, semIdAnalisePassadaList, anc.getId());
									for (KundenNotaFiscalItemSemana nf : lstNf) {
										qtdeEstoqueTransito += nf.getNfkQuantidade();
									}
									//}

									long saldoPedidoFabrica = 0;
									List<KundenPedidoAberto> lstPedido = repositoryKundenPedidoAberto.findByLojIdAndAncId(lojId, anc.getId());
									for (KundenPedidoAberto ped : lstPedido) {
										saldoPedidoFabrica += ped.getPedkSaldo();
									}
									
									GradeProjetada gpj = new GradeProjetada();
									gpj.setCalId(cal.getId());
									gpj.setAncId(anc.getId());
									gpj.setGprDistribuicaoGrade(gp.getGrpPercentual());
									gpj.setGprPedidoTransito(qtdeEstoqueTransito);
									gpj.setGprPedidoFabrica(saldoPedidoFabrica);
									
									
									// Vendas 
									double quantidadeVendasUltimaSemana = quantidadeVendasUltimaSemanaCor.getOrDefault(anc.getCorId(), 0d);
									
									double quantidadeVendaPrevista = 0d;
									for (VendaProjetada vp : lstVendaProjetada) {
										if (vp.getCorId().equals(anc.getCorId())) {
											quantidadeVendaPrevista += vp.getVepQuantidade() * gp.getGrpPercentual();
										}
									}
									
									double totalVendaAposEntrega = 0;
									for (Sazonalidade sazo : lstSazonalidadeCobertura) {
										totalVendaAposEntrega += quantidadeVendasUltimaSemana * gp.getGrpPercentual() * sazo.getSazPorcentagemAjuste();
									}
									gpj.setGprVendaAposEntrega((long) Math.ceil(totalVendaAposEntrega));
	
									// Estoque antes entrega
									if (mov != null) {
										gpj.setGprEstoqueAtual(mov.getEstoqueInformado());
										gpj.setGprEstoqueAntesEntrega(mov.getEstoqueInformado() - (long)Math.ceil(quantidadeVendaPrevista) + qtdeEstoqueTransito  );
									} else {
										gpj.setGprEstoqueAtual(0L);
										gpj.setGprEstoqueAntesEntrega(0 - (long)Math.ceil(quantidadeVendaPrevista) + qtdeEstoqueTransito);
									}
									
									long estoqueConsideradoCalculo;
									if (gpj.getGprEstoqueAntesEntrega() > 0) { // Somente considera estoques positivos
										estoqueConsideradoCalculo = gpj.getGprEstoqueAntesEntrega() + saldoPedidoFabrica;  
										gpj.setGprVendaAntesEntrega((long)Math.ceil(quantidadeVendaPrevista));
									} else {
										estoqueConsideradoCalculo = saldoPedidoFabrica;  
										gpj.setGprVendaAntesEntrega(0L);
									}
	
	
									// Estoque apos entrega / Pedido fabrica
									if (estoqueConsideradoCalculo > gpj.getGprVendaAposEntrega()) {
										gpj.setGprPedidoNovo((long)0);
									} else {
										gpj.setGprPedidoNovo(gpj.getGprVendaAposEntrega() - estoqueConsideradoCalculo);
									}
	
									gpj.setGprEstoqueAposEntrega(estoqueConsideradoCalculo + gpj.getGprPedidoNovo() - gpj.getGprVendaAposEntrega());
									
									lstGradeProjetada.add(gpj);
									
								}
							}
						}
						
						repositoryGradeProjetada.saveAll(lstGradeProjetada);
						
						
					} // for artigo
					
					logger.info("CALCULO: Thread " + idThread.intValue() + " finalizou loja " + lojId);
				} // loja
			}
		}			
	}
		
	/*
		
	private void geraPedidoAnaliseEstoque(Object[] lojaList, Integer idThread) {
		
		logger.info("CALCULO: Thread " + idThread.intValue() + " recebeu " + lojaList.length + " lojas.");


		int numeroSemanasCalculoPassado = super.config.getProperty("planet.calculo.quantidade_semanas_passado", Integer.class);
		int numeroSemanasCalculoEstoque = super.config.getProperty("planet.calculo.quantidade_semanas_grade", Integer.class);
		
		
		Calendar calendar = Calendar.getInstance();
		
		Optional<Semana> optSemanaAtual = repositorySemana.findByData(calendar.getTime());
		//Optional<Semana> optSemanaAtual = repositorySemana.findById(129L);
		if (optSemanaAtual.isPresent()) {


			long ano = optSemanaAtual.get().getSemAno();
			
			List<Familia> lstFamilia = repositoryFamilia.findByFamGeraPedido(Constantes.SIM);
			for (Familia f : lstFamilia) {
				
				
				// Prepara semanas para analise passada
				List<Long> lstSemanaAnalisePassado = new ArrayList<>();
				for (long x = optSemanaAtual.get().getSemNumero() - numeroSemanasCalculoPassado;  x < optSemanaAtual.get().getSemNumero(); x++) {
					
					if (x < 1) {
						
						Optional<Semana> optSemana = repositorySemana.findBySemAnoAndSemNumero(ano-1, x+52);
						if (optSemana.isPresent()) {
							lstSemanaAnalisePassado.add(optSemana.get().getId());
						}
						
					} else {
						
						Optional<Semana> optSemana = repositorySemana.findBySemAnoAndSemNumero(ano, x);
						if (optSemana.isPresent()) {
							lstSemanaAnalisePassado.add(optSemana.get().getId());
						}
						
					}
				}

				// Prepara semanas para analise estoque
				List<Long> lstSemanaAnaliseEstoque = new ArrayList<>();
				for (long x = optSemanaAtual.get().getSemNumero() - numeroSemanasCalculoEstoque;  x < optSemanaAtual.get().getSemNumero(); x++) {
					
					if (x < 1) {
						
						Optional<Semana> optSemana = repositorySemana.findBySemAnoAndSemNumero(ano-1, x+52);
						if (optSemana.isPresent()) {
							lstSemanaAnaliseEstoque.add(optSemana.get().getId());
						}
						
					} else {
						
						Optional<Semana> optSemana = repositorySemana.findBySemAnoAndSemNumero(ano, x);
						if (optSemana.isPresent()) {
							lstSemanaAnaliseEstoque.add(optSemana.get().getId());
						}
						
					}
				}

				lstSemanaAnaliseEstoque.remove(lstSemanaAnaliseEstoque.size()-1);
				
				
				
				for (Object lj : lojaList) {

					if ( Constantes.LOJA_BLOQUEADA.equals(((Loja)lj).getLojStatus())  ||   Constantes.LOJA_BLOQUEADA_ABASTECIMENTO.equals(((Loja)lj).getLojStatus()) )
						continue;
					
					Long lojId = ((Loja)lj).getId();
					logger.info("CALCULO: Thread " + idThread.intValue() + " processando loja " + lojId);
					
					List<Artigo> lstArtigo = repositoryArtigo.findByFamId(f.getId());
					
					for (Artigo a : lstArtigo) {
						
						
						Map<Long, Long> outrasSaidasCor = new HashMap<>();
						Map<Long, Long> estoqueCor = new HashMap<>();
						Set<Long> cores = new HashSet<>() ;
	
						// Calcula total outras saidas
						List<Movimentacao> lstArtigoCor =  repositoryMovimentacao.findByLojIdAndArtIdAndSemIdIn(lojId, a.getId(), (Long[])lstSemanaAnalisePassado.toArray());
						for (Movimentacao mac : lstArtigoCor) {

							Long corId = mac.getArtigoNumeracaoCor().getCorId();
							
							Long qtde = outrasSaidasCor.get(corId);
							if (qtde == null) {
								qtde = 0L;
							}
							
							outrasSaidasCor.put(corId, qtde + mac.getSaidaOutra());
							cores.add(corId);
						}
						

						
						// Calcula estoque
						List<Movimentacao> lstArtigoCorEstoque =  repositoryMovimentacao.findByLojIdAndArtIdAndSemIdIn(lojId, a.getId(), (Long[])lstSemanaAnaliseEstoque.toArray());
						for (Movimentacao mac : lstArtigoCorEstoque) {
							Long corId = mac.getArtigoNumeracaoCor().getCorId();

							Long estoque = estoqueCor.get(corId);
							if (estoque == null) {
								estoque = 0L;
							}
							
							if (mac.getEstoqueInformado() > estoque)
								estoqueCor.put(corId, mac.getEstoqueInformado());
						}

						
	
						
						
					} // for artigo
					
					logger.info("CALCULO: Thread " + idThread.intValue() + " finalizou loja " + lojId);
				} // loja
			}
		}			
	}
	*/
	

	
	
    
	@GetMapping(value = "admin/v1/calculo/recupera/{id}")
	public ResponseEntity<RestCalculo> recupera(@PathVariable String id) throws NotFoundException, BusinessSecurityException {
        Optional<Calculo> dbo = repository.findById(Mapper.decryptId(id));
        if (dbo.isPresent()) {
            
            RestCalculo rest = (RestCalculo) mapper.copyToRestObject(dbo.get(), RestCalculo.class);
            
            return new ResponseEntity<>(rest, HttpStatus.OK);
        }
		throw new NotFoundException();
	}

	@GetMapping(value = "admin/v1/calculo/lista/{pagina}")
	public ResponseEntity<List<RestCalculo>> lista(@PathVariable int pagina) throws BusinessSecurityException {
        mapper.setMaxLevel(2);
        Pageable pageable = PageRequest.of(pagina, 25);
        
        Iterable<Calculo> itDbo = repository.findAll(pageable);
        List<RestCalculo> restList =  (List<RestCalculo>) mapper.copyToRestObject(itDbo, RestCalculo.class);
        
        return new ResponseEntity<>(restList, HttpStatus.OK);
	}
	
}

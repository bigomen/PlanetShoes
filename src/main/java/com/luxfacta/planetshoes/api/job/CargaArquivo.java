package com.luxfacta.planetshoes.api.job;

import com.luxfacta.planetshoes.api.constants.Constantes;
import com.luxfacta.planetshoes.api.model.*;
import com.luxfacta.planetshoes.api.repository.*;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.enums.CSVReaderNullFieldIndicator;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContextType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Optional;

@Component
public class CargaArquivo {

	private Short SIM = (short) 1;
	private Short NAO = (short) 0;
	
	@Autowired
	private FamiliaRepository familiaRepo;
	
	@Autowired
	private CorRepository corRepo;
	
	@Autowired
	private ArtigoRepository artigoRepo;

	@Autowired
	private TipoArtigoRepository tipoArtigoRepo;

	@Autowired
	private ArtigoNumeracaoCorRepository artigoNumeracaoCorRepo;

	@Autowired
	private LojaRepository lojaRepo;


	@Autowired
	private SemanaRepository semanaRepo;

	@Autowired
	private MovimentacaoRepository movimentacaoRepo;

	
    @PersistenceContext(type = PersistenceContextType.TRANSACTION)
    private EntityManager entityManager;

   
	
	//@PostConstruct
    @Scheduled(cron = "*/5 * * * * *")
	@Transactional(propagation = Propagation.NEVER)
	public void processaMovimentacao() throws FileNotFoundException, IOException {
		
		Logger logger = LoggerFactory.getLogger(CargaArquivo.class);
		
		FileReader fr = null;

		try {
			fr = new FileReader("/extra/rcerqueira/movimentacao_produtos_loja.csv");
			//fr = new FileReader("/home/rcerqueira/Downloads/Movimentacao-2022.csv");
			Iterator<ArquivoMovimentacao> it = new CsvToBeanBuilder<ArquivoMovimentacao>(fr)
					.withFieldAsNull(CSVReaderNullFieldIndicator.BOTH)
					.withIgnoreEmptyLine(true)
					.withSkipLines(1) // SOmente se quiser processar a partir da linha que deu erro
					.withType(ArquivoMovimentacao.class).build().iterator();

			logger.debug("Iniciando processamento do arquivo");

			int i =0;
			while (it.hasNext()) {
				
				
				i++;
				logger.debug("Linha " + i);

				
				ArquivoMovimentacao arq = it.next();
				
				Familia fam = null;
				Optional<Familia> optFam = familiaRepo.findByFamMarcaIgnoreCase(arq.getMarca());
				if (optFam.isEmpty()) {
					fam = new Familia();
					fam.setFamMarca(arq.getMarca());
					fam.setFamGeraPedido(Constantes.NAO);
					familiaRepo.save(fam);
				} else {
					fam = optFam.get();
				}
				
				Cor cor = null;
				Optional<Cor> optCor = corRepo.findByCorDescricaoIgnoreCase(arq.getCorNome());
				if (optCor.isEmpty()) {
					cor = new Cor();
					cor.setCorDescricao(arq.getCorNome());
					corRepo.save(cor);
				} else {
					cor = optCor.get();
				}

				TipoArtigo tipoArt = null;
				Optional<TipoArtigo> optTipoArt = tipoArtigoRepo.findByTarDescricaoIgnoreCase(arq.getGenero());
				if (optTipoArt.isEmpty()) {
					tipoArt = new TipoArtigo();
					tipoArt.setTarDescricao(arq.getGenero());
					tipoArtigoRepo.save(tipoArt);
				} else {
					tipoArt = optTipoArt.get();
				}

				
				
				Loja loj = null;
				Optional<Loja> optLoj = lojaRepo.findByLojCnpj(limpa(arq.getLojaCNPJ()));
				if (optLoj.isEmpty()) {
					loj = new Loja();
					loj.setLojCnpj(limpa(arq.getLojaCNPJ()));
					loj.setLojNome(limpa(arq.getLojaCNPJ()));
					loj.setFrqId(-1L);
					loj.setLojStatus(Constantes.LOJA_ATIVA);
					lojaRepo.save(loj);
				} else {
					loj = optLoj.get();
				}


				
				ArtigoNumeracaoCor artNumCor = null;
				Optional<ArtigoNumeracaoCor> optArtNumCor = artigoNumeracaoCorRepo.findByAncEan(arq.getEAN());
				if (optArtNumCor.isPresent()) {
					artNumCor = optArtNumCor.get();
				} else {

					Artigo art = null;
					Optional<Artigo> optArt = artigoRepo.findByArtDescricaoIgnoreCase(arq.getModeloNome());
					if (optArt.isEmpty()) {
						art = new Artigo();
						art.setArtDescricao(arq.getModeloNome());
						art.setArtFabricado(SIM);
						art.setFamId(fam.getId());
						artigoRepo.save(art);
					} else {
						art = optArt.get();
					}

					Optional<ArtigoNumeracaoCor> optArtNumCor2 = artigoNumeracaoCorRepo.findByArtigoNumeracaoCor(art.getId(), cor.getId(), arq.getTamanhoNumero());
					if (optArtNumCor2.isEmpty()) {
						artNumCor = new ArtigoNumeracaoCor();
						artNumCor.setAncEan(arq.getEAN());
						artNumCor.setAncSku(arq.getSKU());
						artNumCor.setAncNumeracao(arq.getTamanhoNumero());
						artNumCor.setArtId(art.getId());
						artNumCor.setCorId(cor.getId());
						artNumCor.setTarId(tipoArt.getId());
											
						artigoNumeracaoCorRepo.save(artNumCor);
					} else {
						artNumCor = optArtNumCor2.get();
					}

				}

				Optional<Semana> optSem = null;
				if (arq.getSemana() < 52)
					optSem = semanaRepo.findBySemAnoAndSemNumero( Long.parseLong(arq.getDataFim().substring(0,4)), arq.getSemana());
				else
					optSem = semanaRepo.findBySemAnoAndSemNumero( Long.parseLong(arq.getDataInicio().substring(0,4)), arq.getSemana());
					

				if (optSem.isPresent()) {

					long estoqueCalculado = 0;
					if (arq.getSemana() > 1) {
						Optional<Semana> optSemAnterior = semanaRepo.findBySemAnoAndSemNumero(optSem.get().getSemAno(), arq.getSemana()-1);
						if (optSemAnterior.isPresent()) {
							Optional<Movimentacao> optMovEstoque = movimentacaoRepo.findByLojIdAndSemIdAndAncId(loj.getId(), optSemAnterior.get().getId(), artNumCor.getId());
							if (optMovEstoque.isPresent()) {
								estoqueCalculado = optMovEstoque.get().getEstoqueCalculado() - arq.getSaida() - arq.getSaidaOutra() + arq.getEntrada() + arq.getEntradaOutra(); 
							}
						}
					}
					
					
					Optional<Movimentacao> optMovCheck = movimentacaoRepo.findByLojIdAndSemIdAndAncId(loj.getId(), optSem.get().getId(), artNumCor.getId());
					Movimentacao mov = null;
					
					
					if (optMovCheck.isPresent()) {
						mov = optMovCheck.get();
						mov.setEntrada(arq.getEntrada() + mov.getEntrada());
						mov.setSaida(arq.getSaida() + mov.getSaida());
						mov.setEntradaOutra(arq.getEntradaOutra() + mov.getEntradaOutra());
						mov.setSaidaOutra(arq.getSaidaOutra() + mov.getSaidaOutra());
						mov.setEstoqueInformado(arq.getPosicaoEstoque() + mov.getEstoqueInformado());
						mov.setEstoqueCalculado(estoqueCalculado + mov.getEstoqueCalculado());
						
					} else {
						mov = new Movimentacao();
						mov.setEntrada(arq.getEntrada());
						mov.setSaida(arq.getSaida());
						mov.setEntradaOutra(arq.getEntradaOutra());
						mov.setSaidaOutra(arq.getSaidaOutra());
						mov.setEstoqueInformado(arq.getPosicaoEstoque());
						mov.setEstoqueCalculado(estoqueCalculado);
						mov.setAncId(artNumCor.getId());
						mov.setLojId(loj.getId());
						mov.setSemId(optSem.get().getId());
						mov.setEntroqueReinicia(NAO);
						
					}
					
					movimentacaoRepo.save(mov);

					if (i % 1000 == 0) {
						//entityManager.flush();
						entityManager.clear();
						System.gc();
					}
					
				} else {
					throw new Exception("Semana nao encontrada");
				}
			}
			
		  } catch (Exception e) {
			  
			  if (fr != null) {
				  logger.error(e.getMessage(), e);
				  fr.close();
			  }
			  
		  } finally {
			  try {
				  new File("/extra/rcerqueira/movimentacao_produtos_loja.csv").renameTo(new File("/extra/rcerqueira/_movimentacao_produtos_loja.csv"));
			  } catch (Exception e2) {}
		  }
		
		
		
		
	}
	
	
	private String limpa(String campo) {
		if (campo == null)
			return null;
		
		return campo.replaceAll("[^0-9]", "").trim();
	}
	
}

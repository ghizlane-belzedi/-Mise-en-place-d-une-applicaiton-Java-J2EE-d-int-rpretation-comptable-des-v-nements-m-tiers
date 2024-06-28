package ma.inetum.brique.services.impl;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ma.inetum.brique.model.coda.LinkHead;
import ma.inetum.brique.model.coda.LinkLine;
import ma.inetum.brique.model.coda.LinkQuantity;
import ma.inetum.brique.model.principale.EcritureComptable;
import ma.inetum.brique.model.principale.PieceComptable;
import ma.inetum.brique.repository.coda.LinkHeadRepository;
import ma.inetum.brique.repository.coda.LinkLineRepository;
import ma.inetum.brique.repository.coda.LinkQuantityRepository;
import ma.inetum.brique.repository.principale.PieceComptableRepository;
import ma.inetum.brique.services.CodaService;
import ma.inetum.brique.utilities.Constantes;
@Service
public class CodaServiceImpl implements CodaService{
	private static final Logger log = LoggerFactory.getLogger(CodaServiceImpl.class);
	@Autowired
	PieceComptableRepository pieceComptableRepository;
	@Autowired
	LinkHeadRepository headRepository;
	@Autowired
	LinkLineRepository lineRepository;
	@Autowired 
	LinkQuantityRepository quantityRepository;
	
	@Transactional(value = "chainedTransactionManager")
	public PieceComptable saveInCoda(PieceComptable piece) throws RuntimeException {
		try {
			LinkHead head = new LinkHead();
			head.setLinkcode(piece.getSimulation().getFlux().getCode()!=null?
					piece.getSimulation().getFlux().getCode().substring(0, Math.min(12, piece.getSimulation().getFlux().getCode().length())):null);
			head.setCmpcode(Constantes.COMPANY_CODE);
			head.setDoccode(piece.getCodeJournale());
			head.setDocnum(Long.valueOf(piece.getNumeroPiece()));
			head.setPosted(Constantes.CODE_LINK_POSTED);
			head.setYr(Integer.valueOf(piece.getDate().format(DateTimeFormatter.ofPattern("yyyy"))));
			head.setPeriod(Integer.valueOf(piece.getDate().format(DateTimeFormatter.ofPattern("MM"))));
			head.setCurdoc(null);
			head.setDocdate(piece.getDate());
			head.setAuthuser(null);
			head.setDescr(piece.getEnteteDoc()!=null?piece.getEnteteDoc().substring(0, Math.min(36, piece.getEnteteDoc().length())):null);
			head.setOrigcmpcode(null);
			head.setOrigdoccode(null);
			head.setOrigdocnum(null);
			int i = 1;
			List<LinkLine> lineList = new ArrayList<>();
			for(EcritureComptable ecriture : piece.getEcritures()) {
				
				LinkLine line = new LinkLine();
				line.setLinkcode(piece.getSimulation().getFlux().getCode()!=null?
						piece.getSimulation().getFlux().getCode().substring(0, Math.min(12, piece.getSimulation().getFlux().getCode().length())):null);
				line.setCmpcode(Constantes.COMPANY_CODE);
				line.setDoccode(piece.getCodeJournale());
				line.setDocnum(head.getDocnum());
				line.setDoclinenum(i++);
				String accode = ecriture.getNcpt() + (ecriture.getCostCenter()!= null ? "."+ecriture.getCostCenter():"");
				line.setAccode(accode);
				line.setValuedoc(ecriture.getMontantMAD());
				line.setValuedoc_dp(2);
				line.setDocrate(null);
				line.setValuehome(null);
				line.setValuehome_dp(2);
				if(ecriture.getNcpt().contains(".F")) {
					line.setElcurr2(ecriture.getCurrencyCode());
					line.setElvalue2(ecriture.getMontant());
					line.setElvalue2_dp(2);
				}
				line.setDebitcredit('C' == ecriture.getSens() ? 160 : 161);
				line.setDescr(ecriture.getAccountDescription()!=null?ecriture.getAccountDescription().substring(0, Math.min(34, ecriture.getAccountDescription().length())):null);
				line.setExtref1(ecriture.getRef1() != null ? ecriture.getRef1().substring(0, Math.min(32, ecriture.getRef1().length())) : "");
				line.setExtref2(ecriture.getRef2() != null ? ecriture.getRef2().substring(0, Math.min(32, ecriture.getRef2().length())) : "");
				line.setExtref3(ecriture.getRef3() != null ? ecriture.getRef3().substring(0, Math.min(32, ecriture.getRef3().length())) : "");
				line.setExtref4(ecriture.getRef4() != null ? ecriture.getRef4().substring(0, Math.min(32, ecriture.getRef4().length())) : "");
				line.setExtref5(ecriture.getRef5() != null ? ecriture.getRef5().substring(0, Math.min(32, ecriture.getRef5().length())) : "");
				line.setExtref6(ecriture.getRef6() != null ? ecriture.getRef6().substring(0, Math.min(32, ecriture.getRef6().length())) : "");
//				boolean saveQte = false;
				
				if(Constantes.COMPTE_CODA_QUANTITE.contains(ecriture.getNcpt())) {
					LinkQuantity quantity = new LinkQuantity();
					quantity.setCmpcode(line.getCmpcode());
					quantity.setDoccode(line.getDoccode());
					quantity.setDocnum(line.getDocnum());
					quantity.setDoclinenum(line.getDoclinenum());
					quantity.setElmlevel(1);
					quantity.setQtyposition(1);
					quantity.setQtyvalue_dp(0);
					quantity.setQtyvalue(1d);
//					saveQte = true;
					quantityRepository.save(quantity);
				}
				lineList.add(line);
			}
			headRepository.save(head);
			lineRepository.saveAll(lineList);
			return piece;
		} catch (Exception e) {
			log.info(e.getMessage());
			throw new RuntimeException("Un probléme est survenu lors de l'envoi des piéces comptables dans CODA, merci de voir avec votre équipe technique: " + e.getMessage());
		}
	}
}

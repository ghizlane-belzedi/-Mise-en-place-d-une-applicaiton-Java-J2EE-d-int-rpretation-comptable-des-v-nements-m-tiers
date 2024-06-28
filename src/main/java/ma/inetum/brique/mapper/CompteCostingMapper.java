package ma.inetum.brique.mapper;

import ma.inetum.brique.bean.CompteView;
import ma.inetum.brique.model.principale.ParamCompteCosting;

public class CompteCostingMapper {

	private static CompteCostingMapper mapper;

	private CompteCostingMapper() {
	}

	public static CompteCostingMapper getInstance() {

		// create object if it's not already created
		if (mapper == null) {
			mapper = new CompteCostingMapper();
		}

		// returns the singleton object
		return mapper;
	}

	public CompteView entityTobean(ParamCompteCosting entity) {
		CompteView compte = new CompteView();
		compte.setCode(entity.getCode());
		compte.setCostCenter(entity.getCostCenter());
		compte.setCodeAnalyse(entity.getCodeAnalyse());
		compte.setDescription(entity.getDescription());
		compte.setFlux(entity.getFlux().getNom());
		compte.setFournisseur(entity.getCodeFournisseur());
		compte.setId(entity.getId());
		compte.setTvaAccount(entity.getCompteTVA());
		compte.setTvaDescription(entity.getTvaAccountDescription());
		compte.setCompteDebiteur(entity.getCompteDebiteur());
		compte.setCompteDebiteurDescription(entity.getCompteDebiteurDescription());
		compte.setCompteCrediteur(entity.getCompteCrediteur());
		compte.setCompteCrediteurDescription(entity.getCompteCrediteurDescription());
		return compte;
	}
	
//	public PieceComptableDetailsView entityTobeanDetails(PieceComptable p) {
//		PieceComptableDetailsView pieceComptable = new PieceComptableDetailsView();
//		pieceComptable.setCodeJournale(p.getCodeJournale());
//		pieceComptable.setDate(p.getDate().format(Constantes.FORMAT_DATE));
//		pieceComptable.setDescription(p.getEnteteDoc());
//		pieceComptable.setNumeroPiece(p.getNumerpPiece());
//		pieceComptable.setFlux(p.getFlux().getNom());
//		pieceComptable.setId(p.getId());
//		pieceComptable.setStatut(p.getSent());
//		pieceComptable.setEcritures(new ArrayList<>());
//		for(EcritureComptable e : p.getEcritures()) {
//			EcritureComptableView ecriture = new EcritureComptableView();
//			ecriture.setAccountDescription(e.getAccountDescription());
//			ecriture.setCostCenter(e.getCostCenter());
//			ecriture.setId(e.getId());
//			ecriture.setMontant( e.getMontant() != null ? e.getMontant().toString() : "");
//			ecriture.setMontantMAD(e.getMontantMAD() != null ? e.getMontantMAD().toString() : "");
//			ecriture.setNcpt(e.getNcpt());
//			ecriture.setRef1(e.getRef1());
//			ecriture.setRef2(e.getRef2());
//			ecriture.setRef3(e.getRef3());
//			ecriture.setRef4(e.getRef4());
//			ecriture.setRef5(e.getRef5());
//			ecriture.setRef6(e.getRef6());
//			ecriture.setSens(e.getSens().toString());
//			pieceComptable.getEcritures().add(ecriture);
//		}
//		return pieceComptable;
//	}
}

package ma.inetum.brique.mapper;

import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

import ma.inetum.brique.bean.PieceComptableDetailsView;
import ma.inetum.brique.bean.PieceComptableDetailsView.EcritureComptableView;
import ma.inetum.brique.bean.PieceComptableView;
import ma.inetum.brique.model.principale.EcritureComptable;
import ma.inetum.brique.model.principale.PieceComptable;
import ma.inetum.brique.utilities.Constantes;

public class PieceComptableMapper {

	private static PieceComptableMapper mapper;

	private PieceComptableMapper() {
	}

	public static PieceComptableMapper getInstance() {

		// create object if it's not already created
		if (mapper == null) {
			mapper = new PieceComptableMapper();
		}

		// returns the singleton object
		return mapper;
	}

	public PieceComptableView entityTobean(PieceComptable p) {
		PieceComptableView pieceComptable = new PieceComptableView();
		pieceComptable.setCodeJournale(p.getCodeJournale());
		if(p.getDate() != null){
			pieceComptable.setDate(p.getDate().format(Constantes.FORMAT_DATE));}
		pieceComptable.setDescription(p.getEnteteDoc());
		pieceComptable.setNumeroPiece(p.getNumeroPiece());
		pieceComptable.setFlux(p.getFlux().getNom());
		pieceComptable.setId(p.getId());
		pieceComptable.setStatut(p.getSent());
		return pieceComptable;
	}

	public PieceComptableDetailsView entityTobeanDetails(PieceComptable p) {
		PieceComptableDetailsView pieceComptable = new PieceComptableDetailsView();
		pieceComptable.setCodeJournale(p.getCodeJournale());
		DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols();
		formatSymbols.setDecimalSeparator(',');
		formatSymbols.setGroupingSeparator(' ');
		if(p.getDate() != null){
			pieceComptable.setDate(p.getDate().format(Constantes.FORMAT_DATE));}
		pieceComptable.setDescription(p.getEnteteDoc());
		pieceComptable.setNumeroPiece(p.getNumeroPiece());
		pieceComptable.setFlux(p.getFlux().getNom());
		pieceComptable.setFluxCode(p.getFlux().getCode());
		pieceComptable.setId(p.getId());
		pieceComptable.setStatut(p.getSent());
		pieceComptable.setEcritures(new ArrayList<>());
		for(EcritureComptable e : p.getEcritures()) {
			EcritureComptableView ecriture = new EcritureComptableView();
			ecriture.setAccountDescription(e.getAccountDescription());
			ecriture.setCostCenter(e.getCostCenter());
			ecriture.setId(e.getId());
			ecriture.setMontant(e.getMontant() != null ?  Constantes.DECIMAL_FORMAT_MONTANT.format(e.getMontant()) : "" );
			ecriture.setMontantMAD(e.getMontantMAD() != null ?  Constantes.DECIMAL_FORMAT_MONTANT.format(e.getMontantMAD()) : "" );
			ecriture.setNcpt(e.getNcpt());
			ecriture.setRef1(e.getRef1());
			ecriture.setRef2(e.getRef2());
			ecriture.setRef3(e.getRef3());
			ecriture.setRef4(e.getRef4());
			ecriture.setRef5(e.getRef5());
			ecriture.setRef6(e.getRef6());
			ecriture.setSens(e.getSens().toString());
			pieceComptable.getEcritures().add(ecriture);
		}
		return pieceComptable;
	}
}

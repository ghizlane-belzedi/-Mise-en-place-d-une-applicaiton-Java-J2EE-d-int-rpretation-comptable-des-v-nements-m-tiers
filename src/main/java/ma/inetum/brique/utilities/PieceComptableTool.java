package ma.inetum.brique.utilities;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import ma.inetum.brique.model.principale.EcritureComptable;
import ma.inetum.brique.model.principale.Erreur;
import ma.inetum.brique.model.principale.PieceComptable;

public class PieceComptableTool {

	private PieceComptable piece;
	private boolean isPieceValide;
	private List<Erreur> erreurs;
	private Long magic;
	
	public PieceComptableTool(PieceComptable piece) {
		this.piece = piece;
		isPieceValide = validatePiece();
	}
	
	public PieceComptableTool(PieceComptable piece, Long magic) {
		this.piece = piece;
		this.magic = magic;
		isPieceValide = validatePiece();
	}

	public boolean validatePiece() {
		isPieceValide = true;
		if (erreurs == null) {
			erreurs = new ArrayList<>();
		}
		if (piece == null) {
			isPieceValide = false;
			erreurs.add(new Erreur(magic, 0L,"Pièce absente !!"));
		} else if ((piece.getEcritures() == null) || (piece.getEcritures().isEmpty())) {
			isPieceValide = false;
			erreurs.add(new Erreur(magic, 0L,"Pièce ne contenant aucune écriture"));			
		} else {
			BigDecimal mntDebit     = BigDecimal.ZERO;
			BigDecimal mntCredit    = BigDecimal.ZERO;
			BigDecimal mntDebitMAD  = BigDecimal.ZERO;
			BigDecimal mntCreditMAD = BigDecimal.ZERO;
			for (int idx=0; idx<piece.getEcritures().size();idx++) {
				EcritureComptable ecriture = piece.getEcritures().get(idx);
				if (ecriture.getSens().equals(Constantes.SENS_DEBIT)) {
					mntDebit = mntDebit.add(ecriture.getMontant()!=null?BigDecimal.valueOf(ecriture.getMontant()):BigDecimal.ZERO);
					mntDebitMAD = mntDebitMAD.add(ecriture.getMontantMAD()!=null?BigDecimal.valueOf(ecriture.getMontantMAD()):BigDecimal.ZERO);
				} else if (ecriture.getSens().equals(Constantes.SENS_CREDIT)) {
					mntCredit = mntCredit.add(ecriture.getMontant()!=null?BigDecimal.valueOf(ecriture.getMontant()):BigDecimal.ZERO);
					mntCreditMAD = mntCreditMAD.add(ecriture.getMontantMAD()!=null?BigDecimal.valueOf(ecriture.getMontantMAD()):BigDecimal.ZERO);
				} else {
					isPieceValide = false;
					erreurs.add(new Erreur(magic, Long.valueOf(idx),"Sens (C/D) non défini"));					
				}
			}
			if (mntDebit.compareTo(mntCredit)!=0) {
				isPieceValide = false;
				erreurs.add(new Erreur(magic, 0L,"Somme des montants débit en devise ("+mntDebit.setScale(2, RoundingMode.FLOOR)+") différente de la somme des montants crédit en devise ("+mntCredit.setScale(2, RoundingMode.FLOOR)+")"));				
			}
			if (mntDebitMAD.compareTo(mntCreditMAD)!=0) {
				isPieceValide = false;
				erreurs.add(new Erreur(magic, 0L,"Somme des montants débit ("+mntDebitMAD.setScale(2, RoundingMode.FLOOR)+") différente de la somme des montants crédit ("+mntCreditMAD.setScale(2, RoundingMode.FLOOR)+")"));				
			}
		}
		
		return isPieceValide;
	}

	public PieceComptable getPiece() {
		return piece;
	}

	public void setPiece(PieceComptable piece) {
		this.piece = piece;
	}

	public boolean isPieceValide() {
		return isPieceValide;
	}

	public void setPieceValide(boolean isPieceValide) {
		this.isPieceValide = isPieceValide;
	}

	public List<Erreur> getErreurs() {
		return erreurs;
	}

	public Long getMagic() {
		return magic;
	}

	public void setMagic(Long magic) {
		this.magic = magic;
	}

}

package ma.inetum.brique.services;

import ma.inetum.brique.model.principale.PieceComptable;

public interface CodaService {

	public PieceComptable saveInCoda(PieceComptable piece) throws RuntimeException;
}

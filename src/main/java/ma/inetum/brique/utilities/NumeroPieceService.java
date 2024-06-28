package ma.inetum.brique.utilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ma.inetum.brique.repository.principale.PieceComptableRepository;

@Component
public class NumeroPieceService {

    @Autowired
    PieceComptableRepository pieceComptableRepository;

    public Long getNumeroPiece(String codeJournal){
    	Long lastnumer = pieceComptableRepository.getMaxNumeroPieceByCodeJournal(codeJournal);
    	return lastnumer != null ? lastnumer : 1l;
    }
}

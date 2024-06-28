package ma.inetum.brique.repository.principale;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ma.inetum.brique.model.principale.PieceComptable;

public interface PieceComptableRepository extends JpaRepository<PieceComptable, Long>, JpaSpecificationExecutor<PieceComptable>{

    @Query("SELECT MAX(p.numeroPiece) FROM PieceComptable p WHERE p.codeJournale = :codeJournal")
    public Long getMaxNumeroPieceByCodeJournal(@Param("codeJournal") String codeJournal);

    @Query("SELECT p FROM PieceComptable p WHERE p.simulation.id = :id")
    public List<PieceComptable> findbySimulationId(@Param("id") Long id);
//    @Query("select max(v.numeroPiece) from PieceComptable v where v.sent = true and v.flux.code = :code")
//    public String finfLastNumero(String code);
    
//    @Query("select max(v.numeroPiece) from PieceComptable v where v.sent = true and v.codeJournale = :code")
//    public String findLastNumeroByCode(String code);
    
}

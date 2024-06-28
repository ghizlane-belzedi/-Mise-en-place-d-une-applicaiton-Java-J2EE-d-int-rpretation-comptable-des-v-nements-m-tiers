package ma.inetum.brique.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import ma.inetum.brique.bean.ListString;
import ma.inetum.brique.bean.SimulationDetailsView;
import ma.inetum.brique.bean.SimulationView;
import ma.inetum.brique.exception.ExceptionFonctionnelle;
import ma.inetum.brique.model.principale.PieceComptable;

public interface SimulationService {
	
		public List<SimulationView> findByCriteria(String flux, String state, int page, int size, String sort, String dir);
	public List<SimulationView> getAll();
	public DataTablesOutput<SimulationView> getAll(DataTablesInput input, List<String> flux);
	public SimulationDetailsView findById(Long id) ;
	public ListString generer(String flux, LocalDate dateArret ) ;
	public SimulationDetailsView generer(Long id) throws ExceptionFonctionnelle ;
	
	public Map<Boolean, Object> simuler(String flux, String dateArret, SimulationDetailsView view) throws CloneNotSupportedException;
	//public UserDetails findByUserName(String username);
	public SimulationDetailsView annuler(Long id);
	public List<PieceComptable> getAllPieces(Long simulationId);
}

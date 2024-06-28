package ma.inetum.brique.services;

import java.util.List;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import ma.inetum.brique.bean.PieceComptableDetailsView;
import ma.inetum.brique.bean.PieceComptableView;
import ma.inetum.brique.model.principale.PieceComptable;

public interface PieceComptableService {

	public PieceComptableDetailsView findById(Long id) ;

	public List<PieceComptableView> getAll();
	public DataTablesOutput<PieceComptableView> getAll(DataTablesInput input, List<String> flux);

	List<PieceComptable> getAllVNFacturedNotDeliverd();
}

package ma.inetum.brique.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import ma.inetum.brique.model.principale.Chargement;
import ma.inetum.brique.model.principale.Invoice;
import ma.inetum.brique.model.principale.Simulation;

public interface InvoiceService {

	<T> List<List<String>> generer(List<String> header, LocalDate date, Class<?> c);
	<T> List<List<String>> generer(List<String> header, Long first, Long last, Class<?> c);
	
	List<Invoice> findAllByDate(LocalDate date);
	Map<Boolean, Object> simuler(Simulation simulation, LocalDate date) throws CloneNotSupportedException;
	public  List<ma.inetum.brique.model.metier.Invoice> findAllForBatch(Long firstElementToLoad);
	public void saveAll(List<Invoice> list) ;
	public Boolean generer(Simulation simulation);
	public void saveAll(List<Invoice> list, Chargement newChargement);
	<T> List<List<String>> generer(List<String> header, Long simulationId, Class<?> c);
	
	public  List<Invoice> factureNotLivred();
}

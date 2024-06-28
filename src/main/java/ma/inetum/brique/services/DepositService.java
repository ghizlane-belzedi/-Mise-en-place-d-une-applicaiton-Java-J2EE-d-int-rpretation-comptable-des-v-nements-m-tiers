package ma.inetum.brique.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import ma.inetum.brique.model.principale.Chargement;
import ma.inetum.brique.model.principale.Deposit;
import ma.inetum.brique.model.principale.Simulation;

public interface DepositService {

	<T> List<List<String>> generer(List<String> header, LocalDate date, Class<?> c);
	<T> List<List<String>> generer(List<String> header, Long first, Long last, Class<?> c);
	
	List<Deposit> findAllByDate(LocalDate date);
	Map<Boolean, Object> simuler(Simulation simulation, LocalDate date) throws CloneNotSupportedException;
	public  List<ma.inetum.brique.model.metier.Deposit> findAllForBatch(Long firstElementToLoad);
	public void saveAll(List<Deposit> list) ;
	public Boolean generer(Simulation simulation);
	public void saveAll(List<Deposit> list, Chargement newChargement);
	<T> List<List<String>> generer(List<String> header, Long simulationId, Class<?> c);
}

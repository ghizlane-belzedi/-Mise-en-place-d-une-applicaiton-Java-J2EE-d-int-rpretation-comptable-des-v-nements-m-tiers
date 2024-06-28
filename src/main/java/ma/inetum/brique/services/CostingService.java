package ma.inetum.brique.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import ma.inetum.brique.model.principale.CostingHeader;
import ma.inetum.brique.model.principale.Simulation;

public interface CostingService {

	<T> List<List<String>> generer(List<String> header, LocalDate date, Class<?> c1, Class<?> c2);
	<T> List<List<String>> generer(List<String> header, Long first, Long last, Class<?> c1, Class<?> c2);

	List<CostingHeader> findAllByDate(LocalDate date);
	Map<Boolean, Object> simuler(Simulation simulation, LocalDate date) throws CloneNotSupportedException;
	public  List<ma.inetum.brique.model.metier.CostingHeader> findAllForBatch(Long firstElementToLoad);
	public boolean saveAll(List<CostingHeader> list) ;
	public Boolean generer(Simulation simulation);
	<T> List<List<String>> generer(List<String> header, Long simulationId, Class<?> c1, Class<?> c2);

}

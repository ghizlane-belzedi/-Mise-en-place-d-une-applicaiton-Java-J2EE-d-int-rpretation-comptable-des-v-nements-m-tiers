package ma.inetum.brique.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import ma.inetum.brique.model.principale.Simulation;
import ma.inetum.brique.model.principale.WorkShopHeader;

public interface WorkShopService {

	<T> List<List<String>> generer(List<String> header, LocalDate date, Class<?> c1, Class<?> c2);
	<T> List<List<String>> generer(List<String> header, Long first, Long last, Class<?> c1, Class<?> c2);

	List<WorkShopHeader> findAllByDate(LocalDate date);
	Map<Boolean, Object> simuler(Simulation simulation, LocalDate date) throws CloneNotSupportedException;
	public  List<ma.inetum.brique.model.metier.WorkShopHeader> findAllForBatch(Long firstElementToLoad);
	public boolean saveAll(List<WorkShopHeader> list) ;
	public Boolean generer(Simulation simulation);
	<T> List<List<String>> generer(List<String> header, Long simulationId, Class<?> c1, Class<?> c2);

}

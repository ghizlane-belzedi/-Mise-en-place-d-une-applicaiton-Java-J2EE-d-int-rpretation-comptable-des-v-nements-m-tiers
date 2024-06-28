package ma.inetum.brique.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import ma.inetum.brique.model.principale.Chargement;
import ma.inetum.brique.model.principale.Simulation;
import ma.inetum.brique.model.principale.VehiculeOrder;

public interface VehiculeOrderService {

	<T> List<List<String>> generer(List<String> header, LocalDate date, Class<?> c);
	<T> List<List<String>> generer(List<String> header, Long first, Long last, Class<?> c);
	
	List<VehiculeOrder> findAllByDate(LocalDate date);
	Map<Boolean, Object> simuler(Simulation simulation, LocalDate date) throws CloneNotSupportedException;
	public  List<ma.inetum.brique.model.metier.VehiculeOrder> findAllForBatch(Long firstElementToLoad);
	public void saveAll(List<VehiculeOrder> list) ;
	public Boolean generer(Simulation simulation);
	public boolean saveAll(List<VehiculeOrder> list, Chargement newChargement);
}

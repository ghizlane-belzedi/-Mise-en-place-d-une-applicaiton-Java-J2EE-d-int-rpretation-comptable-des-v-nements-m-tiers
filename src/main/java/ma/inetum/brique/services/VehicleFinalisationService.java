package ma.inetum.brique.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import ma.inetum.brique.model.metier.VehicleFinalisationHeader;
import ma.inetum.brique.model.principale.Simulation;
import ma.inetum.brique.model.principale.VehicleFinalisationHeaderP;

public interface VehicleFinalisationService {

    public List<VehicleFinalisationHeader> findAllForBatch(Long firstElementToLoad);

    public void saveAll(List<VehicleFinalisationHeaderP> listHeader);

    <T> List<List<String>> generer(List<String> header, LocalDate date, Class<?> c1, Class<?> c2);

    public Map<Boolean, Object> simuler(Simulation simulation, LocalDate dateArret) throws CloneNotSupportedException;

    public Boolean generer(Simulation simulation);

    <T> List<List<String>> generer(List<String> header, Long first, Long last, Class<?> c1, Class<?> c2);

	<T> List<List<String>> generer(List<String> header, Long simulationId, Class<?> c1, Class<?> c2);

}

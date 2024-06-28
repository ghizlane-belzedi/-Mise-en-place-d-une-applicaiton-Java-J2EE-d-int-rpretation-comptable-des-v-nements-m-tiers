package ma.inetum.brique.services;

import ma.inetum.brique.model.metier.LogisticOperations;
import ma.inetum.brique.model.principale.LogisticOperationsP;
import ma.inetum.brique.model.principale.Simulation;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface LogisticOperationsService {

    public void saveAll(List<LogisticOperationsP> logisticOperationsList);

    public List<LogisticOperations> findAllForBatch(Long firstElementToLoad);

    Map<Boolean, Object> simuler(Simulation simulation, LocalDate date) throws CloneNotSupportedException;

    public Boolean generer(Simulation simulation);

    <T> List<List<String>> generer(List<String> header, LocalDate date, Class<?> c);

    <T> List<List<String>> generer(List<String> header, Long first, Long last, Class<?> c);

	<T> List<List<String>> generer(List<String> header, Long simulationId, Class<?> c);
}

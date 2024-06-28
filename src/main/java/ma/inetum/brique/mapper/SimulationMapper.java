package ma.inetum.brique.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import ma.inetum.brique.bean.SimulationDetailsView;
import ma.inetum.brique.bean.SimulationView;
import ma.inetum.brique.model.principale.Erreur;
import ma.inetum.brique.model.principale.Simulation;
import ma.inetum.brique.model.principale.SimulationState;
import ma.inetum.brique.utilities.Constantes;

public class SimulationMapper {
	private static SimulationMapper mapper;

	private SimulationMapper() {
	}

	public static SimulationMapper getInstance() {

		// create object if it's not already created
		if (mapper == null) {
			mapper = new SimulationMapper();
		}

		// returns the singleton object
		return mapper;
	}

	public SimulationView simulationToSimulationView(Simulation s) {
		SimulationView simulation = new SimulationView();
		simulation.setId(s.getId());
		simulation.setState(s.getState().name());
		simulation.setFlux(s.getFlux().getNom());
		simulation.setDateSimuation(s.getDateSimuation().format(Constantes.FORMAT_DATE_TIME));
		simulation.setDateGeneration(s.getDateGeneration().format(Constantes.FORMAT_DATE_TIME));
		simulation.setDateArreter(s.getDateArreter().format(Constantes.FORMAT_DATE));
		return simulation;
	}

	public SimulationDetailsView simulationToSimulationDetailsView(Simulation s) {
		SimulationDetailsView simulation = new SimulationDetailsView();
		simulation.setId(s.getId());
		simulation.setState(s.getState().name());
		simulation.setFlux(s.getFlux().getNom());
		simulation.setFluxCode(s.getFlux().getCode());
		simulation.setDateSimuation(s.getDateSimuation().format(Constantes.FORMAT_DATE_TIME));
		simulation.setDateGeneration(s.getDateGeneration().format(Constantes.FORMAT_DATE_TIME));
		simulation.setDateArreter(s.getDateArreter().format(Constantes.FORMAT_DATE));
		simulation.setPieces(s.getPieces().stream().map(e -> PieceComptableMapper.getInstance().entityTobean(e)).collect(Collectors.toList()));
		Map<Long, List<String>> map = new TreeMap<>();
		for(Erreur e : s.getErreurs()) {
			Long magic = e.getMagic() == null ? 0L:e.getMagic();
			if (map.get(magic) == null) {
				map.put(magic, new ArrayList<>());
			}
			map.get(magic).add(e.getDescription());
		}
		simulation.setErrors(map);
		simulation.setStatut(!SimulationState.WITH_ERRORS.toString().equals(simulation.getState()));
		return simulation;
	}
}

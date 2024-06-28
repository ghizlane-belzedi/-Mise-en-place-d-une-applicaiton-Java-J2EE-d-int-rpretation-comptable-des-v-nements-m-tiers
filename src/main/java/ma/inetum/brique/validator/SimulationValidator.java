package ma.inetum.brique.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ma.inetum.brique.bean.SimulationDetailsView;
import ma.inetum.brique.repository.principale.SimulationRepository;

@Component

public class SimulationValidator implements Validator{

	@Autowired
	SimulationRepository simulationRepository;

	@Override
	public boolean supports(Class<?> clazz) {
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		SimulationDetailsView simulation = (SimulationDetailsView) target;
		if(simulation.getDateArreter() == null || simulation.getDateArreter().isBlank())
			errors.rejectValue("dateArreter", "", "Merci de renseigné le chanmps date arrêté");
		if(simulation.getFlux() == null || simulation.getFlux().isBlank())
			errors.rejectValue("flux", "", "Merci de renseigné le flux");
		Integer count = simulationRepository.getNbrSimulationsInProgressByFluxCode(simulation.getFlux());
		if(count > 0) {
			errors.rejectValue("enable", "", "Vous ne pouvez pas lancer une nouvelle simulation, Merci de terminer la Simulation en cours");
		}
	}
	
}

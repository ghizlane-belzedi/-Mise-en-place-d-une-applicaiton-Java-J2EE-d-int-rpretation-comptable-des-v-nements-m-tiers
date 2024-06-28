package ma.inetum.brique.thread;

import org.mapstruct.Mapper;

import ma.inetum.brique.bean.SimulationView;
import ma.inetum.brique.model.principale.Simulation;
@Mapper
interface SimulationMapper_ {
	SimulationView sendSimulationToSimulationView(Simulation simulation);
}
public class VehiculeOrderThread {


}

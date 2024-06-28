package ma.inetum.brique.mapper;

import java.util.List;
import java.util.stream.Collectors;

import ma.inetum.brique.bean.FluxEditView;
import ma.inetum.brique.bean.FluxEditView.FluxField;
import ma.inetum.brique.bean.FluxView;
import ma.inetum.brique.model.principale.ParametrageFLux;

public class FluxMapper {
	private static FluxMapper mapper;

	private FluxMapper() {
	}

	public static FluxMapper getInstance() {

		// create object if it's not already created
		if (mapper == null) {
			mapper = new FluxMapper();
		}

		// returns the singleton object
		return mapper;
	}

	public FluxView fluxToFluxView(ParametrageFLux c) {
		FluxView flux = new FluxView();
		flux.setFluxId(c.getId());
		flux.setNom(c.getNom());
		flux.setCode(c.getCode());
		return flux;
	}

	public FluxEditView fluxToFluxEditView(ParametrageFLux f) {
		FluxEditView flux = new FluxEditView();
		flux.setCode(f.getCode());
		flux.setNom(f.getNom());
		flux.setFluxId(f.getId());
		List<FluxField> fields = f
				.getFields().stream().map(e -> new FluxEditView.FluxField(e.getId(), e.getCodeBDD(), e.getDescription(),
						e.getOrdre(), e.getVisible()))
				.sorted()
				.collect(Collectors.toList());
		flux.setFields(fields);
//		flux.setFields(f.getFields().stream().map(e -> 
//			 new FluxEditView.FluxField(e.getId(), e.getCodeBDD(), e.getDescription(), e.getOrdre(), e.getVisible())
//		).collect(Collectors.toCollection(TreeSet::new)));
		return flux;
	}
}

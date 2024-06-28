package ma.inetum.brique.mapper;

import ma.inetum.brique.bean.CompteVersementBean;
import ma.inetum.brique.model.principale.ParamCompteVersement;

public class CompteVersementMapper {
	private static CompteVersementMapper mapper;
//	private static Logger logger = LoggerFactory.getLogger(CompteFacturationMapper.class);
	public static CompteVersementMapper getInstance() {
		if (mapper == null) {
			mapper = new CompteVersementMapper();
		}
		return mapper;
	}
	public CompteVersementBean transform(ParamCompteVersement compteVersement) {
		CompteVersementBean compte = new CompteVersementBean();
		compte.setId(compteVersement.getId());
		compte.setSite(compteVersement.getSite());
		compte.setModePaiement(compteVersement.getModePaiement());
		compte.setCompteGeneral(compteVersement.getCompteGeneral());
		compte.setDescription(compteVersement.getDescription());
		return compte;
	}
	public ParamCompteVersement transform(CompteVersementBean compteVersement) {
		ParamCompteVersement compte = new ParamCompteVersement();
		compte.setId(compteVersement.getId());
		compte.setSite(compteVersement.getSite());
		compte.setModePaiement(compteVersement.getModePaiement());
		compte.setCompteGeneral(compteVersement.getCompteGeneral());
		compte.setDescription(compteVersement.getDescription());
		return compte;
	}
}

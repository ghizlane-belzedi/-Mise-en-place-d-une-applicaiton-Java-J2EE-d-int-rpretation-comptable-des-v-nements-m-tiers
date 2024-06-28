package ma.inetum.brique.mapper;

import ma.inetum.brique.bean.CompteFacturationBean;
import ma.inetum.brique.model.principale.ParamCompteFacturation;

public class CompteFacturationMapper {
	private static CompteFacturationMapper mapper;
//	private static Logger logger = LoggerFactory.getLogger(CompteFacturationMapper.class);
	public static CompteFacturationMapper getInstance() {
		if (mapper == null) {
			mapper = new CompteFacturationMapper();
		}
		return mapper;
	}
	public CompteFacturationBean transform(ParamCompteFacturation compteFacturation) {
		CompteFacturationBean compte = new CompteFacturationBean();
		compte.setId(compteFacturation.getId());
		compte.setSite(compteFacturation.getSite());
		compte.setTva(compteFacturation.getTva());
		compte.setDesc1(compteFacturation.getDesc1());
		compte.setDesc2(compteFacturation.getDesc2());
		compte.setDesc3(compteFacturation.getDesc3());
		compte.setCompteGeneral(compteFacturation.getCompteGeneral());
		compte.setCompteClass3(compteFacturation.getCompteClass3());
		compte.setCompteClass4(compteFacturation.getCompteClass4());
		return compte;
	}
	public ParamCompteFacturation transform(CompteFacturationBean compteFacturation) {
		ParamCompteFacturation compte = new ParamCompteFacturation();
		compte.setId(compteFacturation.getId());
		compte.setSite(compteFacturation.getSite());
		compte.setTva(compteFacturation.getTva());
		compte.setDesc1(compteFacturation.getDesc1());
		compte.setDesc2(compteFacturation.getDesc2());
		compte.setDesc3(compteFacturation.getDesc3());
		compte.setCompteGeneral(compteFacturation.getCompteGeneral());
		compte.setCompteClass3(compteFacturation.getCompteClass3());
		compte.setCompteClass4(compteFacturation.getCompteClass4());
		return compte;
	}
}

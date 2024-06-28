package ma.inetum.brique.mapper;

import ma.inetum.brique.bean.ClientBean;
import ma.inetum.brique.model.principale.ParametrageClient;

public class ClientMapper {
	private static ClientMapper mapper;
//	private static Logger logger = LoggerFactory.getLogger(CompteFacturationMapper.class);
	public static ClientMapper getInstance() {
		if (mapper == null) {
			mapper = new ClientMapper();
		}
		return mapper;
	}
	public ClientBean transform(ParametrageClient c) {
		ClientBean client = new ClientBean();
		client.setId(c.getId());
		client.setSite(c.getSite());
		client.setCodeFinance(c.getCodeFinance());
		client.setCodeMedtier(c.getCodeMedtier());
		client.setDescription(c.getDescription());
		return client;
	}
	public ParametrageClient transform(ClientBean c) {
		ParametrageClient client = new ParametrageClient();
		client.setId(c.getId());
		client.setSite(c.getSite());
		client.setCodeFinance(c.getCodeFinance());
		client.setCodeMedtier(c.getCodeMedtier());
		client.setDescription(c.getDescription());
		return client;
	}
}

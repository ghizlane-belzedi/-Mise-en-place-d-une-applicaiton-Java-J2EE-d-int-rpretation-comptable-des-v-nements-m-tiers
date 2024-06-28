package ma.inetum.brique.services.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.inetum.brique.repository.principale.ParamModePaiementRepository;
import ma.inetum.brique.services.ModePaiementService;
@Service
public class ModePaiementServiceImpl implements ModePaiementService{

	@Autowired
	private ParamModePaiementRepository modePaiementRepository;
	@Override
	public Map<String, String> getAll() {
		Map<String, String> map = new HashMap<>();
		var list = modePaiementRepository.findAll();
		if(list != null) {
			list.forEach(mode -> {
				if(mode.getCode() != null && !mode.getCode().isBlank()) {
					map.put(mode.getCode(), mode.getDescription());
				}
			});
		}
		return map;
	}

}

package ma.inetum.brique.mapper;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ma.inetum.brique.model.principale.VehiculeOrder;

public class VehicleOrderMapper {

	private static VehicleOrderMapper mapper;
	private static Logger logger = LoggerFactory.getLogger(VehicleOrderMapper.class);

	public static VehicleOrderMapper getInstance() {
		if (mapper == null) {
			mapper = new VehicleOrderMapper();
		}
		return mapper;
	}

	public VehiculeOrder transform(VehiculeOrder vehiculeOrder, ma.inetum.brique.model.metier.VehiculeOrder businessVO) {
		if (businessVO != null) {

			if (vehiculeOrder == null) {
				vehiculeOrder = new VehiculeOrder();
			}
			
			try {
				BeanUtils.copyProperties(vehiculeOrder, businessVO);
				vehiculeOrder.setMagic(businessVO.getId());
				vehiculeOrder.setId(null);
			} catch (IllegalAccessException | InvocationTargetException e) {
				logger.error("Une erreur s'est produite lors de la conversion des objets métiers VehiculeOrder", e);
			}
		}
		return vehiculeOrder;
	}

	public List<VehiculeOrder> transform(List<ma.inetum.brique.model.metier.VehiculeOrder> list) {
		List<VehiculeOrder> vehiculeOrderList = null;
		if ((list != null) && (!list.isEmpty())) {

			vehiculeOrderList = new ArrayList<>();
			try {
				BeanUtils.copyProperties(vehiculeOrderList, list);
			} catch (IllegalAccessException | InvocationTargetException e) {
				logger.error("Une erreur s'est produite lors de la conversion des objets métiers VehiculeOrder", e);
			}
		}
		return vehiculeOrderList;
	}
}

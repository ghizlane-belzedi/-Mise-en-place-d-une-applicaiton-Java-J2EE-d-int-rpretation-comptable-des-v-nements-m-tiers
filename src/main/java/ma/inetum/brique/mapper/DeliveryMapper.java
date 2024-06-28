package ma.inetum.brique.mapper;

import ma.inetum.brique.model.metier.Delivery;
import ma.inetum.brique.model.principale.DeliveryP;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;

public class DeliveryMapper {

    private static DeliveryMapper mapper = DeliveryMapper.getInstance();
    private static Logger logger = LoggerFactory.getLogger(DeliveryMapper.class);

    public static ma.inetum.brique.mapper.DeliveryMapper getInstance() {
        if (mapper == null) {
            mapper = new ma.inetum.brique.mapper.DeliveryMapper();
        }
        return mapper;
    }

    public static DeliveryP transform(DeliveryP deliveryP, Delivery delivery){
        if(delivery != null){
            if(deliveryP == null){
                deliveryP = new DeliveryP();
            }
            try{
                BeanUtils.copyProperties(deliveryP, delivery);
                deliveryP.setMagic(delivery.getId());
            }
            catch(IllegalAccessException | InvocationTargetException e){
                logger.error("Une erreur s'est produite lors de la conversion des objets métiers Delivery Note.", e);
            }
        }
        return deliveryP;
    }

}

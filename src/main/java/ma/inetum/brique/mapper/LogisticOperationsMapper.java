package ma.inetum.brique.mapper;

import ma.inetum.brique.model.principale.LogisticOperationsP;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;

public class LogisticOperationsMapper {
    private static LogisticOperationsMapper mapper = LogisticOperationsMapper.getInstance();
    private static Logger logger = LoggerFactory.getLogger(LogisticOperationsMapper.class);

    public static ma.inetum.brique.mapper.LogisticOperationsMapper getInstance() {
        if (mapper == null) {
            mapper = new ma.inetum.brique.mapper.LogisticOperationsMapper();
        }
        return mapper;
    }

    public static LogisticOperationsP transform(LogisticOperationsP logisticOperationsP, ma.inetum.brique.model.metier.LogisticOperations logisticOperations){
        if(logisticOperations != null){
            if(logisticOperationsP == null){
                logisticOperationsP = new LogisticOperationsP();
            }
            try{
                BeanUtils.copyProperties(logisticOperationsP, logisticOperations);
                logisticOperationsP.setMagic(logisticOperations.getId());
            }
            catch(IllegalAccessException | InvocationTargetException e){
                logger.error("Une erreur s'est produite lors de la conversion des objets métiers Logistic Operations", e);
            }
        }
        return logisticOperationsP;
    }
}

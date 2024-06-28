package ma.inetum.brique.mapper;

import ma.inetum.brique.model.metier.VehicleFinalisationDetails;
import ma.inetum.brique.model.metier.VehicleFinalisationHeader;
import ma.inetum.brique.model.principale.VehicleFinalisationDetailsP;
import ma.inetum.brique.model.principale.VehicleFinalisationHeaderP;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;

public class VehicleFinalisationMapper {
    private static  VehicleFinalisationMapper mapper = VehicleFinalisationMapper.getInstance();

    private static Logger logger = LoggerFactory.getLogger(VehicleFinalisationMapper.class);


    public static ma.inetum.brique.mapper.VehicleFinalisationMapper getInstance() {
        if (mapper == null) {
            mapper = new ma.inetum.brique.mapper.VehicleFinalisationMapper();
        }
        return mapper;
    }

    public static VehicleFinalisationHeaderP transform(VehicleFinalisationHeaderP header, VehicleFinalisationHeader vehicleFinalisationHeader) {
        if(vehicleFinalisationHeader != null){
            if(header == null){
                header = new VehicleFinalisationHeaderP();
            }

            try{
                BeanUtils.copyProperties(header, vehicleFinalisationHeader);
                header.setMagic(vehicleFinalisationHeader.getId());

                if(vehicleFinalisationHeader.getDetails() != null){
                    for(VehicleFinalisationDetails detail : vehicleFinalisationHeader.getDetails()){
                        VehicleFinalisationDetailsP detailsP = new VehicleFinalisationDetailsP();

                        detailsP.setCompid(detail.getCompid());
                        detailsP.setVehicle(detail.getVehicle() != null ? detail.getVehicle().intValue() : 0 );
                        detailsP.setChassis(detail.getChassis());
                        detailsP.setModel(detail.getModel());
                        detailsP.setModdesc(detail.getModdesc());
                        detailsP.setVariant(detail.getVariant());
                        detailsP.setVardesc(detail.getVardesc());
                        detailsP.setColcode(detail.getColcode());
                        detailsP.setColdesc(detail.getColdesc());
                        detailsP.setTrimcode(detail.getTrimcode());
                        detailsP.setTrimdesc(detail.getTrimdesc());
                        detailsP.setComm(detail.getComm());
                        detailsP.setCons(detail.getCons());
                        detailsP.setFreight(detail.getFreight());
                        detailsP.setBuycurr(detail.getBuycurr());
                        detailsP.setBuyexch(detail.getBuyexch());
                        detailsP.setFindate(detail.getFindate());
                        detailsP.setFran(detail.getFran());
                        detailsP.setFrandesc(detail.getFrandesc());
                        detailsP.setMagic(detail.getVehicleFinalisationHeader().getId());
                        detailsP.setVehicleFinalisationHeader(header);

                        header.getVehicleFinalisationDetailsP().add(detailsP);
                    }
                }
            }
            catch(IllegalAccessException | InvocationTargetException e){
                logger.error("Une erreur s'est produite lors de la conversion des objets métiers Vehicule Finalisation", e);
            }
        }
        return header;
    }

}

package ma.inetum.brique.mapper;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ma.inetum.brique.model.principale.Deposit;

public class DepositMapper {

	private static DepositMapper mapper;
	private static Logger logger = LoggerFactory.getLogger(DepositMapper.class);

	public static DepositMapper getInstance() {
		if (mapper == null) {
			mapper = new DepositMapper();
		}
		return mapper;
	}

	public Deposit transform(Deposit deposit, ma.inetum.brique.model.metier.Deposit businessDeposit) {
		if (businessDeposit != null) {

			if (deposit == null) {
				deposit = new Deposit();
			}
			if (businessDeposit.getDocnum() != null)
				deposit.setDocnum(businessDeposit.getDocnum());
			if (businessDeposit.getDocdate() != null)
				deposit.setDocdate(businessDeposit.getDocdate());
			if (businessDeposit.getLnkcomp() != null)
				deposit.setLnkcomp(businessDeposit.getLnkcomp().trim());
			if (businessDeposit.getOrdnum() != null)
				deposit.setOrdnum(businessDeposit.getOrdnum());
			if (businessDeposit.getOrddate() != null)
				deposit.setOrddate(businessDeposit.getOrddate());
			if (businessDeposit.getCustcode() != null)
				deposit.setCustcode(businessDeposit.getCustcode());
			if (businessDeposit.getCustname() != null)
				deposit.setCustname(businessDeposit.getCustname().trim());
			if (businessDeposit.getAccount() != null)
				deposit.setAccount(businessDeposit.getAccount().trim());
			if (businessDeposit.getAccname() != null)
				deposit.setAccname(businessDeposit.getAccname().trim());
			if (businessDeposit.getVscompid() != null)
				deposit.setVscompid(businessDeposit.getVscompid().trim());
			if (businessDeposit.getVehicle() != null)
				deposit.setVehicle(businessDeposit.getVehicle());
			if (businessDeposit.getChassis() != null)
				deposit.setChassis(businessDeposit.getChassis().trim());
			if (businessDeposit.getFran() != null)
				deposit.setFran(businessDeposit.getFran().trim());
			if (businessDeposit.getFrandesc() != null)
				deposit.setFrandesc(businessDeposit.getFrandesc().trim());
			if (businessDeposit.getModel() != null)
				deposit.setModel(businessDeposit.getModel().trim());
			if (businessDeposit.getModdesc() != null)
				deposit.setModdesc(businessDeposit.getModdesc().trim());
			if (businessDeposit.getVariant() != null)
				deposit.setVariant(businessDeposit.getVariant().trim());
			if (businessDeposit.getVardesc() != null)
				deposit.setVardesc(businessDeposit.getVardesc().trim());
			if (businessDeposit.getPaymcode() != null)
				deposit.setPaymcode(businessDeposit.getPaymcode().trim());
			if (businessDeposit.getDepref() != null)
				deposit.setDepref(businessDeposit.getDepref().trim());
			if (businessDeposit.getDepvalue() != null)
				deposit.setDepvalue(businessDeposit.getDepvalue());
			if (businessDeposit.getVatvalue() != null)
				deposit.setVatvalue(businessDeposit.getVatvalue());
			if (businessDeposit.getVatcode() != null)
				deposit.setVatcode(businessDeposit.getVatcode());
			if (businessDeposit.getStampdut() != null)
				deposit.setStampdut(businessDeposit.getStampdut());
			if (businessDeposit.getSellprice() != null)
				deposit.setSellprice(businessDeposit.getSellprice());
			if (businessDeposit.getCreationDate() != null)
				deposit.setCreationDate(businessDeposit.getCreationDate());
			if (businessDeposit.getUserId() != null)
				deposit.setUserId(businessDeposit.getUserId().trim());
			if (businessDeposit.getSequence() != null)
				deposit.setSequence(businessDeposit.getSequence());
			if (businessDeposit.getLocation() != null) {
				deposit.setLocation(businessDeposit.getLocation().trim());
			}
			deposit.setMagic(businessDeposit.getMagic());
		}
		return deposit;
	}

	public List<Deposit> transform(List<ma.inetum.brique.model.metier.Deposit> list) {
		List<Deposit> depositList = null;
		if ((list != null) && (!list.isEmpty())) {

			depositList = new ArrayList<>();
			try {
				BeanUtils.copyProperties(depositList, list);
			} catch (IllegalAccessException | InvocationTargetException e) {
				logger.error("Une erreur s'est produite lors de la conversion des objets métiers VehiculeOrder", e);
			}
		}
		return depositList;
	}
}

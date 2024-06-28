package ma.inetum.brique.mapper;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ma.inetum.brique.exception.ExceptionFonctionnelle;
import ma.inetum.brique.model.principale.WorkShopDetails;
import ma.inetum.brique.model.principale.WorkShopHeader;

public class WorkShopMapper {

	private static WorkShopMapper mapper;
	private static Logger logger = LoggerFactory.getLogger(WorkShopMapper.class);

	public static WorkShopMapper getInstance() {
		if (mapper == null) {
			mapper = new WorkShopMapper();
		}
		return mapper;
	}

	public WorkShopHeader transform(WorkShopHeader header, ma.inetum.brique.model.metier.WorkShopHeader BusinessInvoice)
			throws ExceptionFonctionnelle {
		if (BusinessInvoice != null) {

			if (header == null) {
				header = new WorkShopHeader();
			}

			// BeanUtils.copyProperties(header, BusinessInvoice);
			header.setVsCompId(BusinessInvoice.getVsCompId());
			header.setAccount(BusinessInvoice.getAccount());
			header.setBranchNo(BusinessInvoice.getBranchNo());
			header.setCreationDate(BusinessInvoice.getCreationDate());
			header.setCustName(BusinessInvoice.getCustName());
			header.setInValue(BusinessInvoice.getInValue());
			header.setInvDate(BusinessInvoice.getInvDate());
			header.setInvNo(BusinessInvoice.getInvNo());
			header.setMagic(BusinessInvoice.getMagic());
			header.setUserId(BusinessInvoice.getUserId());
			header.setVatCode(BusinessInvoice.getVatCode());
			header.setVatValue(BusinessInvoice.getVatValue());
			header.setWipNo(BusinessInvoice.getWipNo());
			if (BusinessInvoice.getDetails() != null) {
				List<ma.inetum.brique.model.metier.WorkShopDetails> details = List.copyOf(BusinessInvoice.getDetails());
				for (var BusinessDetail : details) {
					WorkShopDetails detail = new WorkShopDetails();
					detail.setAnalDesc(BusinessDetail.getAnalDesc());
					detail.setAnalysis(BusinessDetail.getAnalysis());
					detail.setChassis(BusinessDetail.getChassis());
					detail.setComm(BusinessDetail.getComm());
					detail.setVsCompId(BusinessDetail.getVsCompId());
					detail.setFran(BusinessDetail.getFran());
					detail.setFranDesc(BusinessDetail.getFranDesc());
					detail.setInValue(BusinessDetail.getInValue());
					detail.setLocn(BusinessDetail.getLocn());
					detail.setHeader(header);
					detail.setMagic(BusinessDetail.getHeader().getMagic());
					detail.setProduct(BusinessDetail.getProduct());
					detail.setProgress(BusinessDetail.getProgress());
					detail.setStatus(BusinessDetail.getStatus());
					detail.setVatCode(BusinessDetail.getVatCode());
					detail.setVatValue(BusinessDetail.getVatValue());
					detail.setVehicle(BusinessDetail.getVehicle());
					header.getDetails().add(detail);
				}
			}
		}
		return header;
	}

	public List<WorkShopHeader> transform(List<ma.inetum.brique.model.metier.WorkShopHeader> list) {
		List<WorkShopHeader> headerList = null;
		if ((list != null) && (!list.isEmpty())) {

			headerList = new ArrayList<>();
			try {
				BeanUtils.copyProperties(headerList, list);
			} catch (IllegalAccessException | InvocationTargetException e) {
				logger.error("Une erreur s'est produite lors de la conversion des objets métiers VehiculeOrder", e);
			}
		}
		return headerList;
	}
}

package ma.inetum.brique.mapper;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ma.inetum.brique.exception.ExceptionFonctionnelle;
import ma.inetum.brique.model.principale.CostingDetails;
import ma.inetum.brique.model.principale.CostingHeader;

public class CostingMapper {

	private static CostingMapper mapper;
	private static Logger logger = LoggerFactory.getLogger(CostingMapper.class);

	public static CostingMapper getInstance() {
		if (mapper == null) {
			mapper = new CostingMapper();
		}
		return mapper;
	}

	public CostingHeader transform(CostingHeader header, ma.inetum.brique.model.metier.CostingHeader businessCosting)
			throws ExceptionFonctionnelle {
		if (businessCosting != null) {

			if (header == null) {
				header = new CostingHeader();
			}

			// BeanUtils.copyProperties(header, BusinessCosting);
			header.setCompid(businessCosting.getCompid());
			header.setCurrcode(businessCosting.getCurrcode());
			header.setExchrate(businessCosting.getExchrate());
			header.setImcommno(businessCosting.getImcommno());
			header.setImfileno(businessCosting.getImfileno());
			header.setInvalue(businessCosting.getInvalue());
			header.setInvdate(businessCosting.getInvdate());
			header.setInvno(businessCosting.getInvno());
			header.setMagic(businessCosting.getMagic());
			header.setShipdate(businessCosting.getShipdate());
			header.setShipname(businessCosting.getShipname());
			header.setShipno(businessCosting.getShipno());
			header.setSupacc(businessCosting.getSupacc());
			header.setSupplier(businessCosting.getSupplier());
			header.setVatcode(businessCosting.getVatcode());
			header.setVatvalue(businessCosting.getVatvalue());
			header.setCreationDate(businessCosting.getCreationDate());
			header.setUserId(businessCosting.getUserId());
			header.setInvoiceSupplierReference(businessCosting.getInvoiceSupplierReference()!=null?businessCosting.getInvoiceSupplierReference().trim():null);
			header.setInvoiceSupplierReferenceCompl(businessCosting.getInvoiceSupplierReferenceCompl()!=null?businessCosting.getInvoiceSupplierReferenceCompl().trim():null);
			header.setDocType(businessCosting.getDocType()!=null?businessCosting.getDocType().trim():null);
			if (businessCosting.getDetails() != null) {
				List<ma.inetum.brique.model.metier.CostingDetails> details = List.copyOf(businessCosting.getDetails());
				for (var BusinessDetail : details) {
					CostingDetails detail = new CostingDetails();
					detail.setAnaldesc(BusinessDetail.getAnaldesc());
					detail.setAnalysis(BusinessDetail.getAnalysis());
					detail.setChassis(BusinessDetail.getChassis());
					detail.setComm(BusinessDetail.getComm());
					detail.setCompid(BusinessDetail.getCompid());
					detail.setFran(BusinessDetail.getFran());
					detail.setFrandesc(BusinessDetail.getFrandesc());
					detail.setInvalue(BusinessDetail.getInvalue());
					detail.setLocn(BusinessDetail.getLocn());
					detail.setHeader(header);
					detail.setMagic(BusinessDetail.getHeader().getMagic());
					detail.setProddesc(BusinessDetail.getProddesc());
					detail.setProduct(BusinessDetail.getProduct());
					detail.setProgress(BusinessDetail.getProgress());
					detail.setStatus(BusinessDetail.getStatus());
					detail.setVatcode(BusinessDetail.getVatcode());
					detail.setVatvalue(BusinessDetail.getVatvalue());
					detail.setVehicle(BusinessDetail.getVehicle());
					if (!header.getDetails().contains(detail)) {
						header.getDetails().add(detail);
					}
				}
			}
		}
		return header;
	}

	public List<CostingHeader> transform(List<ma.inetum.brique.model.metier.CostingHeader> list) {
		List<CostingHeader> headerList = null;
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

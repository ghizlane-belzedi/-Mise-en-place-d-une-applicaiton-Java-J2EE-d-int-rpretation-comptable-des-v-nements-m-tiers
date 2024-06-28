package ma.inetum.brique.mapper;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ma.inetum.brique.model.principale.Invoice;

public class InvoiceMapper {

	private static InvoiceMapper mapper;
	private static Logger logger = LoggerFactory.getLogger(InvoiceMapper.class);

	public static InvoiceMapper getInstance() {
		if (mapper == null) {
			mapper = new InvoiceMapper();
		}
		return mapper;
	}

	public Invoice transform(Invoice invoice, ma.inetum.brique.model.metier.Invoice businessInvoice) {
		if (businessInvoice != null) {

			if (invoice == null) {
				invoice = new Invoice();
			}

			try {
				BeanUtils.copyProperties(invoice, businessInvoice);
				if (businessInvoice.getCompid() != null)
					invoice.setCompid(businessInvoice.getCompid().trim());
				if (businessInvoice.getOpertype() != null)
					invoice.setOpertype(businessInvoice.getOpertype().trim());
				if (businessInvoice.getSelllocn() != null)
					invoice.setSelllocn(businessInvoice.getSelllocn().trim());
				if (businessInvoice.getInvoice() != null)
					invoice.setInvoice(businessInvoice.getInvoice());
				if (businessInvoice.getInvdate() != null)
					invoice.setInvdate(businessInvoice.getInvdate());
				if (businessInvoice.getCrnno() != null)
					invoice.setCrnno(businessInvoice.getCrnno());
				if (businessInvoice.getCrndate() != null)
					invoice.setCrndate(businessInvoice.getCrndate());
				if (businessInvoice.getCustord() != null)
					invoice.setCustord(businessInvoice.getCustord().trim());
				if (businessInvoice.getCusdate() != null)
					invoice.setCusdate(businessInvoice.getCusdate());
				if (businessInvoice.getCustcode() != null)
					invoice.setCustcode(businessInvoice.getCustcode().trim());
				if (businessInvoice.getCustname() != null)
					invoice.setCustname(businessInvoice.getCustname().trim());
				if (businessInvoice.getVehicle() != null)
					invoice.setVehicle(businessInvoice.getVehicle());
				if (businessInvoice.getChassis() != null)
					invoice.setChassis(businessInvoice.getChassis().trim());
				if (businessInvoice.getFran() != null)
					invoice.setFran(businessInvoice.getFran().trim());
				if (businessInvoice.getFrandesc() != null)
					invoice.setFrandesc(businessInvoice.getFrandesc().trim());
				if (businessInvoice.getModel() != null)
					invoice.setModel(businessInvoice.getModel().trim());
				if (businessInvoice.getModdesc() != null)
					invoice.setModdesc(businessInvoice.getModdesc().trim());
				if (businessInvoice.getVariant() != null)
					invoice.setVariant(businessInvoice.getVariant().trim());
				if (businessInvoice.getVardesc() != null)
					invoice.setVardesc(businessInvoice.getVardesc().trim());
				if (businessInvoice.getRegn() != null)
					invoice.setRegn(businessInvoice.getRegn().trim());
				if (businessInvoice.getSalenet() != null)
					invoice.setSalenet(businessInvoice.getSalenet());
				if (businessInvoice.getRecpri1() != null)
					invoice.setRecpri1(businessInvoice.getRecpri1());
				if (businessInvoice.getRecpri2() != null)
					invoice.setRecpri2(businessInvoice.getRecpri2());
				if (businessInvoice.getRecpri3() != null)
					invoice.setRecpri3(businessInvoice.getRecpri3());
				if (businessInvoice.getRecpri4() != null)
					invoice.setRecpri4(businessInvoice.getRecpri4());
				if (businessInvoice.getRecpri5() != null)
					invoice.setRecpri5(businessInvoice.getRecpri5());
				if (businessInvoice.getRecpri6() != null)
					invoice.setRecpri6(businessInvoice.getRecpri6());
				if (businessInvoice.getRecpri7() != null)
					invoice.setRecpri7(businessInvoice.getRecpri7());
				if (businessInvoice.getRecpri8() != null)
					invoice.setRecpri8(businessInvoice.getRecpri8());
				if (businessInvoice.getRecpri9() != null)
					invoice.setRecpri9(businessInvoice.getRecpri9());
				if (businessInvoice.getRecpri10() != null)
					invoice.setRecpri10(businessInvoice.getRecpri10());
				if (businessInvoice.getRecpri11() != null)
					invoice.setRecpri11(businessInvoice.getRecpri11());
				if (businessInvoice.getRecpri12() != null)
					invoice.setRecpri12(businessInvoice.getRecpri12());
				if (businessInvoice.getRecpri13() != null)
					invoice.setRecpri13(businessInvoice.getRecpri13());
				if (businessInvoice.getRecpri14() != null)
					invoice.setRecpri14(businessInvoice.getRecpri14());
				if (businessInvoice.getRecpri15() != null)
					invoice.setRecpri15(businessInvoice.getRecpri15());
				if (businessInvoice.getSalevat() != null)
					invoice.setSalevat(businessInvoice.getSalevat());
				if (businessInvoice.getVehtax() != null)
					invoice.setVehtax(businessInvoice.getVehtax());
				if (businessInvoice.getInvalue() != null)
					invoice.setInvalue(businessInvoice.getInvalue());
				if (businessInvoice.getStype() != null)
					invoice.setStype(businessInvoice.getStype().trim());
				if (businessInvoice.getCredagno() != null)
					invoice.setCredagno(businessInvoice.getCredagno().trim());
				if (businessInvoice.getVatexno() != null)
					invoice.setVatexno(businessInvoice.getVatexno().trim());
				if (businessInvoice.getVatexdat() != null)
					invoice.setVatexdat(businessInvoice.getVatexdat());
				if (businessInvoice.getStatus() != null)
					invoice.setStatus(businessInvoice.getStatus().trim());
				if (businessInvoice.getProgress() != null)
					invoice.setProgress(businessInvoice.getProgress().trim());
				if (businessInvoice.getDeldate() != null)
					invoice.setDeldate(businessInvoice.getDeldate());
				if (businessInvoice.getDelnote() != null)
					invoice.setDelnote(businessInvoice.getDelnote().trim());
				if (businessInvoice.getCredate() != null)
					invoice.setCredate(businessInvoice.getCredate());
				if (businessInvoice.getUserid() != null)
					invoice.setUserid(businessInvoice.getUserid().trim());
				if (businessInvoice.getTotcost() != null)
					invoice.setTotcost(businessInvoice.getTotcost());
				if (businessInvoice.getTotnrcst() != null)
					invoice.setTotnrcst(businessInvoice.getTotnrcst());
				if (businessInvoice.getVehcost() != null)
					invoice.setVehcost(businessInvoice.getVehcost());
				if (businessInvoice.getInvacc() != null)
					invoice.setInvacc(businessInvoice.getInvacc().trim());
				if (businessInvoice.getInvname() != null)
					invoice.setInvname(businessInvoice.getInvname().trim());
				if (businessInvoice.getCusacc() != null)
					invoice.setCusacc(businessInvoice.getCusacc().trim());
				if (businessInvoice.getCusname() != null)
					invoice.setCusname(businessInvoice.getCusname().trim());
				invoice.setMagic(businessInvoice.getId());
			} catch (IllegalAccessException | InvocationTargetException e) {
				logger.error("Une erreur s'est produite lors de la conversion des objets métiers Deposit", e);
			}
		}
		return invoice;
	}

	public List<Invoice> transform(List<ma.inetum.brique.model.metier.Invoice> list) {
		List<Invoice> depositList = null;
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

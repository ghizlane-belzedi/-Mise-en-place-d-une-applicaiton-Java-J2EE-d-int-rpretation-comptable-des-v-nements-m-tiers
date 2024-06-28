package ma.inetum.brique.repository.metier;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import ma.inetum.brique.model.metier.Deposit;

@Component
public class DepositDAO {
	
	
	@PersistenceContext(unitName = "Metier")
	private EntityManager entityManager;
	private final static Logger logger = LoggerFactory.getLogger(DepositDAO.class);

	public List<Deposit> findAllForBatch() {

		List<Deposit> depositList = null;
		String query = "select MAGIC, DOCNUM, DOCDATE, LNKCOMP, ORDNUM, ORDDATE, CUSTCODE, CUSTNAME, ACCOUNT,"
				     + "       ACCNAME, VSCOMPID, VEHICLE, CHASSIS, FRAN, FRANDESC, MODEL, MODDESC, VARIANT,"
				     + "       VARDESC, PAYMCODE, DEPREF, DEPVALUE, VATVALUE, VATCODE, STAMPDUT, SELLPRIC,"
				     + "       CREDATE, USERID, SEQUENCE, LOCATION "
				     + "  from V_CS_ME_CDDEP v"
				     + " where CREDATE = CAST(GETDATE() - 1 AS DATE)"
				     ;
		Query statement = entityManager.createNativeQuery(query);
	    logger.info("Deposit Query : " + query);
		List<Object[]> results = statement.getResultList();
		if ((results != null) && (!results.isEmpty())) {
			depositList = new ArrayList<>();
			for (Object[] result : results) {
				Deposit deposit = convert(result);
				depositList.add(deposit);
			}			
		}
		return depositList;
	}

	private Deposit convert(Object[] result) {
		Deposit deposit = null; 
		if (result != null) {
			deposit = new Deposit();
			deposit.setMagic(result[0]!=null?((BigDecimal)result[0]).longValue():null);
			deposit.setDocnum(result[1]!=null?((BigDecimal)result[1]).longValue():null);
			deposit.setDocdate(result[2]!=null?((java.sql.Date)result[2]).toLocalDate():null);
			deposit.setLnkcomp(result[3]!=null?(String)result[3]:null);
			deposit.setOrdnum(result[4]!=null?((BigDecimal)result[4]).longValue():null);
			deposit.setOrddate(result[5]!=null?((java.sql.Date)result[5]).toLocalDate():null);
			deposit.setCustcode(result[6]!=null?((BigDecimal)result[6]).longValue():null);
			deposit.setCustname(result[7]!=null?(String)result[7]:null);
			deposit.setAccount(result[8]!=null?(String)result[8]:null);
			deposit.setAccname(result[9]!=null?(String)result[9]:null);
			deposit.setVscompid(result[10]!=null?(String)result[10]:null);
			deposit.setVehicle(result[11]!=null?((BigDecimal)result[11]).longValue():null);
			deposit.setChassis(result[12]!=null?(String)result[12]:null);
			deposit.setFran(result[13]!=null?String.valueOf((Character)result[13]):null);
			deposit.setFrandesc(result[14]!=null?(String)result[14]:null);
			deposit.setModel(result[15]!=null?(String)result[15]:null);
			deposit.setModdesc(result[16]!=null?(String)result[16]:null);
			deposit.setVariant(result[17]!=null?(String)result[17]:null);
			deposit.setVardesc(result[18]!=null?(String)result[18]:null);
			deposit.setPaymcode(result[19]!=null?(String)result[19]:null);
			deposit.setDepref(result[20]!=null?(String)result[20]:null);
			deposit.setDepvalue(result[21]!=null?((BigDecimal)result[21]).doubleValue():null);
			deposit.setVatvalue(result[22]!=null?((BigDecimal)result[22]).doubleValue():null);
			deposit.setVatcode(result[23]!=null?String.valueOf((Character)result[23]):null);
			deposit.setStampdut(result[24]!=null?((BigDecimal)result[24]).doubleValue():null);
			deposit.setSellprice(result[25]!=null?((BigDecimal)result[25]).doubleValue():null);			
			deposit.setCreationDate(result[26]!=null?((java.sql.Date)result[26]).toLocalDate():null);
			deposit.setUserId(result[27]!=null?(String)result[27]:null);
			deposit.setSequence(result[28]!=null?((BigDecimal)result[28]).longValue():null);			
			deposit.setLocation(result[29]!=null?(String)result[29]:null);
		}
		return deposit;
	}

}

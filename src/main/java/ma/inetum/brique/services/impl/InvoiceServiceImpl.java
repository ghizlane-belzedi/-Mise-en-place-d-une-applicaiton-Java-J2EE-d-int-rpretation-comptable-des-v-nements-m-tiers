package ma.inetum.brique.services.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.persistence.criteria.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import ma.inetum.brique.bean.PieceComptableView;
import ma.inetum.brique.mapper.PieceComptableMapper;
import ma.inetum.brique.model.principale.Chargement;
import ma.inetum.brique.model.principale.EcritureComptable;
import ma.inetum.brique.model.principale.Erreur;
import ma.inetum.brique.model.principale.Invoice;
import ma.inetum.brique.model.principale.ParamCompteFacturation;
import ma.inetum.brique.model.principale.Parametrage;
import ma.inetum.brique.model.principale.ParametrageClient;
import ma.inetum.brique.model.principale.PieceComptable;
import ma.inetum.brique.model.principale.Simulation;
import ma.inetum.brique.model.principale.SimulationInvoice;
import ma.inetum.brique.repository.metier.InvoiceRepository;
import ma.inetum.brique.repository.principale.ChargementRepository;
import ma.inetum.brique.repository.principale.EcritureComptableRepository;
import ma.inetum.brique.repository.principale.ErreurRepository;
import ma.inetum.brique.repository.principale.InvoicePRepository;
import ma.inetum.brique.repository.principale.ParametrageClientRepository;
import ma.inetum.brique.repository.principale.ParametrageRepository;
import ma.inetum.brique.repository.principale.PieceComptableRepository;
import ma.inetum.brique.repository.principale.SimulationInvoiceRepository;
import ma.inetum.brique.services.ClientService;
import ma.inetum.brique.services.CompteFacturationService;
import ma.inetum.brique.services.InvoiceService;
import ma.inetum.brique.utilities.Constantes;
import ma.inetum.brique.utilities.NumeroPieceService;
import ma.inetum.brique.utilities.PieceComptableTool;

@Service
public class InvoiceServiceImpl implements InvoiceService {
	private final static Logger logger = LoggerFactory.getLogger(InvoiceServiceImpl.class);
	@Autowired
	private InvoicePRepository invoiceRepository;

	@Autowired
	private ParametrageRepository parametrageRepository;
	@PersistenceContext(unitName = "Main")
	EntityManager entityManager;
	@Autowired
	PieceComptableRepository pieceComptableRepository;
	@Autowired
	EcritureComptableRepository ecritureComptableRepository;
	@Autowired
	ErreurRepository erreurRepository;

	@Autowired
	ChargementRepository chargementRepository;

	@Autowired
	private InvoiceRepository invoiceMRepository;

	@Autowired
	SimulationInvoiceRepository simulationRepository;
	@Autowired
	ParametrageClientRepository parametrageClientRepository;
	private PieceComptableMapper mapper = PieceComptableMapper.getInstance();
	@Autowired
	private NumeroPieceService numeroPieceService;

	@Autowired
	private CompteFacturationService compteFacturationService;
	@Autowired
	private ClientService clientService;

	@Override
	public <T> List<List<String>> generer(List<String> header, LocalDate date, Class<?> c) {
		String tableName = c.getAnnotation(Table.class).name();
		String query = String.format("select %s from %s ", String.join(", ", header), tableName);
		// query += " where v.DATE_CHARGEMENT <= '" + date + "' and (FLAG_ENVOI = 0 or
		// FLAG_ENVOI is null)";
		String format = "yyyy-MM-dd";
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
		query += " where CREDATE <= to_date('" + dtf.format(date) + "', '"+ format +"') and (FLAG_ENVOI = 0 or FLAG_ENVOI is null)";
		Query statement = entityManager.createNativeQuery(query);
		List<Object[]> results = statement.getResultList();
		List<List<String>> details = new ArrayList<>();
		for (Object[] result : results) {
			List<String> list = new LinkedList<>();
			for (Object value : result) {
				if (value != null) {
					list.add(value.toString());
				} else {
					list.add("");
				}
			}
			details.add(list);
		}
		return details;
	}
	@Override
	public <T> List<List<String>> generer(List<String> header, Long simulationId, Class<?> c) {
		String tableName = c.getAnnotation(Table.class).name();
		String tableSimulation = SimulationInvoice.class.getAnnotation(Table.class).name();
		String query = String.format("select %s from %s v, %s r ", String.join(", ", header), tableName, tableSimulation);
		query += " where r.VEHICULE_ID = v.ID and r.SIMULATION_ID = " + simulationId ;
		Query statement = entityManager.createNativeQuery(query);
		List<Object[]> results = statement.getResultList();
		List<List<String>> details = new ArrayList<>();
		for (Object[] result : results) {
			List<String> list = new LinkedList<>();
			for (Object value : result) {
				if (value != null) {
					list.add(value.toString());
				} else {
					list.add("");
				}
			}
			details.add(list);
		}
		return details;
	}
	@Override
	public <T> List<List<String>> generer(List<String> header, Long first, Long last, Class<?> c) {
		String tableName = c.getAnnotation(Table.class).name();
		String query = String.format("select %s from %s ", String.join(", ", header), tableName);
		query += " where magic between " + first + " and " + (last != null ? last : 0l);
		logger.info("Logistic operation Query : " + query);
		Query statement = entityManager.createNativeQuery(query);
		List<Object[]> results = statement.getResultList();
		List<List<String>> details = new ArrayList<>();
		for (Object[] result : results) {
			List<String> list = new LinkedList<>();
			for (Object value : result) {
				if (value != null) {
					list.add(value.toString());
				} else {
					list.add("");
				}
			}
			details.add(list);
		}
		return details;
	}

	public List<Invoice> findAllByDate(LocalDate date) {
		List<Invoice> invoices = new ArrayList<Invoice>();

		return invoices;
	}

	private List<Erreur> valider(List<Invoice> invoices, Map<String, Map<String, String>> parametrages) {
		List<Erreur> errors = new ArrayList<>();
		Integer i = 0;
		if (invoices.isEmpty()) {
			errors.add(new Erreur(0l, Long.valueOf(0), "La liste générer est vide "));
		}
		Map<Map<String, Boolean>, ParamCompteFacturation> comptesFacturation = compteFacturationService.getAllToMap();
		Map<Map<String, String>, ParametrageClient> clients = clientService.getAllToMap();
		
		for (Invoice invoice : invoices) {
			
			String site = invoice.getSelllocn() != null ? invoice.getSelllocn().trim() : null;
			String client = invoice.getCusacc() != null ? invoice.getCusacc().trim() : null;
			String marque = invoice.getFran() != null ? invoice.getFran().trim() : null;
			Boolean tva = invoice.getSalevat() != null && invoice.getSalevat() > 0 ? Boolean.TRUE : Boolean.FALSE;
			Map<String, String> clientKey = Map.of(site, client);
			Map<String, String> clientKeyD = Map.of(Constantes.DEFAULT_SITE, client);
			Map<String, Boolean> compteFacturationKey = Map.of(site, tva);
			
			if (marque == null) {
				errors.add(new Erreur(invoice.getMagic(), Long.valueOf(i), "La marque n'est pas renseignée"));
			} else if (parametrages.get("marque").get(marque) == null) {
				errors.add(new Erreur(invoice.getMagic(), Long.valueOf(i), "La marque (" + invoice.getFran() + ") n'est pas paramétrée "));
			}
			
			if (site == null) {
				errors.add(new Erreur(invoice.getMagic(), Long.valueOf(0), "Site n'est pas renseigné"));
			} else if (parametrages.get("site") == null
					|| parametrages.get("site").get(String.format("%03d", Integer.parseInt(invoice.getSelllocn().trim()))) == null) {
				errors.add(new Erreur(invoice.getMagic(), Long.valueOf(0),
						"Le Site (" + invoice.getSelllocn() + ") n'est pas paramétré "));
			}
			
			if (invoice.getCredate() == null) {
				errors.add(new Erreur(invoice.getMagic(), Long.valueOf(i), "La date de création est vide"));
			}
			if (invoice.getCusacc() == null) {
				errors.add(new Erreur(invoice.getMagic(), Long.valueOf(i), "Le code client est vide"));
			}

			if(invoice.getOpertype() == null) {
				errors.add(new Erreur(invoice.getMagic(), Long.valueOf(i), "Le type d'opération n'est pas renseigné"));
			}
			if(client == null ) {
				errors.add(new Erreur(invoice.getMagic(), Long.valueOf(i), "Le client n'est pas renseigné"));
			}
			if(!clients.containsKey(clientKey) && !clients.containsKey(clientKeyD)) {
				ParametrageClient defaultCustomer  = clientService.getDefaultCustomer();
				if (defaultCustomer == null) {
					errors.add(new Erreur(invoice.getMagic(), Long.valueOf(i), "Le client (" + client + ") n'est pas paramétré pour le site (" + site + ")"));					
				}
			}
			if(!comptesFacturation.containsKey(compteFacturationKey)) {
				errors.add(new Erreur(invoice.getMagic(), Long.valueOf(i), "Aucun compte TTC n'est pas paramétré pour le site (" + site + ")"));
			} else  {
				if(StringUtils.isEmpty(comptesFacturation.get(compteFacturationKey).getCompteClass3())) {
					errors.add(new Erreur(invoice.getMagic(), Long.valueOf(i), "Le compte class 3 n'est pas renseigné pour le site (" + site + ")"));
				}
				if(StringUtils.isEmpty(comptesFacturation.get(compteFacturationKey).getCompteClass4())) {
					errors.add(new Erreur(invoice.getMagic(), Long.valueOf(i), "Le compte class 4 n'est pas renseigné pour le site (" + site + ")"));
				}
				if(StringUtils.isEmpty(comptesFacturation.get(compteFacturationKey).getCompteGeneral())) {
					errors.add(new Erreur(invoice.getMagic(), Long.valueOf(i), "Le compte général n'est pas renseigné pour le site (" + site + ")"));
				}
			}
		}
		return errors;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Map<Boolean, Object> simuler(Simulation simulation, LocalDate dateArret) throws CloneNotSupportedException {

		Map<Boolean, Object> resultat = new HashMap<>();

		try {

			List<Invoice> invoices = invoiceRepository.findAllBy(dateArret);
			Map<String, Map<String, String>> parametrages = new HashMap<>();
			List<Parametrage> marques = parametrageRepository.findByCategorie("marque");
			List<Parametrage> sites = parametrageRepository.findByCategorie("site");

			parametrages.put("marque",
					marques != null
							? marques.stream()
									.collect(Collectors.toMap(Parametrage::getCodeMedtier, Parametrage::getCodeFinance, (elem1, elem2) -> elem2 ))
							: new HashMap<>());
			parametrages.put("site",
					sites != null
							? sites.stream()
									.collect(Collectors.toMap(Parametrage::getCodeMedtier, Parametrage::getCodeFinance, (elem1, elem2) -> elem2 ))
							: new HashMap<>());
			List<Erreur> erreurs = valider(invoices, parametrages);
			if (!erreurs.isEmpty()) {
				resultat.put(false, getErrorFormated(erreurs, simulation));
			} else {
				List<PieceComptableView> pieces = new ArrayList<>();
				List<SimulationInvoice> depositList = new ArrayList<>();
				List<PieceComptable> piecesInvoice = new ArrayList<>();
				List<PieceComptable> piecesStock = new ArrayList<>();
				List<PieceComptable> piecesAccompte = new ArrayList<>();
				Map<String, Long> mapNumeroPieces = new HashMap<>();
				Map<Map<String, Boolean>, ParamCompteFacturation> comptesFacturation = compteFacturationService.getAllToMap();
				Map<Map<String, String>, ParametrageClient> clients = clientService.getAllToMap();
				
				for (Invoice invoice : invoices) {
					depositList.add(new SimulationInvoice(invoice, simulation));
					String site_   = invoice.getSelllocn() != null ? invoice.getSelllocn().trim() : null;
					String client_ = invoice.getCusacc() != null ? invoice.getCusacc().trim() : null;
					String marque_ = invoice.getFran() != null ? invoice.getFran().trim() : null;
					Boolean tva    = invoice.getSalevat() != null && invoice.getSalevat() > 0 ? Boolean.TRUE : Boolean.FALSE;
					ParametrageClient client = clients.get(Map.of(site_, client_)) != null ? clients.get(Map.of(site_, client_)) : clients.get(Map.of(Constantes.DEFAULT_SITE, client_));
					if (client == null) {
						client = clientService.getDefaultCustomer();
					}
					ParamCompteFacturation compteFacturation = comptesFacturation.get(Map.of(site_, tva));
					if (client == null) {
						erreurs.add(new Erreur(invoice.getMagic(), 0l, "Le client (" + invoice.getCusacc()
								+ ") n'est pas définie pour le site (" + invoice.getSelllocn()+ " )") );
						continue;
					}
					List<EcritureComptable> ecritures = new ArrayList<>();
					PieceComptable piece = new PieceComptable(invoice.getInvdate(), invoice.getInvname()+"/"+invoice.getCustname(), simulation.getFlux(), simulation);
					if(invoice.getInvacc().equalsIgnoreCase(Constantes.DEFAULT_CUSTOMER_CODE) || invoice.getInvacc().startsWith("V")) {
						piece.setEnteteDoc(invoice.getCustname() );
					} else  {
//						piece.setEnteteDoc(invoice.getInvname() + "/" + invoice.getCustname() );
					}
//					PieceComptable piece = new PieceComptable(invoice.getInvdate(), invoice.getInvname()+"/"+invoice.getCustname(), simulation.getFlux(), simulation);
					piece.setEcritures(ecritures);

					String marque = parametrages.get("marque").get(marque_);
					String site = parametrages.get("site").get(site_);
					if (Constantes.INVOICE_SALE_TYPE_3.equals(invoice.getStype())) {
						site = Constantes.INVOICE_CONCESSIONNAIRE_SITE;
					}
					
					if (Constantes.INVOICE_TYPE_VENTE.equals(invoice.getOpertype()) && (invoice.getSalevat() == null
							|| invoice.getSalevat() == 0)) {
						piece.setCodeJournale(Constantes.FVN_EXO);
					} else if (Constantes.INVOICE_TYPE_VENTE.equals(invoice.getOpertype()) && invoice.getSalevat() != null && invoice.getSalevat() > 0) {
						piece.setCodeJournale(Constantes.FVN_TTC);
					} else if (Constantes.INVOICE_TYPE_CREDIT_NOTE.equals(invoice.getOpertype())
							&& invoice.getSalevat() != null && invoice.getSalevat() > 0) {
						piece.setCodeJournale(Constantes.AVN_TTC);
					} else if (invoice.getSalevat() == null || invoice.getSalevat() == 0) {
						piece.setCodeJournale(Constantes.AVN_EXO);
					}
					if(mapNumeroPieces.get(piece.getCodeJournale()) == null ) {
						Long lastnumer = numeroPieceService.getNumeroPiece(piece.getCodeJournale());
						mapNumeroPieces.put(piece.getCodeJournale(), lastnumer);
					} else {
						mapNumeroPieces.put(piece.getCodeJournale(), mapNumeroPieces.get(piece.getCodeJournale()) + 1l );
					}
					piece.setNumeroPiece(mapNumeroPieces.get(piece.getCodeJournale()) );
					

					EcritureComptable ecriture = new EcritureComptable(invoice.getInvoice() + "",
							invoice.getCustord() + "", invoice.getChassis() + "", invoice.getRegn() + "",
							invoice.getVehicle() + "", invoice.getCredagno() + "");
					ecriture.setRef7(
							invoice.getDeldate() != null ? invoice.getDeldate().format(Constantes.FORMAT_DATE) : "");
					ecriture.setRef8(invoice.getVatexdat() != null ? invoice.getVatexno().toString() : "");
					if (Constantes.INVOICE_TYPE_VENTE.equals(invoice.getOpertype())) {
						if (invoice.getSalevat() != null && invoice.getSalevat() > 0) {
							ecriture = (EcritureComptable) ecriture.clone();
							ecriture.setAccountDescription(Constantes.INVOICE_ACCOUNT_DESCRIPTION_TVA);
							ecriture.setMontantMAD(invoice.getSalevat());
							ecriture.setNcpt("445500.TCOL20");
							ecriture.setPiece(piece);
							ecriture.setSens('C');
							ecritures.add(ecriture);
						}
						if (invoice.getInvalue() != null && invoice.getInvalue() > 0) {
							ecriture = (EcritureComptable) ecriture.clone();
							ecriture.setAccountDescription(compteFacturation.getDesc1());
							ecriture.setMontantMAD(invoice.getInvalue());
							ecriture.setNcpt(String.join(".", compteFacturation.getCompteGeneral(), client.getCodeFinance()));
							ecriture.setSens('D');
							ecriture.setPiece(piece);
							ecritures.add(ecriture);							
						}
						if (invoice.getSalenet() != null && invoice.getSalenet() > 0) {
							BigDecimal saleNet = BigDecimal.valueOf(invoice.getSalenet());
							saleNet = saleNet
									.subtract(invoice.getRecpri1() !=null?BigDecimal.valueOf(invoice.getRecpri1()) :BigDecimal.ZERO)
									.subtract(invoice.getRecpri2() !=null?BigDecimal.valueOf(invoice.getRecpri2()) :BigDecimal.ZERO)
									.subtract(invoice.getRecpri3() !=null?BigDecimal.valueOf(invoice.getRecpri3()) :BigDecimal.ZERO)
									.subtract(invoice.getRecpri5() !=null?BigDecimal.valueOf(invoice.getRecpri5()) :BigDecimal.ZERO)
									.subtract(invoice.getRecpri6() !=null?BigDecimal.valueOf(invoice.getRecpri6()) :BigDecimal.ZERO)
									.subtract(invoice.getRecpri7() !=null?BigDecimal.valueOf(invoice.getRecpri7()) :BigDecimal.ZERO)
									.subtract(invoice.getRecpri8() !=null?BigDecimal.valueOf(invoice.getRecpri8()) :BigDecimal.ZERO)
//									.subtract(invoice.getRecpri9() !=null?BigDecimal.valueOf(invoice.getRecpri9()) :BigDecimal.ZERO)
									.subtract(invoice.getRecpri10()!=null?BigDecimal.valueOf(invoice.getRecpri10()):BigDecimal.ZERO)
									.subtract(invoice.getRecpri11()!=null?BigDecimal.valueOf(invoice.getRecpri11()):BigDecimal.ZERO)
									.subtract(invoice.getRecpri12()!=null?BigDecimal.valueOf(invoice.getRecpri12()):BigDecimal.ZERO)
									.subtract(invoice.getRecpri13()!=null?BigDecimal.valueOf(invoice.getRecpri13()):BigDecimal.ZERO)
									.subtract(invoice.getRecpri14()!=null?BigDecimal.valueOf(invoice.getRecpri14()):BigDecimal.ZERO)
									.subtract(invoice.getRecpri15()!=null?BigDecimal.valueOf(invoice.getRecpri15()):BigDecimal.ZERO)
									;
							ecriture = (EcritureComptable) ecriture.clone();
							ecriture.setAccountDescription(Constantes.INVOICE_ACCOUNT_VENTE_VN);
							ecriture.setMontantMAD(saleNet.doubleValue());
							if (invoice.getSalevat() != null && invoice.getSalevat() > 0) {
								ecriture.setNcpt("711120");
							} else {
								ecriture.setNcpt("711121");
							}
							ecriture.setCostCenter(String.join(".", site, "AVN", marque));
							ecriture.setPiece(piece);
							ecriture.setSens('C');
							ecritures.add(ecriture);
						}
						if (invoice.getRecpri2() != null && invoice.getRecpri2() > 0) {
							ecriture = (EcritureComptable) ecriture.clone();
							ecriture.setAccountDescription(Constantes.INVOICE_ACCOUNT_FRAIX_CONVOYAGE);
							ecriture.setMontantMAD(invoice.getRecpri2());
							ecriture.setNcpt("712460");
							ecriture.setCostCenter(String.join(".", site, "AVN", marque));
							ecriture.setPiece(piece);
							ecriture.setSens('C');
							ecritures.add(ecriture);
						}
						if (invoice.getRecpri5() != null && invoice.getRecpri5() > 0) {
							ecriture = (EcritureComptable) ecriture.clone();
							ecriture.setAccountDescription(Constantes.INVOICE_ACCOUNT_MISE_EN_CIRCULATION);
							ecriture.setMontantMAD(invoice.getRecpri5());
							ecriture.setNcpt("712450");
							ecriture.setCostCenter(String.join(".", site, "AVN", marque));
							ecriture.setPiece(piece);
							ecriture.setSens('C');
							ecritures.add(ecriture);
						}
						if ((invoice.getRecpri3() != null) && (invoice.getRecpri3() > 0d)) {
							if (Constantes.INVOICE_FRAIX_WW < invoice.getRecpri3()) {
								//Frais WW
								ecriture = (EcritureComptable) ecriture.clone();
								ecriture.setAccountDescription(Constantes.INVOICE_ACCOUNT_FRAIX_WW);
								ecriture.setMontantMAD(Constantes.INVOICE_FRAIX_WW);
								ecriture.setNcpt("348801");
								ecriture.setPiece(piece);
								ecriture.setSens('C');
								ecritures.add(ecriture);
								
								//Frais Carte Grise
								ecriture = (EcritureComptable) ecriture.clone();
								ecriture.setAccountDescription(Constantes.INVOICE_ACCOUNT_FRAIX_IMMATRICULATION);
								ecriture.setMontantMAD(invoice.getRecpri3() - Constantes.INVOICE_FRAIX_WW);
								ecriture.setNcpt("448800");
								ecriture.setPiece(piece);
								ecriture.setSens('C');
								ecritures.add(ecriture);
								
							} else {
								
								//Frais Carte Grise
								ecriture = (EcritureComptable) ecriture.clone();
								ecriture.setAccountDescription(Constantes.INVOICE_ACCOUNT_FRAIX_IMMATRICULATION);
								ecriture.setMontantMAD(invoice.getRecpri3());
								ecriture.setNcpt("448800");
								ecriture.setPiece(piece);
								ecriture.setSens('C');
								ecritures.add(ecriture);
								
							}
						} 
						if (invoice.getVehtax() != null && invoice.getVehtax() > 0) {
							ecriture = (EcritureComptable) ecriture.clone();
							ecriture.setAccountDescription(Constantes.INVOICE_ACCOUNT_TAXE_VOITURE_LUXE);
							ecriture.setMontantMAD(invoice.getVehtax());
							ecriture.setNcpt("448800");
							ecriture.setPiece(piece);
							ecriture.setSens('C');
							ecritures.add(ecriture);
						}
							/*
							double totalCredit = ecritures.stream()
									.filter(e -> e.getSens() == 'C')
									.mapToDouble(e -> e.getMontantMAD())
									.sum();
							double totalDebit= ecritures.stream()
									.filter(e -> e.getSens() == 'D')
									.mapToDouble(e -> e.getMontantMAD())
									.sum();
							if(totalCredit != totalDebit) {
								erreurs.add(new Erreur(invoice.getMagic(), 0l, "Total des montants en crédit (" + totalCredit+ ") différent du total des montants en débit (" + totalDebit + ")"));
							} else {*/
								piece.setEcritures(ecritures);
								PieceComptableTool pcTool = new PieceComptableTool(piece, invoice.getMagic());
								if (!pcTool.isPieceValide()) {
									erreurs.addAll(pcTool.getErreurs());
								}								
								piecesInvoice.add(piece);
							//}
						
						//Impact Stock
						Double mnt = invoice.getVehcost() != null ? invoice.getVehcost() : 0d;
						mnt = invoice.getTotnrcst() != null ? (mnt + invoice.getTotnrcst()) : mnt;
						if(mnt > 0) {
							piece = (PieceComptable) piece.clone();
							ecritures = new ArrayList<>();
							piece.setCodeJournale(Constantes.CODE_JRNL_STOCK_VHL);
							piece.setEnteteDoc(Constantes.STOCK_VEHICULE_NEUF);
							if(mapNumeroPieces.get(piece.getCodeJournale()) == null ) {
								Long lastnumer = numeroPieceService.getNumeroPiece(piece.getCodeJournale());
								mapNumeroPieces.put(piece.getCodeJournale(), lastnumer);
							} else {
								mapNumeroPieces.put(piece.getCodeJournale(), mapNumeroPieces.get(piece.getCodeJournale()) + 1l );
							}
							piece.setNumeroPiece(mapNumeroPieces.get(piece.getCodeJournale()) );
							ecriture = (EcritureComptable) ecriture.clone();
							ecriture.setAccountDescription(Constantes.STOCK_VEHICULE_NEUF);
							ecriture.setMontantMAD(mnt);
							ecriture.setCostCenter(String.join(".", site, "AVN", marque));
							ecriture.setNcpt("311100");
							ecriture.setPiece(piece);
							ecriture.setSens('C');
							ecritures.add(ecriture);
							ecriture = (EcritureComptable) ecriture.clone();
							ecriture.setAccountDescription(Constantes.INVOICE_ACCOUNT_VARIATION_STOCK);
							ecriture.setMontantMAD(invoice.getTotcost());
							ecriture.setNcpt("611400");
							ecriture.setCostCenter(String.join(".", site, "AVN", marque));
							ecriture.setPiece(piece);
							ecriture.setSens('D');
							ecritures.add(ecriture);
							piece.setEcritures(ecritures);
							
							PieceComptableTool pcToolStock = new PieceComptableTool(piece, invoice.getMagic());
							if (!pcToolStock.isPieceValide()) {
								erreurs.addAll(pcToolStock.getErreurs());
							}
							
							piecesStock.add(piece);
						}
						
						/*
						 * Impact accompte client A revoir plus tard
						 * 
						final ParametrageClient finalClient = client;
						Specification<EcritureComptable> specification = (root, query, criteriaBuilder) -> {
							List<Predicate> predicates = new ArrayList<>();
							predicates.add(criteriaBuilder.equal(root.join("piece").join("flux").get("code"),
									Constantes.FLUX_DEPOSIT));
							predicates.add(criteriaBuilder.equal(root.join("piece").get("sent"), true));
							predicates.add(criteriaBuilder.like(root.get("ncpt"), compteFacturation.getCompteClass4() + "%"));							
							predicates.add(criteriaBuilder.equal(root.get("ref2"), invoice.getCustord()));
							predicates.add(criteriaBuilder.equal(root.get("ref5"), invoice.getVehicle()));
							return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
						};
						List<EcritureComptable> list = ecritureComptableRepository.findAll(specification);
						if ((list != null) && (!list.isEmpty())) {
							piece = (PieceComptable) piece.clone();
							ecritures = new ArrayList<>();
							piece.setEnteteDoc(Constantes.STOCK_VEHICULE_NEUF);
							piece.setCodeJournale(Constantes.CODE_JRNL_DEPOSIT_VHL);
							piece.setEnteteDoc("Acompte client " + invoice.getCusacc() + "(" + invoice.getCustname() + ")");
							if(mapNumeroPieces.get(piece.getCodeJournale()) == null ) {
								Long lastnumer = numeroPieceService.getNumeroPiece(piece.getCodeJournale());
								mapNumeroPieces.put(piece.getCodeJournale(), lastnumer);
							} else {
								mapNumeroPieces.put(piece.getCodeJournale(), mapNumeroPieces.get(piece.getCodeJournale()) + 1l );
							}
							piece.setNumeroPiece(mapNumeroPieces.get(piece.getCodeJournale()) );
							for (EcritureComptable e : list) {
								ecriture = (EcritureComptable) ecriture.clone();
								ecriture.setAccountDescription(Constantes.INVOICE_CLIENT_ACCOMPTE);
								ecriture.setMontantMAD(e.getMontantMAD());
								ecriture.setNcpt(e.getNcpt());
								ecriture.setPiece(piece);
								ecriture.setSens('D');
								ecritures.add(ecriture);
								ecriture = (EcritureComptable) ecriture.clone();
								ecriture.setAccountDescription("Client " + invoice.getCustname());
								ecriture.setMontantMAD(e.getMontantMAD());
								ecriture.setNcpt(String.join(".", compteFacturation.getCompteClass3(), client.getCodeFinance()));								
								ecriture.setPiece(piece);
								ecriture.setSens('C');
								ecritures.add(ecriture);
							}
							piece.setEcritures(ecritures);
							PieceComptableTool pcToolAcompte = new PieceComptableTool(piece, invoice.getMagic());
							if (!pcToolAcompte.isPieceValide()) {
								erreurs.addAll(pcToolAcompte.getErreurs());
							}
													
							piecesAccompte.add(piece);
						}*/
					} else if (Constantes.INVOICE_TYPE_CREDIT_NOTE.equals(invoice.getOpertype())) {
						if (invoice.getSalevat() != null && invoice.getSalevat() > 0) {
							ecriture = new EcritureComptable(invoice.getCrnno() + "",
									invoice.getCustord()+ "", invoice.getChassis(),
									invoice.getRegn()+ "", invoice.getVehicle()+ "",invoice.getCredagno()+ ""
									);

							ecriture.setAccountDescription(Constantes.INVOICE_ACCOUNT_DESCRIPTION_TVA);
							ecriture.setMontantMAD(invoice.getSalevat());
							ecriture.setNcpt("445500.TCOL20");
							ecriture.setPiece(piece);
							ecriture.setSens('D');
							ecritures.add(ecriture);
						}
						ecriture = new EcritureComptable(invoice.getCrnno() + "",
								invoice.getCustord() + "", invoice.getChassis() + "", invoice.getRegn() + "",
								invoice.getVehicle() + "", invoice.getCredagno() + "");
						if (invoice.getInvalue() != null && invoice.getInvalue() > 0) {
							ecriture = (EcritureComptable) ecriture.clone();
							ecriture.setAccountDescription("Client " + invoice.getCusname());
							ecriture.setMontantMAD(invoice.getInvalue());
							ecriture.setNcpt(String.join(".", compteFacturation.getCompteGeneral(), client.getCodeFinance()));
							ecriture.setSens('C');
							ecriture.setPiece(piece);
							ecritures.add(ecriture);
						}
						if (invoice.getSalenet() != null && invoice.getSalenet() > 0) {
							ecriture = (EcritureComptable) ecriture.clone();
							ecriture.setAccountDescription(Constantes.INVOICE_ACCOUNT_VENTE_VN);
							BigDecimal saleNet = BigDecimal.valueOf(invoice.getSalenet());
							saleNet = saleNet
									.subtract(invoice.getRecpri1() !=null?BigDecimal.valueOf(invoice.getRecpri1()) :BigDecimal.ZERO)
									.subtract(invoice.getRecpri2() !=null?BigDecimal.valueOf(invoice.getRecpri2()) :BigDecimal.ZERO)
									.subtract(invoice.getRecpri3() !=null?BigDecimal.valueOf(invoice.getRecpri3()) :BigDecimal.ZERO)
									.subtract(invoice.getRecpri5() !=null?BigDecimal.valueOf(invoice.getRecpri5()) :BigDecimal.ZERO)
									.subtract(invoice.getRecpri6() !=null?BigDecimal.valueOf(invoice.getRecpri6()) :BigDecimal.ZERO)
									.subtract(invoice.getRecpri7() !=null?BigDecimal.valueOf(invoice.getRecpri7()) :BigDecimal.ZERO)
									.subtract(invoice.getRecpri8() !=null?BigDecimal.valueOf(invoice.getRecpri8()) :BigDecimal.ZERO)
//									.subtract(invoice.getRecpri9() !=null?BigDecimal.valueOf(invoice.getRecpri9()) :BigDecimal.ZERO)
									.subtract(invoice.getRecpri10()!=null?BigDecimal.valueOf(invoice.getRecpri10()):BigDecimal.ZERO)
									.subtract(invoice.getRecpri11()!=null?BigDecimal.valueOf(invoice.getRecpri11()):BigDecimal.ZERO)
									.subtract(invoice.getRecpri12()!=null?BigDecimal.valueOf(invoice.getRecpri12()):BigDecimal.ZERO)
									.subtract(invoice.getRecpri13()!=null?BigDecimal.valueOf(invoice.getRecpri13()):BigDecimal.ZERO)
									.subtract(invoice.getRecpri14()!=null?BigDecimal.valueOf(invoice.getRecpri14()):BigDecimal.ZERO)
									.subtract(invoice.getRecpri15()!=null?BigDecimal.valueOf(invoice.getRecpri15()):BigDecimal.ZERO)
									;
							
							ecriture.setMontantMAD(saleNet.doubleValue());
							if (invoice.getSalevat() != null && invoice.getSalevat() > 0) {
								ecriture.setNcpt("711120");
							} else {
								ecriture.setNcpt("711121");
							}
							ecriture.setCostCenter(String.join(".", site, "AVN", marque));
							ecriture.setPiece(piece);
							ecriture.setSens('D');
							ecritures.add(ecriture);
						}
						if (invoice.getRecpri2() != null && invoice.getRecpri2() > 0) {
							ecriture = (EcritureComptable) ecriture.clone();
							ecriture.setAccountDescription(Constantes.INVOICE_ACCOUNT_FRAIX_CONVOYAGE);
							ecriture.setMontantMAD(invoice.getRecpri2());
							ecriture.setNcpt("712460");
							ecriture.setCostCenter(String.join(".", site, "AVN", marque));
							ecriture.setPiece(piece);
							ecriture.setSens('D');
							ecritures.add(ecriture);
						}
						if (invoice.getRecpri5() != null && invoice.getRecpri5() > 0) {
							ecriture = (EcritureComptable) ecriture.clone();
							ecriture.setAccountDescription(Constantes.INVOICE_ACCOUNT_MISE_EN_CIRCULATION);
							ecriture.setMontantMAD(invoice.getRecpri5());
							ecriture.setNcpt("712450");
							ecriture.setCostCenter(String.join(".", site, "AVN", marque));
							ecriture.setPiece(piece);
							ecriture.setSens('D');
							ecritures.add(ecriture);
						}
						if ((invoice.getRecpri3() != null) && (invoice.getRecpri3() > 0d)) {
							if (Constantes.INVOICE_FRAIX_WW < invoice.getRecpri3()) {
								//Frais WW
								ecriture = (EcritureComptable) ecriture.clone();
								ecriture.setAccountDescription(Constantes.INVOICE_ACCOUNT_FRAIX_WW);
								ecriture.setMontantMAD(Constantes.INVOICE_FRAIX_WW);
								ecriture.setNcpt("348801");
								ecriture.setPiece(piece);
								ecriture.setSens('D');
								ecritures.add(ecriture);
								
								//Frais Carte Grise
								ecriture = (EcritureComptable) ecriture.clone();
								ecriture.setAccountDescription(Constantes.INVOICE_ACCOUNT_FRAIX_IMMATRICULATION);
								ecriture.setMontantMAD(invoice.getRecpri3() - Constantes.INVOICE_FRAIX_WW);
								ecriture.setNcpt("448800");
								ecriture.setPiece(piece);
								ecriture.setSens('D');
								ecritures.add(ecriture);
								
							} else {
								
								//Frais Carte Grise
								ecriture = (EcritureComptable) ecriture.clone();
								ecriture.setAccountDescription(Constantes.INVOICE_ACCOUNT_FRAIX_IMMATRICULATION);
								ecriture.setMontantMAD(invoice.getRecpri3());
								ecriture.setNcpt("448800");
								ecriture.setPiece(piece);
								ecriture.setSens('D');
								ecritures.add(ecriture);
								
							}
						}						
						if (invoice.getVehtax() != null && invoice.getVehtax() > 0) {
							ecriture = (EcritureComptable) ecriture.clone();
							ecriture.setAccountDescription(Constantes.INVOICE_ACCOUNT_TAXE_VOITURE_LUXE);
							ecriture.setMontantMAD(invoice.getVehtax());
							ecriture.setNcpt("448800");
							ecriture.setPiece(piece);
							ecriture.setSens('D');
							ecritures.add(ecriture);
						}
						if(ecritures.size() == 0) {
							erreurs.add(new Erreur(invoice.getMagic(), 0l, "Cette pièce comptable ne posséde aucune écriture"));
						} else {
//							double totalCredit = ecritures.stream()
//									.filter(e -> e.getSens() == 'C')
//									.mapToDouble(e -> e.getMontantMAD())
//									.sum();
//							double totalDebit= ecritures.stream()
//									.filter(e -> e.getSens() == 'D')
//									.mapToDouble(e -> e.getMontantMAD())
//									.sum();
							piece.setEcritures(ecritures);
							PieceComptableTool pcTool = new PieceComptableTool(piece, invoice.getMagic());
							if (!pcTool.isPieceValide()) {
								erreurs.addAll(pcTool.getErreurs());
								continue;
							}
							piece.setEcritures(ecritures);
							piecesInvoice.add(piece);
//							pieces.add(mapper.entityTobean(piece));
							
						}
						if(invoice.getTotcost() != null && invoice.getTotcost() > 0) {
							piece = (PieceComptable) piece.clone();
							ecritures = new ArrayList<>();
							piece.setCodeJournale(Constantes.CODE_JRNL_STOCK_VHL);
							piece.setEnteteDoc(Constantes.STOCK_VEHICULE_NEUF);
							if(mapNumeroPieces.get(piece.getCodeJournale()) == null ) {
								Long lastnumer = numeroPieceService.getNumeroPiece(piece.getCodeJournale());
								mapNumeroPieces.put(piece.getCodeJournale(), lastnumer);
							} else {
								mapNumeroPieces.put(piece.getCodeJournale(), mapNumeroPieces.get(piece.getCodeJournale()) + 1l );
							}
							piece.setNumeroPiece(mapNumeroPieces.get(piece.getCodeJournale()) );
							ecriture = (EcritureComptable) ecriture.clone();
							ecriture.setAccountDescription(Constantes.STOCK_VEHICULE_NEUF);
							ecriture.setMontantMAD(invoice.getTotcost());
							ecriture.setCostCenter(String.join(".", site, "AVN", marque));
							ecriture.setNcpt("311100");
							ecriture.setPiece(piece);
							ecriture.setSens('D');
							ecritures.add(ecriture);
							ecriture = (EcritureComptable) ecriture.clone();
							ecriture.setAccountDescription(Constantes.INVOICE_ACCOUNT_VARIATION_STOCK);
							ecriture.setMontantMAD(invoice.getTotcost());
							ecriture.setNcpt("611400");
							ecriture.setCostCenter(String.join(".", site, "AVN", marque));
							ecriture.setPiece(piece);
							ecriture.setSens('C');
							ecritures.add(ecriture);
							piece.setEcritures(ecritures);
							piecesStock.add(piece);
//							pieces.add(mapper.entityTobean(piece));
						}
					}
				}
				if (!erreurs.isEmpty()) {
					resultat.put(false, getErrorFormated(erreurs, simulation));
				} else {
					piecesInvoice.addAll(piecesAccompte);
					piecesInvoice.addAll(piecesStock);
					for(var p : piecesInvoice) {
						pieceComptableRepository.save(p);
						pieces.add(mapper.entityTobean(p));
						
					}
					simulationRepository.saveAll(depositList);
					simulationRepository.saveAll(depositList);
					resultat.put(true, pieces);
					resultat.put(true, pieces);
				}
			}
		} catch (Exception ex) {
			String errDescr = "L'erreur suivante s'est produite lors de la simulation : " + ex.getMessage() ;
			logger.error(errDescr, ex);
			List<Erreur> erreurs = new ArrayList<>();
			Erreur erreur = new Erreur(null, 0l, errDescr);
			erreur.setSimulation(simulation);
			erreurs.add(erreur);

			Map<String, List<String>> map = new HashMap<>();
			List<String> err = new ArrayList<>();
			err.add(errDescr);
			map.put("0", err);
			resultat.put(false, map);
		}
		return resultat;
	}

	public List<ma.inetum.brique.model.metier.Invoice> findAllForBatch(Long firstElementToLoad) {
		return invoiceMRepository.findAllForBatch(firstElementToLoad);
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public void saveAll(List<Invoice> list, Chargement newChargement) {
		Chargement chg = chargementRepository.getOne(newChargement.getId());
		for (Invoice invoice : list) {
			invoice.setChargement(chg);
		}
		invoiceRepository.saveAll(list);
	}
	@Transactional(value = "chainedTransactionManager")
	@Override
	public Boolean generer(Simulation simulation) {
		LocalDateTime now = LocalDateTime.now();
		List<Invoice> invoices = simulationRepository.findBySimulation(simulation);
		for (var invoice : invoices) {
			invoice.setDateEnvoi(now);
			invoice.setSent(true);
		}
		invoiceRepository.saveAll(invoices);
		return true;
	}

	@Override
	public void saveAll(List<Invoice> list) {
		// TODO Auto-generated method stub

	}
	private Map<String, List<String>> getErrorFormated(List<Erreur> erreurs, Simulation simulation) {
		Map<String, List<String>> map = new HashMap<>();
		if (!erreurs.isEmpty()) {
			simulation.setErreurs(erreurs);
			for (Erreur e : erreurs) {
				if (map.get(e.getMagic().toString()) == null) {
					map.put(e.getMagic().toString(), new ArrayList<>());
				}
				map.get(e.getMagic().toString()).add(e.getDescription());
				e.setSimulation(simulation);
			}
//			resultat.put(false, map);
		}
		return map;
	}
	@Override
	public List<Invoice> factureNotLivred() {
//		List<Invoice> invoices = invoiceRepository.findAllFacturedNotLivred();
  		return null;
	}
	
}

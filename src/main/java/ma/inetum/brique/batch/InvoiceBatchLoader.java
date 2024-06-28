package ma.inetum.brique.batch;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ma.inetum.brique.exception.ExceptionFonctionnelle;
import ma.inetum.brique.mapper.InvoiceMapper;
import ma.inetum.brique.model.principale.Chargement;
import ma.inetum.brique.model.principale.ChargementState;
import ma.inetum.brique.model.principale.Erreur;
import ma.inetum.brique.model.principale.Invoice;
import ma.inetum.brique.model.principale.ParametrageFLux;
import ma.inetum.brique.services.ChargementService;
import ma.inetum.brique.services.InvoiceService;
import ma.inetum.brique.services.ParametrageFluxService;
import ma.inetum.brique.utilities.Constantes;

@Component
public class InvoiceBatchLoader {

	@Autowired
	ParametrageFluxService paramFluxService;

	@Autowired
	ChargementService chargementService;

	@Autowired
	InvoiceService invoiceService;

	private final static Logger logger = LoggerFactory.getLogger(InvoiceBatchLoader.class);

	public void loadInvoice() throws ExceptionFonctionnelle {

		Chargement newChargement = null;
		try {

			ParametrageFLux flux = paramFluxService.findFluxByCode(Constantes.FLUX_INVOICE);
			if (flux != null) {
				Chargement chargement = chargementService.findLastChargementIdByFluxAndChargementState(flux, ChargementState.TERMINER_SUCCESS);
				Long firstElementToLoad = ((chargement != null)&&(chargement.getLast()!=null)) ? chargement.getLast() + 1l : 1l;

				newChargement = chargementService.initChargement(flux, firstElementToLoad);

				List<ma.inetum.brique.model.metier.Invoice> list = invoiceService.findAllForBatch(firstElementToLoad);
				if ((list == null) || (list.size() == 0)) {
					logger.info("AUCUN INVOICE A CHARGEE");
					newChargement = chargementService.updateChargementStatus(newChargement, ChargementState.EMPTY_LIST, null);
				} else {
					newChargement = chargementService.updateChargementFirstElement(newChargement, list.stream().sorted((v1, v2) -> v1.getId().compareTo(v2.getId()))
							.findFirst().get().getId());
					InvoiceMapper mapper = InvoiceMapper.getInstance();
					List<Invoice> invoices = new ArrayList<>();
					for (ma.inetum.brique.model.metier.Invoice d : list.stream().collect(Collectors.toList())) {
						Invoice invoice = new Invoice();
						invoice = mapper.transform(invoice, d);
						invoice.setDateChargement(LocalDateTime.now());
						invoice.setLoaded(true);
						invoices.add(invoice);
					}
					invoiceService.saveAll(invoices, newChargement);
					newChargement.setDateFinChargement(LocalDateTime.now());

					Long lastMagic = invoices.stream().sorted((v1, v2) -> -v1.getMagic().compareTo(v2.getMagic()))
							.findFirst().get().getMagic();
					newChargement = chargementService.updateChargementStatus(newChargement, ChargementState.TERMINER_SUCCESS, lastMagic);
				}

			} else {
				throw new ExceptionFonctionnelle("Flux inconnu '" + Constantes.FLUX_INVOICE+ "'");
			}

		} catch (Exception e) {
			logger.error("Une erreur s'est produite lors chargement du flux '" + Constantes.FLUX_INVOICE + "'", e);
			if ((newChargement != null) && (newChargement.getId() != null)) {
				Erreur erreur = new Erreur(0l, 0l, e.getMessage());
				erreur.setChargement(newChargement);
				newChargement.setErreurs(Arrays.asList(erreur));
				newChargement = chargementService.updateChargementStatus(newChargement, ChargementState.WITH_ERRORS, null);
			}
			throw new ExceptionFonctionnelle(
					"Une erreur s'est produite lors chargement du flux '" + Constantes.FLUX_INVOICE + "'", e);
		}

	}
}

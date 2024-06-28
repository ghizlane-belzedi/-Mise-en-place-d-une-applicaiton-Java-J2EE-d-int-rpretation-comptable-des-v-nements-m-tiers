package ma.inetum.brique.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ma.inetum.brique.bean.SchemaEditView;
import ma.inetum.brique.repository.principale.ParamCompteCostingRepository;
import ma.inetum.brique.repository.principale.ParametrageFluxRepository;

@Component

public class SchemaComptableValidator implements Validator{

	@Autowired
	ParametrageFluxRepository fluxRepository;
	@Autowired
	ParamCompteCostingRepository paramCompteCostingRepository;

	@Override
	public boolean supports(Class<?> clazz) {
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		SchemaEditView schema = (SchemaEditView) target;
		if(schema.getCode() == null || schema.getCode() == "") {
			errors.rejectValue("code", "", "Merci de renseigné le code");
		}
		if(schema.getDescription() == null || schema.getDescription() == "") {
			errors.rejectValue("description", "", "Merci de renseigné la description");
		}
		if(schema.getFournisseur() == null || schema.getFournisseur() == "") {
			errors.rejectValue("fournisseur", "", "Merci de renseigné le fournisseur");
		}
		if(schema.getCodeAnalyse() == null || schema.getCodeAnalyse() == "") {
			errors.rejectValue("codeAnalyse", "", "Merci de renseigné le code d'analuse");
		}
		if(!schema.getCompteDebiteurActif() && !schema.getCompteTvaActif() && !schema.getCompteCrediteurActif()) {
			errors.rejectValue("compteDebiteurActif", "", "Merci d'activer au moin l'un des comptes");
			errors.rejectValue("CompteTvaActif", "", "Merci d'activer au moin l'un des comptes");
			errors.rejectValue("CompteCrediteurActif", "", "Merci d'activer au moin l'un des comptes");
		}
		if(schema.getCompteDebiteurActif() ) {
			if(schema.getCompteDebiteur() == null || schema.getCompteDebiteur() == "") {
				errors.rejectValue("compteDebiteur", "", "Merci de renseigné le numéro de compte");
			}
//			if(schema.getAccountSens1() == null) {
//				errors.rejectValue("accountSens1", "", "Merci de renseigné le sens de compte");
//			}
		}
		if(schema.getCompteTvaActif() ) {
			if(schema.getCompteTva() == null || schema.getCompteTva() == "") {
				errors.rejectValue("compteTva", "", "Merci de renseigné le numéro de compte");
			}
//			if(schema.getAccountSens2() == null) {
//				errors.rejectValue("accountSens2", "", "Merci de renseigné le sens de compte");
//			}
		}
		if(schema.getCompteCrediteurActif() ) {
			if(schema.getCompteCrediteur() == null || schema.getCompteCrediteur() == "") {
				errors.rejectValue("accountNumber3", "", "Merci de renseigné le numéro de compte");
			}
//			if(schema.getAccountSens3() == null) {
//				errors.rejectValue("accountSens3", "", "Merci de renseigné le sens de compte");
//			}
		}
	}
	
}

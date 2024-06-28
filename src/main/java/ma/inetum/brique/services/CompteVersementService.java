package ma.inetum.brique.services;

import java.util.List;
import java.util.Map;

import ma.inetum.brique.bean.CompteVersementBean;
import ma.inetum.brique.exception.ExceptionFonctionnelle;
import ma.inetum.brique.model.principale.ParamCompteVersement;

public interface CompteVersementService {
   
	public List<CompteVersementBean> getAll();
    public void add(CompteVersementBean p);
    public CompteVersementBean findById(Long id) throws ExceptionFonctionnelle;
    public void edit(CompteVersementBean view) throws ExceptionFonctionnelle;
    public Map<Map<String, String>, ParamCompteVersement> getAllToMap();
}
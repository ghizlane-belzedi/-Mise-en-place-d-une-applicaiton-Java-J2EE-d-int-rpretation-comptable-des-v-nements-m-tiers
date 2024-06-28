package ma.inetum.brique.services;

import java.util.List;
import java.util.Map;

import ma.inetum.brique.bean.CompteFacturationBean;
import ma.inetum.brique.exception.ExceptionFonctionnelle;
import ma.inetum.brique.model.principale.ParamCompteFacturation;

public interface CompteFacturationService {
   
	public List<CompteFacturationBean> getAll();
    public void add(CompteFacturationBean p);
    public CompteFacturationBean findById(Long id) throws ExceptionFonctionnelle;
    public void edit(CompteFacturationBean view) throws ExceptionFonctionnelle;
    public Map<Map<String, Boolean>, ParamCompteFacturation> getAllToMap();
}
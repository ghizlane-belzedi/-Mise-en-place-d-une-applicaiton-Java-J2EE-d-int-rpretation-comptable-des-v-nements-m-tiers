package ma.inetum.brique.services;

import java.util.List;

import ma.inetum.brique.bean.UserAddDto;
import ma.inetum.brique.bean.UserAllDto;
import ma.inetum.brique.bean.UserEditDto;

public interface UserService {

	public List<UserAllDto> findByCriteria(String nom, String prenom, String statut, String role, int page, int size, String sort, String dir);
	public List<UserAllDto> getAll();
	public UserEditDto findById(Long id) ;
	public void add(UserAddDto contrat) ;
	public void edit(UserEditDto contrat) ;
	//public UserDetails findByUserName(String username);
}

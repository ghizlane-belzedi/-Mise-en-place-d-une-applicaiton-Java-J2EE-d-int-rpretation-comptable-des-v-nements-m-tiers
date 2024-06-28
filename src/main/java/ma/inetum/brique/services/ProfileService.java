package ma.inetum.brique.services;

import java.util.List;

import ma.inetum.brique.bean.ProfileAllDto;
import ma.inetum.brique.bean.ProfileEditDto;

public interface ProfileService {

	public List<ProfileAllDto> findByCriteria(String code, String descrption, String role, int page, int size, String sort, String dir);
	public List<ProfileAllDto> getAll();
	public ProfileEditDto findById(Long id) ;
	public void add(ProfileEditDto profile);
	public void edit(ProfileEditDto profile) ;
	public boolean deleteById(Long id) ;
}

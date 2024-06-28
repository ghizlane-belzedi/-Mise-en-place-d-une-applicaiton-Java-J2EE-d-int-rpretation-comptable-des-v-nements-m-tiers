package ma.inetum.brique.repository.principale;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ma.inetum.brique.model.principale.AppRole;

public interface AppRoleRepository extends JpaRepository<AppRole, Long>{

//	@Query("Select ur.appRole from AppUserRole ur where ur.appUser.userId = :userId")
	public List<AppRole> findAll();
	@Query("Select r from AppRole r where roleName= :code")
	public AppRole findByCode(String code);

}

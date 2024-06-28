package ma.inetum.brique.repository.principale;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ma.inetum.brique.model.principale.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long>, JpaSpecificationExecutor<AppUser>{

	@Query(value = "select u from AppUser u where userName = :userName")
	public AppUser findUserAccount(@Param("userName") String userName);

}

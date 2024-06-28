//package ma.inetum.brique.repository.principale;
//
//import java.util.List;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import ma.inetum.brique.model.principale.AppUser;
//import ma.inetum.brique.model.principale.AppUserRole;
//
//public interface AppUserRoleRepository extends JpaRepository<AppUserRole, Long>, JpaSpecificationExecutor<AppUserRole>{
//
//	@Query("select u from AppUserRole u where u.appUser.userId = :userId")
//	public List<AppUserRole> findByUserId(@Param("userId") Long userId);
//
//}

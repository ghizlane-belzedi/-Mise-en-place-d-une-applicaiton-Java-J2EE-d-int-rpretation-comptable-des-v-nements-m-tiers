package ma.inetum.brique.mapper;
//package ma.cnss.migration.mapper;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import org.springframework.jdbc.core.RowMapper;
//
//import ma.cnss.migration.model.UserInfo;
//
//public class UserInfoMapper implements RowMapper<UserInfo> {
//
//    @Override
//    public UserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
//
//        String userName = rs.getString("Username");
//        String password = rs.getString("Password");
//
//        return new UserInfo(userName, password);
//    }
//
//}
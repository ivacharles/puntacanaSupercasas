package iva.puntacana.supercasas.auth;


import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AppUserMapper implements RowMapper<AppUser> {

    @Override
    public AppUser mapRow(ResultSet rs, int i) throws SQLException {
        String username = rs.getString("username");
        String password = rs.getString("password");

        return new AppUser(username, password);
    }
}

package iva.puntacana.supercasas.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static iva.puntacana.supercasas.auth.AppUserRole.*;

@Repository
public class AppUserRepoImpl implements AppUserRepo{

    private final JdbcTemplate jdbcTemplate;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AppUserRepoImpl(JdbcTemplate jdbcTemplate, PasswordEncoder passwordEncoder) {
        this.jdbcTemplate = jdbcTemplate;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<UserDetails> selectApplicationAdminByUsername(String username) {
        String sql = "SELECT username, password FROM supercasas.userstable WHERE username = ?;";
        var obj = jdbcTemplate.queryForObject(sql, new AppUserMapper(), username);
        Optional<UserDetails> admin;
        assert obj != null;
        String pwd = obj.getPassword();
        obj.setPassword(passwordEncoder.encode(pwd));

         admin = Optional.of(User.builder()
                 .username(obj.getUsername())
                 .password(obj.getPassword())
                 .roles(ADMIN.name())
                 .accountExpired(false)
                 .accountLocked(false)
                 .credentialsExpired(false)
                 .disabled(false)
                 .build());
        return admin;
    }
}

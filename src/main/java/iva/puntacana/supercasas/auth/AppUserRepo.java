package iva.puntacana.supercasas.auth;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface AppUserRepo {

    Optional<UserDetails> selectApplicationAdminByUsername(String username);

}

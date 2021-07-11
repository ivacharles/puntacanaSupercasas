package iva.puntacana.supercasas.authTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import iva.puntacana.supercasas.auth.AppUser;
import iva.puntacana.supercasas.auth.AppUserService;
import iva.puntacana.supercasas.jwt.JwtRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
public class TestAuth {

    private final AppUserService appUserService;
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
//    private final UserTestDataFactory userTestDataFactory;
    private final WebApplicationContext context;


    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }

    private final String password = "Test12345_";

    @Autowired
    public TestAuth(AppUserService appUserService, MockMvc mockMvc, ObjectMapper objectMapper, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, WebApplicationContext context) {
        this.appUserService = appUserService;
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;

        this.context = context;
    }

    @DisplayName("database user exist")
    @Test
    public void T1() {
        AppUser expected = new AppUser(passwordEncoder.encode("1964chu"), "chu1964");
        var actual = appUserService.loadUserByUsername("chu1964");

        assertEquals(expected.getPassword(), actual.getPassword());
    }

    @DisplayName("loginTest")
    @Test
    public void T2() throws Exception {
        JwtRequest jwtRequest = new JwtRequest();
        jwtRequest.setPassword("1964chu");
        jwtRequest.setUsername("chu1964");
        String jsonRequest = objectMapper.writeValueAsString(jwtRequest);


    }
}
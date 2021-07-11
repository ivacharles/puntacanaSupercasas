package iva.puntacana.supercasas.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JwtTokenVerifier2 extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //get the authorization in the request header
        String authorizationHeader = request.getHeader("Authorization");
        //check for null or empty and deny the request
        if(Strings.isEmpty(authorizationHeader) || Strings.isBlank(authorizationHeader)|| !authorizationHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }
        //get the token and remove the bearer_ into it
        String token = authorizationHeader.replace("Bearer ", "");
        String secretKey = "secureKEyHasToBeVeryLongsecureKEyHasToBeVeryLongsecureKEyHasToBeVeryLongsecureKEyHasToBeVeryLongsecureKEyHasToBeVeryLongsecureKEyHasToBeVeryLongsecureKEyHasToBeVeryLongsecureKEyHasToBeVeryLong";

        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
                    .build().parseClaimsJws(token);

            Claims body = claimsJws.getBody();
            String username = body.getSubject();
            var authorities = (List<Map<String, String>>) body.get("authorities");
            //convert the string to simpleGrantedAuthority
            Set<SimpleGrantedAuthority> simpleGrantedAuthorities = authorities.stream().map(m -> new SimpleGrantedAuthority(m.get("authority"))).collect(Collectors.toSet());
            //authenticate the user base on these data from the token

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    simpleGrantedAuthorities//Collections.singleton(simpleGrantedAuthority) //this is for when you only have one authority not multiple
            );
            //tell security to approve the user and its authorities
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }catch (JwtException e){
            throw  new IllegalStateException(String.format("Token %s cannot be trusted",token));
        }
        filterChain.doFilter(request, response);
    }
}


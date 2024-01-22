package pl.chatApi.Controlers;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.chatApi.Config.AuthManager;
import pl.chatApi.Models.ChatUser;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Map;

@CrossOrigin
@RestController

public class AuthController {


    private final AuthManager authenticationManager;
    private final UserDetailServiceImplement userDetailServiceImplement;

    @Autowired
    public AuthController(AuthManager authenticationManager, UserDetailServiceImplement userDetailServiceImplement) {
        this.authenticationManager = authenticationManager;
        this.userDetailServiceImplement = userDetailServiceImplement;
    }

    @PostMapping(value = "/login", consumes = "application/json")
    public ResponseEntity<?> login(@RequestBody ChatUser user) {

        System.out.println(user.getUsername() + user.getPassword());
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            String token = Jwts.builder()
                    .setSubject(user.getUsername())
                    .signWith(SignatureAlgorithm.HS256, key())
                    .compact();
            return ResponseEntity.ok(Map.of("token", token, "message", "Authentication successful"));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(401).body(Map.of("error", "Authentication failed"));
        }

    }

    public Key key() {
        String base64Key = "/nwmbEe24SlrwOMCI/tJPfxUuBYZhDV151svTWQjQ0c="; // Przykładowy klucz Base64
        byte[] keyBytes = Base64.getDecoder().decode(base64Key);
        return new SecretKeySpec(keyBytes, "HmacSHA256");
    }
}

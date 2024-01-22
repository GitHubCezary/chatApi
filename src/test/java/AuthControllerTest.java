import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pl.chatApi.Config.AuthManager;
import pl.chatApi.Controlers.AuthController;
import pl.chatApi.Models.ChatUser;
import pl.chatApi.Services.UserDetailServiceImplement;

import java.util.ArrayList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthControllerTest {


    @Test
    public void test_Login_Successful_Auth() {
        // Przygotowanie danych testowych
        ChatUser user = new ChatUser("testUser", "password");
        Authentication authentication = Mockito.spy(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        UserDetails userDetails = new User(user.getUsername(), user.getPassword(), new ArrayList<>());

        // Utwórz instancjê AuthManager i UserDetailServiceImplement
        AuthManager authManagerMock = Mockito.mock(AuthManager.class);
        UserDetailServiceImplement userDetailServiceImplementMock = Mockito.mock(UserDetailServiceImplement.class);

        // Ustawienie zachowania mocków
        when(authManagerMock.authenticate(Mockito.any())).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(userDetails);

        // Utwórz instancjê AuthController rêcznie i wstrzyknij mocki
        AuthController authController = new AuthController(authManagerMock, userDetailServiceImplementMock);

        // Wywo³anie metody kontrolera
        ResponseEntity<?> response = authController.login(user);

        // Sprawdzenie oczekiwanej odpowiedzi
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        Map<String, Object> responseBody = (Map<String, Object>) response.getBody();
        assertTrue(responseBody.containsKey("token"));
        assertTrue(responseBody.containsKey("message"));
    }


    @Test
    public void test_Login_Failed_Auth() {
        // Przygotowanie danych testowych
        ChatUser user = new ChatUser("nonexistentUser", "wrongPassword");

        // Utwórz instancjê AuthManager i UserDetailServiceImplement
        AuthManager authManagerMock = Mockito.mock(AuthManager.class);
        UserDetailServiceImplement userDetailServiceImplementMock = Mockito.mock(UserDetailServiceImplement.class);

        // Ustawienie zachowania mocków
        when(authManagerMock.authenticate(Mockito.any(UsernamePasswordAuthenticationToken.class))).thenThrow(UsernameNotFoundException.class);

        // Utwórz instancjê AuthController rêcznie i wstrzyknij mocki
        AuthController authController = new AuthController(authManagerMock, userDetailServiceImplementMock);

        // Wywo³anie metody kontrolera
        ResponseEntity<?> response = authController.login(user);

        // Sprawdzenie oczekiwanej odpowiedzi
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().toString().contains("error"));
    }
}

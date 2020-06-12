import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import me.aborozdykh.cinema.exceptions.AuthenticationException;
import me.aborozdykh.cinema.models.User;
import me.aborozdykh.cinema.security.AuthenticationService;
import me.aborozdykh.cinema.security.impl.AuthenticationServiceImpl;
import me.aborozdykh.cinema.service.UserService;
import me.aborozdykh.cinema.util.HashUtilService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Optional;

public class AuthenticationServiceTest {
    private AuthenticationService authenticationService
            = new AuthenticationServiceImpl(null, null, null);

    @Test
    void login_OK() throws AuthenticationException {
        String email = "bob@i.ua";
        String password = "1234";
        var salt = new byte[]{};
        var expected = new User();
        expected.setEmail(email);
        expected.setPassword(password);
        expected.setId(1L);
        expected.setSalt(salt);

        UserService userService = Mockito.mock(UserService.class);
        Mockito.when(userService.findByEmail(email)).thenReturn(expected);

        HashUtilService hashUtilService = Mockito.mock(HashUtilService.class);
        Mockito.when(hashUtilService.hashPassword(password, salt)).thenReturn(password);

        authenticationService = new AuthenticationServiceImpl(userService, null, hashUtilService);
        var actual = authenticationService.login(email, password);

        assertNotNull(actual);
        assertEquals(expected.getEmail(), actual.getEmail());
        assertEquals(expected.getId(), actual.getId());
    }
}

package onlineShop.web;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import onlineShop.dto.AuthenticationRequest;
import onlineShop.dto.UserRegisterDto;
import onlineShop.exceptions.EntityNotFoundException;
import onlineShop.models.User;
import onlineShop.services.UserService;
import onlineShop.utils.JwtUtils;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final UserService userService;
    private final JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthenticationRequest authenticationRequest) {
        UserDetails user;

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
                    authenticationRequest.getPassword()));
            user = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        } catch (AuthenticationException exception) {
            return ResponseEntity.status(401).body(exception.getMessage());
        } catch (EntityNotFoundException exception) {
            return ResponseEntity.status(404).body(exception.getMessage());
        }

        return ResponseEntity.ok(jwtUtils.generateToken(user));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRegisterDto userRegisterDto) {
        User user;

        try {
            user = userService.registerUser(userRegisterDto);
        } catch (EntityNotFoundException exception) {
            return ResponseEntity.status(404).body(exception.getMessage());
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.status(409).body(exception.getMessage());
        }

        return ResponseEntity.ok(jwtUtils.generateToken(user));
    }
}

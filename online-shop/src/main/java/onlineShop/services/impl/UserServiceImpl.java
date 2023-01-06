package onlineShop.services.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import onlineShop.dto.UserRegisterDto;
import onlineShop.exceptions.EntityNotFoundException;
import onlineShop.models.User;
import onlineShop.repositories.UserRepository;
import onlineShop.services.UserService;
import onlineShop.utils.JwtUtils;
import onlineShop.utils.Mapper;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private static final String USER_ENTITY_NAME = "User";

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final Mapper mapper;
    private final JwtUtils jwtUtils;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        User user = this.getByUsername(username);
        return jwtUtils.getUserDetails(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User getByUsername(String username) {
        return userRepository.getUserByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(USER_ENTITY_NAME, "username", username));
    }

    @Override
    @Transactional
    public User registerUser(UserRegisterDto userRegisterDto) {
        if (!userRegisterDto.getPassword().equals(userRegisterDto.getConfirmPassword())) {
            throw new IllegalArgumentException("Password and confirm password does not match.");
        }

        User user = mapper.toUser(userRegisterDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }
}

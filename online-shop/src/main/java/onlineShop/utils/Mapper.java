package onlineShop.utils;

import java.util.UUID;

import org.springframework.stereotype.Component;

import lombok.NoArgsConstructor;
import onlineShop.dto.UserRegisterDto;
import onlineShop.models.User;

@Component
@NoArgsConstructor
public class Mapper {

    public User toUser(UserRegisterDto userRegisterDto) {
        User user = new User();

        user.setId(UUID.randomUUID());
        user.setFirstName(userRegisterDto.getFirstName());
        user.setLastName(userRegisterDto.getLastName());
        user.setUsername(userRegisterDto.getUsername());
        user.setEmail(userRegisterDto.getEmail());
        user.setPassword(userRegisterDto.getPassword());
        user.setActive(true);

        return user;
    }
}

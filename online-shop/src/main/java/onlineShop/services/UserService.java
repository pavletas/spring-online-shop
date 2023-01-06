package onlineShop.services;

import onlineShop.dto.UserRegisterDto;
import onlineShop.models.User;

public interface UserService {

    User getByUsername(String username);

    User registerUser(UserRegisterDto userRegisterDto);
}

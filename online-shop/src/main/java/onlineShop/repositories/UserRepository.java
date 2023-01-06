package onlineShop.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import onlineShop.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> getUserByUsername(String username);
}

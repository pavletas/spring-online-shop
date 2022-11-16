package onlineShop.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

    @NonNull
    @NotNull
    @Size(min = 2, max = 10)
    @Column(name = "first_name")
    private String firstName;

    @NonNull
    @NotNull
    @Size(min = 2, max = 10)
    @Column(name = "last_name")
    private String lastName;

    @NonNull
    @NotNull
    @Email
    @Column(name = "email")
    private String email;

    @NonNull
    @NotNull
    @Size(min = 2, max = 30)
    @Column(name = "username")
    private String username;

    @NonNull
    @NotNull
    @Size(min = 6)
    @Column(name = "password")
    private String password;

    @Column(name = "active")
    private boolean active;
}

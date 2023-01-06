package onlineShop.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
    @Size(min = 2, max = 20)
    @Column(name = "username")
    private String username;

    @NonNull
    @NotNull
    @Column(name = "password", columnDefinition = "TEXT")
    private String password;

    @Column(name = "active")
    private boolean active;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = {
                    @JoinColumn(name = "user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_users_roles_user")) },
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_users_roles_role")) })
    private Set<Role> roles = new HashSet<>();
}

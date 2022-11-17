package onlineShop.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "carts")
@Getter
@Setter
public class Cart extends BaseEntity {

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_carts_user"))
    private User user;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "carts_items",
            joinColumns = {
                    @JoinColumn(name = "cart_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_carts_items_cart")) },
            inverseJoinColumns = {
                    @JoinColumn(name = "item_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_carts_items_item")) })
    private Set<Item> items = new HashSet<>();
}

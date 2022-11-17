package onlineShop.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import onlineShop.enums.Size;

@Entity
@Table(name = "item_sizes", uniqueConstraints = {
        @UniqueConstraint(name = "item_size_unique_constraint", columnNames = { "item_id", "size" })
})
@Getter
@Setter
public class ItemSize extends BaseEntity {

    @Column(name = "stock_number")
    private int stockNumber;

    @NotNull
    @Column(name = "size")
    private Size size;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;
}

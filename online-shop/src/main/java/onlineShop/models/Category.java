package onlineShop.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "categories")
@Getter
@Setter
public class Category extends BaseEntity {

    @NotNull
    @Column(name = "name")
    private String name;

    @Column(name = "is_subcategory")
    private boolean isSubcategory;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "categories_subcategories",
            joinColumns = {
                    @JoinColumn(name = "category_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_categories_subcategories_category")) },
            inverseJoinColumns = {
                    @JoinColumn(name = "subcategory_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_categories_subcategories_subcategory")) })
    private Set<Category> subcategories;
}

package Repository;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Product {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    String name;
    String description;
    Double price;
    int amount;

    public Product(Integer id, String name, int amount) {
        this.id = id;
        this.name = name;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

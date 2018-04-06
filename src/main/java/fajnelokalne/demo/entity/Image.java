package fajnelokalne.demo.entity;

import io.springlets.format.EntityFormat;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "product")
@ToString(exclude = "product")
@EntityFormat("#{path}")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    Product product;

    @Column(nullable = false)
    private String path;

    public Image(Product product, String path) {
        this.path = path;
        this.product = product;
    }
}

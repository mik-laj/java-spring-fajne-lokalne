package fajnelokalne.demo.entity;

import io.springlets.format.EntityFormat;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "product")
@ToString(exclude = "product")
@EntityFormat("#{path}")
@EntityListeners(AuditingEntityListener.class)
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    Product product;

    private int value;

    @CreatedBy
    @ManyToOne
    protected User createdBy;

    public Vote(Product product, int value, User createdBy) {
        this.product = product;
        this.value = value;
        this.createdBy = createdBy;
    }
}

package fajnelokalne.demo.entity;

import io.springlets.format.EntityFormat;
import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityFormat("#{content}")
@EqualsAndHashCode(exclude = "id")
@ToString(exclude = "review")
public class ReviewAttribute {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    Review review;

    @Column
    String content;

    @Enumerated(EnumType.ORDINAL)
    Type type;

    public ReviewAttribute(String content, Type type) {
        this.content = content;
        this.type = type;
    }

    public enum Type {
        PROS,
        CONS
    }
}

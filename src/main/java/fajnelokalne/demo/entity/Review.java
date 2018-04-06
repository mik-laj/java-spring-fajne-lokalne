package fajnelokalne.demo.entity;


import io.springlets.format.EntityFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;
import static javax.persistence.TemporalType.TIMESTAMP;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityFormat("Review ##{id}")
@EqualsAndHashCode(exclude = "attributes")
@EntityListeners(AuditingEntityListener.class)
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @CreatedBy
    @ManyToOne
    protected User createdBy;

    @CreatedDate
    @Temporal(TIMESTAMP)
    protected Date creationDate;

    @LastModifiedBy
    @ManyToOne
    protected User lastModifiedBy;

    @LastModifiedDate
    @Temporal(TIMESTAMP)
    protected Date lastModifiedDate;

    @ManyToOne
    @JoinColumn(nullable = false)
    Product product;

    @Column(nullable = false)
    String content;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<ReviewAttribute> attributes = new HashSet<>();

    public List<ReviewAttribute> getCons() {
        return attributes.stream()
                .filter(d -> d.getType() == ReviewAttribute.Type.CONS)
                .collect(toList());
    }

    public List<ReviewAttribute> getPros() {
        return attributes.stream()
                .filter(d -> d.getType() == ReviewAttribute.Type.PROS)
                .collect(toList());
    }

    public void addAttribute(ReviewAttribute attribute){
        if(!attributes.contains(attribute)){
            attribute.setReview(this);
            attributes.add(attribute);
        }
    }

    public void removeAttribute(ReviewAttribute attribute) {
        attributes.stream()
                .filter(t -> t.equals(attribute))
                .findAny()
                .ifPresent(a -> {
                    a.setReview(null);
                    attributes.remove(a);
                });
    }

}

package fajnelokalne.demo.entity;

import io.springlets.format.EntityFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.*;

import static javax.persistence.TemporalType.TIMESTAMP;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
@EntityFormat("#{name}")
@EntityListeners(AuditingEntityListener.class)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @CreatedBy
    @ManyToOne
    protected User createdBy;

    @CreatedDate
    @Temporal(TIMESTAMP)
    protected Date creationDate;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Company company;

    @Column
    private String name;

    @Column
    private int voteCount = 0;

    @Column
    private int voteValue = 0;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Tag> tags = new HashSet<>();

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private Set<Image> images = new HashSet<>();

    @Column(columnDefinition="TEXT", nullable = false)
    private String description;

    public Product(String name, Company company) {
        this.name = name;
        this.company = company;
    }

    public void addTag(Tag tag){
        if(!containsTag(tag)){
            tags.add(tag);
        }
    }

    public void removeTag(Tag tag) {
        tags.stream()
                .filter(t -> t.getName().equals(tag.getName()))
                .findAny()
                .ifPresent(tag1 -> tags.remove(tag1));
    }


    private boolean containsTag(Tag tag) {
        return tags.stream()
                .anyMatch(t -> t.getName().equals(tag.getName()));
    }

    public float getVote() {
        if(voteCount == 0){
            return 0;
        }
        return voteValue / voteCount;
    }

    public int getVoteInt() {
        return (int) getVote();
    }

}

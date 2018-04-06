package fajnelokalne.demo.entity;

import io.springlets.format.EntityFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@EntityFormat("#{name}")
@EqualsAndHashCode(exclude = "id")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotNull
    @Column(unique = true)
    String name;

    public Tag(String name) {
        this.name = name;
    }


}

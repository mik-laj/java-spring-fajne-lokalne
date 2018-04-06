package fajnelokalne.demo.entity;

import io.springlets.format.EntityFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityFormat("#{name}")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    private City city;

    private String krs;

    public Company(String name, City city, String krs) {
        this.name = name;
        this.city = city;
        this.krs = krs;
    }
}

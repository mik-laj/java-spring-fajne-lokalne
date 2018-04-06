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
@Table
@EntityFormat("#{name}")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String name;

    private Point location;

    public City(String name, Point location) {
        this.name = name;
        this.location = location;
    }
}

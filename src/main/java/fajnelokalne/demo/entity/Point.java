package fajnelokalne.demo.entity;

import io.springlets.format.EntityFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Point {

    @Column(scale = 8, precision = 12)
    private BigDecimal lat;

    @Column(scale = 8, precision = 12)
    private BigDecimal lng;

}
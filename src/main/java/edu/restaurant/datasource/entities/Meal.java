package edu.restaurant.datasource.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name = "meals")
@Entity
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
     private int id;
    @Column(name = "name")
     private String name;
    @Column(name = "price")
     private BigDecimal price;

    public Meal(int id) {
        this.id = id;
    }
    public Meal(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }
    public Meal(int id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Meal() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}

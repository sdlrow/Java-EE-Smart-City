package Nurbol.Entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "buildings", indexes = {
        @Index(name = "buildings_name_key", columnList = "name", unique = true)
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Building {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "dateofestablishment", nullable = false)
    private LocalDate dateofestablishment;

    @Column(name = "rating", nullable = false)
    private Double rating;

    @Column(name = "address", nullable = false, length = 50)
    private String address;

    @Column(name = "telephonenumber", nullable = false, length = 50)
    private String telephonenumber;

    @Lob
    @Column(name = "description", nullable = false)
    private String description;

    public String getDateofestablishment() {
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd.MM.uuuu");
        String text = this.dateofestablishment.format(formatters);
        return text;
    }
    //    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getTelephonenumber() {
//        return telephonenumber;
//    }
//
//    public void setTelephonenumber(String telephonenumber) {
//        this.telephonenumber = telephonenumber;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public Double getRating() {
//        return rating;
//    }
//
//    public void setRating(Double rating) {
//        this.rating = rating;
//    }
//
//    public LocalDate getDateofestablishment() {
//        return dateofestablishment;
//    }
//
//    public void setDateofestablishment(LocalDate dateofestablishment) {
//        this.dateofestablishment = dateofestablishment;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
}
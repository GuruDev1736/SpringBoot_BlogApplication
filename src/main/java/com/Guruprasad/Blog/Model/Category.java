package com.Guruprasad.Blog.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories" , uniqueConstraints = {@UniqueConstraint(columnNames = {"categoryName"})})
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;

    @Column(name = "categoryName" , nullable = false)
    private  String name ;

    @Column(name = "description" , nullable = false)
    private String description ;

    @OneToMany(mappedBy = "category" , cascade = CascadeType.ALL , orphanRemoval = true)
    private List<Post> posts;
}

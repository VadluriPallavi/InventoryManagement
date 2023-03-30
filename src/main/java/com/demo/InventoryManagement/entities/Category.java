package com.demo.InventoryManagement.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "category")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "categoryId")
    public long categoryId;

    @Column(name = "categoryName", unique = true)
    @NonNull
    private String categoryName;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    List<Item> itemList;

}

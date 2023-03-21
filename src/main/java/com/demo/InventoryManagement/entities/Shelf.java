package com.demo.InventoryManagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.*;

@Entity
@Table(name = "shelf")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Shelf {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shelfId")
    private long shelfId;

    @NonNull
    @Column(name = "shelfCapacity")
    private long shelfCapacity;

//    @ManyToMany(mappedBy = "shelfLocations")
//    Set<Item> itemsStored = new HashSet<>();;

    @OneToMany(mappedBy = "shelf", cascade = CascadeType.ALL)
    @JsonIgnore
    List<ItemQuantity> quantities = new ArrayList<>();

}

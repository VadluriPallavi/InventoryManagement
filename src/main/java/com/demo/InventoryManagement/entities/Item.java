package com.demo.InventoryManagement.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "item")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itemId")
    private long itemId;

    @Column(name = "itemName", unique = true)
    @NonNull
    private String itemName;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "categoryId", nullable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "vendorId", nullable = false)
    private Vendor vendor;

    @NonNull
    @Column(name = "pricePerUnit")
    private long pricePerUnit;

    @Column(name = "capacityConsumption")
    @NonNull
    private long capacityConsumption;

//    @ManyToMany
//    @JoinTable(
//            name = "items_stored",
//            joinColumns = @JoinColumn(name = "itemId"),
//            inverseJoinColumns = @JoinColumn(name = "shelfId")
//    )
//    Set<Shelf> shelfLocations = new HashSet<>();;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    List<ItemQuantity> quantities = new ArrayList<>();;

}

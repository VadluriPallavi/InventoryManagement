package com.demo.InventoryManagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ItemQuantity {

    @EmbeddedId
    private ItemQuantityKey id = new ItemQuantityKey();

    @ManyToOne
    @MapsId("shelfId")
    @JoinColumn(name = "shelf_Id")
    private Shelf shelf;

    @ManyToOne
    @MapsId("itemId")
    @JoinColumn(name = "item_Id")
    @JsonIgnore
    private Item item;

    private long quantity;
}

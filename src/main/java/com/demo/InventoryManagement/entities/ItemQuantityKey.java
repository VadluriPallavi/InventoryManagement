package com.demo.InventoryManagement.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
public class ItemQuantityKey implements Serializable {

    @Column(name = "shelfId")
    Long shelfId;

    @Column(name = "itemId")
    Long itemId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemQuantityKey that)) return false;
        return getShelfId().equals(that.getShelfId()) && getItemId().equals(that.getItemId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getShelfId(), getItemId());
    }
}

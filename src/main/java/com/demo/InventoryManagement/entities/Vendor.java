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
@Table(name = "vendor")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "vendorId")
    public long vendorId;
    @Column(name = "vendorName", unique = true)
    @NonNull
    private String vendorName;

    @Column(name = "vendorLink")
    private String vendorLink;

    @JsonIgnore
    @OneToMany(mappedBy = "vendor")
    List<Item> itemList;

}

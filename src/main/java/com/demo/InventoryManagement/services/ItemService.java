package com.demo.InventoryManagement.services;

import com.demo.InventoryManagement.entities.FilterList;
import com.demo.InventoryManagement.entities.Item;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ItemService {
    public List<Item> getItems();

    public Item getItem(long itemId);


    public Item addItem(Item item);

    public Optional<Item> addMoreItems(long itemId, long shelfId, long quantity);

    public Item updateItem(Item item);

    public void deleteItem(long itemId);
    public Item updateItemFields(long itemId, Map<String, Object> fields);

    public Optional<Item> deductItemQuantity(long itemId, long shelfId, long requestedQuantity);
}

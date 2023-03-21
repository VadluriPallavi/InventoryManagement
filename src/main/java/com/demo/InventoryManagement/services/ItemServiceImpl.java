package com.demo.InventoryManagement.services;

import com.demo.InventoryManagement.entities.FilterList;
import com.demo.InventoryManagement.entities.Item;
import com.demo.InventoryManagement.entities.ItemQuantity;
import com.demo.InventoryManagement.entities.Shelf;
import com.demo.InventoryManagement.repository.ItemQuantityRepository;
import com.demo.InventoryManagement.repository.ItemRepository;
import com.demo.InventoryManagement.repository.ShelfRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ShelfService shelfService;


    @Autowired
    private ItemQuantityRepository itemQuantityRepository;

    @Override
    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    public Item getItem(long itemId) {
        return itemRepository.findById(itemId).get();
    }

    @Override
    public Item addItem(Item item) {
        if (itemRepository.existsByItemName(item.getItemName())) {
            return itemRepository.findByItemName(item.getItemName());
        }

        Item newItem = new Item();
        newItem.setItemName(item.getItemName());
        newItem.setCategory(item.getCategory());
        newItem.setVendor(item.getVendor());
        newItem.setPricePerUnit(item.getPricePerUnit());
        newItem.setCapacityConsumption(item.getCapacityConsumption());
        newItem.getQuantities().addAll((item.getQuantities()
                .stream()
                .map(itemQuantity -> {
                    Shelf shelf = shelfService.findShelfById(itemQuantity.getShelf().getShelfId());
                    ItemQuantity newItemQuantity = new ItemQuantity();
                    newItemQuantity.setShelf(shelf);
                    newItemQuantity.setItem(newItem);
                    newItemQuantity.setQuantity(itemQuantity.getQuantity());
                    return newItemQuantity;
                })
                .collect(Collectors.toList())
        ));
        return itemRepository.save(newItem);
    }

    @Override
    public Optional<Item> addMoreItems(long itemId, long shelfId, long quantity) {
        log.info("Inside addMoreItems");
        Optional<Item> existingItem = itemRepository.findById(itemId);


        if(existingItem.isPresent()) {
            List<ItemQuantity> itemQuantities = existingItem.get().getQuantities();

            log.info("existing item quantities", itemQuantities);

            Boolean exists = false;

            for(ItemQuantity itemQuantity : itemQuantities) {
                if (itemQuantity.getId().getItemId() == itemId && itemQuantity.getShelf().getShelfId() == shelfId) {
                    // same shelf and item id
                    itemQuantity.setQuantity(itemQuantity.getQuantity() + quantity);
                    exists = true;
                }
            }


            if (!exists) {
                Shelf shelf = shelfService.findShelfById(shelfId);
                ItemQuantity newItemQuantity = new ItemQuantity();
                newItemQuantity.setShelf(shelf);
                newItemQuantity.setQuantity(quantity);
                newItemQuantity.setItem(existingItem.get());

                existingItem.get().getQuantities().add(newItemQuantity);
            }


            itemRepository.save(existingItem.get());
        }

        return existingItem;
    }

    @Override
    public Item updateItem(Item item) {
        itemRepository.save(item);
        return item;
    }

    @Override
    public void deleteItem(long itemId) {
        Item item = itemRepository.findById(itemId).get();
        itemRepository.delete(item);
    }

    @Override
    public Item updateItemFields(long itemId, Map<String, Object> fields) {
        Optional<Item> existingItem = itemRepository.findById(itemId);

        if (existingItem.isPresent()) {
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Item.class, key);
                field.setAccessible(true);
                ReflectionUtils.setField(field, existingItem.get(), value);
            });

            return itemRepository.save(existingItem.get());
        }

        return null;
    }

    @Override
    public Optional<Item> deductItemQuantity(long itemId, long shelfId, long requestedQuantity) {
        Optional<Item> item = itemRepository.findById(itemId);
        Shelf shelf = shelfService.findShelfById(shelfId);
        if(item.isPresent()) {
            ItemQuantity itemQuantity = itemQuantityRepository.findByItemAndShelf(item, shelf);

            long currentQuantity = itemQuantity.getQuantity();

            if (requestedQuantity == currentQuantity) {
                itemQuantityRepository.delete(itemQuantity);
            }else if (requestedQuantity < currentQuantity) {
                itemQuantity.setQuantity(currentQuantity - requestedQuantity);
                itemQuantityRepository.save(itemQuantity);
            } else {
                throw new IllegalStateException("More items requested than in shelf");
            }
        }
        return item;
    }

}

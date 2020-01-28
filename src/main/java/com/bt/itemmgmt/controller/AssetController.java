package com.bt.itemmgmt.controller;

import com.bt.itemmgmt.model.Item;
import com.bt.itemmgmt.service.ItemService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.*;

@RestController
@RequestMapping("/api/v1/assets")
public class AssetController {

    private ItemService assetService;

    public AssetController(ItemService assetService){
        this.assetService = assetService;
    }

    @PostMapping

    @ResponseStatus(HttpStatus.CREATED)
    public Item saveItem(@RequestBody Item item){
        return this.assetService.save(item);
    }

    @GetMapping
    public List<Item> fetchAll(){
      return this.assetService.listAll();
    }

    @GetMapping(value = "/{itemId}")
    public Item fetchItemById( @PathVariable ("itemId") long itemId){
        return this.assetService.findById(itemId);
    }

    @PutMapping(value = "/{itemId}")
    public Item fetchItemById( @PathVariable ("itemId") long itemId, @RequestBody Item item){
        return this.assetService.update(itemId, item);
    }

    @DeleteMapping(value = "/{itemId}")
    public void deleteItemById( @PathVariable ("itemId") long itemId){
        this.assetService.deleteItemById(itemId);
    }

    //@ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntTimeException(Exception exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Exception thrown from the asset controller error handler");
    }
}
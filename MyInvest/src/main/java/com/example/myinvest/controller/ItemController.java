package com.example.myinvest.controller;

import com.example.myinvest.model.receive.ItemReceiveModel;
import com.example.myinvest.model.receive.PackageReceiveModel;
import com.example.myinvest.model.response.ApiResponse;
import com.example.myinvest.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/{username}/{packageName}")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PreAuthorize(value = "hasAuthority('USER')")
    @PostMapping("/add")
    public ApiResponse addItem(
            @RequestBody ItemReceiveModel itemReceiveModel,
            @PathVariable("packageName") String packageName
    ){
        return itemService.addItem(packageName,itemReceiveModel);
    }

    @PreAuthorize(value = "hasAuthority('USER')")
    @GetMapping("")
    public ApiResponse getItemsList(
            @PathVariable("packageName") String packageName,
            @PathVariable String username) {
        return itemService.getItemList(username,packageName);
    }

}

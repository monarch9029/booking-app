package com.softwaretask.Booking_app.controller;

import com.softwaretask.Booking_app.entity.Load;
import com.softwaretask.Booking_app.service.LoadService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/load")
public class LoadController {
    private final LoadService loadService;

    public LoadController(LoadService loadService) {
        this.loadService = loadService;
    }
    //create a new load
    @PostMapping
    public Load createLoad(@RequestBody Load load) {
        return loadService.createLoad(load);
    }
    //update a load
    @PutMapping("/{loadId}")
    public Load upddateLoad(@PathVariable UUID loadId,@RequestBody Load load){
        return loadService.updateLoad(load,loadId);
    }
    //get load by loadID
    @GetMapping("/{loadId}")
    public Load getLoadById(@PathVariable UUID loadId) {
        return loadService.getLoadById(loadId);
    }
    //delete load by loadId
    @DeleteMapping("/{loadId}")
    public void deleteLoad(@PathVariable UUID loadId) {
        loadService.deleteLoadById(loadId);
    }
    //get loads by shipperId
    @GetMapping("/shipper/{shipperId}")
    public List<Load> getLoadsByShipperId(@PathVariable String shipperId) {
        return loadService.getLoadsByShipperId(shipperId);
    }
    //get loads by truckType
    @GetMapping("/truckType/{truckType}")
    public List<Load> getLoadsByTruckType(@PathVariable String truckType) {
        return loadService.getLoadByTruckType(truckType);
    }
}

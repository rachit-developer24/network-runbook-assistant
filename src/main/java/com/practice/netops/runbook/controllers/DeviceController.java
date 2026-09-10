package com.practice.netops.runbook.controllers;

import com.practice.netops.runbook.exceptions.DeviceNotFoundException;
import com.practice.netops.runbook.inventory.InventoryService;
import com.practice.netops.runbook.models.Device;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {

    private final InventoryService inventoryService;

    public DeviceController(InventoryService inventoryService){
        this.inventoryService = inventoryService;
    }

    @GetMapping("/{hostname}")
    public ResponseEntity<Device> getDevice(@PathVariable String hostname) {
        Device device = inventoryService.findByHostname(hostname)
                .orElseThrow(() -> new DeviceNotFoundException(hostname));
        return ResponseEntity.ok(device);
    }
}

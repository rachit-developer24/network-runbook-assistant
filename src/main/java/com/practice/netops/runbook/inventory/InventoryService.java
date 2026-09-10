package com.practice.netops.runbook.inventory;

import com.practice.netops.runbook.models.Device;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class InventoryService {


   private final Map<String, Device> devices = Map.of(
           "LDN-01", new Device("LDN-01", "ISR4451-X", "17.9.4a"),
           "LDN-02", new Device("LDN-02", "ISR4331", "17.6.5"),
           "FRA-01", new Device("FRA-01", "Juniper MX204", "Junos 21.4R3"),
           "MAN-SW-01", new Device("MAN-SW-01", "Catalyst 9300", "17.9.4a"),
           "LAB-01", new Device("LAB-01", "ISR4331", "17.9.4a")
   );


   public Optional<Device> findByHostname(String hostname){
    return Optional.ofNullable(devices.get(hostname));
   }





}

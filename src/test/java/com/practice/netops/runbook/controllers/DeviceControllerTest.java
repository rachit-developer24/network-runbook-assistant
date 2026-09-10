package com.practice.netops.runbook.controllers;

import com.practice.netops.runbook.inventory.InventoryService;
import com.practice.netops.runbook.models.Device;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DeviceController.class)
class DeviceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private InventoryService inventoryService;

    @Test
    void returnsDeviceWhenHostnameIsKnown() throws Exception {
        given(inventoryService.findByHostname("LDN-01"))
                .willReturn(Optional.of(new Device("LDN-01", "ISR4451-X", "17.9.4a")));

        mockMvc.perform(get("/api/devices/LDN-01"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.hostname").value("LDN-01"))
                .andExpect(jsonPath("$.model").value("ISR4451-X"))
                .andExpect(jsonPath("$.softwareVersion").value("17.9.4a"));
    }

    @Test
    void returns404WhenHostnameIsUnknown() throws Exception {
        given(inventoryService.findByHostname("NOPE-99")).willReturn(Optional.empty());

        mockMvc.perform(get("/api/devices/NOPE-99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.detail")
                        .value("Device with hostname NOPE-99 was not found."));
    }
}

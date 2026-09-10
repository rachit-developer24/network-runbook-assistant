package com.practice.netops.runbook.models;

public class Device {

    private final String hostname;
    private final String model;
    private final String softwareVersion;

    public Device(String hostname, String model, String softwareVersion) {
        this.hostname = hostname;
        this.model = model;
        this.softwareVersion = softwareVersion;
    }

    public String getHostname() {
        return hostname;
    }

    public String getModel() {
        return model;
    }

    public String getSoftwareVersion() {
        return softwareVersion;
    }
}

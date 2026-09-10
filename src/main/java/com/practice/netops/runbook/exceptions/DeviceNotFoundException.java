package com.practice.netops.runbook.exceptions;

public class DeviceNotFoundException extends RuntimeException {
    public DeviceNotFoundException(String hostname){
        super("Device with hostname " + hostname + " was not found.");
    }
}

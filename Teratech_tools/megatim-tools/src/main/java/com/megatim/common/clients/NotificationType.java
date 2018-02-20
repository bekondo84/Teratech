/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatim.common.clients;

/**
 *
 * @author DEV
 */
public enum NotificationType {

    ERROR("ERROR"),
    WARNING("WARNING"),
    SUCCES("SUCCESS"),
    INFOS("INFOS");
    private String name = "";

    private NotificationType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

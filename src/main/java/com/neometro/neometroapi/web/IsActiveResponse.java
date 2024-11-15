package com.neometro.neometroapi.web;

public class IsActiveResponse {

    private Boolean isActive;

    public IsActiveResponse(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
}

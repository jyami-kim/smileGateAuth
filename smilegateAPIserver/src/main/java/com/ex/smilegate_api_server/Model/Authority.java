package com.ex.smilegate_api_server.Model;

public enum Authority {
    GENERAL, MANAGER;

    private Authority status;

    public String getStatus() {
        return this.status.name();
    }

    public void setStatus(String status) {
        this.status = Authority.valueOf(status);
    }
}


package com.fosanzdev.trainingbrain.models;

public class SessionBuilder {
    private String name;
    private String username;
    private String password;
    private String authToken;
    private String accessToken;
    private String refreshToken;

    public SessionBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public SessionBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public SessionBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public SessionBuilder setAuthToken(String authToken) {
        this.authToken = authToken;
        return this;
    }

    public SessionBuilder setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public SessionBuilder setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
        return this;
    }

    public SessionDTO build() {
        SessionDTO sessionDTO = new SessionDTO();
        sessionDTO.setName(name);
        sessionDTO.setUsername(username);
        sessionDTO.setPassword(password);
        sessionDTO.setAuthToken(authToken);
        sessionDTO.setAccessToken(accessToken);
        sessionDTO.setRefreshToken(refreshToken);
        return sessionDTO;
    }
}
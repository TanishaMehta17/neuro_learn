package com.example.neuro_learn.neuro_learn.Config;

import lombok.Data;

@Data
public class JwtRequest {
    private String email;
    private String password;

    public static class JwtResponse {
        private String token;

        public JwtResponse(String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}


package com.example.bst_part.model;

public class User {
    private String username;
    private String password;
    private String role; // "admin" or "user"

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Getters
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getRole() { return role; }

    public String toString() {
        return String.format("%s,%s,%s", username, password, role);
    }

    public static User fromString(String str) {
        String[] parts = str.split(",");
        return new User(parts[0], parts[1], parts[2]);
    }
}
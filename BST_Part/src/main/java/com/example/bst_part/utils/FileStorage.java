package com.example.bst_part.utils;

import com.example.bst_part.model.*;
import java.io.*;
import java.util.*;

public class FileStorage {
    private static final String DATA_DIR = "data/";
    private static final String PACKAGES_FILE = DATA_DIR + "packages.txt";
    private static final String USERS_FILE = DATA_DIR + "users.txt";

    static {
        new File(DATA_DIR).mkdirs();
    }

    public static void savePackages(BSTPackage bst) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(PACKAGES_FILE))) {
            writer.print(bst.getPackagesInOrder());
        }
    }

    public static BSTPackage loadPackages() throws IOException {
        BSTPackage bst = new BSTPackage();
        File file = new File(PACKAGES_FILE);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (!line.trim().isEmpty()) {
                        bst.insert(TourismPackage.fromString(line));
                    }
                }
            }
        }
        return bst;
    }

    public static void saveUsers(List<User> users) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(USERS_FILE))) {
            for (User user : users) {
                writer.println(user.toString());
            }
        }
    }

    public static List<User> loadUsers() throws IOException {
        List<User> users = new ArrayList<>();
        File file = new File(USERS_FILE);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (!line.trim().isEmpty()) {
                        users.add(User.fromString(line));
                    }
                }
            }
        } else {
            // Create default users if file doesn't exist
            users.add(new User("admin", "admin123", "admin"));
            users.add(new User("user", "user123", "user"));
            saveUsers(users);
        }
        return users;
    }
}
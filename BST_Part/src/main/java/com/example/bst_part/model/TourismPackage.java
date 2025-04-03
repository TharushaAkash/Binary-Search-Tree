package com.example.bst_part.model;

public class TourismPackage {
    private int packageId;
    private String name;
    private String destination;
    private double price;
    private int duration;

    // Constructor
    public TourismPackage(int packageId, String name, String destination, double price, int duration) {
        this.packageId = packageId;
        this.name = name;
        this.destination = destination;
        this.price = price;
        this.duration = duration;
    }

    // ✅ Add the missing getter method for packageId
    public int getPackageId() {
        return packageId;
    }

    public String toString() {
        return String.format("%d,%s,%s,%.2f,%d",
                packageId, name, destination, price, duration);
    }

    public static TourismPackage fromString(String str) {
        String[] parts = str.split(",");
        return new TourismPackage(
                Integer.parseInt(parts[0]),
                parts[1],
                parts[2],
                Double.parseDouble(parts[3]),
                Integer.parseInt(parts[4])
        );
    }
}

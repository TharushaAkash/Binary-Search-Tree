package com.example.bst_part.model;

public class BSTPackage {
    private Node root;

    private static class Node {
        TourismPackage data;
        Node left, right;
        public Node(TourismPackage data) {
            this.data = data;
        }
    }

    public void insert(TourismPackage pkg) {
        root = insertRec(root, pkg);
    }

    private Node insertRec(Node root, TourismPackage pkg) {
        if (root == null) return new Node(pkg);
        if (pkg.getPackageId() < root.data.getPackageId()) {
            root.left = insertRec(root.left, pkg);
        } else if (pkg.getPackageId() > root.data.getPackageId()) {
            root.right = insertRec(root.right, pkg);
        }
        return root;
    }

    public String getPackagesInOrder() {
        StringBuilder sb = new StringBuilder();
        inOrderRec(root, sb);
        return sb.toString();
    }

    private void inOrderRec(Node root, StringBuilder sb) {
        if (root != null) {
            inOrderRec(root.left, sb);
            sb.append(root.data.toString()).append("\n");
            inOrderRec(root.right, sb);
        }
    }
}
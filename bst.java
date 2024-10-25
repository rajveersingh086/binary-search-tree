package adv_DS;

import java.util.Scanner;

class Node {
    int data;
    Node left, right;

    public Node(int item) {
        data = item;
        left = right = null;
    }
}

public class bst {
    Node root;

    bst() {
        root = null;
    }

    // Insertion operation
    void insert(int data) {
        root = insertRec(root, data);
    }

    Node insertRec(Node root, int data) {
        if (root == null) {
            root = new Node(data);
            return root;
        }
        if (data < root.data) {
            root.left = insertRec(root.left, data);
        } else if (data > root.data) {
            root.right = insertRec(root.right, data); // Corrected
        }
        return root;
    }

    // Search operation
    boolean search(int data) {
        return searchRec(root, data);
    }

    boolean searchRec(Node root, int data) {
        if (root == null) {
            return false;
        }
        if (root.data == data) {
            return true;
        }
        if (data < root.data) {
            return searchRec(root.left, data);
        }
        return searchRec(root.right, data);
    }

    // Deletion operation
    void delete(int data) {
        root = deleteRec(root, data);
    }

    Node deleteRec(Node root, int data) {
        if (root == null) {
            return root;
        }
        if (data < root.data) {
            root.left = deleteRec(root.left, data);
        } else if (data > root.data) {
            root.right = deleteRec(root.right, data);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
            root.data = minValue(root.right);
            root.right = deleteRec(root.right, root.data);
        }
        return root;
    }

    int minValue(Node root) {
        int minValue = root.data;
        while (root.left != null) {
            minValue = root.left.data;
            root = root.left;
        }
        return minValue;
    }

    // Modification operation
    void modify(int oldData, int newData) {
        delete(oldData);
        insert(newData);
    }

    // In-order Traversal (to visualize the tree structure)
    void inOrder() {
        inOrderRec(root);
        System.out.println();
    }

    void inOrderRec(Node root) {
        if (root != null) {
            inOrderRec(root.left);
            System.out.print(root.data + " ");
            inOrderRec(root.right);
        }
    }

    // Main method with menu options
    public static void main(String[] args) {
        bst bst = new bst();
        Scanner sc = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- Binary Search Tree Operations ---");
            System.out.println("1. Insert");
            System.out.println("2. Delete");
            System.out.println("3. Search");
            System.out.println("4. Modify");
            System.out.println("5. In-Order Traversal");
            System.out.println("6. Exit");
            System.out.print("Choose an option (1-6): ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1: // Insert
                    System.out.print("Enter value to insert: ");
                    int insertValue = sc.nextInt();
                    bst.insert(insertValue);
                    System.out.println(insertValue + " inserted.");
                    break;

                case 2: // Delete
                    System.out.print("Enter value to delete: ");
                    int deleteValue = sc.nextInt();
                    bst.delete(deleteValue);
                    System.out.println(deleteValue + " deleted.");
                    break;

                case 3: // Search
                    System.out.print("Enter value to search: ");
                    int searchValue = sc.nextInt();
                    if (bst.search(searchValue)) {
                        System.out.println(searchValue + " found in the BST.");
                    } else {
                        System.out.println(searchValue + " not found in the BST.");
                    }
                    break;

                case 4: // Modify
                    System.out.print("Enter old value to modify: ");
                    int oldValue = sc.nextInt();
                    System.out.print("Enter new value to replace: ");
                    int newValue = sc.nextInt();
                    bst.modify(oldValue, newValue);
                    System.out.println("Modified " + oldValue + " to " + newValue);
                    break;

                case 5: // In-order Traversal
                    System.out.println("In-Order Traversal of the tree:");
                    bst.inOrder();
                    break;

                case 6: // Exit
                    System.out.println("Exiting...");
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid choice! Please select a valid option.");
            }
        }
        sc.close();
    }
}

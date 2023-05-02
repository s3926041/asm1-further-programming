package asm1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {
    public static ShoppingCart cart = null;

    public static ShoppingCart getCart() {
        return cart;
    }

    public static void setCart(ShoppingCart cart) {
        Main.cart = cart;
    }

    public static void main(String[] args) {
        // Data.read();
        // for(ShoppingCart s: ShoppingCart.getAllCart()){
        // for(String str : s.getCart().keySet()){
        // System.out.println(s.getCart().get(str).getProduct() );
        // System.out.println(s.getCart().get(str).getQuantity());
        // }
        // System.out.println("");
        // }

        // User Interaction coding here
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Select an option from here:");
            System.out.println("-----------------------------------------");
            System.out.println("1. Create new products");
            System.out.println("2. Edit products");
            System.out.println("3. View products");
            System.out.println("-----------------------------------------");
            System.out.println("4. Create cart");
            System.out.println("5. Add items to cart");
            System.out.println("6. Remove items from cart");
            System.out.println("-----------------------------------------");
            System.out.println("7. Update/View Message of Gift Items");
            System.out.println("-----------------------------------------");
            System.out.println("8. Apply coupon");
            System.out.println("9. Remove coupon");
            System.out.println("-----------------------------------------");
            System.out.println("10. View cart in detail");
            System.out.println("11. Sorting for carts");
            System.out.println("0. Exit");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    createProduct(scanner);
                    break;
                case 2:
                    editProduct(scanner);
                    break;
                case 3:
                    viewProduct(scanner);
                    break;
                case 4:
                    createCart();
                    break;
                case 5:
                    addProduct(scanner);
                    break;
                case 6:
                    removeProduct(scanner);
                    break;
                case 7:
                    updateOrViewMessage(scanner);
                    break;
                case 8:
                    addCoupon(scanner);
                    break;
                case 9:
                    removeCoupon();
                case 10:
                    viewCartDetails();
                case 11:
                    sortingCart();

                default:
                    System.err.println("Unknown command");
                    break;
            }
        }
    }

    public static void createProduct(Scanner scanner) {
        HashMap<String, Product> allProduct = Product.getAllProduct();
        System.out.println("Enter product name:");
        String name = scanner.nextLine();
        if (allProduct.containsKey(name)) {
            System.err.println("Product existed.");
            return;
        }

        System.out.println("Enter product description:");
        String description = scanner.nextLine();
        scanner.nextLine();

        System.out.println("Enter quantity available:");
        int quantityAvailable = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter price:");
        double price = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Choose taxType\n1. TAX_FREE\n2. NORMAL_TAX 3.LUXURY_TAX");
        int option = scanner.nextInt();
        String taxString = "";
        switch (option) {
            case 1:
                taxString = "TAX_FREE";
                break;
            case 2:
                taxString = "NORMAL_TAX";
                break;
            case 3:
                taxString = "LUXURY_TAX";
                break;
            default:
                taxString = "TAX_FREE";
                break;

        }
        System.out.println("Is this product can be used as GIFT? (Y/N)");
        boolean canBeGift = false;
        String answer = scanner.nextLine();
        if (answer.equalsIgnoreCase("y")) {
            canBeGift = true;
        }
        System.out.println("Is this a physical product? (Y/N)");
        answer = scanner.nextLine();

        Product product;
        if (answer.equalsIgnoreCase("y")) {
            System.out.println("Enter weight:");
            double weight = scanner.nextDouble();
            product = new PhysicalProduct(name, description, quantityAvailable, price, weight,
                    TaxType.valueOf(taxString), canBeGift);

        } else {
            product = new DigitalProduct(name, description, quantityAvailable, price,
                    TaxType.valueOf(taxString), canBeGift);

        }
        System.out.println("Product created.");
    }

    public static void editProduct(Scanner scanner) {
        HashMap<String, Product> allProduct = Product.getAllProduct();
        if (allProduct.isEmpty()) {
            System.err.println("No product available.");
            return;
        }
        System.out.println("All product:");
        for (String s : allProduct.keySet()) {
            System.out.println("-" + s);
        }
        System.out.println("");
        System.out.println("Enter product name to edit:");
        String name = scanner.nextLine();
        if (allProduct.containsKey(name)) {
            System.out.println("Select an option:");
            System.out.println("1. Change description");
            System.out.println("2. Change quantity");
            System.out.println("3. Change price");
            int option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    System.out.println("Enter new description: ");
                    String newDescription = scanner.nextLine();
                    if (newDescription.isEmpty()) {
                        System.out.println("Invalid description");
                        return;
                    }
                    allProduct.get(name).setDescription(newDescription);
                    System.out.println("Description changed.");
                    break;
                case 2:
                    System.out.println("Enter new quantity: ");
                    int newQuantity = scanner.nextInt();
                    allProduct.get(name).setQuantity(newQuantity);
                    System.out.println("Quantity changed.");
                    break;
                case 3:
                    System.out.println("Enter new price: ");
                    double newPrice = scanner.nextDouble();
                    allProduct.get(name).setPrice(newPrice);
                    System.out.println("Price changed.");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
            return;
        }
        System.err.println("No product name " + name);
    }

    public static void viewProduct(Scanner scanner) {
        System.out.println("Enter product name:");
        String name = scanner.nextLine();
        HashMap<String, Product> allProduct = Product.getAllProduct();
        if (!allProduct.containsKey(name)) {
            System.err.println("No product name " + name);
            return;
        }
        Product p = allProduct.get(name);
        System.out.println("Product name: " + name);
        System.out.println("Product type: " + p.getType());
        System.out.println("Description: " + p.getDescription());
        System.out.println("Price: " + p.getPrice());
        System.out.println("Tax type: " + p.getTaxType().name());
        System.out.println("Can be gift: " + p.canBeGift());
        if (p instanceof PhysicalProduct) {
            System.out.println("Weight: " + ((PhysicalProduct) p).getWeight());
        }
    }

    public static void createCart() {
        ShoppingCart cart = new ShoppingCart();
        System.out.println("Cart " + cart.getCartOrder() + " created!");
        setCart(cart);
    }

    public static void addProduct(Scanner scanner) {
        if (cart == null) {
            System.out.println("Please create cart first!");
            return;
        }
        System.out.println("Enter product name: ");
        String name = scanner.nextLine();
        System.out.println("Enter product's quantity: ");
        int quantity = scanner.nextInt();
        cart.addItem(name, quantity);
    }

    public static void removeProduct(Scanner scanner) {
        if (cart == null) {
            System.out.println("Please create cart first!");
            return;
        }
        System.out.println("Enter product name: ");
        String name = scanner.nextLine();
        System.out.println("Enter product's quantity: ");
        int quantity = scanner.nextInt();
        cart.removeItem(name, quantity);
    }

    public static void updateOrViewMessage(Scanner scanner) {
        System.out.println("Enter the name of the gift item:");
        String name = scanner.nextLine();
        ShoppingCart curCart = ShoppingCart.getAllCart().get(ShoppingCart.getAllCart().size() - 1);
        if (curCart.getCart().containsKey(name)) {
            ProductItem p = curCart.getCart().get(name);
            if (p instanceof GiftItem) {
                System.out.println("Current message: " + ((GiftItem) p).getMessage());
                System.out.println("Do you want to update message? (Y/N)");
                String option = scanner.nextLine();
                if (option.equalsIgnoreCase("y")) {
                    System.out.println("Enter new message: ");
                    String newMessage = scanner.nextLine();
                    ((GiftItem) p).setMessage(newMessage);
                    System.out.println("Message set.");
                }
            } else {
                System.out.println("Not gift item");
                return;
            }
        } else
            System.out.println("No product item name " + name);
    }

    public static void addCoupon(Scanner scanner) {
        System.out.println("Enter coupon name: ");
        String name = scanner.nextLine();
        Coupon coupon = null;
        for (Coupon c : Coupon.getAllCoupons()) {
            if (c.getStringValue().equals(name)) {
                coupon = c;
                break;
            }
        }
        if (coupon == null) {
            System.out.println("No coupon name " + name);
            return;
        }
        ShoppingCart curCart = ShoppingCart.getAllCart().get(ShoppingCart.getAllCart().size() - 1);
        curCart.addCoupon(coupon);
    }

    public static void removeCoupon() {
        ShoppingCart curCart = ShoppingCart.getAllCart().get(ShoppingCart.getAllCart().size() - 1);
        curCart.removeCoupon();
    }

    public static void viewCartDetails(Scanner scanner) {
        ShoppingCart curCart = ShoppingCart.getAllCart().get(ShoppingCart.getAllCart().size() - 1);
        System.out.println("Current cart ID: " + curCart.getCartOrder());
        System.out.println("Enter cart ID you want to view: ");
        int id = scanner.nextInt() - 1;
        if (id < 0 || id >= ShoppingCart.getAllCart().size()) {
            System.out.println("No cart exist");
            return;
        }
        ShoppingCart cart = ShoppingCart.getAllCart().get(id);
        System.out.println("Product Items: ");
        String weight;
        for(ProductItem p : cart.getCart().values()){
            weight = "";
            if (p.getProduct() instanceof PhysicalProduct){
                weight = String.valueOf( ((PhysicalProduct) p.getProduct()).getWeight());
            }
            System.out.println(p.getProduct().getName() + " | " + p.getQuantity() + " | " + p.getProduct().getPrice() + " | " + p.getProduct().getTaxType().name() + " | " + weight );
        }

        System.out.println("Coupon using: " + cart.getCoupon().getStringValue());
        System.out.println("Total price: "+cart.cartAmount());
        System.out.println("Total weight: " +cart.getTotalWeight());
    }

    public static void sortingCart() {
        ArrayList<ShoppingCart> allCart = ShoppingCart.getAllCart();
        Collections.sort(allCart, new ShoppingCartWeightComparator());
        String header = String.format("%-3s| %-7s | %-10s | %-8s\n", "ID","Coupon", "Final Price",
                "Weight");
        System.out.println(header);

        for (ShoppingCart cart : allCart) {
            String cartDetails = String.format("%-3d | %7s | %-10.2f | $%7.2f",
                    cart.getCartOrder(),
                    cart.getCoupon().getStringValue(),
                    cart.cartAmount(),
                    cart.getTotalWeight());
            System.out.println(cartDetails);
        }

    }

}

class ShoppingCartWeightComparator implements Comparator<ShoppingCart> {
    public int compare(ShoppingCart cart1, ShoppingCart cart2) {
        if (cart1.getTotalWeight() < cart2.getTotalWeight()) {
            return -1;
        } else if (cart1.getTotalWeight() > cart2.getTotalWeight()) {
            return 1;
        } else {
            return 0;
        }
    }
}
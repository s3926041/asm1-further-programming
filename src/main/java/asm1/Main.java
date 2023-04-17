
/**
 * @author <Nguyen Thanh Hung - s3926041>
 */
package asm1;

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
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Select an option:");
            System.out.println("1. Create a new product");
            System.out.println("2. Edit a product");
            System.out.println("3. Create a new shopping cart");
            System.out.println("4. Add a product to the current shopping cart");
            System.out.println("5. Remove a product from the current shopping cart");
            System.out.println("6. Display the cart amount");
            System.out.println("7. Display all shopping carts based on their total weight");
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
                    createCart();
                    break;
                case 4:
                    addProduct(scanner);
                    break;
                case 5:
                    removeProduct(scanner);
                    break;
                case 6:
                    displayCartAmount();
                    break;
                case 7:
                    displayAllCart();
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.err.println("Invalid option.");
            }
            // System.out.println("Press any key to continue...");
            // scanner.nextLine(); // waits for user to press enter
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

        System.out.println("Enter quantity available:");
        int quantityAvailable = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter price:");
        double price = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Is this a physical product? (y/n)");
        String answer = scanner.nextLine();

        if (answer.equalsIgnoreCase("y")) {
            System.out.println("Enter weight:");
            double weight = scanner.nextDouble();
            Product product = new PhysicalProduct(name, description, quantityAvailable, price, weight);

        } else {
            Product product = new DigitalProduct(name, description, quantityAvailable, price);

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

    public static void createCart() {
        ShoppingCart cart = new ShoppingCart();
        System.out.println("Cart "+cart.getCartOrder() + " created!");
        setCart(cart);
    }

    public static void addProduct(Scanner scanner) {
        if(cart == null){
            System.out.println("Please create cart first!");
            return;
        }
        System.out.println("Enter product name: ");
        String name = scanner.nextLine();
        cart.addItem(name);
    }

    public static void removeProduct(Scanner scanner) {
        if(cart == null){
            System.out.println("Please create cart first!");
            return;
        }
        System.out.println("Enter product name: ");
        String name = scanner.nextLine();
        cart.removeItem(name);
    }

    public static void displayCartAmount() {
        if(cart == null){
            System.out.println("Please create cart first!");
            return;
        }
        HashMap<String, Product> allProduct = Product.getAllProduct();
        Product product;
        double cartAmount = cart.cartAmount();
        if (cartAmount == 0) {
            System.out.println("There is no product in cart");
            return;
        }
        double fee = 0;
        System.out.println("-----------------------------------------------------------------------");
        System.out.printf("%-30s%-20s%-20s\n", "Product Name", "Price", "Weight");
        System.out.println("-----------------------------------------------------------------------");
        for (String productName : cart.getProductNames()) {
            product = allProduct.get(productName);
            double weight = 0;
            if (product instanceof PhysicalProduct) {
                weight = ((PhysicalProduct) product).getWeight();
                fee += 0.1 * weight;
            }
            System.out.printf("%-30s%-20.2f%-20.2f\n", product.getName(), product.getPrice(), weight);
            fee += product.getPrice();

        }
        System.out.println("-----------------------------------------------------------------------");
        System.out.printf("Total cart amount: %.2f\n", fee);
    }

    public static void displayAllCart() {
        if (ShoppingCart.getAllCart().isEmpty()) {
            System.out.println("There is no cart.");
            return;
        }

        
        System.out.println("-----------------------------------------------------------------------");
        System.out.printf("%-30s%-20s%-20s\n", "Cart Order", "Price", "Weight");
        System.out.println("-----------------------------------------------------------------------");
        String string = "";
        ShoppingCart.sortList();
        for (ShoppingCart s : ShoppingCart.getAllCart()) {
            if (s.getCartOrder() == Main.cart.getCartOrder()) {
                string = " (Current Cart)";
            }
            System.out.printf("%-30s%-20.2f%-20.2f\n", s.getCartOrder() + string, s.cartAmount(), s.getTotalWeight());
            string = "";
        }
        System.out.println("-----------------------------------------------------------------------");
    }


}

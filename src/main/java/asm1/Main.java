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
        Data.read();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Select an option from here:");
            System.out.println("-----------------------------------------");
            System.out.println("1. Create new products");
            System.out.println("2. Edit products");
            System.out.println("3. View products");
            System.out.println("-----------------------------------------");
            System.out.println("4. Create new cart");
            System.out.println("5. Choose cart");
            System.out.println("6. Add items to current cart");
            System.out.println("7. Remove items from current cart");
            System.out.println("-----------------------------------------");
            System.out.println("8. Update/View Message of Gift Items of the current cart");
            System.out.println("-----------------------------------------");
            System.out.println("9. Apply coupon to the current cart");
            System.out.println("10. Remove coupon from the current cart");
            System.out.println("-----------------------------------------");
            System.out.println("11. View current cart in detail");
            System.out.println("12. Sorting for all carts");
            System.out.println("-----------------------------------------");
            System.out.println("13. Print receipt for current cart");
            System.out.println("-----------------------------------------");
            System.out.println("0. Exit");
            System.out.println("-----------------------------------------");
            System.out.println("Enter option: ");
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
                    chooseCart(scanner);
                    break;
                case 5 + 1:
                    addProduct(scanner);
                    break;
                case 6 + 1:
                    removeProduct(scanner);
                    break;
                case 7 + 1:
                    updateOrViewMessage(scanner);
                    break;
                case 8 + 1:
                    addCoupon(scanner);
                    break;
                case 9 + 1:
                    removeCoupon();
                    break;
                case 10 + 1:
                    viewCartDetails(scanner);
                    break;
                case 11 + 1:
                    sortingCart();
                    break;
                case 13:
                    printReceipt();
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.err.println("Unknown command");
                    break;
            }
            System.out.println();
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

        System.out.println("Enter price:");
        double price = scanner.nextDouble();

        System.out.println("Choose taxType\n1. TAX_FREE\n2. NORMAL_TAX\n3.LUXURY_TAX");
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
        scanner.nextLine();
        System.out.println("Is this a physical product? (Y/N)");
        String answer2 = scanner.nextLine();

        Product product;
        if (answer2.equalsIgnoreCase("y")) {
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
        System.out.println("Enter product name to edit:");
        String name = scanner.nextLine();
        if (allProduct.containsKey(name)) {
            System.out.println("Select an option:");
            System.out.println("1. Change description");
            System.out.println("2. Change quantity");
            System.out.println("3. Change price");
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    System.out.println("Enter new description: ");
                    String newDescription = scanner.nextLine();
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
                    break;
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
        cart = new ShoppingCart();
        System.out.println("Cart " + cart.getCartOrder() + " created and become new current cart!");
        setCart(cart);
    }

    public static void chooseCart(Scanner scanner) {
        System.out.println("Enter cart ID: (From 1 to " + ShoppingCart.getAllCart().size() + ")");
        int id = scanner.nextInt();
        if (id < 1 || id > ShoppingCart.getAllCart().size()) {
            System.out.println("Invalid ID");
        } else {
            cart = ShoppingCart.getAllCart().get(id - 1);
            System.out.println("The current cart is cart with ID " + id);
        }

    }

    public static void addProduct(Scanner scanner) {

        if (cart == null) {
            System.out.println("Please create a cart or choose a cart first!");
            return;
        }
        if (!cart.modifiable()) {
            System.out.println("This cart can not be modify");
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
            System.out.println("Please create a cart or choose a cart first!");
            return;
        }
        if (!cart.modifiable()) {
            System.out.println("This cart can not be modify");
            return;
        }
        System.out.println("Enter product name: ");
        String name = scanner.nextLine();
        System.out.println("Enter product's quantity: ");
        int quantity = scanner.nextInt();
        cart.removeItem(name, quantity);
    }

    public static void updateOrViewMessage(Scanner scanner) {
        if (cart == null) {
            System.out.println("Please create a cart or choose a cart first!");
            return;
        }
        if (!cart.modifiable()) {
            System.out.println("This cart can not be modify");
            return;
        }
        boolean check = false;
        for (ProductItem p : cart.getCart().values()) {
            if (p instanceof GiftItem) {
                check = true;
                break;
            }

        }
        if (!check) {
            System.out.println("There are no gift item in this cart");
            return;
        }
        System.out.println("GIFT items name:");
        for (ProductItem p : cart.getCart().values()) {
            if (p instanceof GiftItem) {
                System.out.println(p.getProduct().getName());
            }
        }

        System.out.println("Enter the name of the gift item:");
        String name = scanner.nextLine();
        if (cart.getCart().containsKey(name)) {
            ProductItem p = cart.getCart().get(name);
            if (p instanceof GiftItem) {
                if (((GiftItem) p).getMessage() != null)
                    System.out.println("Current message: " + ((GiftItem) p).getMessage());
                else
                    System.out.println("This item not have any gift message yet");

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

        if (cart == null) {
            System.out.println("Please create a cart or choose a cart first!");
            return;
        }
        if (!cart.modifiable()) {
            System.out.println("This cart can not be modify");
            return;
        }
        System.out.println("Enter product item in the current cart to add coupon: ");
        String name = scanner.nextLine();
        if (!cart.getCart().containsKey(name)) {
            System.out.println("No product name " + name);
            return;
        }
        Product p = Product.getAllProduct().get(name);
        ArrayList<Coupon> coupons = new ArrayList<>();
        for (Coupon c : Coupon.getAllCoupons()) {
            if (c.getTiedProduct().equals(p)) {
                coupons.add(c);
            }
        }
        if (coupons.size() == 0)
            System.out.println("No coupon available for this product");
        else {
            System.out.println("Available coupon:");
            int i = 1;
            for (Coupon c : coupons) {
                System.out.println(i + ". " + c.getStringValue());
                i++;
            }
            System.out.println("Please choose coupon to apply (Press 0 to cancel )");
            int option = scanner.nextInt();
            if (option == 0) {
                return;
            }
            if (option < 1 || option > coupons.size()) {
                System.out.println("Invalid option");
                return;
            }
            cart.addCoupon(coupons.get(option - 1));
        }

    }

    public static void removeCoupon() {

        if (cart == null) {
            System.out.println("Please create a cart or choose a cart first!");
            return;
        }
        if (!cart.modifiable()) {
            System.out.println("This cart can not be modify");
            return;
        }
        cart.removeCoupon();
    }

    public static void viewCartDetails(Scanner scanner) {
        if (cart == null) {
            System.out.println("Please create a cart or choose a cart first!");
            return;
        }

        String header = String.format("%-20s | %4s | %10s ", "Product name",
                "Quantity",
                "Unit Price");
        System.out.println(header);

        for (ProductItem p : cart.getCart().values()) {

            System.out.printf("%-20s | %8d | %10.2f\n",
                    p.getProduct().getName(),
                    p.getQuantity(), p.getProduct().getPrice(), p.getProduct().getTaxType().name(),
                    p.getProduct().getTaxType().getRate() * p.getProduct().getPrice());

        }
        System.out.println();
        if (cart.getCoupon() != null)
            System.out.println("Coupon using: " + cart.getCoupon().getStringValue());
    }

    public static void sortingCart() {
        ArrayList<ShoppingCart> allCart = ShoppingCart.getAllCart();
        Collections.sort(allCart, new ShoppingCartWeightComparator());
        String header = String.format("%-5s | %12s | %9s | %s", "ID", "Final Price",
                "Weight", "Coupon");
        System.out.println(header);

        for (ShoppingCart cart : allCart) {
            String coupon = "";
            if (cart.getCoupon() != null) {
                coupon = cart.getCoupon().getStringValue();
            }
            String cartDetails = String.format("%-5d | $%11.2f | %9.2f | %s",
                    cart.getCartOrder(),
                    cart.cartAmount(),
                    cart.getTotalWeight(), coupon);

            System.out.println(cartDetails);
        }
    }

    public static void printReceipt() {
        if (cart == null) {
            System.out.println("Please create a cart or choose a cart first!");
            return;
        }
        System.out.println("RECEIPT for cart ID " + (cart.getCartOrder() + 1));
        String header = String.format("%-20s | %4s | %10s | %23s | %15s | %15s | %15s | %15s | %15s", "Product name",
                "Quantity",
                "Unit Price", "Unit Tax", "Total Price", "Total Tax", "Discount", "Final Price",
                "Weight");
        System.out.println(header);
        double discount = 0, rememberDiscount = 0, tax = 0;
        for (ProductItem p : cart.getCart().values()) {
            double weight = 0;

            if (p.getProduct() instanceof PhysicalProduct) {
                weight = ((PhysicalProduct) p.getProduct()).getWeight();
            }
            discount = 0;
            if (cart.getCoupon() != null)
                if (cart.getCoupon().getTiedProduct().equals(p.getProduct())) {
                    discount = cart.getCoupon().discount();
                    rememberDiscount = discount * p.getQuantity();
                }

            System.out.printf("%-20s | %8d | %10.2f | %-10s - %10.2f | %15.2f | %15.2f | %15.2f | %15.2f | %15.2f\n",
                    p.getProduct().getName(),
                    p.getQuantity(), p.getProduct().getPrice(), p.getProduct().getTaxType().name(),
                    p.getProduct().getTaxType().getRate() * p.getProduct().getPrice(),
                    p.getProduct().getPrice() * p.getQuantity(),
                    p.getQuantity() * p.getProduct().getTaxType().getRate() * p.getProduct().getPrice(),
                    discount,
                    p.getQuantity() * (1 + p.getProduct().getTaxType().getRate()) * p.getProduct().getPrice(),
                    weight);
            tax += p.getQuantity() * p.getProduct().getTaxType().getRate() * p.getProduct().getPrice();
        }

        System.out.println();
        String str = "";
        if (cart.getCoupon() != null)
            str = "Coupon using: " + cart.getCoupon().getStringValue();

        System.out.printf("Base price:  : %.2f\n",
                cart.cartAmount() + rememberDiscount - tax - cart.getTotalWeight() * 0.1);
        System.out.printf("Discount     : %.2f %s \n", rememberDiscount, str);
        System.out.printf("Tax          : %.2f\n", tax);
        System.out.printf("Shipping fee : %.2f\n", cart.getTotalWeight() * 0.1);
        System.out.printf("Final price  : %.2f  (%.2f + %.2f + %.2f - %.2f)\n", cart.cartAmount(),
                cart.cartAmount() + rememberDiscount - tax - cart.getTotalWeight() * 0.1, tax,
                cart.getTotalWeight() * 0.1, rememberDiscount);
        System.out.printf("Total weight : %.2f\n", cart.getTotalWeight());
        System.out.println("Release Date : " + new Date());
        cart.setModifiable(false);
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
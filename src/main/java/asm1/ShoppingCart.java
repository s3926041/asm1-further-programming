/**
 * @author <Nguyen Thanh Hung - s3926041>
 */
package asm1;

import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Set;

public class ShoppingCart {
    private Set<String> productNames;

    public Set<String> getProductNames() {
        return productNames;
    }

    private static ArrayList<ShoppingCart> allCart = new ArrayList<>();



    public static ArrayList<ShoppingCart> getAllCart() {
        return allCart;
    }

    private double totalWeight = 0;

    public double getTotalWeight() {
        return totalWeight;
    }

    private int cartOrder;

    public int getCartOrder() {
        return cartOrder;
    }

    public ShoppingCart() {
        this.productNames = new TreeSet<>();
        this.cartOrder = allCart.size() + 1;
        allCart.add(this);

    }

    public static void sortList(){
        Comparator<ShoppingCart> ShoppingCartComparator = new Comparator<ShoppingCart>() {
            @Override
            public int compare(ShoppingCart p1, ShoppingCart p2) {
              if(p1.getTotalWeight() > p2.getTotalWeight()) return 1;
              return -1;
            }
          };
        Collections.sort(allCart,ShoppingCartComparator);
    }

    public boolean addItem(String productName) {
        if (this.productNames.contains(productName)) {
            System.out.println(productName + " already in the cart.");
            return false;
        }

        HashMap<String, Product> allProduct = Product.getAllProduct();
        if (!allProduct.containsKey(productName)) {
            System.out.println("There is no product name " + productName);
            return false;
        }
        Product product = allProduct.get(productName);
        if (product.getQuantity() == 0) {
            System.out.println("This product is not available at the moment.");
            return false;
        }
        product.setQuantity(product.getQuantity() - 1);
        if (product instanceof PhysicalProduct) {
            totalWeight += ((PhysicalProduct) product).getWeight();
        }
        this.productNames.add(productName);
        System.out.println("Product added.");
        return true;
    }

    public boolean removeItem(String productName) {
        if (!productNames.contains(productName)) {
            System.out.println(productName + "is not in the cart.");
            return false;
        }

        HashMap<String, Product> allProduct = Product.getAllProduct();
        if (!allProduct.containsKey(productName)) {
            System.out.println("There is no product name " + productName);
            return false;
        }
        Product product = allProduct.get(productName);
        product.setQuantity(product.getQuantity() + 1);
        if (product instanceof PhysicalProduct) {
            totalWeight -= ((PhysicalProduct) product).getWeight();
        }
        this.productNames.remove(productName);
        System.out.println(productName + "is removed from the cart.");
        return true;
    }

    public double cartAmount() {
        HashMap<String, Product> allProduct = Product.getAllProduct();
        Product product;
        double fee = 0;
        for (String productName : productNames) {
            product = allProduct.get(productName);
            double weight = 0;
            if (product instanceof PhysicalProduct) {
                weight = ((PhysicalProduct) product).getWeight();
                fee += 0.1 * weight;
            }
            fee += product.getPrice();
        }

        return fee;
    }
}
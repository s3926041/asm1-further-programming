/**
 * @author <Nguyen Thanh Hung - s3926041>
 */
package asm1;

import java.util.ArrayList;
import java.util.HashMap;

public class ShoppingCart {
    private HashMap<String, ProductItem> cart;
    private Coupon coupon = null;
    private static ArrayList<ShoppingCart> allCart = new ArrayList<>();

    public HashMap<String, ProductItem> getCart() {
        return cart;
    }

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
        this.cart = new HashMap<>();
        this.cartOrder = allCart.size() + 1;
        allCart.add(this);
    }

    public boolean addItem(String productName, Integer quantity) {
        // check existing product
        HashMap<String, Product> allProduct = Product.getAllProduct();
        if (!allProduct.containsKey(productName)) {
            // System.out.println("sai");
            return false;
        }
        Product product = allProduct.get(productName);
        // check quantity
        if (product.getQuantity() - quantity < 0) {
            // System.out.println(false);
            return false;
        }
        ProductItem productItem;
        if (product.canBeGift())
            productItem = new GiftItem(product, quantity);
        else
            productItem = new ProductItem(product, quantity);
        // update resources
        if (this.cart.containsKey(productName)) {
            productItem.setQuantity(productItem.getQuantity() + quantity);
        } else
            this.cart.put(productName, productItem);

        // System.out.println(productItem.getProduct().getName());
        product.setQuantity(product.getQuantity() - quantity);

        // add weight
        if (product instanceof PhysicalProduct) {
            this.totalWeight += ((PhysicalProduct) product).getWeight() * quantity;
        }
        return true;
    }

    public boolean removeItem(String productName, Integer quantity) {
        if (!cart.containsKey(productName))
            return false;

        if (quantity <= 0)
            return false;

        int newQuantity = this.cart.get(productName).getQuantity() - quantity;

        if (newQuantity < 0)
            return false;

        // Update product quantity of current cart
        if (newQuantity == 0) {
            this.cart.remove(productName);
        } else {
            this.cart.get(productName).setQuantity(newQuantity);
        }

        // Change quantity in resources
        HashMap<String, Product> allProduct = Product.getAllProduct();
        Product product = allProduct.get(productName);
        product.setQuantity(product.getQuantity() + quantity);

        // Change weight of current cart
        if (product instanceof PhysicalProduct) {
            this.totalWeight -= ((PhysicalProduct) product).getWeight() * quantity;
        }
        return true;
    }

    public double cartAmount() {
        HashMap<String, Product> allProduct = Product.getAllProduct();
        Product product;
        double fee = 0;
        for (String productName : cart.keySet()) {
            product = allProduct.get(productName);
            double weight = 0;
            int quantity = cart.get(productName).getQuantity();

            // shiping fee
            if (product instanceof PhysicalProduct) {
                weight = ((PhysicalProduct) product).getWeight();
                fee += 0.1 * weight * quantity;
            }

            // discount check
            double discount = 0;
            if (this.coupon !=null)
                if (this.coupon.getTiedProduct().equals(product)) {
                    discount = coupon.discount() * quantity;
                }
            // sum
            fee += product.getPrice() * (1 + product.getTaxType().getRate()) * quantity - discount;
        }
        return fee;
    }

    public void addCoupon(Coupon coupon) {
        if (cart.containsKey(coupon.getTiedProduct().getName()))
            this.coupon = coupon;
        else
            System.out.println("No product using coupon");
    }

    public void removeCoupon() {
        this.coupon = null;
    }

    public double getTax() {
        return 0;
    }
}
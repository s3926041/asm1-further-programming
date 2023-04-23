package asm1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Data {
    public static void read() {
        try (Stream<String> lines = Files.lines(Paths.get("products.txt"))) {
            lines.forEach(line -> {
                String[] fields = line.split(",");

                // product check
                if (fields[0].contains("product")) {
                    String name = fields[1].trim();
                    String description = fields[2].trim();
                    int quantity = Integer.parseInt(fields[3].trim());
                    double price = Double.parseDouble(fields[4].trim());
                    String taxType = fields[5].trim();
                    boolean isGift = fields[6].equals("GIFT");
                    String giftMessage = isGift ? fields[7].trim() : null;

                    Product newProduct;
                    if (fields[0].equals("physicalproduct")) {
                        double weight = Double.parseDouble(fields[8].trim());
                        newProduct = new PhysicalProduct(name, description, quantity, price, weight,
                                TaxType.valueOf(taxType), isGift);

                    } else {
                        newProduct = new DigitalProduct(name, description, quantity, price, TaxType.valueOf(taxType),
                                isGift);
                    }
                    newProduct.setMessage(giftMessage);
                }
                // coupon check
                if (fields[0].contains("coupon")) {
                    Product tiedProduct = Product.getAllProduct().get(fields[1].trim());
                    // System.out.println(fields[1].trim());
                    String stringValue = fields[2].trim();

                    String discountString = fields[3].trim();
                    Coupon newCoupon;
                    if (fields[0].contains("percent")) {
                        newCoupon = new PerCentCoupon(tiedProduct, stringValue, Integer.parseInt(discountString));
                    } else {
                        newCoupon = new PriceCoupon(tiedProduct, stringValue, Double.parseDouble(discountString));
                    }
                }

            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        try (Stream<String> lines = Files.lines(Paths.get("carts.txt"))) {
            lines.forEach(line -> {
                String[] fields = line.split(",");

                // product check
                if (fields[0].contains("CART")) {
                    String name = fields[1].trim();
                    String description = fields[2].trim();
                    int quantity = Integer.parseInt(fields[3].trim());
                    double price = Double.parseDouble(fields[4].trim());
                    String taxType = fields[5].trim();
                    boolean isGift = fields[6].equals("GIFT");
                    String giftMessage = isGift ? fields[7].trim() : null;

                    Product newProduct;
                    if (fields[0].equals("physicalproduct")) {
                        double weight = Double.parseDouble(fields[8].trim());
                        newProduct = new PhysicalProduct(name, description, quantity, price, weight,
                                TaxType.valueOf(taxType), isGift);

                    } else {
                        newProduct = new DigitalProduct(name, description, quantity, price, TaxType.valueOf(taxType),
                                isGift);
                    }
                    newProduct.setMessage(giftMessage);
                }
                // coupon check
                if (fields[0].contains("coupon")) {
                    Product tiedProduct = Product.getAllProduct().get(fields[1].trim());
                    // System.out.println(fields[1].trim());
                    String stringValue = fields[2].trim();

                    String discountString = fields[3].trim();
                    Coupon newCoupon;
                    if (fields[0].contains("percent")) {
                        newCoupon = new PerCentCoupon(tiedProduct, stringValue, Integer.parseInt(discountString));
                    } else {
                        newCoupon = new PriceCoupon(tiedProduct, stringValue, Double.parseDouble(discountString));
                    }
                }

            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void write() {

        ArrayList<String> data = new ArrayList<>();
        data.add("Product Type, Name, Description, Quantity, Price, TaxType, isGift, GiftMessage, Weight(Physical Product)");
        String line;
        for (String s : Product.getAllProduct().keySet()) {
            line = "";
            Product p = Product.getAllProduct().get(s);
            if (p instanceof PhysicalProduct) {
                line += "physicalproduct, ";
            } else {
                line += "digitalproduct, ";
            }
            String gift = p.isGift() ? "GIFT" : "NOTGIFT";
            line += p.getName() + ", " + p.getDescription() + ", " + p.getQuantity() + ", " + p.getPrice() + ", "
                    + p.getTaxType().name() + ", " + gift + ", " + p.getMessage() + ", ";
            if (p instanceof PhysicalProduct) {
                line += ((PhysicalProduct) p).getWeight();
            }
            data.add(line);
        }
        data.add("");
        data.add("Coupon Type, Tied Product , String Value, Discount(Percent/Price)");
        for (Coupon c : Coupon.getAllCoupons()) {
            line = "";
            String discount;
            if (c instanceof PerCentCoupon) {
                line += "percentcoupon, ";
                discount = String.valueOf(((PerCentCoupon) c).getPercent());
            } else {
                line += "pricecoupon, ";
                discount = String.valueOf(((PriceCoupon) c).getPrice());
            }
            line += c.getTiedProduct().getName() + ", " + c.getStringValue() + ", " + discount;
            data.add(line);
        }
        try {
            Files.write(Paths.get("products.txt"), data.stream()
                    .collect(Collectors.joining(System.lineSeparator()))
                    .getBytes());
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
/**
 * @author <Nguyen Thanh Hung - s3926041>
 */
package asm1;

public class PhysicalProduct extends Product {
    private double weight;

    public PhysicalProduct(String name, String description, int quantity, double price, double weight,TaxType taxType) {
        super(name, description, quantity, price,taxType);
        this.weight = weight;
    }
    public PhysicalProduct(String name, String description, int quantity, double price, double weight,TaxType taxType,boolean isGift) {
        super(name, description, quantity, price,taxType,isGift);
        this.weight = weight;
    }
    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getType() {
        return "PHYSICAL";
    }
}
/**
 * @author <Nguyen Thanh Hung - s3926041>
 */
package asm1;


public class DigitalProduct extends Product {
    public DigitalProduct(String name, String description, int quantity, double price,TaxType taxType) {
        super(name, description, quantity, price,taxType);
    }
    public DigitalProduct(String name, String description, int quantity, double price,TaxType taxType,boolean isGift) {
        super(name, description, quantity, price,taxType,isGift);
    }

    public String getType() {
        return "DIGITAL";
    }
}
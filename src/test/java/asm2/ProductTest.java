/**
 * @author Group 15
 */
package asm2;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.Test;

public class ProductTest {
    @Test
    void createProduct() {
        
        Product p1 = new PhysicalProduct("p1", "p1", 110, 110.0, 10.0, TaxType.valueOf("TAX_FREE"), false);
        Product p2 = new DigitalProduct("p2", "p2", 120, 120.0, TaxType.valueOf("TAX_FREE"), false);
        assertEquals(p1, Product.getAllProduct().get("p1"));
        assertEquals(p2, Product.getAllProduct().get("p2"));
    }
    @Test
    void editProduct(){
        Product p1 = new PhysicalProduct("p1", "p1", 110, 110.0, 10.0, TaxType.valueOf("TAX_FREE"), false);
        p1.setName("p2");
        p1.setDescription("p2");
        p1.setQuantity(12);
        p1.setPrice(23.0);
        ((PhysicalProduct) p1).setWeight(12.0);
        p1.setCanBeGift(true);
        assertEquals("p2",p1.getName());
        assertEquals("p2",p1.getDescription());
        assertEquals(12,p1.getQuantity());
        assertEquals(23.0,p1.getPrice());
        assertEquals(12.0, ((PhysicalProduct) p1).getWeight());
        assertEquals(true,p1.canBeGift());
    }

}

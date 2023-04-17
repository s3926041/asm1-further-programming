/**
 * @author <Nguyen Thanh Hung - s3926041>
 */
package asm1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    @Test
    public void productTypeTest() {
        // create some products
        Product p1 = new PhysicalProduct("book", "book", 10, 25.0, 2.0);
        Product p2 = new DigitalProduct("book2", "book", 10, 25.0);

      


        assertEquals("PHYSICAL - book", p1.toString());
        assertEquals("DIGITAL - book2",p2.toString() );
   
    }

    @Test
    public void giftProductTest(){
        GiftProduct p3 = new PhysicalProduct("book3", "book", 10, 25.0, 2.0);
        p3.setMessage("this is book 3");

        assertEquals("this is book 3", p3.getMessage());
    }

}

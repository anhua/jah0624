package jah0624;
import jah0624.Shop;
import org.junit.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.function.Executable;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit testing the Shop Rental activities
 */
public class RentalTest {
	Shop shop;
	@Before                                         
    public void setUp() {
        shop = new Shop();
    }
    @Test
    @DisplayName("Test the exception when checkout with 101 discount")
    public void testCase1() {
    	assertThrows(Exception.class, new Executable() {
            
           @Override
           public void execute() throws Throwable {
        	   shop.checkOut("JAKR", 5, 101, "9/3/15");
           }
       });
    }
    
    @Test
    @DisplayName("Test input: LADW 3 10 7/2/20")
    public void testCase2() throws Exception {
    	RentalAgreement agreement = shop.checkOut("LADW", 3, 10, "7/2/20");
    	assertEquals(agreement.getFinalCharge(), 3.58f, 0.001);
    }
    
    @Test
    @DisplayName("Test input: CHNS 5 25 7/2/15")
    public void testCase3() throws Exception {
    	RentalAgreement agreement = shop.checkOut("CHNS", 5, 25, "7/2/15");
    	assertEquals(agreement.getFinalCharge(), 3.35f, 0.001);
    }
    
    @Test
    @DisplayName("Test input: JAKD 6 0 9/3/15")
    public void testCase4() throws Exception {
    	RentalAgreement agreement = shop.checkOut("JAKD", 6, 0, "9/3/15");
    	assertEquals(agreement.getFinalCharge(), 8.97f, 0.001);
    }
    @Test
    @DisplayName("Test input: JAKR 9 0 7/2/15")
    public void testCase5() throws Exception {
    	RentalAgreement agreement = shop.checkOut("JAKR", 9, 0, "7/2/15");
    	assertEquals(agreement.getFinalCharge(), 14.95f, 0.001);
    }
    
    @Test
    @DisplayName("Test input: JAKR 4 50 7/2/20")
    public void testCase6() throws Exception {
    	RentalAgreement agreement = shop.checkOut("JAKR", 4, 50, "7/2/20");
    	assertEquals(agreement.getFinalCharge(), 1.49f, 0.001);
    }
    
}
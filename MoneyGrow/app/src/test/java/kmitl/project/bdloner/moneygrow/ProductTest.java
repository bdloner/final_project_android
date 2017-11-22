package kmitl.project.bdloner.moneygrow;

import org.junit.Test;

import kmitl.project.bdloner.moneygrow.model.Product;
import kmitl.project.bdloner.moneygrow.model.Wallet;

import static org.junit.Assert.assertEquals;

/**
 * Created by BDLoneR on 21/11/2560.
 */

public class ProductTest {

    @Test
    public void ProductTestGetSetCon(){

        int imageId = 12345678;
        String title = "ดอกเบี้ย";
        String description = "เงินที่ได้รับจากดอกเบี้ยการฝากธนาคาร";

        Product product = new Product(imageId, title, description);

        assertEquals(12345678, product.getImageId());
        assertEquals("ดอกเบี้ย", product.getTitle());
        assertEquals("เงินที่ได้รับจากดอกเบี้ยการฝากธนาคาร", product.getDescription());

        product.setImageId(12345678);
        product.setTitle("ดอกเบี้ย");
        product.setDescription("เงินที่ได้รับจากดอกเบี้ยการฝากธนาคาร");

    }

}

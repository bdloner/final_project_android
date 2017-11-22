package kmitl.project.bdloner.moneygrow;

import org.junit.Test;

import kmitl.project.bdloner.moneygrow.model.Image;
import kmitl.project.bdloner.moneygrow.model.Product;

import static org.junit.Assert.assertEquals;

/**
 * Created by BDLoneR on 22/11/2560.
 */

public class ImageTest {

    @Test
    public void ImageTestGetSetCon(){

        int imageId = 12345678;
        String title = "รถยนต์";

        Image image = new Image(imageId, title);

        assertEquals(12345678, image.getImage_id());
        assertEquals("รถยนต์", image.getImage_title());

        image.setImage_id(12345678);
        image.setImage_title("รถยนต์");

    }

}

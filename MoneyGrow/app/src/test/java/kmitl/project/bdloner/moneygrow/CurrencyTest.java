package kmitl.project.bdloner.moneygrow;

import org.junit.Test;

import kmitl.project.bdloner.moneygrow.model.Currency;
import kmitl.project.bdloner.moneygrow.model.Image;

import static org.junit.Assert.assertEquals;

/**
 * Created by BDLoneR on 22/11/2560.
 */

public class CurrencyTest {

    @Test
    public void CurrencyTestGetCon(){

        String name = "USD";
        double rate = 5.256;

        Currency currency = new Currency(name, rate);

        assertEquals("USD", currency.getName());
        assertEquals(5.256, currency.getRate(), 5.256);

    }

}

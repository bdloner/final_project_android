package kmitl.project.bdloner.moneygrow;

/**
 * Created by BDLoneR on 21/11/2560.
 */

import org.junit.Test;

import kmitl.project.bdloner.moneygrow.model.Wallet;

import static org.junit.Assert.*;

public class WalletTest {

    @Test
    public void WalletTestGetSetCon(){

        String image_id_wallet = "2131765337";
        String cat_name_wallet = "เงินเดือน";
        String date_wallet = "วันอังคาร , 20 พฤศจิกายน 2020";
        String amount_wallet = "50000";
        String note_wallet = "เฮงๆรวยๆ";
        String wid="1";

        Wallet wallet = new Wallet(image_id_wallet, cat_name_wallet, date_wallet,
                amount_wallet, note_wallet, wid);

        assertEquals("2131765337", wallet.getImage_id_wallet());
        assertEquals("เงินเดือน", wallet.getCat_name_wallet());
        assertEquals("วันอังคาร , 20 พฤศจิกายน 2020", wallet.getDate_wallet());
        assertEquals("50000", wallet.getAmount_wallet());
        assertEquals("เฮงๆรวยๆ", wallet.getNote_wallet());
        assertEquals("1", wallet.getWid());

        wallet.setImage_id_wallet("2131765337");
        wallet.setCat_name_wallet("เงินเดือน");
        wallet.setDate_wallet("วันอังคาร , 20 พฤศจิกายน 2020");
        wallet.setAmount_wallet("50000");
        wallet.setNote_wallet("เฮงๆรวยๆ");
        wallet.setWid("1");

        Wallet wallet2 = new Wallet();

    }


}

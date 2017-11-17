package kmitl.project.bdloner.moneygrow;

/**
 * Created by BDLoneR on 16/11/2560.
 */

public class Wallet {

    private String cat_name_wallet;
    private String date_wallet;
    private String note_wallet;
    private String amount_wallet;
    private String image_id_wallet;
    private String wid;

    public Wallet(){

    }

    public Wallet(String image_id_wallet, String cat_name_wallet, String date_wallet,
                  String amount_wallet, String note_wallet, String wid) {
        this.image_id_wallet = image_id_wallet;
        this.cat_name_wallet = cat_name_wallet;
        this.date_wallet = date_wallet;
        this.amount_wallet = amount_wallet;
        this.note_wallet = note_wallet;
        this.wid = wid;
    }

    public String getImage_id_wallet() {
        return image_id_wallet;
    }

    public void setImage_id_wallet(String image_id_wallet) {
        this.image_id_wallet = image_id_wallet;
    }

    public String getCat_name_wallet() {
        return cat_name_wallet;
    }

    public void setCat_name_wallet(String cat_name_wallet) {
        this.cat_name_wallet = cat_name_wallet;
    }

    public String getWid() {
        return wid;
    }

    public void setWid(String wid) {
        this.wid = wid;
    }

    public String getDate_wallet() {
        return date_wallet;
    }

    public void setDate_wallet(String date_wallet) {
        this.date_wallet = date_wallet;
    }

    public String getNote_wallet() {
        return note_wallet;
    }

    public void setNote_wallet(String note_wallet) {
        this.note_wallet = note_wallet;
    }

    public String getAmount_wallet() {
        return amount_wallet;
    }

    public void setAmount_wallet(String amount_wallet) {
        this.amount_wallet = amount_wallet;
    }


}

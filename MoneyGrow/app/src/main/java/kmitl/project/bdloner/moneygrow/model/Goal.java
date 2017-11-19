package kmitl.project.bdloner.moneygrow.model;

/**
 * Created by BDLoneR on 11/11/2560.
 */

public class Goal {

    private String img_id;
    private String title_goal;
    private String start_goal;
    private String amount_goal;
    private String description_goal;
    private String date_goal;
    private String cid;

    public Goal() {

    }

    public Goal(String img_id, String title_goal, String start_goal, String amount_goal, String description_goal, String date_goal, String cid) {
        this.img_id = img_id;
        this.title_goal = title_goal;
        this.start_goal = start_goal;
        this.amount_goal = amount_goal;
        this.description_goal = description_goal;
        this.date_goal = date_goal;
        this.cid = cid;
    }

    public String getImg_id() {
        return img_id;
    }

    public void setImg_id(String img_id) {
        this.img_id = img_id;
    }

    public String getStart_goal() {
        return start_goal;
    }

    public void setStart_goal(String start_goal) {
        this.start_goal = start_goal;
    }


    public String getTitle_goal() {
        return title_goal;
    }

    public void setTitle_goal(String title_goal) {
        this.title_goal = title_goal;
    }

    public String getAmount_goal() {
        return amount_goal;
    }

    public void setAmount_goal(String amount_goal) {
        this.amount_goal = amount_goal;
    }

    public String getDescription_goal() {
        return description_goal;
    }

    public void setDescription_goal(String description_goal) {
        this.description_goal = description_goal;
    }

    public String getDate_goal() {
        return date_goal;
    }

    public void setDate_goal(String date_goal) {
        this.date_goal = date_goal;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }
}

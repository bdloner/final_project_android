package kmitl.project.bdloner.moneygrow;

import org.junit.Test;

import kmitl.project.bdloner.moneygrow.model.Goal;
import kmitl.project.bdloner.moneygrow.model.Wallet;

import static org.junit.Assert.assertEquals;

/**
 * Created by BDLoneR on 22/11/2560.
 */

public class GoalTest {

    @Test
    public void GoalTestGetSetCon(){

        String img_id = "123456789";
        String title_goal = "บ้าน";
        String start_goal = "0";
        String amount_goal = "500,000";
        String description_goal = "อยากมีบ้านเป็นของตัวเอง";
        String date_goal = "22 พฤศจิกายน 2017";
        String cid = "1";

        Goal goal = new Goal(img_id, title_goal, start_goal, amount_goal,
                description_goal, date_goal, cid);

        assertEquals("123456789", goal.getImg_id());
        assertEquals("บ้าน", goal.getTitle_goal());
        assertEquals("0", goal.getStart_goal());
        assertEquals("500,000", goal.getAmount_goal());
        assertEquals("อยากมีบ้านเป็นของตัวเอง", goal.getDescription_goal());
        assertEquals("22 พฤศจิกายน 2017", goal.getDate_goal());
        assertEquals("1", goal.getCid());

        goal.setImg_id("2131765337");
        goal.setTitle_goal("เงินเดือน");
        goal.setStart_goal("วันอังคาร , 20 พฤศจิกายน 2020");
        goal.setAmount_goal("50000");
        goal.setDescription_goal("เฮงๆรวยๆ");
        goal.setDate_goal("22 พฤศจิกายน 2017");
        goal.setCid("1");

        Goal goal2 = new Goal();

    }

}

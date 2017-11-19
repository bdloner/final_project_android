package kmitl.project.bdloner.moneygrow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AddMoneyGoal extends AppCompatActivity {

    private CustomTextView oldAmount;
    private EditText addNewMoney;
    private Button addBtn, cancelBtn;

    private myDbAdapter dbAdapter;
    private String oldId;
    private List<Goal> listItemGoal = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_money_goal);

        addNewMoney = findViewById(R.id.add_money_goal);
        oldAmount = findViewById(R.id.old_amount);
        addBtn = findViewById(R.id.add_btn);
        cancelBtn = findViewById(R.id.cancel_btn);

        dbAdapter = new myDbAdapter(getApplicationContext());
        oldId = getIntent().getStringExtra("oldId");

        List<List> datas = dbAdapter.getData();

        listItemGoal = new ArrayList<>();

        for (int i=0; i<datas.size();i++) {
            List<String> eachEvent = datas.get(i);
            if (eachEvent.get(6).equals(oldId)) {
                oldAmount.setText(eachEvent.get(2));
            }
        }

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String new_money = addNewMoney.getText().toString();
                String txtOldAmount = oldAmount.getText().toString();

                String old_amount = txtOldAmount.replace(",", "");

                int add_new_money = Integer.parseInt(old_amount) + Integer.parseInt(new_money);

                if(new_money.equals("")){

                    Toast.makeText(getApplicationContext(),"กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                }
                else {
                    long id = dbAdapter.updateMoney(String.valueOf(NumberFormat.getNumberInstance(Locale.US).format(add_new_money)), oldId);
                    if(id <=0){
                        Toast.makeText(getApplicationContext(),"เพิ่มเงินผิดพลาด",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getApplicationContext(),"เพิ่มเงินสำเร็จ",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}

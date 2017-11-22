package kmitl.project.bdloner.moneygrow.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import kmitl.project.bdloner.moneygrow.R;
import kmitl.project.bdloner.moneygrow.controller.myDbAdapter;
import kmitl.project.bdloner.moneygrow.init.CustomTextView;
import kmitl.project.bdloner.moneygrow.model.Wallet;

public class TotalWalletActivity extends AppCompatActivity {

    List<Wallet> listAllWallet = new ArrayList<>();
    private myDbAdapter dbAdapter;
    private CustomTextView txtIncome, txtExpenses, txtTotal, txtOldIncome, txtOldExpenses;
    private Button homeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_wallet);

        txtIncome = findViewById(R.id.txt_income);
        txtExpenses = findViewById(R.id.txt_expenses);
        txtTotal = findViewById(R.id.txt_total);
        txtOldIncome = findViewById(R.id.old_income);
        txtOldExpenses = findViewById(R.id.old_expenses);

        homeBtn = findViewById(R.id.home_btn);

        dbAdapter = new myDbAdapter(getApplicationContext());
        List<List> datas = dbAdapter.getDataWallet();

        Double temp = 0.0, tempIn =0.0, tempEx=0.0;
        String txtTemp = "";
        for(int i=0;i<datas.size();i++){
            txtTemp = String.valueOf(datas.get(i).get(2));

            temp += Double.parseDouble(txtTemp);

            Double tempCheck = Double.parseDouble(txtTemp);

            if(tempCheck >= 0){
                tempIn += tempCheck;
                txtIncome.setText(String.valueOf(tempIn));
            } else if(tempCheck < 0){
                tempEx += tempCheck;
                txtExpenses.setText(String.valueOf(tempEx));
            }

        }

        txtTotal.setText(String.valueOf(temp));


        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }


}

package kmitl.project.bdloner.moneygrow;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TotalWalletActivity extends AppCompatActivity {

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

        /*SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);*/

        SharedPreferences sp = getSharedPreferences("PREF_NAME", Context.MODE_PRIVATE);

        String dataIncome = sp.getString("income", "0");

        txtOldIncome.setText(dataIncome);

        txtIncome.getText().toString();

        String testOld = txtOldIncome.getText().toString();
        String testNew = txtIncome.getText().toString();

        int test1 = Integer.parseInt(testOld);
        int test2 = Integer.parseInt(testNew);

        test2 = test1+test2;

        txtIncome.setText(String.valueOf(test2));

        /*int x = Integer.parseInt(txtIncome.getText().toString());

        int income = Integer.parseInt(dataIncome);

        x += income;

        txtIncome.setText(String.valueOf(x));*/

//        String dataExpenses = prefs.getString("expenses", "0");
//
//        int y = Integer.parseInt(txtExpenses.getText().toString());
//
//        int expenses = Integer.parseInt(dataExpenses);
//
//        y += expenses;
//
//        txtExpenses.setText(String.valueOf(y));

        /*x = getIntent().getExtras().getInt("income");
        txtIncome.setText(String.valueOf(x));

        y = getIntent().getExtras().getInt("expenses");
        txtExpenses.setText(String.valueOf(y));

        int total = x-y;
        txtTotal.setText(String.valueOf(total));*/

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }


}

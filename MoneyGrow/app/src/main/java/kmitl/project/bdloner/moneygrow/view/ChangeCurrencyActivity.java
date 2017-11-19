package kmitl.project.bdloner.moneygrow.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.DecimalFormat;

import kmitl.project.bdloner.moneygrow.R;
import kmitl.project.bdloner.moneygrow.init.CustomTextView;

public class ChangeCurrencyActivity extends AppCompatActivity {

    private CustomTextView tvTitle, tvSubTitle, tvOutputName, tvOutputRate;
    private EditText etInput;
    private Button btnCalculate, btn_back;

    private String currencyName;
    private double currencyRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_currency);

        tvTitle = findViewById(R.id.tvTitle);
        tvSubTitle = findViewById(R.id.tvSubTitle);
        tvOutputName = findViewById(R.id.tvOutputName);
        tvOutputRate = findViewById(R.id.tvOutputRate);

        etInput = findViewById(R.id.etInput);
        btnCalculate = findViewById(R.id.btnCalculate);
        btn_back = findViewById(R.id.btnBack);

        Intent intent = getIntent();
        currencyName = intent.getStringExtra("currency_name");
        currencyRate = intent.getDoubleExtra("currency_rate", 0);

        tvTitle.setText("จาก USD เป็น " + currencyName.toUpperCase());
        tvSubTitle.setText("Rate 1 : " + currencyRate);
        tvOutputName.setText(currencyName.toUpperCase() + ": ");

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etInput.getText().toString().length() == 0) {
                    return;
                }

                double input;
                try {
                    input = Double.parseDouble(etInput.getText().toString());
                } catch (NumberFormatException e) {
                    etInput.setText("");
                    return;
                }

                double output = input * currencyRate;
                DecimalFormat decimalFormat = new DecimalFormat("#,###.##");
                tvOutputRate.setText(decimalFormat.format(output));
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}

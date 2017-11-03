package kmitl.project.bdloner.moneygrow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class CalculatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        CustomTextView dateText = (CustomTextView) findViewById(R.id.dateText);
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());

        dateText.setText(currentDateTimeString);

    }
}

package kmitl.project.bdloner.moneygrow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

public class CalculatorActivity extends AppCompatActivity {

    private CustomTextView screen;
    private String display = "";
    private String currentOperator = "";
    private String result = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        screen = (CustomTextView) findViewById(R.id.screen);
        screen.setText(display);

        //DateTime
        CustomTextView dateText = (CustomTextView) findViewById(R.id.dateText);
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());

        dateText.setText(currentDateTimeString);

    }

    private void updateScreen(){
        screen.setText(display);
    }

    public  void onClickNumber (View v){
        if(result != ""){
            clear();
            updateScreen();
        }
        Button b = (Button) v;
        display += b.getText();
        updateScreen();
    }

    private boolean isOperator(char op){
        switch (op){
            case '+':
            case '-':
            case '*':
            case '/': return true;
            default: return false;
        }
    }

    public void onClickOperator(View v){
        if(display == "") return;
        Button b = (Button) v;
        if(result != ""){
            String displayR = result;
            clear();
            display = displayR;
        }

        if(currentOperator != ""){
            if(isOperator(display.charAt(display.length()-1))){
                display.replace(display.charAt(display.length()-1), b.getText().charAt(0));
                updateScreen();
                return;
            }
            else {
                getResult();
                display = result;
                result = "";
            }
            currentOperator = b.getText().toString();
        }
        display += b.getText();
        currentOperator = b.getText().toString();
        updateScreen();
    }

    private void clear(){
        display = "";
        currentOperator = "";
        result = "";
    }

    public void onClickClear(View v){
        clear();
        updateScreen();
    }

    private double operate(String a, String b, String op){
        result = "";
        switch (op){
            case "+" : return Double.valueOf(a) + Double.valueOf(b);
            case "-" : return Double.valueOf(a) - Double.valueOf(b);
            case "x" : return Double.valueOf(a) * Double.valueOf(b);
            case "/" : try{
                return Double.valueOf(a) / Double.valueOf(b);
            }catch (Exception e){
                Log.d("CalError", e.getMessage());
            }
            default: return -1;
        }
    }

    private boolean getResult(){
        if(currentOperator == "") return false;
        String[] operation = display.split(Pattern.quote(currentOperator));
        if(operation.length < 2) return false;
        result = String.valueOf(operate(operation[0], operation[1], currentOperator));
        return true;
    }

    public void onClickEqual(View v){
        if (display == "") return;
        if(!getResult()) return;
        screen.setText(String.valueOf(result));
    }


    public void onClickCatIncome(View view) {
        Intent intent = new Intent(getApplicationContext(), CategoryIncome.class);
        startActivity(intent);
    }
}

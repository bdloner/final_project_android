package kmitl.project.bdloner.moneygrow;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

public class CalculatorActivity extends AppCompatActivity {

    private EditText screen;
    private Button test;
    private String display = "";
    private String currentOperator = "";
    private String result = "";
    private Calendar myCalendar;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        myCalendar = Calendar.getInstance();

        editText= (EditText) findViewById(R.id.dateText);
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };

        editText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(CalculatorActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        //Display Calculator
        screen = (EditText) findViewById(R.id.screen);
        screen.setEnabled(false);
        screen.setText(display);

        //choose income or expen
        int x = getIntent().getExtras().getInt("btnCatIn");

        if(x == 0){
            test = (Button) findViewById(R.id.btnCatIn);
            test.setText("เลือกประเภทรายรับ");
            View a = findViewById(R.id.btnCatIn);
            a.setVisibility(View.VISIBLE);
        }else if(x == 1){
            test = (Button) findViewById(R.id.btnCatEx);
            test.setText("เลือกประเภทรายจ่าย");
            View b = findViewById(R.id.btnCatEx);
            b.setVisibility(View.VISIBLE);
        }

        /*String strX = String.valueOf(x);*/

        //DateTime
        editText.setHint(formatTh().format(myCalendar.getTime()));

    }

    private Format formatTh(){
        Format formatter;
        formatter = new SimpleDateFormat("EEEE , dd MMMM yyyy", new Locale("th", "TH"));
        return formatter;
    }

    private void updateLabel() {

        editText.setText(formatTh().format(myCalendar.getTime()));
        editText.setTextColor(Color.WHITE);
        editText.setTextSize(15);
        editText.setGravity(Gravity.CENTER);

    }

    private void updateScreen(){
        screen.setText(display);
    }

    public  void onClickNumber (View v){
        if(result != "" || screen.getText().toString().equals("0") || screen.getText().toString().equals("00")){
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
        if( screen.getText().toString().trim().equals("")) {

            screen.setError("Money is required!");

        } else {
            screen.setError(null);

            int x = getIntent().getExtras().getInt("btnCatIn");
            if (x == 0){
                Intent intent = new Intent(getApplicationContext(), CategoryActivity.class);
                intent.putExtra("catChoose", 0);
                startActivity(intent);
            } else if(x == 1){
                Intent intent = new Intent(getApplicationContext(), CategoryActivity.class);
                intent.putExtra("catChoose", 1);
                startActivity(intent);
            }
        }



    }
}

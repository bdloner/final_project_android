package kmitl.project.bdloner.moneygrow;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddGoalActivity extends AppCompatActivity {

    private EditText title, start, amount, desc, dateGoal;
    private myDbAdapter helper;
    private Button cancelBtn, addBtn;
    private Calendar myCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_goal);

        title= findViewById(R.id.title_goal);
        start= findViewById(R.id.start_goal);
        amount= findViewById(R.id.amount_goal);
        desc= findViewById(R.id.description_goal);
        dateGoal= findViewById(R.id.date_goal);
        cancelBtn = findViewById(R.id.cancelBtn);
        addBtn = findViewById(R.id.addBtn);

        start.addTextChangedListener(onTextChangedListenerStart());
        amount.addTextChangedListener(onTextChangedListenerEnd());
        myCalendar = Calendar.getInstance();

        helper = new myDbAdapter(this);

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

        dateGoal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(AddGoalActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add();
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private Format formatTh(){
        Format formatter;
        formatter = new SimpleDateFormat("dd MMMM yyyy", new Locale("th", "TH"));
        return formatter;
    }

    private void updateLabel() {

        dateGoal.setText(formatTh().format(myCalendar.getTime()));
        dateGoal.setGravity(Gravity.CENTER);

    }

    public void add() {
        Goal goal = new Goal();

        goal.setTitle_goal(title.getText().toString());
        goal.setStart_goal(start.getText().toString());
        goal.setAmount_goal(amount.getText().toString());
        goal.setDescription_goal(desc.getText().toString());
        goal.setDate_goal(dateGoal.getText().toString());

        String txtStart = goal.getStart_goal().replace(",", "");
        String txtAmount = goal.getAmount_goal().replace(",", "");

        if(goal.getTitle_goal().isEmpty() || goal.getStart_goal().isEmpty()
                || goal.getAmount_goal().isEmpty() || goal.getDescription_goal().isEmpty()
                || goal.getDate_goal().isEmpty())
        {
            Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
        }
        else
        {
            long id = helper.insertData(goal.getTitle_goal(),goal.getStart_goal(),
                    goal.getAmount_goal(),goal.getDescription_goal(), goal.getDate_goal());
            if(id<=0)
            {
                Toast.makeText(getApplicationContext(), "เพิ่มเป้าหมายไม่สำเร็จ", Toast.LENGTH_SHORT).show();
                title.setText("");
                start.setText("");
                amount.setText("");
                desc.setText("");
                dateGoal.setText("");
            } else
            {
                Toast.makeText(getApplicationContext(), "เพิ่มข้อมูลสำเร็จ", Toast.LENGTH_SHORT).show();
                title.setText("");
                start.setText("");
                amount.setText("");
                desc.setText("");
                dateGoal.setText("");
                finish();
            }
        }
    }

    private TextWatcher onTextChangedListenerEnd() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                amount.removeTextChangedListener(this);

                try {
                    String originalString = s.toString();

                    Long longval;
                    if (originalString.contains(",")) {
                        originalString = originalString.replaceAll(",", "");
                    }
                    longval = Long.parseLong(originalString);

                    DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
                    formatter.applyPattern("#,###,###,###");
                    String formattedString = formatter.format(longval);

                    //setting text after format to EditText
                    amount.setText(formattedString);
                    amount.setSelection(amount.getText().length());
                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                }

                amount.addTextChangedListener(this);
            }
        };
    }

    private TextWatcher onTextChangedListenerStart() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                start.removeTextChangedListener(this);

                try {
                    String originalString = s.toString();

                    Long longval;
                    if (originalString.contains(",")) {
                        originalString = originalString.replaceAll(",", "");
                    }
                    longval = Long.parseLong(originalString);

                    DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
                    formatter.applyPattern("#,###,###,###");
                    String formattedString = formatter.format(longval);

                    //setting text after format to EditText
                    start.setText(formattedString);
                    start.setSelection(start.getText().length());
                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                }

                start.addTextChangedListener(this);
            }
        };
    }
}

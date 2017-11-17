package kmitl.project.bdloner.moneygrow;

import android.app.DatePickerDialog;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class EditWalletActivity extends AppCompatActivity {

    private ImageView oldImage;
    private EditText oldTitle, newDesc, newDate, newAmount;
    private Button editBtn, cancelBtn;

    private myDbAdapter dbAdapter;
    private Button delete;
    List<Wallet> listAllWallet = new ArrayList<>();
    private String oldId;
    private Calendar myCalendar;

    private String img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_wallet);

        oldImage = findViewById(R.id.old_image);
        oldTitle = findViewById(R.id.old_title);
        newDesc = findViewById(R.id.new_desc);
        newDate = findViewById(R.id.new_date);
        newAmount = findViewById(R.id.new_amount);

        editBtn = findViewById(R.id.edit_btn);
        cancelBtn = findViewById(R.id.cancel_btn);

        myCalendar = Calendar.getInstance();

        dbAdapter = new myDbAdapter(getApplicationContext());
        List<List> datas = dbAdapter.getDataWallet();
        oldId = getIntent().getStringExtra("oldId");

        listAllWallet = new ArrayList<>();
        for (int i=0; i<datas.size();i++) {
            List<String> eachWallet = datas.get(i);
            if (eachWallet.get(5).equals(oldId)) {

                oldImage.setImageResource(Integer.parseInt(eachWallet.get(4)));
                img = eachWallet.get(4);

                oldTitle.setText(eachWallet.get(0));
                newDesc.setText(eachWallet.get(3));
                newDate.setText(eachWallet.get(1));
                newAmount.setText(eachWallet.get(2));

            }
        }

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {

                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };

        newDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(EditWalletActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickEditWallet();
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void clickEditWallet() {

        String old_image = img;
        String old_title = oldTitle.getText().toString();
        String new_date = newDate.getText().toString();
        String new_amount = newAmount.getText().toString();
        String new_desc = newDesc.getText().toString();

        if(new_date.equals("") || new_amount.equals("")){

            Toast.makeText(getApplicationContext(),"กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
        }
        else {

            int id = dbAdapter.updateDetailWallet(new_date, new_amount  , new_desc , old_image , old_title, oldId);
            if(id <=0){

                Toast.makeText(getApplicationContext(),"แก้ไขข้อมูลไม่สำเร็จ",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getApplicationContext(),"แก้ไขข้อมูลสำเร็จ",Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    private Format formatTh(){
        Format formatter;
        formatter = new SimpleDateFormat("EEEE , dd MMMM yyyy", new Locale("th", "TH"));
        return formatter;
    }

    private void updateLabel() {

        newDate.setText(formatTh().format(myCalendar.getTime()));
        newDate.setGravity(Gravity.CENTER);

    }

}

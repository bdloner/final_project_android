package kmitl.project.bdloner.moneygrow.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import kmitl.project.bdloner.moneygrow.R;
import kmitl.project.bdloner.moneygrow.controller.GridViewAdapter;
import kmitl.project.bdloner.moneygrow.controller.ListViewAdapter;
import kmitl.project.bdloner.moneygrow.controller.myDbAdapter;
import kmitl.project.bdloner.moneygrow.init.CustomTextView;
import kmitl.project.bdloner.moneygrow.model.Product;
import kmitl.project.bdloner.moneygrow.model.Wallet;

public class CategoryActivity extends AppCompatActivity {

    static final int VIEW_MODE_LISTVIEW = 0;
    static final int VIEW_MODE_GRIDVIEW = 1;
    private ViewStub stubGrid;
    private ViewStub stubList;
    private ListView listView;
    private GridView gridView;
    private ListViewAdapter listViewAdapter;
    private GridViewAdapter gridViewAdapter;
    private CustomTextView textTopicCat;
    private List<Product> productList;
    private int currentViewMode = 0;
    private myDbAdapter helper;
    AdapterView.OnItemClickListener onItemClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            add(position);

            //Do any thing when user click to item
            Toast.makeText(getApplicationContext(), productList.get(position).getTitle() + " - " + productList.get(position).getDescription(), Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        helper = new myDbAdapter(this);

        stubList = (ViewStub) findViewById(R.id.stub_list);
        stubGrid = (ViewStub) findViewById(R.id.stub_grid);

        //Inflate ViewStub before get view

        stubList.inflate();
        stubGrid.inflate();

        listView = (ListView) findViewById(R.id.mylistview);
        gridView = (GridView) findViewById(R.id.mygridview);

        //get list of product
        getProductList();

        //Get current view mode in share reference
        SharedPreferences sharedPreferences = getSharedPreferences("ViewMode", MODE_PRIVATE);
        currentViewMode = sharedPreferences.getInt("currentViewMode", VIEW_MODE_LISTVIEW);//Default is view listview
        //Register item lick
        listView.setOnItemClickListener(onItemClick);
        gridView.setOnItemClickListener(onItemClick);

        switchView();
    }

    private void switchView() {

        if (VIEW_MODE_LISTVIEW == currentViewMode) {
            //Display listview
            stubList.setVisibility(View.VISIBLE);
            //Hide gridview
            stubGrid.setVisibility(View.GONE);
        } else {
            //Hide listview
            stubList.setVisibility(View.GONE);
            //Display gridview
            stubGrid.setVisibility(View.VISIBLE);
        }
        setAdapters();
    }

    private void setAdapters() {
        if (VIEW_MODE_LISTVIEW == currentViewMode) {
            listViewAdapter = new ListViewAdapter(this, R.layout.list_item, productList);
            listView.setAdapter(listViewAdapter);
        } else {
            gridViewAdapter = new GridViewAdapter(this, R.layout.grid_item, productList);
            gridView.setAdapter(gridViewAdapter);
        }
    }

    public List<Product> getProductList() {
        //pseudo code to get product, replace your code to get real product here
        int y = getIntent().getExtras().getInt("catChoose");
        textTopicCat = (CustomTextView) findViewById(R.id.textTopicCat);

        if (y == 0) {

            textTopicCat.setText("กรุณาเลือกประเภทรายรับ");
            productList = new ArrayList<>();
            productList.add(new Product(R.drawable.salary, "เงินเดือน", "เงินที่ได้ต่อเดือน"));
            productList.add(new Product(R.drawable.sales, "ยอดขาย", "เงินที่ได้จากการทำยอดขาย"));
            productList.add(new Product(R.drawable.interest, "ดอกเบี้ย", "เงินได้ที่จากการฝากธนาคารหรือการฝากแบบอื่น"));
            productList.add(new Product(R.drawable.saving, "การออม", "เงินที่ได้จากการอดออมทรัพทย์สินของตัวเอง"));
            productList.add(new Product(R.drawable.scholarship, "ทุนการศึกษา", "เงินที่ได้จากการเรียนดีหรือทุนการศึกษาอื่น"));
            productList.add(new Product(R.drawable.reward, "เงินรางวัล", "เงินที่ได้จากการทำความดีหรือเงินรางวัลอื่น"));
            productList.add(new Product(R.drawable.old, "เกษียณ", "เงินที่ได้จากการเกษียณอายุราชการ"));
            productList.add(new Product(R.drawable.other, "อื่นๆ", "เงินที่ได้จากช่องทางอื่น"));

        } else if (y == 1) {

            textTopicCat.setText("กรุณาเลือกประเภทรายจ่าย");
            productList = new ArrayList<>();
            productList.add(new Product(R.drawable.food, "อาหาร", "ค่าใช้จ่ายเกี่ยวกับอาหารหรืออาหารประเภทอื่นๆ"));
            productList.add(new Product(R.drawable.drink, "เครื่องดื่ม", "ค่าใช้จ่ายเกี่ยวกับเครื่องดื่มหรือเครื่องดื่มประเภทอื่นๆ"));
            productList.add(new Product(R.drawable.car, "รถยนต์", "ค่าใช้จ่ายเกี่ยวกับการดูแลรักษารถยนต์ เช่น น้ำมันรถ, ซ่อมบำรุง"));
            productList.add(new Product(R.drawable.taxi, "การเดินทาง", "ค่าใช้จ่ายเกี่ยวกับการเดินทาง เช่น แท็กซี่, รถเมย์"));
            productList.add(new Product(R.drawable.bill, "บิล", "ค่าใช้จ่ายเกี่ยวกับสาธารณูปโภคต่างๆ เช่น ค่าน้ำ, ค่าไฟ"));
            productList.add(new Product(R.drawable.shopping, "ช้อปปิ้ง", "ค่าใช้จ่ายเกี่ยวกับการเที่ยวซื้อของต่างๆ เช่น เดินตลาด, เดินห้าง, ซื้อของออนไลน์"));
            productList.add(new Product(R.drawable.pet, "สัตว์เลี้ยง", "ค่าใช้จ่ายเกี่ยวกับสัตว์เลี้ยง เช่น สุนัข, แมว"));
            productList.add(new Product(R.drawable.games, "บันเทิง", "ค่าใช้จ่ายเกี่ยวกับสิ่งบันเทิงต่างๆ เช่น เกมส์, ภาพยนตร์"));
            productList.add(new Product(R.drawable.healthly, "สุขภาพ", "ค่าใช้จ่ายเกี่ยวกับด้านสุขภาพ เช่น การรักษา, ฟิตเนส"));
            productList.add(new Product(R.drawable.donate, "บริจาค", "ค่าใช้จ่ายเกี่ยวกับการบริจาคต่างๆ เช่น เพื่อการกุศล, ช่วยเหลืองาน"));
            productList.add(new Product(R.drawable.house, "บ้าน", "ค่าใช้จ่ายเกี่ยวกับสิ่งของเครื่องใช้ภายในบ้าน เช่น ซ่อมแซมบ้าน, ปรับปรุงบ้าน"));
            productList.add(new Product(R.drawable.school, "การศึกษา", "ค่าใช้จ่ายเกี่ยวกับค่าศึกษาเล่าเรียน เช่น ค่าเทอม, หนังสือ, อุปกรณ์การเรียน"));
            productList.add(new Product(R.drawable.business, "การลงทุน", "ค่าใช้จ่ายเกี่ยวกับการลงทุนด้านธุรกิจต่างๆ เช่น เชิงธุรกิจ, ขายหุ้น"));
            productList.add(new Product(R.drawable.other, "อื่นๆ", "ค่าใช้จ่ายจากช่องทางอื่น"));

        }
        return productList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_view, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_menu_1:
                currentViewMode = VIEW_MODE_GRIDVIEW;
                switchView();
                //Save view mode in share reference
                SharedPreferences sharedPreferences = getSharedPreferences("ViewMode", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("currentViewMode", currentViewMode);
                editor.commit();
                break;

            case R.id.item_menu_2:
                currentViewMode = VIEW_MODE_LISTVIEW;

                switchView();
                //Save view mode in share reference
                sharedPreferences = getSharedPreferences("ViewMode", MODE_PRIVATE);
                editor = sharedPreferences.edit();
                editor.putInt("currentViewMode", currentViewMode);
                editor.commit();

                break;
        }
        return true;
    }

    public void add(int position) {
        Wallet wallet = new Wallet();

        int y = getIntent().getExtras().getInt("catChoose");

        String date_wallet = getIntent().getExtras().getString("date_wallet");
        String amount_wallet = getIntent().getExtras().getString("amount_wallet");
        String note_wallet = getIntent().getExtras().getString("note_wallet");

        wallet.setCat_name_wallet(productList.get(position).getTitle());
        wallet.setImage_id_wallet(String.valueOf(productList.get(position).getImageId()));
        wallet.setDate_wallet(date_wallet);

        if(y == 0){
            wallet.setAmount_wallet(amount_wallet);
        } else if (y == 1){
            wallet.setAmount_wallet("-"+amount_wallet);
        }

        wallet.setNote_wallet(note_wallet);

        long id = helper.insertDataWallet(wallet.getCat_name_wallet(), wallet.getDate_wallet(),
                wallet.getAmount_wallet(), wallet.getNote_wallet(), wallet.getImage_id_wallet());
        if (id <= 0) {
            Toast.makeText(getApplicationContext(), "เพิ่มลงในกระเป๋าตังไม่สำเร็จ", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(getApplicationContext(), "เพิ่มลงในกระเป๋าตังสำเร็จ", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);

        }
    }

}



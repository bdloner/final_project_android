package kmitl.project.bdloner.moneygrow.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import kmitl.project.bdloner.moneygrow.R;
import kmitl.project.bdloner.moneygrow.controller.ListImageAdapter;
import kmitl.project.bdloner.moneygrow.model.Image;

public class SelectImgActivity extends AppCompatActivity {

    private ListView listView;
    private ListImageAdapter listImageAdapter;
    private List<Image> imageList;
    AdapterView.OnItemClickListener onItemClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            Toast.makeText(getApplicationContext(), imageList.get(position).getImage_title(), Toast.LENGTH_SHORT).show();

            String iconId = String.valueOf(imageList.get(position).getImage_id());

            Intent returnIntent = new Intent();
            returnIntent.putExtra("result", iconId);
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_img);

        listView = findViewById(R.id.img_listview);

        //get list of product
        getImageList();


        listView.setOnItemClickListener(onItemClick);

        listImageAdapter = new ListImageAdapter(this, R.layout.img_item, imageList);
        listView.setAdapter(listImageAdapter);

    }

    public List<Image> getImageList() {

        imageList = new ArrayList<>();
        imageList.add(new Image(R.drawable.car_img, "รถยนต์"));
        imageList.add(new Image(R.drawable.house_img, "บ้าน"));
        imageList.add(new Image(R.drawable.business_img, "ธุรกิจ"));
        imageList.add(new Image(R.drawable.camera_img, "กล้อง"));
        imageList.add(new Image(R.drawable.desktop_img, "คอมพิวเตอร์"));
        imageList.add(new Image(R.drawable.television_img, "โทรทัศน์"));
        imageList.add(new Image(R.drawable.motorbike_img, "มอเตอร์ไซค์"));
        imageList.add(new Image(R.drawable.tip_img, "การท่องเที่ยว"));
        imageList.add(new Image(R.drawable.toy_img, "ของเล่น"));
        imageList.add(new Image(R.drawable.condo_img, "คอนโดฯ"));
        imageList.add(new Image(R.drawable.smartphone_img, "โทรศัพท์"));
        imageList.add(new Image(R.drawable.backpack_img, "กระเป๋า"));
        imageList.add(new Image(R.drawable.cosmetics_img, "เครื่องสำอาง"));
        imageList.add(new Image(R.drawable.other_img, "อื่นๆ"));


        return imageList;
    }
}

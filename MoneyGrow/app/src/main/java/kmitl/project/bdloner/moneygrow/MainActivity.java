package kmitl.project.bdloner.moneygrow;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import kmitl.project.bdloner.moneygrow.R;

public class MainActivity extends AppCompatActivity {

    private SectionsPageAdapter mSectionsPageAdapter;

    private ViewPager mViewPager;

    private FloatingActionButton fab_plus, fab_ex, fab_in;
    private CustomTextView fab_ex_text, fab_in_text;
    private Animation FabOpen, FabClose, FabRClockwisw, FabRanticlockwise;
    boolean isOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        mViewPager.setCurrentItem(1);

        fab_plus = (FloatingActionButton)findViewById(R.id.fab_plus);
        fab_ex = (FloatingActionButton)findViewById(R.id.fab_ex);
        fab_in = (FloatingActionButton)findViewById(R.id.fab_in);
        fab_ex_text = (CustomTextView) findViewById(R.id.fab_ex_text);
        fab_in_text = (CustomTextView) findViewById(R.id.fab_in_text);

        FabOpen = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_open);
        FabClose = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);
        FabRClockwisw = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_clockwise);
        FabRanticlockwise = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_anticlockwise);

        fab_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isOpen){
                    animationCloseOnClick();
                }else {
                    animationOpenOnClick();
                }
            }
        });

        fab_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animationCloseOnClick();

                Intent intent = new Intent(getApplicationContext(), CalculatorActivity.class);
                intent.putExtra("btnCatIn", 0);
                startActivity(intent);
            }
        });

        fab_ex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animationCloseOnClick();

                Intent intent = new Intent(getApplicationContext(), CalculatorActivity.class);
                intent.putExtra("btnCatIn", 1);
                startActivity(intent);
            }
        });

    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new Tab1Fragment(), "เดือนที่แล้ว");
        adapter.addFragment(new Tab2Fragment(), "เดือนนี้");
        adapter.addFragment(new Tab3Fragment(), "ปฏิทิน");
        viewPager.setAdapter(adapter);
    }

    public void animationCloseOnClick(){
        fab_in.startAnimation(FabClose);
        fab_ex.startAnimation(FabClose);
        fab_in_text.startAnimation(FabClose);
        fab_ex_text.startAnimation(FabClose);
        fab_plus.startAnimation(FabRanticlockwise);
        fab_ex.setClickable(false);
        fab_in.setClickable(false);
        isOpen = false;
    }

    public void animationOpenOnClick(){
        fab_in.startAnimation(FabOpen);
        fab_ex.startAnimation(FabOpen);
        fab_in_text.startAnimation(FabOpen);
        fab_ex_text.startAnimation(FabOpen);
        fab_plus.startAnimation(FabRClockwisw);
        fab_ex.setClickable(true);
        fab_in.setClickable(true);
        isOpen = true;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_day) {
            Toast.makeText(this, "day is clicked", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.action_week){
            Toast.makeText(this, "week is clicked", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.action_month){
            Toast.makeText(this, "month is clicked", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.action_year){
            Toast.makeText(this, "year is clicked", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.action_all){
            Toast.makeText(this, "all is clicked", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

}

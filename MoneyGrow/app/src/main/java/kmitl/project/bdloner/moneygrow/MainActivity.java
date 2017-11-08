package kmitl.project.bdloner.moneygrow;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton fab_plus, fab_ex, fab_in;
    private CustomTextView fab_ex_text, fab_in_text;
    private Animation FabOpen, FabClose, FabRClockwisw, FabRanticlockwise;
    boolean isOpen = false;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            switch (item.getItemId()) {
                case R.id.navigation_wallet:
                    if(fab_plus.getVisibility() == View.INVISIBLE){
                        fab_plus.setVisibility(View.VISIBLE);
                    }
                    WalletFragment fragmentWallet = new WalletFragment();
                    transaction.replace(R.id.content, fragmentWallet , "WalletFragment").commit();
                    return true;
                case R.id.navigation_goal:
                    if(fab_plus.getVisibility() == View.VISIBLE){
                        fab_plus.setVisibility(View.INVISIBLE);
                    }
                    GoalFragment fragmentGoal = new GoalFragment();
                    transaction.replace(R.id.content, fragmentGoal , "GoalFragment").commit();
                    return true;
                case R.id.navigation_chart:
                    if(fab_plus.getVisibility() == View.VISIBLE){
                        fab_plus.setVisibility(View.INVISIBLE);
                    }
                    ChartFragment fragmentChart = new ChartFragment();
                    transaction.replace(R.id.content, fragmentChart , "ChartFragment").commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fab_plus = findViewById(R.id.fab_plus);
        fab_ex = findViewById(R.id.fab_ex);
        fab_in = findViewById(R.id.fab_in);
        fab_ex_text = findViewById(R.id.fab_ex_text);
        fab_in_text = findViewById(R.id.fab_in_text);

        FabOpen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        FabClose = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        FabRClockwisw = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_clockwise);
        FabRanticlockwise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_anticlockwise);

        fab_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isOpen) {
                    animationCloseOnClick();
                } else {
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

    public void animationCloseOnClick() {
        fab_in.startAnimation(FabClose);
        fab_ex.startAnimation(FabClose);
        fab_in_text.startAnimation(FabClose);
        fab_ex_text.startAnimation(FabClose);
        fab_plus.startAnimation(FabRanticlockwise);
        fab_ex.setClickable(false);
        fab_in.setClickable(false);
        isOpen = false;
    }

    public void animationOpenOnClick() {
        fab_in.startAnimation(FabOpen);
        fab_ex.startAnimation(FabOpen);
        fab_in_text.startAnimation(FabOpen);
        fab_ex_text.startAnimation(FabOpen);
        fab_plus.startAnimation(FabRClockwisw);
        fab_ex.setClickable(true);
        fab_in.setClickable(true);
        isOpen = true;
    }

}

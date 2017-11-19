package kmitl.project.bdloner.moneygrow.view;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import kmitl.project.bdloner.moneygrow.R;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            switch (item.getItemId()) {
                case R.id.navigation_wallet:
                    WalletFragment fragmentWallet = new WalletFragment();
                    transaction.replace(R.id.content, fragmentWallet, "WalletFragment").commit();
                    return true;
                case R.id.navigation_goal:
                    GoalFragment fragmentGoal = new GoalFragment();
                    transaction.replace(R.id.content, fragmentGoal, "GoalFragment").commit();
                    return true;
                case R.id.navigation_currency:
                    CurrencyFragment fragmentCurrency = new CurrencyFragment();
                    transaction.replace(R.id.content, fragmentCurrency, "CurrencyCurrency").commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content, new WalletFragment()).commit();

    }
}

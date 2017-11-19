package kmitl.project.bdloner.moneygrow.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import kmitl.project.bdloner.moneygrow.init.CustomTextView;
import kmitl.project.bdloner.moneygrow.R;
import kmitl.project.bdloner.moneygrow.model.Currency;

/**
 * Created by tomm on 4/5/16 AD.
 */
public class CurrencyAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<Currency> currencyList;
    private CurrencyItemClickListener currencyItemClickListener;
    private CustomTextView tvName, tvRate;

    public CurrencyAdapter(Context context, List<Currency> currencyList, CurrencyItemClickListener currencyItemClickListener) {
        this.context = context;
        this.currencyList = currencyList;
        this.currencyItemClickListener = currencyItemClickListener;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return currencyList.size();
    }

    @Override
    public Object getItem(int i) {
        return currencyList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View currencyItemView = layoutInflater.inflate(R.layout.currency_item, null);
        tvName = currencyItemView.findViewById(R.id.tvName);
        tvRate = currencyItemView.findViewById(R.id.tvRate);

        final Currency c = currencyList.get(i);
        tvName.setText(c.getName());
        tvRate.setText(Double.toString(c.getRate()));

        currencyItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currencyItemClickListener.onCurrencyItemClick(c);
            }
        });
        return currencyItemView;
    }
}

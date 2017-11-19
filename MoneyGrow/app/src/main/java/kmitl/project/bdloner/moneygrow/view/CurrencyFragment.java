package kmitl.project.bdloner.moneygrow.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import kmitl.project.bdloner.moneygrow.controller.CurrencyExchangeService;
import kmitl.project.bdloner.moneygrow.controller.CurrencyItemClickListener;
import kmitl.project.bdloner.moneygrow.R;
import kmitl.project.bdloner.moneygrow.controller.CurrencyAdapter;
import kmitl.project.bdloner.moneygrow.model.Currency;
import kmitl.project.bdloner.moneygrow.model.CurrencyExchange;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CurrencyFragment extends Fragment implements Callback<CurrencyExchange>, CurrencyItemClickListener {

    private OnFragmentInteractionListener mListener;
    private ListView lvCurrency;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_currency, container, false);

        lvCurrency = v.findViewById(R.id.list_view_currency);

        return v;

    }

    @Override
    public void onStart() {
        super.onStart();
        loadCurrencyExchangeData();
    }

    private void loadCurrencyExchangeData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.fixer.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CurrencyExchangeService service = retrofit.create(CurrencyExchangeService.class);
        Call<CurrencyExchange> call = service.loadCurrencyExchange();
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<CurrencyExchange> call, Response<CurrencyExchange> response) {
        //Toast.makeText(this, response.body().getBase(), Toast.LENGTH_LONG).show();
        CurrencyExchange currencyExchange = response.body();
        try {
            lvCurrency.setAdapter(new CurrencyAdapter(getActivity(), currencyExchange.getCurrencyList(), this));
        } catch (Exception e) {

        }
    }

    @Override
    public void onFailure(Call<CurrencyExchange> call, Throwable t) {
        Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCurrencyItemClick(Currency c) {
        //Toast.makeText(this, c.getName(), Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getActivity(), ChangeCurrencyActivity.class);
        intent.putExtra("currency_name", c.getName());
        intent.putExtra("currency_rate", c.getRate());

        startActivity(intent);
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

package kmitl.project.bdloner.moneygrow.controller;

import kmitl.project.bdloner.moneygrow.model.CurrencyExchange;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CurrencyExchangeService {
    @GET("latest?base=USD")
    Call<CurrencyExchange> loadCurrencyExchange();
}

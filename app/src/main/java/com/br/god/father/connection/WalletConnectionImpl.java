package com.br.god.father.connection;

import java.io.IOException;

import com.br.god.father.model.Customer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class WalletConnectionImpl {

    public void registerCustomer() throws IOException {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://172.20.48.1:3000").addConverterFactory(JacksonConverterFactory.create()).build();

        WalletConnection connection = retrofit.create(WalletConnection.class);

        Call call1 = connection.registerCustomer();
        call1.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Customer customer = (Customer) response.body();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                call.cancel();
            }
        });
    }


}

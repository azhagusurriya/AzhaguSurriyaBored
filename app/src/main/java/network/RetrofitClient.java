package network;

import com.squareup.moshi.Moshi;

import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class RetrofitClient {

    private static RetrofitClient instance = null;
    private Api api;

    private RetrofitClient(){
        Moshi moshi = new Moshi.Builder().build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build();

        api = retrofit.create(Api.class);
    }

    public  static  synchronized RetrofitClient getInstance(){
        if (instance == null){
            instance = new RetrofitClient();
        }

        return instance;
    }

    public Api getApi(){
        return api;
    }

}

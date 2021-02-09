package viewmodel;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import model.Bored;
import network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoredViewModel extends ViewModel {

    private final String TAG = this.getClass().getCanonicalName();
    private static final BoredViewModel ourInstance = new BoredViewModel();
    public MutableLiveData<Bored> boredLiveObj = new MutableLiveData<>();

    public static BoredViewModel getInstance(){
        return ourInstance;
    }

    private BoredViewModel(){}

    public void fetchBoredData(){
        Call<Bored> call = RetrofitClient.getInstance().getApi().retrieveResponse();

        try{
            call.enqueue(new Callback<Bored>() {
                @Override
                public void onResponse(Call<Bored> call, Response<Bored> response) {
                    Bored boredObj = response.body();
                    boredLiveObj.postValue(boredObj);
                }

                @Override
                public void onFailure(Call<Bored> call, Throwable t) {
                    Log.e(TAG, "Error while fetching data" + t.getLocalizedMessage());
                }
            });
        }catch (Exception ex){
            Log.e(TAG, ex.getLocalizedMessage());
            Log.e(TAG, ex.toString());
        }
    }
}

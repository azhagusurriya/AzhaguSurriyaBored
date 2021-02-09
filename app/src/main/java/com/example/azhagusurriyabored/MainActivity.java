package com.example.azhagusurriyabored;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import model.Bored;
import viewmodel.ActivityViewModel;
import viewmodel.BoredViewModel;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private BoredViewModel boredViewModel;
    private final String TAG = this.getClass().getCanonicalName();

    private TextView tvBoredActivity;
    private Button btnShowAnother;
    private Button btnLike;

    private ActivityViewModel activityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.activityViewModel = ActivityViewModel.getInstance();


        boredViewModel = BoredViewModel.getInstance();
        boredViewModel.fetchBoredData();

        this.tvBoredActivity = findViewById(R.id.tvActivity);
        this.btnShowAnother = findViewById(R.id.btnShowAnother);
        this.btnShowAnother.setOnClickListener(this);
        this.btnLike = findViewById(R.id.btnLike);
        this.btnLike.setOnClickListener(this);

        this.boredViewModel.boredLiveObj.observe(this, new Observer<Bored>() {
            @Override
            public void onChanged(Bored bored) {

                tvBoredActivity.setText(bored.getActivity());

                Log.e(TAG, "Bored "  + " Activity : " + bored.getActivity());

            }
        });

    }

    @Override
    public void onClick(View view) {
        if( view != null){
            switch (view.getId()){
                case R.id.btnShowAnother: {

                    boredViewModel.fetchBoredData();

                    this.boredViewModel.boredLiveObj.observe(this, new Observer<Bored>() {
                        @Override
                        public void onChanged(Bored bored) {
                            tvBoredActivity.setText(bored.getActivity());

                            Log.e(TAG, "Bored "  + " Another bored Activity : " + bored.getActivity());
                        }
                    });
                  break;
                }
                case R.id.btnLike: {

                    Bored newActivity = new Bored();
                    newActivity.setActivity(this.tvBoredActivity.getText().toString());
                    Log.d(TAG, "onClicAdding New Activity to database: " + newActivity);
                    this.activityViewModel.addActivity(newActivity);
                        //save data to database
//                        this.saveActivityToDB();

                    break;
                }
                default:
                    break;
            }
        }
    }

//    private void saveActivityToDB(){
//        this.boredViewModel.boredLiveObj.observe(this, new Observer<Bored>() {
//            @Override
//            public void onChanged(Bored bored) {
//                tvBoredActivity.setText(bored.getActivity());
//                newActivity.setActivity(tvBoredActivity.getText().toString());
//                activityViewModel.addActivity(newActivity);
//
//                Log.e(TAG, "Bored "  + " Activity adding in firebase database : " + bored.getActivity());
//            }
//        });
//
//    }
}
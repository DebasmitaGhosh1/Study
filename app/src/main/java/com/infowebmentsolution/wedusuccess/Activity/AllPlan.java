package com.infowebmentsolution.wedusuccess.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.infowebmentsolution.wedusuccess.Adapter.AllPlanAdapter;
//import com.infowebmentsolution.wedusuccess.Adapter.MyplanAdapter;
import com.infowebmentsolution.wedusuccess.Gson.AllPlanList;
//import com.infowebmentsolution.wedusuccess.Gson.MyPlanList;
import com.infowebmentsolution.wedusuccess.MainActivity;
import com.infowebmentsolution.wedusuccess.R;
import com.infowebmentsolution.wedusuccess.Response.AllPlanResponse;
//import com.infowebmentsolution.wedusuccess.Response.MyPlanResponse;
import com.infowebmentsolution.wedusuccess.Utils.Constants;
import com.infowebmentsolution.wedusuccess.Utils.RestApi;
import com.infowebmentsolution.wedusuccess.Utils.RetrofitClient;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllPlan extends AppCompatActivity implements AllPlanAdapter.ClickedOnItemListener {
    private RecyclerView recyclerView;
    private ArrayList<AllPlanList> data;
    private AllPlanAdapter adapter;
    Toolbar toolbar;
    TextView textView;
    ImageView no_plan;
    LinearLayout ll;
//    private ArrayList<MyPlanList> result;
    String UserId;
    String strDate;
    int intentFlag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wedusuccess);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        toolbar =(Toolbar) findViewById(R.id.final_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle((CharSequence) "");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        textView =(TextView) findViewById(R.id.toolbar_text);
     //   textView.setText(R.string.textPlan);
        no_plan = (ImageView) findViewById(R.id.empty_image);
        ll = (LinearLayout) findViewById(R.id.main_layout);
        SharedPreferences editor = getSharedPreferences(Constants.LOG_IN_DATA, 0);
        this.UserId = editor.getString(Constants.USER_ID, "none");
        adapter = new AllPlanAdapter(this::ClickedItem);
        initViews();
    }

    private static RestApi getInterface(){
        return RetrofitClient.getClient(Constants.BASE_URL).create(RestApi.class);
    }

    private void initViews(){
        recyclerView = (RecyclerView)findViewById(R.id.recycleview_alluniversity);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        System.out.println("ok-uni");
        loadJSON();
    }

    private void loadJSON(){
        Call<AllPlanResponse> call = getInterface().subscriptionApi();

        call.enqueue(new Callback<AllPlanResponse>() {
            @Override
            public void onResponse(Call<AllPlanResponse> call, Response<AllPlanResponse> response) {

                AllPlanResponse allPlanResponse = response.body();
                data = new ArrayList<>(Arrays.asList(allPlanResponse.getData()));
                adapter.setData(data);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<AllPlanResponse> call, Throwable t) {
            }
        });
    }

    private static RestApi getAPIInterface2() {
        return RetrofitClient.getClient(Constants.APIURL).create(RestApi.class);
    }

    @Override
    public void ClickedItem(AllPlanList allPlanList) {
        Intent intent1 = new Intent(AllPlan.this, MainActivity.class);
        intent1.putExtra("uid", allPlanList.getUid());
        intent1.putExtra("branch_id", allPlanList.getBid());
        intent1.putExtra("semester_id", allPlanList.getSid());
        intent1.putExtra("amount", allPlanList.getSubscriptionamount());
        intent1.putExtra("duration", allPlanList.getSubscriptionduration());
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        strDate = formatter.format(date);
//        Call<MyPlanResponse> call = getAPIInterface2().myplanAPi(this.UserId);
//        call.enqueue(new Callback<MyPlanResponse>() {
//            @Override
//            public void onResponse(Call<MyPlanResponse> call, Response<MyPlanResponse> response) {
//                if(response.code()==200){
//                    MyPlanResponse myPlanResponse = response.body();
//                    result = new ArrayList<>(Arrays.asList(myPlanResponse.getResult()));
//                    for(int index=0;index<result.size();index++){
//                        if(result.get(index).getSemesterid().equals(allPlanList.getSemesterid()) && result.get(index).getBranchid().equals(allPlanList.getBid()) && result.get(index).getUniversityid().equals(allPlanList.getUid()) && result.get(index).getUsersubscriptionduration().compareTo(strDate)>=0 && result.get(index).getUsersubscriptionstatus().equals("1")){
//                            intentFlag = 1;
//                            Toast.makeText(AllPlan.this, "Already Subscribed", Toast.LENGTH_SHORT).show();
//                            Intent startmyplan = new Intent(AllPlan.this, MyPlan.class);
//                            startActivity(startmyplan);
//                            break;
//                        }
//
//                    }
//                    if(intentFlag == 0){
//                        startActivity(intent1);
//                    }
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<MyPlanResponse> call, Throwable t) {
//                startActivity(intent1);
//            }
//        });
//
//    }
    }

}
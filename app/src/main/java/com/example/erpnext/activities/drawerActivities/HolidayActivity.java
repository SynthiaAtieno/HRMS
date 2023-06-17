package com.example.erpnext.activities.drawerActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.erpnext.R;
import com.example.erpnext.adapters.HolidayListAdapter;
import com.example.erpnext.models.HolidayList;
import com.example.erpnext.services.ApiClient;
import com.example.erpnext.session.UserSessionManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HolidayActivity extends AppCompatActivity {
    RecyclerView holidayrecycler;
    HolidayListAdapter holidayListAdapter;
    List<HolidayList.Holiday> holidayLists;
    UserSessionManager sessionManager;
    ProgressBar progressBar;

    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_holiday);

        toolbar = findViewById(R.id.holiday_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Holiday List");
        sessionManager = new UserSessionManager(this);
        holidayrecycler = findViewById(R.id.holidayRecycler);
        holidayLists = new ArrayList<>();
        holidayrecycler.setLayoutManager(new LinearLayoutManager(this));
        holidayListAdapter = new HolidayListAdapter(holidayLists, this);

        progressBar = findViewById(R.id.progressbarholiday);
        getHolidays();
    }
    public void getHolidays(){
        progressBar.setVisibility(View.VISIBLE);
        String doctype = "Holiday List";
        String holidayname = "2023-Holiday-List";
        ApiClient.getApiClient().getHolidayList(doctype, holidayname, sessionManager.getUserId()).enqueue(new Callback<HolidayList>() {
            @Override
            public void onResponse(Call<HolidayList> call, Response<HolidayList> response) {
                if (response.isSuccessful()){
                    HolidayList holidayList = response.body();
                    if (holidayList != null && !holidayList.getDocs().isEmpty() && holidayList.getDocs() !=null){
                        HolidayList.Doc holiday = holidayList.getDocs().get(0);

                        holidayrecycler.setAdapter(holidayListAdapter);
                        holidayLists.addAll(holiday.getHolidays());
                        progressBar.setVisibility(View.GONE);
                        holidayListAdapter.notifyDataSetChanged();
                    }
                    else {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(HolidayActivity.this, "No Data in Holiday List", Toast.LENGTH_SHORT).show();
                    }

                }
                else {
                    progressBar.setVisibility(View.GONE);

                    Toast.makeText(HolidayActivity.this, "Response not successfully", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<HolidayList> call, Throwable t) {
                progressBar.setVisibility(View.GONE);

                Toast.makeText(HolidayActivity.this, "error"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
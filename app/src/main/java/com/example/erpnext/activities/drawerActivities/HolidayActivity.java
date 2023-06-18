package com.example.erpnext.activities.drawerActivities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.erpnext.R;
import com.example.erpnext.adapters.DateUtils;
import com.example.erpnext.adapters.HolidayListAdapter;
import com.example.erpnext.models.HolidayList;
import com.example.erpnext.services.ApiClient;
import com.example.erpnext.session.UserSessionManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@RequiresApi(api = Build.VERSION_CODES.N)
public class HolidayActivity extends AppCompatActivity {
    RecyclerView holidayrecycler;
    List<HolidayList.Holiday> holidayLists;
    UserSessionManager sessionManager;
    ProgressBar progressBar;
    SimpleDateFormat dateFormat;
    private HolidayListAdapter holidayListAdapter;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_holiday);

        toolbar = findViewById(R.id.holiday_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Holiday List");

        holidayrecycler = findViewById(R.id.holidayRecycler);
        progressBar = findViewById(R.id.progressbarholiday);
        getHolidays();
    }
    public void getHolidays(){
        sessionManager = new UserSessionManager(this);
        progressBar.setVisibility(View.VISIBLE);
        String doctype = "Holiday List";
        String holidayname = "2023-Holiday-List";
        ApiClient.getApiClient().getHolidayList(doctype, holidayname, sessionManager.getUserId()).enqueue(new Callback<HolidayList>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<HolidayList> call, Response<HolidayList> response) {
                if (response.isSuccessful()){
                    HolidayList holidayList = response.body();
                    if (holidayList != null && !holidayList.getDocs().isEmpty() && holidayList.getDocs() !=null){

                        dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                        holidayLists = new ArrayList<>();
                        holidayListAdapter = new HolidayListAdapter(holidayLists, HolidayActivity.this);
                        HolidayList.Doc holiday = holidayList.getDocs().get(0);
                        String currentDate = dateFormat.format(new Date());
                        for (HolidayList.Holiday holiday1 : holiday.getHolidays()){
                            if (holiday1.getHolidayDate().compareTo(currentDate)>0 || holiday1.getHolidayDate().compareTo(currentDate) == 0){
                                String formattedDate = DateUtils.convertStringToDate(holiday1.getHolidayDate(), "yyyy-MM-dd", "dd MMM yyyy");
                                holiday1.setHolidayDate(formattedDate);
                                holidayLists.add(holiday1);

                            }
                        }

                        holidayrecycler.setAdapter(holidayListAdapter);
                        holidayrecycler.setLayoutManager(new LinearLayoutManager(HolidayActivity.this));
                        holidayrecycler.setHasFixedSize(true);
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
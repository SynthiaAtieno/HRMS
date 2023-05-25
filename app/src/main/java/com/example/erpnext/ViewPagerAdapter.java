package com.example.erpnext;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class ViewPagerAdapter extends PagerAdapter {
    Context context;

    int image[] = {
            R.drawable.first_image,
            R.drawable.second_image,
            R.drawable.third_image
    };

    int heading[] = {
            R.string.run_payroll_in_seconds,
            R.string.easy_attendance_system,
            R.string.task_management_system
    };
    int description[] = {
            R.string.say_goodbye_to_running_payroll_manually_on_n_complicated_spreadsheet,
            R.string.attendance,
            R.string.task_management
    };

    public ViewPagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return heading.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (LinearLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider_layout, container, false);

        ImageView slideTitleImageView = view.findViewById(R.id.title_image);
        TextView slideHeadingTitle = view.findViewById(R.id.text_title);
        TextView slideDescription = view.findViewById(R.id.text_description);

        slideTitleImageView.setImageResource(image[position]);
        slideDescription.setText(description[position]);
        slideHeadingTitle.setText(heading[position]);

        container.addView(view);
        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }
}

package com.bpk.mydemopoc;


import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bpk.mydemopoc.TabAdapter.InnerViewPageAdapter;
import com.bpk.mydemopoc.TabAdapter.MovieAdapter;
import com.bpk.mydemopoc.TabAdapter.PageAdapter;
import com.bpk.mydemopoc.TabAdapter.ViewPageAdapter;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static android.support.v7.widget.RecyclerView.NO_POSITION;
import static android.support.v7.widget.RecyclerView.SCROLL_STATE_DRAGGING;
import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;

/**
 * A simple {@link Fragment} subclass.
 */
public class Demo1Fragment extends Fragment implements SeekBar.OnSeekBarChangeListener,
        OnChartValueSelectedListener, View.OnClickListener {


    private ViewPager pager;
    TabLayout tabLayout;
    InnerViewPageAdapter adapter;
    PageAdapter fragmentAdapter;
    CharSequence Titles[] = {"H", "D", "W", "M"};
    int Numboftabs = 4;
    ConstraintLayout cl;
    private BarChart chart;
    private SeekBar seekBarX, seekBarY;
    private TextView tvX, tvY;
    BarDataSet set1, set2, set3, set4;
    View viewGroup;
    private Button button_Hour;
    private Button button_Daily;
    private Button button_Week;
    private Button button_Month;
    private Spinner spinClick;
    private EditText edtShowData;
    Fragment fr = null;
    FragmentManager fragmentManager;
    TextView tv_date_data;
    RecyclerView recyclerview;
    private List<Movie> movieList = new ArrayList<>();
    private MovieAdapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    ImageView img_Previous;
    ImageView img_Next;

    int currentVisibleItem = 0;

    //To check whether user scrolled the recycler view or used arrows to navigate.
    private boolean programaticallyScrolled;

    public Demo1Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_demo_one, container, false);

        button_Hour = (Button) v.findViewById(R.id.button1);
        button_Daily = (Button) v.findViewById(R.id.btn_Daily);
        button_Week = (Button) v.findViewById(R.id.btn_Weekly);
        button_Month = (Button) v.findViewById(R.id.btn_Monthly);
        button_Hour.setOnClickListener(this);
        button_Hour.setElevation(8f);
        button_Daily.setOnClickListener(this);
        button_Week.setOnClickListener(this);
        button_Month.setOnClickListener(this);
        fr = new DemoFragment();

        try {
            if (fr != null) {
                fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().replace(R.id.container, fr, fr.toString());
                fragmentTransaction.commit();
            } else {
                // error in creating fragment
                Log.e("MainActivity", "Error in creating fragment");
            }
        }
        catch (RuntimeException e){
            e.printStackTrace();
        }

        return v;
    }

//    public int findFirstCompletelyVisibleItemPosition() {
//        final View child = findOneVisibleChild(0, getChildCount(), true, false);
//        return child == null ? NO_POSITION : getPosition(child);
//    }



    public void show_change_Hour() {
        button_Hour.setBackgroundResource(R.drawable.bg_state);
        button_Daily.setBackgroundResource(R.drawable.bg_state_h);
        button_Week.setBackgroundResource(R.drawable.bg_state_h);
        button_Month.setBackgroundResource(R.drawable.bg_state_h);
        button_Hour.setElevation(8f);
        button_Daily.setElevation(0f);
        button_Week.setElevation(0f);
        button_Month.setElevation(0f);

        button_Hour.setTextColor(Color.parseColor("#ffffff"));
        button_Daily.setTextColor(Color.parseColor("#F44336"));
        button_Week.setTextColor(Color.parseColor("#F44336"));
        button_Month.setTextColor(Color.parseColor("#F44336"));
    }


//    private void prepareMovieData() {
//        Movie movie = new Movie("Mad Max: Fury Road", "Action & Adventure", "2015");
//        movieList.add(movie);
//
//        movie = new Movie("Inside Out", "Animation, Kids & Family", "2015");
//        movieList.add(movie);
//
//        movie = new Movie("Star Wars: Episode VII - The Force Awakens", "Action", "2015");
//        movieList.add(movie);
//
//        movie = new Movie("Shaun the Sheep", "Animation", "2015");
//        movieList.add(movie);
//
//        movie = new Movie("" +
//                "The Martian", "Science Fiction & Fantasy", "2015");
//        movieList.add(movie);
//
//        movie = new Movie("Mission: Impossible Rogue Nation", "Action", "2015");
//        movieList.add(movie);
//
//        movie = new Movie("Up", "Animation", "2009");
//        movieList.add(movie);
//
//        movie = new Movie("Star Trek", "Science Fiction", "2009");
//        movieList.add(movie);
//
//        movie = new Movie("The LEGO Movie", "Animation", "2014");
//        movieList.add(movie);
//
//        movie = new Movie("Iron Man", "Action & Adventure", "2008");
//        movieList.add(movie);
//
//        movie = new Movie("Aliens", "Science Fiction", "1986");
//        movieList.add(movie);
//
//        movie = new Movie("Chicken Run", "Animation", "2000");
//        movieList.add(movie);
//
//        movie = new Movie("Back to the Future", "Science Fiction", "1985");
//        movieList.add(movie);
//
//        movie = new Movie("Raiders of the Lost Ark", "Action & Adventure", "1981");
//        movieList.add(movie);
//
//        movie = new Movie("Goldfinger", "Action & Adventure", "1965");
//        movieList.add(movie);
//
//        movie = new Movie("Guardians of the Galaxy", "Science Fiction & Fantasy", "2014");
//        movieList.add(movie);
//
//        mAdapter.notifyDataSetChanged();
//    }

    public void show_change_Daily() {
        button_Hour.setBackgroundResource(R.drawable.bg_state_h);
        button_Daily.setBackgroundResource(R.drawable.bg_state);
        button_Daily.setElevation(8f);
        button_Hour.setElevation(0f);
        button_Week.setElevation(0f);
        button_Month.setElevation(0f);
        button_Week.setBackgroundResource(R.drawable.bg_state_h);
        button_Month.setBackgroundResource(R.drawable.bg_state_h);
        button_Hour.setTextColor(Color.parseColor("#F44336"));
        button_Daily.setTextColor(Color.parseColor("#FFFFFF"));
        button_Week.setTextColor(Color.parseColor("#F44336"));
        button_Month.setTextColor(Color.parseColor("#F44336"));


    }

    public void show_change_Week() {
        button_Hour.setBackgroundResource(R.drawable.bg_state_h);
        button_Daily.setBackgroundResource(R.drawable.bg_state_h);
        button_Week.setBackgroundResource(R.drawable.bg_state);
        button_Week.setElevation(8f);
        button_Daily.setElevation(0f);
        button_Month.setElevation(0f);
        button_Hour.setElevation(0f);
        button_Month.setBackgroundResource(R.drawable.bg_state_h);
        button_Hour.setTextColor(Color.parseColor("#F44336"));
        button_Daily.setTextColor(Color.parseColor("#F44336"));
        button_Week.setTextColor(Color.parseColor("#FFFFFF"));
        button_Month.setTextColor(Color.parseColor("#F44336"));


    }

    public void show_change_Month() {
        button_Hour.setBackgroundResource(R.drawable.bg_state_h);
        button_Daily.setBackgroundResource(R.drawable.bg_state_h);
        button_Week.setBackgroundResource(R.drawable.bg_state_h);
        button_Month.setBackgroundResource(R.drawable.bg_state);
        button_Month.setElevation(8f);
        button_Week.setElevation(0f);
        button_Daily.setElevation(0f);
        button_Hour.setElevation(0f);

        button_Hour.setTextColor(Color.parseColor("#F44336"));
        button_Daily.setTextColor(Color.parseColor("#F44336"));
        button_Week.setTextColor(Color.parseColor("#F44336"));
        button_Month.setTextColor(Color.parseColor("#FFFFFF"));


    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        float groupSpace = 0.08f;
        float barSpace = 0.01f; // x4 DataSet
        float barWidth = 0.1f; // x4 DataSet
        // (0.2 + 0.03) * 4 + 0.08 = 1.00 -> interval per "group"

        int groupCount = seekBarX.getProgress() + 1;
        int startYear = 1980;
        int endYear = startYear + groupCount;

        tvX.setText(String.format(Locale.ENGLISH, "%d-%d", startYear, endYear));
        tvY.setText(String.valueOf(seekBarY.getProgress()));

        ArrayList<BarEntry> values1 = new ArrayList<>();
        ArrayList<BarEntry> values2 = new ArrayList<>();
        ArrayList<BarEntry> values3 = new ArrayList<>();
//        ArrayList<BarEntry> values4 = new ArrayList<>();

        float randomMultiplier = seekBarY.getProgress() * 1000f;

        for (int i = startYear; i < endYear; i++) {
            values1.add(new BarEntry(i, (float) (Math.random() * randomMultiplier)));
            values2.add(new BarEntry(i, (float) (Math.random() * randomMultiplier)));
            //   values3.add(new BarEntry(i, (float) (Math.random() * randomMultiplier)));
            //          values4.add(new BarEntry(i, (float) (Math.random() * randomMultiplier)));
        }

        BarDataSet set1, set2, set3, set4;

        if (chart.getData() != null && chart.getData().getDataSetCount() > 0) {

            set1 = (BarDataSet) chart.getData().getDataSetByIndex(0);
            set2 = (BarDataSet) chart.getData().getDataSetByIndex(1);
            //     set3 = (BarDataSet) chart.getData().getDataSetByIndex(2);
            //       set4 = (BarDataSet) chart.getData().getDataSetByIndex(3);
            set1.setValues(values1);
            set2.setValues(values2);
            // set3.setValues(values3);
            //      set4.setValues(values4);
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();

        } else {
            // create 4 DataSets
            set1 = new BarDataSet(values1, "Company A");
            set1.setColor(Color.rgb(104, 241, 175));
            set2 = new BarDataSet(values2, "Company B");
            set2.setColor(Color.rgb(164, 228, 251));
            //  set3 = new BarDataSet(values3, "Company C");
            //  set3.setColor(Color.rgb(242, 247, 158));
            //        set4 = new BarDataSet(values4, "Company D");
            //      set4.setColor(Color.rgb(255, 102, 0));

            BarData data = new BarData(set1, set2);
            data.setValueFormatter(new LargeValueFormatter());
            //       data.setValueTypeface(tfLight);

            chart.setData(data);
        }

        // specify the width each bar should have
        chart.getBarData().setBarWidth(barWidth);

        // restrict the x-axis range
        chart.getXAxis().setAxisMinimum(startYear);

        // barData.getGroupWith(...) is a helper that calculates the width each group needs based on the provided parameters
        chart.getXAxis().setAxisMaximum(startYear + chart.getBarData().getGroupWidth(groupSpace, barSpace) * groupCount);
        chart.groupBars(startYear, groupSpace, barSpace);
        chart.invalidate();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }


    private ArrayList<BarDataSet> getDataSet() {
        ArrayList<BarDataSet> dataSets = null;

        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        BarEntry v1e1 = new BarEntry(110.000f, 0); // Jan
        valueSet1.add(v1e1);
        BarEntry v1e2 = new BarEntry(40.000f, 1); // Feb
        valueSet1.add(v1e2);
        BarEntry v1e3 = new BarEntry(60.000f, 2); // Mar
        valueSet1.add(v1e3);
        BarEntry v1e4 = new BarEntry(30.000f, 3); // Apr
        valueSet1.add(v1e4);
        BarEntry v1e5 = new BarEntry(90.000f, 4); // May
        valueSet1.add(v1e5);
        BarEntry v1e6 = new BarEntry(100.000f, 5); // Jun
        valueSet1.add(v1e6);

        ArrayList<BarEntry> valueSet2 = new ArrayList<>();
        BarEntry v2e1 = new BarEntry(150.000f, 0); // Jan
        valueSet2.add(v2e1);
        BarEntry v2e2 = new BarEntry(90.000f, 1); // Feb
        valueSet2.add(v2e2);
        BarEntry v2e3 = new BarEntry(120.000f, 2); // Mar
        valueSet2.add(v2e3);
        BarEntry v2e4 = new BarEntry(60.000f, 3); // Apr
        valueSet2.add(v2e4);
        BarEntry v2e5 = new BarEntry(20.000f, 4); // May
        valueSet2.add(v2e5);
        BarEntry v2e6 = new BarEntry(80.000f, 5); // Jun
        valueSet2.add(v2e6);

        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "Brand 1");
        barDataSet1.setColor(Color.rgb(0, 155, 0));
        BarDataSet barDataSet2 = new BarDataSet(valueSet2, "Brand 2");
        barDataSet2.setColors(ColorTemplate.COLORFUL_COLORS);

        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        dataSets.add(barDataSet2);
        return dataSets;
    }

    private ArrayList<String> getXAxisValues() {
        ArrayList<String> xAxis = new ArrayList<>();
        xAxis.add("JAN");
        xAxis.add("FEB");
        xAxis.add("MAR");
        xAxis.add("APR");
        xAxis.add("MAY");
        xAxis.add("JUN");
        return xAxis;
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }

    @Override
    public void onClick(View view) {

        int id = view.getId();


        switch (id) {
            case R.id.button1:
                show_change_Hour();
                fr = new DemoFragment();
                SimpleTime();
                break;
            case R.id.btn_Daily:
                show_change_Daily();
                fr = new DemonextFragment();
                SimpleCurrentDay();

                break;
            case R.id.btn_Weekly:
                show_change_Week();
                fr = new GraphByWeekFragment();
                SimpleWeek();

                break;
            case R.id.btn_Monthly:
                show_change_Month();
                fr = new GraphByMonthFragment();
                SimpleMonth();
                break;
            case R.id.img_Previous:
                scrollPreviousPosition();

                break;
            case R.id.img_Next:

                scrollNextPosition();

                break;

        }

        try {
            Graphreplacing();

        } catch (Exception e) {

            e.printStackTrace();
        }


    }


    public void scrollPreviousPosition(){
        try{
            programaticallyScrolled = true;
            //Decrement current visible item position to navigate back to previous item
            currentVisibleItem--;
            handleWritingViewNavigationArrows(true);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void scrollNextPosition(){
        try{
            programaticallyScrolled = true;
            //Increment current visible item position to navigate next item
            currentVisibleItem++;
            handleWritingViewNavigationArrows(true);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    private void handleWritingViewNavigationArrows(boolean scroll) {
        if (currentVisibleItem == (recyclerview.getAdapter().getItemCount() - 1)) {
            img_Next.setVisibility(View.GONE);
            img_Previous.setVisibility(View.VISIBLE);

        } else if (currentVisibleItem != 0) {
//            img_Next.setAlpha(1.3f);
            img_Previous.setVisibility(View.VISIBLE);
            img_Next.setVisibility(View.VISIBLE);
        } else if (currentVisibleItem == 0) {
            img_Previous.setVisibility(View.GONE);
            img_Next.setVisibility(View.VISIBLE);
        }
        if (scroll) {
            recyclerview.smoothScrollToPosition(currentVisibleItem);
        }
    }

   private void Date_picker() {
        DialogFragment newFragment = new SelectDateFragment();
        newFragment.show(getFragmentManager(), "DatePicker");
    }

    private void Graphreplacing() {

        try {
            if (fr != null) {
                fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().replace(R.id.container, fr, fr.toString());
                fragmentTransaction.commit();
            } else {
                // error in creating fragment
                Log.e("MainActivity", "Error in creating fragment");
            }
        }
        catch (RuntimeException e){
            e.printStackTrace();
        }
    }



    private void showDatePicker() {
        DatePickerFragment date = new DatePickerFragment();
        /**
         * Set Up Current Date Into dialog
         */
        Calendar calender = Calendar.getInstance();
        Bundle args = new Bundle();
        args.putInt("year", calender.get(Calendar.YEAR));
        args.putInt("month", calender.get(Calendar.MONTH));
        args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));
        date.setArguments(args);
        /**
         * Set Call back to capture selected date
         */
        date.setCallBack(ondate);
        date.show(getFragmentManager(), "Date Picker");
    }

    DatePickerDialog.OnDateSetListener ondate = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
//            String monthname = (String) android.text.format.DateFormat.format("MMM d", new Date());
//             edtShowData.setText(monthname);


          //  edtShowData.setText(String.valueOf(dayOfMonth) + "-" + String.valueOf(monthOfYear+1)
            //        + "-" + String.valueOf(year));
        }
    };


    public static class SelectDateFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        //        TextView tv_date_data;
        EditText edtShowData;

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            int yy = calendar.get(Calendar.YEAR);
            int mm = calendar.get(Calendar.WEEK_OF_MONTH);
            int dd = calendar.get(Calendar.DAY_OF_MONTH);
//            edtShowData = (EditText) getActivity().findViewById(R.id.edit_UserType);
            return new DatePickerDialog(getActivity(), this, yy, mm, dd);
        }

        public void onDateSet(DatePicker view, int yy, int mm, int dd) {
            populateSetDate(yy, mm + 1, dd);
        }

        public void populateSetDate(int year, int month, int day) {
            Toast.makeText(getContext(), month + "/" + day + "/" + year, Toast.LENGTH_SHORT).show();
            String monthname = (String) android.text.format.DateFormat.format("EEEE, MMM d", new Date());
       //     edtShowData.setText(month);
        }

    }


    public void SimpleWeek() {
        final Calendar calendar = Calendar.getInstance();
        int week_start_day = calendar.getFirstDayOfWeek();
        int week_end_day = week_start_day + 6;
        String monthname = (String) android.text.format.DateFormat.format("MMM", new Date());
        // this will get the starting day os week in integer format i-e 1 if monday
//        Toast.makeText(getContext(),"Current Week is"+current_week +"Start Day is"+week_start_day,Toast.LENGTH_SHORT).show();
      //  edtShowData.setText(week_start_day + "-" + week_end_day + monthname);

    }

    public void SimpleMonth() {
        final Calendar calendar = Calendar.getInstance();
        String monthname = (String) android.text.format.DateFormat.format("MMMM", new Date());
//        edtShowData.setText(monthname);
    }

    public void SimpleCurrentDay() {

        final Calendar calendar = Calendar.getInstance();
        String monthname = (String) android.text.format.DateFormat.format("EEE, MMM d", new Date());
//        edtShowData.setText(monthname);
    }

    public void SimpleTime() {
//        final Calendar calendar = Calendar.getInstance();
//        String monthname = (String) android.text.format.DateFormat.format("h:mm a", new Date());
//        tv_date_data.setText(monthname);
//        Calendar c = Calendar.getInstance();
//        System.out.println("Current time => "+c.getTime());
//
//        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
//        String formattedDate = df.format(c.getTime());
//        // formattedDate have current date/time
//        Toast.makeText(getContext(), formattedDate, Toast.LENGTH_SHORT).show();

        try {
//            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a", Locale.ENGLISH);
//
//            String startTime = "08:00 AM";
//            String endTime = "04:00 PM";
//
//            LocalTime start = LocalTime.parse(startTime, timeFormatter);
//            LocalTime end = LocalTime.parse(endTime, timeFormatter);
//
//            Duration diff = Duration.between(start, end);

//            long hours = diff.toHours();
//            long minutes = diff.minusHour;
     //       String totalTimeString = String.format("%02d:%02d", hours, minutes);
       //     System.out.println("TotalTime in Hours and Mins Format is " + totalTimeString);
            //    tv_date_data.setText(start+"-"+end);

           /// Log.i("log_tag","Hours: "+hours+", Mins: "+min);

            } catch (Exception e) {
                e.printStackTrace();
            }
           }


    public void printDifference(Date startDate, Date endDate) {
        //milliseconds
        long different = endDate.getTime() - startDate.getTime();

        System.out.println("startDate : " + startDate);
        System.out.println("endDate : "+ endDate);
        System.out.println("different : " + different);

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long elapsedSeconds = different / secondsInMilli;

        System.out.printf(
                "%d days, %d hours, %d minutes, %d seconds%n",
                elapsedDays, elapsedHours, elapsedMinutes, elapsedSeconds);
    }

}

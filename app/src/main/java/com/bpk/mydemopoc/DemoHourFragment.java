package com.bpk.mydemopoc;


import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bpk.mydemopoc.CustomViews.MyMarkerView;
import com.bpk.mydemopoc.TabAdapter.InnerViewPageAdapter;
import com.bpk.mydemopoc.TabAdapter.MovieAdapter;
import com.bpk.mydemopoc.TabAdapter.PageAdapter;
import com.github.mikephil.charting.charts.BubbleChart;
import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BubbleData;
import com.github.mikephil.charting.data.BubbleDataSet;
import com.github.mikephil.charting.data.BubbleEntry;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_DRAGGING;
import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;


/**
 * A simple {@link Fragment} subclass.
 */
public class DemoHourFragment extends Fragment implements SeekBar.OnSeekBarChangeListener,View.OnClickListener {


    private ViewPager pager;
    TabLayout tabLayout;
    InnerViewPageAdapter adapter;
    PageAdapter fragmentAdapter;
    CharSequence Titles[] = {"H", "D", "W", "M"};
    int Numboftabs = 4;
    private CandleStickChart chart;
    private SeekBar seekBarX, seekBarY;
    private TextView tvX, tvY;
    IDataSet set;
    RecyclerView recyclerview;
    private List<Movie> movieList = new ArrayList<>();
    private MovieAdapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    ImageView img_Previous;
    ImageView img_Next;
    private List<LocalTime> times;
    private LocalTime time;
    private Date d = null;
    BubbleChart bubbleChart;
    BubbleData bubbleData;
    BubbleDataSet bubbleDataSet;
    ArrayList bubbleEntries;

    int currentVisibleItem = 0;

    //To check whether user scrolled the recycler view or used arrows to navigate.
    private boolean programaticallyScrolled;


    private Typeface mTf;


    public DemoHourFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_demo_hour, container, false);
//        img_Previous = (ImageView) v.findViewById(R.id.img_Previous);
//        img_Next = (ImageView)v.findViewById(R.id.img_Next);
//       // bubbleChart = (BubbleChart) v.findViewById(R.id.bubble_chart);
//        img_Next.setOnClickListener(this);
//        img_Previous.setOnClickListener(this);
//        recyclerview = (RecyclerView) v.findViewById(R.id.recyclerview);
//        String rs[] = getResources().getStringArray(R.array.monthArray);
//        chart = (CandleStickChart) v.findViewById(R.id.chart1);
//        Calendar c = Calendar.getInstance();
//        System.out.println("Current time => "+c.getTime());
//
//        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
//        String formattedDate = df.format(c.getTime());
//
//        //    String dis = formattedDate+15;
////        final String dateString = "16:15";
//        int x = 15;//900000/60*10000;
////        final long millisToAdd = x; //two hours
////        SimpleDateFormat formatDate = new SimpleDateFormat("hh:mm:ss");
////        DateFormat format = new SimpleDateFormat("HH:mm:ss");
//        //            String formattedDate = formatDate.format(c.getTime());
//        String myTime = "07:00";
//
//
//        try {
//            //        d = formatDate.parse(formattedDate);
//            SimpleDateFormat evaluatedf = new SimpleDateFormat("HH:mm");
//            //d = evaluatedf.parse(formattedDate);
//            d = evaluatedf.parse(myTime);
////            d.setTime(d.getTime());
//            Calendar cal = Calendar.getInstance();
//            cal.setTime(d);
//            cal.add(Calendar.MINUTE, 15);
//            String newTime = evaluatedf.format(cal.getTime());
//
//            System.out.println("New value: " + newTime);
//
//            int gapInMinutes =  15 ;  // Define your span-of-time.
//            int loops = ( (int) Duration.ofHours( 14 ).toMinutes() / gapInMinutes ) ;
////            List<LocalTime> times = new ArrayList<>( loops ) ;
//            times = new ArrayList<>();
//            time = LocalTime.parse(newTime);  // '00:00'
//            for( int i = 1 ; i <= loops ; i ++ ) {
//                times.add( time ) ;
//                // Set up next loop.
//                time = time.plusMinutes( gapInMinutes ) ;
//            }
//
//            System.out.println( times.size() + " ln slots: " ) ;
//            System.out.print("intervas:"+times+"\n");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//
//
//
//
//
//        chart.setBackgroundColor(Color.WHITE);
//        chart.getDescription().setEnabled(true);
//
//        ArrayList<CandleEntry> yValsCandleStick= new ArrayList();
////        yValsCandleStick.add(new CandleEntry(1, 150, 100, 104, 141));
////        yValsCandleStick.add(new CandleEntry(2, 130, 92, 110, 120));
////        yValsCandleStick.add(new CandleEntry(2, 70,  222, 225, 223));
////        yValsCandleStick.add(new CandleEntry(3, 150, 217, 222, 217));
//
//
//
//
//
//        yValsCandleStick.add(new CandleEntry(1, 80, 67, 100, 69));
//        yValsCandleStick.add(new CandleEntry(11, 228, 222, 223, 226));
//        yValsCandleStick.add(new CandleEntry(16, 226,  222, 225, 223));
//        yValsCandleStick.add(new CandleEntry(21, 222, 217, 222, 217));
//        yValsCandleStick.add(new CandleEntry(21, 222, 217, 222, 217));
//        CandleDataSet set1 = new CandleDataSet(yValsCandleStick, "Data Set");
//
//        set1.setDrawIcons(false);
//        set1.setAxisDependency(YAxis.AxisDependency.LEFT);
//        set1.setColor(Color.rgb(80, 80, 80));
//        set1.setShadowColor(Color.RED);
//        set1.setShadowWidth(8);
//        set1.setDecreasingColor(Color.RED);
//        set1.setDecreasingPaintStyle(Paint.Style.FILL);
//        set1.setIncreasingColor(Color.rgb(250, 0, 0));
//        set1.setIncreasingPaintStyle(Paint.Style.FILL);
//        //  bubbleChart();
//
//        CandleData data = new CandleData(set1);
//        chart.setData(data);
//        chart.invalidate();
//
//
//        chart.setMaxVisibleValueCount(30);
//        MyMarkerView mv = new MyMarkerView(getContext(), R.layout.custom_marker_view);
//        mv.setChartView(chart); // For bounds control
//        chart.setMarker(mv); // Set the marker to the chart
////        chart.setScaleXEnabled(false);
////       chart.setVisibleXRangeMaximum(30);
////        chart.setScaleYEnabled(false);
////        chart.setDrawGridBackground(false);
//
////        chart.setVisibleXRangeMaximum(20); // allow 20 values to be displayed at once on the x-axis, not more
////        chart.moveViewToX(15);
//        // scaling can now only be done on x- and y-axis separately
//        //        chart.setDrawGridBackground(false);
//
//        XAxis xAxis = chart.getXAxis();
//        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xAxis.setDrawGridLines(true);
//        xAxis.setDrawLabels(true);
//
//
//        YAxis leftAxis = chart.getAxisLeft();
//        leftAxis.setEnabled(true);
//        leftAxis.setLabelCount(7, true);
//        leftAxis.setGridColor(Color.GRAY);
//        leftAxis.setDrawGridLines(false);
//        leftAxis.enableGridDashedLine(15f, 1f, 10f);
//
//        leftAxis.setDrawAxisLine(true);
//
//        YAxis rightAxis = chart.getAxisRight();
//        rightAxis.setEnabled(false);
////        // setting data
////        seekBarX.setProgress(30);
////        seekBarY.setProgress(100);
////
//        chart.getLegend().setEnabled(false);
////
//        XAxis xAxis1;
//        {
//            xAxis = chart.getXAxis();
//
//            // vertical grid lines
//            xAxis.enableGridDashedLine(10f, 10f, 0f);
//            xAxis.setAxisMaximum(20);
//        }
////
////
//        YAxis yAxis;
//        {   // // Y-Axis Style // //
//            yAxis = chart.getAxisLeft();
//
//            // disable dual axis (only use LEFT axis)
//            chart.getAxisLeft().setEnabled(true);
//
//            // horizontal grid lines
//            yAxis.enableGridDashedLine(10f, 10f, 0f);
//
//            // axis range
//            yAxis.setAxisMaximum(200f);
//            yAxis.setAxisMinimum(0f);
//        }
////
//        {
//            LimitLine ll1 = new LimitLine(195f, "Max Rate");
//            ll1.setLineWidth(2f);
//
//            ll1.enableDashedLine(15f, 10f, 0f);
//            ll1.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
//            ll1.setTextSize(10f);
//
//
////            // add limit lines
//            LimitLine llXAxis = new LimitLine(35f, "Min Rate");
//            llXAxis.setLineWidth(2f);
//            llXAxis.enableDashedLine(15f, 10, 0f);
//            llXAxis.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
//            llXAxis.setTextSize(10f);
//
//            // add limit lines
//            LimitLine llXAveragexis = new LimitLine(120f, "Average Rate");
//            llXAveragexis.setLineWidth(1f);
////            llXAveragexis.setLineLenght(5);
//            llXAveragexis.setLabel("abg");
//            llXAveragexis.enableDashedLine(10f, 10f, 0f);
//            //           llXAveragexis.enableDashedLine(0f, 0, 10f);
//            llXAveragexis.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
//            llXAveragexis.setTextSize(10f);
//
//            xAxis.setDrawLimitLinesBehindData(true);
//            yAxis.setDrawLimitLinesBehindData(false);
////            yAxis.addLimitLine(ll1);
////            yAxis.addLimitLine(llXAxis);
//            yAxis.addLimitLine(llXAveragexis);
//
//        }
////
//        mAdapter = new MovieAdapter(movieList);
//        mLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
//        recyclerview.setLayoutManager(mLayoutManager);
//        recyclerview.setItemAnimator(new DefaultItemAnimator());
//        recyclerview.setAdapter(mAdapter);
////        edtShowData.setOnClickListener(this);
//        recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                switch (newState) {
//                    case SCROLL_STATE_DRAGGING:
//                        //Indicated that user scrolled.
//                        programaticallyScrolled = false;
//                        break;
//                    case SCROLL_STATE_IDLE:
//                        if (!programaticallyScrolled) {
////                            currentVisibleItem = mLayoutManager.findFirstCompletelyVisibleItemPosition();
//                            handleWritingViewNavigationArrows(false);
//                        }
//                        break;
//                    default:
//                        break;
//                }
//            }
//        });
//
//        prepareMovieData();



        return v;

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
        DialogFragment newFragment = new Demo1Fragment.SelectDateFragment();
        newFragment.show(getFragmentManager(), "DatePicker");
    }



//    private void showDatePicker() {
//        DatePickerFragment date = new DatePickerFragment();
//        /**
//         * Set Up Current Date Into dialog
//         */
//        Calendar calender = Calendar.getInstance();
//        Bundle args = new Bundle();
//        args.putInt("year", calender.get(Calendar.YEAR));
//        args.putInt("month", calender.get(Calendar.MONTH));
//        args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));
//        date.setArguments(args);
//        /**
//         * Set Call back to capture selected date
//         */
//        date.setCallBack(ondate);
//        date.show(getFragmentManager(), "Date Picker");
//    }




    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        progress = (seekBarX.getProgress());

//        tvX.setText(String.valueOf(progress));
//        tvY.setText(String.valueOf(seekBarY.getProgress()));

        chart.resetTracking();

        ArrayList<CandleEntry> values = new ArrayList<>();




        for (int i = 0; i < progress; i++) {
            //       float multi = (seekBarY.getProgress() + 1);
            float val = (float) (Math.random() * 40) + 50;

            float high = (float) (Math.random() * 9) + 8f;
            float low = (float) (Math.random() * 9) + 8f;

            float open = (float) (Math.random() * 6) + 1f;
            float close = (float) (Math.random() * 6) + 1f;

            boolean even = i % 4 == 0;

            values.add(new CandleEntry(
                    i,
                    val + high,
                    val - low,
                    even ? val + open : val - open,
                    even ? val - close : val + close
            ));

        }

        CandleDataSet set1 = new CandleDataSet(values, "Data Set");

        set1.setDrawIcons(false);
        set1.setAxisDependency(YAxis.AxisDependency.RIGHT);
        set1.setColor(Color.rgb(80, 80, 80));
        //     set1.setShadowColor(Color.RED);
        //   set1.setShadowWidth(3f);
        set1.setDecreasingColor(Color.RED);
        set1.setDecreasingPaintStyle(Paint.Style.FILL);
        set1.setIncreasingColor(Color.rgb(255, 0, 0));
        set1.setIncreasingPaintStyle(Paint.Style.FILL);
//        set1.setNeutralColor(Color.parseColor(""));
        set1.setHighlightLineWidth(0f);
        set1.setDrawValues(false);

        CandleData data = new CandleData(set1);

        chart.setData(data);
        chart.invalidate();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onClick(View view) {

        int id = view.getId();
        switch (id) {
            case R.id.img_Previous:
                scrollPreviousPosition();

                break;
            case R.id.img_Next:

                scrollNextPosition();

                break;
        }
    }

    private void prepareMovieData() {
        Movie movie = new Movie("Today,5-6 P.M.", "Action & Adventure", "2015");
        movieList.add(movie);

        movie = new Movie("6-7 P.M.", "Animation, Kids & Family", "2015");
        movieList.add(movie);

        movie = new Movie("7-8 P.M.", "Action", "2015");
        movieList.add(movie);

        movie = new Movie("Shaun the Sheep", "Animation", "2015");
        movieList.add(movie);

        movie = new Movie("" +
                "The Martian", "Science Fiction & Fantasy", "2015");
        movieList.add(movie);

        movie = new Movie("Mission: Impossible Rogue Nation", "Action", "2015");
        movieList.add(movie);

        movie = new Movie("Up", "Animation", "2009");
        movieList.add(movie);

        movie = new Movie("Star Trek", "Science Fiction", "2009");
        movieList.add(movie);

        movie = new Movie("The LEGO Movie", "Animation", "2014");
        movieList.add(movie);

        movie = new Movie("Iron Man", "Action & Adventure", "2008");
        movieList.add(movie);

        movie = new Movie("Aliens", "Science Fiction", "1986");
        movieList.add(movie);

        movie = new Movie("Chicken Run", "Animation", "2000");
        movieList.add(movie);

        movie = new Movie("Back to the Future", "Science Fiction", "1985");
        movieList.add(movie);

        movie = new Movie("Raiders of the Lost Ark", "Action & Adventure", "1981");
        movieList.add(movie);

        movie = new Movie("Goldfinger", "Action & Adventure", "1965");
        movieList.add(movie);

        movie = new Movie("Guardians of the Galaxy", "Science Fiction & Fantasy", "2014");
        movieList.add(movie);

        mAdapter.notifyDataSetChanged();
    }



    public void bubbleChart(){
//        chart.setVisibility(View.GONE);
        getEntries();
        bubbleDataSet = new BubbleDataSet(bubbleEntries, "");
        bubbleData = new BubbleData(bubbleDataSet);
        bubbleChart.setData(bubbleData);
        bubbleDataSet.setColors(R.color.red_300);
        bubbleDataSet.setValueTextColor(Color.BLACK);
        bubbleDataSet.setValueTextSize(14);


        XAxis xAxis1;
        {
            xAxis1 = bubbleChart.getXAxis();

            // vertical grid lines
            xAxis1.enableGridDashedLine(10f, 10f, 0f);
            xAxis1.setAxisMaximum(20);
        }
//
//
        YAxis yAxis;
        {   // // Y-Axis Style // //
            yAxis = bubbleChart.getAxisLeft();

            // disable dual axis (only use LEFT axis)
            bubbleChart.getAxisLeft().setEnabled(true);

            // horizontal grid lines
            yAxis.enableGridDashedLine(10f, 10f, 0f);

            // axis range
            yAxis.setAxisMaximum(200f);
            yAxis.setAxisMinimum(30f);
        }
//
        {
            // add limit lines
            LimitLine llXAveragexis = new LimitLine(120f, "Average Rate");
            llXAveragexis.setLineWidth(1f);
//            llXAveragexis.setLineLenght(5);
            llXAveragexis.setLabel("abg");
            llXAveragexis.enableDashedLine(10f, 10f, 0f);
            llXAveragexis.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
            llXAveragexis.setTextSize(10f);

            xAxis1.setDrawLimitLinesBehindData(true);
            xAxis1.addLimitLine(llXAveragexis);

        }
    }

    private void getEntries() {
        bubbleEntries = new ArrayList<>();
        bubbleEntries.add(new BubbleEntry(0, 1,0.21f));
        bubbleEntries.add(new BubbleEntry(1, 2,0.12f));
        bubbleEntries.add(new BubbleEntry(2, 3,0.20f));
        bubbleEntries.add(new BubbleEntry(2,4, 0.52f));
        bubbleEntries.add(new BubbleEntry(3, 5,0.20f));
        bubbleEntries.add(new BubbleEntry(4, 6,0.32f));
    }

}
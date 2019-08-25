package com.bpk.mydemopoc;


import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bpk.mydemopoc.CustomViews.MyMarkerView;
import com.bpk.mydemopoc.TabAdapter.PageAdapter;
import com.bpk.mydemopoc.TabAdapter.ViewPageAdapter;
import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class DemonextFragment extends Fragment implements SeekBar.OnSeekBarChangeListener,View.OnClickListener,
        OnChartValueSelectedListener {


    private ViewPager pager;
    TabLayout tabLayout;
    ViewPageAdapter adapter;
    PageAdapter fragmentAdapter;
    CharSequence Titles[] = {"H", "D", "W", "M"};
    int Numboftabs = 4;
    ConstraintLayout cl;
    //    private BarChart chart;
    private SeekBar seekBarX, seekBarY;
    private TextView tvX, tvY;
    BarDataSet set1, set2, set3, set4;
    //  private LineChart chart;
    private CandleStickChart chart;
    private GridView gvDemo;
    private Button btn_Week;
    private Button btn_Month;
    private Button btn_Year;


    public DemonextFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_graph_by_day, container, false);
        btn_Week = (Button) v.findViewById(R.id.btn_Weekly);
        btn_Month = (Button) v.findViewById(R.id.btn_Monthly);
        btn_Year = (Button) v.findViewById(R.id.btn_Year);
//        btn_Week.setElevation(8f);
        btn_Week.setOnClickListener(this);
        btn_Month.setOnClickListener(this);
        btn_Year.setOnClickListener(this);

//        tvX = (TextView) v.findViewById(R.id.tvXMax);
//        tvX.setTextSize(10);
//        tvY = (TextView)v.findViewById(R.id.tvYMax);

//        tvX = v.findViewById(R.id.tvXMax);
//        tvY = v.findViewById(R.id.tvYMax);
        //       gvDemo = v.findViewById(R.id.grid_view);

//
//        seekBarX = (SeekBar) v.findViewById(R.id.seekBar1);
//        seekBarX.setOnSeekBarChangeListener(this);
//
//        seekBarY = (SeekBar)v.findViewById(R.id.seekBar2);
//        seekBarY.setMax(180);
//        seekBarY.setOnSeekBarChangeListener(this);

        show_change_Week();

        chart = (CandleStickChart) v.findViewById(R.id.chart1);
        //chart.setOnChartValueSelectedListener(this);
        chart.getDescription().setEnabled(false);
        chart.setDrawBorders(true);
        // scaling can now only be done on x- and y-axis separately
        chart.setDrawGridBackground(false);

        // create a custom MarkerView (extend MarkerView) and specify the layout
        // to use for it
        MyMarkerView mv = new MyMarkerView(getContext(), R.layout.custom_marker_view);
        mv.setChartView(chart); // For bounds control
        chart.setMarker(mv); // Set the marker to the chart

//        seekBarX.setProgress(10);
//        seekBarY.setProgress(100);

//        Legend l = chart.getLegend();
//        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
//        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
//        l.setOrientation(Legend.LegendOrientation.VERTICAL);
//        l.setDrawInside(true);
//        l.setYOffset(0f);
//        l.setXOffset(10f);
//        l.setYEntrySpace(0f);
//        l.setTextSize(8f);

//        XAxis xAxis = chart.getXAxis();
//        //xAxis.setTypeface(tfLight);
//        xAxis.setGranularity(1f);
//        xAxis.setCenterAxisLabels(true);
//        xAxis.setValueFormatter(new ValueFormatter() {
//            @Override
//            public String getFormattedValue(float value) {
//                return String.valueOf((int) value);
//            }
//        });
//
//        YAxis leftAxis = chart.getAxisLeft();
//        //  leftAxis.setTypeface(tfLight);
//        leftAxis.setValueFormatter(new LargeValueFormatter());
//        leftAxis.setDrawGridLines(false);
//        leftAxis.setSpaceTop(35f);
//        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
//
//        chart.getAxisRight().setEnabled(false);

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawLabels(true);


        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setEnabled(true);
        leftAxis.setLabelCount(7, true);
        leftAxis.setGridColor(Color.GRAY);
        leftAxis.setDrawGridLines(true);
        leftAxis.enableGridDashedLine(15f, 5f, 10f);

//        leftAxis.setDrawAxisLine(true);

        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setEnabled(false);
        // setting data
//        seekBarX.setProgress(30);
//        seekBarY.setProgress(100);

        chart.getLegend().setEnabled(false);

        XAxis xAxis1;
        {
            xAxis = chart.getXAxis();

            // vertical grid lines
            xAxis.enableGridDashedLine(10f, 10f, 0f);
            xAxis.setAxisMaximum(20);
        }


        YAxis yAxis;
        {   // // Y-Axis Style // //
            yAxis = chart.getAxisLeft();

            // disable dual axis (only use LEFT axis)
            chart.getAxisLeft().setEnabled(true);

            // horizontal grid lines
            yAxis.enableGridDashedLine(10f, 10f, 0f);

            // axis range
            yAxis.setAxisMaximum(250f);
            yAxis.setAxisMinimum(0f);
        }

        {
            LimitLine ll1 = new LimitLine(195f, "Max Rate");
            ll1.setLineWidth(2f);
            ll1.enableDashedLine(15f, 10f, 0f);
            ll1.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
            ll1.setTextSize(10f);


//            // add limit lines
            LimitLine llXAxis = new LimitLine(35f, "Min Rate");
            llXAxis.setLineWidth(2f);
            llXAxis.enableDashedLine(15f, 10, 0f);
            llXAxis.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
            llXAxis.setTextSize(10f);

            xAxis.setDrawLimitLinesBehindData(false);
            yAxis.setDrawLimitLinesBehindData(false);
            yAxis.addLimitLine(ll1);
            yAxis.addLimitLine(llXAxis);

        }

        return v;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

//        tvX.setText(String.valueOf(seekBarX.getProgress()));
//        tvY.setText(String.valueOf(seekBarY.getProgress()));
//        setData(seekBarX.getProgress(), seekBarY.getProgress());
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

        chart.invalidate();
    }

    public void show_change_Week() {
        btn_Week.setBackgroundResource(R.drawable.bg_state);
        btn_Month.setBackgroundResource(R.drawable.bg_state_h);
        btn_Year.setBackgroundResource(R.drawable.bg_state_h);
        btn_Week.setTextColor(Color.parseColor("#ffffff"));
        btn_Month.setTextColor(Color.parseColor("#F44336"));
        btn_Year.setTextColor(Color.parseColor("#F44336"));
    }


    public void show_change_Month() {
        btn_Week.setBackgroundResource(R.drawable.bg_state_h);
        btn_Year.setBackgroundResource(R.drawable.bg_state_h);
        btn_Month.setBackgroundResource(R.drawable.bg_state);
        btn_Year.setTextColor(Color.parseColor("#F44336"));
        btn_Week.setTextColor(Color.parseColor("#F44336"));
        btn_Month.setTextColor(Color.parseColor("#FFFFFF"));


    }

    public void show_change_Year() {
        btn_Week.setBackgroundResource(R.drawable.bg_state_h);
        btn_Month.setBackgroundResource(R.drawable.bg_state_h);
        btn_Year.setBackgroundResource(R.drawable.bg_state);
        btn_Week.setTextColor(Color.parseColor("#F44336"));
        btn_Year.setTextColor(Color.parseColor("#FFFFFF"));
        btn_Month.setTextColor(Color.parseColor("#F44336"));


    }


    private void setData(int count, float range) {
//
//        ArrayList<Entry> values = new ArrayList<>();
//
//        for (int i = 0; i < count; i++) {
//
//            float val = (float) (Math.random() * range) - 30;
//            values.add(new Entry(i, val));
//        }
//
//        LineDataSet set1;
//
//        if (chart.getData() != null &&
//                chart.getData().getDataSetCount() > 0) {
//            set1 = (LineDataSet) chart.getData().getDataSetByIndex(0);
//            set1.setValues(values);
//            set1.notifyDataSetChanged();
//            chart.getData().notifyDataChanged();
//            chart.notifyDataSetChanged();
//        } else {
//            // create a dataset and give it a type
//            set1 = new LineDataSet(values, "DataSet 1");
//
//            set1.setDrawIcons(false);
//
//            // draw dashed line
//            set1.enableDashedLine(10f, 5f, 0f);
//
//            // black lines and points
//            set1.setColor(Color.BLACK);
//            set1.setCircleColor(Color.BLACK);
//
//            // line thickness and point size
//            set1.setLineWidth(1f);
//            set1.setCircleRadius(3f);
//
//            // draw points as solid circles
//            set1.setDrawCircleHole(false);
//
//            // customize legend entry
//            set1.setFormLineWidth(1f);
//            set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
//            set1.setFormSize(15.f);
//
//            // text size of values
//            set1.setValueTextSize(9f);
//
//            // draw selection line as dashed
//            set1.enableDashedHighlightLine(10f, 5f, 0f);
//
//            // set the filled area
//            set1.setDrawFilled(true);
//            set1.setFillFormatter(new IFillFormatter() {
//                @Override
//                public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
//                    return chart.getAxisLeft().getAxisMinimum();
//                }
//            });
//
//            // set color of filled area
//            if (Utils.getSDKInt() >= 18) {
//                // drawables only supported on api level 18 and above
//                Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.fade_red);
//                set1.setFillDrawable(drawable);
//            } else {
//                set1.setFillColor(Color.BLACK);
//            }
//
//            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
//            dataSets.add(set1); // add the data sets
//
//            // create a data object with the data sets
//            LineData data = new LineData(dataSets);
//
//            // set data
//            chart.setData(data);
//        }
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
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }



    @Override
    public void onClick(View view) {

        int id = view.getId();
        switch (id) {
            case R.id.btn_Weekly:
                show_change_Week();
                break;
            case R.id.btn_Monthly:
                show_change_Month();
                break;
            case R.id.btn_Year:
                show_change_Year();
                break;
        }


    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}


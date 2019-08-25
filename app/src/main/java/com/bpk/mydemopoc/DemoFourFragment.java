package com.bpk.mydemopoc;


import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bpk.mydemopoc.CustomViews.MyMarkerView;
import com.bpk.mydemopoc.TabAdapter.PageAdapter;
import com.bpk.mydemopoc.TabAdapter.ViewPageAdapter;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Utils;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class DemoFourFragment extends Fragment implements SeekBar.OnSeekBarChangeListener,
        OnChartValueSelectedListener{


    private ViewPager pager;
    TabLayout tabLayout ;
    ViewPageAdapter adapter;
    PageAdapter fragmentAdapter;
    CharSequence Titles[]={"H","D","W","M"};
    int Numboftabs =4;
    ConstraintLayout cl;
//    private BarChart chart;
    private SeekBar seekBarX, seekBarY;
    private TextView tvX, tvY;
    BarDataSet set1, set2, set3, set4;
    private LineChart chart;



    public DemoFourFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View v = inflater.inflate(R.layout.fragment_demo_four, container, false);

/*
        tabLayout = (TabLayout) v.findViewById(R.id.tab_layout1);
        tabLayout.addTab(tabLayout.newTab().setText("H"));
        tabLayout.addTab(tabLayout.newTab().setText("D"));
        tabLayout.addTab(tabLayout.newTab().setText("W"));
        tabLayout.addTab(tabLayout.newTab().setText("M"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        pager =  (ViewPager) v.findViewById(R.id.pager1);
        adapter =  new ViewPageAdapter(getActivity().getSupportFragmentManager(),Numboftabs);
        pager.setAdapter(adapter);
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab LayoutTab) {

                pager.setCurrentItem(LayoutTab.getPosition());



            }

            @Override
            public void onTabUnselected(TabLayout.Tab LayoutTab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab LayoutTab) {

            }
        });
*/
        tvX = (TextView) v.findViewById(R.id.tvXMax);
        tvX.setTextSize(10);
        tvY = (TextView)v.findViewById(R.id.tvYMax);

        tvX = v.findViewById(R.id.tvXMax);
        tvY = v.findViewById(R.id.tvYMax);

        seekBarX = v.findViewById(R.id.seekBar1);
        seekBarX.setOnSeekBarChangeListener(this);

        seekBarY = v.findViewById(R.id.seekBar2);
        seekBarY.setMax(180);
        seekBarY.setOnSeekBarChangeListener(this);


        chart = (LineChart) v.findViewById(R.id.chart1);
        chart.setOnChartValueSelectedListener(this);
        chart.getDescription().setEnabled(false);
//
        chart.setDrawBorders(true);
//
//        // scaling can now only be done on x- and y-axis separately
//        chart.setPinchZoom(false);
//
//        chart.setDrawBarShadow(false);
//
//        chart.setDrawGridBackground(false);

        // create a custom MarkerView (extend MarkerView) and specify the layout
        // to use for it
        MyMarkerView mv = new MyMarkerView(getContext(), R.layout.custom_marker_view);
        mv.setChartView(chart); // For bounds control
        chart.setMarker(mv); // Set the marker to the chart

        seekBarX.setProgress(10);
        seekBarY.setProgress(100);

        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(true);
        l.setYOffset(0f);
        l.setXOffset(10f);
        l.setYEntrySpace(0f);
        l.setTextSize(8f);

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

//        YAxis leftAxis = chart.getAxisLeft();
//      //  leftAxis.setTypeface(tfLight);
//        leftAxis.setValueFormatter(new LargeValueFormatter());
//        leftAxis.setDrawGridLines(false);
//        leftAxis.setSpaceTop(35f);
//        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        chart.getAxisRight().setEnabled(false);


        XAxis xAxis;
        {   // // X-Axis Style // //
            xAxis = chart.getXAxis();

            // vertical grid lines
            xAxis.enableGridDashedLine(10f, 10f, 0f);
        }

        YAxis yAxis;
        {   // // Y-Axis Style // //
            yAxis = chart.getAxisLeft();

            // disable dual axis (only use LEFT axis)
            chart.getAxisRight().setEnabled(false);

            // horizontal grid lines
            yAxis.enableGridDashedLine(10f, 10f, 0f);

            // axis range
            yAxis.setAxisMaximum(200f);
            yAxis.setAxisMinimum(-50f);
        }


        {   // // Create Limit Lines // //
            LimitLine llXAxis = new LimitLine(9f, "Index 10");
            llXAxis.setLineWidth(4f);
            llXAxis.enableDashedLine(10f, 10f, 0f);
            llXAxis.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
            llXAxis.setTextSize(10f);

            LimitLine ll1 = new LimitLine(150f, "Max Rate");
            ll1.setLineWidth(4f);
            ll1.enableDashedLine(10f, 10f, 0f);
            ll1.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
            ll1.setTextSize(10f);

            LimitLine ll2 = new LimitLine(-30f, "Min Rate");
            ll2.setLineWidth(4f);
            ll2.enableDashedLine(10f, 10f, 0f);
            ll2.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
            ll2.setTextSize(10f);

            // draw limit lines behind data instead of on top
            yAxis.setDrawLimitLinesBehindData(true);
            //xAxis.setDrawLimitLinesBehindData(true);

            // add limit lines
            yAxis.addLimitLine(ll1);
            yAxis.addLimitLine(ll2);
            xAxis.addLimitLine(llXAxis);
        }

        // add data
        seekBarX.setProgress(45);
        seekBarY.setProgress(180);
        setData(45, 180);

        // draw points over time
        chart.animateX(1500);

        // get the legend (only possible after setting data)
//        Legend l = chart.getLegend();

        // draw legend entries as lines
        l.setForm(Legend.LegendForm.LINE);




        return v;
    }




    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

//        float groupSpace = 0.08f;
//        float barSpace = 0.03f; // x4 DataSet
//        float barWidth = 0.2f; // x4 DataSet
//        // (0.2 + 0.03) * 4 + 0.08 = 1.00 -> interval per "group"
//
//        int groupCount = seekBarX.getProgress() + 1;
//        int startYear = 1980;
//        int endYear = startYear + groupCount;
//
//        tvX.setText(String.format(Locale.ENGLISH, "%d-%d", startYear, endYear));
//        tvY.setText(String.valueOf(seekBarY.getProgress()));
//
//        ArrayList<BarEntry> values1 = new ArrayList<>();
//        ArrayList<BarEntry> values2 = new ArrayList<>();
//        ArrayList<BarEntry> values3 = new ArrayList<>();
//        ArrayList<BarEntry> values4 = new ArrayList<>();
//
//        float randomMultiplier = seekBarY.getProgress() * 100000f;
//
//        for (int i = startYear; i < endYear; i++) {
//            values1.add(new BarEntry(i, (float) (Math.random() * randomMultiplier)));
//            values2.add(new BarEntry(i, (float) (Math.random() * randomMultiplier)));
//            values3.add(new BarEntry(i, (float) (Math.random() * randomMultiplier)));
//            values4.add(new BarEntry(i, (float) (Math.random() * randomMultiplier)));
//        }
//
//        BarDataSet set1, set2, set3, set4;
//
//        if (chart.getData() != null && chart.getData().getDataSetCount() > 0) {
//
//            set1 = (BarDataSet) chart.getData().getDataSetByIndex(0);
//            set2 = (BarDataSet) chart.getData().getDataSetByIndex(1);
//            set3 = (BarDataSet) chart.getData().getDataSetByIndex(2);
//            set4 = (BarDataSet) chart.getData().getDataSetByIndex(3);
//            set1.setValues(values1);
//            set2.setValues(values2);
//            set3.setValues(values3);
//            set4.setValues(values4);
//            chart.getData().notifyDataChanged();
//            chart.notifyDataSetChanged();
//
//        } else {
//            // create 4 DataSets
//            set1 = new BarDataSet(values1, "Company A");
//            set1.setColor(Color.rgb(104, 241, 175));
//            set2 = new BarDataSet(values2, "Company B");
//            set2.setColor(Color.rgb(164, 228, 251));
//            set3 = new BarDataSet(values3, "Company C");
//            set3.setColor(Color.rgb(242, 247, 158));
//            set4 = new BarDataSet(values4, "Company D");
//            set4.setColor(Color.rgb(255, 102, 0));
//
//            BarData data = new BarData(set1, set2, set3, set4);
//            data.setValueFormatter(new LargeValueFormatter());
//    //        data.setValueTypeface(tfLight);
//
//            chart.setData(data);
//        }
//
//        // specify the width each bar should have
//        chart.getBarData().setBarWidth(barWidth);
//
//        // restrict the x-axis range
//        chart.getXAxis().setAxisMinimum(startYear);
//
//        // barData.getGroupWith(...) is a helper that calculates the width each group needs based on the provided parameters
//        chart.getXAxis().setAxisMaximum(startYear + chart.getBarData().getGroupWidth(groupSpace, barSpace) * groupCount);
//        chart.groupBars(startYear, groupSpace, barSpace);
        tvX.setText(String.valueOf(seekBarX.getProgress()));
        tvY.setText(String.valueOf(seekBarY.getProgress()));

        setData(seekBarX.getProgress(), seekBarY.getProgress());

        // redraw
        chart.invalidate();

        chart.invalidate();
    }

    private void setData(int count, float range) {

        ArrayList<Entry> values = new ArrayList<>();

//        for (int i = 0; i < count; i++) {
//
//            float val = (float) (Math.random() * range) - 30;
//            values.add(new Entry(i, val, getResources().getDrawable(R.drawable.star)));
//        }

        LineDataSet set1;

        if (chart.getData() != null &&
                chart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) chart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            set1.notifyDataSetChanged();
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(values, "DataSet 1");

            set1.setDrawIcons(false);

            // draw dashed line
            set1.enableDashedLine(10f, 5f, 0f);

            // black lines and points
            set1.setColor(Color.BLACK);
            set1.setCircleColor(Color.BLACK);

            // line thickness and point size
            set1.setLineWidth(1f);
            set1.setCircleRadius(3f);

            // draw points as solid circles
            set1.setDrawCircleHole(false);

            // customize legend entry
            set1.setFormLineWidth(1f);
            set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set1.setFormSize(15.f);

            // text size of values
            set1.setValueTextSize(9f);

            // draw selection line as dashed
            set1.enableDashedHighlightLine(10f, 5f, 0f);

            // set the filled area
            set1.setDrawFilled(true);
            set1.setFillFormatter(new IFillFormatter() {
                @Override
                public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                    return chart.getAxisLeft().getAxisMinimum();
                }
            });

            // set color of filled area
            if (Utils.getSDKInt() >= 18) {
                // drawables only supported on api level 18 and above
                Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.fade_red);
                set1.setFillDrawable(drawable);
            } else {
                set1.setFillColor(Color.BLACK);
            }

            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1); // add the data sets

            // create a data object with the data sets
            LineData data = new LineData(dataSets);

            // set data
            chart.setData(data);
        }
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
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }

}

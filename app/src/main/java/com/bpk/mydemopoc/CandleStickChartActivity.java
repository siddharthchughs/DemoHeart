
package com.bpk.mydemopoc;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.bpk.mydemopoc.CustomViews.MyMarkerView;
import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.components.YAxis.AxisDependency;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.interfaces.datasets.ICandleDataSet;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;

import java.util.ArrayList;

public class CandleStickChartActivity extends DemoBase implements OnSeekBarChangeListener {

    private CandleStickChart chart;
    private SeekBar seekBarX, seekBarY;
    private TextView tvX, tvY;
    IDataSet set;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_candlechart);

        setTitle("CandleStickChartActivity");

        tvX = findViewById(R.id.tvXMax);
        tvY = findViewById(R.id.tvYMax);

        seekBarX = findViewById(R.id.seekBar1);
        seekBarX.setOnSeekBarChangeListener(this);

        seekBarY = findViewById(R.id.seekBar2);
        seekBarY.setOnSeekBarChangeListener(this);

        chart = findViewById(R.id.chart1);
        chart.setBackgroundColor(Color.WHITE);
    //    chart.setScaleXEnabled(false);

        chart.getDescription().setEnabled(false);

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        chart.setMaxVisibleValueCount(30);
        MyMarkerView mv = new MyMarkerView(this, R.layout.custom_marker_view);
        mv.setChartView(chart); // For bounds control
        chart.setMarker(mv); // Set the marker to the chart
        chart.setScaleXEnabled(true);
        chart.setScaleYEnabled(true);


        // scaling can now only be done on x- and y-axis separately
      //  chart.setPinchZoom(false);

//        chart.setDrawGridBackground(false);

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawLabels(true);


        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setEnabled(true);
        leftAxis.setLabelCount(5, true);
        leftAxis.setDrawGridLines(true);
        leftAxis.setGridColor(Color.GRAY);
        leftAxis.setDrawTopYLabelEntry(true);

//        leftAxis.setDrawAxisLine(true);

        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setEnabled(false);
        // setting data
        seekBarX.setProgress(30);
        seekBarY.setProgress(100);

        chart.getLegend().setEnabled(false);

        XAxis xAxis1;
        {
            xAxis = chart.getXAxis();

            // vertical grid lines
            xAxis.enableGridDashedLine(10f, 10f, 0f);
//            xAxis.setAxisMaximum(20);
        }


        YAxis yAxis;
        {   // // Y-Axis Style // //
            yAxis = chart.getAxisLeft();

            // disable dual axis (only use LEFT axis)
//            chart.getAxisRight().setEnabled(true);

            // horizontal grid lines
            yAxis.enableGridDashedLine(10f, 10f, 0f);

            // axis range
            yAxis.setAxisMaximum(250f);
            yAxis.setAxisMinimum(0f);
        }

//        YAxis ybottomAxis;
//        {   // // Y-Axis Style // //
//            ybottomAxis = chart.getAxisLeft();
//
//            // disable dual axis (only use LEFT axis)
//            chart.getAxisRight().setEnabled(true);
//
//            // horizontal grid lines
//            ybottomAxis.enableGridDashedLine(10f, 9f, 0f);
//
//            // axis range
//            ybottomAxis.setAxisMaximum(600f);
//            ybottomAxis.setAxisMinimum(-50f);
//        }


        {
            LimitLine ll1 = new LimitLine(195f, "Max Rate");
            ll1.setLineWidth(2f);
            ll1.enableDashedLine(15f, 10f, 5f);
            ll1.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
            ll1.setTextSize(10f);


            // add limit lines
            LimitLine llXAxis = new LimitLine(35f, "Min Rate");
            llXAxis.setLineWidth(2f);
            llXAxis.enableDashedLine(15f, 4f, 0f);
            llXAxis.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
            llXAxis.setTextSize(10f);

            xAxis.setDrawLimitLinesBehindData(false);
            yAxis.setDrawLimitLinesBehindData(false);
            yAxis.addLimitLine(ll1);
            yAxis.addLimitLine(llXAxis);

        }
    }
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        progress = (seekBarX.getProgress());

        tvX.setText(String.valueOf(progress));
        tvY.setText(String.valueOf(seekBarY.getProgress()));

        chart.resetTracking();

        ArrayList<CandleEntry> values = new ArrayList<>();

        for (int i = 0; i < progress; i++) {
            float multi = (seekBarY.getProgress() + 1);
            float val = (float) (Math.random() * 40) + multi;

            float high = (float) (Math.random() * 9) + 8f;
            float low = (float) (Math.random() * 9) + 8f;

            float open = (float) (Math.random() * 6) + 1f;
            float close = (float) (Math.random() * 6) + 1f;

            boolean even = i % 2 == 0;

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
        set1.setAxisDependency(AxisDependency.RIGHT);
        set1.setColor(Color.rgb(80, 80, 80));
       //set1.setShadowColor(Color.WHITE);
        set1.setShadowWidth(1.0f);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.candle, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.actionToggleValues: {
                for (IDataSet set : chart.getData().getDataSets())
                    set.setDrawValues(!set.isDrawValuesEnabled());

                chart.invalidate();
                break;
            }
            case R.id.actionToggleIcons: {
                for (IDataSet set : chart.getData().getDataSets())
                    set.setDrawIcons(!set.isDrawIconsEnabled());

                chart.invalidate();
                break;
            }
            case R.id.actionToggleHighlight: {
                if(chart.getData() == null) {
                    chart.getData().setHighlightEnabled(!chart.getData().isHighlightEnabled());
                    chart.invalidate();
                }
                break;
            }
            case R.id.actionTogglePinch: {
                if (chart.isPinchZoomEnabled())
                    chart.setPinchZoom(false);
                else
                    chart.setPinchZoom(true);

                chart.invalidate();
                break;
            }
            case R.id.actionToggleAutoScaleMinMax: {
                chart.setAutoScaleMinMaxEnabled(!chart.isAutoScaleMinMaxEnabled());
                chart.notifyDataSetChanged();
                break;
            }
            case R.id.actionToggleMakeShadowSameColorAsCandle: {
                for (ICandleDataSet set : chart.getData().getDataSets()) {
                    ((CandleDataSet) set).setShadowColorSameAsCandle(!set.getShadowColorSameAsCandle());
                }

                chart.invalidate();
                break;
            }
            case R.id.animateX: {
                chart.animateX(2000);
                break;
            }
            case R.id.animateY: {
                chart.animateY(2000);
                break;
            }
            case R.id.animateXY: {
                chart.animateXY(2000, 2000);
                break;
            }
        }
        return true;
    }

    @Override
    protected void saveToGallery() {
        saveToGallery(chart, "CandleStickChartActivity");
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {}

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {}
}

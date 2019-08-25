package com.bpk.mydemopoc;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.transition.Transition;
import android.support.transition.TransitionInflater;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Scene;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.graphics.Paint;
import android.graphics.RectF;



import com.bpk.mydemopoc.TabAdapter.BarDemoAdapter;
import com.bpk.mydemopoc.TabAdapter.GraphDataAdapter;
import com.bpk.mydemopoc.TabAdapter.MovieAdapter;
import com.bpk.mydemopoc.Utility.CalculationOffset;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {

    private BarChart chart;
    private SeekBar seekBarX, seekBarY;
    private TextView tvX, tvY;
    private ConstraintLayout cl;
    DrawLineGraph drawViewGraph;
    public ImageView imageView;
    int startX = 20;
    int startY = 100;
    int stopX = 140;
    int stopY = 30;
    CalculationOffset calOffset;
    RectF mRectF;
    Paint mPaint, otherPaint, outerPaint, mTextPaint;


    private DashPathEffect mAxisLineDashPathEffect = null;
    ViewGroup sceneRoot;
    private View contentView;
    private View loadingView;
    private int shortAnimationDuration;
    private GridView grid_view;
    private GraphDataAdapter graphDataAdapter;
    private RecyclerView recyclerviewGrid;
    private RecyclerView.LayoutManager layoutManager;
    private List<Movie> movieList = new ArrayList<>();
    private MovieAdapter mAdapter;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        drawViewGraph = new DrawLineGraph(this);
        drawViewGraph.setBackgroundColor(Color.BLUE);
        setContentView(R.layout.activity_main);
//        contentView = findViewById(R.id.content);
        imageView = findViewById(R.id.imageView);
//        loadingView = findViewById(R.id.loading_spinner);
//        grid_view =(GridView) findViewById(R.id.grid_view);
//        grid_view.setAdapter(new BarDemoAdapter(this));
//        grid_view.setFastScrollEnabled(true);
//        recyclerviewGrid = (RecyclerView) findViewById(R.id.recyclerviewGrid);
//        graphDataAdapter = new GraphDataAdapter(movieList);
////        layoutManager = new GridLayoutManager(this,6);
//        recyclerviewGrid.setLayoutManager(new GridLayoutManager(getApplication(),4));
//        recyclerviewGrid.setItemAnimator(new DefaultItemAnimator());
//        recyclerviewGrid.setAdapter(graphDataAdapter);
//        prepareMovieData();
        // Initially hide the content view.
      //  contentView.setVisibility(View.GONE);

        // Retrieve and cache the system's default "short" animation time.
//        shortAnimationDuration = getResources().getInteger(
//                android.R.integer.config_shortAnimTime);
//        imageView = (ImageView) findViewById(R.id.imageView);
//        GraphView graph = (GraphView) findViewById(R.id.graph);
//        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
//                new DataPoint(0, 1),
//                new DataPoint(1, 5),
//                new DataPoint(2, 3),
//                new DataPoint(3, 2),
//                new DataPoint(4, 6)
//        });
//        graph.addSeries(series);
//        GraphView graph = (GraphView) findViewById(R.id.graph);
//        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
//                new DataPoint(0, 1),
//                new DataPoint(1, 5),
//                new DataPoint(2, 3),
//                new DataPoint(3, 2),
//                new DataPoint(4, 6)
//        });
//        graph.addSeries(series);

        showLine();
//        XYSeries series = new XYSeries("Foo Bar Chart");
//        Random rn = new Random();
//        for (int i = 0; i < 10; i++) {
//            series.add(i, (int)rn.nextInt(10+i) + 1)
//        }
//        XYSeriesRenderer renderer = new XYSeriesRenderer();
//        renderer.setLineWidth(2);
//        renderer.setColor(Color.RED);
//        renderer.setDisplayBoundingPoints(true);
//        renderer.setPointStyle(PointStyle.CIRCLE);
//        renderer.setPointStrokeWidth(3);
//        XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
//        mRenderer.addSeriesRenderer(renderer);
//        mRenderer.setMarginsColor(Color.argb(0x00, 0xff, 0x00, 0x00));
//        mRenderer.setPanEnabled(false, false);
//        mRenderer.setYAxisMax(35);
//        mRenderer.setYAxisMin(0);
//        mRenderer.setShowGrid(true);
//        GraphicalView chartView = ChartFactory.getLineChartView(getActivity(), dataset, mRenderer);
//        chartLinearLayout.addView(chartView, 0);

        Scene aScene;
        Scene anotherScene;

// Create the scene root for the scenes in this app
        sceneRoot = (ViewGroup) findViewById(R.id.scene_root);

//// Create the scenes
//        aScene = Scene.getSceneForLayout(sceneRoot, R.layout.a_scene, this);
//        anotherScene =
//                Scene.getSceneForLayout(sceneRoot, R.layout.another_scene, this);
//
//        Transition fadeTransition =
//                TransitionInflater.from(this).
//                        inflateTransition(R.transition.fade_transition);

     //   crossfade();

    }



    private void crossfade() {

        // Set the content view to 0% opacity but visible, so that it is visible
        // (but fully transparent) during the animation.
//        contentView.setAlpha(1f);
//        contentView.setVisibility(View.VISIBLE);
//
//        // Animate the content view to 100% opacity, and clear any animation
//        // listener set on the view.
//        contentView.animate()
//                .alpha(1.7f)
//                .setDuration(shortAnimationDuration)
//                .setListener(null);
//
//        // Animate the loading view to 0% opacity. After the animation ends,
//        // set its visibility to GONE as an optimization step (it won't
//        // participate in layout passes, etc.)
//        loadingView.animate()
//                .alpha(1f)
//                .setDuration(shortAnimationDuration)
//                .setListener(new AnimatorListenerAdapter() {
//                    @Override
//                    public void onAnimationEnd(Animator animation) {
//                        loadingView.setVisibility(View.GONE);
//                    }
//                });
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

        graphDataAdapter.notifyDataSetChanged();
    }

    public void showLine(){
        otherPaint = new Paint();

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//        ((MainActivity) getContext()).getWindowManager()
//                .getDefaultDisplay()
//                .getMetrics(displayMetrics);


        int screenWidth = displayMetrics.widthPixels;
        int screenHeight = displayMetrics.heightPixels;

        mRectF = new RectF(screenWidth / 4, screenHeight / 3, screenWidth / 6, screenHeight / 2);

//        BarChart chart = new BarChart(this);
        calOffset = new CalculationOffset();
        Bitmap bitmap = Bitmap.createBitmap(
                calOffset.width, // Width
                calOffset.height, // Height
                Bitmap.Config.ARGB_8888 // Config
        );

//        chart.setDrawGridBackground(true);


//        ValueFormatter custom = new MyValueFormatter("$");

//        YAxis leftAxis = chart.getAxisLeft();
//  ///      leftAxis.setTypeface(tfLight);
//        leftAxis.setLabelCount(8, false);
//     //   leftAxis.setValueFormatter(custom);
//        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
//        leftAxis.setSpaceTop(15f);
//        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)




        // Initialize a new Canvas instance
        Canvas canvas = new Canvas(bitmap);
        // Draw a solid color on the canvas as background
        canvas.drawColor(Color.WHITE);
        // Initialize a new Paint instance to draw the line
        Paint paint = new Paint();
        // Line color
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        // Line width in pixels
        paint.setStrokeWidth(5);
        paint.setAntiAlias(true);




//        // Set a pixels value to offset the line from canvas edge
//        int offset = 0;
//        // Set a pixels value to offset the line from canvas edge y
//        int offsetyaxis = 0;
//        int offheightyaixs=600;




                /*
                    public void drawLine (float startX, float startY, float stopX, float stopY, Paint paint)
                        Draw a line segment with the specified start and stop x,y coordinates, using
                        the specified paint.

                        Note that since a line is always "framed", the Style is ignored in the paint.

                        Degenerate lines (length is 0) will not be drawn.

                    Parameters
                        startX : The x-coordinate of the start point of the line
                        startY : The y-coordinate of the start point of the line
                        paint : The paint used to draw the line

                */

        // Draw a line on canvas at the center position
        canvas.drawLine(
                calOffset.start_x_axisstart, // startX
                calOffset.start_x_y_axisstart, // startY
                calOffset.start_x_axisstop, // stopX
                calOffset.start_x_y_axisstop, // stopY
                paint // Paint
        );
        canvas.drawLine(calOffset.start_y_x_axisstart, // startY
                calOffset.start_y_axisstart , // startY
                calOffset.start_y_x_axisstop, // stopX
                calOffset.start_y_axisstop, // stopY
                paint // Paint
        );
        canvas.drawRoundRect(mRectF, 10, 10, otherPaint);
        canvas.clipRect(mRectF, Region.Op.DIFFERENCE);
//        canvas.drawPaint(outerPaint);

        canvas.drawLine(590, // startY
                20 , // startY
                130, // stopX
                20, // stopY
                paint // Paint
        );

        canvas.drawRoundRect(mRectF, 120, 210, otherPaint);


//        LimitLine ll = new LimitLine(140f, "Critical Blood Pressure");
//        ll.setLineColor(Color.RED);
//        ll.setLineWidth(4f);
//        ll.setTextColor(Color.BLACK);
//        ll.setTextSize(12f);
    //    leftAxis.addLimitLine(ll);

        imageView.setImageBitmap(bitmap);


    }

    public void enableAxisLineDashedLine(float lineLength, float spaceLength, float phase) {
        mAxisLineDashPathEffect = new DashPathEffect(new float[]{
                lineLength, spaceLength
        }, phase);
    }

    /**
     * Enables the axis line to be drawn in dashed mode, e.g. like this
     * "- - - - - -". THIS ONLY WORKS IF HARDWARE-ACCELERATION IS TURNED OFF.
     * Keep in mind that hardware acceleration boosts performance.
     *
     * @param effect the DashPathEffect
     */
    public void setAxisLineDashedLine(DashPathEffect effect) {
        mAxisLineDashPathEffect = effect;
    }

    /**
     * Disables the axis line to be drawn in dashed mode.
     */
    public void disableAxisLineDashedLine() {
        mAxisLineDashPathEffect = null;
    }

    /**
     * Returns true if the axis dashed-line effect is enabled, false if not.
     *
     * @return
     */
    public boolean isAxisLineDashedLineEnabled() {
        return mAxisLineDashPathEffect == null ? false : true;
    }

    /**
     * returns the DashPathEffect that is set for axis line
     *
     * @return
     */
    public DashPathEffect getAxisLineDashPathEffect() {
        return mAxisLineDashPathEffect;
    }

    /**
     * ###### BELOW CODE RELATED TO CUSTOM AXIS VALUES ######
     */

//    public float getAxisMaximum() {
//        return mAxisMaximum;
//    }
//
//    public float getAxisMinimum() {
//        return mAxisMinimum;
//    }


//    private void openChart(){
//        int[] x = { 0,1,2,3,4,5,6,7 };
//        int[] income = { 2000,2500,2700,3000,2800,3500,3700,3800};
//        int[] expense = {2200, 2700, 2900, 2800, 2600, 3000, 3300, 3400 };
//
//        // Creating an  XYSeries for Income
//        XYSeries incomeSeries = new XYSeries("Income");
//        // Creating an  XYSeries for Expense
//        XYSeries expenseSeries = new XYSeries("Expense");
//        // Adding data to Income and Expense Series
//        for(int i=0;i<x.length;i++){
//            incomeSeries.add(i,income[i]);
//            expenseSeries.add(i,expense[i]);
//        }
//
//        // Creating a dataset to hold each series
//        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
//        // Adding Income Series to the dataset
//        dataset.addSeries(incomeSeries);
//        // Adding Expense Series to dataset
//        dataset.addSeries(expenseSeries);
//
//        // Creating XYSeriesRenderer to customize incomeSeries
//        XYSeriesRenderer incomeRenderer = new XYSeriesRenderer();
//        incomeRenderer.setColor(Color.rgb(130, 130, 230));
//        incomeRenderer.setFillPoints(true);
//        incomeRenderer.setLineWidth(2);
//        incomeRenderer.setDisplayChartValues(true);
//
//        // Creating XYSeriesRenderer to customize expenseSeries
//        XYSeriesRenderer expenseRenderer = new XYSeriesRenderer();
//        expenseRenderer.setColor(Color.rgb(220, 80, 80));
//        expenseRenderer.setFillPoints(true);
//        expenseRenderer.setLineWidth(2);
//        expenseRenderer.setDisplayChartValues(true);
//
//        // Creating a XYMultipleSeriesRenderer to customize the whole chart
//        XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();
//        multiRenderer.setXLabels(0);
//        multiRenderer.setChartTitle("Income vs Expense Chart");
//        multiRenderer.setXTitle("Year 2012");
//        multiRenderer.setYTitle("Amount in Dollars");
//        multiRenderer.setZoomButtonsVisible(true);
//        for(int i=0; i< x.length;i++){
//            multiRenderer.addXTextLabel(i, mMonth[i]);
//        }
//
//        // Adding incomeRenderer and expenseRenderer to multipleRenderer
//        // Note: The order of adding dataseries to dataset and renderers to multipleRenderer
//        // should be same
//        multiRenderer.addSeriesRenderer(incomeRenderer);
//        multiRenderer.addSeriesRenderer(expenseRenderer);
//
//        // Creating an intent to plot bar chart using dataset and multipleRenderer
//        Intent intent = ChartFactory.getBarChartIntent(getBaseContext(), dataset, multiRenderer, Type.DEFAULT);
//
//        // Start Activity
//        startActivity(intent);
//
//    }




}
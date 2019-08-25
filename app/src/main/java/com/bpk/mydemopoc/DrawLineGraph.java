package com.bpk.mydemopoc;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;



    public class DrawLineGraph extends View {
        Paint paint = new Paint();
        MainActivity mainActivity;

        private void init() {
            paint.setColor(Color.CYAN);
        }

        public DrawLineGraph(Context context) {
            super(context);
            init();
        }

        public DrawLineGraph(Context context, AttributeSet attrs) {
            super(context, attrs);
            init();
        }

        public DrawLineGraph(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
            init();
        }

        @Override
        public void onDraw(Canvas canvas) {
            Bitmap bitmap = Bitmap.createBitmap(
                    1000, // Width
                    700, // Height
                    Bitmap.Config.ARGB_8888 // Config
            );

            // Initialize a new Canvas instance
            canvas = new Canvas(bitmap);

            // Draw a solid color on the canvas as background
            canvas.drawColor(Color.LTGRAY);

            // Initialize a new Paint instance to draw the line
            Paint paint = new Paint();
            // Line color
            paint.setColor(Color.RED);
            paint.setStyle(Paint.Style.STROKE);
            // Line width in pixels
            paint.setStrokeWidth(8);
            paint.setAntiAlias(true);

            // Set a pixels value to offset the line from canvas edge
            int offset = 0;
            // Set a pixels value to offset the line from canvas edge y
            int offsetyaxis = 0;
            int offheightyaixs=600;




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
                    40, // startX
                    550, // startY
                    900, // stopX
                    550, // stopY
                    paint // Paint
            );
            canvas.drawLine(95, // startY
                    600 , // startY
                    95, // stopX
                    90, // stopY
                    paint // Paint
            );

            canvas.drawLine(50, 10, 20, 120, paint);
            canvas.drawLine(420, 10, 0, 120, paint);
//            Bitmap bitmap = Bitmap.createBitmap(
//                    500, // Width
//                    300, // Height
//                    Bitmap.Config.ARGB_8888 // Config
//            );

            // Initialize a new Canvas instance
//             canvas = new Canvas(bitmap);
//
//            // Draw a solid color on the canvas as background
//            canvas.drawColor(Color.LTGRAY);
//
//            // Initialize a new Paint instance to draw the line
//            Paint paint = new Paint();
//            // Line color
//            paint.setColor(Color.RED);
//            paint.setStyle(Paint.Style.STROKE);
//            // Line width in pixels
//            paint.setStrokeWidth(8);
//            paint.setAntiAlias(true);
//
//            // Set a pixels value to offset the line from canvas edge
//            int offset = 50;
//
//                /*
//                    public void drawLine (float startX, float startY, float stopX, float stopY, Paint paint)
//                        Draw a line segment with the specified start and stop x,y coordinates, using
//                        the specified paint.
//
//                        Note that since a line is always "framed", the Style is ignored in the paint.
//
//                        Degenerate lines (length is 0) will not be drawn.
//
//                    Parameters
//                        startX : The x-coordinate of the start point of the line
//                        startY : The y-coordinate of the start point of the line
//                        paint : The paint used to draw the line
//
//                */
//
//            // Draw a line on canvas at the center position
//            canvas.drawLine(
//                    offset, // startX
//                    canvas.getHeight() / 2, // startY
//                    canvas.getWidth() - offset, // stopX
//                    canvas.getHeight() / 2, // stopY
//                    paint // Paint
//            );







        }

    }


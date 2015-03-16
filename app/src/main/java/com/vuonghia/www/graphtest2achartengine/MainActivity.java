package com.vuonghia.www.graphtest2achartengine;


    import org.achartengine.ChartFactory;
    import org.achartengine.chart.BarChart.Type;
    import org.achartengine.model.XYMultipleSeriesDataset;
    import org.achartengine.model.XYSeries;
    import org.achartengine.renderer.XYMultipleSeriesRenderer;
    import org.achartengine.renderer.XYSeriesRenderer;

    import android.graphics.Color;
    import android.graphics.Paint.Align;
    import android.graphics.Typeface;
    import android.os.Bundle;
    import android.support.v7.app.ActionBarActivity;
    import android.view.Menu;
    import android.view.MenuItem;
    import android.view.View;
    import android.view.View.OnClickListener;
    import android.widget.Button;
    import android.widget.LinearLayout;

public class MainActivity extends ActionBarActivity {

        private View mChart;
        private String[] mMonth = new String[] {
                "Jan", "Feb" , "Mar", "Apr", "May", "Jun", "Jul", "Aug" , "Sep"
        };

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            // Getting reference to the button btn_chart
            Button btnChart = (Button) findViewById(R.id.btn_chart);
            Button btnChart2 = (Button) findViewById(R.id.btn_chart2);

            // Defining click event listener for the button btn_chart
            OnClickListener clickListener = new OnClickListener() {

                @Override
                public void onClick(View v) {
                    // Draw the Income vs Expense Chart
                    openChart();
                }
            };
            // Defining click event listener for the button btn_chart
            OnClickListener clickListener2 = new OnClickListener() {

                @Override
                public void onClick(View v) {
                    // Draw the Income vs Expense Chart
                    openChart2();
                }
            };
            // Setting event click listener for the button btn_chart of the MainActivity layout
            btnChart.setOnClickListener(clickListener);
            btnChart2.setOnClickListener(clickListener2);

        }

    private void openChart(){
            int[] x = { 0, 1, 2, 3, 4, 5, 6, 7};
            int[] income = {400,250,270,300,280,350,370,380};
//            int[] expense = {220,270,290,280,260,300,30,340};

            // Creating an XYSeries for Income
            XYSeries incomeSeries = new XYSeries("Income");
            // Creating an XYSeries for Expense
//            XYSeries expenseSeries = new XYSeries("Expense");
            // Adding data to Income and Expense Series
            for(int i=0;i<x.length;i++){
                incomeSeries.add(i,income[i]);
//                expenseSeries.add(i,expense[i]);
            }

            // Creating a dataset to hold each series
            XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
            // Adding Income Series to the dataset
            dataset.addSeries(incomeSeries);
            // Adding Expense Series to dataset
//            dataset.addSeries(expenseSeries);

            // Creating XYSeriesRenderer to customize incomeSeries
            XYSeriesRenderer incomeRenderer = new XYSeriesRenderer();
            incomeRenderer.setColor(Color.YELLOW); //color of the graph set to cyan
            incomeRenderer.setFillPoints(true);
            incomeRenderer.setLineWidth(2);
            incomeRenderer.setDisplayChartValues(true);
            incomeRenderer.setDisplayChartValuesDistance(10); //setting chart value distance

            // Creating XYSeriesRenderer to customize expenseSeries
            XYSeriesRenderer expenseRenderer = new XYSeriesRenderer();
            expenseRenderer.setColor(Color.RED);
            expenseRenderer.setFillPoints(true);
            expenseRenderer.setLineWidth(2);
            expenseRenderer.setDisplayChartValues(true);

            // Creating a XYMultipleSeriesRenderer to customize the whole chart
            XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();
            multiRenderer.setOrientation(XYMultipleSeriesRenderer.Orientation.HORIZONTAL);
            multiRenderer.setXLabels(0);
            multiRenderer.setChartTitle("Income vs Expense Chart");
            multiRenderer.setXTitle("Year 2014");
            multiRenderer.setYTitle("Amount in Dollars");

            /***
             * Customizing graphs
             */

            // setting text size of the title
            multiRenderer.setChartTitleTextSize(28);
            //setting text size of the axis title
            multiRenderer.setAxisTitleTextSize(24);
            //setting text size of the graph lable
            multiRenderer.setLabelsTextSize(24);
            //setting zoom buttons visiblity
            multiRenderer.setZoomButtonsVisible(false);
            //setting pan enablity which uses graph to move on both axis
            multiRenderer.setPanEnabled(false, false);
            //setting click false on graph
            multiRenderer.setClickEnabled(false);
            //setting zoom to false on both axis
            multiRenderer.setZoomEnabled(false, false);
            //setting lines to display on y axis
            multiRenderer.setShowGridY(false);
            //setting lines to display on x axis
            multiRenderer.setShowGridX(false);
            //setting legend to fit the screen size
            multiRenderer.setFitLegend(false); // true
            //setting displaying line on grid
            multiRenderer.setShowGrid(false);
            //setting zoom to false
            multiRenderer.setZoomEnabled(false);
            //setting external zoom functions to false
            multiRenderer.setExternalZoomEnabled(false);
            //setting displaying lines on graph to be formatted(like using graphics)
            multiRenderer.setAntialiasing(false); // true
            //setting to in scroll to false
            multiRenderer.setInScroll(false);
            //setting to set legend height of the graph
            multiRenderer.setLegendHeight(30);
            //setting x axis label align
            multiRenderer.setXLabelsAlign(Align.CENTER);
            //setting y axis label to align
            multiRenderer.setYLabelsAlign(Align.LEFT);
            //setting text style
            multiRenderer.setTextTypeface("sans_serif", Typeface.NORMAL);
            //setting no of values to display in y axis
            multiRenderer.setYLabels(10);
            // setting y axis max value, Since i'm using static values inside the graph so i'm setting y max value to 4000.
            // if you use dynamic values then get the max y value and set here
            multiRenderer.setYAxisMax(400);
            multiRenderer.setYAxisMin(0);
            //setting used to move the graph on xaxiz to .5 to the right
            multiRenderer.setXAxisMin(-.5); //-.5

            // setting max values to be display in x axis
            multiRenderer.setXAxisMax(8);
            //setting bar size or space between two bars
            multiRenderer.setBarSpacing(0.5);
            //Setting background color of the graph to transparent
            multiRenderer.setBackgroundColor(Color.TRANSPARENT);
            //Setting margin color of the graph to transparent
            multiRenderer.setMarginsColor(getResources().getColor(R.color.transparent_background));
            multiRenderer.setApplyBackgroundColor(true);

            //setting the margin size for the graph in the order top, left, bottom, right
            multiRenderer.setMargins(new int[]{30, 30, 30, 30}); // 30,30,30,30

            for(int i=0; i< x.length;i++){
                multiRenderer.addXTextLabel(i, mMonth[i]);
            }

            // Adding incomeRenderer and expenseRenderer to multipleRenderer
            // Note: The order of adding dataseries to dataset and renderers to multipleRenderer
            // should be same
            multiRenderer.addSeriesRenderer(incomeRenderer);
//            multiRenderer.addSeriesRenderer(expenseRenderer);

            //this part is used to display graph on the xml
            LinearLayout chartContainer = (LinearLayout) findViewById(R.id.chart);
            //remove any views before u paint the chart
            chartContainer.removeAllViews();
            //drawing bar chart
            mChart = ChartFactory.getBarChartView(MainActivity.this, dataset, multiRenderer,Type.DEFAULT);
            //adding the view to the linearlayout
            chartContainer.addView(mChart);
        }
    private void openChart2(){
        int[] x = { 0, 1, 2, 3, 4, 5, 6, 7};
        int[] income = {400,250,270,300,280,350,370,380};
//            int[] expense = {220,270,290,280,260,300,30,340};

        // Creating an XYSeries for Income
        XYSeries incomeSeries = new XYSeries("Income");
        // Creating an XYSeries for Expense
//        XYSeries expenseSeries = new XYSeries("Expense");
        // Adding data to Income and Expense Series
        for(int i=0;i<x.length;i++){
            incomeSeries.add(i,income[i]);
//                expenseSeries.add(i,expense[i]);
        }

        // Creating a dataset to hold each series
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        // Adding Income Series to the dataset
        dataset.addSeries(incomeSeries);
        // Adding Expense Series to dataset
//            dataset.addSeries(expenseSeries);

        // Creating XYSeriesRenderer to customize incomeSeries
        XYSeriesRenderer incomeRenderer = new XYSeriesRenderer();
        incomeRenderer.setColor(Color.BLACK); //color of the graph set to cyan
        incomeRenderer.setFillPoints(true);
        incomeRenderer.setLineWidth(2);
        incomeRenderer.setDisplayChartValues(true);
        incomeRenderer.setDisplayChartValuesDistance(10); //setting chart value distance

        // Creating XYSeriesRenderer to customize expenseSeries
        XYSeriesRenderer expenseRenderer = new XYSeriesRenderer();
        expenseRenderer.setColor(Color.RED);
        expenseRenderer.setFillPoints(true);
        expenseRenderer.setLineWidth(2);
        expenseRenderer.setDisplayChartValues(true);

        // Creating a XYMultipleSeriesRenderer to customize the whole chart
        XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();
        multiRenderer.setOrientation(XYMultipleSeriesRenderer.Orientation.HORIZONTAL);
        multiRenderer.setXLabels(0);
        multiRenderer.setChartTitle("Income vs Expense Chart");
        multiRenderer.setXTitle("Year 2014");
        multiRenderer.setYTitle("Amount in Dollars");

        /***
         * Customizing graphs
         */

        // setting text size of the title
        multiRenderer.setChartTitleTextSize(28);
        //setting text size of the axis title
        multiRenderer.setAxisTitleTextSize(24);
        //setting text size of the graph lable
        multiRenderer.setLabelsTextSize(24);
        //setting zoom buttons visiblity
        multiRenderer.setZoomButtonsVisible(false);
        //setting pan enablity which uses graph to move on both axis
        multiRenderer.setPanEnabled(false, false);
        //setting click false on graph
        multiRenderer.setClickEnabled(false);
        //setting zoom to false on both axis
        multiRenderer.setZoomEnabled(false, false);
        //setting lines to display on y axis
        multiRenderer.setShowGridY(false);
        //setting lines to display on x axis
        multiRenderer.setShowGridX(false);
        //setting legend to fit the screen size
        multiRenderer.setFitLegend(false); // true
        //setting displaying line on grid
        multiRenderer.setShowGrid(false);
        //setting zoom to false
        multiRenderer.setZoomEnabled(false);
        //setting external zoom functions to false
        multiRenderer.setExternalZoomEnabled(false);
        //setting displaying lines on graph to be formatted(like using graphics)
        multiRenderer.setAntialiasing(false); // true
        //setting to in scroll to false
        multiRenderer.setInScroll(false);
        //setting to set legend height of the graph
        multiRenderer.setLegendHeight(30);
        //setting x axis label align
//        multiRenderer.setXLabelsAlign(Align.CENTER);
        //setting y axis label to align
//        multiRenderer.setYLabelsAlign(Align.LEFT);
        //setting text style
        multiRenderer.setTextTypeface("sans_serif", Typeface.NORMAL);
        //setting no of values to display in y axis
//        multiRenderer.setYLabels(10);
        // setting y axis max value, Since i'm using static values inside the graph so i'm setting y max value to 4000.
        // if you use dynamic values then get the max y value and set here
        multiRenderer.setYAxisMax(400);
        multiRenderer.setYAxisMin(0);
        //setting used to move the graph on xaxiz to .5 to the right
        multiRenderer.setXAxisMin(-.5); //-.5

        // setting max values to be display in x axis
        multiRenderer.setXAxisMax(8);
        //setting bar size or space between two bars
        multiRenderer.setBarSpacing(0.5);
        //Setting background color of the graph to transparent
        multiRenderer.setBackgroundColor(Color.TRANSPARENT);
        //Setting margin color of the graph to transparent
        multiRenderer.setMarginsColor(getResources().getColor(R.color.transparent_background));
        multiRenderer.setApplyBackgroundColor(true);

        //setting the margin size for the graph in the order top, left, bottom, right
        multiRenderer.setMargins(new int[]{30, 30, 30, 30}); // 30,30,30,30

        for(int i=0; i< x.length;i++){
            multiRenderer.addXTextLabel(i, mMonth[i]);
        }

        // Adding incomeRenderer and expenseRenderer to multipleRenderer
        // Note: The order of adding dataseries to dataset and renderers to multipleRenderer
        // should be same
        multiRenderer.addSeriesRenderer(incomeRenderer);
//            multiRenderer.addSeriesRenderer(expenseRenderer);

        //this part is used to display graph on the xml
        LinearLayout chartContainer = (LinearLayout) findViewById(R.id.chart2);
        //remove any views before u paint the chart
        chartContainer.removeAllViews();
        //drawing bar chart
        mChart = ChartFactory.getBarChartView(MainActivity.this, dataset, multiRenderer,Type.DEFAULT);
        //adding the view to the linearlayout
        chartContainer.addView(mChart);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

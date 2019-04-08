package com.nexp.pavel.networkexpertise.ui.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.nexp.pavel.networkexpertise.R;

import java.util.Calendar;
import java.util.Date;

public class StatFragment extends Fragment {

    private GraphView graph;

    public static StatFragment newInstance(){
        StatFragment fragment = new StatFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_stat, container, false );
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        graph = view.findViewById(R.id.graph);

        String [] labels = new String[] {"old", "middle", "new", "math", "physics", "some", "sd"};

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6),
                new DataPoint(6, 1)
        });
        series.setDrawBackground(true);
        series.setAnimated(true);
        series.setDrawDataPoints(true);
        series.setTitle("People");

        graph.addSeries(series);

        BarGraphSeries<DataPoint> series2 = new BarGraphSeries<>(new DataPoint[] {
                new DataPoint(0, -1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6),
                new DataPoint(5, 4)
        });
        //series2.setDataWidth(1d);
        series2.setSpacing(0);
        series2.setDataWidth(0.5d);
        series2.setAnimated(true);
        series2.setDrawValuesOnTop(true);
        series2.setTitle("Children");
        series2.setColor(Color.argb(255, 60, 200, 128));



        graph.getGridLabelRenderer().setNumHorizontalLabels(7);

        graph.getViewport().setMinX(0d);
        graph.getViewport().setMaxX(6d);
        graph.getViewport().setMaxY(8);
        graph.getViewport().setMinY(-1);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
        graph.addSeries(series2);
        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
        staticLabelsFormatter.setHorizontalLabels(labels);

        graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
//        graph.getGridLabelRenderer().setHorizontalAxisTitle("Lessons");
//        graph.getGridLabelRenderer().setHorizontalAxisTitleTextSize(56);





    }
}

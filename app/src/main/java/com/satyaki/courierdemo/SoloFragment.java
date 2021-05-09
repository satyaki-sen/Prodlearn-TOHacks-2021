package com.satyaki.courierdemo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;


public class SoloFragment extends Fragment {

    PieChart pieChart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_solo, container, false);
        pieChart=view.findViewById(R.id.piechart_Solo);
        formChart(10,9);
        return view;
    }

    public void formChart(int a,int b){

        ArrayList<PieEntry> listCompleted=new ArrayList<>();
        listCompleted.add(new PieEntry(a,"Completed"));
        listCompleted.add(new PieEntry(b,"Incomplete"));

        PieDataSet dataSet = new PieDataSet(listCompleted,"");

        PieData data = new PieData(dataSet);
        pieChart.setData(data);
        pieChart.setDrawEntryLabels(true);
        pieChart.setCenterText("Tasks: Incomplete V/S Complete");
        pieChart.setHoleRadius(60);
        dataSet.setColors(ColorTemplate.PASTEL_COLORS);
        pieChart.animateXY(5000, 5000);
        pieChart.invalidate();
    }
}
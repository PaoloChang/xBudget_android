package com.prj666_183a06.xbudget;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.FileUtils;

import java.util.ArrayList;

public class ReportActivity extends Fragment {

    private PieChart mPie;
    private float mTotalExpenses;

    private Typeface tf;

    public ReportActivity() {

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Report");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_report, container, false);

        // Pie Chart
        generatePieChart(v);

        return v;
    }

    private void generatePieChart(View v) {
        mPie = v.findViewById(R.id.pieChart1);
        mPie.getDescription().setEnabled(false);

        mPie.setCenterText(generateCenterText());
        mPie.setCenterTextSize(10f);

        mPie.setHoleRadius(45f);
        mPie.setTransparentCircleRadius(50f);

        Legend lgdPie = mPie.getLegend();
        lgdPie.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        lgdPie.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        lgdPie.setOrientation(Legend.LegendOrientation.VERTICAL);
        lgdPie.setDrawInside(false);

        mPie.setData(generatePieData());
    }

    private SpannableString generateCenterText() {
        SpannableString s = new SpannableString("Expensess\nJuly 2018");
        s.setSpan(new RelativeSizeSpan(2f), 0, 8, 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 8, s.length(), 0);

        return s;
    }

    protected PieData generatePieData() {
        int count = 7;

        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();

        float totalExpenses;
        float v_grocery = 400;
        float v_clothes = 120;
        float v_transportation = 150;
        float v_drink = 50;
        float v_eatout = 250;
        float v_financing = 130;
        float v_insurance = 350;

        entries.add(new PieEntry((float) v_grocery, "Grocery"));
        entries.add(new PieEntry((float) v_clothes, "Clothes"));
        entries.add(new PieEntry((float) v_transportation, "Transportation"));
        entries.add(new PieEntry((float) v_drink, "Drink"));
        entries.add(new PieEntry((float) v_eatout, "Eatout"));
        entries.add(new PieEntry((float) v_financing, "Financing"));
        entries.add(new PieEntry((float) v_insurance, "Insurance"));

        PieDataSet ds = new PieDataSet(entries, "Expenses July 2018");
        ds.setColors(ColorTemplate.COLORFUL_COLORS);
        ds.setSliceSpace(2f);
        ds.setValueTextColor(Color.WHITE);
        ds.setValueTextSize(12f);

        PieData d = new PieData(ds);
        d.setValueTypeface(tf);

        return d;
    }

}

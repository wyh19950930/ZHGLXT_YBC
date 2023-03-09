package com.jymj.zhglxt.widget;

import android.content.Context;
import android.graphics.Color;

import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.jymj.zhglxt.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class InitYLRadar {
    private static volatile InitYLRadar singleton;
    
    private InitYLRadar() {
    }
    
    public static InitYLRadar getInstance() {
        if (singleton == null) {
            synchronized (InitYLRadar.class) {
                if (singleton == null) {
                    singleton = new InitYLRadar();
                }
            }
        }
        return singleton;
    }
    
    public void creatRadar(Context mContext, RadarChart mChart, ArrayList<RadarEntry> radarEntries) {
        
        
        mChart.setBackgroundColor(Color.WHITE);
        
        mChart.getDescription().setEnabled(false);
        
        mChart.setWebLineWidth(1f);
        mChart.setWebColor(Color.LTGRAY);
        mChart.setWebLineWidthInner(1f);
        mChart.setWebColorInner(Color.LTGRAY);
        mChart.setWebAlpha(100);
        
        // create a custom MarkerView (extend MarkerView) and specify the layout
        // to use for it
        MarkerView mv = new RadarMarkerView(mContext, R.layout.radar_markerview);
        mv.setChartView(mChart); // For bounds control
        mChart.setMarker(mv); // Set the marker to the chart
        
        setData(mChart,radarEntries);
        
        mChart.animateXY(1400, 1400);
        
        XAxis xAxis = mChart.getXAxis();
        xAxis.setTextSize(9f);
        xAxis.setYOffset(0f);
        xAxis.setXOffset(0f);
        xAxis.setValueFormatter(new IndexAxisValueFormatter() {
            
            private String[] mActivities = new String[]{"用地面积", "人口数", "容积率", "代际关系"};
            
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return mActivities[(int) value % mActivities.length];
            }
        });
        xAxis.setTextColor(Color.BLACK);
        
        YAxis yAxis = mChart.getYAxis();
        yAxis.setLabelCount(4, false);
        yAxis.setTextSize(9f);
        yAxis.setAxisMinimum(0f);
        float yMax = 0f;
        for (int i = 0; i < radarEntries.size(); i++) {
            if (yMax < radarEntries.get(i).getValue()){
                yMax = radarEntries.get(i).getValue()+20;
            }
        }

        yAxis.setAxisMaximum(yMax);
        yAxis.setDrawLabels(false);

        
        Legend l = mChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(5f);
        l.setTextColor(Color.BLACK);
        
        setData(mChart,radarEntries);
    }
    public void setData(RadarChart mChart, ArrayList<RadarEntry> radarEntries) {
        
        RadarDataSet set1 = new RadarDataSet(radarEntries,"院落雷达图");
        set1.setColor(Color.rgb(103, 110, 129));
        set1.setFillColor(Color.rgb(103, 110, 129));
        set1.setDrawFilled(true);
        set1.setFillAlpha(180);
        set1.setLineWidth(2f);
        set1.setDrawHighlightCircleEnabled(true);
        set1.setDrawHighlightIndicators(false);

        set1.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return "";
            }
        });
        
        
        ArrayList<IRadarDataSet> sets = new ArrayList<IRadarDataSet>();
        sets.add(set1);
        
        RadarData data = new RadarData(sets);
        data.setValueTextSize(8f);
        data.setDrawValues(true);
        data.setValueTextColor(Color.BLACK);
        data.setValueFormatter(new IndexAxisValueFormatter() {
    
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return new DecimalFormat("#0.00").format(value/100d)+"";
            }
        });
        mChart.setData(data);
        mChart.invalidate();
    }
}

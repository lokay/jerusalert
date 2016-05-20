package com.example.admin.jerusalert;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.InputStream;
import org.xmlpull.v1.XmlPullParserFactory;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;
import java.util.*;
import java.io.*;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.Gravity;
import android.util.TypedValue;
import android.graphics.Typeface;
import android.widget.ImageView;
import android.graphics.Color;
public class MyReqStatus extends AppCompatActivity {
    public static String EXTRA_NAME;
    private XmlPullParserFactory xmlFactoryObject;
    public volatile boolean parsingComplete = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        createFakeDB();

        SharedPreferences sp = getSharedPreferences("fakeDB", Context.MODE_PRIVATE);
        String myNum = EXTRA_NAME;
//        SharedPreferences.Editor e = sp.edit();
//        e.putString("1", "a");
//        e.putString("2", "b");
//        e.putString("3", "c");
//        e.commit();


        //search id in db
        //fake implementation

        String subCategory;
        String time;
        String date;
        String status;

        ArrayList<ArrayList<String>> myAlerts = new ArrayList<ArrayList<String>>();
        int i = 1;
        String num = Integer.toString(i);
        String category = sp.getString("1-category" + num, null);
        while (category != null) {
            ArrayList<String> line = new ArrayList<String>();
            num = Integer.toString(i);
            line.add(category);
            subCategory = sp.getString("1-subCategory" + num, null);
            line.add(subCategory);
            date = sp.getString("1-date" + num, null);
            line.add(date);
            time = sp.getString("1-time" + num, null);
            line.add(time);
            status = sp.getString("1-status" + num, null);
            line.add(status);

            myAlerts.add(line);
            i++;
            num = Integer.toString(i);
            category = sp.getString("1-category" + num, null);

        }

        //show results
        presentData(myAlerts);

    }

    protected void createFakeDB() {
        SharedPreferences sp = getSharedPreferences("fakeDB", Context.MODE_PRIVATE);
        SharedPreferences.Editor e = sp.edit();
        e.putString("1-category1", "מים");
        e.putString("1-category2", "חשמל");
        e.putString("1-category3", "אש");
        e.commit();

        e.putString("1-subCategory1", "פיצוץ צינור");
        e.putString("1-subCategory2", "הפסקת חשמל");
        e.putString("1-subCategory3", "שריפה");
        e.commit();

        e.putString("1-date1", "19.5.16");
        e.putString("1-date2", "18.5.16");
        e.putString("1-date3", "19.5.16");
        e.commit();

        e.putString("1-time1", "12:49");
        e.putString("1-time2", "9:38");
        e.putString("1-time3", "10:32");
        e.commit();

        e.putString("1-status1", "not read");
        e.putString("1-status2", "done");
        e.putString("1-status3", "in progress");

        e.commit();

    }


    protected void presentData(ArrayList<ArrayList<String>> myAlerts) {

        TableLayout table = new TableLayout(this);

        table.setStretchAllColumns(true);
        table.setShrinkAllColumns(true);

        TableRow rowTitle = new TableRow(this);
        rowTitle.setGravity(Gravity.CENTER_HORIZONTAL);


        ArrayList<TableRow> rows = new ArrayList<TableRow>();

        TableRow labels = new TableRow(this);
        TextView empty = new TextView(this);

        // title column/row
        TextView title = new TextView(this);
        title.setText("הדיווחים שלי");

        title.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
        title.setGravity(Gravity.CENTER);
        title.setTypeface(Typeface.SERIF, Typeface.BOLD);

        TableRow.LayoutParams params = new TableRow.LayoutParams();
        params.span = 5;

        rowTitle.addView(title, params);

        //category column
        TextView category = new TextView(this);
        category.setText("קטגוריה");
        category.setTypeface(Typeface.SERIF, Typeface.BOLD);

        // sub-category column
        TextView subCategory = new TextView(this);
        subCategory.setText("תת קטגוריה");
        subCategory.setTypeface(Typeface.SERIF, Typeface.BOLD);

        // date column
        TextView date = new TextView(this);
        date.setText("תאריך");
        date.setTypeface(Typeface.SERIF, Typeface.BOLD);

        // time column
        TextView time = new TextView(this);
        time.setText("שעה");
        time.setTypeface(Typeface.SERIF, Typeface.BOLD);

        // status column
        TextView status = new TextView(this);
        status.setText("סטטוס");
        status.setTypeface(Typeface.SERIF, Typeface.BOLD);

        labels.addView(category);
        labels.addView(subCategory);
        labels.addView(date);
        labels.addView(time);
        labels.addView(status);
        table.addView(labels);

        TableRow row;
        for(int i=0; i < myAlerts.size(); i++){
            row = new TableRow(this);

            category = new TextView(this);
            category.setText(myAlerts.get(i).get(0));
            category.setGravity(Gravity.CENTER_HORIZONTAL);
            row.addView(category);

            subCategory = new TextView(this);
            subCategory.setText(myAlerts.get(i).get(1));
            subCategory.setGravity(Gravity.CENTER_HORIZONTAL);
            row.addView(subCategory);

            date = new TextView(this);
            date.setText(myAlerts.get(i).get(2));
            date.setGravity(Gravity.CENTER_HORIZONTAL);
            row.addView(date);

            time = new TextView(this);
            time.setText(myAlerts.get(i).get(3));
            time.setGravity(Gravity.CENTER_HORIZONTAL);
            row.addView(time);

            status = new TextView(this);
            status.setGravity(Gravity.CENTER_HORIZONTAL);
            if (myAlerts.get(i).get(4).equals("in progress")){
                status.setBackgroundColor(Color.YELLOW);
                status.setText("בטיפול");
            } else if (myAlerts.get(i).get(4).equals("not read")){
                status.setBackgroundColor(Color.RED);
                status.setText("לא התקבל");
            } else {
                status.setBackgroundColor(Color.GREEN);
                status.setText("טופל");
            }
            status.invalidate();

            row.addView(status);

            table.addView(row);
        }

        setContentView(table);

    }

}
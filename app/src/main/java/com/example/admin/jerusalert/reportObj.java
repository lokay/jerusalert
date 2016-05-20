package com.example.admin.jerusalert;
import java.util.Date;


public class reportObj {
    public int Id;
    public String Category;
    public String Subcategory;
    public double Location_x;
    public double Location_y;
    public String Report_time;
    public String Report_text;
    public String Phone;
    public int Counter;

    public reportObj() {
        Id = 0;
        Category = "";
        Subcategory = "";
        Location_x = 0;
        Location_y = 0;
        Report_time = "";
        Report_text = "";
        Phone = "";
        Counter = 0;
    }

}
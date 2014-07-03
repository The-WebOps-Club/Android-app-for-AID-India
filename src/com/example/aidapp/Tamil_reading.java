package com.example.aidapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Tamil_reading extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tamil_reading);
        Button s3 = (Button)findViewById(R.id.Next2);
        s3.setOnClickListener(new View.OnClickListener() {
        		@Override
    			public void onClick(View arg0) {
    				// TODO Auto-generated method stub
    				Intent i=new Intent(Tamil_reading.this,English.class);
    				startActivity(i);
    			}
    
        });
        Button s4=(Button)findViewById(R.id.add);
        s4.setOnClickListener(new View.OnClickListener()
        {
			
			@Override
			public void onClick(View arg0) 
			{
		   	 TableLayout mytable = (TableLayout)findViewById(R.id.tamilreading);
				TableRow tr = new TableRow(Tamil_reading.this);
		        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
		        tr.setLayoutParams(lp);

                TextView item1 = new TextView(Tamil_reading.this);
                CheckBox item2 = new CheckBox(Tamil_reading.this);
                CheckBox item3 = new CheckBox(Tamil_reading.this);
                CheckBox item4 = new CheckBox(Tamil_reading.this);
                CheckBox item5 = new CheckBox(Tamil_reading.this);
                CheckBox item6 = new CheckBox(Tamil_reading.this);
                
                tr.addView(item1);
                tr.addView(item2);
                tr.addView(item3);
                tr.addView(item4);
                tr.addView(item5);
                tr.addView(item6);

                mytable.addView(tr, new TableLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
				
			}
		});
        
    }
}

package com.example.aidapp;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button s = (Button)findViewById(R.id.teach);
        s.setOnClickListener(new View.OnClickListener() {
        		@Override
    			public void onClick(View arg0) {
    				// TODO Auto-generated method stub
    				Intent i=new Intent(MainActivity.this,Teacher_info.class);
    				startActivity(i);
    			}
    			
        	
    		}); 
        Button s1 = (Button)findViewById(R.id.Next);
        s1.setOnClickListener(new View.OnClickListener() {
        		@Override
    			public void onClick(View arg0) {
    				// TODO Auto-generated method stub
    				Intent i=new Intent(MainActivity.this,Tamil_reading.class);
    				startActivity(i);
    			}
    			
        	
    		}); 
        
        }
    }
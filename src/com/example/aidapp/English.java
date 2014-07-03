
package com.example.aidapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

public class English extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.english);
        Button s2 = (Button)findViewById(R.id.Next3);
        s2.setOnClickListener(new View.OnClickListener() {
        		@Override
    			public void onClick(View arg0) {
    				// TODO Auto-generated method stub
    				Intent i=new Intent(English.this,Math.class);
    				startActivity(i);
    			}
    
        });
        
    }
}

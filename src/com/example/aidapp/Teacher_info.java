package com.example.aidapp;
import android.content.ContentUris;
import android.content.ContentValues;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class Teacher_info extends ActionBarActivity 
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
      { 
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_info);
        Button b = (Button)findViewById(R.id.Save);
        b.setOnClickListener(new View.OnClickListener() {
        	@Override
        	public void onClick(View view) {
        	int col=0;
        	ContentValues values = new ContentValues(); 
        	EditText mteach=(EditText)findViewById(R.id.teach_id);
        	EditText mName=(EditText)findViewById(R.id.name);
        	EditText mCenter=(EditText)findViewById(R.id.center);
        	values.put(Teach.COLUMN_no,col);
			values.put(Teach.COLUMN_ID,mteach.getText().toString());
        	values.put(Teach.COLUMN_Name,mName.getText().toString());
        	values.put(Teach.COLUMN_Center, mCenter.getText().toString());

        	if (col == 0) { 
        	Uri itemUri =Teacher_info.this.getContentResolver().insert(Teach.CONTENT_URI, values); 
        	col =(int)ContentUris.parseId(itemUri); 
        	} 
        	else {
        	int count =Teacher_info.this.getContentResolver().update(
        	ContentUris.withAppendedId(
        	Teach.CONTENT_URI, col),
        	values, null, null); 
        	if (count != 1) 
        	throw new IllegalStateException("Unable to update "
        	+ col);
        	}
        	Toast.makeText(Teacher_info.this,
        	getString(R.string.abc_action_bar_up_description),
        	Toast.LENGTH_SHORT).show(); 
        	Teacher_info.this.finish (); 
        	}
        	});
      }
}
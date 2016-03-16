package com.example.skafle.androidassessment1solutions;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class OutputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);

        TextView output = (TextView) findViewById(R.id.output_txt);

        Intent intent = getIntent();
        double out = intent.getDoubleExtra(MainActivity.RESULTKEY, 0);

        output.setText(out + "");
    }
}

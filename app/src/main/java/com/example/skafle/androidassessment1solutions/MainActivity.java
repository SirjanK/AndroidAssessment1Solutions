package com.example.skafle.androidassessment1solutions;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String RESULTKEY = "result_key"; // Will need this for the intent
    // Declaring my Views
    private EditText firstNum;
    private EditText secondNum;
    private Button computeBtn;
    // Declaring my SharedPreference
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializing my views
        firstNum = (EditText) findViewById(R.id.first_num);
        secondNum = (EditText) findViewById(R.id.second_num);
        computeBtn = (Button) findViewById(R.id.compute_btn);
        // Assigning my preferences instance a value using PreferenceManager
        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        computeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                computeSum();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        // Go to settings
        if (id == R.id.action_settings) {
            Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void computeSum() {
        Double num1 = retrieve(firstNum.getText().toString()); // Get text from editText
        Double num2 = retrieve(secondNum.getText().toString());

        if (num1 == null || num2 == null) {
            String error = "Please input a number!";
            Toast.makeText(MainActivity.this, error, Toast.LENGTH_SHORT).show();
        } else {
            Double result = operate(num1, num2);
            if (result == null) {
                Toast.makeText(MainActivity.this, "Some whacky error", Toast.LENGTH_SHORT).show();
            } else {
                // Pass result to OutputActivity
                Intent intent = new Intent(getApplicationContext(), OutputActivity.class);
                intent.putExtra(RESULTKEY, result);
                startActivity(intent);
            }
        }
    }

    private Double operate(Double num1, Double num2) {
        String operator = preferences.getString("operator_key", "error"); // Grabbing proper operator from SharedPreference

        if (operator.equals("add")) {
            return num1 + num2;
        } else if (operator.equals("mult")) {
            return num1 * num2;
        } else {
            return null;
        }
    }

    private Double retrieve(String str) {
        try {
            return Double.parseDouble(str);
        } catch (Exception e) {
            return null;
        }
    }
}

package com.example.converter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public String num1Type, num2Type;
    public static String[] numberTypes = {"BIN", "OCT", "DEC", "HEX"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, numberTypes);

        Spinner numType1Spinner = findViewById(R.id.numType1);
        Spinner numType2Spinner = findViewById(R.id.numType2);
        numType1Spinner.setAdapter(adapter);
        numType2Spinner.setAdapter(adapter);
        numType1Spinner.setOnItemSelectedListener(this);
        numType2Spinner.setOnItemSelectedListener(this);

        Button convertButton = findViewById(R.id.convertButton);
        convertButton.setOnClickListener(v -> {
            EditText input = findViewById(R.id.value);
            String inputValue = input.getText().toString();
            String result = convert(num1Type, num2Type, inputValue);
            TextView resultView = findViewById(R.id.Result);
            resultView.setText(result);
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
        if(parent.getId() == R.id.numType1){
            num1Type = parent.getSelectedItem().toString();
        }
        if(parent.getId() == R.id.numType2){
            num2Type = parent.getSelectedItem().toString();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
    }

    String convert(String type1, String type2, String value){
        String result = null;
        int decNum = 0;
        if(value.isEmpty()){
            return "Input Empty.";
        }

        if(type1.equals("BIN"))
        {
            decNum = Integer.parseInt(value, 2);
        }
        if(type1.equals("OCT"))
        {
            decNum = Integer.parseInt(value, 8);
        }
        if(type1.equals("DEC"))
        {
            decNum = Integer.parseInt(value);
        }
        if(type1.equals("HEX"))
        {
            decNum = Integer.parseInt(value, 16);
        }

        if(type2.equals("BIN"))
        {
            result = Integer.toBinaryString(decNum);
        }
        if(type2.equals("OCT"))
        {
            result = Integer.toOctalString(decNum);
        }
        if(type2.equals("DEC"))
        {
            result = Integer.toString(decNum);
        }
        if(type2.equals("HEX"))
        {
            result = Integer.toHexString(decNum);
        }

        return result;
    }

}


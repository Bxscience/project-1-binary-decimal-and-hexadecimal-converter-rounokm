package com.example.rounokm.converter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Integer.parseInt;
import static java.lang.Integer.toBinaryString;
import static java.lang.Integer.toHexString;

public class MainActivity extends AppCompatActivity {
    private EditText dec;
    private EditText bin;
    private EditText hex;
    private Button btn;
    private Button btnClear;
    private String input;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dec = (EditText) findViewById(R.id.decimal);
        bin = (EditText) findViewById(R.id.binary);
        hex = (EditText) findViewById(R.id.hexadecimal);
        btn = (Button) findViewById(R.id.convert);
        btnClear = (Button) findViewById(R.id.clear);


        dec.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    bin.setText("");
                    hex.setText("");
                    input="dec";
                }
            }
        });

        bin.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    dec.setText("");
                    hex.setText("");
                    input="bin";
                }
            }
        });

        hex.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    bin.setText("");
                    dec.setText("");
                    input="hex";
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                dec.setText("");
                bin.setText("");
                hex.setText("");
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                String d,b,h;
                d=""+dec.getText();
                b=""+bin.getText();
                h=""+hex.getText();
                int k;
                if (input=="dec") {
                    if (d.length() == 0) {
                        Toast toast = Toast.makeText(MainActivity.this, "Don't leave the box empty!", Toast.LENGTH_LONG);
                        toast.show();
                    } else {
                        k = parseInt(d, 10);
                        bin.setText(toBinaryString(k));
                        hex.setText(toHexString(k));
                    }
                }

                if (input=="bin" ) {//more ifs
                    if(b.length()==0){
                        Toast toast = Toast.makeText(MainActivity.this, "Don't leave the box empty!", Toast.LENGTH_LONG);
                        toast.show();
                    }
                    else if(binaryCheck()==false){
                        Toast toast = Toast.makeText(MainActivity.this, "Enter A Binary Value", Toast.LENGTH_LONG);
                        toast.show();
                    }
                    else{
                        k=parseInt(""+bin.getText(),2);
                        dec.setText(""+k);
                        hex.setText(""+toHexString(k));
                    }
                }

                if (input=="hex" ) {//parse for any other letter cannot contain z
                    if(h.length()==0){
                        Toast toast = Toast.makeText(MainActivity.this, "Don't leave the box empty!", Toast.LENGTH_LONG);
                        toast.show();
                    }
                    if(!hexaCheck()){
                        Toast toast = Toast.makeText(MainActivity.this, "Enter A Hexadecimal Value", Toast.LENGTH_LONG);
                        toast.show();
                    }
                    else{
                        k=parseInt(""+hex.getText(),16);
                        dec.setText(""+k);
                        bin.setText(""+toBinaryString(k));
                    }
                }
            }
        });

    }

    public boolean binaryCheck(){
        boolean b = false;
        int one = 0;
        int zero = 0;
        int num = Integer.parseInt(bin.getText().toString());

        while (num > 0) {
            if ((num % 10 == 0) || (num % 10 == 1))
                one++;
            zero++;
            num = num / 10;
        }
        if (one == zero) {
            b = true;
        } else
            b = false;
        return b;
    }

    public boolean hexaCheck(){
        String hexStr= ""+hex.getText();

        char[] hexDig = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f', 'A', 'B', 'C', 'D', 'E', 'F' };

        for (char symbol : hexStr.toCharArray()) {
            boolean isHex = false;
            for (char hexD : hexDig) {
                if (symbol == hexD) {
                    isHex = true;
                    break;
                }
            }
            if(!isHex)
                return false;
        }
        return true;
    }


}

package com.example.webview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity2 extends AppCompatActivity {
    Button startCallBtn, joinCallBtn, customUrlBtn;
    EditText editText;
    private String startCallUrl = "http://mlbrains.com/call/?action=start_call&uid=xAta6uMm9kdwxcC2rG7K5aTb4Jf1";
    private String joinCallUrl = "http://mlbrains.com/call/?action=join_call&uid=Ajj7UlNbNEhEqOEXiyYGoHIyZoJ3";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        startCallBtn = findViewById(R.id.start_call_button);
        joinCallBtn = findViewById(R.id.join_call_button);
        customUrlBtn = findViewById(R.id.use_custom_text_button);
        editText = findViewById(R.id.url_et);

        startCallBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("url", startCallUrl);
            startActivity(intent);
        });

        joinCallBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("url", joinCallUrl);
            startActivity(intent);
        });

        customUrlBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("url", editText.getText().toString()+"");
            startActivity(intent);
        });
    }
}
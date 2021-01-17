package com.example.myresturent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void menuOnClick(View view)
    {
        Intent intent = new Intent(this,menu.class);
        startActivity(intent);
    }
    public void exitOnClick(View view)
    {
        finish();
    }

    public void location(View view) {
        Intent intent = new Intent(this,FindLocation.class);
        startActivity(intent);
    }
}
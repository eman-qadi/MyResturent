package com.example.myresturent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    //a list to store all the products
    List<category> categoryList;
    //the recyclerview
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        recyclerView = findViewById(R.id.category);
        categoryList = new ArrayList<>();


        LoadData();
    }
    public void LoadData()
    {
        String cat = getIntent().getStringExtra("cat");
        String url = "https://www.themealdb.com/api/json/v1/1/filter.php?c="+cat;


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url,
                        null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        String[] days;
                        try {

                            JSONArray array = response.getJSONArray("meals");
                            days = new String[array.length()];
                            for(int i = 0; i<array.length(); i++){
                                JSONObject obj = array.getJSONObject(i);

                                    categoryList.add(new category(
                                            obj.getString("strMeal"),
                                            obj.getString("strMealThumb")

                                    ));

                            }
                            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity2.this));
                            Adapter adapter = new Adapter(MainActivity2.this, categoryList);
                            recyclerView.setAdapter(adapter);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }
}
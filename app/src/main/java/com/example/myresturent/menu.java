package com.example.myresturent;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.List;

public class menu extends AppCompatActivity {

    private   String URL_PRODUCTS =  "http://192.168.1.112:84/rest/menus.php?";

    //a list to store all the products
    List<category> categoryList;
    //the recyclerview
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        recyclerView = findViewById(R.id.menus);
        categoryList = new ArrayList<>();


        LoadData();

    }


    public void LoadData()
    {
        String url = "https://www.themealdb.com/api/json/v1/1/categories.php?";


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url,
                  null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        String[] days;
                        try {

                            JSONArray array = response.getJSONArray("categories");
                            days = new String[array.length()];
                            for(int i = 0; i<array.length(); i++){
                                JSONObject obj = array.getJSONObject(i);
                               if(! obj.getString("strCategory").equals("Pork")) {
                                   categoryList.add(new category(
                                           obj.getString("strCategory"),
                                           obj.getString("strCategoryThumb")

                                   ));
                               }
                            }
                            recyclerView.setLayoutManager(new LinearLayoutManager(menu.this));
                            category_adapter adapter = new category_adapter(menu.this, categoryList);
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
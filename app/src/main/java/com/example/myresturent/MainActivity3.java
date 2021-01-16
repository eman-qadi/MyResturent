package com.example.myresturent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.URL;

public class MainActivity3 extends AppCompatActivity {

    private TextView titletxt , categorytxt,hometxt,urltxt;
    private EditText ing1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        titletxt=findViewById(R.id.titletxt);
        categorytxt=findViewById(R.id.categorytxt);
        hometxt=findViewById(R.id.hometxt);
        ing1=findViewById(R.id.ing1);
        urltxt=findViewById(R.id.urltxt);
        LoadData();
    }
    public void backOnClick(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void LoadData()
    {
        String title = getIntent().getStringExtra("cat");
        String url = "https://www.themealdb.com/api/json/v1/1/search.php?s="+title;


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url,
                        null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        String[] ing= new String[20];
                        String[] t = new String[3];
                        String[] mea = new String[20];
                        String link="";
                        String[] jj;
                        URL url1;
                        try {

                            JSONArray array = response.getJSONArray("meals");
                            jj = new String[array.length()];
                            for(int i = 0; i<array.length(); i++){
                                JSONObject obj = array.getJSONObject(i);
                                t[0]=obj.getString("strMeal");
                                t[1]=obj.getString("strCategory");
                                t[2]=obj.getString("strArea");
                                link= obj.getString("strYoutube");


                                ing[0]=obj.getString("strIngredient1");
                                ing[1]=obj.getString("strIngredient2");
                                ing[2]=obj.getString("strIngredient3");
                                ing[3]=obj.getString("strIngredient4");
                                ing[4]=obj.getString("strIngredient5");

                                ing[5]=obj.getString("strIngredient6");
                                ing[6]=obj.getString("strIngredient7");
                                ing[7]=obj.getString("strIngredient8");
                                ing[8]=obj.getString("strIngredient9");
                                ing[9]=obj.getString("strIngredient10");

                                ing[10]=obj.getString("strIngredient11");
                                ing[11]=obj.getString("strIngredient12");
                                ing[12]=obj.getString("strIngredient13");
                                ing[13]=obj.getString("strIngredient14");
                                ing[14]=obj.getString("strIngredient15");

                                ing[15]=obj.getString("strIngredient16");
                                ing[16]=obj.getString("strIngredient17");
                                ing[17]=obj.getString("strIngredient18");
                                ing[18]=obj.getString("strIngredient19");
                                ing[19]=obj.getString("strIngredient20");


                                mea[0]=obj.getString("strMeasure1");
                                mea[1]=obj.getString("strMeasure2");
                                mea[2]=obj.getString("strMeasure3");
                                mea[3]=obj.getString("strMeasure4");
                                mea[4]=obj.getString("strMeasure5");

                                mea[5]=obj.getString("strMeasure6");
                                mea[6]=obj.getString("strMeasure7");
                                mea[7]=obj.getString("strMeasure8");
                                mea[8]=obj.getString("strMeasure9");
                                mea[9]=obj.getString("strMeasure10");

                                mea[10]=obj.getString("strMeasure11");
                                mea[11]=obj.getString("strMeasure12");
                                mea[12]=obj.getString("strMeasure13");
                                mea[13]=obj.getString("strMeasure14");
                                mea[14]=obj.getString("strMeasure15");

                                mea[15]=obj.getString("strMeasure16");
                                mea[16]=obj.getString("strMeasure17");
                                mea[17]=obj.getString("strMeasure18");
                                mea[18]=obj.getString("strMeasure19");
                                mea[19]=obj.getString("strMeasure20");

                            }
                            titletxt.setText( t[0]);
                            categorytxt.setText( t[1]);
                            hometxt.setText( t[2]);
                            urltxt.setClickable(true);
                            urltxt.setMovementMethod(LinkMovementMethod.getInstance());
                            String text = "<a href="+link+ ">"+ link +"</a>";
                            urltxt.setText(Html.fromHtml(text));
                            String str = "";
                            for (int j =0 ; j< 20; j++)
                            {
                                if(!ing[j].equals("") && !ing[j].equals(null) && !ing[j].equals(" ")&&!mea[j].equals("") && !mea[j].equals(null) && !mea[j].equals(" ")) {
                                    str += ing[j] + ":" + mea[j] + "\n";
                                }
                            }
                            ing1.setText(str);


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
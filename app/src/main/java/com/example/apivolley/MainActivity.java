package com.example.apivolley;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String api="https://jsonplaceholder.typicode.com/todos";
    ArrayList<userModel> list= new ArrayList<>();
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recy);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, api,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                      //  Log.d("api","onResponse"+response.toString());
                        try {
                            JSONArray array= new JSONArray(response);
                            for(int i=0;i< array.length();i++)
                            {
                                JSONObject object = array.getJSONObject(i);
                                userModel userModel = new userModel(
                                        object.getInt("userId"),
                                        object.getInt("id"),
                                        object.getString("title"),
                                        object.getBoolean("completed")
                                );
                                list.add(userModel);
                                Log.d("user","All list"+ list.size());
                            }

                            recyclerView.setAdapter(new UserAdapter(MainActivity.this,list));
                        } catch (JSONException e) {
                            throw new RuntimeException(e);


                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

             Log.d("api","onErrorResponse"+error.getLocalizedMessage());
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
package com.example.bks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private static final String TAG ="tag";
    RecyclerView recyclerView;
    ProgressBar progressBar;
    private List<CropModel> mList;
    private cropAdapter cropAdapter;
    private RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recycleView);
        progressBar=findViewById(R.id.progressBar);
        cropAdapter= new cropAdapter(this, mList );
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        requestQueue=volleySingleton.getmInstance(this).getRequestQueue();
        mList= new ArrayList<>();
        fetchData();


    }

    private void fetchData() {
        progressBar.setVisibility(View.VISIBLE);
        String url= "http://bharatkrushiseva.in/api_user/getCropType?language=en";
        JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                progressBar.setVisibility(View.GONE);
                try {
                    JSONArray jsonArray= response.getJSONArray("crop");
                    for(int i=0; i<jsonArray.length();i++){
                        JSONObject jsonObject= jsonArray.getJSONObject(i);
                        String name= jsonObject.getString("crop_name");
                         String image= jsonObject.getString("crop_image");
                         CropModel post = new CropModel(name,image);
                         mList.add(post);

                    }
                    cropAdapter adapter = new cropAdapter(MainActivity.this,mList);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);


    }

}
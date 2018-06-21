package android.c4q.com.finalretake;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.Toast;

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
import java.util.Map;

public class MainActivity extends AppCompatActivity {

     RecyclerView recyclerView;
    RecyclerView.Adapter mExampleCustomRecyclerAdapter;
    RecyclerView.LayoutManager layoutManager;

    ArrayList<Item> mItemList;

     RequestQueue mRequestQueue;

     EditText editText;
     //String result = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.inputCards);
        if (Integer.parseInt(editText.getText().toString())<=0){
            Toast.makeText(MainActivity.this, "Cant be less than 0", Toast.LENGTH_SHORT).show();

        }
        if (Integer.parseInt(editText.getText().toString())>=47){
            Toast.makeText(MainActivity.this, "There are only remaing cards", Toast.LENGTH_SHORT).show();

        }

        mRequestQueue = Volley.newRequestQueue(this);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

         layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);



        mItemList = new ArrayList<>();

        mExampleCustomRecyclerAdapter = new CustomRecyclerAdapter(MainActivity.this, mItemList);
        recyclerView.setAdapter(mExampleCustomRecyclerAdapter);


        sendRequest();
    }


    private void sendRequest() {
        String url = "https://deckofcardsapi.com/api/deck/new/shuffle/?deck_count=1";
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        for (int i = 0; i < response.length(); i++) {
                            Item item = new Item();


                            try {
                                JSONArray jsonArray  = new JSONArray( new JSONObject((Map) response.getJSONObject(i)));

                                //JSONObject jsonObject = response.getJSONObject(i);
                                item.setCardValue(jsonArray.getString(Integer.parseInt("1")));
                                item.setmImageUrl(jsonArray.getString(Integer.parseInt("2")));
                            } catch (JSONException e1) {
                                e1.printStackTrace();
                            }
                            mItemList.add(item);

                            /*for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hit = jsonArray.getJSONObject(i);

                                String imageUrl = hit.getString("image");
                                String cardValue = hit.getString("value");
                                //int likeCount = hit.getInt("likes");

                                mItemList.add(new Item(imageUrl, cardValue));*/
                            }

                            mExampleCustomRecyclerAdapter = new CustomRecyclerAdapter(MainActivity.this,mItemList);
                        recyclerView.setAdapter(mExampleCustomRecyclerAdapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(jsonArrayRequest);
    }
}

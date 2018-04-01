package rana.com.adjustablelayout;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "PhotoGalleryFragment";

    ArrayList<HashMap<String,String>> list_hm = new ArrayList<HashMap<String,String>>();
    String items = "";
    String message, result;
    TextView RecipeName, RecipeItems;
    List<String> stringList = new ArrayList<>();
    private EditText etEnterName;
    private AdjustableLayout adjustableLayout; //Custom class extending Linear layout

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        RecipeName = (TextView) findViewById(R.id.name);
//        RecipeItems = (TextView) findViewById(R.id.salary);
        initializeComponents();
    }

    public void sendMessage(View view) {
//        EditText editText = (EditText) findViewById(R.id.editText);
        items = items.substring(0,items.length()-1);
        Log.i(TAG, "all items: " + items);
        message = "https://recipe-recommend-jaykun.c9users.io/api/recipes/?i=" + items;
        new FetchItemsTask().execute();
    }

    private class FetchItemsTask extends AsyncTask<Void,Void,String> {
        @Override
        protected String doInBackground(Void... params) {
            try {
//                result = new HttpGetRequest().getUrlString("https://recipe-recommend-jaykun.c9users.io/api/");
                result = new HttpGetRequest().getUrlString(message);

                Log.i(TAG, "Fetched contents of URL: " + result);
            } catch (IOException ioe) {
                Log.e(TAG, "Failed to fetch URL: ", ioe);
            }
//            return null;
            return result;
        }
        //        @Override
        protected void onPostExecute(String result) {
            try {
                Log.i(TAG,"successssssss");
                JSONObject obj = new JSONObject(result);
//                name = obj.getString("message");
//                JSONArray array = obj.getJSONArray("result");
//                RecipeName.setText("Name: "+name);
//                RecipeItems.setText("Items: "+array.get(1));
                JSONArray array = obj.getJSONArray("results");
                for (int i = 0; i<array.length(); i++)
                {
                    JSONObject obj2 = array.getJSONObject(i);
                    JSONArray array1 = obj2.getJSONArray("matches");
                    JSONArray array2 = obj2.getJSONArray("not_matches");
//                    JSONArray array3 = new JSONArray();
//                    for(int j = 0; j < array1.length(); j++)
//                        array3.put(array1.get(j));
//                    for(int j = 0; j < array2.length(); j++)
//                        array3.put(array2.get(j));
                    String ingredients = "";
                    for(int j = 0; j < array1.length(); j++)
                    {
                        ingredients += array1.get(j);
                        ingredients += ",";
                    }
                    for(int j = 0; j < array2.length(); j++)
                    {
                        ingredients += array2.get(j);
                        ingredients += ",";
                    }
                    ingredients = ingredients.substring(0,ingredients.length()-1);
                    HashMap<String, String> hm = new HashMap<String, String>();
                    hm.put("ingredients",ingredients);
                    JSONObject obj3 = obj2.getJSONObject("info");
                    String name = obj3.getString("name");
                    String instructions = obj3.getString("instructions");
                    hm.put("name",name);
                    hm.put("instructions",instructions);
                    list_hm.add(hm);
                }
//                for(int i = 0; i < list_hm.size(); i++) {
//                    for(Map.Entry<String,String> entry : list_hm.get(i).entrySet()) {
//                        Log.i(TAG, entry.getKey() + ": " + entry.getValue());
//                    }
//                }
                Intent intent = new Intent(MainActivity.this,RecipeActivity.class);
                intent.putExtra("ListExtra",list_hm);
                startActivity(intent);



            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void initializeComponents() {
        setStringList();
        etEnterName = (EditText)findViewById(R.id.etEnterName);
        adjustableLayout = (AdjustableLayout)findViewById(R.id.container); //Custom layout file
//        addViewInALoop();
    }

    private void setStringList() {
        String[] strings = items.split(" ");
        stringList = new ArrayList<String>(Arrays.asList(strings));
    }

    public void onClickAddNewView(View view){
        /**
         * User only one function at a time to view different demo.
         */
        addChipsView();
        etEnterName.setText(null);
//        addRandomView();
//        addButtons();
    }

    public void onClickRemoveView(View view){
        adjustableLayout.removeAllViews();
    }

    /**
     * Adding buttons
     */
    private void addButtons() {
        Button button = new Button(this);
        button.setText("Button");
        adjustableLayout.addView(button);
    }

    /**
     * Using view_images layout
     */
    private void addRandomView() {
        String name = etEnterName.getText().toString();
        if (!TextUtils.isEmpty(name)){
          final View newView = LayoutInflater.from(this).inflate(R.layout.view_images,null);
            TextView tvNumber = (TextView)newView.findViewById(R.id.tvNumber);
            tvNumber.setText(name);
            ImageView ivRemove = (ImageView)newView.findViewById(R.id.ivRemove);
            ivRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    adjustableLayout.removeView(newView);
                }
            });
            tvNumber.setText(name);
            adjustableLayout.addView(newView);
        }else {
            Toast.makeText(this,"Enter some text",Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Using view_chip_text layout
     */
    private void addChipsView() {
        String name = etEnterName.getText().toString();
        items += name + ",";
        if (!TextUtils.isEmpty(name)){
            final View newView = LayoutInflater.from(this).inflate(R.layout.view_chip_text,null);
            TextView tvName = (TextView)newView.findViewById(R.id.tvName);
            ImageView ivRemove = (ImageView)newView.findViewById(R.id.ivRemove);
            ivRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    adjustableLayout.removeView(newView);
                }
            });
            tvName.setText(name);
            adjustableLayout.addView(newView);
        }else {
            Toast.makeText(this,"Enter some text",Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Function to add view in a loop
     */
    private void addViewInALoop() {
        for (int i=0;i<stringList.size();i++){
            final View newView = LayoutInflater.from(this).inflate(R.layout.view_chip_text,null);
            TextView tvName = (TextView)newView.findViewById(R.id.tvName);
            ImageView ivRemove = (ImageView)newView.findViewById(R.id.ivRemove);
            ivRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    adjustableLayout.removeView(newView);
                }
            });
            tvName.setText(stringList.get(i));
            adjustableLayout.addingMultipleView(newView);
        }
        adjustableLayout.invalidateView();
    }
}

//https://recipe-recommend-jaykun.c9users.io/api/recipes/?i=eggs,honey,flour
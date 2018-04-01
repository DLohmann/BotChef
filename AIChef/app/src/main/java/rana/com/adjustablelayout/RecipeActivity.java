package rana.com.adjustablelayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RecipeActivity extends AppCompatActivity {
    private static final String TAG = "PhotoGalleryFragment";
    ArrayList<HashMap<String,String>> list_hm = new ArrayList<HashMap<String,String>>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        list_hm = (ArrayList<HashMap<String,String>>) getIntent().getSerializableExtra("ListExtra");
                        for(int i = 0; i < list_hm.size(); i++) {
                    for(Map.Entry<String,String> entry : list_hm.get(i).entrySet()) {
                        Log.i(TAG, entry.getKey() + ": " + entry.getValue());
                    }
                }
    }
    
}

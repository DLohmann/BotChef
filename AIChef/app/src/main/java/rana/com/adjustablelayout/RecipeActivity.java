package rana.com.adjustablelayout;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecipeActivity extends AppCompatActivity {
    private static final String TAG = "PhotoGalleryFragment";
    Toolbar mToolbar;
    RecyclerView mRecyclerView;
    List mFlowerList;
    FlowerData mFlowerData;
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

        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle(getResources().getString(R.string.app_name));
        mToolbar.setBackgroundColor(Color.parseColor("#80000000"));

        mRecyclerView = findViewById(R.id.recyclerview);
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(RecipeActivity.this, 2);
        mRecyclerView.setLayoutManager(mGridLayoutManager);

        mFlowerList = new ArrayList<>();
        for(int i = 0; i < list_hm.size(); i++) {
            String name = list_hm.get(i).get("name");
            String details = "<b>INGREDIENTS:</b>\n" + list_hm.get(i).get("ingredients") + "\n\n" + "<b>INSTRUCTIONS:</b>\n" + list_hm.get(i).get("instructions");
            int j = i%4;
            if(j==0)
            mFlowerData = new FlowerData(name, details,
                    R.drawable.chicken);
            else if(j==1) mFlowerData = new FlowerData(name, details,
                    R.drawable.beef);
            else if(j==2) mFlowerData = new FlowerData(name, details,
                    R.drawable.pork);
            else mFlowerData = new FlowerData(name, details,
                    R.drawable.salmon);
            mFlowerList.add(mFlowerData);
//            for(Map.Entry<String,String> entry : list_hm.get(i).entrySet()) {
//                Log.i(TAG, entry.getKey() + ": " + entry.getValue());
//            }
        }


//        mFlowerData = new FlowerData("Rose", getString(R.string.description_flower_rose),
//                R.drawable.rose);
//        mFlowerList.add(mFlowerData);
//        mFlowerData = new FlowerData("Carnation", getString(R.string.description_flower_carnation),
//                R.drawable.carnation);
//        mFlowerList.add(mFlowerData);
//        mFlowerData = new FlowerData("Tulip", getString(R.string.description_flower_tulip),
//                R.drawable.tulip);
//        mFlowerList.add(mFlowerData);
//        mFlowerData = new FlowerData("Daisy", getString(R.string.description_flower_daisy),
//                R.drawable.daisy);
//        mFlowerList.add(mFlowerData);
//        mFlowerData = new FlowerData("Sunflower", getString(R.string.description_flower_sunflower),
//                R.drawable.sunflower);
//        mFlowerList.add(mFlowerData);
//        mFlowerData = new FlowerData("Daffodil", getString(R.string.description_flower_daffodil),
//                R.drawable.daffodil);
//        mFlowerList.add(mFlowerData);
//        mFlowerData = new FlowerData("Gerbera", getString(R.string.description_flower_gerbera),
//                R.drawable.gerbera);
//        mFlowerList.add(mFlowerData);
//        mFlowerData = new FlowerData("Orchid", getString(R.string.description_flower_orchid),
//                R.drawable.orchid);
//        mFlowerList.add(mFlowerData);
//        mFlowerData = new FlowerData("Iris", getString(R.string.description_flower_iris),
//                R.drawable.lris);
//        mFlowerList.add(mFlowerData);
//        mFlowerData = new FlowerData("Lilac", getString(R.string.description_flower_lilac),
//                R.drawable.lilac);
//        mFlowerList.add(mFlowerData);

        MyAdapter myAdapter = new MyAdapter(RecipeActivity.this, mFlowerList);
        mRecyclerView.setAdapter(myAdapter);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
    }
    
}

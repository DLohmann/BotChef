package rana.com.adjustablelayout;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    Toolbar mToolbar;
    ImageView mFlower;
    TextView mDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setBackgroundColor(Color.parseColor("#80000000"));
        mFlower = findViewById(R.id.ivImage);
        mDescription = findViewById(R.id.tvDescription);
//        mDescription.setText(Html.fromHtml(mDescription.getText().toString()));
        mDescription.setMovementMethod(new ScrollingMovementMethod());
        Bundle mBundle = getIntent().getExtras();
        if (mBundle != null) {
            mToolbar.setTitle(mBundle.getString("Title"));
            mFlower.setImageResource(mBundle.getInt("Image"));
            mDescription.setText(Html.fromHtml(mBundle.getString("Description")));
//            mDescription.setText("hello\nworld");

        }
    }
}
package rana.com.adjustablelayout;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by yeo on 4/1/18.
 */

public class MyAdapter extends RecyclerView.Adapter<FlowerViewHolder> {

    private Context mContext;
    private List mFlowerList;

    MyAdapter(Context mContext, List mFlowerList) {
        this.mContext = mContext;
        this.mFlowerList = mFlowerList;
    }

    @Override
    public FlowerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_row, parent, false);
        return new FlowerViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(final FlowerViewHolder holder, int position) {
        FlowerData test = (FlowerData)mFlowerList.get(position);
        holder.mImage.setImageResource(test.getFlowerImage());
        holder.mTitle.setText(test.getFlowerName());
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(mContext, DetailActivity.class);
                FlowerData test2 = (FlowerData)mFlowerList.get(holder.getAdapterPosition());
                mIntent.putExtra("Title", test2.getFlowerName());
                mIntent.putExtra("Description", test2.getFlowerDescription());
                mIntent.putExtra("Image", test2.getFlowerImage());
                mContext.startActivity(mIntent);
            }
        });
//        holder.mImage.setImageResource(mFlowerList.get(position).getFlowerImage());
//        holder.mTitle.setText(mFlowerList.get(position).getFlowerName());
    }

    @Override
    public int getItemCount() {
        return mFlowerList.size();
    }
}

class FlowerViewHolder extends RecyclerView.ViewHolder {

    ImageView mImage;
    TextView mTitle;
    CardView mCardView;

    FlowerViewHolder(View itemView) {
        super(itemView);

        mImage = itemView.findViewById(R.id.ivImage);
        mTitle = itemView.findViewById(R.id.tvTitle);
        mCardView = itemView.findViewById(R.id.cardview);
    }
}

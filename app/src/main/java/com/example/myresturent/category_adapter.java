package com.example.myresturent;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;

import java.util.List;

public class category_adapter extends RecyclerView.Adapter<category_adapter.ViewHolder> {


    private Context context;
    private List<category> categories;

    public category_adapter(Context context, List<category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView v= (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.menulist,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        category categorys = categories.get(position);
        CardView cardView=holder.cardView;


//        Drawable dr = ContextCompat.getDrawable(cardView.getContext(),Integer.parseInt(categorys.getImageId()));
//        imageView.setImageDrawable(dr);

        Glide.with(context).load(categorys.getImageId()).into(holder.imageView);

        TextView txts = cardView.findViewById(R.id.text);
        txts.setText(categorys.getImageId());
        TextView txt = cardView.findViewById(R.id.text);
        txt.setText(categorys.getCate());


        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(context,MainActivity2.class);
                intent.putExtra("cat",categorys.getCate());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {

        private final CardView cardView;
        ImageView imageView;

        LinearLayout mainLayout;
        public ViewHolder(@NonNull CardView cardView) {
            super(cardView);
            this.cardView= cardView;
            imageView = cardView.findViewById(R.id.image);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }


}


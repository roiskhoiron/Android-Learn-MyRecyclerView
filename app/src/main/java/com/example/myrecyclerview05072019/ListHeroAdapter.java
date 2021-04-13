package com.example.myrecyclerview05072019;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class ListHeroAdapter extends RecyclerView.Adapter<ListHeroAdapter.CategoryViewHolder> {
    private ArrayList<Hero> listHero;
    private OnItemClickCallbcak onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallbcak onItemClickCallback){
        this.onItemClickCallback = onItemClickCallback;
    }

    public ListHeroAdapter(ArrayList<Hero> list){
        this.listHero = list;
    }

    @NonNull
    @Override
    public ListHeroAdapter.CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_hero, parent, false);

        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListHeroAdapter.CategoryViewHolder holder, int position) {
        Hero hero = listHero.get(position);

        Glide.with(holder.itemView.getContext()).load(hero.getPhoto()).apply(new RequestOptions().override(55,55)).into(holder.imgPhoto);
        holder.tvName.setText(hero.getName());
        holder.tvFrom.setText(hero.getFrom());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickCallback.onItemClicked(listHero.get(holder.getAdapterPosition()));
            }
        });
    }

    public interface OnItemClickCallbcak {
        void onItemClicked(Hero data);
    }

    @Override
    public int getItemCount() {

        return listHero.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder{
        ImageView imgPhoto;
        TextView tvName,tvFrom;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
            tvFrom = itemView.findViewById(R.id.tv_item_from);
            tvName = itemView.findViewById(R.id.tv_item_name);
        }
    }
}

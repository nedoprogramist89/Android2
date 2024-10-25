package com.example.pw2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<String> items;
    private Context context;

    public MyAdapter(Context context, List<String> items) {
        this.context = context;
        if (items != null && !items.isEmpty()) {
            this.items = items;
        } else {
            this.items = List.of();
            Toast.makeText(context, "Список данных пуст", Toast.LENGTH_SHORT).show();
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (items != null && !items.isEmpty()) {
            String item = items.get(position);
            holder.itemName.setText(item);

            holder.itemView.setOnClickListener(v -> {
                Toast.makeText(context, "Выбранно: " + item, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("item_name", item);
                context.startActivity(intent);
            });
        } else {
            holder.itemName.setText("Нет данных");
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
       public TextView itemName;

      public ViewHolder(View itemView) {
           super(itemView);
           itemName = itemView.findViewById(R.id.item_name);
        }
    }
}

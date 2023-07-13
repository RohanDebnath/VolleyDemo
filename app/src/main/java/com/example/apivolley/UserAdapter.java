package com.example.apivolley;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apivolley.userModel;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private Context context;
    private ArrayList<userModel> userList;

    public UserAdapter(Context context, ArrayList<userModel> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_item_layout, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        userModel user = userList.get(position);
        holder.bind(user);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        private TextView userIdTextView, idTextView, titleTextView, completedTextView;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            userIdTextView = itemView.findViewById(R.id.userIdTextView);
            idTextView = itemView.findViewById(R.id.idTextView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            completedTextView = itemView.findViewById(R.id.completedTextView);
        }

        public void bind(userModel user) {
            userIdTextView.setText("User ID: " + user.getUserId());
            idTextView.setText("ID: " + user.getId());
            titleTextView.setText("Title: " + user.getTitle());
            completedTextView.setText("Completed: " + user.isCompleted());
        }
    }
}

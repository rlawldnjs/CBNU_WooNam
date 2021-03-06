package com.app_services.WooNam.chattingapp.viewholder;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app_services.WooNam.chattingapp.R;
import com.app_services.WooNam.chattingapp.models.Post;

public class PostViewHolder extends RecyclerView.ViewHolder {

    public TextView titleView;
    public TextView authorView;
    public ImageView starView;
    public TextView numStarsView;
    public TextView bodyView;

    public PostViewHolder(View itemView) {
        super(itemView);

        titleView = itemView.findViewById(R.id.postTitle);
        authorView = itemView.findViewById(R.id.postAuthor);
        starView = itemView.findViewById(R.id.star);
        numStarsView = itemView.findViewById(R.id.postNumStars);
        bodyView = itemView.findViewById(R.id.postBody);
    }

    public void bindToPost(Post post, View.OnClickListener starClickListener) {
        titleView.setText(post.title);
        authorView.setText(post.author);
        numStarsView.setText(String.valueOf(post.starCount));
        bodyView.setText(post.body);

        starView.setOnClickListener(starClickListener);
    }
}

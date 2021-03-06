package com.app_services.WooNam.chattingapp.Fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.fragment.NavHostFragment;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.app_services.WooNam.chattingapp.R;
import com.app_services.WooNam.chattingapp.databinding.FragmentNewPostBinding;
import com.app_services.WooNam.chattingapp.models.Post;
import com.app_services.WooNam.chattingapp.UserModel.User;

import java.util.HashMap;
import java.util.Map;

public class NewPostFragment extends Fragment {
    private static final String TAG = "NewPostFragment";
    private static final String REQUIRED = "Required";

    private DatabaseReference mDatabase;
    FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();

    private FragmentNewPostBinding binding;

    public String getUid() {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentNewPostBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        binding.fabSubmitPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitPost();
            }
        });
    }

    private void submitPost() {
        final String title = binding.fieldTitle.getText().toString();
        final String body = binding.fieldBody.getText().toString();

        // Title is required
        if (TextUtils.isEmpty(title)) {
            binding.fieldTitle.setError(REQUIRED);
            return;
        }

        // Body is required
        if (TextUtils.isEmpty(body)) {
            binding.fieldBody.setError(REQUIRED);
            return;
        }

        // Disable button so there are no multi-posts
        setEditingEnabled(false);
        Toast.makeText(getContext(), "Posting...", Toast.LENGTH_SHORT).show();

        final String userId = getUid();
        FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid()).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        // Get user value
                        User user = dataSnapshot.getValue(User.class);
                        if (user == null) {
                            // User is null, error out
                            Log.e(TAG, "User " + userId + " is unexpectedly null");
                            Log.e(TAG, "User " + user.getName() + " is unexpectedly null");
                            Toast.makeText(getContext(),
                                    "Error: could not fetch user.",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            // Write new post
                            writeNewPost(userId, user.getUsername(), title, body);
                        }

                        setEditingEnabled(true);
                        NavHostFragment.findNavController(NewPostFragment.this)
                                .navigate(R.id.action_NewPostFragment_to_PostListFragment);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.w(TAG, "getUser:onCancelled", databaseError.toException());
                        setEditingEnabled(true);
                    }
                });
    }

    private void setEditingEnabled(boolean enabled) {
        binding.fieldTitle.setEnabled(enabled);
        binding.fieldBody.setEnabled(enabled);
        if (enabled) {
            binding.fabSubmitPost.show();
        } else {
            binding.fabSubmitPost.hide();
        }
    }

    private void writeNewPost(String userId, String username, String title, String body) {
        // Create new post at /user-posts/$userid/$postid and at
        // /posts/$postid simultaneously
        String key = mDatabase.child("posts").push().getKey();
        Post post = new Post(userId, username, title, body);
        Map<String, Object> postValues = post.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/posts/" + key, postValues);
        childUpdates.put("/user-posts/" + userId + "/" + key, postValues);

        mDatabase.updateChildren(childUpdates);
    }
}

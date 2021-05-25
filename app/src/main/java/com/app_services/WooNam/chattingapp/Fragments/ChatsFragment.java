package com.app_services.WooNam.chattingapp.Fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app_services.WooNam.chattingapp.Adapter.UserAdapter;
import com.app_services.WooNam.chattingapp.R;
import com.app_services.WooNam.chattingapp.UserModel.ChatList;
import com.app_services.WooNam.chattingapp.UserModel.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class ChatsFragment extends Fragment {

    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private List<User> userList;

    private List<ChatList> userChatList;

    FirebaseUser firebaseUser;
    DatabaseReference reference;

    TextView no_content_tv;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chats, container, false);
        recyclerView = view.findViewById(R.id.recycler_view_for_chatted_users);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        userChatList = new ArrayList<>();

        no_content_tv = view.findViewById(R.id.no_content_tv);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        reference = FirebaseDatabase.getInstance().getReference("ChatLists").child(firebaseUser.getUid());
        Log.i(TAG, "onCreateView: reference : "+ reference.toString());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userChatList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    ChatList newChatList = snapshot.getValue(ChatList.class);
                    userChatList.add(newChatList);
                }
                filterForSingleChat();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;
    }

    private void filterForSingleChat() {
        userList = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    User user = snapshot.getValue(User.class);

                    for (ChatList chatList : userChatList){

                        Log.i(TAG, "onDataChange: USER LIST : " + chatList);
                        assert user != null;
                        if (user.getId().equals(chatList.getId())){
                            userList.add(user);
                        }
                    }
                }

                if (userList.isEmpty()){
                    String str = "No chats available.";
                    no_content_tv.setText(str);
                    no_content_tv.setVisibility(View.VISIBLE);
                } else {
                    no_content_tv.setVisibility(View.GONE);
                }

                userAdapter = new UserAdapter(getContext(), userList, true);
                recyclerView.setAdapter(userAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}

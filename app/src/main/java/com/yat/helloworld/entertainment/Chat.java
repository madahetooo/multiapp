package com.yat.helloworld.entertainment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.yat.helloworld.R;
import com.yat.helloworld.models.ChatMessage;

import static android.app.Activity.RESULT_OK;

public class Chat extends Fragment implements View.OnClickListener {
    FloatingActionButton btnSendMessage, btnSendPhoto;

    View v;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.activity_chat, container, false);

        etMessageInput = v.findViewById(R.id.et_Message);
        btnSendMessage = v.findViewById(R.id.btn_sendMessage);
        btnSendPhoto = v.findViewById(R.id.btn_sendPhoto);
        btnSendMessage.setOnClickListener(this);
        btnSendPhoto.setOnClickListener(this);

//        btnSendMessage = findViewById(R.id.btn_sendMessage);
//        btnSendPhoto = findViewById(R.id.btns);
        messageReference = FirebaseDatabase.getInstance().getReference().child("Messages");
        user = FirebaseAuth.getInstance().getCurrentUser();
        userName = user.getDisplayName();

        mMessageRecyclerView = (RecyclerView) v.findViewById(R.id.messageRecyclerView);
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        mLinearLayoutManager.setStackFromEnd(true);
        mMessageRecyclerView.setLayoutManager(mLinearLayoutManager);

        // retrieve and reach to the data in the firebase database
        SnapshotParser<ChatMessage> parser = new SnapshotParser<ChatMessage>() {
            @NonNull
            @Override
            public ChatMessage parseSnapshot(DataSnapshot dataSnapshot) {
                ChatMessage friendlyMessage = dataSnapshot.getValue(ChatMessage.class);
                if (friendlyMessage != null) {
                    friendlyMessage.setId(dataSnapshot.getKey());
                }
                return friendlyMessage;
            }
        };


        // get messages and build recyclerView
        DatabaseReference messagesRef = messageReference.child(MESSAGES_CHILD);
        FirebaseRecyclerOptions<ChatMessage> options =
                new FirebaseRecyclerOptions.Builder<ChatMessage>()
                        .setQuery(messagesRef, parser)
                        .build();
        mFirebaseAdapter = new FirebaseRecyclerAdapter<ChatMessage, MessageViewHolder>(options) {
            @NonNull
            @Override
            public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                return new MessageViewHolder(inflater.inflate(R.layout.message, parent, false));

            }

            @Override
            protected void onBindViewHolder(@NonNull MessageViewHolder holder, int position, @NonNull ChatMessage model) {

//                calculate the time
                long unixTimestamp = (System.currentTimeMillis() / 1000);
                long differenceBetweenTimes = unixTimestamp - Long.parseLong(String.valueOf(model.getMessageTime()));
                long seconds = (differenceBetweenTimes) % 60;
                long minutes = ((differenceBetweenTimes / 60)) % 60;
                long hours = ((differenceBetweenTimes / (60 * 60)) % 24);
                long days = (differenceBetweenTimes / (60 * 60 * 24));


                if (model.getMessageText() != null) {
                    holder.txMessageText.setText(model.getMessageText());
                    holder.txMessageText.setVisibility(TextView.VISIBLE);
                    holder.ivMessagePhoto.setVisibility(ImageView.GONE);


                    holder.txMessageTime.setText(DateFormat.format("dd-MM-yyyy (H:mm)",// Format the date before showing it
                            Long.parseLong(String.valueOf(model.getMessageTime()))));
                    if (days > 0) {
                        holder.txMessageTime.setText(days + "" + "day");
                    } else if (hours > 0) {
                        holder.txMessageTime.setText(hours + "" + "hour");
                    } else if (minutes > 0) {
                        holder.txMessageTime.setText(minutes + "" + "minute");
                    } else {
                        holder.txMessageTime.setText(seconds + "" + "second");
                    }
                } else if (model.getImageUrl() != null) {
                    String imageUrl = model.getImageUrl();
                    if (imageUrl.startsWith("gs://")) {
                        StorageReference storageReference = FirebaseStorage.getInstance()
                                .getReferenceFromUrl(imageUrl);
                        storageReference.getDownloadUrl().addOnCompleteListener(
                                new OnCompleteListener<Uri>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Uri> task) {
                                        if (task.isSuccessful()) {
                                            String downloadUrl = task.getResult().toString();
                                            Glide.with(holder.ivMessagePhoto.getContext())
                                                    .load(downloadUrl)
                                                    .into(holder.ivMessagePhoto);
                                        } else {
                                            Log.w(TAG, "Getting download url was not successful.",
                                                    task.getException());
                                        }
                                    }
                                });
                    } else {
                        Glide.with(holder.ivMessagePhoto.getContext())
                                .load(model.getImageUrl())
                                .into(holder.ivMessagePhoto);
                    }
                    holder.ivMessagePhoto.setVisibility(ImageView.VISIBLE);
                    holder.txMessageText.setVisibility(TextView.GONE);
                }


                holder.txMessageUser.setText(model.getMessageUser());

                holder.txMessageTime.setText(DateFormat.format("dd-MM-yyyy (H:mm)",// Format the date before showing it
                        Long.parseLong(String.valueOf(model.getMessageTime()))));
                if (days > 0) {
                    holder.txMessageTime.setText(days + "" + "day");
                } else if (hours > 0) {
                    holder.txMessageTime.setText(hours + "" + "hour");
                } else if (minutes > 0) {
                    holder.txMessageTime.setText(minutes + "" + "minute");
                } else {
                    holder.txMessageTime.setText(seconds + "" + "second");
                }
            }
        };


        mFirebaseAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                int friendlyMessageCount = mFirebaseAdapter.getItemCount();
                int lastVisiblePosition =
                        mLinearLayoutManager.findLastCompletelyVisibleItemPosition();
                // If the recycler view is initially being loaded or the
                // user is at the bottom of the list, scroll to the bottom
                // of the list to show the newly added message.
                if (lastVisiblePosition == -1 ||
                        (positionStart >= (friendlyMessageCount - 1) &&
                                lastVisiblePosition == (positionStart - 1))) {
                    mMessageRecyclerView.scrollToPosition(positionStart);
                }
            }
        });

        mMessageRecyclerView.setAdapter(mFirebaseAdapter);


        return v;
    }

    public Chat() {
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_sendMessage:
              String message =   etMessageInput.getText().toString();
                if (message.isEmpty()){
                    etMessageInput.setError("Please Enter your Message");
                }
                else {
                    ChatMessage friendlyMessage = new
                            ChatMessage(message,
                            userName,
                            null /* no image */);
                    messageReference.child(MESSAGES_CHILD)
                            .push().setValue(friendlyMessage);
                    etMessageInput.setText("");
                }


                break;

            case R.id.btn_sendPhoto:
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_IMAGE);
                break;
        }

    }

    public static class MessageViewHolder extends RecyclerView.ViewHolder {
        TextView txMessageText;
        ImageView ivMessagePhoto;
        TextView txMessageUser;
        TextView txMessageTime;

        public MessageViewHolder(View v) {
            super(v);
            txMessageText = itemView.findViewById(R.id.txMessageText);
            ivMessagePhoto = itemView.findViewById(R.id.iv_photoMessage);
            txMessageUser = itemView.findViewById(R.id.txMessageUser);
            txMessageTime = itemView.findViewById(R.id.txMessageTime);

        }
    }

    public static final String MESSAGES_CHILD = "Messages";
    private static final String TAG = "Chat";

    EditText etMessageInput;
    private RecyclerView mMessageRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private String userName;
    FirebaseUser user;
    DatabaseReference messageReference;
    FirebaseListAdapter<ChatMessage> adapter;
    private static final int REQUEST_IMAGE = 2;
    private static final String LOADING_IMAGE_URL = "https://www.google.com/images/spin-32.gif";
    private GoogleSignInClient mSignInClient;
    private FirebaseRecyclerAdapter<ChatMessage, MessageViewHolder>
            mFirebaseAdapter;


////    private void displayChatMessage() {
////
////        //Reach to the database
////        Query query = FirebaseDatabase.getInstance().getReference().child("Messages");
////        FirebaseListOptions<ChatMessage> options = new FirebaseListOptions.Builder<ChatMessage>()
////                .setQuery(query, ChatMessage.class)
////                .setLayout(R.layout.message)
////                .build();
////        //create the adapter
////        adapter = new FirebaseListAdapter<ChatMessage>(options) {
////            @SuppressLint("RtlHardcoded")
////            @Override
////            protected void populateView(@NonNull View v, @NonNull ChatMessage model, int position) {
////
////                //find view by id
////                TextView txMessageText = v.findViewById(R.id.txMessageText);
////                TextView txMessageUser = v.findViewById(R.id.txMessageUser);
////                TextView txMessageTime = v.findViewById(R.id.txMessageTime);
////                ImageView ivMessagePhoto= v.findViewById(R.id.iv_photoMessage);
////
////
////
////                //check the current user
////                if (model.getMessageUser() != null && model.getMessageUser().equals(user.getDisplayName())) {
////
////                    //set layout params gravity
////                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
////                    params.gravity = Gravity.RIGHT;
////                    txMessageText.setTextColor(v.getResources().getColor(R.color.textColor));
////
////                    // handle views with the layout params
////                    txMessageUser.setLayoutParams(params);
////                    txMessageText.setLayoutParams(params);
////                    txMessageTime.setLayoutParams(params);
////                    ivMessagePhoto.setLayoutParams(params);
////
////                }
////                else if (model.getImageUrl() != null) {
////                    String imageUrl = model.getImageUrl();
////                    if (imageUrl.startsWith("gs://")) {
////                        StorageReference storageReference = FirebaseStorage.getInstance()
////                                .getReferenceFromUrl(imageUrl);
////                        storageReference.getDownloadUrl().addOnCompleteListener(
////                                new OnCompleteListener<Uri>() {
////                                    @Override
////                                    public void onComplete(@NonNull Task<Uri> task) {
////                                        if (task.isSuccessful()) {
////                                            String downloadUrl = task.getResult().toString();
////                                            Glide.with(ivMessagePhoto.getContext())
////                                                    .load(downloadUrl)
////                                                    .into(ivMessagePhoto);
////                                        }  //                                                    task.getException());
////
////                                    }
////                                });
////                    } else {
////                        Glide.with(ivMessagePhoto.getContext())
////                                .load(model.getImageUrl())
////                                .into(ivMessagePhoto);
////                    }
//////                    ivMessagePhoto.setVisibility(ImageView.VISIBLE);
//////                    txMessageText.setVisibility(TextView.GONE);
////                }
////                else {
////                    txMessageUser.setGravity(Gravity.LEFT | Gravity.START);
////                    txMessageText.setGravity(Gravity.LEFT | Gravity.START);
////                    txMessageTime.setGravity(Gravity.LEFT | Gravity.START );
////                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
////                        ivMessagePhoto.setForegroundGravity(Gravity.LEFT | Gravity.START);
////                    }
////                }
////
////
////                txMessageText.setText(model.getMessageText());
////                txMessageUser.setText(model.getMessageUser());
//////================================================================
////                txMessageUser.setText(model.getMessageUser());
////                ivMessagePhoto.setImageDrawable(Drawable.createFromPath(model.getImageUrl()));
////                txMessageTime.setText(DateFormat.format("dd-MM-yyyy (H:mm)",// Format the date before showing it
////                        Long.parseLong(String.valueOf(model.getMessageTime()))));
////                if (days > 0) {
////                    txMessageTime.setText(days + "" + "day");
////                } else if (hours > 0) {
////                    txMessageTime.setText(hours + "" + "hour");
////                } else if (minutes > 0) {
////                    txMessageTime.setText(minutes + "" + "minute");
////                } else {
////                    txMessageTime.setText(seconds + "" + "second");
////                }
////            }
////        };
////
////
//
//
//
////
//
//
//        adapter.startListening();
//        lvChat.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
//        lvChat.setAdapter(adapter);
//
//        adapter.registerDataSetObserver(new DataSetObserver() {
//            @Override
//            public void onChanged() {
//                super.onChanged();
//                lvChat.setSelection(adapter.getCount() - 1);
//            }
//        });
//
//    }


    @Override
    public void onPause() {
        mFirebaseAdapter.stopListening();

        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mFirebaseAdapter.startListening();

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    final Uri uri = data.getData();

                    ChatMessage tempMessage = new ChatMessage(null, userName,
                            LOADING_IMAGE_URL);
                    messageReference.child("Messages").push()
                            .setValue(tempMessage, new DatabaseReference.CompletionListener() {
                                @Override
                                public void onComplete(DatabaseError databaseError,
                                                       DatabaseReference databaseReference) {
                                    if (databaseError == null) {
                                        String key = databaseReference.getKey();
                                        StorageReference storageReference =
                                                FirebaseStorage.getInstance()
                                                        .getReference(user.getUid())
                                                        .child("images")
                                                        .child(uri.getLastPathSegment());

                                        putImageInStorage(storageReference, uri, key);
                                    } else {
                                    }
                                }
                            });
                }
            }
        }
    }

    private void putImageInStorage(StorageReference storageReference, Uri uri, final String key) {
        storageReference.putFile(uri).addOnCompleteListener(getActivity(),
                new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        if (task.isSuccessful()) {
                            task.getResult().getMetadata().getReference().getDownloadUrl()
                                    .addOnCompleteListener((Activity) getContext(),
                                            new OnCompleteListener<Uri>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Uri> task) {
                                                    if (task.isSuccessful()) {
                                                        ChatMessage friendlyMessage =
                                                                new ChatMessage(null, userName,
                                                                        task.getResult().toString());
                                                        messageReference.child("Messages").child(key)
                                                                .setValue(friendlyMessage);
                                                    }
                                                }
                                            });
                        } else {
                        }
                    }
                });
    }
}
package com.example.martinruiz.snapnotes.fragments;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.martinruiz.snapnotes.DatabaseModel.BoardContent;
import com.example.martinruiz.snapnotes.DatabaseModel.Notes;
import com.example.martinruiz.snapnotes.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.martinruiz.snapnotes.utils.DatabaseCRUD.BOARDS;


/**
 * A simple {@link Fragment} subclass.
 */
public class GalleryFragment extends Fragment {

    private DatabaseReference mDatabase;
    @BindView(R.id.rvGallery) RecyclerView rvGallery;
    @BindView(R.id.ibBack) ImageButton ibBack;
    @BindView(R.id.tvGalleryName) TextView tvGalleryName;
    RecyclerView.LayoutManager layoutManager;
    private FirebaseRecyclerAdapter adapter;
    private FirebaseRecyclerAdapter adapter1;
    private String selectedBoard;



    public GalleryFragment() {
        // Required empty public constructor
    }

    public static GalleryFragment create(){
        return new GalleryFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_gallery, container, false);
        view.setTag(10);
        ButterKnife.bind(this,view);

        mDatabase = FirebaseDatabase.getInstance().getReference().child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child(FirebaseAuth.getInstance().getUid())
                .child(BOARDS)
                .limitToLast(50);



        FirebaseRecyclerOptions<BoardContent> options =
                new FirebaseRecyclerOptions.Builder<BoardContent>()
                        .setQuery(query, BoardContent.class)
                        .build();



         adapter = new FirebaseRecyclerAdapter<BoardContent, BoardsHolder>(options) {
             @Override
            public BoardsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                 // Create a new instance of the ViewHolder, in this case we are using a custom
                 // layout called R.layout.card_gallery for each item
                 View view = LayoutInflater.from(parent.getContext())
                         .inflate(R.layout.card_gallery, parent, false);
                 BoardsHolder boardsHolder = new BoardsHolder(view);
                 boardsHolder.setOnClickListener(new BoardsHolder.ClickListener() {
                     @Override
                     public void onItemClick(View view, int position) {


                         BoardContent boardContent = (BoardContent) adapter.getItem(position);
                         selectedBoard = boardContent.getName();
                         getBoardNotes(mDatabase, selectedBoard);


                         tvGalleryName.setText(selectedBoard);
                         ibBack.setVisibility(View.VISIBLE);

                         //TODO: Delete to add gridview till next todo
                         Query query1 = FirebaseDatabase.getInstance()
                                 .getReference()
                                 .child(FirebaseAuth.getInstance().getUid())
                                 .child(BOARDS)
                                 .child(selectedBoard)
                                 .child("notes")
                                 .limitToLast(50);

                         FirebaseRecyclerOptions<Notes> options1 =
                                 new FirebaseRecyclerOptions.Builder<Notes>()
                                         .setQuery(query1, Notes.class)
                                         .build();

                         adapter1 = new FirebaseRecyclerAdapter<Notes, NotesHolder>(options1) {
                             @Override
                             public NotesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                                 // Create a new instance of the ViewHolder, in this case we are using a custom
                                 // layout called R.layout.card_gallery for each item
                                 View view = LayoutInflater.from(parent.getContext())
                                         .inflate(R.layout.card_gallery, parent, false);
                                 NotesHolder notesHolder = new NotesHolder(view);
                                 notesHolder.setOnClickListener(new NotesHolder.ClickListener() {
                                     @Override
                                     public void onItemClick(View view, int position) {

                                         Toast.makeText(getActivity(), "Item clicked at " + position, Toast.LENGTH_SHORT).show();
                                     }
                                 });

                                 return notesHolder;
                             }

                             @Override
                             protected void onBindViewHolder(NotesHolder holder, int position, Notes model) {
                                 holder.textView.setText(model.getId());
                             }


                         };
                         rvGallery.setAdapter(adapter1);
                         adapter1.startListening();
                         //TODO: End delete


                         Toast.makeText(getActivity(), "Item clicked at " + boardContent.getName(), Toast.LENGTH_SHORT).show();
                     }
                 });

                 return boardsHolder;
             }

            @Override
            protected void onBindViewHolder(BoardsHolder holder, int position, BoardContent model) {
                holder.textView.setText(model.getName());
            }


        };

        layoutManager = new LinearLayoutManager(getContext());
        rvGallery.setAdapter(adapter);
        rvGallery.setLayoutManager(layoutManager);
        adapter.startListening();

        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getBack();
            }
        });
        return view;

    }

    public void getBack(){
        tvGalleryName.setText("Gallery");
        ibBack.setVisibility(View.GONE);
        rvGallery.setAdapter(adapter);

    }

    public static class BoardsHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        private BoardsHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tvBoardName);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mClickListener.onItemClick(view, getAdapterPosition());
                }
            });
        }

        private BoardsHolder.ClickListener mClickListener;

        //Interface to send callbacks...
        public interface ClickListener{
             void onItemClick(View view, int position);
        }

        public void setOnClickListener(BoardsHolder.ClickListener clickListener){
            mClickListener = clickListener;
        }
    }

    public static class NotesHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        private NotesHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tvBoardName);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mClickListener.onItemClick(view, getAdapterPosition());
                }
            });
        }

        private NotesHolder.ClickListener mClickListener;

        //Interface to send callbacks...
        public interface ClickListener{
            void onItemClick(View view, int position);
        }

        public void setOnClickListener(NotesHolder.ClickListener clickListener){
            mClickListener = clickListener;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    public static void getBoardNotes(DatabaseReference mDatabase, String boardName)  {


        mDatabase.child(BOARDS).child(boardName).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get board value

                        ArrayList<Notes> boardNotes = new ArrayList<>();
//                        Log.d("entra","entra");
                        BoardContent boardContent = dataSnapshot.getValue(BoardContent.class);



                        // [START_EXCLUDE]
                        if (boardContent == null) {

                            // Notes is null, error out
                            //Log.e(TAG, "Days is unexpectedly null");
                        } else {

                            if(boardContent.getNotes() != null) {


                                //Get data from the notes
                                for (String key : boardContent.getNotes().keySet()) {
                                    Notes notes = boardContent.getNotes().get(key);
                                    boardNotes.add(notes);
                                    Log.d("Note name", notes.getId());

                                }
                                //TODO: add the gridview here
                            }else{

                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //Log.w(TAG, "getPost:onCancelled", databaseError.toException());

                    }
                });
    }

}

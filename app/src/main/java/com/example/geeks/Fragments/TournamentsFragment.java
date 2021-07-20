package com.example.geeks.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.geeks.R;
import com.example.geeks.models.NewTournamentActivity;

public class TournamentsFragment extends Fragment {

    public static final String TAG = "TournamentFragment";
    Context context;

    Button btNewTournament;

    public TournamentsFragment() {
        // Required empty public constructor
    }

    public static TournamentsFragment newInstance(Context context) {
        TournamentsFragment fragment = new TournamentsFragment();
        fragment.context = context;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tournaments, container, false);
    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btNewTournament = view.findViewById(R.id.btNewTournament);
        btNewTournament.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNewTournament();
            }
        });
}

    private void goToNewTournament() {
        Intent i = new Intent(this.context, NewTournamentActivity.class);
        startActivity(i);
    }
    }
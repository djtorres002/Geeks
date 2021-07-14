package com.example.geeks.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.geeks.R;

public class TournamentsFragment extends Fragment {

    public static final String TAG = "HomeFragment";
    Context context;
    //List<Post> postList;
    //RecyclerView rvPost;
    //PostAdapter adapter;
    //private SwipeRefreshLayout swipeContainer;

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
}
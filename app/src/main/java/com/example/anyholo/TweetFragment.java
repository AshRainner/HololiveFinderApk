package com.example.anyholo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.anyholo.Adapter.KirinukiAdapter;
import com.example.anyholo.Adapter.TweetAdapter;
import com.example.anyholo.Model.KirinukiView;
import com.example.anyholo.Model.MemberView;
import com.example.anyholo.Model.TweetView;

import java.util.ArrayList;

public class TweetFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private ListView listView;
    private TweetAdapter TweetAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ArrayList<TweetView> list;
    private ArrayList<TweetView> copyList;

    public TweetFragment(ArrayList<TweetView> list) {
        this.list = list;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("트위터","시작");
        View view = inflater.inflate(R.layout.tweet_fragment,container,false);
        listView = view.findViewById(R.id.tweet_list);
        swipeRefreshLayout = view.findViewById(R.id.tweetLayout);
        TweetAdapter = new TweetAdapter();
        //list = (ArrayList<TweetView>) getArguments().getSerializable("TweetList");
        copyList = new ArrayList<TweetView>();
        for(TweetView x : list){
            copyList.add(x);
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TweetView t = (TweetView) TweetAdapter.getItem(i);
                Uri uri = Uri.parse("https://twitter.com/"+t.getUserId()+"/status/"+t.getTweetId());
                Log.d("ASDF : ","https://twitter.com/"+t.getUserId()+"/status/"+t.getTweetId());
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }catch (Exception e){
                    Log.d("오류 : ","이이잉");
                }
                //Log.d("사이즈 : ",intent.leng)1585103928907087872
                //startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://twitter.com/usadapekora/status/"+t.getTweetId())));
            }
        });
        TweetAdapter.setItems(list);
        listView.setAdapter(TweetAdapter);
        swipeRefreshLayout.setOnRefreshListener(this);
        return view;
    }
    @Override
    public void onRefresh() {
        new Thread(new Runnable(){
            @Override
            public void run() {
                    swipeRefreshLayout.setRefreshing(false);
            }
        }).start();
    }
}
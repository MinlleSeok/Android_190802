package com.example.itwill.myapplication_190802;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements ActionBar.TabListener {

    // 변수 선언
    ActionBar.Tab tabSong, tabArtist, tabAlbum;

    // 내부클래스(이너클래스) 타입의 배열 생성
    MyTabFragment[] myFrag = new MyTabFragment[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.Layout.activity_main);

        ActionBar bar = getSupportActionBar();
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // 탭을 설정
        tabSong = bar.newTab();
        tabSong.setText("음악별");
        tabSong.setTabListener(this);

        bar.addTab(tabSong);

        tabArtist = bar.newTab();
        tabArtist.setText("가수별");
        tabArtist.setTabListener(this);

        bar.addTab(tabArtist);

        tabAlbum = bar.newTab();
        tabAlbum.setText("앨범별");
        tabAlbum.setTabListener(this);

        bar.addTab(tabAlbum);

    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {

        MyTabFragment myTabFrag = null;

        if(myFrag[tab.getPosition()] == null) {

            myTabFrag = new MyTabFragment();
            Bundle data = new Bundle();
            data.putString("tabName",tab.getText().toString());
            myTabFrag.setArguments(data);
            myFrag[tab.getPosition()] = myTabFrag;

        } else {
            myTabFrag = myFrag[tab.getPosition()];
        }
        ft.replace(android.R.id.content,myTabFrag);
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    // 프래그먼트 : 액티비티의 축소판
    // 프래그먼트 클래스를 이너 클래스로 선언

    public static class MyTabFragment extends Fragment {
        String tabName;

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            Bundle data = getArguments();
            tabName = data.getString("tabName");

        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);

            LinearLayout baseLayout = new LinearLayout(super.getActivity());

            baseLayout.setOrientation(LinearLayout.VERTICAL);
            baseLayout.setLayoutParams(params);

            if(tabName == "음악별") baseLayout.setBackgroundColor(Color.RED);
            if(tabName == "가수별") baseLayout.setBackgroundColor(Color.GREEN);
            if(tabName == "앨범별") baseLayout.setBackgroundColor(Color.BLUE);

            return baseLayout;
        }
    }

}

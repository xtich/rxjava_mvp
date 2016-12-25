package com.customeview.rxjava_mvp.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.ashokvarma.bottomnavigation.BadgeItem;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.customeview.rxjava_mvp.R;
import com.customeview.rxjava_mvp.fragment.AppFragment;
import com.customeview.rxjava_mvp.fragment.MeFagment;
import com.customeview.rxjava_mvp.fragment.MessageFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends BaseActivity {

    @InjectView(R.id.frame_layout)
    FrameLayout frameLayout;
    @InjectView(R.id.table_bar)
    BottomNavigationBar bottomNavigationBar;
    @InjectView(R.id.activity_main)
    LinearLayout activityMain;
    List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        initNativeBar();
    }

    private void initNativeBar() {
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        BadgeItem numberBadgeItem = new BadgeItem()
                .setBorderWidth(4)
                .setBackgroundColor(Color.RED)
                .setText("5")
                .setHideOnSelect(true);
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.ic_home_white_24dp, "消息").setActiveColorResource(R.color.blue).setBadgeItem(numberBadgeItem))
                .addItem(new BottomNavigationItem(R.mipmap.ic_book_white_24dp, "应用").setActiveColorResource(R.color.blue))
                .addItem(new BottomNavigationItem(R.mipmap.ic_music_note_white_24dp, "我的").setActiveColorResource(R.color.blue))
                .setFirstSelectedPosition(0)//默认选中
                .initialise();

        fragments=getFragments();
        setDefaultFragment();
        bottomNavigationBar.setTabSelectedListener(new NativeBarListener());
    }

    private void setDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        MessageFragment  msgFragment = MessageFragment.newInstance("MSG");
        transaction.replace(R.id.frame_layout, msgFragment);
        transaction.commit();
    }


    private List<Fragment> getFragments() {
        List<Fragment> list=new ArrayList<>();
        list.add(MessageFragment.newInstance("MESSAGE"));
        list.add(AppFragment.newInstance("APP"));
        list.add(MeFagment.newInstance("ME"));
        return list;
    }

    @Override
    protected void onDestroy() {
        ButterKnife.reset(this);
        super.onDestroy();
    }

    private class NativeBarListener implements BottomNavigationBar.OnTabSelectedListener{
        public   MessageFragment messageFragment;
        public AppFragment appFragment;
        public MeFagment meFagment;
        @Override
        public void onTabSelected(int position) {
            if (fragments != null) {
                if (position < fragments.size()) {
                    FragmentManager fm = getSupportFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    Fragment fragment = fragments.get(position);
                    if(fragment!=null){
                        ft.replace(R.id.frame_layout, fragment);
                    }
                    ft.commit();
                }
            }
//            FragmentManager fm = getSupportFragmentManager();
//            FragmentTransaction ft = fm.beginTransaction();
//            switch (position){
//                case 0:
//                    if (messageFragment==null){
//                        messageFragment=MessageFragment.newInstance("MSG");
//                    }
//                    ft.replace(R.id.frame_layout, messageFragment);
//                    break;
//                case 1:
//                    if (appFragment==null){
//                        appFragment=AppFragment.newInstance("APP");
//                    }
//                    ft.replace(R.id.frame_layout, appFragment);
//                    break;
//                case 2:
//                    if (meFagment==null){
//                        meFagment=MeFagment.newInstance("ME");
//                    }
//                    ft.replace(R.id.frame_layout, meFagment);
//                    break;
//            }
//                ft.commit();
        }

        @Override
        public void onTabUnselected(int position) {
            if (fragments != null) {
                if (position < fragments.size()) {
                    FragmentManager fm = getSupportFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    Fragment fragment = fragments.get(position);
                    ft.remove(fragment);
                    ft.commitAllowingStateLoss();
                }
            }
        }

        @Override
        public void onTabReselected(int position) {

        }
    }
}

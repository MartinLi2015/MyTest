package com.example.dialogtest.swiperefershlayout;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Toast;

import com.example.dialogtest.MasonryAdapter;
import com.example.dialogtest.Product;
import com.example.dialogtest.R;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

/**
 * Created by admin on 2015/12/5.
 */
public class SwipeRefershLayoutActivity extends Activity {

    private android.os.Handler handler = new android.os.Handler();
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    List<Product> productList;
    private View root;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        root = LayoutInflater.from(SwipeRefershLayoutActivity.this).inflate(R.layout.activity_swipe_refersh_layout,null);
        setContentView(root);


        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.layout_swipe_refersh);
        swipeRefreshLayout.setColorSchemeColors(R.color.gray);
        productList = new ArrayList<Product>();
        final MasonryAdapter adapter=new MasonryAdapter(productList);

        recyclerView = (RecyclerView) findViewById(R.id.swipe_recyclerview);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout.setOnRefreshListener(listener);

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <20; i++) {
                    Product product = new Product(R.drawable.ic_launcher,"test","1111","http://www.baidu.com");
                    productList.add(product);
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();

//        root.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                root.getViewTreeObserver().removeGlobalOnLayoutListener(this);
//                swipeRefreshLayout.setRefreshing(true);
//                listener.onRefresh();
//            }
//        });

//        swipeRefreshLayout.post(new Runnable() {
//            @Override
//            public void run() {
//                Toast.makeText(SwipeRefershLayoutActivity.this, "post runnable", Toast.LENGTH_SHORT).show();
//                swipeRefreshLayout.setRefreshing(true);
//                listener.onRefresh();
//
//            }
//        });
        setRefreshing(swipeRefreshLayout,true,true);


    }

    /*使用反射*/

   /***
    *  反射请在Proguard里面屏蔽掉setRefreshing，代码如下

        -keepclassmembers android.support.v4.widget.SwipeRefreshLayout{
            private void setRefreshing(boolean,boolean);
         }
    *
    * */
    public static void setRefreshing(SwipeRefreshLayout refreshLayout,boolean refreshing, boolean notify){
        Class<? extends SwipeRefreshLayout> refreshLayoutClass = refreshLayout.getClass();
        if (refreshLayoutClass != null) {
            try {
                Method setRefreshing = refreshLayoutClass.getDeclaredMethod("setRefreshing", boolean.class, boolean.class);
                setRefreshing.setAccessible(true);
                setRefreshing.invoke(refreshLayout, refreshing, notify);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace(); } } }

    SwipeRefreshLayout.OnRefreshListener listener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {

            Toast.makeText(SwipeRefershLayoutActivity.this,"refershing",Toast.LENGTH_LONG).show();
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            swipeRefreshLayout.setRefreshing(false);
        }
    };
}

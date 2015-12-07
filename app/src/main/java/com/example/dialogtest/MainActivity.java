package com.example.dialogtest;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity
{
//	private Dialog allMsg;
	// Dialog的布局View
//	private View allMsgView;

	ListView listview;
	List<Product> productList;
	private RecyclerView recyclerView;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

//		MyIntentService3.startActionBaz(MainActivity.this, "", "");
//		MyIntentService3.startActionFoo(MainActivity.this, "", "");
//
//		MyIntentService2.startActionFoo(MainActivity.this, "", "");
//		MyIntentService.startActionFoo(MainActivity.this, "", "");
//
//		init();
		productList = new ArrayList<Product>();
		for (int i = 0; i <10; i++) {
			Product product = new Product(R.drawable.ic_launcher,"test","1111","http://www.baidu.com");
			productList.add(product);
		}
		listview = (ListView) findViewById(R.id.listview);
		listview.setAdapter(new ListViewAdapter(MainActivity.this,productList));


		View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.footer_by_listview,null);

		recyclerView= (RecyclerView) view.findViewById(R.id.recyclerview);
		//设置layoutManager
		recyclerView.setLayoutManager(new ExStaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
		MasonryAdapter adapter=new MasonryAdapter(productList);
		recyclerView.setAdapter(adapter);
		//设置item之间的间隔
		SpacesItemDecoration decoration=new SpacesItemDecoration(16);
		recyclerView.addItemDecoration(decoration);

		listview.addFooterView(view);
	}

//	private void init()
//	{
//		// 通过LayoutInflater找到改布局
//		allMsgView = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.dialog1, null);
//		// 创建Dialog
//		allMsg = new Dialog(MainActivity.this,R.style.Dialog);
//		allMsg.setContentView(allMsgView);
//		// 设置点击外边缘不消失，2.x的应该是默认不消失的
//		allMsg.setCanceledOnTouchOutside(false);
//
//		// findView布局里的控件
//		ImageButton imgBtn_dialog = (ImageButton) allMsgView.findViewById(R.id.dialog_pre_entry_close);
//		imgBtn_dialog.setOnClickListener(this);
//		allMsg.show();
//		//·········
//	}
//
////	public void show(View v)
////	{
////		// 两句的顺序不能调换
////		allMsg.show();
////		allMsg.getWindow().setContentView((RelativeLayout) allMsgView);
////	}
//
//	@Override
//	public void onClick(View v)
//	{
//		switch (v.getId())
//		{
//		// dialog的图片取消button
//		case R.id.dialog_pre_entry_close:
//			allMsg.dismiss();
//			break;
//		default:
//			break;
//		}
//	}
}

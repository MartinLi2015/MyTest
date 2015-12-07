package com.example.dialogtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by admin on 2015/12/3.
 */
public class ListViewAdapter extends BaseAdapter {
    private Context context;
    private List<Product> productList;

    public ListViewAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int i) {
        return productList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view == null){
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.listview_item,null);
            viewHolder.imageView = (ImageView) view.findViewById(R.id.imageview);
            viewHolder.tvTitle = (TextView) view.findViewById(R.id.tv_title);
            viewHolder.tvPrice = (TextView) view.findViewById(R.id.tv_price);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

        if(productList!=null){
            viewHolder.imageView.setImageResource(productList.get(i).getImg());
            viewHolder.tvPrice.setText(productList.get(i).getPrice());
            viewHolder.tvTitle.setText(productList.get(i).getTitle());
        }

        return view;
    }
    public static class ViewHolder{

        ImageView imageView;
        TextView tvTitle;
        TextView tvPrice;
    }
}

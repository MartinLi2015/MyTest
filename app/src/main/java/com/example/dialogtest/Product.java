package com.example.dialogtest;

import android.webkit.WebView;

/**
 * Created by admin on 2015/12/3.
 */
public class Product {
    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getWebViewUrl() {
        return WebViewUrl;
    }

    public void setWebViewUrl(String webViewUrl) {
        WebViewUrl = webViewUrl;
    }

    private int img;
    private String title;
    private String price;
    private String WebViewUrl;

    public Product(int img, String title, String price, String webViewUrl) {
        this.img = img;
        this.title = title;
        this.price = price;
        WebViewUrl = webViewUrl;
    }

}

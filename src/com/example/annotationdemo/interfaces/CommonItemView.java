package com.example.annotationdemo.interfaces;

/**
 * Created by hejinxi on 15/7/4.
 */
public interface CommonItemView<T> {

    void bindView(T data);
    void bindView(T data,int position);
    void bindView(T data,int type,int position);
}

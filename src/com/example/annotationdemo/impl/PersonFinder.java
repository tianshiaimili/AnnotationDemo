package com.example.annotationdemo.impl;

import java.util.List;

import com.example.annotationdemo.bean.Person;

/**
 * Created by test on 2015/7/2.
 */
public interface PersonFinder<T> {
    List<Person> findAll();

//    void findAll();
}

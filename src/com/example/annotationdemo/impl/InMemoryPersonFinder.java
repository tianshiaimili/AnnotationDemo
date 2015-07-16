package com.example.annotationdemo.impl;

import java.util.ArrayList;
import java.util.List;

import org.androidannotations.annotations.EBean;

import com.example.annotationdemo.bean.Person;

/**
 * Created by test on 2015/7/2.
 */
@EBean
public class InMemoryPersonFinder implements PersonFinder {
//    @Override
//    public List<Person> findAll() {
//        return null;
//    }

    @Override
    public List<Person> findAll() {
        List<Person> list = new ArrayList<Person>();
        for(int i =0 ;i < 20;i++){

            Person person = new Person("firstName"+i,"lastName"+i);
            list.add(person);

    }
    return list;
    }
}

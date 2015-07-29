package com.example.annotationdemo;

import java.util.ArrayList;
import java.util.List;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.FragmentById;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ViewById;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.widget.ListView;
import android.widget.Toast;

import com.example.annotationdemo.adapter.PersonListAdapter;
import com.example.annotationdemo.bean.Person;
import com.example.annotationdemo.fragment.MyFragment;
import com.example.annotationdemo.fragment.ThreeFragment_;
import com.example.annotationdemo.impl.InMemoryPersonFinder;

/**
 * Created by test on 2015/7/2.
 */
@EActivity(R.layout.person_list)
public class PersonFragment extends Activity{
    @ViewById
    ListView personList;

    @Bean
    InMemoryPersonFinder finder;

    @Bean
    PersonListAdapter adapter;

    
    @FragmentById(R.id.myFragment)
    MyFragment myFragment;
    
    
    
    @AfterViews
    void bindAdapter() {

        //
        List <Person>list = new ArrayList<Person>();
        for(int i =0 ;i < 20;i++) {

            Person person = new Person("firstName777" + i, "lastName" + i);
            list.add(person);
        }

        adapter.setData(list);
        personList.setAdapter(adapter);

    }

    @ItemClick
    void personListItemClicked(Person person) {
//        makeText(this, person.firstName + " " + person.lastName, LENGTH_SHORT).show();
        Toast.makeText(this, person.firstName + " " + person.lastName, Toast.LENGTH_SHORT).show();
        repleaceFragment();
        
        
    }
    
    private void repleaceFragment(){
    	
    	FragmentManager manager  = getFragmentManager();
    	FragmentTransaction transaction = manager.beginTransaction();
    	transaction.replace(R.id.myFragment, new ThreeFragment_.FragmentBuilder_().anotherStringArgument("From PersonFragment.").myMessage("杩欎釜鏄缃瓼ragmentArg鐨勫弬鏁颁紶閫�").build());
    	transaction.commit();
    }
    
}

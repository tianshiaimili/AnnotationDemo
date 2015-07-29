package com.example.annotationdemo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringArrayRes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.annotationdemo.bean.InterestBean;
import com.example.annotationdemo.utils.LogUtils;
import com.example.annotationdemo.view.CustomGridView;


@EActivity(R.layout.choice_interest)
public class InterestActivity extends Activity {
	
	@ViewById
	TextView cancel_tv,title_tv,sure_tv;
	
	@ViewById
	CustomGridView gridview;
	
	@StringArrayRes(R.array.interest)
	String [] titles;
	
	List<InterestBean> list = new ArrayList<InterestBean>();
	
	@AfterInject
	void initVariable(){
		
		for(String string : titles){
			InterestBean bean = new InterestBean();
			bean.setName(string);
			list.add(bean);
			
		}
		
	}
	
	@AfterViews 
	void initViews(){
		
		
		
		
	}
	
	
}

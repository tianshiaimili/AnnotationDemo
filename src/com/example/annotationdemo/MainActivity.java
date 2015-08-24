package com.example.annotationdemo;

import java.util.Date;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.Button;

import com.example.annotationdemo.testactivity.ChoiceInterest3_;
import com.example.annotationdemo.testactivity.PostsActvity_;
import com.example.annotationdemo.utils.LogUtils;


@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {

	
	public static final String MY_STRING_EXTRA = "myStringExtra";
	public static final String MY_DATE_EXTRA = "myDateExtra";
	public static final String MY_INT_EXTRA = "myIntExtra";
	     
	@ViewById
	Button test1,test2,test3,test4;
	
	
	@Extra(MY_INT_EXTRA)
	String classCastExceptionExtra = "啦啦啦啦啦";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LogUtils.i("onCreate----");
	}

	
	
	@AfterInject
	void afterInjectMethod(){
		LogUtils.d("afterInjectMethod----");
		
	}
	
	@AfterViews
	void AfterViewsMethod(){
		LogUtils.d("AfterViewsMethod----");
		
		Html.fromHtml(getString(R.string.test1));
//		test1.setText(Html.fromHtml(getString(R.string.test1)));
		test1.setText(Html.fromHtml("<b> &lt;喀喀喀 &gt; </b> 换吧换吧 <\n>可以的"));
//		test1.setText(getString(R.string.test1));
		
	}
	
	@Override
	protected void onStart() {
		LogUtils.i("onStart----");
		super.onStart();
	}

	@Override
	protected void onRestart() {
		LogUtils.i("onRestart----");
		super.onRestart();
	}

	@Override
	protected void onResume() {
		LogUtils.i("onResume----");
		super.onResume();
	}

	@Override
	protected void onPause() {
		LogUtils.i("onPause----");
		super.onPause();
	}

	@Override
	protected void onStop() {
		LogUtils.i("onStop----");
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		LogUtils.i("onDestroy----");
		super.onDestroy();
	}
	
	@Click
	void test1(){
		
		Intent intent = new Intent(this,ChoiceInterest_.class);
		startActivity(intent);
		
	}
	
	
	@Click
	void test2(){
		
		Intent intent = new Intent(this,ActivityWithExtra_.class);
		intent.putExtra(MY_STRING_EXTRA, "from MainActivity");
		intent.putExtra(MY_DATE_EXTRA, new Date());
		startActivity(intent);
		
	}
	
	@Click
	void test3(){
		
//		Intent intent = new Intent(this,PersonFragment_.class);
//		startActivity(intent);
		
	}
	
	
	@Click
	void test4(){
		
		Intent intent = new Intent(this,WebViewPlayer_.class);
		startActivity(intent);
		
	}
	
	@Click
	void test5(){
		
		Intent intent = new Intent(this,MultipleItemsList.class);
		startActivity(intent);
		
	}
	
	
	@Click
	void test6(){
		
		Intent intent = new Intent(this,CreamDetail_.class);
		startActivity(intent);
		
	}
	
	@Click
	void test7(){
		
		Intent intent = new Intent(this,ChoiceInterest3_.class);
		startActivity(intent);
		
	}
	
	@Click
	void test8(){
		
		Intent intent = new Intent(this,PostsActvity_.class);
		startActivity(intent);
		
	}
	
	
	@Click
	void test9(){
		
		Intent intent = new Intent(this,CreditActivity_.class);
		startActivity(intent);
		
	}
	
	@Click
	void test10(){
		
		Intent intent = new Intent(this,HTMLActivity.class);
		startActivity(intent);
		
	}
	
	
	@Click
	void test11(){
		
		Intent intent = new Intent(this,PersonFragmentNew_.class);
		startActivity(intent);
		
	}
	
}

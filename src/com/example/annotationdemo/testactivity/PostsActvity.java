package com.example.annotationdemo.testactivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import android.app.Activity;
import android.widget.LinearLayout;

import com.example.annotationdemo.R;
import com.example.annotationdemo.adapter.TestAdapter;
import com.example.annotationdemo.listener.QuickReturnListViewOnScrollListener;
import com.example.annotationdemo.listener.QuickReturnType;
import com.example.annotationdemo.utils.LogUtils;
import com.example.annotationdemo.utils.PhoneInfoUtil;
import com.example.annotationdemo.view.RefleshListView;

@EActivity(R.layout.posts_layout)
public class PostsActvity extends Activity{

	@ViewById
	RefleshListView listView;
//	@ViewById
//	ListView listView;
	@ViewById
	LinearLayout bottom_layout;
	
	private TestAdapter adapter;
	
	@AfterViews
	void initViews(){
//		bottom_layout = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.friend_recommend_foot, null);
		adapter = new TestAdapter(this);
		listView.setAdapter(adapter);
//		listView.addfooterChild(bottom_layout);
//		listView.addFooterView(bottom_layout);
		int footerHeight = getResources().getDimensionPixelSize(R.dimen.footer_height);
		listView.setOnScrollListener(new QuickReturnListViewOnScrollListener(QuickReturnType.FOOTER, null, 0, bottom_layout, footerHeight).setCanSlideInIdleScrollState(true));
		listView.setLoadMoreable(true);
		
		PhoneInfoUtil util = PhoneInfoUtil.getInstance(this);
		String type = util.getNetType();
		LogUtils.e("type ="+type);
		
	}
	
	
	
	
}

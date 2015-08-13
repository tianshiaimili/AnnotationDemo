package com.example.annotationdemo;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.annotationdemo.CreamDetailAdapter.ViewHoldePosts;
import com.example.annotationdemo.CreamDetailAdapter.ViewHolderDesc;
import com.example.annotationdemo.adapter.PostsGalleryAdapter;
import com.example.annotationdemo.adapter.PostsGridViewAdapter;
import com.example.annotationdemo.bean.PostsCreamBean;
import com.example.annotationdemo.bean.TestPostsBean;
import com.example.annotationdemo.view.CustomGridView;
import com.example.annotationdemo.view.FreaturePostsCreamHeaderView;
import com.example.annotationdemo.view.FreaturePostsCreamHeaderView_;
import com.example.annotationdemo.view.RefleshListView;

/**
 * Created by test on 2015/7/2.
 */
@EActivity(R.layout.second_posts_list)
public class PersonFragmentNew extends Activity {

	private int[] imageId = new int[] { R.drawable.bg01, R.drawable.bg02,
			R.drawable.bg03, R.drawable.bg04, R.drawable.bg05 };

	@ViewById
	RefleshListView listView;

	Gallery gallery;

	PostsGalleryAdapter galleryAdapter;

	FreaturePostsCreamHeaderView headerView;

	List<TestPostsBean> list = new ArrayList<TestPostsBean>();

	@AfterInject
	void init() {

		for (int i = 0; i < 10; i++) {

			TestPostsBean bean = new TestPostsBean("name-" + i, "desc - " + i);
			list.add(bean);

		}

	}

	@AfterViews
	void initViews() {
		headerView = FreaturePostsCreamHeaderView_.build(this);
		headerView.setGalleryAdapter(new PostsGalleryAdapter(this));
		listView.addChild(headerView);
		listView.setAdapter(new CreamDetailAdapter(this, list));

	}

	class CreamDetailAdapter extends BaseAdapter {

		TreeSet mSeparatorsSet = new TreeSet();
		Context context;
		List<TestPostsBean> temPostsCreamBeans;
		List<TestPostsBean> gridviewList = new ArrayList<TestPostsBean>();
		int oldType = -1;
		int title_item_position;

		public CreamDetailAdapter(Context tempContext, List<TestPostsBean> list) {
			context = tempContext;
			temPostsCreamBeans = list;
			if(temPostsCreamBeans.size() > 5){
				gridviewList = temPostsCreamBeans.subList(5, temPostsCreamBeans.size());
			}
		}

		@Override
		public int getCount() {
			return temPostsCreamBeans.size() > 5 ?6:temPostsCreamBeans.size();
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder viewHolder;
			// PostsCreamBean bean = temPostsCreamBeans.get(position);
			int type = getItemViewType(position);
			if (convertView == null) {

				convertView = createViewByType(type);
				viewHolder = initHolder(convertView, type);
				convertView.setTag(viewHolder);
			} else {

				viewHolder = (ViewHolder) convertView.getTag();

			}
			if(position < 5){
				setData(viewHolder, temPostsCreamBeans, type, position);
			}else {
				setData(viewHolder, gridviewList, type, position);
			}
			oldType = type;

			return convertView;
		}

		@Override
		public int getItemViewType(int position) {

			int type = 0;
			if (position > 4) {
				type = 1;
			}
			return type;
		}

		@Override
		public int getViewTypeCount() {
			return 2;
		}

		private View createViewByType(int type) {
			View convertView = null;
			switch (type) {
			case 0:
				convertView = LayoutInflater.from(context).inflate(
						R.layout.feature_posts_item1, null);
				break;
			case 1:
				convertView = LayoutInflater.from(context).inflate(
						R.layout.feature_posts_item2, null);
				break;
			default:
				break;
			}
			return convertView;
		}

		private ViewHolder initHolder(View contentView, int type) {
			ViewHolder holder = null;
			switch (type) {
			case 0:
				holder = new ViewHolder1(contentView);
				return holder;
			case 1:
				holder = new ViewHolder2(contentView);
				return holder;
			default:
				// holder = new ViewHolder(contentView);
				break;
			}
			return holder;
		}

		public void setData(ViewHolder holder, List<TestPostsBean> beans,
				int type, int position) {

			int key = type;
			

			switch (key) {

			case 0:
				TestPostsBean bean = beans.get(position);
				ViewHolder1 viewHolder1 = (ViewHolder1) holder;
				if(position == 0){
					viewHolder1.head_title_layout.setVisibility(View.VISIBLE);
					viewHolder1.main_title.setText(bean.getMain_title());
					viewHolder1.title_desc.setText(bean.getDesc_title());
				}else {
					viewHolder1.head_title_layout.setVisibility(View.GONE);
					viewHolder1.main_title.setText(bean.getMain_title());
					viewHolder1.title_desc.setText(bean.getDesc_title());
				}
				break;
			case 1:
				
				ViewHolder2 holder2 = (ViewHolder2) holder;
				holder2.gridview.setAdapter(new PostsGridViewAdapter(context, gridviewList));
				
				break;

			default:
				break;
			}

		}

		public void setCreamDescItem() {

		}

		class ViewHolder {

		}

		class ViewHolder1 extends ViewHolder {

			TextView care_titles;
			TextView main_title;
			TextView title_desc;
			LinearLayout head_title_layout;

			public ViewHolder1(View view) {
				care_titles = (TextView) view.findViewById(R.id.care_titles);
				main_title = (TextView) view.findViewById(R.id.main_title);
				title_desc = (TextView) view.findViewById(R.id.title_desc);
				head_title_layout = (LinearLayout) view
						.findViewById(R.id.head_title_layout);

			}

		}

		class ViewHolder2 extends ViewHolder {

			CustomGridView gridview;

			public ViewHolder2(View view) {
				gridview = (CustomGridView) view.findViewById(R.id.gridview);

			}

		}

	}
}
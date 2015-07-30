package com.example.annotationdemo.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.annotationdemo.R;
import com.example.annotationdemo.bean.PostsNewBean;

public class GridViewAdapter extends BaseAdapter{

	private Context mContext;
	private List<PostsNewBean> listBeans;
	public GridViewAdapter(Context context,List<PostsNewBean> list){
		this.mContext = context;
		this.listBeans = list;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listBeans.get(0).getOtherRecommend().size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		
		ViewHolder holder = null;
		if(convertView == null){
			holder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(R.layout.other_option_gridview_item, null);
			holder.option = (TextView) convertView.findViewById(R.id.option);
			convertView.setTag(holder);
		}else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.option.setText(listBeans.get(0).getOtherRecommend().get(position));
		holder.option.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
				Toast.makeText(mContext, listBeans.get(0).getOtherRecommend().get(position), 300).show();
				
			}
		});
		
		return convertView;
	}

	
	class ViewHolder{
		
		TextView option;
		
	}
	
}

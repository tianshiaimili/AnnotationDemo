package com.example.annotationdemo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.annotationdemo.bean.PostsCreamBean;
import com.example.annotationdemo.view.ScaleImageView;

/**
 * 针对多个type的adapter
 * @author sundh
 *
 */
@EActivity(R.layout.jinghua_detail)
public class CreamDetail extends Activity {  
	   
	@ViewById
	ListView listview;
	
	@StringRes
	String poststest1;
	
	@StringRes
	String poststest2;
	
	CreamDetailAdapter adapter;
	
	List<PostsCreamBean> list = new ArrayList<PostsCreamBean>();
	
	@AfterInject
	void initVariable(){
		
		for(int i = 0;i<5;i++){
			
			PostsCreamBean bean = new PostsCreamBean();

			if(i >= 3){
				bean.setId("ID - "+i);
				bean.setDescTitle("这里是item2测试Title - "+i);
				bean.setDesc(poststest2);
				bean.setCommentCount(100+"");
				bean.setName("test"+i);
				bean.setAge(10+"");
				bean.setTime(new Date().toString());
				bean.setType(0+"");
			}else {
				bean.setId("ID - "+i);
				bean.setDescTitle("这里是item1测试Title - "+i);
				bean.setDesc(poststest1);
				bean.setType(1+"");
			}
			
			list.add(bean);
			
		}
		
	}
	
	
	@AfterViews
	void initViews(){
		
		adapter = new CreamDetailAdapter(this,list);
		
	}
	
	
}  


class CreamDetailAdapter extends BaseAdapter{

	TreeSet mSeparatorsSet = new TreeSet();  
	Context context;
	List<PostsCreamBean> temPostsCreamBeans;
	
	public CreamDetailAdapter(Context tempContext,List<PostsCreamBean> list){
		context = tempContext;
		temPostsCreamBeans = list;
	}
	
	@Override
	public int getCount() {
		return temPostsCreamBeans.size();
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
		
		int type = getItemViewType(position);
		if(convertView == null){
			
			convertView = createViewByType(type);
			viewHolder = initHolder(convertView, type);
			convertView.setTag(viewHolder);
		}else {
			
			viewHolder = (ViewHolder) convertView.getTag();
			
		}
		
		
		
		return null;
	}

	@Override
	public int getItemViewType(int position) {
		return Integer.valueOf(temPostsCreamBeans.get(position).getType());
	}

	@Override
	public int getViewTypeCount() {
		return 2;
	}
	
	
	   private View createViewByType(int type) {
	        View convertView = null;
	        switch (type) {
	            case 0:
	                convertView = LayoutInflater.from(context).inflate(R.layout.jianghu_adapter_desc_item, null);
	                break;
	            case 1:
	                convertView = LayoutInflater.from(context).inflate(R.layout.jianghua_postslist_lv_item2, null);
	                break;
//	            case 3:
//	                convertView = LayoutInflater.from(context).inflate(R.layout.postslist_lv_item2, null);
//	                LogUtil.e("error", "进去拉阿萨阿萨");
//	                break;
	            default:
//	                convertView = LayoutInflater.from(context).inflate(R.layout.postslist_lv_item, null);
	                break;
	        }
	        return convertView;
	    }
	
	   
	    private ViewHolder initHolder(View contentView, int type) {
	        ViewHolder holder = null;
	        switch (type) {
	            case 0:
	                holder = new ViewHolderDesc(contentView);
	                break;
	            case 1:
	                holder = new ViewHoldePosts(contentView);
	                break;
	            default:
//	                holder = new ViewHolder(contentView);
	                break;
	        }
	        return holder;
	    }
	   
	public void setData(View contentView,PostsCreamBean bean,int type,int position){
		
		int key = type;
		
		switch (key) {
		case 0:
			
			break;
			
		case 1:
			
			break;


		default:
			break;
		}
		
	}
	    
	
	public void setCreamDescItem(){
		
		
		
		
	}
	
	
	class ViewHolder{
		
		TextView adapter_item_title;
		TextView adapter_item_sub_title;
		LinearLayout title_layout;
		
		public ViewHolder(View view){
			adapter_item_title = (TextView) view.findViewById(R.id.adapter_item_title);
			adapter_item_sub_title = (TextView) view.findViewById(R.id.adapter_item_sub_title);
			title_layout = (LinearLayout) view.findViewById(R.id.title_layout);
			
		}
		
	}
	
	class ViewHolderDesc extends ViewHolder{
		
		TextView adapter_item_desc;
		TextView adapter_item_sub_desc;
		
		public ViewHolderDesc(View view){
			super(view);
			adapter_item_desc = (TextView) view.findViewById(R.id.adapter_item_desc);
			adapter_item_sub_desc = (TextView) view.findViewById(R.id.adapter_item_sub_desc);
		}
		
	}
	
	class ViewHoldePosts extends ViewHolder{
		
		ScaleImageView avatar_img;
		TextView author;
		TextView bb_age;
		TextView count;
		TextView time;
		ImageView title_ico;
		TextView title;
		TextView message;
		
		public ViewHoldePosts(View view){
			super(view);
			avatar_img = (ScaleImageView) view.findViewById(R.id.avatar_img);
			author = (TextView) view.findViewById(R.id.author);
			bb_age = (TextView) view.findViewById(R.id.bb_age);
			time = (TextView) view.findViewById(R.id.time);
			author = (TextView) view.findViewById(R.id.author);
			title_ico = (ImageView) view.findViewById(R.id.title_ico);
			title = (TextView) view.findViewById(R.id.title);
			message = (TextView) view.findViewById(R.id.message);
		}
		
	}
	
	
}
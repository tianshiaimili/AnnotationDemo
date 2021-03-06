package com.example.annotationdemo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.FragmentById;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ItemLongClick;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringArrayRes;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Gallery;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.annotationdemo.adapter.GalleryAdapter;
import com.example.annotationdemo.adapter.GridViewAdapter;
import com.example.annotationdemo.adapter.PersonListAdapter;
import com.example.annotationdemo.bean.Person;
import com.example.annotationdemo.bean.PostsNewBean;
import com.example.annotationdemo.fragment.MyFragment;
import com.example.annotationdemo.fragment.ThreeFragment_;
import com.example.annotationdemo.impl.InMemoryPersonFinder;
import com.example.annotationdemo.utils.LogUtils;
import com.example.annotationdemo.utils.PreferenceUtils;
import com.example.annotationdemo.view.CustomGridView;



/**
 * Created by test on 2015/7/2.
 */
@EActivity(R.layout.person_list)
public class PersonFragment extends Activity {
	
	private int[] imageId = new int[]{
			R.drawable.bg01,R.drawable.bg02,R.drawable.bg03,
			R.drawable.bg04,R.drawable.bg05
	};
	
	@ViewById
	ListView personList;

	Gallery gallery;
	
	GalleryAdapter galleryAdapter;
	
	@Bean
	InMemoryPersonFinder finder;

	@Bean
	PersonListAdapter adapter;

	private List<Person> list;

	@FragmentById(R.id.myFragment)
	MyFragment myFragment;

	@StringArrayRes(R.array.other_recommend)
	String[] others ;
	
    
    /**这是底部显示的tab item部分 ，这里主要是想滑动listview 实现隐藏*/
	@ViewById(R.id.bottom_layout)
    LinearLayout bottom_layout;
	private int buttomLayoutItemHeight;
	private TranslateAnimation anim;
	
	private LinearLayout foodViewLayout;
	private CustomGridView gridView;
	private GridViewAdapter gridViewAdapter;
	private List<PostsNewBean> postsNewBeans;

	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onCreate(savedInstanceState);
    	
    	foodViewLayout = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.footlayout, null);
    	gridView = (CustomGridView) foodViewLayout.findViewById(R.id.gridview);
    	gallery = (Gallery) foodViewLayout.findViewById(R.id.gallery);
    }
	
	@AfterViews
	void bindAdapter() {

		//
		list = new ArrayList<Person>();
		for (int i = 0; i < 20; i++) {

			Person person = new Person("ID" + i, "Item - " + i, "Name - " + i);
			// if(isFirstItem(person.getId(), this)){
			// list.add(0, person);
			// }else {
			//
			// list.add(person);
			// }

			list.add(person);
		}

		changeListOrder(list, this);

		galleryAdapter = new GalleryAdapter(this);
		gallery.setAdapter(galleryAdapter);
		
		//
		setFVAdapterData();
		gridViewAdapter = new GridViewAdapter(this, postsNewBeans);
		gridView.setAdapter(gridViewAdapter);
		//
		personList.addFooterView(foodViewLayout);
//		adapter.setData(list);
		personList.setAdapter(adapter);
		personList.setOnScrollListener(new OnScrollListener() {
			
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				
				if(scrollState == OnScrollListener.SCROLL_STATE_IDLE){
					bottom_layout.setVisibility(View.VISIBLE);
					anim = new TranslateAnimation(0, 0, bottom_layout.getHeight(),
							0);
					anim.setFillAfter(true);
					anim.setDuration(500);
					anim.setAnimationListener(new AnimationListener() {
						
						@Override
						public void onAnimationStart(Animation animation) {
							
						}
						
						@Override
						public void onAnimationRepeat(Animation animation) {
							
						}
						
						@Override
						public void onAnimationEnd(Animation animation) {
						}
					});
					bottom_layout.startAnimation(anim);
				}else if(scrollState == OnScrollListener.SCROLL_STATE_FLING){
					LogUtils.d("SCROLL_STATE_TOUCH_SCROLL--");
					bottom_layout.setVisibility(View.VISIBLE);
					anim = new TranslateAnimation(0, 0, 0,
							bottom_layout.getHeight());
					anim.setFillAfter(true);
					anim.setDuration(500);
					anim.setAnimationListener(new AnimationListener() {
						
						@Override
						public void onAnimationStart(Animation animation) {
							
						}
						
						@Override
						public void onAnimationRepeat(Animation animation) {
							
						}
						
						@Override
						public void onAnimationEnd(Animation animation) {
						}
					});
					bottom_layout.startAnimation(anim);
					
				}
			}
			
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				
			}
		});

	}

	
	
	public void setFVAdapterData(){
		
		postsNewBeans = new ArrayList<PostsNewBean>();
		PostsNewBean bean = new PostsNewBean();
		bean.setOtherRecommend(Arrays.asList(others));
		postsNewBeans.add(bean);
		
	}
	
	@ItemClick
	void personListItemClicked(Person person) {
		// makeText(this, person.firstName + " " + person.lastName,
		// LENGTH_SHORT).show();
		Toast.makeText(this,
				person.getFirstName() + " " + person.getLastName(),
				Toast.LENGTH_SHORT).show();
		repleaceFragment();
	}

	@ItemLongClick
	void personListItemLongClicked(final int position) {
		LogUtils.i("------------------personal = "
				+ list.get(position).getFirstName());
		String[] items = { "置顶", "退出" };
		new AlertDialog.Builder(this).setTitle("lalla")
				.setItems(items, new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						LogUtils.d("which --" + which);

						if (which == 0) {

							Person person = list.get(position);
							list.remove(position);
							list.add(0, person);
							PreferenceUtils.setUserSharePref(
									PersonFragment.this, "firstItem",
									person.getId());
							adapter.notifyDataSetChanged();
						} else if (which == 1) {

							list.remove(position);
							adapter.notifyDataSetChanged();

						}

					}
				}).show();

		// //下面使用视图

		// final AlertDialog.Builder builder = new AlertDialog.Builder(this);
		//
		// View view =
		// LayoutInflater.from(getApplicationContext()).inflate(R.layout.change_position,
		// null);
		// TextView zhiding = (TextView) view.findViewById(R.id.zhiding);
		// TextView tuichu = (TextView) view.findViewById(R.id.tuichu);
		// zhiding.setOnClickListener(new View.OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// Person person = list.get(position);
		// list.remove(position);
		// list.add(0, person);
		// PreferenceUtils.setUserSharePref(PersonFragment.this, "firstItem",
		// person.getId());
		// adapter.notifyDataSetChanged();
		// }
		//
		// });
		//
		// tuichu.setOnClickListener(new View.OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// list.remove(position);
		// adapter.notifyDataSetChanged();
		// }
		//
		// });
		//
		//
		// // new AlertDialog.Builder(this)
		// builder
		// .setTitle("lalla")
		// .setView(view).show();

		// ///下面是另外的一种方式
		// final Dialog dialog = new Dialog(this, R.style.MyDialog);
		//
		// View view = LayoutInflater.from(getApplicationContext()).inflate(
		// R.layout.change_position, null);
		// TextView zhiding = (TextView) view.findViewById(R.id.zhiding);
		// TextView tuichu = (TextView) view.findViewById(R.id.tuichu);
		// zhiding.setOnClickListener(new View.OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// Person person = list.get(position);
		// list.remove(position);
		// list.add(0, person);
		// PreferenceUtils.setUserSharePref(PersonFragment.this,
		// "firstItem", person.getId());
		// // personList.setSelection(0);
		// personList.smoothScrollToPosition(0);
		// adapter.notifyDataSetChanged();
		// dialog.dismiss();
		// }
		//
		// });
		//
		// tuichu.setOnClickListener(new View.OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// list.remove(position);
		// adapter.notifyDataSetChanged();
		// dialog.dismiss();
		// }
		//
		// });
		//
		// // dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// // dialog.setContentView(view, params)
		// dialog.setContentView(view);
		// // dialog.
		// Window dialogWindow = dialog.getWindow();
		// // WindowManager.LayoutParams lp = dialogWindow.getAttributes();
		// // dialogWindow.setGravity(Gravity.LEFT | Gravity.TOP);
		// // dialogWindow.setGravity(Gravity.CENTER_VERTICAL);
		// // WindowManager m = getWindowManager();
		// DisplayMetrics d = getResources().getDisplayMetrics(); // 获取屏幕宽、高用
		// WindowManager.LayoutParams p = dialogWindow.getAttributes(); //
		// 获取对话框当前的参数值
		// p.height = (int) (d.heightPixels * 0.3); // 高度设置为屏幕的0.6
		// p.width = (int) (d.widthPixels * 0.5); // 宽度设置为屏幕的0.95
		// dialogWindow.setAttributes(p);
		//
		// dialog.show();

	}

	private void repleaceFragment() {

		FragmentManager manager = getFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		transaction.replace(
				R.id.myFragment,
				new ThreeFragment_.FragmentBuilder_()
						.anotherStringArgument("From PersonFragment.")
						.myMessage("杩欎釜鏄缃瓼ragmentArg鐨勫弬鏁颁紶閫�").build());
		transaction.commit();
	}

	public void changePosition(List<Person> list, int position) {
		if (list.size() > 0) {
			Person person = list.get(position);
			list.remove(position);
			list.add(0, person);
		}

	}

	public boolean isFirstItem(String id, Context context) {

		return id
				.equals(PreferenceUtils.getUserSharePref(context, "firstItem"));
	}

	public void changeListOrder(List<Person> list, Context context) {
		if (list.size() <= 0)
			return;
		int tempIndex;
		boolean isRemove = false;
		Person person = null;
		for (Iterator<Person> iterator = list.iterator(); iterator.hasNext();) {
			// LogUtils.d("--"+person.getFirstName());
			person = iterator.next();
			if (person.getId().equals(
					PreferenceUtils.getUserSharePref(context, "firstItem"))) {
				LogUtils.d("--" + person.getFirstName());
				// list.remove(person);
				iterator.remove();
				isRemove = true;
				break;
			}

		}

		if (isRemove) {
			list.add(0, person);
		}

	}

}

package com.example.annotationdemo.bean;

import java.io.Serializable;
import java.util.List;

public class PostsNewBean implements Serializable{

	private String id;
	private List<String> otherRecommend;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<String> getOtherRecommend() {
		return otherRecommend;
	}
	public void setOtherRecommend(List<String> otherRecommend) {
		this.otherRecommend = otherRecommend;
	}
	
}

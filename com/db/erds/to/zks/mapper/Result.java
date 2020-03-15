package com.db.erds.to.zks.mapper;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {
	@SerializedName("todos")
	@Expose
	private List<Todo> todos = new ArrayList<>();
	
	public List<Todo> getTodos() {
		return todos;
	}

	public void setTodos(List<Todo> todos) {
		this.todos = todos;
	}
	
	

}

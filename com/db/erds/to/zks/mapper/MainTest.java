package com.db.erds.to.zks.mapper;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;

public class MainTest {
	public static void main(String[] args) {
		Gson gson = new Gson();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader("test.json"));
			Result result = gson.fromJson(reader, Result.class);
			for(Todo todo: result.getTodos()) {
				System.out.println(todo.getTitle()+":"+todo.getId()+":"+todo.getCompleted());
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally {
			if(reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

}

package com.yedam.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MapExample {
	public static void main(String[] args) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("산초", 80);
		map.put("산톨", 81);
		map.put("몽돌", 82);
		
		Map<String, Integer> map2 = new HashMap<String, Integer>();
		map2.put("산초", 82);
		map2.put("산톨", 83);
		map2.put("몽돌", 84);
		
		List<Map<String, Integer>> list = new ArrayList<Map<String,Integer>>();
		list.add(map);
		list.add(map2);
		
		Gson gson = new GsonBuilder().create();
		System.out.println(gson.toJson(list));
		
//		System.out.println(map.get("산초")); // 겟이 키값을 넣어주면 밸류를 리턴
//		System.out.println(map.get("산톨"));
	}
}

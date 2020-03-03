package com.news.controller;

public class Test {

	public static void main(String[] args) {
		
		try {
			System.out.println("1");
			int a = 1/0;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("2");
		}
	}
}

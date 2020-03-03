package com.news.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import java.util.Set;

public class Study {
	
	public static void main(String[] args) throws IOException {
		//TestList();
		//tetsIo();
		//upData("a.txt","b.txt");
		
		//File file = new File("C:\\");
		//printJava(file);
		File src = new File("C:\\360WiFi");
		File desc = new File("C:\\XSBDownload");
		copyFile(src,desc);
	}

	static void TestList(){
		
		/*List<Integer> lsit = new ArrayList<>();
		lsit.add(2);*/
		Set<Integer> sett = new HashSet<>();
		sett.add(1);
		sett.add(2);
		sett.add(3);
		sett.add(4);
		Iterator<Integer> iterator = sett.iterator();
		while(iterator.hasNext()){
			Integer next = iterator.next();
			System.out.println(next);
		}
		System.out.println(sett.size());
	System.out.println("------------------------");	
		Map<Integer,String> map = new HashMap<>();
		 map.put(1, "a");
		        map.put(2, "b");
		        map.put(3, "ab");
		        map.put(4, "ab");
		       map.put(4, "ab");// 和上面相同 ， 会自己筛选
		     for(Integer in:map.keySet()){
		    	 System.out.println(in+"---"+map.get(in));
		     }
		     Iterator<Entry<Integer, String>> iterator2 = map.entrySet().iterator();
		     while(iterator2.hasNext()){
		    	 Entry<Integer, String> next = iterator2.next();
		    	 System.out.println(next.getKey()+"--"+next.getValue());
		     }
		     
	}
	
	static void tetsIo(){
		try {
		/*	FileWriter fw = new FileWriter("a.txt");
			fw.write("这是我的测试");
			fw.close();
			*/
			char[]a =new char[5];
			int ch ;
			FileReader fr = new FileReader("LogAnno.java");
			while((ch=fr.read(a))!=-1){
				System.out.print(new String(a,0,ch));
			}
			fr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("出现异常");
		}
	}
	
	static void copyFile(String fileName,String copyFileName){
		try {
			FileReader fr = new FileReader(fileName);
			FileWriter fw = new FileWriter(copyFileName);
			char[] ch = new char[1024];
			int len;
			while((len=fr.read(ch))!=-1){
				fw.write(new String(ch, 0, len));
			}
			fw.close();
			fr.close();
			System.out.println("copy over");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("copy filed");
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("copy filed");
		}
		/**
		 * desc:把文本书的数据读取到集合中 然后便利修改第几个数据的，然后写入到新的文件
		 */
	}
	
	static void upData(String fielname,String targetName) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(fielname));
		BufferedWriter bw = new BufferedWriter(new FileWriter(targetName));
		List<String> fileListWord = new ArrayList<>();
		int len;
		char[] ch = new char[1024];
		String wo;
		
	/*	while((wo=br.readLine())!=null){
			fileListWord.add(wo);
		}
		for (String c : fileListWord) {
			System.out.println(c);
		}*/
		while((len=br.read(ch))!=-1){
			bw.write(ch, 0, len);
		}
		bw.close();
		br.close();
	}
	
	
	
	static void printJava(File file){
		if(file.isDirectory()){
			File[] listFiles = file.listFiles();
			for (File file2 : listFiles) {
				if(file2.isFile()){
					if(file2.getName().endsWith(".java")){
						System.out.println(file2.getName());
					}
				}else{
					printJava(file2);
				}
			}
			
		}
	}
	
	
	static void copyFile(File src,File desc) throws IOException{
		if(src.isDirectory()&&desc.isDirectory()){
			
			File newfile = new File(desc,src.getName());
			if(!newfile.exists()){
				newfile.mkdir();
			}
			
			File[] listFiles = src.listFiles();
			for (File file : listFiles) {
				if(file.isFile()){
				
				FileInputStream fi = new FileInputStream(file);
				FileOutputStream fo = new FileOutputStream(new File(newfile,file.getName()));
				byte[] b = new byte[1024];
				int len;
				while((len=fi.read(b))!=-1){
					fo.write(b, 0, len);
				}
				fo.close();
				fi.close();
				}else if(file.isDirectory()){
					File f = new File(desc,src.getName());
					copyFile(file, f);
				}
			}
		}
		
		
	}
}

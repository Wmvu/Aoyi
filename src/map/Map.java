package map;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Map {
 ArrayList<String>list = new ArrayList<>();
 public int map[][] = null;

 public void 查看地图配置() throws Exception {
	int [][]地图配置 = readMap();
	for (int i =0;i<地图配置.length;i++) {
		for(int n = 0;n<地图配置[i].length;n++) {
			System.out.print(地图配置[i][n]+" ");
		}
		System.out.println("");
	}
	
 }
 public int[][] readMap() throws Exception {
	 FileInputStream readmap = new FileInputStream("src/map/config.txt");
	 BufferedReader watch = new BufferedReader(new InputStreamReader(readmap));
	 
	 String value = watch.readLine();
	 while(value!=null) {
		 list.add(value);
		 value = watch.readLine();
		 
	 }
	 watch.close();
	 readmap.close();
	 int dsh = list.size();
	 int dsl = 0;
	 String pdl = list.get(0);
	 String[] hql=pdl.split(",");
	 dsl=hql.length;
 map = new int[dsh][dsl];
 	for(int r = 0;r<list.size();r++) {
 		String w = list.get(r);
 		String[] a = w.split(",");
 		for (int b = 0;b<a.length;b++) {
 			map[r][b] = Integer.parseInt(a[b]);
 		}		
 	}
 	return map;
 }
}

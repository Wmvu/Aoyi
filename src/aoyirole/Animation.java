package aoyirole;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Animation implements Runnable{//动画管理器

	private int index = 0;
	private String temp;
	private Map<String,Integer> Maxlen;//根据添加进来的动作集合创建该集合对应的长度表
	private Map<String,ArrayList<BufferedImage>> animap = null;//动作集合地图表
	private boolean boolta=true;//是否允许改变动画
	private int tl;//当前动画不需要改变的时间*200ms
	
	public Animation(){
		this.animap = new HashMap<String, ArrayList<BufferedImage>>();//如果无传递Map参数则自己创建
		this.Maxlen = new HashMap<>();
	}
	
	public Animation(Map<String,ArrayList<BufferedImage>> animap){
		this.animap = animap;
		this.Maxlen = new HashMap<>();
		for(int i=0;i<animap.size();i++) {
			
		}
		Iterator<Map.Entry<String,ArrayList<BufferedImage>>> iterator = animap.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<java.lang.String, java.util.ArrayList<java.awt.image.BufferedImage>> entry = (Map.Entry<java.lang.String, java.util.ArrayList<java.awt.image.BufferedImage>>) iterator
					.next();
			Maxlen.put(entry.getKey(),entry.getValue().size());
		}
	}
	
	public void addBuffImageList (String Key,ArrayList<BufferedImage> BuffimageList) {
		this.animap.put(Key, BuffimageList);
		this.Maxlen.put(Key, BuffimageList.size());
	}
	
	public BufferedImage getAnilist(String str) {
		if(this.temp!=str & this.boolta) {
			this.index=0;
			this.temp = str;
		}
//		if(!boolta)str = this.temp;
		if (this.Maxlen.get(this.temp)!=0) {
			return this.animap.get(this.temp).get(this.index%this.Maxlen.get(this.temp));
		}else {
			return this.animap.get(this.temp).get(0);
		}
		
	}
	public void setnoAim(String str,boolean bool,int t) {
			this.index=0;
			this.temp = str;
			this.boolta = bool;
			this.tl = t;
	}
	private void timebup() {
		if(!this.boolta) {
			this.tl --;
			if(this.tl <= 0 )
				this.boolta =true;
		}
		
	}
	@Override
	public void run() {
		while (true) {
			timebup();
			try {
			this.index++;
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		}

	}

}

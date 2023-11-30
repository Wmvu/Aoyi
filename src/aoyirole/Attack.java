package aoyirole;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import map.Obstacle;
import teamandenemy.Enemy;
import com.aoyi.Tools;

public abstract class Attack {
	public String name,rolename;
	public int x,y;
	public int width,height;
	public int seep = 10,distance =30,sked =0;
	public int trun;
	public double drct;
	public ArrayList<BufferedImage> img;
	public int ow,oh;
	public int rx,ry;
	public boolean penetrate =false;
	public boolean is_surv = true;
	public ArrayList<Obstacle> isq = new ArrayList<Obstacle>();
	public Attack(int x,int y,int width,int height,int turn){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.trun = turn;
		img = new ArrayList<BufferedImage>();
	}
	public BufferedImage getimg() {
		return img.get(0);
	}
	public void tebh(Graphics2D g2) {
	}
	public void atm(Obstacle qg) {
//		qg.hp-=5;
//		if(!this.isq.contains(qg)) gj.isq.add(qg);
	}
	public void dismove() {
		//销毁方法
		this.is_surv = false;
	}
	public void hurt() {
		
	}
	public void hurt(Enemy e) {
		
	}
}

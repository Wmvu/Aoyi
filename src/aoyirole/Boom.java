package aoyirole;

import java.awt.Color;
import java.awt.Graphics2D;

public class Boom extends Attack{
	public double pyzx ,pyzy;
	double 时间偏移x = 0,时间偏移y = 0;
	double sjpydx = 0,sjpydy = 0;
	int x1,y1;
	int []xz,yz;
	GameRole role;
	public Boom(int x, int y, int width, int height, int turn,GameRole role) {
		super(x, y, width, height, turn);
		this.x = x;
		this.y = y;
		x1 = x;
		y1 = y;
		this.width = width;
		this.height = height;
		this.trun = turn;
		seep = 20;
		distance =15;
		xz = new int[this.distance];yz = new int[this.distance];
		this.role = role;
//		shootat();
	}

	@Override
	public void tebh(Graphics2D g2) {
//			this.sjpydx=(int)this.seep*this.pyzx;
//			this.sjpydy=(int)this.seep*this.pyzy;

//	this.时间偏移x=  this.pyzx*this.seep;
//		this.时间偏移y=  this.pyzy*this.seep;
		
			Color c =g2.getColor();
			g2.setColor(Color.red);
//			g2.fillOval(this.x+=sjpydx+时间偏移x, this.y+=sjpydy+时间偏移y, this.width, this.height);this.sked+=1;sjpydx = 0;
			this.rx+=xz[this.sked];this.ry+=yz[this.sked];
			this.x = this.rx+role.getTx();this.y = this.ry+role.getTy();
			g2.fillOval(this.x,this.y , this.width, this.height);
//			g2.fillOval(this.x1-=(this.时间偏移x),this.y1-=(this.时间偏移y), this.width, this.height);
//			g2.drawLine(x1, y1, x1+(int)(this.时间偏移x)*100, y1+(int)(this.时间偏移y)*100);
//			g2.drawLine(x1+(int)(this.pyzx*800), y1+(int)(this.pyzy*800), x1, y1);
			g2.setColor(c);
this.sked+=1;
//			if(this.sjpydx/1!=0)this.sjpydx-=(int)(this.sjpydx);
//			if(this.sjpydy/1!=0)this.sjpydy-=(int)(this.sjpydy);
//	g2.drawImage(img, this.x+=sjpydx+时间偏移x, this.y+=sjpydy+时间偏移y,null);
	}
	public void shootat() {
		this.时间偏移x=  this.pyzx;
		this.时间偏移y=  this.pyzy;
		for (int i = 0; i < this.distance; i++) {
			xz[i]=(int)(this.时间偏移x*this.seep*(i+1))-(int)(this.时间偏移x*this.seep*i);
			yz[i]=(int)(this.时间偏移y*this.seep*(i+1))-(int)(this.时间偏移y*this.seep*i);
		}
		for (this.sked = 0; this.sked < 2; this.sked++) {
			this.x+=xz[this.sked];
			this.y+=yz[this.sked];
		}
	}
	
}

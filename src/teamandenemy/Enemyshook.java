package teamandenemy;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.aoyi.Aoyi;
import com.aoyi.Tools;

public class Enemyshook extends Enemy{
	private Enemy host;
//	private String img;
	public int x,y;
//	public String name;
	private double turn;
	private int seep;
	double rx,ry,px,py;
	BufferedImage imge;
	 public Enemyshook (int x,int y,int width,int height,Image image) {
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
			this.img = image;
//			fristmain();
	 }
	public Enemyshook(int x, int y,  String turn,Image image,Enemy enemy) {
		this.host = enemy;
		this.x = x;
		this.y = y;
		fristmain();
	}
	public Enemyshook(Integer x, Integer y, Double turn, Integer seep) {
		fristmain();
	}
	public Enemyshook(Image img, Integer valueOf, Integer valueOf2, Double valueOf3, Integer valueOf4) {
		this.img =img;
		this.rx =valueOf;
		this.ry=valueOf2;
		this.turn=valueOf3;
		this.seep = valueOf4;
		fristmain();
	}
	public int getX() {
		return 0;
		
	}
	public int getY() {
		return 0;
		
	}
	public void fristmain() {
		this.px =Math.cos(Math.toRadians(this.turn));

		this.py =Math.sin(Math.toRadians(this.turn));
		
		imge = Tools.rotateImagee(Tools.ro(this.img),(int)(this.turn));
	}
	public void getdraw() {
		this.rx +=  this.px * this.seep;
		this.ry +=  this.py * this.seep;
	}
	public void paint(Graphics2D g,Aoyi ao) {
		this.getdraw();
		
		g.drawImage(imge, (int)this.rx + ao.back.x, (int)this.ry + ao.back.y, null);
	}
//	public BufferedImage getimage() {
//		return 
//	}
}

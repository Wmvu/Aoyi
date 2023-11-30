package teamandenemy;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import com.sun.javafx.geom.Rectangle;

import com.aoyi.Aoyi;

public abstract class Enemy {
 public int width,height;
 public int x,y;
 public Image img;
 public int Hp,Lv;
 public String name;
 public int pagwidth=60,pagheight=60,pagx=40,pagy=40;
 public boolean flag = true,disdec;
 public int getHp() {
	return Hp;
}
public void setHp(int hp) {
	Hp = hp;
}
public int getLv() {
	return Lv;
}
public void setLv(int lv) {
	Lv = lv;
}
public String getAnim() {
	return anim;
}
public void setAnim(String anim) {
	this.anim = anim;
}
public String anim;
public int maxHp;
public String tp;
public String zs;
 public  BufferedImage GetImage() {
	return null;
}
 public int GetX() {
	return this.x;
}
 public int GetY() {
	return this.y;
}
 public String GetName() {
	return this.name;
}
 public void setX(int x) {
		this.x = x;
	}
 public void setY(int y) {
		this.y = y;
	}
 public Rectangle getRect() {
	 return new Rectangle(this.x + this.pagx, this.y+this.pagy, this.pagwidth, this.pagheight);
 }
	public void fristmain() {
	}
	public void getdraw() {
	}
	public void paint(Graphics2D g, Aoyi aoyi) {
	}
  public boolean getdisdec() {
	  return this.disdec;
  }
  public void setdicdec(boolean f) {
	  this.disdec = f;
  }
}

package map;

import java.awt.Image;

public abstract class Obstacle {
public int x,y,ix,iy;
public int width,height;
public Image img;
public Obstacle(int x,int y,int width,int height,Image img) {
	this.x = x;
	this.y = y;
	this.width = width;
	this.height = height;
	this.img = img;
	this.ix =x;this.iy = y;
}
public int getx() {
	return this.ix;
}
public int gety() {
	return this.iy;
}
public void getGameMapOrigin(int sx,int sy) {
	this.x = this.ix + sx;
	this.y = this.iy + sy;
}
}

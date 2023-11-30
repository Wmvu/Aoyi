package com.aoyi;

import java.awt.Color;
import java.awt.Graphics;

public class Loading {
	static int x1=150,y1=400,width=600,height=20;
	static Color co;
	static Graphics gr;
	private static int currentProgress;
	public Loading() {

	}
	public static void setBounds(int x,int y,int w,int h) {
		x1 = x;
		y1 = y;
		width = w;
		height = h;
	}
	public static void setColor(Color color) {
		co = color;
	}
	public static void setProgress(int current){
		currentProgress =current;
		draw();
	}
	public static void setGraphics(Graphics g){
		gr=g;
		setProgress(0);
	}
	static void draw() {
		gr.clearRect(0, 0, 1000, 1000);
		gr.drawRect(x1, y1, width, height);
		gr.fillRect(x1, y1, currentProgress*(width/100), height);
		gr.drawString(currentProgress+"%", x1+width/2, y1);
		
	}
}

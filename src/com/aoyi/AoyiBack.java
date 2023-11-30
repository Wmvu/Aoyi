package com.aoyi;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class AoyiBack {
	public int x=0,y=0;
	public int ox=0,oy=0;
	public Image img;
	public BufferedImage back;
	AoyiBack() {
		WordBack();
	}
	public void WordBack() {
		try {
			img = ImageIO.read(new BufferedInputStream(new FileInputStream("src/image/人机地图.jpg")));
			back = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_BYTE_GRAY);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public int getWidth() {
		return back.getWidth();
	}
	public int getHeight() {
		return back.getHeight();
	}
}

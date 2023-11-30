package com.aoyi;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Transparency;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.imageio.spi.ImageReaderSpi;
import javax.imageio.stream.FileImageInputStream;

import com.sun.imageio.plugins.gif.GIFImageReader;
import com.sun.imageio.plugins.gif.GIFImageReaderSpi;

import aoyirole.Attack;

public class Tools {
	public static long starttime,endtime;
/*--------------------------------
 *## 画直线带箭头
 */
		public void Arrow(Graphics2D g2,int x1,int y1,int x2,int y2) {
			g2.setPaint(Color.yellow);
			drawAL(x1, y1, x2, y2, g2);// 这里x1, y1, x2, y2必须要声明并且初始化，而具体声明的位置和初始化的值
		}

		public static void drawAL(int sx, int sy, int ex, int ey, Graphics2D g2)
		{
	 
			double H = 10; // 箭头高度
			double L = 4; // 底边的一半
			int x3 = 0;
			int y3 = 0;
			int x4 = 0;
			int y4 = 0;
			double awrad = Math.atan(L / H); // 箭头角度
			double arraow_len = Math.sqrt(L * L + H * H); // 箭头的长度
			double[] arrXY_1 = rotateVec(ex - sx, ey - sy, awrad, true, arraow_len);
			double[] arrXY_2 = rotateVec(ex - sx, ey - sy, -awrad, true, arraow_len);
			double x_3 = ex - arrXY_1[0]; // (x3,y3)是第一端点
			double y_3 = ey - arrXY_1[1];
			double x_4 = ex - arrXY_2[0]; // (x4,y4)是第二端点
			double y_4 = ey - arrXY_2[1];
	 
			Double X3 = new Double(x_3);
			x3 = X3.intValue();
			Double Y3 = new Double(y_3);
			y3 = Y3.intValue();
			Double X4 = new Double(x_4);
			x4 = X4.intValue();
			Double Y4 = new Double(y_4);
			y4 = Y4.intValue();
			// 画线
			g2.drawLine(sx, sy, ex, ey);
			//
			GeneralPath triangle = new GeneralPath();
			triangle.moveTo(ex, ey);
			triangle.lineTo(x3, y3);
			triangle.lineTo(x4, y4);
			triangle.closePath();
			//实心箭头
			g2.fill(triangle);
			//非实心箭头
			//g2.draw(triangle);
	 
		}
	 
		// 计算
		public static double[] rotateVec(int px, int py, double ang,
				boolean isChLen, double newLen) {
	 
			double mathstr[] = new double[2];
			// 矢量旋转函数，参数含义分别是x分量、y分量、旋转角、是否改变长度、新长度
			double vx = px * Math.cos(ang) - py * Math.sin(ang);
			double vy = px * Math.sin(ang) + py * Math.cos(ang);
			if (isChLen) {
				double d = Math.sqrt(vx * vx + vy * vy);
				vx = vx / d * newLen;
				vy = vy / d * newLen;
				mathstr[0] = vx;
				mathstr[1] = vy;
			}
			return mathstr;
		}
		//取角度的计算
		static float get_lines_arctan(float line_1_k, float line_2_k, int aaa)
		{
		    if (aaa == 0)
		    {
		        float tan_k = 0; //直线夹角正切值
		        float lines_arctan;//直线斜率的反正切值
		        tan_k = (line_2_k - line_1_k) / (1 + line_2_k*line_1_k); //求直线夹角的公式
		        lines_arctan = (float) Math.atan(tan_k);
		        return lines_arctan;
		    }
		    else
		    { 
		        float tan_k = 0; //直线夹角正切值
		        float lines_arctan;//直线斜率的反正切值
		        tan_k = (line_2_k - line_1_k) / (1 + line_2_k*line_1_k); //求直线夹角的公式
		        lines_arctan = (float) (Math.atan(tan_k)* 180.0 / 3.1415926);

		        return lines_arctan;
		    }
		}

		public static float countK(float px,float py,float mx,float my){
			return (my-py)/(mx-px);
			}
		/*--------------------------------
		 *## 取角度
		 */
			public static double 取角度(int px,int py,int mx,int my){//获得人物中心和鼠标坐标连线，与y轴正半轴之间的夹角
		      float w =  countK((float)px,(float)py,(float)mx,(float)my);
		  System.out.println(Math.toDegrees(w));
		  double drct =(double)get_lines_arctan((float) 0.00,w,1);
		  if(px<mx && py>my) {
			  
			  drct = drct +180;
		  }
		 if(px<mx && py<my) {
			  
			  drct = drct +180;
		  }
		
		 if(px>mx && py<my) {
			  
			  drct = drct +360;
		 }
		 if(px==mx && py<my) {
			  
			  drct = 270;
		  }
		 if(px==mx && py>my) {
			  
			  drct = 90;
		  }
		 if(px<mx && py==my) {
			  
			  drct = 180;
		  }
		 System.out.println("计算的值："+drct);
//		        return(double) Math.atan2(x, y) * (180/Math.PI) ;
		        return drct;
		        }
			
			
			public static BufferedImage ro(Image image) {
				int w = image.getWidth(null);
				int h = image.getHeight(null);
				BufferedImage bImage = new BufferedImage(w, h,
				BufferedImage.TYPE_INT_RGB);
				Graphics2D g2d = bImage.createGraphics();
				
				bImage = g2d.getDeviceConfiguration().createCompatibleImage(w,h, Transparency.TRANSLUCENT); 
//				g2d.dispose(); 
				g2d = bImage.createGraphics(); 
				g2d.drawImage(image,0, 0, null);
				g2d.dispose();
				return bImage;
			}
			/**
			 * 
			 * @param bufferedImage
			 *            图片
			 * @param angel
			 *            旋转角度
			 * @return
			 */
			public static BufferedImage rotateImagee(BufferedImage bufferedImage, int angel) {
				if (bufferedImage == null) {
					return null;
				}
				if (angel < 0) {
					// 将负数角度，纠正为正数角度
					angel = angel + 360;
				}
				
				int imageWidth = bufferedImage.getWidth(null);
				int imageHeight = bufferedImage.getHeight(null);
				// 计算重新绘制图片的尺寸
				Rectangle rectangle = calculatorRotatedSize(new Rectangle(new Dimension(imageWidth, imageHeight)), angel);
			    // 获取原始图片的透明度
				int type = bufferedImage.getColorModel().getTransparency();
				BufferedImage newImage = null;
				newImage = new BufferedImage(rectangle.width, rectangle.height, type);
				Graphics2D graphics = newImage.createGraphics();
				// 平移位置
				graphics.translate((rectangle.width - imageWidth) / 2, (rectangle.height - imageHeight) / 2);
				// 旋转角度
				graphics.rotate(Math.toRadians(angel), imageWidth / 2, imageHeight / 2);
				// 绘图
				graphics.drawImage(bufferedImage, null, null);
				return newImage;
			}

			/**
			 * 计算旋转后的尺寸
			 * 
			 * @param src
			 * @param angel
			 * @return
			 */
			public static Rectangle calculatorRotatedSize(Rectangle src, int angel) {
				if (angel >= 90) {
					if (angel / 90 % 2 == 1) {
						int temp = src.height;
						src.height = src.width;
						src.width = temp;
					}
					angel = angel % 90;
				}
//				System.out.println(angel);
				double r = Math.sqrt(src.height * src.height + src.width * src.width) / 2;
				double len = 2 * Math.sin(Math.toRadians(angel) / 2) * r;
				double angel_alpha = (Math.PI - Math.toRadians(angel)) / 2;
				double angel_dalta_width = Math.atan((double) src.height / src.width);
				double angel_dalta_height = Math.atan((double) src.width / src.height);

				int len_dalta_width = (int) (len * Math.cos(Math.PI - angel_alpha - angel_dalta_width));
				int len_dalta_height = (int) (len * Math.cos(Math.PI - angel_alpha - angel_dalta_height));
				int des_width = src.width + len_dalta_width * 2;
				int des_height = src.height + len_dalta_height * 2;
				return new java.awt.Rectangle(new Dimension(des_width, des_height));
			}
			public void ropatan(BufferedImage img,Attack gj) {
				int gx=gj.x-img.getWidth()/2;
				int gy =gj.y- img.getHeight()/2;
				gj.x =gx;gj.y=gy;
			}
			public static void startcalc() {
				starttime = System.nanoTime();
			}
			public static void endcalctime() {
				endtime = System.nanoTime();
				System.out.println("本次运行时间："+(endtime-starttime));
			}
			
			public static ArrayList<BufferedImage> getBuffImageList(String name){
				FileImageInputStream in = null;
				ArrayList<BufferedImage> imagelist = new ArrayList<BufferedImage>();
				try {
				    in = new FileImageInputStream(new File(name));
				    ImageReaderSpi readerSpi = new GIFImageReaderSpi();
				    GIFImageReader gifReader = (GIFImageReader) readerSpi.createReaderInstance();
				    gifReader.setInput(in);
				    int num = gifReader.getNumImages(true);System.out.println(num);
				    if (num!=0) {
				    	for (int i = 0; i < num; i++) {
//				              读取读取帧的图片
				         imagelist.add(gifReader.read(i));
				        }
					}else {
						imagelist.add(ImageIO.read(new File(name)));
					}
			}catch (Exception e) {
				System.out.println(e);
				try {
					imagelist.add(ImageIO.read(new File("Null")));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
				return imagelist;
			}
			
	public static ArrayList<BufferedImage> getImageListPNG(String name,int l){
		ArrayList<BufferedImage> Imagelist = new ArrayList<BufferedImage>();
		String ast[] = name.split("_");
		String ast2 = ast[ast.length-1];
		String i = ast2.substring(0, ast2.indexOf("."));
		int ii = Integer.valueOf(i);
		
		try {
			for(;Imagelist.size()<l;ii++) {
				String novname = name.split(ast2)[0];
				novname += String.valueOf(ii);
				novname +=ast2.substring(ast2.indexOf("."));
				System.out.println(novname);
//				ii++;
			Imagelist.add(ImageIO.read(new File(novname)));
			
			}
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return Imagelist;
		
	}
	public static Map<String,String> strtomap(String s){//String转换为Map
		s = s.substring(1, s.length()-1);
		Map<String, String> a = new HashMap<String, String>();
		for (String string : s.split(",")) {
			String n[] = string.trim().split("=");
			if(!n[0].isEmpty() && !n[1].isEmpty())
			a.put(n[0], n[1]);
		}
		return a;
	}
}

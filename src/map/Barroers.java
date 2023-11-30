package map;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Barroers {
	ArrayList<Shape> Blist = new ArrayList<Shape>();
//	Rectangle2D RedRect = new Rectangle(1492, 35, 636, 559);
//	Ellipse2D.Double Oval = new Ellipse2D.Double(889, 780, 1814, 1814); 
	Rectangle2D RedRect = new Rectangle(1860, 80, 980, 620);
	Rectangle2D RedRect2 = new Rectangle(2200, 670,  300, 455);
	Ellipse2D.Double Oval = new Ellipse2D.Double(906, 1100, 2866, 2848);
	int xi[] = {1492,2129,2129,2934,2609,1808,1006,686,1489,1492};
	int yi[] = {562,562,1509,1985,2538,2070,2538,1981,1519,634};
	Polygon polygon = new Polygon(xi, yi, 9);
	public Barroers(){
		Blist.add(RedRect);
//		Blist.add(polygon);
		Blist.add(Oval);
		Blist.add(RedRect2);
		Rectangle re = new Rectangle(58, 3310, 980, 620);
		Blist.add(getTransRect(re,Math.PI/180 *58,re.getCenterX(),re.getCenterY()));
		Rectangle re1 = new Rectangle(398, 2890, 300, 455);
		Blist.add(getTransRect(re1,Math.PI/180 *58,re.getCenterX(),re.getCenterY()));
		Rectangle re2 = new Rectangle(3670, 3318, 980, 620);
		Rectangle re3 = new Rectangle(4002, 2902, 300, 455);
		Blist.add(getTransRect(re2,Math.PI/180 *-58,re2.getCenterX(),re2.getCenterY()));
		Blist.add(getTransRect(re3,Math.PI/180 *-58,re2.getCenterX(),re2.getCenterY()));
		
	}
	public boolean Canintersects(Rectangle2D A) {
		for(int i=0;i<Blist.size();i++) {
			Shape B = Blist.get(i);
			if(B.contains(A)) {return true;}
		}
		return false;
		
	}
	public void show(Graphics2D g2,int bx,int by) {
		polygon.translate(bx, by);

		g2.setColor(Color.YELLOW);
		
		g2.drawRect((int)(RedRect.getX())+bx, (int)(RedRect.getY())+by, (int)(RedRect.getWidth()), (int)(RedRect.getHeight()));
		g2.drawRect((int)(RedRect2.getX())+bx, (int)(RedRect2.getY())+by, (int)(RedRect2.getWidth()), (int)(RedRect2.getHeight()));
		
		g2.drawOval(906+bx, 1100+by, 2866, 2866); 
//		g2.draw(polygon);
		g2.translate(bx, by);
		for(int i=0;i<Blist.size();i++) {
			Shape B = Blist.get(i);
			g2.draw(B);
		}
		
		g2.translate(-bx, -by);
		polygon.translate(-bx, -by);
	}
	public Polygon getTransfrom(int x,int y,int w,int h,double ro) {
//		Rectangle2D r = new Rectangle(58, 3310, 980, 620);
		Rectangle2D r = new Rectangle(x, y, w, h);
		AffineTransform at = AffineTransform.getRotateInstance(ro, r.getCenterX(),  r.getCenterY());
		Polygon p = new Polygon();
		PathIterator i = r.getPathIterator(at);
		while (!i.isDone()) {
			double xy[] = new double[2];
			i.currentSegment(xy);
			p.addPoint((int) xy[0], (int) xy[1]);
			System.out.println(Arrays.toString(xy));

			i.next();
			
		}
		return p;
	}
	public Shape getTransfrom2(int x,int y,int w,int h,double ro,int rx,int ry) {
//		Rectangle2D r = new Rectangle(58, 3310, 980, 620);
		Rectangle2D r = new Rectangle(x, y, w, h);
		AffineTransform at = AffineTransform.getRotateInstance(ro, r.getCenterX(),  r.getCenterY());
		Area zgj = new Area(r);
		Shape gjjx = zgj.createTransformedArea(at);

		return gjjx;
	}
	public Shape getTransRect(Rectangle re,double ro,double d,double e) {
		AffineTransform at = AffineTransform.getRotateInstance(ro, d,  e);
		Area zgj = new Area(re);
		Shape gjjx = zgj.createTransformedArea(at);

		return gjjx;
	}
	public void addObstacle(Shape shape) {
		this.Blist.add(shape);
	}
}

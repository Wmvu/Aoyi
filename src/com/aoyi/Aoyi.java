package com.aoyi;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.Transparency;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;
import java.awt.geom.Rectangle2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.security.spec.EllipticCurve;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JProgressBar;

import com.sun.xml.internal.ws.api.Component;
import com.sun.xml.internal.ws.api.ComponentRegistry;

import map.Obstacle;
import aoyirole.Attack;
import aoyirole.GameRole;
import javafx.scene.transform.Affine;
import map.Map;
import map.Pipe;
import map.Qt;
import map.Xg;
import moniter.Moniter;
import teamandenemy.Enemy;

public class Aoyi extends JFrame{
	/**
	 * @author Wm;
	 */
	private static final long serialVersionUID = 202304091L;
	private BufferedImage qe;
	private Graphics q;
	public GameRole role;
	public Obstacle wall,qt,xg;
	public Enemy dr;
	public AoyiBack back;
	public ArrayList<Attack> koot= new ArrayList<Attack>();
	public ArrayList<Obstacle> eneryList = new ArrayList<Obstacle>();
	public ArrayList<Enemy> enemy =new ArrayList<Enemy>();
	public ArrayList<Enemy> enemyshook =new ArrayList<Enemy>();
	public int time;
	public Socket socket;
	public DataInputStream in;
	public DataOutputStream ou;
	int map[][] =null;
	public static boolean qwe = true;
	public Tools tools;
	public int ux,uy;
	public String Character; 
	public Graphics2D g2;
	public AffineTransform at = new AffineTransform();
public Aoyi() throws Exception{
		Character = Window.Character;
		this.socket = new Socket(Window.fwqdz.getText().trim(),Integer.parseInt(Window.fwqdkh.getText().trim()));
		tools = new Tools();
  qwe = false; Window.gsm =false;
		this.in = new DataInputStream(this.socket.getInputStream());
		ou = new DataOutputStream(this.socket.getOutputStream());
		ou.writeUTF("<#NICK_GANE#>"+ Window.name);
		initialize();
		readmapdata();
		back =new AoyiBack();
		role = new GameRole(this);
		inilt();//初始化
		word();//加载世界线程
		worldwall();//初始化场景障碍
//		this.in.close();
		NETCS ();//创建网络线程
		role.start();
		role.attribute();
	}
		boolean gj = false;
public Aoyi(int a) throws Exception{
	Character = Window.Character;
	tools = new Tools();
	qwe = false; Window.gsm =false;
	initialize();Loading.setGraphics(getGraphics());
	readmapdata();Loading.setProgress(20);
	back =new AoyiBack();Loading.setProgress(30);
	role = new GameRole(this);Loading.setProgress(40);
	Loading.setProgress(55);Loading.setProgress(100);
	word();//加载世界线程
	worldwall();//初始化场景障碍
	inilt();//初始化
	role.start();
	
		}


public void paint(Graphics g) {
	upcreateimage();
//	AffineTransform at1 =new AffineTransform();
//	at1.setTransform(g2.getTransform());
//	AffineTransform an = g2.getTransform();
//	at1.setTransform(an);
//	an.scale(0.2, 0.2);
//	at1.setToTranslation(at.getTranslateX(), at.getScaleY());
//	g2.setTransform(at1);
	/**
	double xx,yy;
	xx = this.ux - role.ox;
	yy = this.uy - role.oy;
//	double sx = xx>400||xx<-400?200*(xx/Math.abs(xx)):200*(xx/400);
//	double sy = yy>400||yy<-400?200*(yy/Math.abs(yy)):200*(yy/400);
	double sx = xx/3;
	double sy = yy/3;
	if(sx>200)sx=200;
	if(sy>200)sy=200;
	g2.drawImage(back.img, back.x-(int)(sx), back.y-(int)(sy),null);
	g2.drawImage(role.role,role.x-(int)(sx), role.y-(int)(sy), role.width, role.height , null);
	g2.drawImage(oap.img, oap.x-(int)(sx), oap.y-(int)(sy), oap.width, oap.height, null);
	**/
	paintingall();
//	 Image scaledInstance = qe.getScaledInstance(java.lang.Double.valueOf(qe.getWidth()*1.5).intValue(),java.lang.Double.valueOf(qe.getHeight()*1.5).intValue(), Image.SCALE_DEFAULT);
	g.drawImage(qe,0,0,null);//将缓冲区内容画出来
//	g.dispose();
    }
public void worldwall() {
	try {
    for (int j =0;j<map.length;j++) {	
    	for(int k=0;k<map[j].length;k++) {
    		switch (map[j][k]) {
			case 1:
				wall = new Pipe(k*60, j*100, 60, 120, ImageIO.read(new File("src/image/pipe.png")));
				eneryList.add(wall);
				break;
			case 2:
				qt = new Qt(k*60, j*100, 260, 420, ImageIO.read(new File("src/image/墙体.png")));
				eneryList.add(qt);
				break;
			case 5:
				xg = new Xg(k*140, j*40, 80, 80, ImageIO.read(new File("src/image/稻草人.gif")));
				eneryList.add(xg);
				break;
			default:
				break;
			}
    	}
    }
    } catch (Exception e) {
    	
	}
//	role.os.addObstacle(shape);96,86
	
}
//======================================清除子弹=================================================
	public void Checkboom() {
		for(int i = 0;i<koot.size();i++) {
		Attack gj = koot.get(i);
		
		if(gj.sked >=gj.distance) {
			gj.dismove();
		}
		if(!gj.is_surv)	koot.remove(i);
		}
		for(int i = 0;i<enemy.size();i++) {
			Enemy e = enemy.get(i);
			if(e.getdisdec()) enemy.remove(e);
		}
		for(int i = 0;i<enemyshook.size();i++) {
			Enemy e = enemyshook.get(i);
			if(e.getdisdec()) enemyshook.remove(e);
		}
	}

	//======================================绘制角色等级信息=================================================
public void jyjs () {
	if (role.TP/role.ro.maxTp>=1) {
		role.Levelup();
	}
}

    int ix = 0;
	int iy = 0;
public void Hpsomething(Graphics qe) {
	/**
	 * 此处画血条经验等
	 */
	int ddx=role.x+role.width/24*7+11 ,ddy=role.y+role.height;
	Graphics2D qa =(Graphics2D) qe;
	qa.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	Stroke stroke = qa.getStroke();
	qa.setStroke(new BasicStroke(5.0f));
	
	qa.drawOval(role.x+18+5, role.y+role.height-5, 18, 18);
	qa.fillOval(role.x+18+5, role.y+role.height-5, 18, 18);
	qa.setColor(Color.blue);//ORANGE
	qa.drawArc(role.x+18+5, role.y+role.height-5, 18, 18,90,-(role.TP*360/role.ro.maxTp));
	qa.setFont(new Font("",Font.BOLD,18));
	qa.setStroke(stroke);
	qa.setColor(Color.RED);//换红色
	qa.drawString(role.LV+"",role.x+27, role.y+role.height+10);
	qa.setColor(Color.BLACK);
	qa.drawRect(role.x+role.width/24*7+13, role.y+role.height, 65, 12);//画血条框
	
	qa.setColor(Color.GREEN);
	double f =role.getHp()/(double)(role.getMaxHp());
	qa.fillRect(role.x+role.width/24*7+13, role.y+role.height, (int) (65*f), 12);//画血条
	
	qa.setColor(Color.BLACK);
	int lw = role.getMaxHp()/20;
//	for (int i =1;i<lw+1;i++) {
//	qa.drawLine(ddx+65/(lw+1)*i,ddy, ddx+65/(lw+1)*i, ddy+6);//画刻度
//	
//	}
	for (int i =1;i<lw+1;i++) {
		qa.drawLine(ddx+65/lw*i,ddy, ddx+65/lw*i, ddy+6);//画刻度
		
		}
	drawnameStrin(qa);
}
public void Hpsomething2(Graphics qa,Enemy e,Color r) {
	/**
	 * 此处画血条经验等
	 */
//	int HUB_X = e.x,HUB_Y=e.y+e.height;
	
	qa.drawOval(e.x+18+10, e.y+e.height-5, 18, 18);
	qa.fillOval(e.x+18+10, e.y+role.height-5, 18, 18);
	qa.setColor(Color.ORANGE);
//	qa.fillArc(e.x+18+10, e.y+e.height-5, 18, 18,0,e.Tpercentage);
	qa.setFont(new Font("",Font.BOLD,16));
	qa.setColor(r);//换红色
	qa.drawString(e.Lv+"",e.x+33, e.y+e.height+10);
	qa.setColor(Color.BLACK);
	qa.drawRect(e.x+e.width/24*7+11, e.y+e.height, 65, 12);//画血条框
	qa.setColor(Color.GREEN);
	double f =e.getHp()/(double)(e.maxHp);
	qa.fillRect(e.x+e.width/24*7+11, e.y+e.height,(int) (65 * f), 12);//画血条
	qa.drawString(e.name, e.x+e.width/2-3*e.name.length(), e.y+e.height+30);
	
}
void drawnameStrin (Graphics n) {
	if(Window.name==null) {
		n.drawString("默", role.x+role.width/2-5, role.y+role.height+30);
	}else {
		int len = 0;
		try {
			len = Window.name.getBytes("gbk").length;
		} catch (UnsupportedEncodingException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	n.drawString(Window.name, role.x+role.width/2 - len * 3, role.y+role.height+30);
	}
	}
//======================================向服务器发送角色信息=================================================
public void setxy () {
	String xy = "";
	int sjzbx = Math.abs(this.back.x) + this.role.x;
	int sjzby = Math.abs(this.back.y) + this.role.y;
	
	xy = "<#NICK_ZBSF#>"+Window.name+ sjzbx + "," + sjzby;
	try {
//		if (ix == sjzbx && iy ==sjzby) {
			
		//}else {
		this.ou.writeUTF(xy);
		this.ou.flush();
		ix = sjzbx;iy = sjzby;
		//}
		
	} catch (IOException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	}
	
}
public void atack(String name,int dhp) {
	String setaple = "<#NICK_HURT#>";
	setaple += role.hurt();
	try {
		this.ou.writeUTF(setaple);
		this.ou.flush();
	} catch (IOException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	}
	
}
public void adat(String name) {
	if (enemy.isEmpty()) return;
	String setaple = "<#NICK_ADAT#>";
	setaple += name;
	try {
		this.ou.writeUTF(setaple);
		this.ou.flush();
	} catch (IOException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	}
}
public void utsend(String name) {
	if (enemy.isEmpty()) return;
	try {
		this.ou.writeUTF(name);
		this.ou.flush();
	} catch (IOException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	}
}
//===========================================================================================
//计时器
//===========================================================================================
public void inilt () {
	new Thread(){
		public void run(){
			while(true) {
				repaint();//重绘页面
				Checkboom();//子弹是否被清除
				jyjs();//角色等级信息
				try {
					Thread.sleep(10 );
				}catch(InterruptedException cw) {
					cw.printStackTrace();
				}
			}
		}
	}.start();
}
public void word () {
	new Thread() {//游戏计时器主线程
		public void run() {
			while (true) {
				time+=1;
				if(time>=5) {
					Random r = new Random();
					xg = new Xg(r.nextInt(60)*140, r.nextInt(60)*40, 80, 80, new ImageIcon("image/稻草人.gif").getImage());
					eneryList.add(xg);
					time = 0;
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
		}
	}.start();
}
public void NETCS () {
	new Thread() {
		public void run() {
			while (true) {
//			getxy();
				role.rsuvivul++;
				if(role.rsuvivul>=1000 + role.LV*100) {
					role.HP = role.getMaxHp();
				}
			try {
				this.sleep(10);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			}
		}
	}.start();
	

	new Thread() {
		public void run() {
			while (true) {
				setxy();
				try {
					this.sleep(10);
				} catch (InterruptedException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			
			}
		}
	}.start();
	Enemythare enemythread =new Enemythare(this);
	enemythread.start();
	enemythread.ueda();
}
public void Sak(int x,int y,int w,int h,int time) {
	 int[] sx = null,sy= null;
	 double dc;
	 try {
		this.ou.writeUTF(sx.toString());
	} catch (IOException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	}
}
private void readmapdata() throws Exception {
	Map ma = new Map();
	map = ma.readMap();
	}
private void initialize() {
	this.setSize(1000,800);
	this.setLocationRelativeTo(null);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	this.setVisible(true);
}
private void upcreateimage() {
	qe = (BufferedImage)this.createImage(this.getSize().width,this.getSize().height);
	q = qe.getGraphics();
	g2 = (Graphics2D)q;
}
private void paintingall() {
	q.drawImage(back.img, back.x, back.y,null);
//	g2.drawImage(back.img, at, null);
	q.drawImage(role.role,role.x, role.y, role.width, role.height , null);
	double r = (double)Math.sqrt(Math.pow(role.ox-ux, 2)+Math.pow(role.oy-uy,2));
	if(r>720) {tools.Arrow(g2,role.ox, role.oy, role.ox+(int)(720*((ux-role.ox)/r)),role.oy+(int)(720*((uy-role.oy)/r)));}else {
	int rux= ux+(int)(Math.abs(at.getTranslateX()));int ruy= uy+(int)(Math.abs(at.getTranslateY()));
		tools.Arrow(g2,role.ox, role.oy,rux ,ruy);}
//	q.drawLine(role.ox, role.oy, ux, uy);java.awt.MouseInfo.getPointerInfo().getLocation().x, java.awt.MouseInfo.getPointerInfo().getLocation().y
	q.drawImage(role.gjicon,this.getWidth()/2-20,this.getHeight()-80,50,50,null);
	q.drawImage(role.ayicon,this.getWidth()/2+50,this.getHeight()-80,50,50,null);
	Hpsomething(q);
//	role.os.show(g2, back.x, back.y);
//遍历敌人列表绘制敌人
	for(int i = 0;i<enemy.size();i++) {
		Enemy e = enemy.get(i);
		q.drawImage(e.img, e.x, e.y, null);
//		q.drawRect(e.x+e.pagx,e.y+e.pagy,e.pagwidth,e.pagheight);
		this.Hpsomething2(q, e, Color.GREEN);
	}
	for(int i = 0;i<enemyshook.size();i++) {
		Enemy e = enemyshook.get(i);
//		q.drawImage(e.img, e.x, e.y, null);
		if(!e.getdisdec())e.paint(g2,this);
	}
//======================================子弹碰撞检测一系列判断=================================================
	for(int i =0;i<eneryList.size();i++) {
		Obstacle oap = eneryList.get(i);
		g2.drawImage(oap.img, oap.x, oap.y, oap.width, oap.height, null);
//		at.setToTranslation( oap.x, oap.y);
//		g2.drawImage(oap.img, at, null);
		Collisiondetection(oap);
		if (oap.getClass() == Xg.class) {
			q.setColor(Color.BLACK);
			Xg qg = (Xg) eneryList.get(i);
			q.drawRect(oap.x+20, oap.y+oap.height, 40, 10);
			q.setColor(Color.YELLOW);
			q.fillRect(oap.x+20, oap.y+oap.height, qg.hp*2, 10);
			
		}
	}
	for(int i =0;i<enemy.size();i++) {
		Enemy e = this.enemy.get(i);
		Rectangle rect = new Rectangle(e.x+e.pagx,e.y+e.pagy,e.pagwidth,e.pagheight);
		for (int x = 0;x<koot.size();x++) {
			Attack gj = koot.get(x);
		Rectangle recte = new Rectangle(gj.x,gj.y,gj.width,gj.height);
		Rectangle rectangle = Tools.calculatorRotatedSize(new Rectangle(new Dimension(gj.width, gj.height)), (int)gj.drct);
		AffineTransform ew =AffineTransform.getRotateInstance(Math.toRadians(gj.drct), gj.x+rectangle.width/2,gj.y+rectangle.height/2);
		ew.translate((rectangle.width - gj.width) / 2, (rectangle.height - gj.height ) / 2);
		Area zgj = new Area(recte);
		Shape gjjx = zgj.createTransformedArea(ew);

		if(gjjx.intersects(rect)) {
			gj.dismove();
			role.hurt();
			gj.hurt(e);
			
		}
		
		
		}
	}

//===========================================================================================
//绘制敌人
//===========================================================================================

		try {
				for (int x = 0;x<koot.size();x++) {
		Attack gj = koot.get(x);
		if (gj!=null && g2!=null)gj.tebh(g2);
		
	}
		} catch (Exception e) {
		System.out.println("不用管他");
		}

}
private void Collisiondetection(Obstacle oap) {
	Rectangle rect = new Rectangle(oap.x,oap.y,oap.width,oap.height);
	for (int x = 0;x<koot.size();x++) {
		Attack gj = koot.get(x);
	Rectangle recte = new Rectangle(gj.x,gj.y,gj.width,gj.height);
	AffineTransform ew = new AffineTransform();
	Area zgj = new Area(recte);
	Rectangle rectangle = tools.calculatorRotatedSize(new Rectangle(new Dimension(gj.width, gj.height)), (int)gj.drct);
	ew.rotate(Math.toRadians((int)gj.drct),gj.x+rectangle.width/2,gj.y+rectangle.height/2);

	ew.translate((rectangle.width - gj.width) / 2, (rectangle.height - gj.height ) / 2);
	Shape gjjx = zgj.createTransformedArea(ew);
//g2.draw(gjjx);
//g2.drawRect(gj.x, gj.y, rectangle.width, rectangle.height);
//	Shape re = recte.createTransformedShape(re);
//	Shape 
	if(gjjx.intersects(rect)) {
		if(oap.getClass()==Xg.class) {//检测子弹与小怪碰撞
			if(!gj.penetrate)koot.remove(x);
			Xg qg = (Xg) oap;
			if(!gj.isq.contains(qg)) qg.hp-=role.getAt();gj.isq.add(qg);
		System.out.println(qg.hp);
		if(qg.hp<=0) {
		eneryList.remove(oap);
		role.upTp(5);
		}
		}
	}
	
	
	}
	
}


public String getGLName() {
	// TODO 自动生成的方法存根
	return Window.name;
}
public void Game_over() {
	System.exit(0);
}
}

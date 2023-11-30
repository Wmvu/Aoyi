package aoyirole;



import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.ImageIcon;

import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D.Double;
import map.Barroers;
import map.Obstacle;
import com.aoyi.Aoyi;

public class GameRole extends Thread{

	public Aoyi ao ;
	public int x=300,y=300;
	public double realX,realY,showx,showy;
	public double Tx,Ty;
	public int ox,oy;
	public int width = 142,height = 100;
	public int speen = 4;
	public int distance = 500;
	public Image role ;
	public Image 攻击 = new ImageIcon("src/image/普攻_right.png").getImage();
	public Image gjicon,ayicon;
	public boolean left=false,right=false,down=false,up=false,普攻=false,奥义=false;
	public String qxz;
	public Role ro;
	public Barroers os = new Barroers();
	public int HP,TP,AT,LV;
	public Statea state;
	public int MaxWidth,MaxHeight;
	public Animation Anment = null;//角色的动画管理器
	ExecutorService executorService = Executors.newSingleThreadExecutor();
	String std="idle";
	public int rsuvivul;
	public GameRole(Aoyi ao) {
		this.ao = ao;
		this.state = new Statea(this);
		qxz = ao.Character;
		state.start();
		this.Anment = new Animation();
//		Anment.addBuffImageList("left", null);
//		Anment.addBuffImageList("right", null);
//		Anment.addBuffImageList("up", null);
//		Anment.addBuffImageList("down", null);
//		Anment.addBuffImageList("at_first", null);
//		Anment.addBuffImageList("att", null);
//		Anment.addBuffImageList("at_end", null);
//		Anment.addBuffImageList("skill_first", null);
//		Anment.addBuffImageList("skill_end", null);
//		Anment.addBuffImageList("skill2_first", null);
//		Anment.addBuffImageList("skill2_end", null);
//		Anment.addBuffImageList("skill3_first", null);
//		Anment.addBuffImageList("skill3_end", null);
		switch (qxz) {
		case "1":
			ro = (Role) new 龙魂之箭(this);
			 role = ro.img;
			Image 攻击 = ro.攻击;	
			break;

		case "2":
			ro = (Role) new 不羁之灵(this);
			role = ro.img;
			width=ro.z;
			height = ro.h;
			break;
		case "3":
			ro = (Role) new 凌霄之灵(this);
			role = ro.img;
			width=60;
			height =60;
			break;
		case "4":
//			System.out.println("xlzx");
			ro = (Role) new 朽零之形(this);
			role = ro.img;
			width=60;
			height =60;
			break;
		}
		LV = ro.Lv;
		TP = ro.Tp;
		HP = ro.Hp;
		gjicon=ro.gjicon;
		ayicon=ro.ayicon;
		ro.Passive();
		this.realX = 2100;this.realY = y;showx=ao.back.x;showy=ao.back.y;
		MaxWidth = ao.back.getWidth()-ao.getWidth();MaxHeight = ao.back.getHeight()-ao.getHeight();
		new Thread(Anment).start();
		setactordseat();
		this.attribute();
	}

	public void run(){
		
		while(true) {
			
			if(this.std == "death")return;
			if(ao.back.x>0)ao.back.x = 0;if(ao.back.x<-MaxWidth)ao.back.x = -MaxWidth;
			if(ao.back.y>0)ao.back.y = 0;if(ao.back.y<-MaxHeight)ao.back.y = -MaxHeight;
			if(left&up&!right&!down) {
				if(!his("UpperLeft")) {
					this.realX -= speen*0.7071067811865476;
					this.realY -= speen*0.7071067811865476;
					
				}
			  }else if(left&&!up&&!down) {
				if(!his("Left")) {
//					return;
				
//				if(x<=500) {
//				this.x -=speen; 
//				}
					this.realX -=speen;
				}
//				this.role= ro.left ;
				this.role= Anment.getAnilist("left");
			}
			if(left&down&!right&!up) {
				if(!his("LowerLeft")) {
					double zy = speen*0.7071067811865476;
					this.realX -= zy;
					this.realY += zy; 
					
				}
			  }else if(down&&!left&&!right) {
				if(!his("Down")) {
					this.realY+=speen;
//				this.role= ro.down ;
				this.role= Anment.getAnilist("down");
			}}
			if(right&up&!left&!down) {
				if(!his("UpperRight")) {
				double zy = speen*0.7071067811865476;
				this.realX += zy;
				this.realY -= zy;
		       }	
			  } else if(right&&!up&&!down) {
			
			   if(!his("Right")) {
				this.realX +=speen;
//				this.role= ro.right ;
				this.role= Anment.getAnilist("right");
			}
			  }
			if(right&down&!left&!up) {
				if(!his("LowerRight")) {
				double zy = speen*0.7071067811865476;
				this.realX += zy;
				this.realY += zy;
				
				}		
			   }else if(up&&!left&&!right) {
				if(!his("Up")) {
				this.realY-=speen;
//				this.role= ro.up ;
				this.role= Anment.getAnilist("up");
				}
			}
			
			if(普攻) {
				/*
				  if (ao.role.right) {
					  addAttack(6,ao.role.width/2) ;
			  }
				  if (ao.role.left) {
					  addAttack(4,- ao.role.width) ;
			  }
				  if ( ao.role.up) {
					  addAttack(8,-ao.role.height) ;
			  }
				  if ( ao.role.down) {
					  addAttack(2,ao.role.height/2) ;
			  }*/
				if (std!="idle") return;
				std ="first_at";
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						try {
							Thread.sleep(1000);
							
						} catch (InterruptedException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
						ro.普攻();
							std ="idle";
					}
				}).start();
				
			}
			if(奥义) {
				奥义 = false;
				if (std!="idle") return;
				std ="first_at";
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
						ro.奥义();
						std ="idle";

					}
				}).start();
				
			}
//			this.x = (int)this.realX;this.y = (int)this.realY;
//			speen = 5;
			showstart();
			ox = this.x+this.width/2;oy=this.y+this.height/2;
			try {
				this.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}	
	}

	public boolean his(String pq) {
	Shape rect = new Double(ox+ro.rx, oy+ro.ry, ro.rw, ro.rh);
//	ao.g2.draw(rect);
	Rectangle rectle = null;
//	os.show(ao.g2, getTx(), getTy());
	Rectangle2D  r2 = null;
//	Line2D l = new Line2D.Double(ao.back.x+110, ao.back.y+305, ao.back.x+940, ao.back.y+964 );
	
	for (int i = 0; i < ao.eneryList.size(); i++) {
		Obstacle enery = ao.eneryList.get(i);
		if(pq.equals("Left")){
			rectle = new Rectangle(enery.x+speen,enery.y,enery.width,enery.height);
			r2 = new Rectangle((int)(this.realX)-speen,(int)(this.realY),(int)ro.rw,(int)ro.rh);
//			l = new Line2D.Double(ao.back.x+110+speen, ao.back.y+305, ao.back.x+940+speen, ao.back.y+964 );
		}else if(pq.equals("Right")){
			// 右侧碰撞物检测。
			rectle = new Rectangle(enery.x-speen,enery.y,enery.width,enery.height);
			r2 = new Rectangle((int)(this.realX)+speen,(int)(this.realY),(int)ro.rw,(int)ro.rh);
//			l = new Line2D.Double(ao.back.x+110-speen, ao.back.y+305, ao.back.x+940-speen, ao.back.y+964 );
		}
		
		else if(pq.equals("Up")){
			rectle = new Rectangle(enery.x,enery.y+speen,enery.width,enery.height);
			r2 = new Rectangle((int)(this.realX),(int)(this.realY)-speen,(int)ro.rw,(int)ro.rh);
//			l = new Line2D.Double(ao.back.x+110, ao.back.y+305+speen, ao.back.x+940, ao.back.y+speen+964 );
		
		}else if(pq.equals("Down")){
			rectle = new Rectangle(enery.x,enery.y-speen,enery.width,enery.height);
			r2 = new Rectangle((int)(this.realX),(int)(this.realY)+speen,(int)ro.rw,(int)ro.rh);
//			l = new Line2D.Double(ao.back.x+110, ao.back.y+305-speen, ao.back.x+940, ao.back.y+964-speen );
		}else if(pq.equals("UpperLeft")) {
			rectle = new Rectangle(enery.x+speen,enery.y+speen,enery.width,enery.height);
			r2 = new Rectangle((int)(this.realX)-speen,(int)(this.realY)-speen,(int)ro.rw,(int)ro.rh);
		}else if(pq.equals("UpperRight")) {
			rectle = new Rectangle(enery.x-speen,enery.y+speen,enery.width,enery.height);
			r2 = new Rectangle((int)(this.realX)+speen,(int)(this.realY)-speen,(int)ro.rw,(int)ro.rh);
		}else if(pq.equals("LowerLeft")) {
			rectle = new Rectangle(enery.x+speen,enery.y-speen,enery.width,enery.height);
			r2 = new Rectangle((int)(this.realX)-speen,(int)(this.realY)+speen,(int)ro.rw,(int)ro.rh);
		}else if(pq.equals( "LowerRight")) {
			rectle = new Rectangle(enery.x-speen,enery.y-speen,enery.width,enery.height);
			r2 = new Rectangle((int)(this.realX)+speen,(int)(this.realY)+speen,(int)ro.rw,(int)ro.rh);
		}
//		ao.g2.setColor(Color.yellow);ao.g2.drawRect((int)(r2.getX()+getTx()), (int)(r2.getY()+getTy()), (int)ro.rw,(int)ro.rh);
//		if(rect.intersectsLine(l)) {return true;}
		if(rect.intersects(rectle)) {
//		if(pq.equals("Left")) {
//			ao.role.x += speen;
//		}
//		if(pq.equals("Right")) {
//			ao.role.x -= speen;
//		}
//		if(pq.equals("Up")) {
//			ao.role.y += speen;
//		}
//		if(pq.equals("Down")) {
//			ao.role.y -= speen;
//		}

//			System.out.println(enery.getClass());
		return true;
	}
		Rectangle r3 = new Rectangle((int)(r2.getCenterX() +ro.rx+50), (int)(r2.getCenterY() + ro.ry+50), (int)(ro.rw), (int)(ro.rh));
		if(!os.Canintersects(r3)) {
			return true;
		}
		
		double ra = 0.00;
		Rectangle rectangle = new Rectangle((int)(r2.getCenterX() +ro.rx), (int)(r2.getCenterY() + ro.ry), (int)(ro.rw), (int)(ro.rh));
		Area a = new Area(rectangle);
		Rectangle2D r = new Rectangle(x, y, 50, 50);
		AffineTransform at = AffineTransform.getRotateInstance(ra, r.getCenterX(),  r.getCenterY());
		Area zgj = new Area(rectangle);
		Shape gjjx = zgj.createTransformedArea(at);
		a.intersect(new Area(gjjx));
		a.isEmpty();
		//以上是全新的碰撞检测
//		r2.clone();
//		rectle.clone();
		
	}
	return false;
	
}

	public void shoot(int x,int y) {
		if (std!="idle") return;
		std ="first_at";
		executorService.execute(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(300);
					
				} catch (InterruptedException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				ro.普攻(x,y);
				std ="idle";
			}
		});
		
		
		
	}
	public void skill() {
		ro.skill();
			
		}
	public void showstart() {
		double qx,qy;
		int xx,yy;
		int node1,node2;
//		xx = ao.ux - this.ox;
//		yy = ao.uy - this.oy;
//		int sx = 100*(xx/ao.getWidth()/2);
//		int sy = 100*(yy/ao.getHeight()/2);
		node1 = (ao.getWidth()/3);//这东西就是窗口宽度和人物图片的宽度/2
		
		node2 = (ao.getHeight()/3);
//		node1 = 500;
//		node2 = 500;
		
		setworldseat(node1, node2);
//		setactordseat();//校验位置准确性
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		if(this.realX-(ao.getWidth()/2-this.width/2)>=0) {
//			qx=-(this.realX-(ao.getWidth()/2-this.width/2));
//		}else {qx=0;this.x = (int)this.realX;
//		}
//		if(this.realY-(ao.getHeight()/2-this.height/2)>=0) {
//			qy=-(this.realY-(ao.getHeight()/2-this.height/2));
//		}else {qy=0;
//		this.y = (int)this.realY;}System.out.println(qy);
//		ao.at.setToTranslation((int)(x), (int)(y));
//		long starttime =System.nanoTime();long endtime =System.nanoTime();
//			System.out.println(endtime-starttime);	
		
//		ao.back.x =(int)(qx);
//		ao.back.y =(int)(qy);
	
		
//		ao.at.setToTranslation(qx, qy);
	}
	/**
	 * 根据真实坐标计算摄像机的位置
	 * 其中分为三部分
	 * 1.全界偏移(世界部分)
	 * 2.触底反弹（绘图部分）
	 * 3.可动界域（角色部分）
	 * 无论摄像机怎么移动真实指标始终为一
	 * @param dx
	 * @param dy
	 */boolean flag=false;int wx = 0,wy = 0;
	void setworldseat(double dx,double dy){
		int Dezd_zooe_width = (int) dx;
		int Dezd_zooe_height = (int) dy;
//		int wx=0,wy=0;
		
		//-----------------------------------------
		if(!flag)wx = -(int) (this.realX - ao.getWidth()/2-this.width/2);
		if(!flag)wy = -(int) (this.realY - ao.getHeight()/2-this.height/2);
		flag=true;
		
		if(this.showx<=dx)wx = -(int) (this.realX - dx);//-1-9999
		if(this.showx>dx+Dezd_zooe_width)wx = -(int) (this.realX - dx-Dezd_zooe_width);
		if(this.showy<dy)wy = -(int) (this.realY - dy);
		if(this.showy>dy+Dezd_zooe_height)wy = -(int) (this.realY - dy-Dezd_zooe_height);//这两个是背景的负偏移度当角色位于中心移动时不再移动绘制图片位置而移动负偏移度
		//这两个变量是移动除去角色外的世界的所有东西的坐标
		
		this.showx =this.realX+ao.back.x;
		this.showy =this.realY+ao.back.y;
		
		//-------------------------------
		if(wx>0)wx=0; 
		if(wx<-MaxWidth)wx=-MaxWidth;
		if(wy>0)wy=0;
		if(wy<-MaxHeight)wy=-MaxHeight;
		//触底解决方案-------------------
		
		if((this.showx<dx+Dezd_zooe_width && this.showx>dx)||wx>=0||wx<=-MaxWidth)this.x =(int)(this.showx);
		if((this.showy<dy+Dezd_zooe_height && this.showy>dy)||wy>=0||wy<=-MaxHeight)this.y =(int)(this.showy);
//		this.x =(int)(this.showx);
//		this.y =(int)(this.showy);
//		if(wx>0&&wx>-MaxWidth) {wx=0;this.x =(int)(this.showx);}else if (wx<0&&wx<-MaxWidth) {wx=-MaxWidth;this.x =(int)(this.showx);}
		//如若左边到最左，则...否则如果比-宽度还小则为右边到最右则...
		//        0             999
		//   -33  0            -999
		
//		if(wy>0&&wy>-MaxHeight) {wy=0;this.y =(int)(this.showy);}else if (wy<0&&wy<-MaxHeight) {wy=-MaxHeight;this.y =(int)(this.showy);}
		moveword(wx,wy);

	}
	void moveword(int wx,int wy) {
		ao.back.x = wx;
		ao.back.y = wy;
//移动地图中所有其他物件
		for (int i =0;i<ao.eneryList.size();i++) {
			Obstacle o = ao.eneryList.get(i);
			o.getGameMapOrigin((int)(wx), (int)(wy));
		}
//		for (int i = 0; i < ao.koot.size(); i++) {
//			Attack a = ao.koot.get(i);
//			
//			
//		}
	}
	void setactordseat() {
		//中心锚点
//		System.out.println(ao.getWidth());
//		System.out.println(ao.getContentPane().getWidth());
		int w = ao.getWidth()/2-this.width/2;
		int h = ao.getHeight()/2-this.height/2;
		
		int yw=0,xw=0;
		
		MaxWidth = ao.back.getWidth()-ao.getWidth();MaxHeight = ao.back.getHeight()-ao.getHeight();		
//		if(this.realY<=h)this.y=(int) this.realY;else if(this.realY>=MaxHeight) {this.y=(int) this.realY-MaxHeight;yw=MaxHeight;}else {
//			yw =(int) (this.realY-h);
//		
//		}
//		if(this.realX<=w)this.x=(int) this.realX;else if(this.realX>=MaxWidth) {this.x=(int) this.realX-MaxWidth;xw=MaxWidth;}else {
//			
//			
//			
//			xw =(int) (this.realX-w);
//			
//		}
		
		xw =(int) (this.realX-w);yw =(int) (this.realY-h);
		if(xw<=0) {
			xw=0;
			
			this.x=(int) this.realX;
		}
		if(xw>=MaxWidth) {
			xw=MaxWidth;
			this.x=(int) this.realX-MaxWidth;
		}
//		System.out.println("realX: "+this.realX+",realY: "+this.realY);
//		System.out.println("wX: "+xw+",xY: "+yw);

		if(yw<=0) {
			yw=0;
			this.y=(int) this.realY;
		}
		if(yw>=MaxHeight) {
			yw=MaxHeight;
			this.y=(int) this.realY-MaxHeight;
		}
		moveword(-xw, -yw);
		
		showx = this.realX-xw;
		showy = this.realY-yw;
		this.x = (int) showx;
		this.y = (int) showy;
		
	}
	void down() {
		if(Canscroll((ao.getWidth()/2-this.width/2), (ao.getWidth()/2-this.width/2), 2000, 2000, this.x)) {
			if(Canscroll(0, 0, 2000, 2000, this.x)) {
				
			}
		}
		if(Canscroll(0, 0, 2000, 2000, this.x)) {
			
		}
		if(Canscroll(0, 0, 2000, 2000, this.y)) {
			
		}
	}
	void left() {
		
	}
	void up() {
		
	}
	void right() {
		
	}
	void upperleft() {
		
	}
	void upperright() {
		
	}
	void lowerleft(){
		
	}
	void lowerright() {
		
	}
	public Animation getAniation() {
		return this.Anment;
	}
	
	void WordSeat(double xl,double yl) {
	WorldSeatX(xl);
	WorldSeatY(yl);
	}
	void WorldSeatX(double lh) {
		/**
		 * lh:移动长度
		 * lh负数时为向右卷动反之则向左
		 */
		ao.back.x+=lh;
		for (int i =0;i<ao.eneryList.size();i++) {
			Obstacle o = ao.eneryList.get(i);
			o.x+=lh;
		}
	}
	void WorldSeatY(double lh) {
		/**
		 * lh:移动长度
		 * lh负数时为向下卷动反之则向上
		 */
		ao.back.y+=lh;
		for (int i =0;i<ao.eneryList.size();i++) {
			Obstacle o = ao.eneryList.get(i);
			o.y+=lh;
		}
	}
	void ScreenScroll() {
		
	}
	boolean Canscroll(int s1,int s2,int s3,int s4,int iu){
		/**
		 *判断是否超出边界（否则返回真；是则返回假）
		 * s1：左x轴
		 * s2：左y轴
		 * s3：右x轴
		 * s4：右y轴
		 */
		if(s1>iu||s2>iu||s3<iu||s4<iu) {
			return true;
			}else {
			return false;
			}
	}
	void setspeen(double sp) {
		this.speen = (int) sp;
	}
	double getspeen() {
		return this.speen;
	}
	int getTx() {
//		this.Tx = this.realX - (ao.getWidth()/2-this.width/2);
		this.Tx = ao.back.x;
		return (int) this.Tx;
	}
	int getTy() {
//		this.Ty = this.realY -(ao.getHeight()/2-this.height/2);
		this.Ty =ao.back.y;
		return (int) this.Ty;
	}
	boolean Canleft() {
		if(left&!right&!up&!down)return true;
		return false;
	}
	public void upTp(int dum) {
		this.TP +=dum;
		if(this.TP/this.getMaxTp()>=1)this.Levelup();
	}
	public void Levelup() {
		this.TP-=getMaxTp();
		ro.Levelup();
		this.HP = this.getMaxHp();
		if (this.TP/this.getMaxTp()>=1) this.Levelup();
	}
	public int getHp() {
		return this.HP;
	}
	public int getMaxHp() {
		return this.ro.MaxHp;
	}
	public int getMaxTp() {
		return this.ro.maxTp;
	}
	public int getAt() {
		return this.ro.At;
	}
	public String hurt() {
		 return ro.hurt();
	}
	public void injured(Map<String,String> hur) {
		ro.injured(hur);
		this.attribute();
	}
	public void attribute() {
		ro.attribute();
	}
	public void isdeath() {
		if(this.HP <= 0) {
			this.std = "death";
			ao.Game_over();
		}
	}
}

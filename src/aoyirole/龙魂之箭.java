package aoyirole;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


import moniter.Mouseiter;
import teamandenemy.Enemy;
import com.aoyi.Aoyi;
import com.aoyi.Tools;
import com.aoyi.Window;

public class 龙魂之箭 extends Role {
public 龙魂之箭(GameRole role) {
	super(role);
	Race = 0;
	try {
		img = ImageIO.read(new File("src/image/龙魂之箭.png"));
		攻击 =ImageIO.read(new File("src/image/普攻_right.png"));
		gjicon = ImageIO.read(new File("src/image/龙箭普攻.png"));
		ayicon = ImageIO.read(new File("src/image/龙箭奥义.png"));
		right = ImageIO.read(new File("src/image/龙箭_right.gif"));
		left =ImageIO.read(new File("src/image/龙箭2.gif"));
		up = ImageIO.read(new File("src/image/龙箭_UP.png"));
		down = ImageIO.read(new File("src/image/龙箭_DOWN.png"));
		role.getAniation().addBuffImageList("left", Tools.getBuffImageList("src/image/龙箭2.gif"));
		role.getAniation().addBuffImageList("right", Tools.getBuffImageList("src/image/龙箭_right.gif"));
		role.getAniation().addBuffImageList("up", Tools.getBuffImageList("src/image/龙箭_UP.png"));
		role.getAniation().addBuffImageList("down", Tools.getBuffImageList("src/image/龙箭_DOWN.png"));
	} catch (IOException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	}
	
	Hp = 50;
	MaxHp =50;
	Tp = 0;
	At = 5;   
}

@Override
public void attribute() {
	Map<String, String> hur = new HashMap<String, String>();

	hur.put("name", Window.name);
	hur.put("lv",String.valueOf(role.LV));
	hur.put("hp", String.valueOf(this.Hp));
	hur.put("maxhp", String.valueOf(this.MaxHp));
	hur.put("Tp", String.valueOf(this.Tp/(double)(this.maxTp)));
	hur.put("anie", String.valueOf(role.std));
	role.ao.utsend("<#NICK_ARBT#>"+hur.toString());hur.clear();
//	System.out.println(hur.toString());
}

@Override
	public void Levelup() {
		//在5、10、15级时属性迎来一个暴涨
	this.Lv ++;
//	this.Hp = ;
	this.maxTp = (int) (this.maxTp*1.25);
	this.MaxHp = (int) (this.MaxHp*1.15);
	this.At = (int) (this.At*1.25);
	if(this.Lv == 5) {
		this.MaxHp +=3;
		this.At +=3;
	}
	if(this.Lv == 10) {
		this.MaxHp +=5;
		this.At +=5;
	}
	if(this.Lv == 15) {
		this.MaxHp +=8;
		this.At +=8;
	}
	}

public void 奥义() {
	//	for (int i = 0; i < 5; i++) {
//		new Attack(role.x,role.y,240,56,0);
//		
//	}
//java.awt.MouseInfo.getPointerInfo().getLocation().x;java.awt.MouseInfo.getPointerInfo().getLocation().y;
	int ix =ao.ux;
	int iy =ao.uy;
	int rx = ix - role.ox;
	int ry = iy - role.oy;
	double zxd= ao.tools.取角度(ix, iy, ao.role.x, ao.role.y);
	double ir = Math.sqrt(Math.pow(ix-ao.role.ox, 2)+Math.pow(iy-ao.role.oy,2));
  普攻(ix, iy);
  普攻((int)(rx*Math.cos(30*Math.PI/180)-ry*Math.sin(30*Math.PI/180)+ix), (int)(rx*Math.sin(30*Math.PI/180)+ry*Math.cos(30*Math.PI/180)+iy)); 
  普攻((int)(rx*Math.cos(60*Math.PI/180)-ry*Math.sin(60*Math.PI/180)+ix), (int)(rx*Math.sin(60*Math.PI/180)+ry*Math.cos(60*Math.PI/180)+iy));
  普攻((int)(rx*Math.cos(330*Math.PI/180)-ry*Math.sin(330*Math.PI/180)+ix), (int)(rx*Math.sin(330*Math.PI/180)+ry*Math.cos(330*Math.PI/180)+iy)); 
  普攻((int)(rx*Math.cos(300*Math.PI/180)-ry*Math.sin(300*Math.PI/180)+ix), (int)(rx*Math.sin(300*Math.PI/180)+ry*Math.cos(300*Math.PI/180)+iy));
  
}
public void 普攻() {
	  if (role.right) {
		  addAttack(6,role.width/2) ;
  }
	  if (role.left) {
		  addAttack(4,-role.width) ;
  }
	  if (role.up) {
		  addAttack(8,-role.height) ;
  }
	  if (role.down) {
		  addAttack(2,role.height/2) ;
  }
}
public void addAttack(int 方向,int 偏移度) {
	
	  Attacka attack;attack = new Attacka(ao.role.x,ao.role.y,240,56,方向); 
	  if(方向 == 6) {
		 attack = new Attacka(role.x+偏移度,ao.role.y,240,56,方向); 
		  attack.seep=attack.seep;
		  role.普攻 = false;
	  }
	  if(方向 == 4) {
		  attack = new Attacka(role.x+偏移度,ao.role.y,240,56,方向); 
		  attack.seep=-(attack.seep);
		  role.普攻 = false;
	  }
	  if(方向 == 2) {
		 attack = new Attacka(role.x+20,ao.role.y+偏移度,56,240,方向); 
		  attack.seep=attack.seep;
		  role.普攻 = false;
	  }
	  if(方向 == 8) {
		  attack = new Attacka(role.x+20,ao.role.y+偏移度,56,240,方向); 
		  attack.seep=-(attack.seep);
		  role.普攻 = false;
	  }
	  ao.koot.add(attack);
}
public void 普攻(int x,int y){
	 int Rx = 0,Ry = 0;
	 double Rhx = 0,Rhy = 0,cosz;
	 	double am = Math.sqrt(ao.role.x*ao.role.x +ao.role.y*ao.role.y);
	 	double bm = Math.sqrt(x*x+y*y);
	 	cosz = Math.cos((ao.role.x*x+ao.role.y*y)/am*bm);
	 	Rx =x-ao.role.ox;
	 	Ry =y-ao.role.oy;
	 	
	 	Map<String, String> map = new HashMap<String, String>();
	 	map.put("name", "adat");
	 	
	 	
	 	//等待前摇
	 	Attacka gj = new Attacka(ao.role.ox, ao.role.oy, 240, 56, 0);
	 	double r = Math.sqrt(Math.pow(x-ao.role.ox, 2)+Math.pow(y-ao.role.oy,2));
			gj.drct = Tools.取角度(x, y, ao.role.ox, ao.role.oy);
			gj.pyzx =Rx/r;
			map.put("turn", String.valueOf(gj.drct));
			map.put("seep", String.valueOf(gj.seep));
			
			//--------------
//			System.out.println(gj.pyzx+"角度："+Math.cos(gj.drct));
			gj.pyzy = Ry/r;
//			System.out.println(gj.pyzy+"角度："+Math.sin(gj.drct));
			BufferedImage img = Tools.rotateImagee(Tools.ro(ao.role.攻击),(int)(gj.drct));
			ao.tools.ropatan(img, gj);gj.getRealXY();
			
			String nameti = String.valueOf(System.currentTimeMillis());
			gj.name = nameti.substring(nameti.length() -6);
			map.put("name", gj.name);
			map.put("X", String.valueOf(gj.rx));
			map.put("Y", String.valueOf(gj.ry));
			gj.img.add(img);
			
			role.ao.adat(map.toString());
			gj.shootat();
			ao.koot.add(gj);
			

}
class Attacka extends Attack {
	public double pyzx ,pyzy;
	double 时间偏移x = 0,时间偏移y = 0;
	double sjpydx = 0,sjpydy = 0;
	int []xz,yz;
	public Attacka(int x,int y,int width,int height,int turn){
		super(x, y, width, height, turn);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.trun = turn;
		this.seep = 20;
		xz = new int[this.distance];yz = new int[this.distance];
		this.penetrate = false;
		
	}
	public void que(Image a) {
		img.add(Tools.rotateImagee(Tools.ro(a),(int)(this.drct)));
	}
	public void tebh(Graphics2D g2) {
		switch (this.trun) {
		case 0:
//			Image im = 
//			g2.rotate(Math.toRadians(this.cosg),role.x,role.y);

			this.rx+=this.xz[this.sked];this.ry+=this.yz[this.sked];
			this.x = this.rx+role.getTx();this.y = this.ry+role.getTy();
			g2.drawImage(this.getimg(), this.x,this.y,null);
			this.sked+=1;
			sjpydx -= (int)(时间偏移x-(int)(this.seep*this.pyzx));
			sjpydy -= (int)(时间偏移y-(int)(this.seep*this.pyzy));
//			g2.drawLine(this.x, this.y, ao.ux, ao.uy);
	
			break;
		case 2://下
		g2.drawImage(new ImageIcon("image/普攻_down.png").getImage(), this.x+25, this.y+=this.seep,this.width, this.height,null);this.sked+=1;
		break;
		case 4://左
		g2.drawImage(new ImageIcon("image/普攻_lelf.png").getImage(), this.x+=this.seep, this.y+25,this.width, this.height,null);this.sked+=1;
		break;
		case 6://右
		g2.drawImage(new ImageIcon("image/普攻_right.png").getImage(), this.x+=this.seep, this.y+25,this.width, this.height,null);this.sked+=1;
		break;
		case 8://上
		g2.drawImage(new ImageIcon("image/普攻_up.png").getImage(), this.x+25, this.y+=this.seep,this.width,this.height,null);this.sked+=1;
		break;
//		case 1:
//		
//		case 3:
//			
//		case 7:
//			
//		case 9:
		}
	
//		g2.drawImage(img, this.x+=sjpydx+时间偏移x, this.y+=sjpydy+时间偏移y,null);this.sked+=1;
	}
	public void shootat() {
		this.时间偏移x=  this.pyzx;
		this.时间偏移y=  this.pyzy;
		for (int i = 0; i < this.distance; i++) {
			xz[i]=(int)(this.时间偏移x*this.seep*(i+1))-(int)(this.时间偏移x*this.seep*i);
			yz[i]=(int)(this.时间偏移y*this.seep*(i+1))-(int)(this.时间偏移y*this.seep*i);
		}
	}
	public void getRealXY () {
	this.rx = Math.abs(role.getTx())+this.x;
	this.ry = Math.abs(role.getTy())+this.y;
   }
	public void getRealXY (int x,int y) {
	this.rx = Math.abs(role.getTx())+x;
	this.ry = Math.abs(role.getTy())+y;
	}
	@Override
	public void dismove() {
		role.ao.utsend("<#NICK_DSAT#>"+this.name);
		super.dismove();
		
	}
	@Override
	public void hurt(Enemy e) {
		Map<String, String> map = new HashMap<String, String>();
	 	map.put("name",this.name);
	 	map.put("role", this.rolename);
	 	map.put("hurt",String.valueOf(role.getAt()));
	 	role.ao.utsend("<#NICK_HURT#>"+e.name+"&"+map.toString());
	}
	
}
   
}



package aoyirole;

import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import aoyirole.龙魂之箭.Attacka;

public class 凌霄之灵 extends Role{

	public 凌霄之灵(GameRole role) {
		super(role);
		img = new ImageIcon("src/image/z.jpg").getImage();
		right = new ImageIcon("src/image/you.jpg").getImage();
		left =new ImageIcon("src/image/zuo.jpg").getImage();
		up = new ImageIcon("src/image/hou.jpg").getImage();
		down = new ImageIcon("src/image/z.jpg").getImage();
		this.rx=-10;this.ry=10;this.rw=20;this.rh=15;
	}

	@Override
	public void 奥义() {
		// TODO 自动生成的方法存根
		super.奥义();
		int ix =ao.ux;
		int iy =ao.uy;
		int rx = ix - role.ox;
		int ry = iy - role.oy;
		普攻((int)(rx*Math.cos(7*Math.PI/180)-ry*Math.sin(7*Math.PI/180)+ix), (int)(rx*Math.sin(7*Math.PI/180)+ry*Math.cos(7*Math.PI/180)+iy)); 
		  普攻((int)(rx*Math.cos(-7*Math.PI/180)-ry*Math.sin(-7*Math.PI/180)+ix), (int)(rx*Math.sin(-7*Math.PI/180)+ry*Math.cos(7*Math.PI/180)+iy));
		  普攻((int)(rx*Math.cos(20*Math.PI/180)-ry*Math.sin(20*Math.PI/180)+ix), (int)(rx*Math.sin(20*Math.PI/180)+ry*Math.cos(20*Math.PI/180)+iy)); 
		  普攻((int)(rx*Math.cos(-20*Math.PI/180)-ry*Math.sin(-20*Math.PI/180)+ix), (int)(rx*Math.sin(-20*Math.PI/180)+ry*Math.cos(20*Math.PI/180)+iy));
	}

	@Override
	public void 普攻(int x, int y) {
		super.普攻(x, y);
		int Rx = 0,Ry = 0;
	 	double r = (double)Math.sqrt(Math.pow(x-ao.role.ox, 2)+Math.pow(y-ao.role.oy,2));
	 	Rx =x-ao.role.ox;
	 	Ry =y-ao.role.oy;
		Boom a =new Boom(ao.role.ox-5, ao.role.oy-5, 10, 10, 0,role);
			a.pyzx =Rx/r;
			a.pyzy = Ry/r;
			a.rx = Math.abs(role.getTx())+a.x;
			a.ry = Math.abs(role.getTy())+a.y;
			a.shootat();
//a.pyzx =Rx/(r/(800/10));
//			a.pyzy = Ry/(r/(800/10));
//			System.out.println(Math.atan2(Rx, Ry));
//			a.initilaze();
		ao.koot.add(a);
		
	}

	@Override
	public void Passive() {
		// TODO 自动生成的方法存根
		super.Passive();
	}

	@Override
	public void skill() {
		// TODO 自动生成的方法存根
		super.skill();
		role.state.Addstate("加速", 50);
	}



}

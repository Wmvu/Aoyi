package aoyirole;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import com.aoyi.Tools;

public class 不羁之灵 extends Role {
	public 不羁之灵(GameRole role) {
		super(role);
		img = new ImageIcon("src/image/z.jpg").getImage();
		right = new ImageIcon("src/image/you.jpg").getImage();
		left =new ImageIcon("src/image/zuo.jpg").getImage();
		up = new ImageIcon("src/image/hou.jpg").getImage();
		down = new ImageIcon("src/image/z.jpg").getImage();
		攻击 = new ImageIcon("src/image/zb.gif").getImage();

		Hp = 50;
		Tp = 0;
		At = 5;   
		z = 60;
		h = 60;
	}

	@Override
	public void 普攻() {
		// TODO 自动生成的方法存根
		super.普攻();
	}

	@Override
	public void 奥义() {

		Attacka a = new Attacka(ao.ux-76/2, ao.uy-76/2, 0, 0, 0) {
			public void tebh(Graphics2D g2) {
				switch (this.trun) {
				case 0:
					this.x = this.rx+role.getTx();this.y = this.ry+role.getTy();
					g2.drawImage(img, this.x, this.y,null);
					this.sked+=1;
					if(sked >=30&&isq == false) {
						qend();
					}
					break;
				}
			
			}
			public void qend() {
				Attacka b =new Attacka(this.x-182/2, this.y-70/2, 346, 144, 0);
				b.isq=true;
				b.img = new ImageIcon("image/gj1.gif").getImage();
				b.distance = 60;
				ao.koot.add(b);
			}
		};
		a.img = new ImageIcon("image/zb.gif").getImage();
		ao.koot.add(a);
	}

	@Override
	public void skill() {
		super.skill();
		role.state.Addstate("加速", 50);
	}

	@Override
	public void 普攻(int x, int y) {
		super.普攻(x, y);
		Attacka a = new Attacka(x-76/2, y-76/2, 0, 0, 0);
		a.img = new ImageIcon("image/zb.gif").getImage();
		ao.koot.add(a);
	}
	
	class Attacka extends Attack {
		public double pyzx ,pyzy;
		public Image img;
		public int ow,oh;
		boolean isq=false;
		public Attacka(int x,int y,int width,int height,int turn){
			super(x, y, width, height, turn);
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
			this.trun = turn;
			seep = 20;distance =30;sked =0;
			getRealXY ();
		}
		public void tebh(Graphics2D g2) {
			switch (this.trun) {
			case 0:
				this.x = this.rx+role.getTx();this.y = this.ry+role.getTy();
				g2.drawImage(img, this.x, this.y,null);
				this.sked+=1;
				if(sked >=29&&isq == false) {
					qend();
				}
				break;
			}
		
		}
		public void qend() {
			Attacka b =new Attacka(x, y, 76, 76, 0);
			b.isq=true;b.img = 攻击;
			ao.koot.add(b);
			
		}
		public void getRealXY () {
			this.rx = Math.abs(role.getTx())+this.x;
			this.ry = Math.abs(role.getTy())+this.y;
		   }
	}

}
 
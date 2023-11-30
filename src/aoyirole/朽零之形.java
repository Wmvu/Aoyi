package aoyirole;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import aoyirole.龙魂之箭.Attacka;
import map.Obstacle;
import com.aoyi.Tools;

public class 朽零之形 extends Role {
	public 朽零之形(GameRole role) {
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
			down = ImageIO.read(new File("image/龙箭_DOWN.png"));
			role.getAniation().addBuffImageList("left", Tools.getImageListPNG("src/image_at/124114350852_10.png",9));
			role.getAniation().addBuffImageList("right", Tools.getImageListPNG("src/image_at/124114350852_10.png",9));
			role.getAniation().addBuffImageList("up", Tools.getImageListPNG("src/image_at/124114350852_10.png",9));
			role.getAniation().addBuffImageList("down", Tools.getImageListPNG("src/image_at/124114350852_10.png",9));
			role.getAniation().addBuffImageList("at", Tools.getImageListPNG("src/image_at/124114350852_10.png",9));
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		Hp = 50;
		Tp = 0;
		At = 5;   
		
	}

	@Override
	public void 普攻() {
		// TODO 自动生成的方法存根
		super.普攻();
	}

	@Override
	public void 奥义(int x, int y) {
		 int Rx = 0,Ry = 0;
		 	Rx =x-ao.role.ox;
		 	Ry =y-ao.role.oy;
		//1.出手前摇，动画管理器等待当前动作执行完成后再改变动画
		
		//2.禁止移动，否定再次冲刺
		role.speen = 0;
		//3.准备完成冲刺的时间，开始技能
		Attacko gj = new Attacko(ao.role.ox, ao.role.oy, 240, 56, 0);
	 	double r = Math.sqrt(Math.pow(x-ao.role.ox, 2)+Math.pow(y-ao.role.oy,2));
			gj.pyzx =Rx/r;
			System.out.println(gj.pyzx+"角度："+Math.cos(gj.drct));
			gj.pyzy = Ry/r;
			System.out.println(gj.pyzy+"角度："+Math.sin(gj.drct));

			ao.koot.add(gj);
		//4技能结束，后摇
		
	}

	@Override
	public void 普攻(int x, int y) {
		// TODO 自动生成的方法存根
//		super.普攻(x, y);
		Attacka a = new Attacka((int)ao.role.showx, (int)ao.role.showy);
		ao.koot.add(a);
		
		
	}

	@Override
	public void skill() {
		// TODO 自动生成的方法存根
		super.skill();
	}

	@Override
	public void skill2() {
		// TODO 自动生成的方法存根
		super.skill2();
	}

	@Override
	public void skill3() {
		// TODO 自动生成的方法存根
		super.skill3();
	}
	@Override
	public void 奥义() {
		int ix =ao.ux;
		int iy =ao.uy;
		int rx = ix - role.ox;
		int ry = iy - role.oy;
		 int Rx = 0,Ry = 0;
		 	Rx =rx-ao.role.ox;
		 	Ry =ry-ao.role.oy;
		//1.出手前摇，动画管理器等待当前动作执行完成后再改变动画
		
		//2.禁止移动，否定再次冲刺
		role.speen = 0;
		//3.准备完成冲刺的时间，开始技能
		Attacko gj = new Attacko(ao.role.ox, ao.role.oy, 240, 56, 0);
	 	double r = Math.sqrt(Math.pow(rx-ao.role.ox, 2)+Math.pow(ry-ao.role.oy,2));
			gj.pyzx =Rx/r;
		
			gj.pyzy = Ry/r;
			

			ao.koot.add(gj);
	}
	class Attacka extends Attack{
		int index=0;
		@Override
		public BufferedImage getimg() {
			// TODO 自动生成的方法存根
			return this.img.get(index/5%9);
		}

		@Override
		public void tebh(Graphics2D g2) {
			// TODO 自动生成的方法存根
//			super.tebh(g2);
			index++;
			this.sked+=1;
			g2.drawImage(this.getimg(),this.x-this.width/2+60, this.y-this.height/2, this.width, this.height, null);
		}

		@Override
		public void atm(Obstacle qg) {
			// TODO 自动生成的方法存根
			super.atm(qg);
		}

		public Attacka(int x, int y) {
			super(x, y, 300, 100, 0);
			this.img = Tools.getImageListPNG("image_at/124114350852_10.png",9);
			this.distance =30;
		}
		
		
	}
	class Attacko extends Attack{
		public double pyzx ,pyzy;
		public Attacko(int x, int y, int width, int height, int turn) {
			super(x, y, 300, 100, 0);
//			this.img = Tools.getImageListPNG("image_at/124114350852_10.png",9);
			this.distance =30;
		}

		int index=0;
		@Override
		public BufferedImage getimg() {
			// TODO 自动生成的方法存根
			return this.img.get(index/5%9);
		}

		@Override
		public void tebh(Graphics2D g2) {
			// TODO 自动生成的方法存根
//			super.tebh(g2);
			role.realX+=this.pyzx;
			role.realY+=this.pyzy;
			this.sked+=1;
			if(this.sked == this.distance-1)role.speen =4;
		}

		@Override
		public void atm(Obstacle qg) {
			// TODO 自动生成的方法存根
			super.atm(qg);
		}

		
	}
}
package moniter;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import aoyirole.Attack;
import com.aoyi.Aoyi;;

public class Mouseiter implements MouseMotionListener,MouseListener{
	public Aoyi ao ;
	public Mouseiter(Aoyi ao) {
		this.ao = ao;
		ao.addMouseMotionListener(this);
	}
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == e.BUTTON3) {
			ao.role.skill();//鼠标右键释放技能
			System.out.println(3);
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO 自动生成的方法存根

	}

	@Override
	public void mousePressed(MouseEvent e) {
			if(e.getButton() == e.BUTTON1) {
				路径进程法(e.getX(), e.getY());
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO 自动生成的方法存根

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO 自动生成的方法存根

	}
public void 路径进程法 (int x ,int y) {
ao.role.shoot(x,y);

}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		ao.ux=e.getX();
		ao.uy=e.getY();
		
	}
	
}

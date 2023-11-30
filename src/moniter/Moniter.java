package moniter;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.aoyi.Aoyi;

import aoyirole.Attack;


public class Moniter extends KeyAdapter{
  public Aoyi ao;
  public Moniter(Aoyi ao) {
	  this.ao =ao;
  }
  public void keyPressed(KeyEvent e) {
	  int direction = e.getKeyCode();
//	  System.out.println(direction);
	  switch(direction) {
	  case 39:
		  ao.role.right = true;
	  break;
	  case 37:
		  ao.role.left =true;
	  break;
	  case 40:
		  ao.role.down =true;
	  break;
	  case 38:
		  ao.role.up = true;
	  break;
	  case 90:
		  ao.role.普攻 = true;
	  break;
	  case 32:
		  ao.role.奥义 = true;  
	  break;
	  case 68:
		  ao.role.right = true;
	  break;
	  case 65:
		  ao.role.left =true;
	  break;
	  case 83:
		  ao.role.down =true;
	  break;
	  case 87:
		  ao.role.up = true;
	  break;
	  }
	  
  }
  public void keyReleased(KeyEvent e) {
	  int dise = e.getKeyCode();
	  switch(dise) {
	  //向右走
	  case 39:
	   ao.role.right = false;
	  break;
	  //向左走
	  case 37:
	   ao.role.left =false;
	  break;
	  //向下走
	  case 40:
	   ao.role.down =false;
	  break;
	  //向上走
	  case 38:
	   ao.role.up = false;
	  break;
	  case 90:  
	   ao.role.普攻 = false;
	  break;
	  case 32:  
	   ao.role.奥义 = false;
	  break;
	  //向右走
	  case 68:
	   ao.role.right = false;
	  break;
	  //向左走
	  case 65:
	   ao.role.left =false;
	  break;
	  //向下走
	  case 83:
	   ao.role.down =false;
	  break;
	  //向上走
	  case 87:
	   ao.role.up = false;
	  break;
	  }
  }

  /*
	public void mouseReleased(MouseEvent e) {
		// TODO 自动生成的方法存根
			if(e.getButton()==e.BUTTON3){    // 判断获取的按钮是否为鼠标的右击     
           System.out.println(e.getX()+","+e.getY());     // 获得鼠标点击位置的坐标并发送到标签的文字上
           
			}
	}
	*/
}

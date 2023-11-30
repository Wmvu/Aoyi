package aoyirole;

import java.awt.Image;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.ImageIcon;

import com.aoyi.Aoyi;

public abstract class Role {
	public GameRole role;
	public Aoyi ao;
	public int Hp, MaxHp;// 生命值和生命上限
	public int Lv = 1;// 等级
	public int Tp = 0, maxTp = Lv * 50;// 经验值
	public int At;
	public Image img;
	public Image 攻击;
	public Image gjicon, ayicon;
	public Image right, left, up, down;
	public int z, h;
	public double rx = -15, ry = 28, rw = 20, rh = 15;// 这里填写角色的碰撞椭圆
	public byte Race;

	public Role(GameRole role) {
		this.role = role;
		this.ao = role.ao;
	}

	public void 普攻() {

	}

	public void 奥义() {

	}
	public void 奥义(int x, int y) {

	}

	public void 普攻(int x, int y) {
		// TODO 自动生成的方法存根

	}

	public void Passive() {

	}

	public void skill() {// 技能

	}
	public void skill(int x, int y) {// 技能

	}
	public void skill2() {

	}
	public void skill2(int x, int y) {

	}

	public void skill3() {

	}
	public void skill3(int x, int y) {

	}
	public void Awake() {

	}

	public void Levelup() {//升级

	}

	public String hurt() {
		return null;
		// 伤害
		
	}
	public void inh(int h) {
		this.Hp -= h;
		role.HP = this.Hp;
		if(this.Hp <=0) {
			Map<String, String> map = new HashMap<String, String>();
		 	map.put("name",ao.getGLName());
//		 	map.put("role", this.rolename);
		 	map.put("detime",String.valueOf(10+role.LV));
		 	
		 	ao.utsend("<#NICK_DEAH#>"+map.toString());
		 	role.rsuvivul =0;
		 	role.isdeath();
		}
	}
	public void injured(Map<String, String> hur) {
		//受伤
		Iterator<Map.Entry<String, String>> iterator = hur.entrySet().iterator();
		while(iterator.hasNext()) {
			Map.Entry<String, String>eny =	(Entry<String, String>) iterator.next();
			String key = eny.getKey();
			switch (key) {
			case "hurt":
				this.inh(Integer.valueOf(eny.getValue()));
				
				break;
				
			default:
				break;
			}
		}
	}
	
	public void attribute() {

	}
}

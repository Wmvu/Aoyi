package com.aoyi;

import java.awt.Image;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Iterator;
import java.util.Map;

import aoyirole.Role;
import teamandenemy.Enemy;
import teamandenemy.Enemyshook;

public class Enemythare extends Thread{
	Aoyi aoyi;
	Socket socket;
	DataInputStream in;
	MulticastSocket udp;
	DatagramPacket upack;
	boolean flag = true;
	byte []buff;
	public Enemythare(Aoyi ao) {
		// TODO 自动生成的构造函数存根
		this.aoyi = ao;
		this.socket = aoyi.socket;
		this.in = new DataInputStream(aoyi.in);
		
			try {
				this.udp = new MulticastSocket(2023);
				this.udp.joinGroup(InetAddress.getByName("233.233.233.233"));

			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		
		
	}
@Override
	public void run() {
	while (flag) {
			try {
				String msg = in.readUTF().trim();
//				System.out.println(msg);
				if(msg.startsWith("<#NICK_LIST#>")) {//在线玩家列表
					this.add_enemy(msg);
				}else if(msg.startsWith("<#NICK_ZBSF#>")) {//玩家坐标
//					this.data_add(msg);
					this.updata(msg);
				}else if(msg.startsWith("<#NICK_ADAT#>")) {//添加攻击
					this.addtook(msg);
				}else if(msg.startsWith("<#NICK_HURT#>")) {//收到伤害
					this.injured(msg);
				}else if(msg.startsWith("<#NICK_ARBT#>")) {
					this.attribute(msg);//属性
				}else if(msg.startsWith("<#NICK_DSAT#>")) {//<#NICK_DEAH#>
					this.upatade(msg);//消失
				}else if(msg.startsWith("<#NICK_DEAH#>")) {//死亡
					this.setdaet(msg);
				}
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}

	}
	}
	private void setdaet(String msg) {
		String data =  msg.substring(13);
		Map<String, String> map = Tools.strtomap(data);
		data = map.get("name");
		for(int i = 0;i<aoyi.enemy.size();i++) {
			Enemy e = aoyi.enemy.get(i);
			if(data.equals(e.name)) {
				//预留方法
				e.setdicdec(true);
			}
		}
	
}
	private void upatade(String msg) {
		String data =  msg.substring(13);
//		Map<String, String> map = Tools.strtomap(data);
//		data = map.get("name");
		for(int i = 0;i<aoyi.enemyshook.size();i++) {
			Enemy e = aoyi.enemyshook.get(i);
			if(data.equals(e.name))e.setdicdec(true);
		}
}
	private void attribute(String msg) {
		String data =  msg.substring(13);
		Map<String, String> map = Tools.strtomap(data);
		String name = map.get("name");
		for(int i = 0;i<aoyi.enemy.size();i++) {
			Enemy e = aoyi.enemy.get(i);
			if(name.equals(e.name)) {
				e.Lv = Integer.valueOf(map.get("lv"));
				e.Hp = Integer.valueOf(map.get("hp"));
				e.maxHp = Integer.valueOf(map.get("maxhp"));
				e.tp = map.get("tp");
				e.anim = map.get("anie");
				e.zs = map.get("zsate");
				
				break;
			}
		}
}
	private void injured(String msg) {
		String data =  msg.substring(13);
		Map<String, String> map = Tools.strtomap(data);
		aoyi.role.injured(map);
}
	private void addtook(String msg) {
		String data =  msg.substring(13);
		Map<String, String> map = Tools.strtomap(data);
		Enemyshook es = new Enemyshook(aoyi.role.攻击,Integer.valueOf(map.get("X")), Integer.valueOf(map.get("Y")),Double.valueOf(map.get("turn")), Integer.valueOf(map.get("seep")));
		es.name = map.get("name");
		aoyi.enemyshook.add(es);
		
}
	private void updata(String msg) {
		String data =  msg.substring(13);
		for(int i = 0;i<aoyi.enemy.size();i++) {
			Enemy e = aoyi.enemy.get(i);
			if(data.startsWith(e.name)) {
				int size = e.name.length();
				String updata = data.substring(size);
				String datazb [] = updata.split(",");
//		System.out.println(size +"|"+ data +"|"+updata);
		e.x = Integer.parseInt(datazb[0])+aoyi.back.x;
		e.y = Integer.parseInt(datazb[1])+aoyi.back.y;
			}
			
		}
	
}

	public void add_enemy(String msg) {
		String name = msg.substring(13);
		String namelist [] = name.split("\\|");
//		System.out.println(namelist.length);
		for (int i = 1; i < namelist.length; i++) {
			String string = namelist[i];
			if(!string.equals(Window.name)) {
			Enemy enemy = new Enemyshook(0,0,142,100,aoyi.role.role);
			enemy.name = string;
			enemy.Lv = 1;
			enemy.Hp = 50;
			enemy.maxHp = 50;
			aoyi.enemy.add(enemy);
			}
		}
		/*
		for (int i = 0; i < namelist.length; i++) {
			String string = namelist[i];
			boolean you_no = true;
			for(int j =0;0<aoyi.enemy.size();j++) {
				Enemy e =aoyi.enemy.get(j);
				String a = e.name;System.out.println("有用户"+a);
				if(string.equals(Window.name)||string.equals(e.name)) {
					you_no = false;
					break;
				}
			}
			if(you_no!=false) {
			Enemy enemy = new Enemyshook(0,0,142,100,aoyi.role.role);
			enemy.name = string;
			aoyi.enemy.add(enemy);
			}
			System.out.println(string);
			}*/
		}
	public void ueda() {
		
		for(int i = 0;i<Window.onlist.length;i++) {
			String msg = Window.onlist[i];
			if (msg!= Window.name) {
				msg = "<#NICK_LIST#>"+ Window.onlist[i];this.add_enemy(msg);
			}
			
		}
	}
	public void data_add(String msg) {
		String data =  msg.substring(13);
		String guest[] = Window.onlist;
		boolean 是否需要判断=true;
		for (int j = 0; j < aoyi.enemy.size(); j++) {
			String nar = aoyi.enemy.get(j).name;
			if(data.startsWith(nar)) {
				int size = aoyi.enemy.get(j).name.length();
				String updata = data.substring(size);
				String datazb [] = updata.split(",");
//		System.out.println(size +"|"+ data +"|"+updata);
		aoyi.enemy.get(j).x = Integer.parseInt(datazb[0])+aoyi.back.x;
		aoyi.enemy.get(j).y = Integer.parseInt(datazb[1])+aoyi.back.y;
				是否需要判断=false;
				break;
			}
				
				
				
		}
		
		if(是否需要判断 ==true) {
			for (int i = 0; i < guest.length; i++) {
				String string = guest[i];
				boolean art = false;
				
				for(int i1 = 0;i1<aoyi.enemy.size();i1++) {
					Enemy e = aoyi.enemy.get(i1);
					if(string.startsWith(e.name)) {
						art = true;
						break;
					}
					
				}
				if(art !=true) {
					String name = string;
					if(!name.equals(Window.name)) {
//					Enemy enemy = new Enemyshook(0,0,142,100,aoyi.role.role);
//					enemy.name = name;
//					aoyi.enemy.add(enemy);
					}
				}

		}
		}

		
	}
}

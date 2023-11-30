package aoyirole;

import java.util.ArrayList;

public class Statea extends Thread{
GameRole role;
ArrayList<Zstate>state = new ArrayList<Zstate>();
  Statea(GameRole role){
	this.role = role;
  }

@Override
public void run() {	
	while(true) {
	for (int i = 0; i < state.size(); i++) {
		Zstate s =state.get(i);
		s.running();	
		if(s.time>=s.maxtime) {System.out.println(123);
					s.delete();
					state.remove(i);
					};	
		}
	try {
		Thread.sleep(100);
	} catch (InterruptedException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	}
	  }
	}

public void Addstate(String name,int time) {
	if(name.equals("加速")) {
		Addstate(1,time);
	}
	}
public void Addstate(int index,int time) {
	switch (index) {
	case 1://移动速度提升80%
		state.add(new Zstate(time) {
			public void inilitle(){
				ccsj = (int) (role.speen*0.8);
				role.speen += ccsj;
			}

			public void delete() {
				role.speen = role.speen -ccsj;
				
			}
		});
		break;

	default:
		break;
	}
	}
}

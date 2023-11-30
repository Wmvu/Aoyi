package aoyirole;

public class UniactManager {//唯一行动管理器
	private static volatile UniactManager uniactManager;
	
	private boolean first_att,end_att;
	
	public static UniactManager getuniactmanager() {
		if(uniactManager == null) {
			synchronized (UniactManager.class) {
				if(uniactManager == null) {
					return new UniactManager();
				}
			}
		}
		return uniactManager;	
	}
	//====================以上为双检查锁实现单例===================
//	private static class UniAct{
//		private static final UniactManager INSTANCE = new UniactManager();
//		
//	}
//	public static UniactManager getuniactmanager() {
//		return UniAct.INSTANCE;	
//	}
	//以上为静态类实现
	
	public boolean getallact() {//允许动作变更
		if(first_att || end_att)
			return false;
		return true;
	}
	
	public void attclk() {
		this.first_att = true;
	}
	
}

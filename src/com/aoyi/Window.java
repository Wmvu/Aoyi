package com.aoyi;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import moniter.Moniter;
import moniter.Mouseiter;

public class Window extends JFrame{
	private static final long serialVersionUID = 1L;
	public Socket socket;
	PrintWriter os;
	JTextArea jt;
	DataOutputStream ou;
	DataInputStream in;
	byte []ab;
	JButton 开始游戏,本地游戏;
	ActorList ac;
	public static String name;
	public static JTextField fwqdz;
	public static JTextField fwqdkh;
	public JTextField yhm;
	public JTextField ex;
	public JButton ljfwq,zxyh;
	public static boolean gsm = true;
	public Vector v = new Vector();
	public JLabel fwqmc; 
	public JButton jb;
	public static String Character;
	static String[] onlist = {};
	Thread speaksa;
	public Window() {
		ab = new byte[1024];
		开始游戏 = new JButton("游戏开始");
		Container cr = this.getContentPane();
		setLayout(null);
		setSize(1000, 700);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(Window.EXIT_ON_CLOSE);
		setTitle("已知名                             V 1.0.1");
		jt = new JTextArea(20,20);
		JScrollPane jc = new JScrollPane(jt);jt.setEditable(false);
		ex = new JTextField(10);ex.setEditable(false);
		JPanel ljfwqmb = new JPanel(null);
		JLabel qsrdz = new JLabel("服务器地址:");qsrdz.setBounds(10, 0, 80, 40);
		JLabel qsrdkh = new JLabel("端口号:");qsrdkh.setBounds(10, 30, 80, 40);
		JLabel qsryhm = new JLabel("用户名:");qsryhm.setBounds(10, 60, 80, 40);
		fwqdz = new JTextField("127.0.0.1");fwqdz.setBounds(90, 10, 100, 22);
		fwqdkh = new JTextField("2020");fwqdkh.setBounds(90, 40, 100, 22);
		yhm = new JTextField(5);yhm.setBounds(90, 70, 100, 22);
		ljfwq = new JButton("连接服务器");ljfwq.setBounds(60, 100, 100, 28);
		jb = new JButton("发送");jb.setEnabled(false);
		zxyh = new JButton("在线用户");zxyh.setBounds(860, 430, 113, 25);zxyh.setEnabled(false);开始游戏.setEnabled(false);
		fwqmc = new JLabel("服务器名：");fwqmc.setBounds(10, 10, 150, 25);
		本地游戏 = new JButton("本地游戏");本地游戏.setBounds(30, 590, 130, 45);
		JButton 角色 = new JButton("角色");角色.setBounds(30, 540, 90, 38);
		角色.setContentAreaFilled(false);
		本地游戏.setContentAreaFilled(false);
		开始游戏.setContentAreaFilled(false);
		jb.setContentAreaFilled(false);
		ljfwqmb.add(qsrdz);
		ljfwqmb.add(qsrdkh);
		ljfwqmb.add(qsryhm);
		ljfwqmb.add(fwqdkh);
		ljfwqmb.add(fwqdz);
		ljfwqmb.add(ljfwq);
		ljfwqmb.add(yhm);
		cr.add(fwqmc);
		cr.add(jc);
		cr.add(ex);
		cr.add(jb);
		cr.add(ljfwqmb);
		cr.add(zxyh);
		cr.add(本地游戏);
		cr.add(角色);
		cr.add(开始游戏);开始游戏.setBounds(this.getWidth()/2-50, this.getHeight()/2-25, 100, 50);
		jc.setBounds(this.getWidth()-320, this.getHeight()-240, 300, 150);
		ex.setBounds(this.getWidth()-320, this.getHeight()-80, 220, 22);
		jb.setBounds(this.getWidth()-95,this.getHeight()-88,70,35);
		ljfwqmb.setBounds(750,200,200,130);
setVisible(true);
	Character ="1";
zxyh.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				for (int i = 1; i < onlist.length; i++) {
					String b = onlist[i];
					String ss = jt.getText()+b;
				jt.setText(ss+"\n");
				}
				jt.setText(jt.getText() +"当前在线用户总数:"+(onlist.length -1)+"\n");
				
			}
		});
		ljfwq.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
			
				
				fwqdz.setEnabled(false);
				fwqdkh.setEnabled(false);
				yhm.setEditable(false);
				ljfwq.setEnabled(false);
//				readq();
				ex.setEditable(true);
				jb.setEnabled(true);
				loadserver();
				fwqmc.setText("服务器名称：始源之石");
			}
		});
		本地游戏.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Aoyi ao1 = new Aoyi(0);
					Moniter a1 = new Moniter(ao1);
					Mouseiter b1 = new Mouseiter(ao1);
					ao1.addKeyListener(a1);
					ao1.addMouseListener(b1);
//					setContentPane(ao1);
				} catch (Exception e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
		});
		开始游戏.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				try {
					 gsm = false;
//					 ou.writeUTF("<#NICK_STAR#>");
					 speaksa.stop();
					 in.close();
					 ou.close();
					 socket.close();
					AoyiGameFrame.zcjr();
	
				} catch (Exception e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
		});
		
		jb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				
				try {
					ou.writeUTF(ex.getText());
					ou.flush();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				String ss = jt.getText()+ex.getText()+"\n";
				jt.setText(ss);
				ex.setText("");
			
			}
		});
		角色.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				actor();
			}
		});
		
//		readq();
	}
	void actor() {
			ac = new ActorList(this);
			ac.setLocation((this.getWidth()-ac.getWidth())/2, (this.getHeight()-ac.getHeight())/2);
			this.add(ac,getComponentCount()-1);
			System.out.println(getComponentCount());
//			this.setContentPane(ac);
			this.repaint();

		}
	public void actordiess() {
		this.remove(ac);
		this.repaint();
	}
	void loadserver(){
		String address =fwqdz.getText().trim();
		int pork =Integer.parseInt(fwqdkh.getText().trim());
		String houstname =yhm.getText().trim();
		try {
			socket = new Socket(address, pork);
			PrintStream po = new PrintStream(new BufferedOutputStream(socket.getOutputStream()));
			os = new PrintWriter(socket.getOutputStream());
			
			in = new DataInputStream(socket.getInputStream());
			ou = new DataOutputStream(socket.getOutputStream());
//			System.out.println(houstname.length());
			if(houstname.length()<1) {
			name =new String( new DecimalFormat("#.00").format((Math.random()*10)));
			yhm.setText(name);
			}else {name = houstname;}
			ou.writeUTF("<#SRCE_NAME#>");
			ou.writeUTF("<#NICK_NAME#>"+ name);
			ou.flush();
		  speaksa =	new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					while (gsm) {
						readq();
					}
				}
			});
		  speaksa.start();
			jt.setText("服务器连接成功"+"\n");
			zxyh.setEnabled(true);开始游戏.setEnabled(true);
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			jt.setText("服务器连接失败"+"\n");
			fwqdz.setEnabled(true);
			fwqdkh.setEnabled(true);
			yhm.setEditable(true);
			ljfwq.setEnabled(true);
			ex.setEditable(false);
			jb.setEnabled(false);
			e.printStackTrace();
		}
	}
	void readq () {
		 try {
			 if(Aoyi.qwe !=false) {
				String ss = in.readUTF();
			 if(ss.startsWith("<#NICK_LIST#>")) {
			 String onlinelist = ss.substring(13);
			  onlist = onlinelist.split("\\|");
			 }else if(ss.startsWith("<#SRCE_NAME#>")) {
				 fwqmc.setText("服务器名称："+ss.substring(13));
			 }else if(ss.startsWith("<#NICK_ZBSF#>")) {
//				 String a = jt.getText()+ss.substring(13)+"\n";
//					jt.setText(a);
			 }else {jt.setText(jt.getText()+ss+"\n");	}}
//			BufferedReader is = new BufferedReader(new InputStreamReader());
//			System.out.println(is.read());
			
		} catch (IOException e) {
			System.out.println("Window.readq方法抛出异常");
		}
	}
	void ActorSDK() {
//		Character =Integer.parseInt(JOptionPane.showInputDialog(null, "输入你的角色码","请选择角色",JOptionPane.YES_NO_CANCEL_OPTION));
	}
}

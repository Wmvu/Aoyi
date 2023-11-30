package com.aoyi;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ActorList extends JPanel implements ActionListener{
Window f;
JButton IDK,RETURN;
String Character;
	public ActorList(Window f) {
		this.f =f; 
		initialize();
	}
	public void initialize() {
		this.setSize(500, 500);
		this.setBackground(Color.BLACK);
//		this.setVisible(true);
		newcomman();
	}
	private void newcomman() {
		IDK = new JButton("IDk");IDK.setBounds(400, 460, 80, 30);
		RETURN = new JButton("返回");RETURN.setBounds(430, 10, 60, 30);
		this.add(IDK);
		this.add(RETURN);
		IDK.addActionListener(this);
		RETURN.addActionListener(this);
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		if (e.getSource() == this.IDK) {
			Character = JOptionPane.showInputDialog(null, "输入你的角色码","请选择角色",JOptionPane.YES_NO_CANCEL_OPTION);
			if (Character.equals("1")||Character.equals("2")||Character.equals("3")||Character.equals("4")) {
			}else {Character ="1";
				}

			Window.Character = Character;
//			return;
		}else if (e.getSource() == this.RETURN) {
			f.actordiess();
		}
		
	}
	
}

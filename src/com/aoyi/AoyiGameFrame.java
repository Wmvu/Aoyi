package com.aoyi;


import java.awt.FlowLayout;
import java.io.DataInputStream;
import java.net.Socket;

import moniter.Moniter;
import moniter.Mouseiter;

public class AoyiGameFrame {

	public static void main(String[] args) throws Exception {
		new Window();
//		zcjr();
	}
	public static void zcjr() throws Exception {
		Aoyi ao = new Aoyi ();
		Moniter a = new Moniter(ao);
		Mouseiter b = new Mouseiter(ao);
		ao.addKeyListener(a);
		ao.addMouseListener(b);
	}
}

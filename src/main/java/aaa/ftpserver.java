package aaa;

import java.io.*;
import java.net.*;

public class ftpserver {

	String vpname = "C:\\1.mp3";
	ServerSocket ss;
	Socket cs = null;
	OutputStream out;
	byte data[] = null;

	public ftpserver() {

		try {
			ss = new ServerSocket(5550);

		} catch (Exception ex) {
			System.out.println(ex.toString() + "������������");
		}

		while (true) {
			try {
				cs = ss.accept();

				if (cs == null) {
				} else {

					out = cs.getOutputStream();
					FileInputStream fi = new FileInputStream(vpname);

					System.out.println(fi.available());
					data = new byte[8192];
					int read = 0;
					while (true) {
						if (fi != null) {
							read = fi.read(data);
						}
						if (read == -1) {
							break;
						}
						System.out.println("��������ÿ�ζ�ȡ�ͷ��͵��ֽ���====*" + read);
						out.write(data, 0, read);

					}

					out.flush();
					fi.close();
					out.close();
					cs.close();

					System.out.println("�ļ�����������������ֽ�����");

				}

			} catch (Exception ex1) {
				System.out.println("�����ˣ�����" + ex1.toString());
			}
		}

	}

	public static void main(String[] args) {
		System.out.println("�ļ����俪ʼ.....");
		ftpserver csmode = new ftpserver();
		

	}

}

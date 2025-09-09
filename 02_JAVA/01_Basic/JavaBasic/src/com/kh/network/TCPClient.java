package com.kh.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 요청하고자 하는 서버의 위치를 알아야 함
		// ip + port
		String serverIP ="192.168.10.16";
		int port = 3000;
		BufferedReader br = null;
		PrintWriter pw = null;
		
		try {
			// 1) 서버로 연결 요청(서버의 ip와 port로 연결을 요청)
			Socket socket = new Socket(serverIP,port);
			if(socket != null) {
				System.out.println("서버 연결 성공");
				// 입력용 스트림 (클라이언트 -> 서버)
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

				// 출력용 스트림 (서버 -> 클라이언트)
				pw = new PrintWriter(socket.getOutputStream());
				
				while(true) {
					System.out.print("서버로 보낼 내용 : ");
					String sendMessage = sc.nextLine();
					pw.println(sendMessage);
					pw.flush();
					
					String message = br.readLine();
					System.out.println("서버로부터 전달받은 메세지 : "+message);
				}
				
			}
		} catch(IOException e){
			e.printStackTrace();
		}
		
		
	}

}

package com.kh.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TCPServer {
	/*
	 * TCP
	 * - 서버, 클라이언트와 1:1 소켓통신
	 * - 데이터를 교환하기에 앞서 서버, 클라이언트 연결이 되어있어야 한다.
	 *   (서버가 먼저 실행되어 있어야 클라이언트의 연결 요청을 받을 수 있음)
	 *
	 * Socket
	 * - 프로세스간의 통신을 담당
	 * - input/outputStream을 가지고 있음 (해당 스트림을 통해 데이터 입출력 가능)
	 *
	 * ServerSocket
	 * - 포트와 연결되어 외부의 연결 요청을 기다림 → 요청이 들어오면 수락
	 *   → 통신할 수 있는 socket 생성
	 */
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int port = 3000; // 1) 포트번호 지정
		ServerSocket server = null;
		Socket socket = null;
		BufferedReader br = null;
		PrintWriter pw = null;

		try {
			server = new ServerSocket(port);
			System.out.println("클라이언트 요청을 기다립니다...");

			socket = server.accept(); // 연결 수락
			InetAddress clientIp = socket.getInetAddress();
			System.out.println(clientIp.getHostAddress() + " 가 연결을 요청함...");

			// 입력용 스트림 (클라이언트 -> 서버)
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			// 출력용 스트림 (서버 -> 클라이언트)
			pw = new PrintWriter(socket.getOutputStream());

			while (true) {
				String message = br.readLine();
				if (message == null) break; // 클라이언트 종료 시
				System.out.println("클라이언트로부터 전달받은 메세지 : " + message);

				System.out.print("클라이언트로 보낼 내용 : ");
				String sendMessage = sc.nextLine();

				pw.println(sendMessage);
				pw.flush();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 자원 정리
			try {
				if (pw != null) pw.close();
				if (br != null) br.close();
				if (socket != null) socket.close();
				if (server != null) server.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

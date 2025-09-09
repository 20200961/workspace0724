package com.kh.example.collection1;

import java.util.List;
import java.util.Scanner;

public class MusicView {
    private Scanner sc = new Scanner(System.in);
    private MusicController mc = new MusicController();

    public void mainMenu() {
        while (true) {
            System.out.println("***** 메인 메뉴 *****");
            System.out.println("1. 마지막 위치에 곡 추가");
            System.out.println("2. 첫 위치에 곡 추가");
            System.out.println("3. 전체 곡 목록 출력");
            System.out.println("4. 특정 곡 검색");
            System.out.println("5. 특정 곡 삭제");
            System.out.println("6. 특정 곡 정보 수정");
            System.out.println("7. 곡명 오름차순 정렬");
            System.out.println("8. 가수명 내림차순 정렬");
            System.out.println("9. 종료");
            System.out.print("메뉴 번호 입력: ");

            int sel = Integer.parseInt(sc.nextLine());
            switch (sel) {
                case 1: addList(); break;
                case 2: addAtZero(); break;
                case 3: printAll(); break;
                case 4: searchMusic(); break;
                case 5: removeMusic(); break;
                case 6: setMusic(); break;
                case 7: ascTitle(); break;
                case 8: descSinger(); break;
                case 9: System.out.println("프로그램 종료"); return;
                default: System.out.println("잘못 입력하셨습니다.");
            }
            System.out.println();
        }
    }

    public void addList() {
        System.out.print("곡명 입력: ");
        String title = sc.nextLine();
        System.out.print("가수 입력: ");
        String singer = sc.nextLine();
        int result = mc.addList(new Music(title, singer));
        System.out.println(result == 1 ? "추가 성공" : "추가 실패");
    }

    public void addAtZero() {
        System.out.print("곡명 입력: ");
        String title = sc.nextLine();
        System.out.print("가수 입력: ");
        String singer = sc.nextLine();
        int result = mc.addAtZero(new Music(title, singer));
        System.out.println(result == 1 ? "추가 성공" : "추가 실패");
    }

    public void printAll() {
        List<Music> list = mc.printAll();
        if (list.isEmpty()) System.out.println("등록된 곡이 없습니다.");
        else for (Music m : list) System.out.println(m);
    }

    public void searchMusic() {
        System.out.print("검색할 곡명 입력: ");
        String title = sc.nextLine();
        Music m = mc.searchMusic(title);
        System.out.println(m != null ? "검색 결과: " + m : "검색한 곡이 없습니다.");
    }

    public void removeMusic() {
        System.out.print("삭제할 곡명 입력: ");
        String title = sc.nextLine();
        Music m = mc.removeMusic(title);
        System.out.println(m != null ? m + " 을(를) 삭제했습니다." : "삭제할 곡이 없습니다.");
    }

    public void setMusic() {
        System.out.print("수정할 곡명 입력: ");
        String oldTitle = sc.nextLine();
        System.out.print("새 곡명 입력: ");
        String newTitle = sc.nextLine();
        System.out.print("새 가수명 입력: ");
        String newSinger = sc.nextLine();
        Music old = mc.setMusic(oldTitle, new Music(newTitle, newSinger));
        System.out.println(old != null ? old + " 의 정보가 변경되었습니다." : "수정할 곡이 없습니다.");
    }

    public void ascTitle() {
        
    }

    public void descSinger() {
    	
    }
}
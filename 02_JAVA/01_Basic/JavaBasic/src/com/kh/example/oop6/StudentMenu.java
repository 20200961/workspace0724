package com.kh.example.oop6;

public class StudentMenu {
	private StudentController sc = new StudentController();
	
	public StudentMenu() {
		Student[] stArr = sc.printStudent();
		
		System.out.println("======= 학생 정보 출력 =======");
		for(Student st : stArr) {
			if(st ==null) {
				break;
			}
			System.out.println(st.inform());
		}
		
		double[] scoreArr = sc.avgScore();
		System.out.println(scoreArr[0]);
		System.out.println(scoreArr[1]);
		
		
		System.out.println("======= 학생 성적 출력 =======");
		
		System.out.println("학생 점수 합계 : " + scoreArr[0]);
		System.out.println("학생 점수 평균 : " + scoreArr[1]);
		System.out.println("======= 성적 결과 출력 =======");
		for(Student st : stArr) {
			if(st.getScore() < sc.CUT_LINE) {
				System.out.println("재시험 대상");
			} else {
				System.out.println("통과");
			}
			
			System.out.printf("%s학생은 %s입니다.",st.getName(),st.getScore()<sc.CUT_LINE ? "재시험 통과" : "통과");
		}
	}
}

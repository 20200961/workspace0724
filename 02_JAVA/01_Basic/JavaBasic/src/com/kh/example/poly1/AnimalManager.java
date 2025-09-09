package com.kh.example.poly1;

public class AnimalManager {
	public static void main(String[] args) {
		Animal[] animals = new Animal[5];
		animals[0] = new Dog("진돗개",1,"진돗개");
		animals[1] = new Cat("검정색",2,"검정색");
		animals[2] = new Dog("삽살개",3,"삽살개");
		animals[3] = new Dog("진돗개",4,"진돗개");
		animals[4] = new Cat("흰색",5,"흰색");
		
		for(Animal a : animals) {
			a.speak();
			
			if(a instanceof Dog) {
				Dog d = (Dog) a;
				System.out.println("이 개의 견종은 "+d.getBreed()+"입니다");
			} else if(a instanceof Cat) {
				Cat c = (Cat) a;
				System.out.println("이 고양이의 색상은 "+c.getColor()+"입니다");
			}
			
		}
			
		
		
		
	}
}

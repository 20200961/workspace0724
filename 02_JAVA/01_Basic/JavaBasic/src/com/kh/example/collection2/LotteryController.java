package com.kh.example.collection2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.TreeSet;

public class LotteryController {
	private HashSet<Lottery> lottery = new HashSet<>();
	private HashSet<Lottery> win = new HashSet<>();
	
	public boolean insertObject(Lottery l){ 
		return lottery.add(l);
	} 
	public boolean deleteObject(Lottery l){ 
		boolean isRemove = lottery.remove(l);
		if(win != null && isRemove) {
			win.remove(l);
		}
		return isRemove;
	} 
	 
	public HashSet winObject(){ 
		// 남은 인원만 선발
		if(win.size()<4) {
			Random rand = new Random();
			
			ArrayList<Lottery> list = new ArrayList<>();
			list.addAll(lottery);
			while(win.size()<4 && win.size() != lottery.size()) {
				int index = rand.nextInt(list.size()); // 0~size() -1 중 랜덤으로 정수 추출
				win.add(list.get(index));
			}
		}
		return win;
	} 
	public TreeSet sortedWinObject(){ 
		return null;
	
	} 
	  
	public boolean searchWinner(Lottery l){ 
		return win.contains(l);
	
	} 
} 



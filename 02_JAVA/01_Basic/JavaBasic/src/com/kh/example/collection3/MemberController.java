package com.kh.example.collection3;

import java.util.HashMap;

public class MemberController {
	HashMap<String, Member> map = new HashMap<>();
			
	public boolean joinMembership(String id, Member m) {
		if(id != null) {
			map.put(id, m);
			return true;
		} else {
			return false;
		}
	}
	
//	public String logIn(String id, String password) {
//		if(id != null) {
//			return m.name;
//		} else {
//			return null;
//		}
//	}
//	
//	public boolean changePassword(String id, String oldPw, String newPw) {
//		if() {
//			return true;
//		}
//	}
			
			
}

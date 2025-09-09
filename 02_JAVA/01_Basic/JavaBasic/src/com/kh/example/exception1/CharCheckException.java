package com.kh.example.exception1;

public class CharCheckException extends Exception {

    public CharCheckException() {
        super("잘못된 문자가 포함되어 있습니다.");
    }

    public CharCheckException(String message) {
        super(message);
    }
}

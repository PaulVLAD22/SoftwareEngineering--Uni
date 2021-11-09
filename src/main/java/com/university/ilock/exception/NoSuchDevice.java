package com.university.ilock.exception;

public class NoSuchDevice extends RuntimeException{
    public NoSuchDevice(){
        super("There is no such device");
    }
}

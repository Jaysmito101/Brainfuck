package com.jaysmito.brainfuck;

import java.util.ArrayList;
import java.io.*;

public class Brainfuck32{
	private String code;
	private ArrayList<Integer> memory;
	private int ptr;
	private int maxMem;
	private OutputStream outStream;
	private InputStream inStream;
	private PrintStream writter;

	public Brainfuck32(String code){
		this.code = code;
		this.ptr = 0;
		this.maxMem = 30000;
		this.memory = new ArrayList<Integer>(maxMem);
		this.outStream = System.out;
		this.inStream = System.in;
		writter = new PrintStream(System.out);
	}

	public Brainfuck32(String code, int maxMem){
		this.code = code;
		this.ptr = 0;
		this.memory = new ArrayList<Integer>(maxMem);
		this.maxMem = maxMem;
		this.outStream = System.out;
		this.inStream = System.in;
		writter = new PrintStream(this.outStream);
	}

	public Brainfuck32(String code, InputStream inStream, OutputStream outStream){
		this.code = code;
		this.ptr = 0;
		this.maxMem = 30000;
		this.memory = new ArrayList<Integer>(maxMem);
		this.outStream = outStream;
		this.inStream = inStream;
		writter = new PrintStream(this.outStream);
	}

	public Brainfuck32(String code, int maxMem, InputStream inStream, OutputStream outStream){
		this.code = code;
		this.ptr = 0;
		this.memory = new ArrayList<Integer>(maxMem);
		this.maxMem = maxMem;
		this.outStream = outStream;
		this.inStream = inStream;
		writter = new PrintStream(this.outStream);
	}

	protected void finalize(){
		try{
			
		}catch(Exception ex){
		}
	}

	public String getCode(){
		return this.code;
	}

	public void execute(){
		memory.add(0);
		char instruction;
		boolean isComment = false;
		code = code.trim() + " ";
		for(int i = 0 ; i < code.length() - 1 ; i++){
			instruction = code.charAt(i);
			if(instruction == '\\' && code.charAt(i+1) == '\\')
				isComment = true;
			if(instruction == '\n')
				isComment = false;
			if(isComment)
				continue;
			switch(instruction){
				case '+':
				plus();
				break;
				case '-':
				minus();
				break;
				case '>':
				forroward();
				break;
				case '<':
				backword();
				break;
				case '[':
				lopen();
				break;
				case ']':
				lclose();
				break;
				case '.':
				print();
				break;
				case ',':
				input();
				break;
				default:
				break;
			}
		}
		code = code.trim();
	}

	private void plus(){
		try{
			memory.set(ptr, new Integer(Integer.valueOf(memory.get(ptr) + 1)));
		}catch(Exception ex){
			writter.println("Error in writhng to memory.");
			System.exit(-1);
		}
	}

	private void minus(){
		try{
			memory.set(ptr, new Integer(Integer.valueOf(memory.get(ptr) - 1)));
		}catch(Exception ex){
			writter.println("Error in writhng to memory.");
			System.exit(-1);
		}
	}

	private void forroward(){
		try{
			if(memory.size() > maxMem || ptr > maxMem){
				writter.println("Out of Memory Error!");
				System.exit(-1);
			}
			if(ptr == memory.size() - 1){
				ptr++;
				memory.add(0);
			}else{
				ptr++;
			}
		}catch(Exception ex){
			writter.println("Error in reading from memory.");
			System.exit(-1);
		}
	}

	private void backword(){
		try{
			if(ptr == 0){
				writter.println("Invalid memory referenced!");
			}else{
				ptr--;
			}
		}catch(Exception ex){
			writter.println("Error in reading from memory.");
			System.exit(-1);
		}
	}

	private void print(){
		try{
			writter.write(memory.get(ptr));
		}catch(Exception ex){
			writter.println("Error in writhng to output stream.");
			System.exit(-1);
		}
	}

	private void input(){
		try{
			int byteData = inStream.read();
			memory.set(ptr, byteData);
		}catch(Exception ex){
			writter.println("Error in reading form input stream.");
			System.exit(-1);
		}
	}

	private void lopen(){}

	private void lclose(){}
}
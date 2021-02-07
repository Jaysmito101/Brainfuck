package com.jaysmito.brainfuck;

import java.util.ArrayList;
import java.io.*;

public class Brainfuck32{
	private String code;
	private boolean embrk;
	private ArrayList<Integer> memory;
	private int ptr, iptr;
	private int maxMem;
	private OutputStream outStream;
	private InputStream inStream;
	private PrintStream writter;
	private boolean isComment = false;

	public Brainfuck32(String code){
		this.code = code;
		this.embrk=false;
		this.code = code.trim() + "    ";
		this.ptr = 0;
		this.maxMem = 30000;
		this.memory = new ArrayList<Integer>(maxMem);
		this.outStream = System.out;
		this.inStream = System.in;
		writter = new PrintStream(System.out);
	}

	public Brainfuck32(String code, int maxMem){
		this.code = code;
		this.code = code.trim() + "    ";
		this.ptr = 0;
		this.embrk=false;
		this.iptr = 0;
		this.memory = new ArrayList<Integer>(maxMem);
		this.maxMem = maxMem;
		this.outStream = System.out;
		this.inStream = System.in;
		writter = new PrintStream(this.outStream);
	}

	public Brainfuck32(String code, InputStream inStream, OutputStream outStream){
		this.code = code;
		this.code = code.trim() + "    ";
		this.iptr = 0;
		this.embrk=false;
		this.ptr = 0;
		this.maxMem = 30000;
		this.memory = new ArrayList<Integer>(maxMem);
		this.outStream = outStream;
		this.inStream = inStream;
		writter = new PrintStream(this.outStream);
	}

	public Brainfuck32(String code, int maxMem, InputStream inStream, OutputStream outStream){
		this.code = code;
		this.code = code.trim() + "    ";
		this.embrk=false; 
		this.iptr = 0;
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
		return this.code.trim();
	}

	public void execute(){
		memory.add(0);
		next();
	}

	private void next(){
		try{
			char instruction;
			instruction = code.charAt(iptr);
			if(instruction == '\\' && code.charAt(iptr+1) == '\\')
				isComment = true;
			if(instruction == '\n')
				isComment = false;
			if(isComment){
				iptr++;
				next();
			}
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
			iptr++;
			if(iptr <= code.length()-1 && !embrk){
				next();
			}
		}catch(StackOverflowError err){
			if(embrk)
				return;
			writter.println("Stack Overflow Error.\n");
			embrk = true;
		}
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

	private void lopen(){
		try{
			if(Integer.valueOf(memory.get(ptr)) == 0){
				int nxtptr = code.indexOf(']', iptr);
				if(nxtptr == -1){
					writter.println("Mismatching [].");
					System.exit(-1);
				}
				else{
					iptr = nxtptr;
				}
			}
		}catch(Exception ex){
			writter.println("Error in reading form memory.");
			System.exit(-1);
		}
	}

	private void lclose(){
		try{
			if(Integer.valueOf(memory.get(ptr)) != 0){
				int nxtptr = iptr;
				for(int i = iptr ; i>= 0 ; i--){
					if(code.charAt(i) == '['){
						iptr = i;
						break;
					}
				}
				if(nxtptr == iptr){
					writter.println("Mismatching [].");
					System.exit(-1);
				}
			}
		}catch(Exception ex){
			writter.println("Error in reading form memory.");
			System.exit(-1);
		}
	}
}
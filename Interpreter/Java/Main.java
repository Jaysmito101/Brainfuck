import com.jaysmito.brainfuck.*;

import java.util.*;

public class Main{
	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Brainfuck Interpreter\n\n");

		String s = "";
		while(true){
			System.out.print("Enter the code: ");
			s= scanner.nextLine();
			Brainfuck32 brea = new Brainfuck32(s);
			brea.execute();
			System.out.println();
		}
	}
}
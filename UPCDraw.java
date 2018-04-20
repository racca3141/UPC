//Draws a label using a UPC number

//Emerson Racca

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.Scanner;

public class UPCDraw extends JPanel{

	private static int[] upc;
	private String upcString;
	private int x, y;

	public UPCDraw(String bc){
		
		x = 30;
		y = 30;		

		upc = new int[12];
		upcString = bc;
		int bcBit;
		for(int c = 0; c < 12; c++){

			bcBit = Integer.parseInt(bc.substring(c, c + 1));			
			upc[c] = bcBit;
		}

	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		int high = 30;
		int colCount = 0;
		String patternCode;
		String bitCode;

		//bar left
		for(int c = 1; c <= 3; c++){
			if(c % 2 == 1)
				g.setColor(Color.black);
			else
				g.setColor(Color.white);

			g.drawLine(x + colCount, y, x + colCount, y + high);
			colCount++;
		}
		
		//info left
		for(int c = 0; c < 6; c++){
			patternCode = getBitPattern("l", upc[c]);
			System.out.println(patternCode);
			for(int i = 0; i < 7; i++){
				bitCode = patternCode.substring(i, i + 1);
				if(bitCode.equals("b")) g.setColor(Color.black);
				if(bitCode.equals("w")) g.setColor(Color.white);
			
				g.drawLine(x + colCount, y, x + colCount, y + high);
				colCount++;
			}
		}
			
		//bar middle
		for(int c = 0; c < 5; c++){
			if(c % 2 == 1)
				g.setColor(Color.black);
			else
				g.setColor(Color.white);

			g.drawLine(x + colCount, y, x + colCount, y + high);
			colCount++;
		}

		//info right and check digit
		for(int c = 0; c < 6; c++){
			patternCode = getBitPattern("r", upc[c + 6]);
			System.out.println(patternCode);
			for(int i = 0; i < 7; i++){
				bitCode = patternCode.substring(i, i + 1);
				if(bitCode.equals("b")) g.setColor(Color.black);
				if(bitCode.equals("w")) g.setColor(Color.white);
			
				g.drawLine(x + colCount, y, x + colCount, y + high);
				colCount++;
			}
		}
		

		//bar right
		for(int c = 1; c <= 3; c++){
			if(c % 2 == 1)
				g.setColor(Color.black);
			else
				g.setColor(Color.white);

			g.drawLine(x + colCount, y, x + colCount, y + high);
			colCount++;
		}

		g.drawRect(x - 5, y - 5, 105, 55);
		g.drawString(upcString, x + 5, y + 45);

		System.out.println(colCount);  //should be 95 columns
	}
	
	public int getBit(int loc){		
		return upc[loc];
	}

	private String getBitPattern(String s, int bit){
		String bitPattern = "";
		//left side
		if(s.equals("l")){
			if(bit == 0) bitPattern = "wwwbbwb";
			if(bit == 1) bitPattern = "wwbbwwb";
			if(bit == 2) bitPattern = "wwbwwbb";
			if(bit == 3) bitPattern = "wbbbbwb";
			if(bit == 4) bitPattern = "wbwwwbb";
			if(bit == 5) bitPattern = "wbbwwwb";
			if(bit == 6) bitPattern = "wbwbbbb";
			if(bit == 7) bitPattern = "wbbbwbb";
			if(bit == 8) bitPattern = "wbbwbbb";
			if(bit == 9) bitPattern = "wwwbwbb";

		}	
		//right side
		if(s.equals("r")){
			if(bit == 0) bitPattern = "bbbwwbw";
			if(bit == 1) bitPattern = "bbwwbbw";
			if(bit == 2) bitPattern = "bbwbbww";
			if(bit == 3) bitPattern = "bwwwwbw";
			if(bit == 4) bitPattern = "bwbbbww";
			if(bit == 5) bitPattern = "bwwbbbw";
			if(bit == 6) bitPattern = "bwbwwww";
			if(bit == 7) bitPattern = "bwwwbww";
			if(bit == 8) bitPattern = "bwwbwww";
			if(bit == 9) bitPattern = "bbbwbww";
		}

		return bitPattern;

	}
	
	public static void main(String[] args){
		Scanner reader = new Scanner(System.in);
		System.out.print("Enter a barcode: ");
		String UPC = reader.nextLine();
		//String UPC = "718103124775";
		JFrame GUI = new JFrame();
		//std
		GUI.setSize(200, 200);
		GUI.setTitle("UPC Draw");
		GUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container pane = GUI.getContentPane();
				
		UPCDraw drawBC = new UPCDraw(UPC);

		pane.add(drawBC);
		GUI.setVisible(true);

		/* 
		//test the method for data verification
		for(int c = 0; c < 12; c++){
			System.out.println("upc[" + c + "] = " + drawBC.getBit(c));
		}
		*/

	}

	

}
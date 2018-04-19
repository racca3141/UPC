//Enter a 12-digit number such as "0123456789012" and this app
//will compute the check digit to verify if the number is a
//valid UPC-A barcode.  The left and right data strings are also
//extracted for use in creating a barcode.

//Emerson Racca

//I apologize for the messy code.  It's meant for further development.


import java.util.Scanner;

public class UPCMain{

	public static void main(String[] args){
		Scanner reader = new Scanner(System.in);
		
		int length;
		String upc;	
		while(true){
			try{	
				System.out.println();
				System.out.print("Enter a 12 digit UPC number: ");	
				upc = reader.nextLine();
				upc = upc.trim();
				length = upc.length();
				int check = 0;
				boolean goodUPC = true;
				char cDigit;
				int iDigit;
				if(length == 12){
					for(int c = 0; c < 12; c++){
						cDigit = upc.charAt(c);
						iDigit = (int) cDigit;
						//iDigit = Character.getNumericValue(cDigit);
						//System.out.println(iDigit + "");
						if(!(iDigit >= 48 && iDigit <=57))
							goodUPC = false;
					}
				}
				else{
					System.out.println("It must be a string of 12 numbers.");
					goodUPC = false;
				}
				if(goodUPC){ //check digit check.
					String cDig;
					int iDig;
					int oddSum = 0;
					int evenSum = 0;
					int checkDigitComputed = -1;
					int checkDigitGiven = -1;
					for(int c = 0; c < 11; c++){  //11th digit
						cDig = upc.substring(c, c + 1);
						iDig = Integer.parseInt(cDig);
						if((c % 2) == 1){ //even although odd location
							//System.out.println(iDig + "e");
							evenSum = evenSum + iDig;
						}
						else{
							//System.out.println(iDig + "o");
							oddSum = oddSum + iDig;
						}
						
					}
					//System.out.println(evenSum + " e o " + oddSum);
					checkDigitComputed = 10 - (((oddSum * 3) + evenSum) % 10);
					System.out.println(checkDigitComputed + "<-----checkDigitComputed");
					checkDigitGiven = Integer.parseInt(upc.substring(11, 12));
					System.out.println(checkDigitGiven + "<---------checkDigitGiven");
					if(checkDigitComputed == checkDigitGiven)
						break;
					else{
						goodUPC = false;
						System.out.println("The check digit does not validate.");
					}
					
				} 
			
			}
			catch(Exception e){
				System.out.println("Error.  Something went wrong.");
			}
		//reader.nextLine(); //not necessary
		}

		System.out.println(upc + " is a valid UPC number");

		//get the left 6 digits for the barcode.

		String leftData = upc.substring(0, 6);  //dumb that endIndex gets 1 less.
		System.out.println(leftData + "<---------leftData");
		
		//get right 5 digits
		String rightData = upc.substring(6, 11);
		System.out.println(rightData + "<----------rightData");



	}	
}
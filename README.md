# UPC
Build a UPC-A label.  Here is a link on how it's made -- https://www.quora.com/How-do-barcodes-work-1

Here is another useful link -- https://www.wikihow.com/Read-12-Digit-UPC-Barcodes


UPCMain.java is a command line program that takes in a 12 digit number and checks to see if it is a valid UPC-A barcode.
It does this by comparing the entered check digit (the 12th digit) with what it should be by computation.  The program
also gets the substrings for the first 6 digits and the second 5 digits of the barcode.  These substrings are treated
differently when laying out the barcode image.  See below in the Design Notes.

UPCDraw.java takes in a 12 digit UPC string and draws it.  It can be combined with UPCMain.java to verify the 12 digit 
as a valid UPC-A.

![image](https://user-images.githubusercontent.com/31526815/39031002-7da40662-441a-11e8-9e8c-bd89c9e26f7e.png) ![image](https://user-images.githubusercontent.com/31526815/39031414-d2146bb8-441c-11e8-8342-583fa6c0f888.png)

Using mobile Bing's Barcode Scanner, it correctly identifies that barcode above as Staple's glue.  One is in "BADASS_GREEN".

Design Notes:

//from left to right where b is a single black line and w is a single white line:

//bar left

		bwb

//info left

	0	wwwbbwb		
	1	wwbbwwb		
	2	wwbwwbb		
	3	wbbbbwb		
	4	wbwwwbb		
	5	wbbwwwb		
	6	wbwbbbb
	7	wbbbwbb
	8	wbbwbbb
	9	wwwbwbb

//bar middle
	
		wbwbw

//info right (negative of info left)

	0	bbbwwbw
	1	bbwwbbw
	2	bbwbbww
	3	bwwwwbw
	4	bwbbbww
	5	bwwbbbw
	6	bwbwwww
	7	bwwwbww
	8	bwwbwww
	9	bbbwbww

//check digit
	
	see above for pattern
	
	compute:  1) for the 11 digits, add all ODD places. (6 numbers). multiply by 3.
		  2) add all EVEN places.  (5 numbers)
		  3) add the numbers from steps 1 and 2.
		  4) the check number is obtained by subtracting the number
                 of step 3 (called S) from the next higher multiple of 10.
				checkDigit = 10 - (S % 10)
	
//bar right

		bwb

//final comment
	
	total columns occupied by all b and w patterns is 95:
	3 + 6(7) + 5 + 5(7) + 7 + 3 

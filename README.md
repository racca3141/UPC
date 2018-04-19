 # UPC
Build a UPC-A label.  Here is a link on how it's made -- https://www.quora.com/How-do-barcodes-work-1

Here is another useful link -- https://www.wikihow.com/Read-12-Digit-UPC-Barcodes


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

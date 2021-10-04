package ui;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Menu {
	private int numberOfBooks;
	private int bookPrice;
	
	
	public void showMenu() {
		System.out.println("\nEnter the option that you want to do: ");
		System.out.println("[1] Manually, enter data into the program.\n"+
		                   "[2] Watch a test case in the program.\n"+
		                   "[3] Exit the program.\n");
	}
	
	public int readOption(BufferedReader br) throws NumberFormatException, IOException {
		int option = Integer.parseInt(br.readLine());
		return option;
	}
	
	public void doOption(BufferedReader br, int option) throws NumberFormatException, IOException {
		switch(option) {
		
		case 1:
			System.out.println("-------------------------------------------------------");
			System.out.println("               CALCULATE THE EXACT SUM");
			System.out.println("-------------------------------------------------------\n");
			readBooksData(br);
			break;
			
		case 2:
			System.out.println("-------------------------------------------------------");
			System.out.println("                     TEST CASES");
			System.out.println("-------------------------------------------------------");
			
			showTestCases();
			doOptionTestCases(readOption(br));
			break;
			
		case 3:
			System.out.println("Bye! ;)");
			br.close();
			break;

		default:
			System.out.println("Wrong choice. Try again");
			break;
			
		}
	}
	
	public void startProgram() throws NumberFormatException, IOException {
		int exit = 3;
		int option = 0;
		
		do {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			showMenu();
			option = readOption(br);
			doOption(br, option);
			
		}while(option != exit);
	}
	
	
	public void readBooksData(BufferedReader br) throws NumberFormatException, IOException {
		
		System.out.println("Write (in integer value) the number of books: ");
		numberOfBooks = Integer.parseInt(br.readLine());
		int [] prices = new int[numberOfBooks];

		System.out.println("*************** PRICE OF THE " + numberOfBooks + " BOOKS ***************\n");
		System.out.println("Write below (in integer values) the price of each book in each line:");
		
		for (int i = 0; i < prices.length; i++) {
			bookPrice = Integer.parseInt(br.readLine());
			prices[i] = bookPrice;
		}
		insertionSort(prices);
		
		System.out.println("Write the total money (in integer value) that you have to buy books. Just type a numeric value, without points or commas.");
		int money = Integer.parseInt(br.readLine());
		
		calculateExactSum(prices, money);
	}
	
	
	public void calculateExactSum(int []prices, int money) {
		int price1 = 0;
		int price2 = 0;
		
		for(int i=0; i<prices.length-1;i++) {
			if( (prices[i]+prices[i+1]) == money) {
				price1 = prices[i];
				price2 = prices[i+1];
			}else if((prices[i]+prices[i+1]) < money) {
				price1 = prices[i];
				price2 = prices[i+1];
			}
		}
		
		if( (binarySearch(prices, price1)) && (binarySearch(prices, price2)) ) {
			System.out.println("______________________________________________________________________");
			System.out.println("\nYou should buy books whose prices are "+price1+" and "+price2+".");
			
		
		}else {
			System.out.println("There are not two books that you can buy using all the money that you have.");
		}
		int remainingMoney = (money-(price1+price2));
		System.out.println("Remaining money: "+remainingMoney);
		System.out.println("——————————————————————————————————————————————————————————————————————");
	}
	
	
	public void insertionSort(int[] array) {
		int aux;
		int j;
		for(int i=1; i<array.length;i++) {
			aux = array[i];
			j = i-1;
			while((j>=0)&&(aux<array[j])) {
				array[j+1] = array[j];
				j--;
			}
			array[j+1] = aux;
		}
	}
	
	public boolean binarySearch(int[] array,int element){
		int i, j, m;
		boolean find = false;
		i = 0;
		j = array.length-1;
		while(find == false) {
			m = (int)((i+j)/2);
			if(element == array[m]) {
				find = true;
			}else if(element <array[m]) {
				j = m-1;
			}else {
				i = m+1;
			}
		}
		
		return find;
	}
	
	public int[] readBooksPrices(String FILE_IMPORT_TXT_PATH) throws IOException {
		List <String> elements = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader(FILE_IMPORT_TXT_PATH));
		
		String line = br.readLine();
		while(line!=null) {
			elements.add(line);
			line = br.readLine();
		}
		int[] booksPrices = new int[elements.size()-2];
		
		for(int i=0; i<booksPrices.length ;i++) {
			int bookPrice = Integer.parseInt(elements.get(i+1));
			booksPrices[i] = bookPrice;
		}
		
		insertionSort(booksPrices);
		
		return booksPrices;
	}
	
	public int readNumberOfBooks(String FILE_IMPORT_TXT_PATH) throws IOException {
		List <String> elements = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader(FILE_IMPORT_TXT_PATH));
		String line = br.readLine();
		while(line!=null) {
			elements.add(line);
			line = br.readLine();
		}
		int numberOfBooks = Integer.parseInt(elements.get(0));
		return numberOfBooks;
	}
	
	public int readMoney(String FILE_IMPORT_TXT_PATH) throws IOException {
		List <String> elements = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader(FILE_IMPORT_TXT_PATH));
		String line = br.readLine();
		while(line!=null) {
			elements.add(line);
			line = br.readLine();
		}
		int money = Integer.parseInt(elements.get(elements.size()-1));
		return money;
	}

	
	public void showTestCases() {
		System.out.println("\nSelect the test case that you want to watch: \n");
		System.out.println("[1]  Test case with 2 prices. A test case with exact sum.\n"+
		                   "[2]  Test case with 5 prices. A test case with exact sum.\n"+
		                   "[3]  Test case with 11 prices. A test case with exact sum.\n"+
		                   "[4]  Test case with 8 prices. A test case with exact sum.\n"+
		                   "[5]  Test case with 9 prices. A test case with exact sum.\n"+
		                   "[6]  Test case with 8 prices. A test case with exact sum.\n"+
		                   "[7]  Test case with 10 prices. A test case with exact sum.\n"+
		                   "[8]  Test case with 20 prices. A test case with exact sum.\n"+
		                   "[9]  Test case with 4 prices. A test case with exact sum.\n"+
		                   "[10] Test case with 7 prices. A test case with exact sum.\n"+
		                   "[11] Test case with 14 prices. A test case with exact sum.\n"+
		                   "[12] Test case with 3 prices. A test case with exact sum.\n"+
		                   "[13] Test case with 15 prices. A test case with exact sum.\n"+
		                   "[14] Test case with 12 prices. A test case with exact sum.\n"+
		                   "[15] Test case with 19 prices. A test case with exact sum.\n"+
		                   "[16] Test case with 17 prices. A test case with exact sum.\n"+
		                   "[17] Test case with 22 prices. A test case with exact sum.\n"+
		                   "[18] Test case with 13 prices. A test case with exact sum.\n"+
		                   "[19] Test case with 21 prices. A test case with exact sum.\n"+
		                   "[20] Test case with 24 prices. A test case with exact sum.\n"+
		                   "[21] Test case with 16 prices. A test case in which no two books that cost the same as the total money.\n"+
		                   "[22] Test case with 25 prices. A test case in which no two books that cost the same as the total money.\n"+
		                   "[23] Test case with 23 prices. A test case in which no two books that cost the same as the total money.\n"+
		                   "[24] Test case with 28 prices. A test case in which no two books that cost the same as the total money.\n"+
		                   "[25] Test case with 30 prices. A test case with exact sum.\n");
	}
	
	public void doOptionTestCases(int option) throws IOException {
		switch(option) {
		
		case 1:
			String FILE_IMPORT_TXT_PATH1 = "data/TextCase1.txt";
			
			System.out.println("/////   TEST CASE 1   /////\n");
			System.out.println("Number of books: "+readNumberOfBooks(FILE_IMPORT_TXT_PATH1));
			System.out.println("Book prices: ");
			int [] booksPrices = readBooksPrices(FILE_IMPORT_TXT_PATH1);
			for(int i=0; i<booksPrices.length;i++) {
				System.out.println(booksPrices[i]);
			}
			System.out.println("Total money: "+readMoney(FILE_IMPORT_TXT_PATH1));

			calculateExactSum(readBooksPrices(FILE_IMPORT_TXT_PATH1), readMoney(FILE_IMPORT_TXT_PATH1));
			
			break;
			
		case 2:
			String FILE_IMPORT_TXT_PATH2 = "data/TextCase2.txt";

			System.out.println("/////   TEST CASE 2   /////\n");
			System.out.println("Number of books: " + readNumberOfBooks(FILE_IMPORT_TXT_PATH2));
			System.out.println("Book prices: ");
			int[] booksPrices2 = readBooksPrices(FILE_IMPORT_TXT_PATH2);
			for (int i = 0; i < booksPrices2.length; i++) {
				System.out.println(booksPrices2[i]);
			}
			System.out.println("Total money: " + readMoney(FILE_IMPORT_TXT_PATH2));

			calculateExactSum(readBooksPrices(FILE_IMPORT_TXT_PATH2), readMoney(FILE_IMPORT_TXT_PATH2));
			
			break;
			
		case 3:
			String FILE_IMPORT_TXT_PATH3 = "data/TextCase3.txt";
			
			System.out.println("/////   TEST CASE 3   /////\n");
			System.out.println("Number of books: " + readNumberOfBooks(FILE_IMPORT_TXT_PATH3));
			System.out.println("Book prices: ");
			int[] booksPrices3 = readBooksPrices(FILE_IMPORT_TXT_PATH3);
			for (int i = 0; i < booksPrices3.length; i++) {
				System.out.println(booksPrices3[i]);
			}
			System.out.println("Total money: " + readMoney(FILE_IMPORT_TXT_PATH3));

			calculateExactSum(readBooksPrices(FILE_IMPORT_TXT_PATH3), readMoney(FILE_IMPORT_TXT_PATH3));
			
			break;
			
		case 4:
			String FILE_IMPORT_TXT_PATH4 = "data/TextCase4.txt";
			
			System.out.println("/////   TEST CASE 4   /////\n");
			System.out.println("Number of books: " + readNumberOfBooks(FILE_IMPORT_TXT_PATH4));
			System.out.println("Book prices: ");
			int[] booksPrices4 = readBooksPrices(FILE_IMPORT_TXT_PATH4);
			for (int i = 0; i < booksPrices4.length; i++) {
				System.out.println(booksPrices4[i]);
			}
			System.out.println("Total money: " + readMoney(FILE_IMPORT_TXT_PATH4));

			calculateExactSum(readBooksPrices(FILE_IMPORT_TXT_PATH4), readMoney(FILE_IMPORT_TXT_PATH4));
			break;
			
		case 5:
			String FILE_IMPORT_TXT_PATH5 = "data/TextCase5.txt";
			
			System.out.println("/////   TEST CASE 5   /////\n");
			System.out.println("Number of books: " + readNumberOfBooks(FILE_IMPORT_TXT_PATH5));
			System.out.println("Book prices: ");
			int[] booksPrices5 = readBooksPrices(FILE_IMPORT_TXT_PATH5);
			for (int i = 0; i < booksPrices5.length; i++) {
				System.out.println(booksPrices5[i]);
			}
			System.out.println("Total money: " + readMoney(FILE_IMPORT_TXT_PATH5));

			calculateExactSum(readBooksPrices(FILE_IMPORT_TXT_PATH5), readMoney(FILE_IMPORT_TXT_PATH5));
			
			break;
			
		case 6:
			String FILE_IMPORT_TXT_PATH6 = "data/TextCase6.txt";
			
			System.out.println("/////   TEST CASE 6   /////\n");
			System.out.println("Number of books: " + readNumberOfBooks(FILE_IMPORT_TXT_PATH6));
			System.out.println("Book prices: ");
			int[] booksPrices6 = readBooksPrices(FILE_IMPORT_TXT_PATH6);
			for (int i = 0; i < booksPrices6.length; i++) {
				System.out.println(booksPrices6[i]);
			}
			System.out.println("Total money: " + readMoney(FILE_IMPORT_TXT_PATH6));

			calculateExactSum(readBooksPrices(FILE_IMPORT_TXT_PATH6), readMoney(FILE_IMPORT_TXT_PATH6));
			break;
			
		case 7:
			String FILE_IMPORT_TXT_PATH7 = "data/TextCase7.txt";
			
			System.out.println("/////   TEST CASE 7   /////\n");
			System.out.println("Number of books: " + readNumberOfBooks(FILE_IMPORT_TXT_PATH7));
			System.out.println("Book prices: ");
			int[] booksPrices7 = readBooksPrices(FILE_IMPORT_TXT_PATH7);
			for (int i = 0; i < booksPrices7.length; i++) {
				System.out.println(booksPrices7[i]);
			}
			System.out.println("Total money: " + readMoney(FILE_IMPORT_TXT_PATH7));

			calculateExactSum(readBooksPrices(FILE_IMPORT_TXT_PATH7), readMoney(FILE_IMPORT_TXT_PATH7));
			break;
			
		case 8:
			String FILE_IMPORT_TXT_PATH8 = "data/TextCase8.txt";
			
			System.out.println("/////   TEST CASE 8   /////\n");
			System.out.println("Number of books: " + readNumberOfBooks(FILE_IMPORT_TXT_PATH8));
			System.out.println("Book prices: ");
			int[] booksPrices8 = readBooksPrices(FILE_IMPORT_TXT_PATH8);
			for (int i = 0; i < booksPrices8.length; i++) {
				System.out.println(booksPrices8[i]);
			}
			System.out.println("Total money: " + readMoney(FILE_IMPORT_TXT_PATH8));

			calculateExactSum(readBooksPrices(FILE_IMPORT_TXT_PATH8), readMoney(FILE_IMPORT_TXT_PATH8));
			break;
			
		case 9:
			String FILE_IMPORT_TXT_PATH9 = "data/TextCase9.txt";
			
			System.out.println("/////   TEST CASE 9   /////\n");
			System.out.println("Number of books: " + readNumberOfBooks(FILE_IMPORT_TXT_PATH9));
			System.out.println("Book prices: ");
			int[] booksPrices9 = readBooksPrices(FILE_IMPORT_TXT_PATH9);
			for (int i = 0; i < booksPrices9.length; i++) {
				System.out.println(booksPrices9[i]);
			}
			System.out.println("Total money: " + readMoney(FILE_IMPORT_TXT_PATH9));

			calculateExactSum(readBooksPrices(FILE_IMPORT_TXT_PATH9), readMoney(FILE_IMPORT_TXT_PATH9));
			break;
			
		case 10:
			String FILE_IMPORT_TXT_PATH10 = "data/TextCase10.txt";
			
			System.out.println("/////   TEST CASE 10   /////\n");
			System.out.println("Number of books: " + readNumberOfBooks(FILE_IMPORT_TXT_PATH10));
			System.out.println("Book prices: ");
			int[] booksPrices10 = readBooksPrices(FILE_IMPORT_TXT_PATH10);
			for (int i = 0; i < booksPrices10.length; i++) {
				System.out.println(booksPrices10[i]);
			}
			System.out.println("Total money: " + readMoney(FILE_IMPORT_TXT_PATH10));

			calculateExactSum(readBooksPrices(FILE_IMPORT_TXT_PATH10), readMoney(FILE_IMPORT_TXT_PATH10));
			break;
			
		case 11:
			String FILE_IMPORT_TXT_PATH11 = "data/TextCase11.txt";
			System.out.println("/////   TEST CASE 11   /////\n");
			System.out.println("Number of books: " + readNumberOfBooks(FILE_IMPORT_TXT_PATH11));
			System.out.println("Book prices: ");
			int[] booksPrices11 = readBooksPrices(FILE_IMPORT_TXT_PATH11);
			for (int i = 0; i < booksPrices11.length; i++) {
				System.out.println(booksPrices11[i]);
			}
			System.out.println("Total money: " + readMoney(FILE_IMPORT_TXT_PATH11));

			calculateExactSum(readBooksPrices(FILE_IMPORT_TXT_PATH11), readMoney(FILE_IMPORT_TXT_PATH11));
			break;
			
		case 12:
			String FILE_IMPORT_TXT_PATH12 = "data/TextCase12.txt";
			System.out.println("/////   TEST CASE 12   /////\n");
			System.out.println("Number of books: " + readNumberOfBooks(FILE_IMPORT_TXT_PATH12));
			System.out.println("Book prices: ");
			int[] booksPrices12 = readBooksPrices(FILE_IMPORT_TXT_PATH12);
			for (int i = 0; i < booksPrices12.length; i++) {
				System.out.println(booksPrices12[i]);
			}
			System.out.println("Total money: " + readMoney(FILE_IMPORT_TXT_PATH12));

			calculateExactSum(readBooksPrices(FILE_IMPORT_TXT_PATH12), readMoney(FILE_IMPORT_TXT_PATH12));
			break;
			
		case 13:
			String FILE_IMPORT_TXT_PATH13 = "data/TextCase13.txt";
			System.out.println("/////   TEST CASE 13   /////\n");
			System.out.println("Number of books: " + readNumberOfBooks(FILE_IMPORT_TXT_PATH13));
			System.out.println("Book prices: ");
			int[] booksPrices13 = readBooksPrices(FILE_IMPORT_TXT_PATH13);
			for (int i = 0; i < booksPrices13.length; i++) {
				System.out.println(booksPrices13[i]);
			}
			System.out.println("Total money: " + readMoney(FILE_IMPORT_TXT_PATH13));

			calculateExactSum(readBooksPrices(FILE_IMPORT_TXT_PATH13), readMoney(FILE_IMPORT_TXT_PATH13));
			break;
			
		case 14:
			String FILE_IMPORT_TXT_PATH14 = "data/TextCase14.txt";
			System.out.println("/////   TEST CASE 14   /////\n");
			System.out.println("Number of books: " + readNumberOfBooks(FILE_IMPORT_TXT_PATH14));
			System.out.println("Book prices: ");
			int[] booksPrices14 = readBooksPrices(FILE_IMPORT_TXT_PATH14);
			for (int i = 0; i < booksPrices14.length; i++) {
				System.out.println(booksPrices14[i]);
			}
			System.out.println("Total money: " + readMoney(FILE_IMPORT_TXT_PATH14));

			calculateExactSum(readBooksPrices(FILE_IMPORT_TXT_PATH14), readMoney(FILE_IMPORT_TXT_PATH14));
			break;
			
		case 15:
			String FILE_IMPORT_TXT_PATH15 = "data/TextCase15.txt";
			System.out.println("/////   TEST CASE 15   /////\n");
			System.out.println("Number of books: " + readNumberOfBooks(FILE_IMPORT_TXT_PATH15));
			System.out.println("Book prices: ");
			int[] booksPrices15 = readBooksPrices(FILE_IMPORT_TXT_PATH15);
			for (int i = 0; i < booksPrices15.length; i++) {
				System.out.println(booksPrices15[i]);
			}
			System.out.println("Total money: " + readMoney(FILE_IMPORT_TXT_PATH15));

			calculateExactSum(readBooksPrices(FILE_IMPORT_TXT_PATH15), readMoney(FILE_IMPORT_TXT_PATH15));
			break;
			
		case 16:
			String FILE_IMPORT_TXT_PATH16 = "data/TextCase16.txt";
			System.out.println("/////   TEST CASE 16   /////\n");
			System.out.println("Number of books: " + readNumberOfBooks(FILE_IMPORT_TXT_PATH16));
			System.out.println("Book prices: ");
			int[] booksPrices16 = readBooksPrices(FILE_IMPORT_TXT_PATH16);
			for (int i = 0; i < booksPrices16.length; i++) {
				System.out.println(booksPrices16[i]);
			}
			System.out.println("Total money: " + readMoney(FILE_IMPORT_TXT_PATH16));

			calculateExactSum(readBooksPrices(FILE_IMPORT_TXT_PATH16), readMoney(FILE_IMPORT_TXT_PATH16));
			break;
			
		case 17:
			String FILE_IMPORT_TXT_PATH17 = "data/TextCase17.txt";
			System.out.println("/////   TEST CASE 17   /////\n");
			System.out.println("Number of books: " + readNumberOfBooks(FILE_IMPORT_TXT_PATH17));
			System.out.println("Book prices: ");
			int[] booksPrices17 = readBooksPrices(FILE_IMPORT_TXT_PATH17);
			for (int i = 0; i < booksPrices17.length; i++) {
				System.out.println(booksPrices17[i]);
			}
			System.out.println("Total money: " + readMoney(FILE_IMPORT_TXT_PATH17));

			calculateExactSum(readBooksPrices(FILE_IMPORT_TXT_PATH17), readMoney(FILE_IMPORT_TXT_PATH17));
			break;
			
		case 18:
			String FILE_IMPORT_TXT_PATH18 = "data/TextCase18.txt";
			System.out.println("/////   TEST CASE 18   /////\n");
			System.out.println("Number of books: " + readNumberOfBooks(FILE_IMPORT_TXT_PATH18));
			System.out.println("Book prices: ");
			int[] booksPrices18 = readBooksPrices(FILE_IMPORT_TXT_PATH18);
			for (int i = 0; i < booksPrices18.length; i++) {
				System.out.println(booksPrices18[i]);
			}
			System.out.println("Total money: " + readMoney(FILE_IMPORT_TXT_PATH18));

			calculateExactSum(readBooksPrices(FILE_IMPORT_TXT_PATH18), readMoney(FILE_IMPORT_TXT_PATH18));
			break;
			
		case 19:
			String FILE_IMPORT_TXT_PATH19 = "data/TextCase19.txt";
			System.out.println("/////   TEST CASE 19   /////\n");
			System.out.println("Number of books: " + readNumberOfBooks(FILE_IMPORT_TXT_PATH19));
			System.out.println("Book prices: ");
			int[] booksPrices19 = readBooksPrices(FILE_IMPORT_TXT_PATH19);
			for (int i = 0; i < booksPrices19.length; i++) {
				System.out.println(booksPrices19[i]);
			}
			System.out.println("Total money: " + readMoney(FILE_IMPORT_TXT_PATH19));

			calculateExactSum(readBooksPrices(FILE_IMPORT_TXT_PATH19), readMoney(FILE_IMPORT_TXT_PATH19));
			break;
			
		case 20:
			String FILE_IMPORT_TXT_PATH20 = "data/TextCase20.txt";
			System.out.println("/////   TEST CASE 20   /////\n");
			System.out.println("Number of books: " + readNumberOfBooks(FILE_IMPORT_TXT_PATH20));
			System.out.println("Book prices: ");
			int[] booksPrices20 = readBooksPrices(FILE_IMPORT_TXT_PATH20);
			for (int i = 0; i < booksPrices20.length; i++) {
				System.out.println(booksPrices20[i]);
			}
			System.out.println("Total money: " + readMoney(FILE_IMPORT_TXT_PATH20));

			calculateExactSum(readBooksPrices(FILE_IMPORT_TXT_PATH20), readMoney(FILE_IMPORT_TXT_PATH20));
			break;
			
		case 21:
			String FILE_IMPORT_TXT_PATH21 = "data/TextCase21.txt";
			System.out.println("/////   TEST CASE 21   /////\n");
			System.out.println("Number of books: " + readNumberOfBooks(FILE_IMPORT_TXT_PATH21));
			System.out.println("Book prices: ");
			int[] booksPrices21 = readBooksPrices(FILE_IMPORT_TXT_PATH21);
			for (int i = 0; i < booksPrices21.length; i++) {
				System.out.println(booksPrices21[i]);
			}
			System.out.println("Total money: " + readMoney(FILE_IMPORT_TXT_PATH21));

			calculateExactSum(readBooksPrices(FILE_IMPORT_TXT_PATH21), readMoney(FILE_IMPORT_TXT_PATH21));
			break;
			
		case 22:
			String FILE_IMPORT_TXT_PATH22 = "data/TextCase22.txt";
			System.out.println("/////   TEST CASE 22   /////\n");
			System.out.println("Number of books: " + readNumberOfBooks(FILE_IMPORT_TXT_PATH22));
			System.out.println("Book prices: ");
			int[] booksPrices22 = readBooksPrices(FILE_IMPORT_TXT_PATH22);
			for (int i = 0; i < booksPrices22.length; i++) {
				System.out.println(booksPrices22[i]);
			}
			System.out.println("Total money: " + readMoney(FILE_IMPORT_TXT_PATH22));

			calculateExactSum(readBooksPrices(FILE_IMPORT_TXT_PATH22), readMoney(FILE_IMPORT_TXT_PATH22));
			break;
			
		case 23:
			String FILE_IMPORT_TXT_PATH23 = "data/TextCase23.txt";
			System.out.println("/////   TEST CASE 23   /////\n");
			System.out.println("Number of books: " + readNumberOfBooks(FILE_IMPORT_TXT_PATH23));
			System.out.println("Book prices: ");
			int[] booksPrices23 = readBooksPrices(FILE_IMPORT_TXT_PATH23);
			for (int i = 0; i < booksPrices23.length; i++) {
				System.out.println(booksPrices23[i]);
			}
			System.out.println("Total money: " + readMoney(FILE_IMPORT_TXT_PATH23));

			calculateExactSum(readBooksPrices(FILE_IMPORT_TXT_PATH23), readMoney(FILE_IMPORT_TXT_PATH23));
			break;
			
		case 24:
			String FILE_IMPORT_TXT_PATH24 = "data/TextCase24.txt";
			System.out.println("/////   TEST CASE 24   /////\n");
			System.out.println("Number of books: " + readNumberOfBooks(FILE_IMPORT_TXT_PATH24));
			System.out.println("Book prices: ");
			int[] booksPrices24 = readBooksPrices(FILE_IMPORT_TXT_PATH24);
			for (int i = 0; i < booksPrices24.length; i++) {
				System.out.println(booksPrices24[i]);
			}
			System.out.println("Total money: " + readMoney(FILE_IMPORT_TXT_PATH24));

			calculateExactSum(readBooksPrices(FILE_IMPORT_TXT_PATH24), readMoney(FILE_IMPORT_TXT_PATH24));
			break;
			
		case 25:
			String FILE_IMPORT_TXT_PATH25 = "data/TextCase25.txt";
			System.out.println("/////   TEST CASE 25   /////\n");
			System.out.println("Number of books: " + readNumberOfBooks(FILE_IMPORT_TXT_PATH25));
			System.out.println("Book prices: ");
			int[] booksPrices25 = readBooksPrices(FILE_IMPORT_TXT_PATH25);
			for (int i = 0; i < booksPrices25.length; i++) {
				System.out.println(booksPrices25[i]);
			}
			System.out.println("Total money: " + readMoney(FILE_IMPORT_TXT_PATH25));

			calculateExactSum(readBooksPrices(FILE_IMPORT_TXT_PATH25), readMoney(FILE_IMPORT_TXT_PATH25));
			break;

		default:
			System.out.println("Wrong choice. Try again");
			break;
		}
	}
}

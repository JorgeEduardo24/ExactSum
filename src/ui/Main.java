package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		int bookPrice;
		int[] prices;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Escribe la cantidad de libros: ");
		int numberOfBooks = Integer.parseInt(br.readLine());
		prices = new int[numberOfBooks];

		System.out.println("*************** PRECIO DE LOS " + numberOfBooks + " LIBROS ***************\n");
		System.out.println("Escribe el precio de cada libro en cada línea:");
		
		for (int i = 0; i < prices.length; i++) {
			bookPrice = Integer.parseInt(br.readLine());
			prices[i] = bookPrice;
			
		}
		insertionSort(prices);
		
		System.out.println("Escribe el dinero total que tiene para comprar libros. Sólo escribe un valor numérico, sin puntos ni comas.");
		int money = Integer.parseInt(br.readLine());
		
		int price1 = 0;
		int price2 = 0;
		for(int i=0; i<prices.length-1;i++) {
			if( (prices[i]+prices[i+1]) == money) {
				price1 = prices[i];
				price2 = prices[i+1];
			}
		}
		
		if( (binarySearch(prices, price1)) && (binarySearch(prices, price2)) ) {
			System.out.println("\nDebe comprar libros entre "+price1+" pesos "+" y "+price2+"pesos");
		}else {
			System.out.println("No hay dos libros que pueda comprar usando todo el dinero que tiene.");
		}
		
		br.close();
	}
	
	
	public static void insertionSort(int[] array) {
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
	
	public static boolean binarySearch(int[] array,int element){
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

}

package com.ss.lms.utility;

import java.util.List;

public class Utility {

	public static void displayMenu(String menu[]) {
		for (int i = 0; i < menu.length; i++) {
			System.out.print(i + 1 + ")" + menu[i] + "   ");
		}
		System.out.println("");
	}

	public static <T> void printList(List<T> list) {
		
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				System.out.println(i + 1 + ") " + list.get(i));
			}
		} else {
			System.out.print("Emplty list passed");
		}
		System.out.println("");
	}

}

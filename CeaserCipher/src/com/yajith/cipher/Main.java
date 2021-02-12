package com.yajith.cipher;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Scanner;


public class Main {
    private static String cipher;
    private static int choice,j;
    public static void main(String[] args) {
	    Scanner s=new Scanner(System.in);
	    System.out.println("1.encrypt\n2.decrypt");
	    choice=s.nextInt();
	    System.out.println("Enter the key");
	    j=s.nextInt();
	    if(choice==1)
        {
            Scanner s1=new Scanner(System.in);
            System.out.println("Enter The Plain Text:");
            cipher=s1.nextLine();
            cipher=cipher.toLowerCase();
                for (int i = 0; i < cipher.length(); i++) {
                    int a = (int) cipher.charAt(i);
                    if (a == 32) {
                        System.out.print(" ");
                        continue;
                    }
                    char c = (char) ( ((( (a + j) - 97) % 26) + 97));
                    c = Character.toUpperCase(c);
                    System.out.print(c);
                }
                System.out.println();
        }
	    else if(choice==2) {
            Scanner s2=new Scanner(System.in);
            System.out.println("Enter The Cipher Text:");
            cipher=s2.nextLine();
            cipher=cipher.toLowerCase();
            System.out.println("The Plain Text are");
                for (int i = 0; i < cipher.length(); i++) {
                    int a = (int) cipher.charAt(i);
                    if (a == 32) {
                        System.out.print(" ");
                        continue;
                    }
                    char c = (char) ( ( ((a - j) - 97) % 26) + 97);
                    System.out.print(c);
                }
                System.out.println();
            }
	    else
        {
            System.out.println("Wrong Choice");
        }
    }
}

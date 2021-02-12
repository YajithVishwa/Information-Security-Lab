package com.yajith.frequency;

import java.util.Scanner;

public class Main {
    private static String input;
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        input=scanner.nextLine();
        String cipherText = "";
        String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
        cipherText = input;
        char freq = getMaxOccuringChar(cipherText);
        System.out.println("The most occurring cipher letter is:"+ freq);
        System.out.println("Enter the plain letter corresponding to " + freq + ": ");
        char p = scanner.next().charAt(0);
        int cpos = ALPHABET.indexOf(freq);
        int ppos = ALPHABET.indexOf(p);
        int keyVal = (cpos - ppos) % 26;
        System.out.println("Key value: " + (keyVal*-1));
        decrypt(cipherText);
    }
    static void decrypt(String text){
        System.out.println("Key: ");
        int key = new Scanner(System.in).nextInt();
        char chars[] = text.toCharArray();
        int i = 0;
        while(i<chars.length){
            chars[i] = Character.toLowerCase(chars[i]);
            int val = ((int)chars[i]-key);
            val = (val >= 97)?val:121-(97-val);
            chars[i] = (char)val;
            System.out.print(Character.toLowerCase(chars[i]));
            i++;
        }
        System.out.println('\n');
    }
    static char getMaxOccuringChar(String str) {
        // Create array to keep the count of individual
        // characters and initialize the array as 0
        int count[] = new int[256];

        // Construct character count array from the input
        // string.
        int len = str.length();
        for (int i = 0; i < len; i++)
            count[str.charAt(i)]++;

        int max = -1;  // Initialize max count
        char result = ' ';   // Initialize result

        // Traversing through the string and maintaining
        // the count of each character
        for (int i = 0; i < len; i++) {
            if (max < count[str.charAt(i)]) {
                max = count[str.charAt(i)];
                result = str.charAt(i);
            }
        }

        return result;
    }

}

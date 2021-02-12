package com.yajith.hillcipher;

import java.util.Scanner;

public class Main {
        private static String input,key;
        private static int n,choice,dets;
        private static int[][] plain,keymatrix,a;
        public static void main(String[] args)
        {
            Scanner scanner=new Scanner(System.in);
            System.out.println("1.encrypt\n2.decrypt\n3. Known Plain Text");
            choice=scanner.nextInt();
        /*n=2;
        plain=new int[2][1];
        keymatrix=new int[2][2];
        a=new int[2][1];
        choice=scanner.nextInt();
        System.out.println("Enter the Key matrix 2x2");
        for(int i=0;i<2;i++)
        {
            for(int j=0;j<2;j++)
            {
                keymatrix[i][j]=scanner.nextInt();
            }
        }*/
            if(choice==1)
            {
                System.out.println("Enter the plain matrix 2x1");
                for(int i=0;i<2;i++)
                {
                    for(int j=0;j<1;j++)
                    {
                        plain[i][j]=scanner.nextInt();
                    }
                }
                a=matrixmultiply(keymatrix,plain);
                a=modmtrix(a);
                printmatrix(a);
            }
            else if(choice==2)
            {
                System.out.println("Enter the Cipher matrix 2x1");
                for(int i=0;i<2;i++)
                {
                    for(int j=0;j<1;j++)
                    {
                        plain[i][j]=scanner.nextInt();
                    }
                }
                dets=((keymatrix[0][0]*keymatrix[1][1])-(keymatrix[0][1]*keymatrix[1][0]));

                if(dets<0)
                {
                    dets=dets+26;
                }
                else
                {
                    dets=dets%26;
                }
                dets=extendedeuclidean(dets);
                keymatrix=scalarmultiply(adjmatrix(keymatrix),dets);
                printmatrix(matrixmultiply(keymatrix,plain));

            }
            else if(choice==3){
                String pl,cip;
                int[] keys = new int[4];
                int k_idx = 0;
                int a = 0, a1=0;
                char[] cip_char = new char[4];
                char[] pl_char = new char[4];
                int k=0;
                System.out.println("Enter plain text " + (k+1));
                pl = scanner.next();
                System.out.println("Enter cipher text " + (k+1));
                k++;
                cip = scanner.next();
                cip_char[a++] = cip.charAt(0);
                cip_char[a++] = cip.charAt(1);
                pl_char[a1++] = pl.charAt(0);
                pl_char[a1++] = pl.charAt(1);
                System.out.println("Enter plain text " + (k+1));
                pl = scanner.next();
                System.out.println("Enter cipher text " + (k+1));
                cip = scanner.next();
                cip_char[a++] = cip.charAt(0);
                cip_char[a++] = cip.charAt(1);
                pl_char[a1++] = pl.charAt(0);
                pl_char[a1++] = pl.charAt(1);

                keys = knownPlain(cip_char,pl_char);

                System.out.println(keys[0] + " " + keys[1] + keys[2] + " " + keys[3]);
            }
        }

        public static int extendedeuclidean(int r2)
        {
            int q,r1=26,t1,t2=1,r,t;
            t1=0;
            while (r2>0)
            {
                q=r1/r2;
                r=r1-(q*r2);
                r1=r2;
                r2=r;
                t=t1-(q*t2);
                t1=t2;
                t2=t;
            }
            if(r1==1)
            {
                if(t1<0)
                {
                    return t1+26;
                }
                else
                {
                    if(t1>26)
                    {
                        return t1%26;
                    }
                    else
                    {
                        return t1;
                    }
                }
            }
            else
            {
                return t1;
            }
        }
        public static void printmatrix(int a[][])
        {
            for(int i=0;i<2;i++)
            {
                for(int j=0;j<1;j++)
                {
                    a[i][j]%=26;
                    char c=(char)(a[i][j]+65);
                    System.out.print(c);
                }
            }
        }
        public static int[][] scalarmultiply(int a[][],int b)
        {
            for(int i=0;i<2;i++)
            {
                for(int j=0;j<2;j++)
                {
                    a[i][j]*=b;
                    a[i][j]%=26;
                }
            }
            return a;
        }
        public static int[][] adjmatrix(int a[][])
        {
            int [][] c=new int[2][2];
            c[0][0]=a[1][1];
            c[1][1]=a[0][0];
            c[0][1]=-1*a[0][1];
            c[1][0]=-1*a[1][0];
            if(c[0][1]<0)
            {
                c[0][1]=c[0][1]+26;
            }
            if(c[1][0]<0)
            {
                c[1][0]=c[1][0]+26;
            }
            return c;
        }
        public static int[][] matrixmultiply(int a[][] ,int b[][])
        {
            int c[][]=new int[2][1];
            for(int i=0;i<a.length;i++)
            {
                for(int j=0;j<b[0].length;j++)
                {
                    c[i][j]=0;
                    for(int k=0;k<a[0].length;k++)
                    {
                        c[i][j]+=a[i][k]*b[k][j];
                    }
                }
            }
            return c;
        }
        public static int[][] modmtrix(int a[][])
        {
            int[][] c=new int[2][1];
            for(int i=0;i<2;i++)
            {
                for(int j=0;j<1;j++)
                {
                    c[i][j]=a[i][j]%26;
                }
            }
            return c;
        }

        static int[] knownPlain(char[] cipher, char[] plain ){

            int k=0;
            int roots[] = new int[4];
            int a = (int) cipher[0] - 64;
            int b = (int) cipher[1] - 64;
            int c = (int) cipher[2] - 64;
            int d = (int) cipher[3] - 64;
            int e[] = {(int) plain[0] - 96, (int) plain[1] - 96};
            int f[] = {(int) plain[2] - 96, (int) plain[3] - 96};
            while(k<2) {
                int x, y;
                int det = ((a) * (d) - (b) * (c));
                x = ((d) * (e[k]) - (b) * (f[k])) / det;
                y = ((a) * (f[k]) - (c) * (e[k])) / det;
                k++;
            }
            return roots;

        }
}

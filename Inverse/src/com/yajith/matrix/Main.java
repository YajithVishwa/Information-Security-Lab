package com.yajith.matrix;

import java.util.Scanner;

public class Main {
    static int N=3;
    static int inverse(int a,int n)
    {
        a=a%n;
        for(int b=1;b<n;b++)
        {
            if((a*b)%n==1)
            {
                return b;
            }
        }
        return 1;
    }
    static int det(int m[][],int num)
    {
        int e=0;
        if(num==1){return m[0][0];}
        int temp[][]=new int[N][N];
        int s=1;
        for(int f=0;f<num;f++)
        {
            cofactor(m,temp,0,f,num);
            e+=s*m[0][f]*det(temp,num-1);
            s=-s;
        }
        return e;

    }
    static void cofactor(int mat[][],int temp[][],int p,int q,int n)
    {
        int i=0,j=0;
        for(int r=0;r<n;r++)
        {
            for(int c=0;c<n;c++)
            {
                if(r!=p&&c!=q)
                {
                    temp[i][j++]=mat[r][c];
                    if(j==n-1)
                    {
                        j=0;
                        i++;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner s=new Scanner(System.in);
        int[][] mat=new int[N][N];
        for(int i=0;i<N;i++)
        {
            for(int j=0;j<N;j++)
            {
                mat[i][j]=s.nextInt();
            }
        }
        int d=det(mat,N);
        System.out.println("Determinant is "+d +" and Inverse is "+ inverse(d,26));

    }
}

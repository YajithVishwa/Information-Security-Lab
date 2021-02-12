package com.yajith.matrix;
public class Main {
    static final int number=3;
    static void cofacto(int mat[][],int temp[][],int p,int q,int n)
    {
        int i=0,j=0;
        for(int row=0;row<n;row++)
        {
            for(int col=0;col<n;col++)
            {
                if(row!=p&&col!=q)
                {
                    temp[i][j++]=mat[row][col];
                    if(j==n-1)
                    {
                        j=0;
                        i++;
                    }
                }
            }
        }
    }
    static int determinant(int mat[][],int n)
    {
        int d=0;
        if(n==1)
        {
            return mat[0][0];
        }
        int temp[][]=new int[number][number];
        int sign=1;
        for(int f=0;f<n;f++)
        {
            cofacto(mat,temp,0,f,n);
            d+=sign*mat[0][f]*determinant(temp,n-1);
            sign=-sign;
        }
        return d;
    }
    static int inverse(int a,int n)
    {
        a=a%n;
        for(int x=1;x<n;x++)
        {
            if((a*x)%n==1)
            {
                return x;
            }
        }
        return 1;
    }
    public static void main(String[] args) {
	    int mat[][]={{1,2,3},{4,5,6},{7,8,9}};
	    int det=determinant(mat,number);
        System.out.println("Inverse of "+det+" is "+inverse(det,26));
    }
}

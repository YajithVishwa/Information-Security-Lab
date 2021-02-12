package com.yajith.adjoint;

import java.util.Scanner;

public class Main {
    static final int N=3;
    static void getCofactor(int A[][],int temp[][],int p,int q,int n)
    {
        int i=0,j=0;
        for(int row=0;row<n;row++)
        {
            for(int col=0;col<n;col++)
            {
                if(row!=p&&col!=q)
                {
                    temp[i][j++]=A[row][col];
                    if(j==n-1)
                    {
                        j=0;i++;
                    }
                }
            }
        }
    }
    static int determinant(int A[][],int n)
    {
        int D=0;
        if(n==1)
        {
            return A[0][0];
        }
        int [][] temp=new int[N][N];
        int sign=1;
        for(int f=0;f<n;f++)
        {
            getCofactor(A,temp,0,f,n);
            D+=sign*A[0][f]*determinant(temp,n-1);
            sign=-sign;

        }
        return D;
    }
    static void adjoint(int A[][] ,int adj[][])
    {
        if(N==1)
        {
            adj[0][0]=1;
            return;
        }
        int sign=1;
        int temp[][]=new int[N][N];
        for(int i=0;i<N;i++)
        {
            for(int j=0;j<N;j++)
            {
                getCofactor(A,temp,i,j,N);
                sign=((i+j)%2==0)?1:-1;
                adj[j][i]=(sign)*determinant(temp,N-1);
            }
        }
    }
    public static void main(String[] args) {
        int[][] A=new int[N][N];
        int [][] adj=new int[N][N];
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter elements of matrix");
        for(int i=0; i<N; i++)
        {
            for(int j=0; j<N; j++)
            {
                A[i][j]=scanner.nextInt();
            }
        }
        adjoint(A,adj);
        System.out.println("Cofactors are");
        {
            for(int i=0; i<N; i++)
            {
                for(int j=0; j<N; j++)
                {
                    System.out.print(adj[j][i]+" ");
                }
                System.out.println();
            }
        }
        System.out.println("Adjoints are");
        for(int i=0; i<N; i++)
        {
            for(int j=0; j<N; j++)
            {
                System.out.print(adj[i][j]+" ");
            }
            System.out.println();
        }
    }
}

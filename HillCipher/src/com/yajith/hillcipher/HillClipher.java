package com.yajith.hillcipher;
import java.util.*;
class HillClipher
{
    static final String ALPHABET="abcdefghijklmnopqrstuvwxyz";
    int[][] numencode(String ptext,int key)
    {
        int row=0;
        int col=0;
        int res=0;
        int extracol=0;
        int k=0;
        int i=0;
        int j=0;
        int col1=0;
        String alpha="abcdefghijklmnopqrstuvwxyz";
        int len=ptext.length();
        int[] enc=new int[100];
        for(i=0;i<len;i++)
        {
            enc[i]=alpha.indexOf(ptext.toLowerCase().charAt(i));
        }
        if(len%key==0)
        {
            row=key;
            col1=len/key;
        }
        else
        {
            row=key;
            res=len%key;
            res=key-res;
            col=res+len;
            col1=col/key;
            for(i=len;i<col;i++)
            {
                enc[i]=23;
            }
        }
        int[][] ans=new int[key][col1];
        k=0;
        for(i=0;i<col1;i++)
        {
            for(j=0;j<key;j++)
            {
                ans[j][i]=enc[k];
                k++;
            }
        }
        for(i=0;i<key;i++)
        {
            for(j=0;j<col1;j++)
            {
                System.out.print(ans[i][j]);
                System.out.print("\t");
            }
            System.out.println();
        }
        return ans;
    }
    void decode(int c[][],int len)
    {
        String p="";
        int k=c.length;
        int q=len/k;
        for(int j=0;j<=q;j++)
        {
            for(int i=0;i<k;i++)
            {
                p+=ALPHABET.charAt(c[i][j]);
            }
        }
        System.out.print(p);
    }
    void decode1(int c[][],int len)
    {
        String p="";
        int k=c.length;
        int q=len/k;
        for(int j=0;j<q;j++)
        {
            for(int i=0;i<k;i++)
            {
                p+=ALPHABET.charAt(c[i][j]);
            }
        }
        System.out.println(p.substring(0,len-1));
    }
    int[][] matmul (int key[][],int a[][])
    {
        int row=key.length;
        int col=a[0].length;
        int col1=key[0].length;
        int [][] c= new int[row][col];
        for (int i = 0; i< row; i++)
        {
            for (int j = 0; j < col; j++)
            {
                for (int k = 0; k < row; k++)
                {
                    c[i][j] = c[i][j] + key[i][k] * a[k][j];
                }
            }
        }
        return c;
    }
    static int determinant(int mat[][], int n)
    {
        int D = 0; // Initialize result
        if (n == 1)
            return mat[0][0];
        // cofactors
        int temp[][] = new int[n][n];
        int sign = 1;
        for (int f = 0; f < n; f++)
        {
            getCofactor(mat, temp, 0, f, n);
            D += sign * mat[0][f]
                    * determinant(temp, n - 1);
            sign = -sign; }
        D=D%26;
        if(D<0)
        {
            D=26+D;
            return D;
        }
        return D;
    }
    int mulinverse(int a,int m)
    {
        a = a % m;
        for (int x = 1; x < m; x++)
            if ((a * x) % m == 1)
                return x;
        return 1;
    }
    int[][] inverse(int adjoint1[][],int g,int N)
    {
        int k[][]=new int[N][N];
        for(int i=0;i<N;i++)
        {
            for(int j=0;j<N;j++)
            {
                k[i][j]=(g*adjoint1[i][j])%26;
            }
        }
        for(int i=0;i<N;i++)
        {
            for(int j=0;j<N;j++)
            {
                if(k[i][j]<0)
                {
                    k[i][j]=26+k[i][j];
                }
            }
        }
        return k;
    }
    int[][] adjoint(int mat[][],int N)
    {
        int si = 1;
        int[][]temp=new int[N][N];
        int[][]adjoint=new int[N][N];
        for (int i=0; i<N; i++)
        {
            for (int j=0; j<N; j++)
            {
                //adjoint
                getCofactor(mat, temp, i, j, N);
                si = ((i+j)%2==0)? 1: -1;
                adjoint[j][i] = (si)*(determinant(temp, N-1));
            }
        }
        return adjoint;
    }
    static void getCofactor(int mat[][],int temp[][], int p, int q, int n)
    {
        int i = 0, j = 0;
        for (int row = 0; row < n; row++)
        {
            for (int col = 0; col < n; col++)
            {
                if (row != p && col != q)
                {
                    temp[i][j++] = mat[row][col];
                    if (j == n - 1)
                    {
                        j = 0;
                        i++;
                    }
                }
            }
        }
    }

    int[][] encrypt(int key[][] ,int a[][] ,int N)
    {
        int row=a.length;
        int col=a[0].length;
        int mat1[][]=new int[N][col];
        char [][] replace=new char[N][col];
        mat1 = matmul(key,a);
        System.out.println("The encrypted matrix is: ");
        for (int i = 0; i< N; i++)
        {
            System.out.println();
            for (int j = 0; j < col; j++)
            {
                mat1[i][j]=(mat1[i][j])%26;
                System.out.print("\t"+mat1[i][j]);
            }
        }
        return mat1;
    }


    // decryption
    int[][] decrypt(int key[][], int b[][],int N)
    {
        int m=26;
        int row=b.length;
        int col=b[0].length;
        int[][] adjoint1=new int [N][N];
        int [][] keyinverse=new int[N][N];
        int[][] mat2=new int[row][col];
        int l=determinant(key, N);
        System.out.println("Determinant " + "of the matrix is : "+ l );
        int g=mulinverse(l,m);
        System.out.println("Inverse of determinant: "+g);
        adjoint1=adjoint(key,N);
        keyinverse=inverse(adjoint1,g,N);
        mat2=matmul(keyinverse,b);
        System.out.println("Decrypted matrix: ");
        for(int i=0;i<row;i++)
        {
            System.out.println();
            for(int j=0;j<col;j++)
            {
                mat2[i][j]=(mat2[i][j])%26;
                System.out.print("\t"+mat2[i][j]);
            }
        }
        return mat2;
    }


    // Known plain text – cipher text attack
    int[][] knownptct(int a[][],int b[][],int N2)
    {
        int row=a.length;
        int col=b[0].length;
        int m=26;
        int [][] adjoint1=new int[row][col];
        int [][] keyinverse1=new int[row][col];
        int [][] mat2=new int[row][col];
        int l=determinant(b, row);
        System.out.println("\n");
        System.out.println("\n");
        System.out.println("\n");
        System.out.println("\nDeterminant " + "of the matrix is : "+ l );
        int g=mulinverse(l,m);
        System.out.println("Inverse of determinant: "+g);
        adjoint1=adjoint(b,row);
        keyinverse1=inverse(adjoint1,g,row);
        mat2=matmul(a,keyinverse1);
        System.out.println("Key matrix: ");
        for(int i=0;i<N2;i++)
        {
            System.out.println();
            for(int j=0;j<N2;j++)
            {
                mat2[i][j]=(mat2[i][j])%26;
                System.out.print("\t"+mat2[i][j]);
            }
        }
        return mat2;
    }
}

class Execution{
    public static void main (String[] args)
    {
        int m=26;
        HillClipher h = new HillClipher();
        Scanner input = new Scanner(System.in);
        Scanner sc= new Scanner(System.in);
        System.out.println("Encryption");
        System.out.println("Enter the order of key: ");
        int o=input.nextInt();
        int[][] key=new int[o][o];
        System.out.println("Enter the elements of key matrix "+"("+o*o+")"+":");
        for(int i=0;i<o;i++)
        {
            for(int j=0;j<o;j++)
            {
                key[i][j]=input.nextInt();
            }
        }
        System.out.println("Enter the plain text to encode :");
        String pt=sc.nextLine();
        int len=pt.length();
        int [][]a=h.numencode(pt,o);
        int[][]mat1=h.encrypt(key,a,o);
        System.out.println("\nThe encrypted cipher text is: ");
        h.decode(mat1,len);
        System.out.println("\nDECRYPTION");
        System.out.println("Enter the order of key matrix: ");
        int o1=input.nextInt();
        int[][] key1=new int[o1][o1];
        System.out.println("\nEnter elements of key matrix:"+"("+o1*o1+")"+":");
        for(int i=0;i<o1;i++)
        {
            for(int j=0;j<o1;j++)
            {
                key1[i][j]=input.nextInt();
            }
        }
        System.out.println("Enter the cipher text to decode: ");
        String pt1=sc.nextLine();
        int len1=pt1.length();

        int[][]a1=h.numencode(pt1,o1);
        int mat2[][]=h.decrypt(key1,a1,o1);
        System.out.println("\nThe decrypted plain text is: ");
        h.decode1(mat2,len1);
        System.out.println("KNOWN Plain Text - Cipher Text");
        System.out.println("Enter the Plain text ");
        String pt3 = sc.nextLine();
        System.out.println("Enter the cipher text ");
        String ct = sc.nextLine();
        System.out.println("Enter order of key matrix: ");
        int o2=input.nextInt();
        int [][] a2=new int[o2][o2];
        int [][] a3=new int[o2][o2];
        int [][]mat3=new int[o2][o2];
        a2=h.numencode(pt3,o2);
        a3=h.numencode(ct,o2);
        mat3= h.knownptct(a2,a3,o2);
    }
}
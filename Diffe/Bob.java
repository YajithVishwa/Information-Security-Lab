import java.net.*;
import java.io.*;
import java.util.*;

public class Bob{
static void Pr (int num)
{
boolean flag = false;
for(int i = 2; i <= num/2; ++i)
{
if(num % i == 0)
{
flag = true;
break;
}
}
if (!flag)
{
System.out.println(num + " is a prime number.");
}
else
{
System.out.println(num + " is not a prime number.");
}
for(int i=2;i < num;i++)
{
int p=0;
int[] a = new int[200];
System.out.println(" ");
for(int j=1;j < num;j++)
{
p = (int)Math.pow(i, j);
int x1=p%num;
System.out.println(+i+" ^"+ j +" mod "+num +"="+x1);
a[j]=x1;
}
find(a,i,num);
}
}

static void find ( int [] a, int i, int num)
{

int[] b = new int[200];
for(int z=1;z<num ;z++)
{
int count=0;
for(int e=1;e<num;e++)
{
b=a;
if(b[e]==a[z]){
count++;
}

}
if(count ==1 && z == num-1)
System.out.println(+i+" is primitive root of "+num);

}
}
public static void main(String[] args)
{
try {
String pstr, gstr, Astr;
String serverName = "localhost";
int port = 8000;
System.out.print("enter prime:");
Scanner S1= new Scanner (System.in);
int p = S1.nextInt();
Pr(p);
System.out.print("Enter private key of Alice:");
int a = S1.nextInt();
System.out.print("Enter Gb:");
int g = S1.nextInt();
double Adash, serverB;

Socket client = new Socket(serverName, port);
System.out.println("Connected to port ");

OutputStream outToServer = client.getOutputStream();
DataOutputStream out = new DataOutputStream(outToServer);

pstr = Integer.toString(p);
out.writeUTF(pstr); 

gstr = Integer.toString(g);
out.writeUTF(gstr); 

double A = ((Math.pow(g, a)) % p); 
Astr = Double.toString(A);
out.writeUTF(Astr); 

System.out.println("From Alice (server): Private Key = " + a);

DataInputStream in = new DataInputStream(client.getInputStream());

serverB = Double.parseDouble(in.readUTF());
System.out.println("From Bob(client) : Public Key = " + serverB);

Adash = ((Math.pow(serverB, a)) % p); 

System.out.println("Secret Key of Bob "+ Adash);
client.close();
}
catch (Exception e) {
e.printStackTrace();
}
}
}

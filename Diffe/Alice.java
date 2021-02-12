import java.net.*;
import java.io.*;
import java.util.*;

public class Alice{
public static void main(String[] args) throws IOException
{
try {
int port = 8000;

Scanner Sc= new Scanner (System.in);
System.out.print("enter a Private key B of Bob:");
int b =Sc.nextInt();

double clientP, clientG, clientA, B, Bdash;
String Bstr;

ServerSocket serverSocket = new ServerSocket(port);
Socket server = serverSocket.accept();
System.out.println("Connected to port ");

System.out.println("The private key of Bob = " + b);

DataInputStream in = new DataInputStream(server.getInputStream());

clientP = Integer.parseInt(in.readUTF());System.out.println("From Alice: P = " + clientP);
clientG = Integer.parseInt(in.readUTF()); 

System.out.println("From Alice : G = " + clientG);

clientA = Double.parseDouble(in.readUTF()); 
System.out.println("From Alice : Public Key = " + clientA);

B = ((Math.pow(clientG, b)) % clientP); 
Bstr = Double.toString(B);

OutputStream outToclient = server.getOutputStream();
DataOutputStream out = new DataOutputStream(outToclient);

out.writeUTF(Bstr); 

Bdash = ((Math.pow(clientA, b)) % clientP); System.out.println("Secret key of Alice "+ Bdash);
server.close();
}

catch (SocketTimeoutException s) {
System.out.println("Timed out!");
}
catch (IOException e) {	
}
}
}

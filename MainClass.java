import java.util.Scanner;
public class MainClass{ 
  public static void main(String args[]){
       Scanner _key = new Scanner(System.in);
double  a;
double  b;
double  c;
double  d;
double  f;
String  t1;
System.out.println("Programa Teste");
System.out.println("Digite A");
a= _key.nextDouble();
System.out.println("Digite B");
b= _key.nextDouble();
if (a>b) {
c = a+b;
}else {
c = a-b;
}

System.out.println("C e igual a ");
System.out.println(c);
d = c*a+b;
System.out.println("D e igual a ");
System.out.println(d);
while (a<b) {
a= _key.nextDouble();
System.out.println(a);
System.out.println("TESTE texto 45 ");
}
  }}
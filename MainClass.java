import java.util.Scanner;
public class MainClass{ 
  public static void main(String args[]){
       Scanner _key = new Scanner(System.in);
double  a;
double  b;
double  c;
double  d;
String  t;
double  f;
System.out.println("Programa Teste");
System.out.println("Digite A");
a= _key.nextDouble();
System.out.println("Digite B");
b= _key.nextDouble();
if (a>b) {
    c = a+b;
} else {
    c = (a-b)/(2+5.6);
}

t= _key.nextLine();
t = "hello"+" "+("world");
f = (a+c)/(2+5.6);
f = -10.525/(+10);
f = 10-10+-1;
f = -10.05;
do {
    a= _key.nextDouble();
    System.out.println(a);
} while (a!=0);

System.out.println("C e igual a ");
System.out.println(c);
d = c*(a+b);
System.out.println("D e igual a ");
System.out.println(d);
while (a<b) {
    a= _key.nextDouble();
    System.out.println(a);
    System.out.println("TESTE 1");
    a = a+1;
}
  }}
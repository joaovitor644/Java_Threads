import java.lang.Thread;

public class GerenciadorLembretes 
{
  public static void main(String[] args)
  {
    Lembrete m1 = new Lembrete("Desalex",1,20);
    Lembrete m2 = new Lembrete("Predisin",4,8);
    Lembrete m3 = new Lembrete("Berotec",5,10);

    Thread t1 = new Thread(m1);
    t1.start();

    Thread t2 = new Thread(m2);
    t2.start();

    Thread t3 = new Thread(m3);
    t3.start();
  }
}

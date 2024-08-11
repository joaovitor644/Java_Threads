import java.lang.Thread;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

public class HelloWorld
{
  public static void main(String[] args)
  {
    Hello h1 = new Hello(1);
    Hello h2 = new Hello(2);

    Thread t1 = new Thread(h1);
    Thread t2 = new Thread(h2);
    
    LocalTime time_t1 = LocalTime.now();
    t1.start();
    LocalTime time_t2 = LocalTime.now();
    t2.start();
    
    System.out.println("Tempo da Thread 1: "+time_t1);
    System.out.println("Tempo da Thread 2: "+time_t2);
  }
}

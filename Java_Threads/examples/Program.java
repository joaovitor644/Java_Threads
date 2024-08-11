

public class Program
{
  public static void main(String[] args) 
  {
    MinhaThread threadA = new MinhaThread("Thread A");
    MinhaThread threadB = new MinhaThread("Thread B");

    threadA.start();
    threadB.start();

    System.out.println("FIM main");
  }
}

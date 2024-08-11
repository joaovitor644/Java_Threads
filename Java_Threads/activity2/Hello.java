import java.lang.Thread;

public class Hello implements Runnable
{
  private int id;
  public Hello(int id)
  {
    this.id = id;
  }
  public void run()
  {
    for(int i = 0; i < 1;i++)
      System.out.println("Hello , eu sou a Thread numero "+this.id);
  }
}

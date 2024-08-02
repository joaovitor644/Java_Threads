import java.lang.Thread;

public class MinhaThread extends Thread
{
  private String name;
  public MinhaThread(String name)
  {
    this.name = name;
  }
  public void run()
  {
    for(int i = 0; i < 10;i++)
    {
      System.out.printf("%s %d\n",this.name,i);
    }
    System.out.printf("FIM %s\n",this.name);
  }
}

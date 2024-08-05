import java.lang.Thread;

public class Lembrete implements Runnable 
{
  private int intervalo;
  private int total;
  private String medicamento;

  public Lembrete(String medicamento,int intervalo,int total)
  {
    this.medicamento = medicamento;
    this.intervalo = intervalo*1000;
    this.total = total;
  }
  public void run()
  {
    for(int i = 1;i <= this.total;i++)
    {
      System.out.println(this.medicamento+"-"+i);
      try
      {
        Thread.sleep(this.intervalo);
      } catch(InterruptedException e){
        e.printStackTrace();
      }
    }
  }
}

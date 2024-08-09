import java.util.Collections;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Thread;

public class WorkArray implements Runnable
{
  private ArrayList<Integer> vector;
  public WorkArray(ArrayList<Integer> vector)
  {
    this.vector = vector;
  }
    @Override
  public void run(){
    Collections.sort(this.vector);
    System.out.println(this.vector);
  }
}

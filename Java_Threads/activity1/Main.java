import java.util.Scanner;
import java.lang.Thread;
import java.util.ArrayList;
public class Main
{
  public static void main(String[] args)
  {
    Scanner scanner = new Scanner(System.in);
    while (true) {
      ArrayList<Integer> vetor = new ArrayList<>();
      System.out.println("Digite os elementos do vetor (digite 'fim' para terminar):");
      while (scanner.hasNextInt()) {
        vetor.add(scanner.nextInt());
      }
      scanner.nextLine();  
      WorkArray w1 = new WorkArray(vetor);
      Thread t1 = new Thread(w1);
      t1.start();
    } 
  }
}

public class SQLiteExampleMultiThreadTest {

    public static void main(String[] args) {
        SQLiteExample example = new SQLiteExample();

        example.createTable();

        Thread t1 = new Thread(() -> example.insertUser("João Silva", 30, "joao.silva@example.com"));
        Thread t2 = new Thread(() -> example.insertUser("Maria Oliveira", 25, "maria.oliveira@example.com"));
        Thread t3 = new Thread(() -> example.insertUser("Carlos Santos", 40, "carlos.santos@example.com"));
        Thread t4 = new Thread(() -> example.insertUser("Ana Souza", 22, "ana.souza@example.com"));

        Thread t5 = new Thread(() -> {
            System.out.println("Lista de usuários (Thread 1):");
            example.selectAllUsers();
        });
        Thread t6 = new Thread(() -> {
            System.out.println("Lista de usuários (Thread 2):");
            example.selectAllUsers();
        });


        t1.start();
        t2.start();
        t3.start();
        t4.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t5.start();
        t6.start();


        try {
            t5.join();
            t6.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

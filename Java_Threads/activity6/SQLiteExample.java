import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SQLiteExample {
	private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

	
    static {
        try {
            // Carregando o driver JDBC para SQLite
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver JDBC do SQLite não encontrado. Certifique-se de que o .jar está no classpath.");
            e.printStackTrace();
        }
    }

	
    private Connection connect() {
        String url = "jdbc:sqlite:meu_banco.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS usuarios (\n"
                   + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                   + " nome TEXT NOT NULL,\n"
                   + " idade INTEGER NOT NULL,\n"
                   + " email TEXT NOT NULL UNIQUE\n"
                   + ");";

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement()) {
            // Cria a tabela
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertUser(String nome, int idade, String email) {
        String sql = "INSERT INTO usuarios(nome, idade, email) VALUES(?, ?, ?)";
        lock.writeLock().lock();
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nome);
            pstmt.setInt(2, idade);
            pstmt.setString(3, email);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
        	lock.writeLock().unlock();
        }
      	
    }

    public void selectAllUsers() {
        // SQL para selecionar todos os usuários
        String sql = "SELECT id, nome, idade, email FROM usuarios";
     	lock.readLock().lock();
	        try (Connection conn = this.connect();
	             Statement stmt = conn.createStatement();
	             ResultSet rs = stmt.executeQuery(sql)) {
	            while (rs.next()) {
	                System.out.println(rs.getInt("id") + "\t" +
	                                   rs.getString("nome") + "\t" +
	                                   rs.getInt("idade") + "\t" +
	                                   rs.getString("email"));
	            }
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        } finally {
	        	lock.readLock().unlock();
	        }
    }

}

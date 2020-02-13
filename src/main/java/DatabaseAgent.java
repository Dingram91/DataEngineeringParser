import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseAgent {
  private static Connection connectToDB() {
    final String DbUrl = "jdbc:sqlite:./src/data/BookStore.db";


    // Database credentials
    final String User = "";
    final String Pass = "";
    Connection conn = null;

    try {
      // create connection
      conn = DriverManager.getConnection(DbUrl, User, Pass);
      return conn;

    } catch (Exception e) {
      e.printStackTrace();
    }

    return null;
  }

  protected static void getPublishersFromDB() {
    Connection conn = connectToDB();

    // sql query string
    String sqlStatement = "SELECT * from publisher";

    try (PreparedStatement ps = conn.prepareStatement(sqlStatement)) {

      ResultSet resultSet = ps.executeQuery();

      while (resultSet.next()) {
        String publisher = resultSet.getString("pub_name");
        System.out.println(publisher);
      }
    }  catch (SQLException e) {
      e.printStackTrace();
    }
  }

  protected static void insertAuthorIntoDb(AuthorParser authorParser) {
    Connection conn = connectToDB();

    // sql query string
    String sqlStatement = "insert into author(author_name, author_email, author_url) "
        + "VALUES (?, ?, ?);";

    try (PreparedStatement ps = conn.prepareStatement(sqlStatement)) {
      ps.setString(1, authorParser.getName());
      ps.setString(2, authorParser.getEmail());
      ps.setString(3, authorParser.getUrl());
      ps.execute();

    }  catch (SQLException e) {
      e.printStackTrace();
    }
  }

  protected static void insertBookIntoDb(BookParser bookParser) {
    Connection conn = connectToDB();

    // sql query string
    String sqlStatement = "insert into book(isbn, publisher_name, author_name, book_year, "
        + "book_title, book_price) VALUES (?, ?, ?, ?, ?, ?);";

    try (PreparedStatement ps = conn.prepareStatement(sqlStatement)) {
      ps.setString(1, bookParser.getIsbn());
      ps.setString(2, bookParser.getPublisher_name());
      ps.setString(3, bookParser.getAuthor_name());
      ps.setInt(4, bookParser.getBook_year());
      ps.setString(5, bookParser.getBook_title());
      ps.setInt(6, bookParser.getBook_price());
      ps.execute();

    }  catch (SQLException e) {
      e.printStackTrace();
    }
  }
}

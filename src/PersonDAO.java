import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PersonDAO {
    public void addPerson(Person person) {
        String query = "INSERT INTO person (first_name, last_name, email, age) VALUES (?,?,?,?)";
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, person.getFirstName());
            statement.setString(2, person.getLastName());
            statement.setString(3, person.getEmail());
            statement.setInt(4, person.getAge());
            statement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}

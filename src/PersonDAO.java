import org.jetbrains.annotations.NotNull;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO {
    /* IMPLEMENTS CRUD OPERATIONS (Data Access Object)
    * Has Empty constructor implicitly. */
    public void addPerson(@NotNull Person person) {
        /* Creates a person in the database (CREATE) part of CRUD.
        Args: person (Person Object)*/
        // The SQL query to use
        String query = "INSERT INTO person (first_name, last_name, email, age) VALUES (?,?,?,?)";
        // Create a Connection type variable named connection that handles the session
        // with SQL server. The method getConnection returns DriveManager.getConnection(specified
        // URL, username, password).
        Connection con = DatabaseConnection.getConnection();
        // Send parameterized SQL Statements to the DB.
        // PreparedStatement statement = con.prepareStatement(query);
        try (con; PreparedStatement statement = con.prepareStatement(query);) {
            // Create the person in DB, based on the Person object we passed to the
            // addPerson method. Use the getters and execute the update op.
            // NOTE TO SELF: the id record auto-increments... tsk
            statement.setString(1, person.getFirstName());
            statement.setString(2, person.getLastName());
            statement.setString(3, person.getEmail());
            statement.setInt(4, person.getAge());
            statement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<Person> getAllPersons() {
        /* Returns a list (READ OP) filled with all present personal information
        on the DB. It of course does not yet handle the exception gracefully, but alas.
        */
        List<Person> persons = new ArrayList<>();
        String query = "SELECT * FROM person";
        Connection con = DatabaseConnection.getConnection();
        try (con; Statement stmt = con.createStatement(); ResultSet result = stmt.executeQuery(query);) {
            while (result.next()) {
                Person person = new Person(result.getInt("id"), result.getString("first_name"),
                        result.getString("last_name"), result.getString("email"),
                        result.getInt("age"));
                persons.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return persons; //ArrayList type
    }

    public Person getPersonById (int id) {
        Person person = null;
        String query = "SELECT * FROM person WHERE id = ?";
        try (Connection con = DatabaseConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                person = new Person(result.getInt("id"), result.getString("first_name"),
                        result.getString("last_name"), result.getString("email"),
                        result.getInt("age"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }

    public void updatePerson (@NotNull Person person) {
        String query = "UPDATE person SET first_name = ?, last_name = ?, email = ?, age = ? WHERE id = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            // set attribute to record in Database with specified index. The
            // index corresponds to the indices in the query.
                stmt.setString(1, person.getFirstName());
                stmt.setString(2, person.getLastName());
                stmt.setString(3, person.getEmail());
                stmt.setInt(4, person.getAge());
                stmt.setInt(5, person.getId());
                stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePerson (int id) {
        String query = "DELETE FROM person WHERE id = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

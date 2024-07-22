import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create new PersonDAO instance
        PersonDAO personDAO = new PersonDAO();

//        // Add a person
//        Person newPerson = new Person(); // Call the empty constructor of Person
//        // since you cannot pass id value directly (DB auto-increment policy)
//        //use setters instead to set fields.
//        newPerson.setFirstName("Pericles");
//        newPerson.setLastName("Stamatis");
//        newPerson.setEmail("periclesstamatis@gmail.com");
//        newPerson.setAge(27);
//        personDAO.addPerson(newPerson);

        // Get every person in DB
        List<Person> persons = personDAO.getAllPersons();
        persons.forEach(person -> System.out.println(person.getLastName() + " " + person.getFirstName()));

        // Get person by ID
        int id = 1;
        Person person = personDAO.getPersonById(id);
        System.out.println(person.getLastName() + " " + person.getFirstName());

//        // Update person
//        person.setLastName("Stam.");
//        personDAO.updatePerson(person);
//        System.out.println(person.getLastName() + " " + person.getFirstName());

//        // Delete person
//        personDAO.deletePerson(1);

    }
}

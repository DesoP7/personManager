package nl.DesoP7;

import java.util.ArrayList;
import java.util.Scanner;

public class PersonManager {

    // Create an ArrayList to store Person objects
    private static ArrayList<Person> persons = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        // Main program loop
        while (running) {
            // Show menu and handle user choice
            int choice = getUserChoice(scanner);

            // Process the user's choice
            running = processUserChoice(choice, scanner);
        }

        // Close the scanner to avoid resource leaks
        scanner.close();
    }

    // Method to display the menu and get the user's choice
    private static int getUserChoice(Scanner scanner) {
        System.out.println("Choose an option:");
        System.out.println("1. Add a person");
        System.out.println("2. Remove a person");
        System.out.println("3. Show all persons");
        System.out.println("4. Exit");

        // Return the user's choice
        return scanner.nextInt();
    }

    // Method to handle the user's choice
    private static boolean processUserChoice(int choice, Scanner scanner) {
        scanner.nextLine();  // Consume newline character
        switch (choice) {
            case 1:
                addPerson(scanner);
                break;
            case 2:
                removePerson(scanner);
                break;
            case 3:
                showAllPersons();
                break;
            case 4:
                System.out.println("Exiting program.");
                return false;  // Stop the loop and exit the program
            default:
                System.out.println("Invalid choice. Please try again.");
        }
        return true;  // Continue running the program
    }

    // Method to add a person to the ArrayList
    private static void addPerson(Scanner scanner) {
        String name = getInput(scanner, "Enter the name of the person: ");
        int age = getAge(scanner);
        String email = getInput(scanner, "Enter the email of the person: ");
        String city = getInput(scanner, "Enter the city of the person: ");
        String bsn = getInput(scanner, "Enter the BSN (social security number) of the person: ");
        String phoneNumber = getInput(scanner, "Enter the phone number of the person: ");

        // Create a new Person object and add it to the ArrayList
        Person person = new Person(name, age, email, city, bsn, phoneNumber);
        persons.add(person);
        System.out.println(name + " has been added.");
    }

    // Method to remove a person from the ArrayList
    private static void removePerson(Scanner scanner) {
        String name = getInput(scanner, "Enter the name of the person to remove: ");

        // Iterate through the ArrayList to find and remove the person by name
        boolean found = false;
        for (Person person : persons) {
            if (person.getName().equalsIgnoreCase(name)) {
                persons.remove(person);
                System.out.println(name + " has been removed.");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Person not found.");
        }
    }

    // Method to display all persons in the ArrayList
    private static void showAllPersons() {
        if (persons.isEmpty()) {
            System.out.println("No persons available.");
        } else {
            System.out.println("List of all persons:");
            // Iterate over the ArrayList and print each person's details
            for (Person person : persons) {
                System.out.println(person);  // Calls the toString method of Person class
            }
        }
    }

    // Helper method to get a string input from the user
    private static String getInput(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    // Helper method to get the age as an integer input
    private static int getAge(Scanner scanner) {
        System.out.print("Enter the age of the person: ");
        int age = scanner.nextInt();
        scanner.nextLine();  // Consume newline character
        return age;
    }
}

// Class representing a Person
class Person {
    private String name;
    private int age;
    private String email;
    private String city;
    private String bsn;
    private String phoneNumber;

    // Constructor for initializing a person object
    public Person(String name, int age, String email, String city, String bsn, String phoneNumber) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.city = city;
        this.bsn = bsn;
        this.phoneNumber = phoneNumber;
    }

    // Getter for name (needed for searching in the removePerson method)
    public String getName() {
        return name;
    }

    // Override the toString method to display a person's details
    @Override
    public String toString() {
        return "Name: " + name + ", Age: " + age + ", Email: " + email +
                ", City: " + city + ", BSN: " + bsn + ", Phone Number: " + phoneNumber;
    }
}

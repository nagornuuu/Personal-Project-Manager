package Services;

import models.Project;

import java.util.Scanner;

/**
 * The TaskManager class is responsible for handling user interaction with the project tasks
 * It allows users to add tasks, complete tasks, and view the task list
 */
public class TaskManager {
    private Project project;
    private Scanner scanner;

    /**
     * Constructs for a TaskManager instance
     * Initializes the scanner for user input
     */
    public TaskManager() {
        this.project = new Project("Default Project");
        this.scanner = new Scanner(System.in);
    }

    /**
     * Starts the task manager application
     * Users can add tasks, complete tasks, view tasks, or exit the application
     */
    public void start() {
        try {
            // Prompt user for project name
            System.out.println("Enter project name: ");
            String projectName = scanner.nextLine();
            project.setProjectName(projectName);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return; // Exit if project name is invalid
        }

        // Main menu loop
        while (true) {
            System.out.println("\n1. Add Task" +
                               "\n2. Complete Task" +
                               "\n3. View Tasks" +
                               "\n4. Exit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            // Handle user input options
            switch (choice) {
                case "1" -> project.addTask(); // Add a new task
                case "2" -> completeTask(); // Mark a task as completed
                case "3" -> project.displayTasks(); // Display all tasks
                case "4" -> {
                    System.out.println("Exiting....");
                    return; // Exit the program
                }
                default -> System.out.println("Invalid option");
            }
        }
    }

    /**
     * Allows the user to mark a task as completed
     * If the task is already completed or not found, an appropriate error message is displayed
     */
    private void completeTask() {
        try {
            // Prompt user for task title
            System.out.print("Enter task title to mark as completed: ");
            String title = scanner.nextLine();
            project.completeTask(title);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

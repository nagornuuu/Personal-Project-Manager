package Services;

import models.Project;
import models.Task;

import java.util.Scanner;

public class TaskManager {
    private Project project;
    private Scanner scanner;

    public TaskManager(String projectName) {
        this.project = new Project(projectName);
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("\n1. Add Task" +
                               "\n2. Complete Task" +
                               "\n3. View Tasks" +
                               "\n4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addTask();
                case 2 -> completeTask();
                case 3 -> project.displayTasks();
                case 4 -> {
                    System.out.println("Exiting....");
                    return;
                }
                default -> System.out.println("Invalid option");
            }
        }
    }

    private void addTask() {
        System.out.print("Enter task title: ");
        String title = scanner.nextLine();

        System.out.print("Enter task description: ");
        String description = scanner.nextLine();

        System.out.print("Enter task priority (1 - High, 2 - Medium, 3 - Low): ");
        int priority = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter task deadline (Format: DD/MM/YYYY): ");
        String deadline = scanner.nextLine();

        if (!deadline.matches("\\d{2}/\\d{2}/\\d{4}")) {
            System.out.println("Invalid date format. Use DD/MM/YYYYY");
            return;
        }

        Task task = new Task(title, description, priority, deadline);
        project.addTask(task);
        System.out.println("Task added");
    }

    private void completeTask() {
        System.out.print("Enter task title to mark as completed: ");
        String title = scanner.nextLine();
        project.completeTask(title);
        System.out.println("Task marked as completed");
    }
}

package Services;

import models.Project;

import java.util.Scanner;

public class TaskManager {
    private Project project;
    private Scanner scanner;

    public TaskManager() {
        this.project = new Project("Default Project");
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        try {
            System.out.println("Enter project name: ");
            String projectName = scanner.nextLine();
            project.setProjectName(projectName);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        while (true) {
            System.out.println("\n1. Add Task" +
                               "\n2. Complete Task" +
                               "\n3. View Tasks" +
                               "\n4. Exit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> project.addTask();
                case "2" -> completeTask();
                case "3" -> project.displayTasks();
                case "4" -> {
                    System.out.println("Exiting....");
                    return;
                }
                default -> System.out.println("Invalid option");
            }
        }
    }

    private void completeTask() {
        try {
            System.out.print("Enter task title to mark as completed: ");
            String title = scanner.nextLine();
            project.completeTask(title);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

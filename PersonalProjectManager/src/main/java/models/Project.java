package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The Project class manages a collection of tasks, allowing users to add tasks,
 * mark tasks as completed, and track project progress
 */
public class Project {
    private String name;        // Project name
    private List<Task> tasks;   // List of all tasks in the project
    private Scanner scanner;    // Scanner object for user iteraction

    /**
     * Constructs a new Project with the specified name
     * Initializes an empty task list and a scanner for input
     * @param name The name of the project
     */
    public Project(String name) {
        this.name = name;
        this.tasks = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Sets the project name with validation
     * @param name The name of the project
     * @throws Exception if the project name is empty or null
     */
    public void setProjectName(String name) throws Exception {
        if (name == null || name.trim().isEmpty()) {
            throw new Exception("Project name cannot be empty");
        }
        this.name = name.trim();
    }

    /**
     * Adds a new task to the project after validating user input
     */
    public void addTask() {
        String title = getValidTitle();
        String description = getValidDescription();
        int priority = getValidPriority();
        String deadline = getValidDeadline();

        try {
            // Ensure task does not already exist
            for (Task task : tasks) {
                if (task.getTitle().equalsIgnoreCase(title)) {
                    throw new Exception("Task with the same title already exists");
                }
            }

            // Create and add task
            Task task = new Task(title, description, priority, deadline);
            tasks.add(task);
            System.out.println("\nTask '" + title + "' successfully added");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Gets a valid task title from user input
     * @return A non-empty task title
     */
    private String getValidTitle() {
        while (true) {
            System.out.print("\nEnter Task Title: ");
            String title = scanner.nextLine().trim();
            if (!title.isEmpty()) {
                return title;
            }
            System.out.println("Task title cannot be empty. Please enter a valid title");
        }
    }

    /**
     * Gets a valid task description from user input
     * @return A non-empty task description
     */
    private String getValidDescription() {
        while (true) {
            System.out.print("Enter Task Description: ");
            String description = scanner.nextLine().trim();
            if (!description.isEmpty()) {
                return description;
            }
            System.out.println("Task description cannot be empty. Please enter a valid description");
        }
    }

    /**
     * Gets a valid priority level from user input
     * @return A priority level (1-High, 2-Medium, 3-Low)
     */
    private int getValidPriority() {
        while (true) {
            try {
                System.out.print("Enter Task Priority (1 - High, 2 - Medium, 3 - Low): ");
                int priority = Integer.parseInt(scanner.nextLine().trim());
                if (priority >= 1 && priority <= 3) {
                    return priority;
                }
                System.out.println("Priority must be between 1 and 3");
            } catch (NumberFormatException e) {
                System.out.println("Invalid priority input! Please enter a number (1, 2, or 3)");
            }
        }
    }

    /**
     * Gets a valid task deadline from user input
     * @return A valid deadline in DD/MM/YYYY format
     */
    private String getValidDeadline() {
        while (true) {
            System.out.print("Enter Task Deadline (Format: DD/MM/YYYY): ");
            String deadline = scanner.nextLine().trim();
            if (deadline.matches("\\d{2}/\\d{2}/\\d{4}")) {
                return deadline;
            }
            System.out.println("Invalid deadline format! Please use DD/MM/YYYY");
        }
    }

    /**
     * Marks a task as completed based on its title
     * @param title The title of the task to complete
     * @throws Exception if the task is not found or is already completed
     */
    public void completeTask(String title) throws Exception {
        boolean found = false;

        for (Task task : tasks) {
            if (task.getTitle().equalsIgnoreCase(title)) {
                if (task.isCompleted()) {
                    throw new Exception("Task '" + title + "' is already marked as completed");
                }

                task.completeTask();
                found = true;
                System.out.println("\nTask '" + task.getTitle() + "' marked as completed");
                break;
            }
        }

        if (!found) {
            throw new Exception("Task with title '" + title + "' not found");
        }
    }

    /**
     * Calculates the percentage of completed tasks in the project
     * @return The progress percentage (0-100)
     */
    public double getProgress() {
        if (tasks.isEmpty()) {
            return 0;
        }
        double completed = 0;
        for (Task task : tasks) {
            if (task.isCompleted()) {
                completed++;
            }
        }
        return Math.round((completed * 100) / tasks.size());
    }

    /**
     * Displays all tasks in the project, sorted by priority
     * Also shows the overall project progress
     */
    public void displayTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found");
            return;
        }
        System.out.println("\nTask List (Sorted by Priority)\n---------------------------------");
        tasks.stream()
                .sorted((t1, t2) -> Integer.compare(t1.getPriority(), t2.getPriority()))
                .forEach(task -> System.out.println(task.details()));
        System.out.println("Project progress: " + getProgress() + "%");
    }
}

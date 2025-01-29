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
    private Scanner scanner;    // Scanner object for user interaction

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
     * Adds a new task to the project after validating user input
     */
    public void addTask() {
        String title;

        // Keep asking for a valid title until a unique one is provided
        while (true) {
            title = getValidTitle();
            if (!isTaskTitleDuplicate(title)) {
                break; // Exit loop if the title is unique
            }
            System.out.println("Task with the same title already exists. Please enter a different title");
        }

            String description = getValidDescription();
            int priority = getValidPriority();
            String deadline = getValidDeadline();

            // Create and add task
            Task task = new Task(title, description, priority, deadline);
            tasks.add(task);
            System.out.println("\nTask '" + title + "' successfully added");

        }

    /**
     * Checks if a task title already exists in the task list
     * @param title The title to check
     * @return True if the title exists, false otherwise
     */
    private boolean isTaskTitleDuplicate(String title) {
        return tasks.stream()
                .anyMatch(task -> task.getTitle().equalsIgnoreCase(title));
        // equalsIgnoreCase - means like that hello = HeLLo because case are ignored
    }

    /**
     * Gets a valid task title from user input
     * @return A non-empty task title
     */
    private String getValidTitle() {
        while (true) {
            System.out.print("\nEnter Task Title: ");
            String title = scanner.nextLine().trim();
            // trim() - Removes all leading and trailing whitespace from the input
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
            // trim() - Removes all leading and trailing whitespace from the input
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
                // trim() - Removes all leading and trailing whitespace from the input
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
            // trim() - Removes all leading and trailing whitespace from the input
            // \\d{2}/\\d{2}/\\d{4} → Regular Expression
            // \\d{2} - Represents DD (Day) - 2 digits
            // \\d{2} - Represents MM (Month) - 2 digits
            // \\d{4} - Represents YYYY (Years) - 4 digits  
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
                // equalsIgnoreCase - means like that hello = HeLLo because case are ignored
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
        if (tasks.isEmpty()) return 0;
        // Task::isCompleted = task -> task.isCompleted()
        double completed = tasks.stream().filter(TaskMain::isCompleted).count();
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

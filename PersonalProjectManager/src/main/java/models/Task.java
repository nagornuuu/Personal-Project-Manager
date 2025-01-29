package models;

/**
 * Class Task represents and individual task in the project
 * It extends TaskMain and provides an implementation for displaying task details
 */
public class Task extends TaskMain {
    /**
     * Constructor for a new Task object
     * @param title          the title of the task
     * @param description    the description of the task
     * @param priority       the priority of the task (1-High, 2-Medium, 3-Low)
     * @param deadline       The deadline for the task (DD/MM/YYYY)
     */
    public Task(String title, String description, int priority, String deadline) {
        super(title, description, priority, deadline);
    }

    /**
     * Displays the details of the task
     * Overrides the abstract method from TaskMain
     */
    @Override
    public void showDetails() {
        System.out.println(details()); // Prints formatted task details
    }
}

package models;

/**
 * The TaskMain class is an abstract class that serves as the base for all task-related objects
 * It contains essential properties such as title, description, priority, deadline, and completion status
 * Subclasses must implement the abstract method {@code showDetails()} to display task information
 */
public abstract class TaskMain {
    protected String title;         // Title of the task
    protected String description;   // Description of the task
    protected String deadline;      // Task should have Deadline in format DD/MM/YYYY
    protected boolean isCompleted;  // Indicates if the task is completed
    protected int priority;         // Task priority (1-High, 2-Medium, 3-Low)

    /**
     * Constructs for a new TaskMain object with the specified title, description, priority, and deadline
     * @param title       The title of the task
     * @param description The description of the task
     * @param priority    The priority of the task (1-High, 2-Medium, 3-Low)
     * @param deadline    The deadline for completing the task in DD/MM/YYYY format
     */
    public TaskMain(String title, String description, int priority, String deadline) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.deadline = deadline;
        this.isCompleted = false;
    }

    /**
     * Marks the task as completed
     */
    public void completeTask() {
        this.isCompleted = true;
    }

    /**
     * Abstract method that must be implemented by subclasses to display task details
     */
    public abstract void showDetails();

    /**
     * Checks whether the task is completed
     * @return true if the task is completed, otherwise false
     */
    public boolean isCompleted() {
        return isCompleted;
    }

    /**
     * Retrieves the priority of the task
     * @return The priority level (1-High, 2-Medium, 3-Low)
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Retrieves the title of the task
     * @return The title of the task
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns a formatted string with all task details, including title, priority, deadline, and status
     * @return A string representing the task details
     */
    public String details() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n==============================\n")
                .append(" Task Title    : ").append(title).append("\n")
                .append(" Priority      : ").append(priority == 1 ? "High" : (priority == 2 ? "Medium" : "Low")).append("\n")
                .append(" Deadline      : ").append(deadline).append("\n")
                .append(" Status        : ").append(isCompleted ? "Completed" : "Not completed").append("\n")
                .append("==============================");
        return sb.toString();
    }
}

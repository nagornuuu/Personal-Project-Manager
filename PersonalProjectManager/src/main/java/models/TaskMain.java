package models;

public abstract class TaskMain {
    protected String title;
    protected String description;
    protected String deadline;
    protected boolean isCompleted;
    protected int priority;

    public TaskMain(String title, String description, int priority, String deadline) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.deadline = deadline;
        this.isCompleted = false;
    }

    public void completeTask() {
        this.isCompleted = true;
    }

    public abstract void showDetails();

    public boolean isCompleted() {
        return isCompleted;
    }

    public int getPriority() {
        return priority;
    }

    public String getTitle() {
        return title;
    }
}

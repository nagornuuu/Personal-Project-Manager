package models;

public class Task extends TaskMain {
    public Task(String title, String description, int priority, String deadline) {
        super(title, description, priority, deadline);
    }

    public Task(String title, int priority, String deadline) {
        super(title,"No description", priority, deadline);
    }
    @Override
    public void showDetails() {
        System.out.println("[" + (isCompleted ? "Yes" : "No") + "] " + title +
                        " (Priority: " + priority + ") - Due: " + deadline);
    }
}

package models;

public class Task extends TaskMain {
    public Task(String title, String description, int priority, String deadline) {
        super(title, description, priority, deadline);
    }

    @Override
    public void showDetails() {
        System.out.println(details());
    }
}

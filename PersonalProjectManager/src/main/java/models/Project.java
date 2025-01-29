package models;

import java.util.ArrayList;
import java.util.List;

public class Project {
    private String name;
    private List<Task> tasks;

    public Project(String name) {
        this.name = name;
        this.tasks = new ArrayList<Task>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void completeTask(String title) {
        tasks.stream()
                .filter(task -> task.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .ifPresent(Task::completeTask);
    }

    public double getProgress() {
        if (tasks.isEmpty()) {
            return 0;
        }
        double completed = tasks.stream().filter(Task::isCompleted).count();
        return Math.round((completed * 100) / tasks.size());
    }

    public void displayTasks() {
        tasks.stream()
                .sorted((t1, t2) -> Integer.compare(t1.getPriority(), t2.getPriority()))
                .forEach(Task::showDetails);
        System.out.println("Project Progress: " + getProgress() + "%");
    }
}

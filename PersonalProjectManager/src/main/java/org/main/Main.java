package org.main;

import Services.TaskManager;

public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();
        manager.start();
    }
}
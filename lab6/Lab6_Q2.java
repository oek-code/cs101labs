package lab6;
import java.util.Scanner;
import java.util.ArrayList;

public class Lab6_Q2 {

    static Scanner in = new Scanner(System.in);

    static String[] taskNames;
    static int[] priorities;
    static int taskCount = 0;

    static ArrayList<String> dynNames = new ArrayList<>();
    static ArrayList<Integer> dynPriorities = new ArrayList<>();

    static boolean usingArrayList = false;

    public static void main(String[] args) {

        System.out.print("Enter initial task capacity: ");
        int capacity = in.nextInt();
        in.nextLine();

        taskNames = new String[capacity];
        priorities = new int[capacity];

        boolean running = true;

        while (running) {

            System.out.println("=== Task Scheduler ===");
            System.out.println("1. Add Task");
            System.out.println("2. Remove Task");
            System.out.println("3. Update Task Priority");
            System.out.println("4. Search Task");
            System.out.println("5. View All Tasks");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int option = in.nextInt();
            in.nextLine();

            if (option == 1) addTask();
            if (option == 2) removeTask();
            if (option == 3) updatePriority();
            if (option == 4) searchTask();
            if (option == 5) viewAllTasks();
            if (option == 6) {
                System.out.println("Quit!");
                running = false;
            }
        }
    }

    static void addTask() {

        System.out.print("Enter task name: ");
        String name = in.nextLine();

        System.out.print("Enter priority (1=High, 2=Medium, 3=Low): ");
        int p = in.nextInt();
        in.nextLine();

        boolean validName = !name.isEmpty();
        boolean validPriority = (p >= 1 && p <= 3);

        if (!validName || !validPriority) {
            System.out.println("Invalid input.");
        } else {
            if (!usingArrayList) {

                if (taskCount < taskNames.length) {
                    taskNames[taskCount] = name;
                    priorities[taskCount] = p;
                    taskCount++;
                    System.out.println("Task added successfully.");
                } else {

                    System.out.println("Array full! Switching to dynamic ArrayList...");

                    int i = 0;
                    while (i < taskCount) {
                        dynNames.add(taskNames[i]);
                        dynPriorities.add(priorities[i]);
                        i++;
                    }

                    dynNames.add(name);
                    dynPriorities.add(p);

                    usingArrayList = true;

                    System.out.println("Task added successfully.");
                }

            } else {

                dynNames.add(name);
                dynPriorities.add(p);
                System.out.println("Task added successfully.");
            }
        }
    }

    static void removeTask() {

        System.out.print("Enter task name to remove: ");
        String name = in.nextLine();

        boolean found = false;

        if (!usingArrayList) {

            int i = 0;
            while (i < taskCount) {
                if (!found) {
                    boolean match = taskNames[i].equalsIgnoreCase(name);
                    if (match) {
                        found = true;

                        int j = i;
                        while (j < taskCount - 1) {
                            taskNames[j] = taskNames[j + 1];
                            priorities[j] = priorities[j + 1];
                            j++;
                        }

                        taskCount--;
                        System.out.println("Task removed successfully.");
                    }
                }
                i++;
            }

        } else {

            int i = 0;
            while (i < dynNames.size()) {
                if (!found) {
                    boolean match = dynNames.get(i).equalsIgnoreCase(name);
                    if (match) {
                        found = true;
                        dynNames.remove(i);
                        dynPriorities.remove(i);
                        System.out.println("Task removed successfully.");
                    }
                }
                i++;
            }
        }

        if (!found) System.out.println("Task not found!");
    }

    static void updatePriority() {

        System.out.print("Enter task name to update: ");
        String name = in.nextLine();

        boolean found = false;

        if (!usingArrayList) {

            int i = 0;
            while (i < taskCount) {
                if (!found) {
                    boolean match = taskNames[i].equalsIgnoreCase(name);
                    if (match) {
                        found = true;
                        System.out.println("Current priority: " + priorities[i]);

                        System.out.print("Enter new priority (1=High, 2=Medium, 3=Low): ");
                        int np = in.nextInt();
                        in.nextLine();

                        boolean same = (np == priorities[i]);
                        boolean valid = (np >= 1 && np <= 3);

                        if (same) {
                            System.out.println("New priority cannot be the same as the current priority. Try again.");
                        } else if (valid) {
                            priorities[i] = np;
                            System.out.println("Priority updated.");
                        }
                    }
                }
                i++;
            }

        } else {

            int i = 0;
            while (i < dynNames.size()) {
                if (!found) {
                    boolean match = dynNames.get(i).equalsIgnoreCase(name);
                    if (match) {
                        found = true;
                        System.out.println("Current priority: " + dynPriorities.get(i));

                        System.out.print("Enter new priority (1=High, 2=Medium, 3=Low): ");
                        int np = in.nextInt();
                        in.nextLine();

                        boolean same = (np == dynPriorities.get(i));
                        boolean valid = (np >= 1 && np <= 3);

                        if (same) {
                            System.out.println("New priority cannot be the same as the current priority. Try again.");
                        } else if (valid) {
                            dynPriorities.set(i, np);
                            System.out.println("Priority updated.");
                        }
                    }
                }
                i++;
            }
        }

        if (!found) System.out.println("Task not found!");
    }

    static void searchTask() {

        System.out.print("Enter task name to search: ");
        String name = in.nextLine();

        boolean found = false;

        if (!usingArrayList) {

            int i = 0;
            while (i < taskCount) {
                boolean match = taskNames[i].equalsIgnoreCase(name);
                if (match && !found) {
                    found = true;
                    System.out.println(name + " found. Priority: " + priorities[i]);
                }
                i++;
            }

        } else {

            int i = 0;
            while (i < dynNames.size()) {
                boolean match = dynNames.get(i).equalsIgnoreCase(name);
                if (match && !found) {
                    found = true;
                    System.out.println(name + " found. Priority: " + dynPriorities.get(i));
                }
                i++;
            }
        }

        if (!found) System.out.println("Task not found!");
    }

    static void viewAllTasks() {

        System.out.println("Tasks:");

        if (!usingArrayList) {

            if (taskCount == 0) System.out.println("No tasks to display.");

            int i = 0;
            while (i < taskCount) {
                System.out.println(taskNames[i] + " (Priority " + priorities[i] + ")");
                i++;
            }

        } else {

            if (dynNames.size() == 0) System.out.println("No tasks to display.");

            int i = 0;
            while (i < dynNames.size()) {
                System.out.println(dynNames.get(i) + " (Priority " + dynPriorities.get(i) + ")");
                i++;
            }
        }
    }
}

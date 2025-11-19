static void addTask() {

    String name = "";
    boolean validName = false;

    // ask for name until valid
    while (!validName) {

        System.out.print("Enter task name: ");
        name = in.nextLine();

        // reject empty or whitespace-only names
        if (name.trim().isEmpty()) {
            System.out.println("Invalid task name. Try again.");
        }
        else {
            // check duplicates
            boolean exists = false;

            // array mode
            if (!usingArrayList) {
                int i = 0;
                while (i < taskCount) {
                    if (!exists && taskNames[i].equalsIgnoreCase(name.trim())) {
                        exists = true;
                    }
                    i++;
                }
            }

            // arraylist mode
            else {
                int i = 0;
                while (i < dynNames.size()) {
                    if (!exists && dynNames.get(i).equalsIgnoreCase(name.trim())) {
                        exists = true;
                    }
                    i++;
                }
            }

            if (exists) {
                System.out.println("Task already exists. Try again.");
            }
            else {
                validName = true; // name is acceptable
            }
        }
    }

    // ask for priority
    System.out.print("Enter priority (1=High, 2=Medium, 3=Low): ");
    int p = in.nextInt();
    in.nextLine();

    boolean validP = (p >= 1 && p <= 3);

    if (!validP) {
        System.out.println("Invalid priority.");
    } 
    
    else {

        // still using array
        if (!usingArrayList) {

            if (taskCount < taskNames.length) {     // has space
                taskNames[taskCount] = name.trim();
                priorities[taskCount] = p;
                taskCount++;
                System.out.println("Task added successfully.");
            }

            // must switch to ArrayList
            else {

                System.out.println("Array full! Switching to dynamic ArrayList...");

                // copy old items
                int i = 0;
                while (i < taskCount) {
                    dynNames.add(taskNames[i]);
                    dynPriorities.add(priorities[i]);
                    i++;
                }

                dynNames.add(name.trim());
                dynPriorities.add(p);

                usingArrayList = true;

                System.out.println("Task added successfully.");
            }
        }

        // already using ArrayList
        else {
            dynNames.add(name.trim());
            dynPriorities.add(p);
            System.out.println("Task added successfully.");
        }
    }
}

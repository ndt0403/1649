package ASM2_DSA_NDT;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_Menu {
    private Queue<String> inBoxQueue;
    private Queue<String> outBoxQueue;
    private Stack<String> processingStack;
    private String nameOfSystem;
    private Main_Menu destination;
    private Main_Menu oldDestination;
    private boolean isConnect = false;


    public Main_Menu(String nameOfSystem) {
        this.nameOfSystem = nameOfSystem;
        inBoxQueue = new Queue<>();
        outBoxQueue = new Queue<>();
        processingStack = new Stack<>();
    }

    public String getNameOfSystem() {
        return nameOfSystem;
    }

    public Main_Menu getDestination() {
        return destination;
    }

    public void setDestination(Main_Menu destination) {
        this.destination = destination;
    }

    public void setOldDestination(Main_Menu oldDestination) {
        this.oldDestination = oldDestination;
    }

    public void main_Menu(Main_Menu system1, Main_Menu system2) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean exit = false;

        while (!exit) {
            try {
                printMenu();
                String input = reader.readLine().trim();

                if (input.isEmpty()) {
                    System.out.println("Invalid input. Please enter a valid choice.");
                    continue;
                }

                int option = Integer.parseInt(input);

                if (option == 8) {
                    exit = true;
                } else {
                    handleOption(option, system1, system2);
                }
            } catch (IOException | NumberFormatException e) {
                handleUnexpectedException(e);
            }
        }
    }

    private void printMenu() {
        System.out.println("____________________________________");
        System.out.println("|__________ Menu of " + this.getNameOfSystem()+ " __________|");
        System.out.println("|      1. Connect with new PC      |");
        System.out.println("|       2. Check connection        |");
        System.out.println("|          3. Disconnect           |");
        System.out.println("|         4. Send message          |");
        System.out.println("|        5. Receive message        |");
        System.out.println("|        6. Process message        |");
        System.out.println("|      7. Check inbox of "+ this.getNameOfSystem()+"      |");
        System.out.println("|             8. Back              |");
        System.out.println("|__________________________________|");
        System.out.print("Enter your option: ");
    }

    private void handleOption(int option, Main_Menu system1, Main_Menu system2) {
        boolean isValidOption = false;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (!isValidOption) {
            switch (option) {
                case 1:
                    connectMenu(system1, system2);
                    isValidOption = true;
                    break;
                case 2:
                    checkConnect();
                    isValidOption = true;
                    break;
                case 3:
                    disconnect();
                    isValidOption = true;
                    break;
                case 4:
                    sendMessage();
                    isValidOption = true;
                    break;
                case 5:
                    receiveMessage();
                    isValidOption = true;
                    break;
                case 6:
                    processMessage();
                    isValidOption = true;
                    break;
                case 7:
                    checkInbox();
                    isValidOption = true;
                    break;
                case 8:
                    System.out.println("Exiting to the previous menu...");
                    isValidOption = true;
                    break;
                default:
                    System.out.println("Invalid input. Please enter a valid choice.");
                    break;
            }
        }
    }

    private void handleUnexpectedException(Exception e) {
        System.out.println("An unexpected error occurred: " + e.getMessage());
    }

    private void connectMenu(Main_Menu system1, Main_Menu system2) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        boolean invalidInput = true;

        while (invalidInput) {
            try {
                System.out.println("____________________________________");
                System.out.println("|______ Connect Menu of " + getNameOfSystem() + " ______|");
                System.out.println("|       1. Connect with " + system1.getNameOfSystem() + "       |");
                System.out.println("|       2. Connect with " + system2.getNameOfSystem() + "       |");
                System.out.println("|             3. Back              |");
                System.out.println("|__________________________________|");
                System.out.print("Enter your option: ");

                int choice;

                // Read the user's choice from the console
                try {
                    choice = Integer.parseInt(reader.readLine());
                } catch (NumberFormatException ex) {
                    System.out.println("Invalid input. Please enter a valid integer choice.");
                    continue;  // Continue to the next iteration to get a valid input
                }

                switch (choice) {
                    case 1:
                        connect(system1);
                        invalidInput = false;  // Exit the loop if the input is valid
                        break;
                    case 2:
                        connect(system2);
                        invalidInput = false;  // Exit the loop if the input is valid
                        break;
                    case 3:
                        System.out.println("Going back to the main menu...");
                        invalidInput = false;  // Exit the loop if the input is valid
                        break;
                    default:
                        System.out.println("Invalid input. Please enter a valid choice.");
                        break;
                }
            } catch (IOException e) {
                handleUnexpectedException(e);
            }
        }
    }

    private void connect(Main_Menu system) {
        long startTime = System.nanoTime();
        try {
            if (!isConnect && !system.isConnect) {
                initializeConnection(system);
            } else if (destination == system) {
                System.out.println(getNameOfSystem() + " is connecting to " + destination.getNameOfSystem());
            } else if (isConnect) {
                System.out.println(getNameOfSystem() + " is connecting to " + destination.getNameOfSystem() + ", it cannot connect to " + system.getNameOfSystem());
            } else {
                System.out.println(system.getNameOfSystem() + " is connecting to " + system.getDestination().getNameOfSystem() + ", " + getNameOfSystem() + " cannot interfere in this process ");
            }
        } catch (Exception e) {
            handleUnexpectedException(e);
        }
        long endTime = System.nanoTime();
        long elapsed = endTime - startTime;
        System.out.println("Time spent: " + elapsed + " nanoseconds");
    }

    private void initializeConnection(Main_Menu system) {
        outBoxQueue = new Queue<>();
        setDestination(system);
        setOldDestination(system);
        isConnect = true;
        system.setDestination(this);
        system.setOldDestination(this);
        system.isConnect = true;
        System.out.println(getNameOfSystem() + " has successfully connected to " + system.getNameOfSystem());
    }

    private void checkConnect() {
        long startTime = System.nanoTime();
        try {
            if (isConnect) {
                System.out.println("Connecting to " + destination.getNameOfSystem());
            } else {
                System.out.println(getNameOfSystem() + " is not currently connected to other systems");
            }
        } catch (Exception e) {
            handleUnexpectedException(e);
        }
        long endTime = System.nanoTime();
        long elapsed = endTime - startTime;
        System.out.println("Time spent: " + elapsed + " nanoseconds");
    }

    private void sendMessage() {
        long startTime = System.nanoTime();
        try {
            if (isConnect) {
                processSending();
            } else {
                System.out.println(getNameOfSystem() + " is not currently connected to other systems for execution, " + "please connect to another system");
            }
        } catch (Exception e) {
            handleUnexpectedException(e);
        }
        long endTime = System.nanoTime();
        long elapsed = endTime - startTime;
        System.out.println("Time spent: " + elapsed + " nanoseconds");
    }

    private void processSending() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.print("Number of messages you want to import: ");
            int numberOfMessage = Integer.parseInt(reader.readLine().trim());
            if (numberOfMessage <= 0) {
                throw new IllegalArgumentException("Do not enter negative numbers, only positive numbers");
            } else {
                for (int i = 1; i <= numberOfMessage; i++) {
                    System.out.printf("Message %d: ", i);
                    String message = reader.readLine().trim();
                    validateMessage(message);
                    enqueueMessage(message);
                }
            }
        } catch (IOException e) {
            handleUnexpectedException(e);
        }
    }

    private void validateMessage(String message) {
        if (message.isEmpty()) {
            throw new IllegalArgumentException("Message is empty and cannot execute the program");
        }
    }

    private void enqueueMessage(String message) {
        if (message.length() <= 250) {
            System.out.println("Sending message: " + message);
            outBoxQueue.enqueue(message);
        } else {
            truncateAndSend(message);
        }
    }

    private void truncateAndSend(String message) {
        System.out.println("Messages over 250 characters, start truncating the message");
        String tempMessage = "";
        for (int i = 0; i < message.length(); i++) {
            tempMessage = tempMessage + message.charAt(i);
            if (tempMessage.length() == 250 || i == message.length() - 1) {
                System.out.println("Sending message: " + tempMessage);
                outBoxQueue.enqueue(tempMessage);
                tempMessage = "";
            }
        }
    }

    private void receiveMessage() {
        long startTime = System.nanoTime();
        try {
            if (isConnect) {
                processReceiving();
            } else {
                System.out.println(getNameOfSystem() + " is not currently connected to other systems for execution, " + "please connect to another system");
            }
        } catch (Exception e) {
            handleUnexpectedException(e);
        }
        long endTime = System.nanoTime();
        long elapsed = endTime - startTime;
        System.out.println("Time spent: " + elapsed + " nanoseconds");
    }

    private void processReceiving() {
        if (!destination.outBoxQueue.isEmpty()) {
            while (!destination.outBoxQueue.isEmpty()) {
                inBoxQueue.enqueue(destination.outBoxQueue.dequeue());
            }
            System.out.println("Complete the process of receiving messages from " + destination.getNameOfSystem());
        } else {
            System.out.println("outQueue in " + destination.getNameOfSystem() + " is empty, process cannot be executed");
        }
    }

    private void processMessage() {
        long startTime = System.nanoTime();
        try {
            if (!inBoxQueue.isEmpty()) {
                processMessages();
            } else {
                System.out.println("inboxQueue is empty, process cannot be executed");
            }
        } catch (Exception e) {
            handleUnexpectedException(e);
        }
        long endTime = System.nanoTime();
        long elapsed = endTime - startTime;
        System.out.println("Time spent: " + elapsed + " nanoseconds");
    }

    private void processMessages() {
        while (!inBoxQueue.isEmpty()) {
            System.out.println("Processing message: " + inBoxQueue.peek());
            processingStack.push(inBoxQueue.dequeue());
        }
        System.out.println("Complete the process of processing messages of " + getNameOfSystem());
    }

    private void disconnect() {
        long startTime = System.nanoTime();
        try {
            if (isConnect) {
                destroyConnection();
            } else {
                System.out.println(getNameOfSystem() + " has disconnected");
            }
        } catch (Exception e) {
            handleUnexpectedException(e);
        }
        long endTime = System.nanoTime();
        long elapsed = endTime - startTime;
        System.out.println("Time spent: " + elapsed + " nanoseconds");
    }

    private void destroyConnection() {
        System.out.println(getNameOfSystem() + " disconnected to " + destination.getNameOfSystem());
        outBoxQueue = null;
        destination.isConnect = false;
        destination.setDestination(null);
        isConnect = false;
        destination = null;
    }

    private void checkInbox() {
        long startTime = System.nanoTime();
        int count = 0;
        try {
            if (!processingStack.isEmpty()) {
                displayMessages(count);
            } else if (processingStack.isEmpty()) {
                System.out.println("The message has not been processed");
            }
        } catch (Exception e) {
            handleUnexpectedException(e);
        }
        long endTime = System.nanoTime();
        long elapsed = endTime - startTime;
        System.out.println("Time spent: " + elapsed + " nanoseconds");
    }

    private void displayMessages(int count) {
        System.out.println(oldDestination.getNameOfSystem() + " just sent " + getNameOfSystem() + " a message: ");
        while (!processingStack.isEmpty()) {
            count++;
            System.out.printf("Part of message %d: ", count);
            System.out.println(processingStack.pop());
        }
    }
}


import java.util.Scanner;

class Player {
    int id;
    String name;
    Player next;

    Player(int id, String name) {
        this.id = id;
        this.name = name;
        this.next = null;
    }
}

public class CircularPlayerGame {

    static Player head = null;
    static Player current = null;
    static Scanner sc = new Scanner(System.in);

    // Insert a new player
    static void insertPlayer() {
        System.out.print("Enter Player ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Player Name: ");
        String name = sc.nextLine();

        Player newPlayer = new Player(id, name);

        if (head == null) {
            head = newPlayer;
            newPlayer.next = head;
            current = head;
        } else {
            Player temp = head;

            while (temp.next != head) {
                temp = temp.next;
            }

            temp.next = newPlayer;
            newPlayer.next = head;
        }

        System.out.println("Player inserted successfully!");
    }

    // Delete a player by ID
    static void deletePlayer() {
        if (head == null) {
            System.out.println("No players available.");
            return;
        }

        System.out.print("Enter Player ID to delete: ");
        int id = sc.nextInt();

        Player temp = head;
        Player prev = null;

        do {
            if (temp.id == id) {
                if (temp.next == temp) {
                    head = null;
                    current = null;
                } else {
                    if (temp == head) {
                        Player last = head;

                        while (last.next != head) {
                            last = last.next;
                        }

                        head = head.next;
                        last.next = head;
                    } else {
                        prev.next = temp.next;
                    }

                    if (current == temp) {
                        current = temp.next;
                    }
                }

                System.out.println("Player deleted successfully!");
                return;
            }

            prev = temp;
            temp = temp.next;

        } while (temp != head);

        System.out.println("Player ID not found.");
    }

    // Display all players
    static void displayPlayers() {
        if (head == null) {
            System.out.println("No players available.");
            return;
        }

        Player temp = head;

        System.out.println("\n--- Players in the Circle ---");

        do {
            System.out.println("Player ID: " + temp.id
                    + " | Name: " + temp.name);
            temp = temp.next;
        } while (temp != head);

        System.out.println("Last player connects back to first player.");
    }

    // Display current turn and move to next player
    static void nextTurn() {
        if (current == null) {
            System.out.println("No players available. Add players first.");
            return;
        }

        System.out.println("Current Turn: Player "
                + current.id + " - " + current.name);

        current = current.next;
    }

    public static void main(String[] args) {
        int choice = 0;

        do {
            System.out.println("\n===== CIRCULAR PLAYER GAME =====");
            System.out.println("1. Insert Player");
            System.out.println("2. Delete Player");
            System.out.println("3. Display Players");
            System.out.println("4. Next Turn");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            if (!sc.hasNextInt()) {
                System.out.println("Invalid input! Enter a number.");
                sc.nextLine();
                continue;
            }

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    insertPlayer();
                    break;

                case 2:
                    deletePlayer();
                    break;

                case 3:
                    displayPlayers();
                    break;

                case 4:
                    nextTurn();
                    break;

                case 5:
                    System.out.println("Game ended.");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 5);

        sc.close();
    }
}

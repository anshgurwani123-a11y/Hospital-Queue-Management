
import java.util.Scanner;

class Patient {
    int id;
    String name;
    Patient next;

    Patient(int id, String name) {
        this.id = id;
        this.name = name;
        this.next = null;
    }
}

class PatientQueue {
    Patient front, rear;

    PatientQueue() {
        front = rear = null;
    }

    void enqueue(int id, String name) {
        Patient newPatient = new Patient(id, name);

        if (rear == null) {
            front = rear = newPatient;
        } else {
            rear.next = newPatient;
            rear = newPatient;
        }

        System.out.println("Patient added successfully!");
    }

    void dequeue() {
        if (front == null) {
            System.out.println("No patients in the queue.");
            return;
        }

        System.out.println("Patient served: "
                + front.name + " (ID: " + front.id + ")");

        front = front.next;

        if (front == null) {
            rear = null;
        }
    }

    void peek() {
        if (front == null) {
            System.out.println("No patients in the queue.");
        } else {
            System.out.println("Next patient: "
                    + front.name + " (ID: " + front.id + ")");
        }
    }

    void display() {
        if (front == null) {
            System.out.println("No patients in the queue.");
            return;
        }

        Patient temp = front;
        System.out.println("\nWaiting Patients:");

        while (temp != null) {
            System.out.println("ID: " + temp.id
                    + " | Name: " + temp.name);
            temp = temp.next;
        }
    }
}

public class HospitalPatientQueue {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PatientQueue q = new PatientQueue();
        int choice;

        do {
            System.out.println("\n--- Hospital Patient Queue ---");
            System.out.println("1. Add Patient (Enqueue)");
            System.out.println("2. Serve Patient (Dequeue)");
            System.out.println("3. View Next Patient (Peek)");
            System.out.println("4. Display Patients");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Patient ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Patient Name: ");
                    String name = sc.nextLine();

                    q.enqueue(id, name);
                    break;

                case 2:
                    q.dequeue();
                    break;

                case 3:
                    q.peek();
                    break;

                case 4:
                    q.display();
                    break;

                case 5:
                    System.out.println("Exiting program...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 5);

        sc.close();
    }
}

import java.util.*;

public class OnlineExaminationSystem {
    static Scanner sc = new Scanner(System.in);
    static String currentUser = "user";
    static String currentPassword = "pass";
    static boolean isLoggedIn = false;

    static Map<Integer, String> mcqAnswers = new HashMap<>();
    static int score = 0;

    public static void main(String[] args) {
        while (true) {
            if (!isLoggedIn) {
                login();
            } else {
                showMenu();
            }
        }
    }

    static void login() {
        System.out.println("\n=== ONLINE EXAMINATION SYSTEM ===");
        System.out.print("Enter Username: ");
        String user = sc.nextLine();
        System.out.print("Enter Password: ");
        String pass = sc.nextLine();

        if (user.equals(currentUser) && pass.equals(currentPassword)) {
            System.out.println(" Login Successful!\n");
            isLoggedIn = true;
        } else {
            System.out.println(" Invalid Credentials. Try again.\n");
        }
    }

    static void showMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. Update Profile & Password");
        System.out.println("2. Take Exam");
        System.out.println("3. Logout");
        System.out.print("Choose option: ");
        int choice = sc.nextInt();
        sc.nextLine();  // clear newline

        switch (choice) {
            case 1:
                updateProfile();
                break;
            case 2:
                startExam();
                break;
            case 3:
                logout();
                break;
            default:
                System.out.println(" Invalid choice.");
        }
    }

    static void updateProfile() {
        System.out.print("Enter new username: ");
        currentUser = sc.nextLine();
        System.out.print("Enter new password: ");
        currentPassword = sc.nextLine();
        System.out.println(" Profile updated successfully.");
    }

    static void startExam() {
        System.out.println("\n=== EXAM STARTED ===");
        System.out.println(" You have 60 seconds to complete 5 MCQs");

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                System.out.println("\n Time's up! Auto-submitting your exam...");
                submitExam();
                logout();
                System.exit(0);
            }
        };

        timer.schedule(task, 60000);  // 60 seconds

        askQuestion(1, "What is the capital of India?", "A) Delhi", "B) Mumbai", "C) Chennai", "D) Kolkata", "A");
        askQuestion(2, "2 + 2 = ?", "A) 3", "B) 4", "C) 5", "D) 6", "B");
        askQuestion(3, "Which planet is known as the Red Planet?", "A) Earth", "B) Venus", "C) Mars", "D) Jupiter", "C");
        askQuestion(4, "Who wrote 'Hamlet'?", "A) Wordsworth", "B) Dickens", "C) Milton", "D) Shakespeare", "D");
        askQuestion(5, "Which is the largest ocean?", "A) Atlantic", "B) Pacific", "C) Indian", "D) Arctic", "B");

        timer.cancel();  // cancel if finished early
        submitExam();
    }

        static void askQuestion(int qNo, String q, String opt1, String opt2, String opt3, String opt4, String correctAnswer) {
        System.out.println("\nQuestion " + qNo + ": " + q);
        System.out.println(opt1);
        System.out.println(opt2);
        System.out.println(opt3);
        System.out.println(opt4);
        System.out.print("Your answer (A/B/C/D): ");
        String ans = sc.nextLine().trim().toUpperCase();

        // Validate answer
        while (!ans.matches("[ABCD]")) {
            System.out.print("Invalid choice. Please enter A, B, C, or D: ");
            ans = sc.nextLine().trim().toUpperCase();
        }

        mcqAnswers.put(qNo, ans);

        if (ans.equals(correctAnswer)) {
            score++;
        }
    }

    static void submitExam() {
        System.out.println("\n=== EXAM SUBMITTED ===");
        System.out.println("Your Answers:");
        for (Map.Entry<Integer, String> entry : mcqAnswers.entrySet()) {
            System.out.println("Q" + entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("Final Score: " + score + " out of 5");
    }

    static void logout() {
        System.out.println("\nLogging out...");
        isLoggedIn = false;
        mcqAnswers.clear();
        score = 0;
    }
}



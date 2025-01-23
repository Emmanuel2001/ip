import java.util.Scanner;

public class BuddyTalk {
    public static void main(String[] args) {
        String start = """
                ____________________________________________________________
                Hello! I'm BuddyTalk
                What can I do for you?
                ____________________________________________________________
                """;

        String bye = """
                ____________________________________________________________
                Bye. Hope to see you again soon!
                ____________________________________________________________
                """;
        System.out.println(start);

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                exit = true;
                System.out.println(bye);
            } else {
                String text = String.format("""
                        ____________________________________________________________
                        %s
                        ____________________________________________________________
                        """, input);
                System.out.println(text);
            }
        }
    }
}

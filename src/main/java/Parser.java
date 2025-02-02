
public class Parser {
    public static Command parse(String input) throws BuddyException {
        if (input.isEmpty()) {
            throw new BuddyException("Empty inputs are not accepted. Please try again.");
        }

        String[] parts = input.split(" ", 2);

        String command = parts[0];

        if (parts.length == 1) {
            return new Command(command, "");
        }

        return new Command(command, parts[1].trim());
    }
}


package braceChecker;

public class BraceCheckerTest {
    public static void main(String[] args) {
        String text = "{Hell(o from ([{Java})";
BraceChecker braceChecker = new BraceChecker(text);
braceChecker.check();

    }
}

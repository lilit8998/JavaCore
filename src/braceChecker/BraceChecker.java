package braceChecker;

public class BraceChecker {
    String text = "";

    BraceChecker(String newText) {
        this.text = newText;
    }

    public void check() {
        Stack stack = new Stack();
        char symbols;
        for (int i = 0; i < text.length(); i++){
            char arrayChar = text.charAt(i);

            switch (arrayChar){
                case '(':
                case '{':
                case '[':
                    stack.push(arrayChar);
                    break;
                case ')':
                    symbols = (char) stack.pop();
                    if (symbols == '{' || symbols == '['){
                        System.out.println("Error: open " + symbols + " but closed " + arrayChar  + " at " + i);
                    }
                    break;
                case '}':
                    symbols = (char) stack.pop();
                    if (symbols == '(' || symbols == '['){
                        System.out.println("Error: open " + symbols + " but closed " + arrayChar + " at " + i);
                        break;
                    }
                case ']':
                    symbols = (char) stack.pop();
                    if (symbols == '(' || symbols == '{'){
                        System.out.println("Error: open " + symbols + " but closed " + arrayChar + " at " + i);
                    }
             }
        }
    }
}

package braceChecker;

public class BraceChecker {
    String text = "";

    BraceChecker(String newText) {
        this.text = newText;
    }

    public void check() {
        Stack stack = new Stack();
        char symbols;
        int openBraceCount = 0;
        int closedBraceCount = 0;
        for (int i = 0; i < text.length(); i++){
            char arrayChar = text.charAt(i);
                switch (arrayChar){
                    case '(':
                    case '{':
                    case '[':
                        stack.push(arrayChar);
                        openBraceCount++;
                        break;
                    case ')':
                        symbols = (char) stack.pop();
                        if (symbols == 0){
                            System.out.println("Error: " + symbols + " closed, but not opened at " + i);
                        }
                        if (symbols == '{' || symbols == '['){
                            System.out.println("Error: open " + symbols + " but closed " + arrayChar  + " at " + i);
                        }
                        closedBraceCount++;
                        break;
                    case '}':
                        symbols = (char) stack.pop();
                        if (symbols == 0){
                            System.out.println("Error: " + symbols + " closed, but not opened at " + i);
                        }
                        if (symbols == '(' || symbols == '['){
                            System.out.println("Error: open " + symbols + " but closed " + arrayChar + " at " + i);
                        }
                        closedBraceCount++;
                        break;
                    case ']':
                        symbols = (char) stack.pop();
                        if (symbols == 0){
                            System.out.println("Error: " + symbols + " closed, but not opened at " + i);
                        }
                        if (symbols == '(' || symbols == '{'){
                            System.out.println("Error: open " + symbols + " but closed " + arrayChar + " at " + i);
                        }
                        closedBraceCount++;
                        break;
                }

            }
                while (stack.getIndex() != -1){
                    System.out.println("Error open" + (char) stack.pop() + " but not closed ");
                }


        }

}

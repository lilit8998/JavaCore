package braceChecker;

public class Stack {
    private int[] array = new int[10];
    private int size;

    public Stack(){
        size = -1;
    }

    public void push(int value){
        if (size == array.length){
            extend();
        }else {
            array[++size] = value;
        }
    }

    public int pop() {
        if (size < 0){
            System.out.println("Stack is empty");
            return 0;
        }else {
            return array[size--];
        }
    }

    private void extend() {
        if (size == array.length){
            int[] tmp = new int[array.length + 10];
            for (int i =0; i < size; i++){
                tmp[i] = array[i];
            }
            array = tmp;

        }
    }
}

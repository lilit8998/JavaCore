package homework5;

public class Sort {
    public static void main(String[] args) {
        int[] array = {4,7,1,3,9,0,2};

        for (int i = 0; i < array.length; i++){
            int minValue = array[i];
            int minIndex = i;
            for (int j = i; j < array.length; j++){
                if (array[j] < minValue){
                  minValue = array[j];
                  minIndex = j;
                }
            }
            int temp = array[i];
            array[i] = minValue;
            array[minIndex] = temp;
            System.out.println(array[i]);
        }

    }
}

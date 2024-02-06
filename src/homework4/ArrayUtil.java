package homework4;

public class ArrayUtil {
    public static void main(String[] args) {
        int[] numbers = {1, 6, 3, 9, 15, 52, -3, 5, 8,55};

        //Տպել բոլոր էլեմենտները իրար կողք, բաժանված պռաբելով։
        for (int i = 0; i < numbers.length; i++){
            System.out.println(numbers[i] + " ");
        }

        //Տպել մասիվի առաջին էլեմենտը
        System.out.println(numbers[0]);

        //Տպել մասիվի վերջին էլեմենտը
        System.out.println(numbers.length - 1);

        //Տպել մասիվի երկարությունը
        System.out.println(numbers.length);

        //Տպել մասիվի ամենափոքր թիվը
        int min = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (min > numbers[i]) {
                min = numbers[i];
            }
        }
        System.out.println(min);

        //Տպել մասիվի մեջտեղի թիվը, ստուգել որ 2-ից մեծ լինի երկարությունը,
        // եթե փոքր կամ հավասար էղավ, տպեք որ can't print middle values. եթե զույգ
        // է մասիվի մեջի էլեմենտների թիվը, տպեք մեջտեղի երկու էլեմենտները, եթե կենտ է մի հատը։

          if (numbers.length % 2 == 1 ){
              int index = numbers.length / 2;
              int value = numbers[index];
              if (value > 2){
                  System.out.println("Middle value: " + value);
              }else {
                  System.out.println("Can't print middle values.");

              }
              }  else {
              int indexes = numbers.length / 2 - 1;
              int secondIndex = indexes++;
              int num1 = numbers[indexes];
              int num2 = numbers[secondIndex];
              System.out.println("The length of the array is even middle values are: ");
              int[] arrayEven = new int[]{num1,num2};
              for (int i =0; i < arrayEven.length; i++){
                  System.out.print(arrayEven[i] + " ");
              }

          }


        System.out.println();
        //Հաշվել ու տպել մասիվում զույգերի քանակը։
        int even = 0;
        for (int i = 0; i < numbers.length; i++){
            if (numbers[i] % 2 == 0){
                even++;
            }
        }
        System.out.println("Even: " + even);

        //Հաշվել ու տպել մասիվում կենտերի քանակը։
        int odd = 0;
        for (int i =0; i< numbers.length; i++){
            if (numbers[i] % 2 != 0){
                odd++;
            }
        }
        System.out.println("odd: " + odd);

        //Տպել մասիվում էլեմենտների գումարը։
        int sum = 0;
        for (int i =0; i < numbers.length; i++){
            sum += numbers[i];
        }
        System.out.println("Sum: " + sum);

        //Տպել մասիվում թվերի միջին թվաբանականը։
        int sumArr = 0;
        int count = 0;
        for (int i = 0; i < numbers.length; i++){
            sumArr += numbers[i];
            count++;
        }
        System.out.println("Average: " +sumArr / count);
    }
    }


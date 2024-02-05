package homework3;

public class ForExamples {
    public static void main(String[] args) {
        //Տպել 1-ից 1000 թվերը իրար կողք, արանքում դնելով - սինվոլը։ Այնպես գրեք, որ վերջում - չլինի

        for (int i = 1; i < 1000; i++){

            if (i  == 999){
                System.out.println(i + " ");
                break;
            }
            System.out.print(i + "-");
        }

        //Տպել 1-ից 100 թվերի մեջ ընկած զույգերը օգտագործելով % գործողությունը
        for (int i = 2; i < 100; i++){
            if (i % 2 == 0){
                System.out.println(i);
            }
        }

        //Հայտարարել թվերի մասիվ՝ int[] array = {2,5,8,4,9,3,7}, ֆոր- օգտագործելով գտնել մասիվի ամենամեծ թիվը ու տպել։
        int[] array = {2,5,8,4,9,3,7};
        int max = 0;
        for (int i = 0; i < array.length; i++){
            if (array[i] > max){
                max = array[i];
            }
        }
        System.out.println("Max: " + max);
    }
}

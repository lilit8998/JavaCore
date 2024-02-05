package homework2;

public class Triangles {
    public static void main(String[] args) {
        for (int i = 5; i > 1; i--){
            for (int j = i; j <= 5; j++){
                System.out.print(" *");
            }
            System.out.println(" ");
        }

        System.out.println();
        System.out.println();

        //2
        for (int i = 1; i < 5; i++){
            for (int j = i; j < 5; j++){
                System.out.print(" *");
            }
            System.out.println(" ");
        }

        System.out.println();
        System.out.println();

        //3
        for (int i = 0; i < 5; i++){
            for (int j = 5; j > i; j--){
                System.out.print(" ");
            }
            for (int j = 0; j <= i; j++){
                System.out.print("*");
            }
            System.out.println();
        }

        System.out.println();
        System.out.println();

        //4
        for (int i = 5; i > 0; i--){
            for (int j = i; j < 5; j++){
                System.out.print(" ");
            }
            for (int j = 0; j < i; j++){
                System.out.print("*");
            }
            System.out.println();
        }

        //5
        for (int i = 1; i <5; i++){
            for (int j = i; j < 5; j++){
                System.out.print(" ");
            }
            for (int k = 1; k < i; k++){
                System.out.print(" *");
            }
            System.out.println(" ");

        }

        for (int i = 5; i > 1; i--){

            for (int j = i; j < 5; j++){
                System.out.print(" ");
            }
            for (int k = 1; k < i; k++){
                System.out.print(" *");
            }
            System.out.println(" ");

        }

    }

}

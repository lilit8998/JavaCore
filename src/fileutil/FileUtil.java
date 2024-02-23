package fileutil;

import java.io.*;
import java.util.Scanner;


public class FileUtil {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        System.out.println("Please input path and file name!!");
        String path = scanner.nextLine();
           String fileName = scanner.nextLine();
//           String content = scanner.nextLine();
//        System.out.println(FileUtil.fileSearch(path, fileName));
//        File file = new File(path);
//        File[] files = file.listFiles();
//        for (File file1 : files) {
//            System.out.println(file1);
//        }
        FileUtil.contentSearch(path, fileName);
//        FileUtil.printSizeOfPackage(path);
//        FileUtil.createFileWithContent(path,fileName,content);
    }

//    այս մեթոդը պետք է սքաններով վերցնի երկու string.
//     1 - path թե որ ֆոլդերում ենք փնտրելու
//     2 - fileName - ֆայլի անունը, որը փնտրում ենք։
//    Որպես արդյունք պտի ծրագիրը տպի true եթե կա էդ ֆայլը էդ պապկի մեջ, false եթե չկա։

    public static boolean fileSearch(String path, String fileName) {
        File directory = new File(path, fileName);
        return directory.exists();

    }

    //այս մեթոդը պետք է սքաններով վերցնի երկու string.
    // 1 - path թե որ ֆոլդերում ենք փնտրելու
    // 2 - keyword - ինչ որ բառ
    // Մեթոդը պետք է նշված path-ում գտնի բոլոր .txt ֆայլերը, և իրենց մեջ փնտրի
    // մեր տված keyword-ը, եթե գտնի, պետք է տպի տվյալ ֆայլի անունը։
    static void contentSearch(String path, String keyword) {
        File directory = new File(path);
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".txt")) {
                    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                        String line = null;
                        while ((line = bufferedReader.readLine()) != null) {
                            if (line.contains(keyword)) {
                                System.out.println(file.getName());
                                break;
                            }
                        }
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                } else if (file.isDirectory()) {
                    contentSearch(file.getAbsolutePath(), keyword);
                }
            }
        }

    }

    //այս մեթոդը պետք է սքաններով վերցնի երկու string.
    // 1 - txtPath txt ֆայլի փաթը
    // 2 - keyword - ինչ որ բառ
    // տալու ենք txt ֆայլի տեղը, ու ինչ որ բառ, ինքը տպելու է էն տողերը, որտեղ գտնի էդ բառը։
    static void findLines(String txtPath, String keyword) {
        File directory = new File(txtPath);
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".txt")) {
                    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                        String line = null;
                        int lineNumber = 1;
                        while ((line = bufferedReader.readLine()) != null) {
                            if (line.contains(keyword)) {
                                System.out.println(file.getName() + " " + " line number: " + lineNumber + " " + " line: " + line);
                                break;
                            }
                        }
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                } else if (file.isDirectory()) {
                    contentSearch(file.getAbsolutePath(), keyword);
                }
            }
        }
    }

    //այս մեթոդը պետք է սքաններով վերցնի մեկ string.
    // 1 - path թե որ ֆոլդերի չափն ենք ուզում հաշվել
    // ֆոլդերի բոլոր ֆայլերի չափսերը գումարում ենք իրար, ու տպում
    static void printSizeOfPackage(String path) {
        File file = new File(path);
        long fileSize = 0;
        if (file.isDirectory() && file.exists()) {
            File[] list = file.listFiles();
            for (File file1 : list) {
               fileSize += file1.length();
            }
            System.out.println("Total size " + fileSize);
        }
    }

    //այս մեթոդը պետք է սքաններով վերցնի երեք string.
    // 1 - path պապկի տեղը, թե որտեղ է սարքելու նոր ֆայլը
    // 2 - fileName ֆայլի անունը, թե ինչ անունով ֆայլ է սարքելու
    // 3 - content ֆայլի պարունակությունը։ Այսինքն ստեղծված ֆայլի մեջ ինչ է գրելու
    // որպես արդյունք պապկի մեջ սարքելու է նոր ֆայլ, իրա մեջ էլ լինելու է content-ով տվածը
    static void createFileWithContent(String path, String fileName, String content) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(content);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(file.getAbsoluteFile());
    }


}


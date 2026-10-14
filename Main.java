import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        Matrix result = null;

        while (!exit) {
            System.out.println("\n Меню ");
            System.out.println("1. Сложение");
            System.out.println("2. Вычитание");
            System.out.println("3. Умножение");
            System.out.println("4. Деление");
            System.out.println("5. Определитель");
            System.out.println("6. Обратная");
            System.out.println("7. Транспонирование");
            System.out.println("8. Вывести сохраненный результат");
            System.out.println("0. Выход");
            System.out.print("Ваш выбор: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                case 2:
                case 3:
                case 4: {
                    try {
                        System.out.println("\n Меню ");
                        System.out.println("Выберете, воспользуемся старым резултатом или введем две новые матрицы");
                        System.out.println("1. Старый дейстиве новый");
                        System.out.println("2. новый дейстиве старый ");
                        System.out.println("3. обе матрицы новые ");
                        int typee = sc.nextInt();
                        sc.nextLine();
                        
                        Matrix A = null ;
                        Matrix B = null ;
                        if ( typee == 1 ){
                            if ( result == null ){
                                throw new IllegalArgumentException("нет запомненного результат ");
                            }
                            A = result ; 
                            B = readMatrix(sc, "B");
                        }
                        else if (  typee == 2 ) {
                            if ( result == null ){
                                throw new IllegalArgumentException("нет запомненного результат ");
                            }
                            A = readMatrix(sc, "A");
                            B = result ; 
                        }
                        else if ( typee == 3 ){
                            A = readMatrix(sc, "A");
                            B = readMatrix(sc, "B");
                        }
                        else {
                            throw new IllegalArgumentException("неверный выбор");
                        } 

                        
                        if (choice == 1) result = A.plus(B);
                        if (choice == 2) result = A.minus(B);
                        if (choice == 3) result = A.multiply(B);
                        if (choice == 4) result = A.divide(B);
                        System.out.println("результат:");
                        System.out.println(result);
                    } 
                    catch (IllegalArgumentException | ArithmeticException e) {
                        System.out.println("ошибка: " + e.getMessage());
                    }
                    break;
                }

                case 5:
                case 6:
                case 7: {                    
                    try {
                        Matrix A = null ;

                        System.out.println("\n Меню ");
                        System.out.println("Выберете, воспользуемся старым резултатом или введем новую матрицу");
                        System.out.println("1. Старая");
                        System.out.println("2. Новая ");
                        int typee = sc.nextInt();
                        sc.nextLine();
                        
                        if ( typee == 1 ){
                            if ( result == null ){
                                throw new IllegalArgumentException("нет запомненного результат ");
                            }
                            A = result ;   
                        }
                        else if ( typee == 2 ) {
                            A = readMatrix(sc, "A");
                        }
                        else {
                            throw new IllegalArgumentException("неверный выбор");
                        }   
                        
                        
                        if (choice == 5) {
                            System.out.println("det(A) = " + A.determinant());
                        }
                        if (choice == 6) {
                            result = A.inverse();
                            System.out.println("обратная:");
                            System.out.println(result);
                        }
                        if (choice == 7) {
                            result = A.transpose();     
                            System.out.println("транспонированная:");
                            System.out.println(result);
                        }
                    } 
                    catch (IllegalArgumentException | ArithmeticException e) {
                        System.out.println("ошибка: " + e.getMessage());
                    }
                    break;
                }
                case 8: 
                    if ( result == null ){
                        System.out.println("Нет запомненного результата");
                    }
                    else {
                        System.out.println(result);
                    }
                    break ;
                case 0:
                    exit = true;
                    System.out.println("Выход");
                    break;
                default:
                    System.out.println("неверный выбор");
            }
        }
        sc.close();
    }


    private static Matrix readMatrix(Scanner sc, String name) {
        System.out.println("Ввод матрицы " + name );
        System.out.print("Строк: ");
        int rows = sc.nextInt();
        System.out.print("Столбцов: ");
        int cols = sc.nextInt();
        sc.nextLine();  
        if ( rows <= 0 || cols <= 0 ){
            throw new IllegalArgumentException("некорректные размеры матрицы");
        }

        Complex[][] matrix = new Complex[rows][cols];

        System.out.println("Введите каждую ячейку как 'действ мнимая' (через пробел):");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print("  " + name + "[" + i + "][" + j + "]: ");
                matrix[i][j] = new Complex(sc.nextDouble(), sc.nextDouble());
            }
        }

        return new Matrix(matrix);
    }

}
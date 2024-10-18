package complexNumb;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите количество строк и столбцов 1 матрицы");
        int n1,m1;
        try {
            n1 = scan.nextInt();
            m1 = scan.nextInt();
        } catch (NumberFormatException | InputMismatchException e){
            System.out.println("Введите число");
            n1 = scan.nextInt();
            m1 = scan.nextInt();
        }

        Matrix matrix1 = new Matrix(n1,m1, 1);

        int n2,m2;
        System.out.println("Введите количество строк и столбцов 2 матрицы");
        try {
            n2 = scan.nextInt();
            m2 = scan.nextInt();
        } catch (NumberFormatException | InputMismatchException e){
            System.out.println("Введите число");
            n2 = scan.nextInt();
            m2 = scan.nextInt();
        }

        Matrix matrix2 = new Matrix(n2, m2,1);
        matrix2.compl(matrix2);
        matrix2.compl(matrix2);


        System.out.print("Выберите операцию, которую Вы хотите выполнить:\n'+' - сложение \n'-'- вычитание\n'*'- умножение\n'T'- транспонирование\n'd' - нахождение детерминанта\n");//получение операции от пользователь
        String operat = scan.nextLine();
        operat = scan.nextLine();

        for (int l = 0; l < operat.length(); l++){ // проходимся по строчке и выполняем все указанные операции
            if (operat.charAt(l) == '+'){
                if(n1 == n2 && m1 == m2) {
                    System.out.println("\nПолученная матрица сложения:");
                    for (int i = 0; i < n1; i++) {
                        for (int j = 0; j < m2; j++)
                            Numbers.info(Numbers.sum(matrix1.getE(i, j), matrix2.getE(i, j)).getRe(), Numbers.sum(matrix1.getE(i, j), matrix2.getE(i, j)).getIm());
                        System.out.print("\n");
                    }
                }
                else
                    System.out.println("\nСложение невозможно, введите одинаковые размеры матриц");
            }

            if (operat.charAt(l) == '-'){
                if(n1 == n2 && m1 == m2) {
                    System.out.println("\nПолученная матрица вычитания:");
                    for (int i = 0; i < n1; i++) {
                        for (int j = 0; j < m1; j++) {
                            Numbers.info(Numbers.sub(matrix1.getE(i, j), matrix2.getE(i, j)).getRe(), Numbers.sub(matrix1.getE(i, j), matrix2.getE(i, j)).getIm());
                        }
                        System.out.print("\n");
                    }
                }
                else
                    System.out.println("\nВычитание невозможно, введите одинаковые размеры матриц");
            }

            if (operat.charAt(l) == '*'){
                if(m1 == n2) {
                    System.out.println("\nПолученная матрица умножения:");
                    Matrix.multiMatrix(matrix1, matrix2, n1, m1, m2);
                }
                else
                    System.out.println("\nУмножение невозможно, введите размеры так, чтобы количество столбцов 1ой матрицы было равно количеству строк второй");
            }

            if (operat.charAt(l) == 'T') {
                System.out.println("\nПервая полученная транспонированная матрица:");
                Matrix.trans(matrix1, n1, m1, 1);

                System.out.println("\nВторая полученная транспонированная матрица:");
                Matrix.trans(matrix2, n1, m1, 1);
            }

            if (operat.charAt(l) == 'd') {
                int flag = 0;
                if (n1 == m1) {
                    System.out.println("\nПолучен детерминант первой матрицы: ");
                    Numbers.info(Matrix.determ(matrix1, n1).getRe(), Matrix.determ(matrix1, n1).getIm());
                    flag++;
                }
                if (n2 == m2) {
                    System.out.println("\nПолучен детерминант второй матрицы: ");
                    Numbers.info(Matrix.determ(matrix2, n2).getRe(), Matrix.determ(matrix2, n2).getIm());
                    flag++;
                }
                if (flag == 0)
                    System.out.println("\nНе удалось вычислить детерминант, введите квадратные матрицы");

            }
        }
    }
}

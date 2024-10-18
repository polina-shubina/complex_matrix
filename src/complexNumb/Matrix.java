package complexNumb;
import java.util.Scanner;

public class Matrix {
    private int rows, columns;
    private Numbers[][] elements = new Numbers[rows][columns];

    public Matrix(int rows, int columns, int flag) {
        elements = new Numbers[rows][columns];
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++)
                elements[i][j] = new Numbers();
        }

        if (flag == 1) {
            int count = 1;
            Scanner scan = new Scanner(System.in);
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    System.out.print("(" + i + "," + j + ") элемент (" + count + " число)\nВведите число: ");
                    elements[i][j].setRe(scan.nextFloat());
                    elements[i][j].setIm(scan.nextFloat());
                    count++;
                }
            }
        }
    }

    public Matrix() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите количество строк и столбцов матрицы");
        rows = scan.nextInt();
        columns = scan.nextInt();
        elements = new Numbers[rows][columns];
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++)
                elements[i][j] = new Numbers();
        }
    }

    public Numbers getE(int i, int j){
        return elements[i][j];
    }

    public void setE(int i, int j, Numbers a){
        elements[i][j] = a;
    }

    //заполнение
    public void compl(Matrix a){
        int count = 1;
        for (int i = 0; i < a.rows; i++) {
            for (int j = 0; j < a.columns; j++) {
                Scanner scan = new Scanner(System.in);
                Numbers h = new Numbers();
                System.out.print("(" + i + "," + j + ") элемент (" + count + " число)\nВведите число: ");
                h.setRe(scan.nextDouble());
                h.setIm(scan.nextDouble());
                a.setE(i, j, h);
                count++;
            }
        }
    }



    //транспонирование
    public static Matrix trans(Matrix a, int length, int height, int flag) {//флаг для определения, выводить ли матрицу
        Matrix help = new Matrix(length,height, 0); //создание  матрицы
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < height; j++)
                help.setE(i,j, new Numbers());
        }

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < height; j++)
                help.setE(i,j, a.getE(j,i));
        }

        if (flag == 1){
            for (int i = 0; i < length; i++) {
                for (int j = 0; j < height; j++)
                    Numbers.info(help.getE(i,j).getRe(), help.getE(i,j).getIm());
                System.out.print("\n");
            }
        }
        return help;
    }



    //перемножение
    public static void multiMatrix(Matrix a, Matrix b, int n, int s, int m){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int k = 0;
                double resIm = 0;
                double resRe = 0;
                while (k < s) {
                    resRe += Numbers.multi(a.getE(i,k), b.getE(k,j)).getRe();
                    resIm += Numbers.multi(a.getE(i,k), b.getE(k,j)).getIm();
                    k++;
                }
                Numbers.info(resRe, resIm);
            }
            System.out.print("\n");
        }
    }



    //вспомогательная функция
    public static Matrix cut(Matrix a, int length, int row, int column){//строка и столбец, который "вырезаем"
        Matrix help = new Matrix(length - 1,length - 1,0); //создание  матрицы
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - 1; j++) {
                help.setE(i,j,new Numbers());
            }
        }
        for (int i = 0; i < length - 1; i++) {
            if(i >= row) {
                for (int j = 0; j < length - 1; j++) {
                    if(j >= column)
                        help.setE(i,j,a.getE(i+1,j+1));
                    else
                        help.setE(i,j, a.getE(i+1,j));
                }
            }
            else {
                for (int j = 0; j < length - 1; j++) {
                    if(j >= column)
                        help.setE(i,j,a.getE(i,j+1));
                    else
                        help.setE(i,j,a.getE(i,j));
                }
            }
        }
        return help;
    }



    //детерминант
    public static Numbers determ(Matrix a, int length){
        if (length == 1 )
            return a.getE(0,0);
        else if (length == 2 )
            return Numbers.sub(Numbers.multi(a.getE(0,0), a.getE(1,1)), Numbers.multi(a.getE(0,1), a.getE(1,0)));
        else{
            int k = 0;
            Numbers res = new Numbers();
            double resRe = 0, resIm = 0;
            while (k < length){
                resRe += Math.pow(-1, k) * Numbers.multi(a.getE(0,k), determ(cut(a,length,0, k),length - 1)).getRe();
                resIm += Math.pow(-1, k) * Numbers.multi(a.getE(0,k), determ(cut(a,length,0, k),length - 1)).getIm();
                k++;
            }
            res.setRe(resRe);res.setIm(resIm);
            return res ;
        }
    }
}


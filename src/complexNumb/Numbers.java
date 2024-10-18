package complexNumb;

public class Numbers {
   private double Re, Im;

    public Numbers(){
        Re = 0;
        Im = 0;
    }

    protected Numbers(double Re, double Im){
        this.Re = Re;
        this.Im = Im;
    }

    public double getIm() {
        return Im;
    }

    public double getRe() {
        return Re;
    }

    public void setIm(double Im) {
        this.Im = Im;
    }

    public void setRe(double Re) {
        this.Re = Re;
    }

    public static void info(double a, double b){
        System.out.print("(" + a + ", " + b + ")");
    }

    public static Numbers sum(Numbers a, Numbers b){
        Numbers c = new Numbers();
        c.setRe(a.Re + b.Re);
        c.setIm(a.Im + b.Im);
        return c;
    }

    public static Numbers sub(Numbers a, Numbers b){
        Numbers c = new Numbers();
        c.setRe(a.Re - b.Re);
        c.setIm(a.Im - b.Im);
        return c;
    }

    public static Numbers multi(Numbers a, Numbers b){
        Numbers c = new Numbers();
        c.setRe(a.Re * b.Re - a.Im * b.Im);
        c.setIm(a.Im * b.Re + a.Re * b.Im);
        return c;
    }

}

public class Complex {
    private double deistv;
    private double mnim;
    
    public Complex() {
        this.deistv = 0;
        this.mnim = 0;
    }
    
    public Complex(double deistv, double mnim) {
        this.deistv = deistv;
        this.mnim = mnim;
    }

    public void setdeistv(double deistv) {
        this.deistv = deistv;
    }
    public double getdeistv() {
        return deistv;
    }

    public double getmnim() {
        return mnim;
    }
    public void setmnim(double mnim) {
        this.mnim = mnim;
    }
    
    
    public void minus(Complex a) {
        this.deistv = this.deistv - a.deistv;
        this.mnim = this.mnim - a.mnim;
    }

    public void plus(Complex a) {
        this.deistv = this.deistv + a.deistv;
        this.mnim = this.mnim + a.mnim;
    }
    
    public void multiply(Complex a) {
        double new_deistv = this.deistv * a.deistv - this.mnim * a.mnim;
        double new_mnim = this.deistv * a.mnim + this.mnim * a.deistv;

        this.deistv = new_deistv;
        this.mnim = new_mnim;
    }
    
    
    public void divide(Complex a) {
        double znam = a.deistv * a.deistv + a.mnim * a.mnim;
        if (Math.abs(znam) < 1e-10) {
            System.out.println("Деление на ноль");
            return;
        }

        double new_deistv = (this.deistv * a.deistv + this.mnim * a.mnim) / znam;
        double new_mnim = (this.mnim * a.deistv - this.deistv * a.mnim) / znam;

        this.deistv = new_deistv;
        this.mnim = new_mnim;
    }

    
    public boolean isZero() {
        return Math.abs(deistv) < 1e-10 && Math.abs(mnim) < 1e-10;
    }
    
        
    public void conjugate() {
        this.mnim = -this.mnim;
    }

    @Override
    public String toString() {
        if (mnim == 0) {
            return deistv + "";
        }
        if (deistv == 0) {
            return mnim + "i";
        }
        if (mnim > 0) {
            return deistv + " + " + mnim + "i";
        }
        return deistv + " - " + (-mnim) + "i";
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + Double.hashCode(deistv);
        result = 31 * result + Double.hashCode(mnim);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        
        if (obj == this) {
            return true;
        }
        
        if (!(obj instanceof Complex)) {
            return false;
        }
        Complex a = (Complex) obj;
        return this.mnim == a.mnim  && this.deistv == a.deistv ;
    }

    
}
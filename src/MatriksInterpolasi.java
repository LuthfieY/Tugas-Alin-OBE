import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;

public class MatriksInterpolasi extends Matriks {
    public MatriksInterpolasi(int _brs, int _kol) {
        super(_brs,_kol);
    }

    public MatriksInterpolasi(Matriks mat) {
        super(mat.data, mat.getBrs(), mat.getKol());
    }

    // Interpolasi
    public void interpolasi(){
        // proses menghitung interpolasi
        for (int i = 1; i <= this.brs; i++) {
            for (int j = 1; j < this.kol; j++) {
                this.data[i][j] = java.lang.Math.pow(this.data[i][j],j-1);
            }
        }

        this.gaussJordan();

        System.out.printf("y= ");
        System.out.printf("%.1f ", this.data[1][this.kol]);
        for (int i = 2; i <= this.brs; i++){
            if (0 != this.data[i][this.kol]){
                if (this.data[i][this.kol] >= 0){
                    System.out.printf("+");
                }
                System.out.printf("%.1fx^%d ", this.data[i][this.kol], i-1);
            }
        }
        System.out.printf("\n");
    }
}


    

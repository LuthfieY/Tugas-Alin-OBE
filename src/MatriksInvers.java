import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;

public class MatriksInvers extends Matriks {
    public MatriksInvers(int _brs, int _kol) {
        super(_brs,_kol);
    }

    public MatriksInvers(Matriks mat) {
        super(mat.data, mat.getBrs(), mat.getKol());
    }

    public void inversAdjoin() {
    	// proses menghitung invers
		//Matriks adjoinMatrix = new Matriks(matrix.getBrs(), matrix.getKol());
		for (int i = 1; i <= this.brs; i++) {
		    for (int j = 1; j <= this.kol; j++) {
		        //adjoinMatrix.data[i][j] = adjoin(matrix);
		        //System.out.print(adjoin(matrix)/this.determinantOBE()+"\t");
		    }
		    System.out.println();
		}
	}
}
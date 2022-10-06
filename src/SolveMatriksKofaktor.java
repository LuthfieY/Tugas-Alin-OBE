public class SolveMatriksKofaktor {
    Matriks source;
    Matriks result;

    /**
     * Konstruktor SolveMatriksKofaktor.
     * 
     * @param source matriks yang akan dicari matriks kofaktornya.
     */
    public SolveMatriksKofaktor(Matriks source) {
        this.source = source;
    }

    /**
     * Jika source merupakan matriks N*N, hitung dan simpan matriks kofaktornya pada
     * result. Jika tidak, result = null.
     */
    public void solve() {
        if (source.getBrs() != source.getKol()) {
            this.result = null;
            return;
        }

        this.result = new Matriks(source.getBrs(), source.getKol());

        for (int i = 1; i <= result.getBrs(); i++) {
            for (int j = 1; j <= result.getKol(); j++) {
                result.data[i][j] = source.kofaktor(i, j);
            }
        }
    }

    /**
     * Jika result != null, print result. Jika tidak, print error.
     */
    public void printResult() {
        if (result == null) {
            System.out.println("Matriks tidak memiliki matriks kofaktor!");
        }

        System.out.println("Matriks kofaktor:");
        this.result.printMatriks();
    }
}
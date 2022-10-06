public class SolveAdjoin {
    Matriks source;
    Matriks result;

    /**
     * Konstruktor SolveAdjoin.
     * 
     * @param source matriks yang akan dicari matriks adjoin-nya.
     */
    public SolveAdjoin(Matriks source) {
        this.source = source;
    }

    /**
     * Jika source merupakan matriks N*N, hitung dan simpan matriks adjoin-nya pada
     * result. Jika tidak, result = null.
     */
    public void solve() {
        SolveMatriksKofaktor matriksKofaktor = new SolveMatriksKofaktor(source);
        matriksKofaktor.solve();

        if (matriksKofaktor.result == null) {
            this.result = null;
        } else {
            this.result = matriksKofaktor.result.transposeMatriks();
        }
    }

    /**
     * Jika result != null, print result. Jika tidak, print error.
     */
    public void printResult() {
        if (result == null) {
            System.out.println("Matriks tidak memiliki matriks adjoin!");
        }

        System.out.println("Matriks kofaktor:");
        this.result.printMatriks();
    }
}
public class MatriksDeterminan extends Matriks {
	public MatriksDeterminan(int _brs, int _kol) {
		super(_brs,_kol);
	}

    public MatriksDeterminan(Matriks mat) {
        super(mat.data, mat.getBrs(), mat.getKol());
    }
}

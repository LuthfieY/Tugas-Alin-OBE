public class MatriksSPL extends Matriks {
	private double[] hasilSPL;
	private int nPeubah;
	private boolean solution;
	private int[] status; //0 = tidak ada, 1 = solusi tunggal, 2 = solusi banyak

	public MatriksSPL(int _brs, int _kol) {
		super(_brs,_kol);
		this.nPeubah = this.kol - 1; //kolom terakhir adalah kolom hasil
		this.hasilSPL = new double[nPeubah+2]; //nPeubah+2 biar ga out of bound
		this.solution = true; //defaultnya true
	}

    public MatriksSPL(Matriks mat) {
        super(mat.data, mat.getBrs(), mat.getKol());
		this.nPeubah = this.kol - 1; 
		this.hasilSPL = new double[nPeubah+2]; 
		this.solution = true;
    }

	public boolean getSolution() {
		return this.solution;
	}

	public void cekStatus() {
		for (int i = this.brs; i > 0; i--) {
			if(!this.isBrsKosong(i)) {
				int idxFirst = this.getFirstIndeks(i);
				if (idxFirst == this.kol) {
					this.solution = false;
				} else {
					
				}
			}
		}
	}

	public void solveGaussForm() {
		this.cekStatus();
		if (this.solution) {
            System.out.println(hasilSPLMaybeNonUnique());
		}	
	}

	public void solveGaussJordan() {
		this.cekStatus();
		if (this.solution) {
            System.out.println(hasilSPLMaybeNonUnique());
		}
	}

	public void solveMatriksBalikan() {
		// [A|I] => [I|A^-1] USING OBE
		// [x = A^-1.b]
		// konstanta (b)
		double[] b_coef = new double[this.brs + 2];
		for (int i = 1; i <= this.brs; i++) {
			b_coef[i] = this.data[i][this.kol];
		}
		// matriks invers
		this.inversOBE();
		// hasil persamaan
		for (int i = 1; i <= this.brs; i++) {
			for (int j = 1; j <= (this.kol - 1); j++) {
				hasilSPL[i] += (this.data[i][j] * b_coef[j]);
			}
		}
	}

	/**
     * Menyelesaikan SPL dengan kaidah Cramer.
     * Ax = b
     * x1 = det(A1)/det(A), x2 = det(A2)/det(A), ...
     */
    public void solveCramerRule() {
        if (this.getBrs() != this.getKol() - 1) {
            System.out.println("Tidak bisa diselesaikan dengan kaidah Cramer!");
            this.solution = false;
            return;
        }

        // Memisahkan konstanta (b) dan koefisien variabel (A) persamaan
        double[] konstantaPersamaan = new double[this.nPeubah + 2];
        Matriks koefisienVariabel = new Matriks(this.getBrs(), this.getKol() - 1);

        for (int i = 1; i <= this.getBrs(); i++) {
            for (int j = 1; j <= this.getKol(); j++) {
                if (j == this.getKol()) {
                    konstantaPersamaan[i] = this.data[i][j];
                } else {
                    koefisienVariabel.data[i][j] = this.data[i][j];
                }
            }
        }

        // Mencari determinan matriks koefisien variabel
        double determinanKoefisienVariabel = koefisienVariabel.determinantLaplaceExpansion();

        if (determinanKoefisienVariabel == 0) {
            System.out.println("Tidak bisa diselesaikan dengan kaidah Cramer! (Tidak memiliki solusi unik)");
            this.solution = false;
            return;
        }

        // iterasi tiap Ai pada kaidah Cramer
        for (int kolomTukar = 1; kolomTukar <= this.getKol(); kolomTukar++) {
            Matriks tempMatrix = new Matriks(koefisienVariabel.getBrs(), koefisienVariabel.getKol());

            for (int i = 1; i <= koefisienVariabel.getBrs(); i++) {
                for (int j = 1; j <= koefisienVariabel.getKol(); j++) {
                    if (j == kolomTukar) {
                        // Tukar kolom dengan konstanta persamaan (b)
                        tempMatrix.data[i][j] = konstantaPersamaan[i];
                    } else {
                        tempMatrix.data[i][j] = koefisienVariabel.data[i][j];
                    }
                }
            }

            // det(Ai)
            double tempDeterminant = tempMatrix.determinantLaplaceExpansion();

            this.hasilSPL[kolomTukar] = tempDeterminant / determinanKoefisienVariabel;
        }

        this.solution = true;
        System.out.println(this.hasilSPL()); 
    }

    public String hasilSPL() {
        String hasil = "";
        for (int i = 1; i <= this.nPeubah; i++) {
            hasil += String.format("x%d = %f; ", i, this.hasilSPL[i]);
        }
        return hasil;
    }

    public String hasilSPLMaybeNonUnique() {
        String hasil = "";

        for (int i = 1; i <= this.getBrs(); i++) {
            int xi = this.getFirstIndeks(i); // indeks kolom x1, x2, x3, ...
            if (xi == this.getKol()) { 
                continue;
            }
            hasil += "x" + xi + " = ";
            for (int j = xi+1; j < this.getKol(); j++) {
                if (this.data[i][j] != 0) {
                    hasil += (this.data[i][j]*-1) + "*x" + j + " + ";
                }
            }
            hasil += this.data[i][this.getKol()] + "\n";
        }

        return hasil;
    }
}
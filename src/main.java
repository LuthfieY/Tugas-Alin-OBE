import java.util.*;

public class main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		char inputMenu, inputPilihan;
		do {
			System.out.printf("\nMENU\n");
			System.out.printf(
				"1. Sistem Persamaaan Linier\n" + 
				"2. Determinan\n" + 
				"3. Matriks balikan\n" + 
				"4. Matriks kofaktor\n" + 
				"5. Adjoin\n" + 
				"6. Interpolasi Polinom\n" + 
				"7. Keluar\n");
			System.out.printf("Pilihan: ");
			inputMenu = input.next().charAt(0);
			if (inputMenu == '1') {
				System.out.printf("\nPILIHAN : \n");
				System.out.printf(
					"1. Metode eliminasi Gauss\n" + 
					"2. Metode eliminasi Gauss-Jordan\n" + 
					"3. Metode matriks balikan\n" + 
					"4. Kaidah Cramer\n");
				inputPilihan = input.next().charAt(0);
				
				Matriks mat = getMatriksConsoleOrFile();
				System.out.println("Input dari user: ");
				mat.printMatriks();
				MatriksSPL M = new MatriksSPL(mat);

				if (inputPilihan == '1') {
					M.gaussForm();
					M.solveGaussForm();
				} else if (inputPilihan == '2') {
					M.gaussJordan(); 
					System.out.println("Solusi SPL");
					M.solveGaussJordan();
				} else if (inputPilihan == '3') {
					M.solveMatriksBalikan();
					String hasil = M.hasilSPL();
					System.out.println(hasil);
				} else if (inputPilihan == '4') {
					M.solveCramerRule();
					String hasil = M.hasilSPL();
					System.out.println(hasil);
				}
				if (!M.getSolution()) {
					System.out.println("No solution");
				}
			} else if (inputMenu == '2') {
				System.out.printf("\nPILIHAN : \n");
				System.out.printf(
					"1. Metode row reduction a.k.a OBE \n" + 
					"2. Metode ekspansi kofaktor a.k.a. Laplace-Expansion\n");
				inputPilihan = input.next().charAt(0);
				
				Matriks mat = getMatriksConsoleOrFile();
				MatriksDeterminan M = new MatriksDeterminan(mat);

				if (inputPilihan == '1') {
					double hasil = M.determinantOBE();
					System.out.println("Hasil determinant = " + hasil);
				} else if (inputPilihan == '2') {
					double hasil = M.determinantLaplaceExpansion();
					System.out.println("Hasil determinant = " + hasil);
				}
			} else if (inputMenu == '3') {
				System.out.printf("\nPILIHAN : \n");
				System.out.printf(
					"1. Metode row reduction a.k.a OBE \n" + 
					"2. Metode adjoint\n");
				inputPilihan = input.next().charAt(0);
				
				Matriks mat = getMatriksConsoleOrFile();
				MatriksInvers M = new MatriksInvers(mat);
				
				if (inputPilihan == '1') {
					M.inversOBE();
					M.printMatriks();
				} else if (inputPilihan == '2') {
					M.inversAdjoin();
					M.printMatriks();
				}
			} else if (inputMenu == '4') {
				Matriks mat = getMatriksConsoleOrFile();

				SolveMatriksKofaktor solver = new SolveMatriksKofaktor(mat);
				solver.solve();
				solver.printResult();
			} else if (inputMenu == '5') {
				Matriks mat = getMatriksConsoleOrFile();

				SolveAdjoin solver = new SolveAdjoin(mat);
				solver.solve();
				solver.printResult();
			} else if (inputMenu == '6') {
				Matriks mat = getMatriksPointConsoleOrFile();
				
				MatriksInterpolasi interMat = new MatriksInterpolasi(mat);
				interMat.interpolasi();
			}

		} while (inputMenu != '7');

		input.close();
	}

	public static Matriks getMatriksConsoleOrFile() {
		Scanner input = new Scanner(System.in);
		System.out.println(
			"1. Input manual\n" +
			"2. Input file");

		int pilihanInput = input.nextInt();

		Matriks mat; 
		if (pilihanInput == 1) {
			System.out.print("Masukkan jumlah baris: ");
			int baris = input.nextInt();
			System.out.print("Masukkan jumlah kolom: ");
			int kolom = input.nextInt(); 
			mat = new MatriksSPL(baris, kolom); // default matriks SPL

			System.out.println("Masukkan matriks:");
			mat.bacaMatriks();
		}
		else { // pilihanInput == 2
			System.out.println("Masukkan nama file:");
			String filename = input.next();
			mat = MatriksFileHandler.readMatriksFromFile(filename);
		}

		return mat;
	}

	public static Matriks getMatriksPointConsoleOrFile() {
		Scanner input = new Scanner(System.in);
		System.out.println(
			"1. Input manual\n" +
			"2. Input file");

		int pilihanInput = input.nextInt();

		Matriks mat;
		if (pilihanInput == 1) {
			System.out.println("Masukkan jumlah koordinat:");
			int baris = input.nextInt();
			int kolom = baris + 1;

			double[][] matriksDouble = new double[baris + 2][kolom + 2];

			System.out.println("Masukkan koordinat:");
			for(int i = 1; i <= baris; i++) {
				double x = input.nextDouble();
				for(int j = 1; j < kolom; j++) {
					matriksDouble[i][j] = x;
				}

				matriksDouble[i][kolom] = input.nextDouble();
			}

			mat = new Matriks(matriksDouble, baris, kolom);
		}
		else { // pilihanInput == 2
			System.out.println("Masukkan nama file:");
			String filename = input.next();
			mat = MatriksFileHandler.readMatriksFromPointFile(filename);
		}

		return mat;
	}
}

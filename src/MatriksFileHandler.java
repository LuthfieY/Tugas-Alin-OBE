import java.util.Scanner;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MatriksFileHandler {
	public static Matriks readMatriksFromFile(String filename)
	{
		Matriks hasil = null;

		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("filename"));

			ArrayList<ArrayList<Double>> matrix = new ArrayList<>();
			int i = -1;

			String line = reader.readLine();
			while (line != null) {
				Scanner numberScanner = new Scanner(line);

				matrix.add(new ArrayList<>());
				i++;

				Double element = numberScanner.nextDouble();
				while (element != null) {
					matrix.get(i).add(element);
					element = numberScanner.nextDouble();
				}
				line = reader.readLine();
			}

			reader.close();

			int baris = matrix.size();
			int kolom = matrix.get(0).size();

			double[][] matriksDouble = new double[baris + 2][kolom + 2];

			for(i = 1; i <= baris; i++) {
				for(int j = 1; j <= kolom; j++) {
					matriksDouble[i][j] = matrix.get(i-1).get(j-1);
				}
			}

			hasil = new Matriks(matriksDouble, baris, kolom);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return hasil;
    }
    
	public static Matriks readMatriksFromPointFile(String filename)
	{
		Matriks hasil = null;

		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("filename"));

			ArrayList<ArrayList<Double>> matrix = new ArrayList<>();
			int i = -1;

			String line = reader.readLine();
			while (line != null) {
				Scanner numberScanner = new Scanner(line);

				matrix.add(new ArrayList<>());
				i++;

				Double element = numberScanner.nextDouble();
				while (element != null) {
					matrix.get(i).add(element);
					element = numberScanner.nextDouble();
				}
				line = reader.readLine();
			}

			reader.close();

			int baris = matrix.size();
			int kolom = baris + 1;

			double[][] matriksDouble = new double[baris + 2][kolom + 2];

			for(i = 1; i <= baris; i++) {
				for(int j = 1; j < kolom; j++) {
					matriksDouble[i][j] = matrix.get(i-1).get(0);
                }
                matriksDouble[i][kolom] = matrix.get(i-1).get(1);
			}

			hasil = new Matriks(matriksDouble, baris, kolom);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return hasil;
    }
}
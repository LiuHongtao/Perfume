package analyzer;

import java.io.BufferedReader;

import java.io.File;

import java.io.FileNotFoundException;

import java.io.FileReader;

import java.io.IOException;

public class SumJavaCode {

	public int[] CaculateLine(String line) {
		int[] result = new int[3];
		boolean comment = false;
		if (line.matches("^[//s&&[^//n]]*$")) {

			result[2]++;

		} else if (line.startsWith("/*")) {
			if (!line.endsWith("*/")) {
				if (line.contains("*/")) {
					result[1]++;
					result[0]++;
				} else {
					result[1]++;
					comment = true;
				}

			}

		} else if (true == comment) {

			result[1]++;

			if (line.endsWith("*/")) {

				comment = false;

			}

		} else if (line.startsWith("//")) {
			result[1]++;

		} else {

			result[0]++;

		}
		if (result[0] == 0) {
			System.out.println();
		}
		return result;
	}

	public int[] LineOfCode(File file) {
		BufferedReader br = null;

		boolean comment = false;
		int[] result = new int[3];

		try {

			br = new BufferedReader(new FileReader(file));

			String line = "";

			try {

				while ((line = br.readLine()) != null) {

					line = line.trim();

					if (line.matches("^[//s&&[^//n]]*$")) {

						result[2]++;

					} else if (line.startsWith("/*")) {
						if (!line.endsWith("*/")) {
							if (line.contains("*/")) {
								result[1]++;
								result[0]++;
							} else {
								result[1]++;
								comment = true;
							}

						}

					} else if (true == comment) {

						result[1]++;

						if (line.endsWith("*/")) {

							comment = false;

						}

					} else if (line.startsWith("//")) {
						result[1]++;

					} else {
						// System.out.println(line+"\t"+result[0]);
						result[0]++;

					}

				}

			} catch (IOException e) {

				e.printStackTrace();

			}

		} catch (FileNotFoundException e) {

			e.printStackTrace();

		} finally {

			if (br != null) {

				try {

					br.close();

					br = null;

				} catch (IOException e) {

					e.printStackTrace();

				}

			}

		}

		return result;

	}

	public int[] LineOfCode(File file, int startLine, int endLine) {
		BufferedReader br = null;

		boolean comment = false;
		int[] result = new int[3];

		try {

			br = new BufferedReader(new FileReader(file));

			String line = "";

			try {
				int countLine = 1;
				while ((line = br.readLine()) != null) {
					if (countLine < startLine) {
						countLine++;
						continue;
					}
					countLine++;
					line = line.trim();

					if (line.matches("^[//s&&[^//n]]*$")) {

						result[2]++;

					} else if (line.startsWith("/*")) {
						if (!line.endsWith("*/")) {
							if (line.contains("*/")) {
								result[1]++;
								result[0]++;
							} else {
								result[1]++;
								comment = true;
							}

						}
					} else if (true == comment) {

						result[1]++;

						if (line.endsWith("*/")) {

							comment = false;

						}

					} else if (line.startsWith("//")) {
						result[1]++;

					} else {

						// System.out.println(line);
						result[0]++;

					}
					if (countLine > endLine)
						break;

				}

			} catch (IOException e) {

				e.printStackTrace();

			}

		} catch (FileNotFoundException e) {

			e.printStackTrace();

		} finally {

			if (br != null) {

				try {

					br.close();

					br = null;

				} catch (IOException e) {

					e.printStackTrace();

				}

			}

		}

		return result;
	}

}

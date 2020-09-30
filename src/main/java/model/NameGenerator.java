package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class NameGenerator {

	private static NameGenerator nameGenerator;
	private static final String FILE_PATH = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "data" + File.separator + "randomNames.csv";

	private ArrayList<String> names;
	private ArrayList<String> lastNames;

	private NameGenerator() {

		names = new ArrayList<String>(1000);
		lastNames = new ArrayList<String>(1000);

		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(FILE_PATH)));
			String line = br.readLine();

			while (line != null) {
				
				String[] ns = line.split(",");
				
				names.add(ns[0]);
				lastNames.add(ns[1]);

				line = br.readLine();
			}

			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

		}

	}

	public String getRandomName() {

		String name = "";

		Random r = new Random();
		name = names.get(r.nextInt(names.size()));

		return name;

	}

	public String getRandomLastName() {

		String lastName = "";

		Random r = new Random();
		lastName = lastNames.get(r.nextInt(lastNames.size()));

		return lastName;

	}

	public String getRandomCompleteName() {
		String completeName = "";

		Random r = new Random();
		completeName = names.get(r.nextInt(names.size()));
		completeName += " " + lastNames.get(r.nextInt(lastNames.size()));
		
		return completeName;
	}

	public static NameGenerator getInstance() {

		if (nameGenerator == null) {
			nameGenerator = new NameGenerator();
		}

		return nameGenerator;

	}

}

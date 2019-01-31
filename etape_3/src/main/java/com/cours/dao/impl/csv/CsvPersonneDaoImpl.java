package com.cours.dao.impl.csv;

import com.cours.dao.IPersonneDao;
import com.cours.entities.Personne;
import com.cours.observer.MySubjectObserver;
import com.cours.utils.Constants;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CsvPersonneDaoImpl /* extends AbstractCsvDao<Personne> */ implements IPersonneDao {

	private final String personnesCsvPathFile = Constants.PERSONNES_CSV_PATH_FILE;
	private MySubjectObserver subject = null;
	private boolean sendNotification = true;

	public CsvPersonneDaoImpl() {
	}

	public CsvPersonneDaoImpl(MySubjectObserver subject) {
		// super(Personne.class, subject, Constants.PERSONNES_CSV_PATH_FILE);
		this.subject = subject;
	}

	@Override
	public Personne findById(int idPersonne) {
		List<Personne> personnes = findAll();
		
		int i = -1;

		for (Personne person : personnes) {
			++i;
			if (idPersonne == person.getIdPersonne()) {
				return person;
			}
		}
		
		return null;
	}

	@Override
	public Personne create(Personne obj) {
		// Besoin des objets Personne

		List<Personne> personnes = findAll();
		int idPersonne = this.generateIdNewPersonne();
		obj.setIdPersonne(idPersonne);

		personnes.add(personnes.size(), obj);
		this.generateNewFileContent(personnes);

		return obj;
	}

	@Override
	public boolean delete(Personne person) {

		// Besoin des objets Personne
		List<Personne> personnes = findAll();
		int i = -1;

		for (Personne personComp : personnes) {
			++i;
			if (person.getPrenom().equals(personComp.getPrenom())
				&& person.getNom().equals(personComp.getNom())
				&& person.getPoids() == personComp.getPoids() 
				&& person.getTaille() == personComp.getTaille()
				&& person.getRue().equals(personComp.getRue())
				&& person.getVille().equals(personComp.getVille())
				&& person.getCodePostal().equals(personComp.getCodePostal())) {

				personnes.remove(i);
				this.generateNewFileContent(personnes);
				return true;
			}
		}
		return false;
	}

	private Personne createPersonneWithFileObject(String[] attributs) {

		Personne person = new Personne();

		if (attributs.length != 8) {
			return null;
		}

		try {
			if (attributs[0] != null) {
				try {
					person.setIdPersonne(Integer.valueOf(attributs[0]));
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			if (attributs[1] != null) {
				try {
					person.setPrenom((String.valueOf(attributs[1])));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (attributs[2] != null) {
				try {
					person.setNom(String.valueOf(attributs[2]));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (attributs[3] != null) {
				try {
					person.setPoids(Double.valueOf(attributs[3]));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (attributs[4] != null) {
				try {
					person.setTaille(Double.valueOf(attributs[4]));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (attributs[5] != null) {
				try {
					person.setRue(String.valueOf(attributs[5]));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (attributs[6] != null) {
				try {
					person.setVille(String.valueOf(attributs[6]));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (attributs[7] != null) {
				try {
					person.setCodePostal(String.valueOf(attributs[7]));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return person;

	}

	@Override
	public List<Personne> findAll() {

		List<Personne> personnes = new ArrayList<Personne>();

		try {
			FileReader reader = new FileReader(personnesCsvPathFile);
			Scanner scanner = new Scanner(reader);
			String Line = scanner.nextLine();

			while (scanner.hasNextLine()) {
				Line = scanner.nextLine();
				Line = Line.replace("; ", ";");
				Personne person = this.createPersonneWithFileObject(Line.split("\\;"));
				if (person != null) {
					personnes.add(person);
				}
			}
			scanner.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return personnes;
	}

	@Override
	public int generateIdNewPersonne() {

		List<Personne> personnes = findAll();
		if (personnes.size() > 0) {			
			Personne lastPerson = personnes.get(personnes.size() - 1);
			return lastPerson.getIdPersonne() + 1;
		} else {
			return 1;
		}

	}

	private List<Personne> generateNewFileContent(List<Personne> obj) {

		String newFileContent = Constants.CSV_HEADER;

		for (Personne person : obj) {
			newFileContent += person.getCsvString();
		}

		System.out.println("nFC:\n" + newFileContent);
		FileWriter fileWriter = null;

		try {
			fileWriter = new FileWriter(personnesCsvPathFile);
			fileWriter.append(newFileContent);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return obj;

	}

	@Override
	public boolean sendNotification() {
		return sendNotification;
	}

	@Override
	public void setSendNotification(boolean sendNotification) {
		this.sendNotification = sendNotification;
	}
}

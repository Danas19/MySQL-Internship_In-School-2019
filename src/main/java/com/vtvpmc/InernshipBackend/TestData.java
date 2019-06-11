package com.vtvpmc.InernshipBackend;

import org.springframework.stereotype.Component;

import com.vtvpmc.InernshipBackend.model.Admin;
import com.vtvpmc.InernshipBackend.model.DocumentType;
import com.vtvpmc.InernshipBackend.model.Person;
import com.vtvpmc.InernshipBackend.model.User;
import com.vtvpmc.InernshipBackend.model.UserGroup;
import com.vtvpmc.InernshipBackend.repository.AdminRepository;
import com.vtvpmc.InernshipBackend.repository.DocumentRepository;
import com.vtvpmc.InernshipBackend.repository.DocumentTypeRepository;
import com.vtvpmc.InernshipBackend.repository.PdfFileRepository;
import com.vtvpmc.InernshipBackend.repository.PersonRepository;
import com.vtvpmc.InernshipBackend.repository.UserGroupRepository;
import com.vtvpmc.InernshipBackend.repository.UserRepository;

@Component
public class TestData {
	private DocumentRepository documentRepository;
	private DocumentTypeRepository documentTypeRepository;
	private PersonRepository personRepository;
	private UserRepository userRepository;
	private UserGroupRepository userGroupRepository;
	private AdminRepository adminRepository;
	private PdfFileRepository pdfFileRepository;
	
	
	
	public TestData(DocumentRepository documentRepository, DocumentTypeRepository documentTypeRepository,
			PersonRepository personRepository, UserRepository userRepository, UserGroupRepository userGroupRepository,
			AdminRepository adminRepository, PdfFileRepository pdfFileRepository) {
		super();
		this.documentRepository = documentRepository;
		this.documentTypeRepository = documentTypeRepository;
		this.personRepository = personRepository;
		this.userRepository = userRepository;
		this.userGroupRepository = userGroupRepository;
		this.adminRepository = adminRepository;
		this.pdfFileRepository = pdfFileRepository;
	}



	public void createTestData() {
		this.documentTypeRepository.save(new DocumentType("Atostogu prasymai"));
		this.documentTypeRepository.save(new DocumentType("Svarbiausi"));
		
		UserGroup[] userGroups = createAndSave5UserGroups();
		Person[] persons = create18Persons();
		User[] users = create15Users();
		createAndSave3Admins(persons[15], persons[16], persons[17]);
		
		createRelationshipPersonsUsers(persons, users);
		addGroupsToUsers(users, userGroups);
		savePersons(persons);
		saveUsers(users);
		
	}
	
	
	
	private void createAndSave3Admins(Person...persons) {
		for (int i = 0; i < persons.length; i++) {
			Admin newAdmin = new Admin();
			newAdmin.setPerson(persons[i]);
			persons[i].setAdmin(newAdmin);
		}
	}



	private void savePersons(Person[] persons) {
		for (int i = 0; i < persons.length; i++) {
			personRepository.save(persons[i]);
		}
	}
	
	private void saveUsers(User[] users) {
		for (int i = 0; i < users.length; i++) {
			userRepository.save(users[i]);
		}
	}



	private UserGroup[] createAndSave5UserGroups() {
		UserGroup[] userGroups = new UserGroup[5];
		userGroups[0] = new UserGroup("Administratoriai");
		userGroups[1] = new UserGroup("Kompiuteristai");
		userGroups[2] = new UserGroup("Praktikantai");
		userGroups[3] = new UserGroup("Valytojai");
		userGroups[4] = new UserGroup("Maisto gamintojai");
		
		for (int i = 0; i < userGroups.length; i++) {
			this.userGroupRepository.save(userGroups[i]);
		}
		return userGroups;
	}
	
	private Person[] create18Persons() {
		Person[] persons = new Person[18];
		
		persons[0] = new Person("Vytautas", "Petrauskas");
		persons[1] = new Person("Algis", "Ramanauskas");
		persons[2] = new Person("Vytautas", "Racickas");
		persons[3] = new Person("Marius", "Petrauskas");
		persons[4] = new Person("Petras", "Petraitis");
		persons[5] = new Person("Gytis", "Algimantauskas");
		persons[6] = new Person("Zydrunas", "Aleksandravicius");
		persons[7] = new Person("Nojus", "Lileikis");
		persons[8] = new Person("Greta", "Petrauskaite");
		persons[9] = new Person("Nijole", "Grybauskaite");
		persons[10] = new Person("Ieva", "Bartuseviciute");
		persons[11] = new Person("Asta", "Kazakeviciene");
		persons[12] = new Person("Mindaugas", "Aleksandravicius");
		persons[13] = new Person("Steponas", "Kazakevicius");
		persons[14] = new Person("Asta", "Lileikiene");
		persons[15] = new Person("Adolfas", "Basanauskas");
		persons[16] = new Person("Irmantas", "Cekavicius");
		persons[17] = new Person("Simas", "Zymantas");
		
		return persons;
	}
	
	public User[] create15Users() {
		User[] users = new User[15];
		users[0] = new User();
		users[1] = new User();
		users[2] = new User();
		users[3] = new User();
		users[4] = new User();
		users[5] = new User();
		users[6] = new User();
		users[7] = new User();
		users[8] = new User();
		users[9] = new User();
		users[10] = new User();
		users[11] = new User();
		users[12] = new User();
		users[13] = new User();
		users[14] = new User();
		
		return users;
	}
	
	public void createRelationshipPersonsUsers(Person[] persons, User[] users) {
		int smallerLength = persons.length < users.length ? persons.length : users.length;
		
		for (int i = 0; i < smallerLength; i++) {
			users[i].setPerson(persons[i]);
			persons[i].setUser(users[i]);
		}
	}
	
	public void addGroupsToUsers(User[] users, UserGroup[] groups) {
		for (int i = 0; i < users.length; i++) {
			for (int j = (int)(Math.random() * groups.length); j < groups.length;) {
				users[i].addGroup(groups[j]);
				groups[j].addUser(users[i]);
				j += (int)(Math.random() * groups.length);
			}
		}
	}
}

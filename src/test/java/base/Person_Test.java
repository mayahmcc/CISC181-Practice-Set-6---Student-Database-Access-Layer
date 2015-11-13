package base;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.PersonDomainModel;

public class Person_Test {

	private static PersonDomainModel Mayah;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Calendar cal = Calendar.getInstance();
		cal.set(1996, 6, 10);
		Date bday = new Date();
		bday = cal.getTime();

		Mayah = new PersonDomainModel();
		Mayah.setFirstName("Mayah");
		Mayah.setLastName("McCutchen");
		Mayah.setCity("Newark");
		Mayah.setPersonID(UUID.randomUUID());
		Mayah.setPostalCode(19702);
		Mayah.setStreet("Thunder");
		Mayah.setBirthday(bday);
	}

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
		PersonDomainModel per;
		PersonDAL.deletePerson(Mayah.getPersonID());
		per = PersonDAL.getPerson(Mayah.getPersonID());
		assertNull("The Person shouldn't have been in the database", per);
	}

	@Test
	public void addPersontest() {
		PersonDomainModel person;
		person = PersonDAL.getPerson(Mayah.getPersonID());
		assertNull("The Person shouldn't have been in the database", person);
		PersonDAL.addPerson(Mayah);

		person = PersonDAL.getPerson(Mayah.getPersonID());
		System.out.println(Mayah.getPersonID() + " found");
		assertNotNull("The Person should have been added to the database", person);
	}

	@Test
	public void updatePersonTest() {
		PersonDomainModel per;
		final String C_LASTNAME = "Thompson";

		per = PersonDAL.getPerson(Mayah.getPersonID());
		assertNull("The Person shouldn't have been in the database", per);
		PersonDAL.addPerson(Mayah);

		Mayah.setLastName(C_LASTNAME);
		PersonDAL.updatePerson(Mayah);

		per = PersonDAL.getPerson(Mayah.getPersonID());

		assertTrue("Name Didn't Change", Mayah.getLastName() == C_LASTNAME);
	}

	@Test
	public void DeletePersonTest() {
		PersonDomainModel per;
		per = PersonDAL.getPerson(Mayah.getPersonID());
		assertNull("The Person shouldn't have been in the database", per);

		PersonDAL.addPerson(Mayah);
		per = PersonDAL.getPerson(Mayah.getPersonID());
		System.out.println(Mayah.getPersonID() + " found");
		assertNotNull("The Person should have been added to the database", per);

		PersonDAL.deletePerson(Mayah.getPersonID());
		per = PersonDAL.getPerson(Mayah.getPersonID());
		assertNull("The Person shouldn't have been in the database", per);

	}

}

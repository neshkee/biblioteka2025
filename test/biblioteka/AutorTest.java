package biblioteka;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class AutorTest {

	private Autor a;
	
	@BeforeEach
	void setUp() throws Exception {
		a = new Autor();
	}

	@AfterEach
	void tearDown() throws Exception {
		a = null;
	}

	@Test
	void testSetIme() {
		a.setIme("Nemanja");
		assertEquals("Nemanja", a.getIme());
	}
	
	@Test
	void testSetImeNull() {
		assertThrows(java.lang.NullPointerException.class, () -> a.setIme(null));
	}

	@Test
	void testSetImePrazno() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> a.setIme(""));
	}
	
	@Test
	void testSetPrezime() {
		a.setPrezime("Nesic");
		assertEquals("Nesic", a.getPrezime());
	}
	
	@Test
	void testSetPrezimeNull() {
		assertThrows(java.lang.NullPointerException.class, () -> a.setPrezime(null));
	}

	@Test
	void testSetprezimePrazno() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> a.setPrezime(""));
	}
	
	@Test
	void testAutor() {
		assertNotNull(a);
	}
	
	@Test
	void testAutorStringString() {
		a = new Autor("Nemanja", "Nesic");
		assertNotNull(a);
		assertEquals("Nemanja", a.getIme());
		assertEquals("Nesic", a.getPrezime());
	}
	
	
	@Test
	void testToString() {
		a = new Autor("Nemanja", "Nesic");
		
		assertTrue(a.toString().contains("Nemanja"));
		assertTrue(a.toString().contains("Nesic"));
	}
	
	
	@ParameterizedTest
	@CsvSource({
		"Nemanja, Nesic, Nemanja, Nesic, true",
		"Nemanja, Nesic, Miljana, Nesic, false",
		"Nemanja, Nesic, Nemanja, Velinovic, false",
		"Nemanja, Nesic, Miljana, Velinovic, false"
	})
	void testEquals(String ime1, String prezime1, String ime2, String prezime2, boolean ocekivano) {
		a = new Autor(ime1, prezime1);
		Autor a2 = new Autor(ime2, prezime2);
		assertEquals(ocekivano, a.equals(a2));
		
	}
	
	
	
	
	
	
}

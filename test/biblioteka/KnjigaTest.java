package biblioteka;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KnjigaTest {

	private Knjiga k;
	
	@BeforeEach
	void setUp() throws Exception {
		k = new Knjiga();
	}

	@AfterEach
	void tearDown() throws Exception {
		k = null;
	}

	@Test
	void testSetNaslov() {
		k.setNaslov("Hajduci");
		assertEquals("Hajduci", k.getNaslov());
	}

	@Test
	void testSetNaslovNull() {
		assertThrows(java.lang.NullPointerException.class, () -> k.setNaslov(null));
	}
	
	
	@Test
	void testSetNaslovPrazno() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> k.setNaslov(""));
	}
	
	
	@ParameterizedTest
	@CsvSource({
		"0",
		"-1"
	})
	void testSetIsbn(long isbn) {
		assertThrows(java.lang.IllegalArgumentException.class, () -> k.setIsbn(isbn));
	}
	
	@Test
	void testSetIsbn2() {
		k.setIsbn(33);
		assertEquals(33, k.getIsbn());
	}
	
	
	@Test
	void testSetIzdavac() {
		k.setIzdavac("Laguna");
		assertEquals("Laguna", k.getIzdavac());
	}

	@Test
	void testSetIzdavacNull() {
		assertThrows(java.lang.NullPointerException.class, () -> k.setIzdavac(null));
	}
	
	
	@Test
	void testSetIzdavacPrazno() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> k.setIzdavac(""));
	}
	
	
	@ParameterizedTest
	@CsvSource({
		"0",
		"-1"
	})
	void testSetIzdanje(int izdanje) {
		assertThrows(java.lang.IllegalArgumentException.class, () -> k.setIzdanje(izdanje));
	}
	
	@Test
	void testSetIzdanje2() {
		k.setIzdanje(2);
		assertEquals(2, k.getIzdanje());
	}
	
	@Test
	void testToString() {
		Autor a1 = new Autor("Ivo", "Andric");
		Autor a2 = new Autor("Danilo", "Kis");
		List<Autor> autori = new ArrayList<>();
		autori.add(a1);
		autori.add(a2);
		k.setAutori(autori);
		k.setIsbn(4);
		k.setIzdanje(5);
		k.setIzdavac("Laguna");
		k.setNaslov("Na drini cuprija");
		assertTrue(k.toString().contains("Danilo") && k.toString().contains("4") 
				&& k.toString().contains("Kis") && k.toString().contains("Ivo") 
				&& k.toString().contains("Andric") && k.toString().contains("5")
				&& k.toString().contains("Na drini cuprija"));
	}
	
	@ParameterizedTest
	@CsvSource({
		"1, 1, true",
		"1, 2, false",
		"2, 1, false",
		"1234, 1234, true"
	})
	void testEquals(int isbn1, int isbn2, boolean ocekivano) {
		Knjiga k1 = new Knjiga();
		
		k.setIsbn(isbn1);
		k1.setIsbn(isbn2);
		
		assertEquals(ocekivano, k.equals(k1));
		
	}
	
	
	
}

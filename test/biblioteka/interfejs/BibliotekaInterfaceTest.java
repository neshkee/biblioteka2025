package biblioteka.interfejs;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import biblioteka.Knjiga;

abstract class BibliotekaInterfaceTest {

	protected BibliotekaInterface biblioteka;
	
	public abstract BibliotekaInterface getInstance();
	
	private Knjiga k1;
	private Knjiga k2;
	
	@BeforeEach
	void setUp() throws Exception {
		biblioteka = getInstance();
		k1 = new Knjiga();
		k2 = new Knjiga();
		k1.setIsbn(111);
		k2.setIsbn(222);
	}

	@AfterEach
	void tearDown() throws Exception {
		biblioteka = null;
		k1 = null;
		k2 = null;
	}

	@Test
	void testDodajKnjigu() {
		biblioteka.dodajKnjigu(k1);
		biblioteka.dodajKnjigu(k2);
		
		List<Knjiga> knjige = biblioteka.vratiSveKnjige();
		
		assertEquals(2, knjige.size());
		assertTrue(knjige.contains(k1));
		assertTrue(knjige.contains(k2));
	}
	
	@Test
	void testDodajKnjiguNull() {
		assertThrows(NullPointerException.class, () -> biblioteka.dodajKnjigu(null));
	}
	
	@Test
	void testDodajKnjiguPostoji() {
		k2.setIsbn(111);
		
		biblioteka.dodajKnjigu(k1);
		
		assertThrows(IllegalArgumentException.class, () -> biblioteka.dodajKnjigu(k2));
	}
	

	@Test
	void testObrisiKnjigu() {
		biblioteka.dodajKnjigu(k1);
		biblioteka.dodajKnjigu(k2);
		
		biblioteka.obrisiKnjigu(k1);
		
		List<Knjiga> knjige = biblioteka.vratiSveKnjige();
		
		assertEquals(1, knjige.size());
		assertEquals(k2, knjige.get(0));
	}
	
	@Test
	void testObrisiKnjiguNull() {
		assertThrows(NullPointerException.class, () -> biblioteka.obrisiKnjigu(null));
	}
	
	@Test
	void testObrisiKnjiguNePostoji() {
		
		biblioteka.dodajKnjigu(k1);
		
		assertThrows(IllegalArgumentException.class, () -> biblioteka.obrisiKnjigu(k2));
	}
	

	@Test
	void testVratiSveKnjige() {
		biblioteka.dodajKnjigu(k1);
		biblioteka.dodajKnjigu(k2);
		
		List<Knjiga> lista = new ArrayList<Knjiga>();
		lista.add(k1);
		lista.add(k2);
		
		List<Knjiga> knjige = biblioteka.vratiSveKnjige();
		
		assertEquals(lista, knjige);
		
	}

	@Test
	void testPronadjiKnjiguNema() {
		k1.setNaslov("Naslov 1");
		k2.setNaslov("Naslov 2");
		
		List<Knjiga> rezultati = biblioteka.pronadjiKnjigu(null, 0, "Drina", null);
		
		assertEquals(0, rezultati.size());
		
	}
	
	@Test
	void testPronadjiKnjiguSve() {
		k1.setNaslov("Naslov 1");
		k2.setNaslov("Naslov 2");
		
		biblioteka.dodajKnjigu(k1);
		biblioteka.dodajKnjigu(k2);
		
		List<Knjiga> rezultati = biblioteka.pronadjiKnjigu(null, 0, "SLOV", null);
		
		assertEquals(2, rezultati.size());
		assertTrue(rezultati.contains(k1));
		assertTrue(rezultati.contains(k2));
		
	}
	
	@Test
	void testPronadjiKnjiguJedna() {
		k1.setNaslov("Naslov 1");
		k2.setNaslov("Drinaslo");
		
		biblioteka.dodajKnjigu(k1);
		biblioteka.dodajKnjigu(k2);
		
		List<Knjiga> rezultati = biblioteka.pronadjiKnjigu(null, 0, "SLOV", null);
		
		assertEquals(1, rezultati.size());
		assertTrue(rezultati.contains(k1));
		
	}
	
	
	@ParameterizedTest
	@CsvSource({
		"0", "-1"
	})
	void testPronadjiKnjiguArgumenti(int isbn) {
		assertThrows(IllegalArgumentException.class, () -> biblioteka.pronadjiKnjigu(null, isbn, null, null));
	}
	
	
	
	
	
	
	
}

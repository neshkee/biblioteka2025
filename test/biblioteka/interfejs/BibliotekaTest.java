package biblioteka.interfejs;

class BibliotekaTest extends BibliotekaInterfaceTest{

	@Override
	public BibliotekaInterface getInstance() {
		return new Biblioteka();
	}

	

}

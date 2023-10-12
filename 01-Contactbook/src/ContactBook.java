

public class ContactBook {
	static final int DEFAULT_SIZE = 100;

	private int counter;
	private Contact[] contacts;

	public ContactBook() {
		counter = 0;
		contacts = new Contact[DEFAULT_SIZE];
	}

	/** Pre: name != null */
	public boolean hasContact(String name) {
		return searchIndex(name) >= 0;
	}

	/** Pre: phone != null */
	public boolean hasContact(int phone) {
		return searchIndex(phone) >= 0;
	}

	public int getNumberOfContacts() {
		return counter;
	}

	/** Pre: name!= null && !hasContact(name) */
	public void addContact(String name, int phone, String email) {
		if (counter == contacts.length) 
			resize();
		contacts[counter] = new Contact(name, phone, email);
		counter++;
	}

	/** Pre: name != null && hasContact(name) */
	public void deleteContact(String name) {
		int index = searchIndex(name);		
		for(int i=index; i<counter; i++)
			contacts[i] = contacts[i+1];
		counter--;
	}

	/** Pre: name != null && hasContact(name) */
	public int getPhone(String name) {
		return contacts[searchIndex(name)].getPhone();
	}

	/** Pre: phone != null && hasContact(phone) */
	public String getName(int phone) {
		return contacts[searchIndex(phone)].getName();
	}

	/** Pre: name != null && hasContact(name) */
	public String getEmail(String name) {
		return contacts[searchIndex(name)].getEmail();
	}

	/** Pre: name != null && hasContact(name) */
	public void setPhone(String name, int phone) {
		contacts[searchIndex(name)].setPhone(phone);
	}

	/** Pre: name != null && hasContact(name) */
	public void setEmail(String name, String email) {
		contacts[searchIndex(name)].setEmail(email);
	}

	public ContactIterator iterator() {
		return new ContactIterator(contacts, counter);
	}
	
	private int searchIndex(String name) {
		int i = 0;
		int result = -1;
		boolean found = false;
		while (i<counter && !found)
			if (contacts[i].getName().equals(name))
				found = true;
			else
				i++;
		if (found) result = i;
		return result;
	}

	private int searchIndex(int phone) {
		int i = 0;
		int result = -1;
		boolean found = false;
		while (i<counter && !found)
			if (contacts[i].getPhone() == phone)
				found = true;
			else
				i++;
		if (found) result = i;
		return result;
	}

	//Verifica duplicados (adaptado do searchIndex()
	public boolean searchDupes() {
		int i = 0;
		boolean found = false;
		while (i<counter && !found) {
			int phone = contacts[i].getPhone();
			int j = i+1;
			while (j < counter && !found) {
				if (contacts[j].getPhone() == phone)
					found = true;
				else
					j++;
			}
			i++;
		}
		return found;
	}

	private void resize() {
		Contact tmp[] = new Contact[2*contacts.length];
		for (int i=0;i<counter; i++)
			tmp[i] = contacts[i];
		contacts = tmp;
	}
	
}

package cbook;

// Ao retirar a visibilidade public, esta classe deixa de estar visivel
// fora da package cBook, incrementando o encapsulamento
class ContactClass implements Contact {
	/**
	 * Nome do contacto.
	 */
	private String name;
	
	/**
	 * Telefone do contacto.
	 */
	private int phone;
	
	/**
	 * Email do contacto.
	 */
	private String email;

	/**
	 * Construtor por omissao
	 */
	public ContactClass(String name, int phone, String email) {
		this.name = name;
		this.phone = phone;
		this.email = email;
	}
	
	public ContactClass(String name) {
		this(name, 0, null);
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public int getPhone() {
		return phone;
	}
	
	@Override
	public String getEmail() {
		return email;
	}
	
	@Override
	public void setPhone(int phone) {
		this.phone = phone;
	}
	
	@Override
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true; 
		
		if (obj == null) return false;
		
		if (!(obj instanceof Contact)) return false;
			
		Contact other = (Contact) obj; 
		
		if (name == null) {
			if (other.getName() != null) return false;
			else return true;
		}
		else return name.equals(other.getName());
	}
	
	@Override
	public String toString() {
		return getName() + "; " + getEmail() + "; " + getPhone();
	}
}

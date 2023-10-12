package cbook;

import cbook.exceptions.*;

import java.util.Iterator;

public interface ContactBook {

	/**
	 * Verifica se ja existe um contacto com o nome dado
	 * @param name indica o nome do contacto 
	 * @return <code>true</code> se o contacto com nome <code>name</code> existe,
	 * 			<code>false</code> caso contrario
	 */
	boolean hasContact(String name);

	/**
	 * Devolve o numero de contactos
	 * @return o numero de contactos
	 */
	public int getNumberOfContacts();

	/**
	 * Adiciona um novo contacto 'a agenda
	 * @param name indica o nome do contacto 
	 * @param phone indica o numero de telefone do contacto
	 * @param email indica o email do contacto
	 * @exception ContactAlreadyExistsException exceção caso o contacto já exista
	 */
	void addContact(String name, int phone, String email) throws ContactAlreadyExistsException;

	/**
	 * Remove um contacto dado o nome
	 * @param name indica o nome do contacto a remover
	 * @exception ContactNotExistException exceção caso o contacto não exista
	 */
	void deleteContact(String name) throws ContactNotExistException;

	/**
	 * Consulta o numero de telefone de um contacto dado o seu nome
	 * @param name indica o nome do contacto a consultar o telefone
	 * @return o numero de telefone do contacto associado ao nome dado
	 * @exception ContactNotExistException exceção caso o contacto não exista
	 */
	int getPhone(String name) throws ContactNotExistException;

	/**
	 * Consulta o email de um contacto dado o seu nome
	 * @param name indica o nome do contacto a consultar o telefone
	 * @return o email do contacto associado ao nome dado
	 * @exception ContactNotExistException exceção caso o contacto não exista
	 */
	String getEmail(String name) throws ContactNotExistException;

	/**
	 * Altera o telefone de um dado contacto
	 * @param name indica o nome do contacto a moficar o telefone
	 * @param phone indica o novo numero de telefone
	 * @exception ContactNotExistException exceção caso o contacto não exista
	 */
	void setPhone(String name, int phone) throws ContactNotExistException;
	
	/**
	 * Altera o email de um dado contacto
	 * @param name indica o nome do contacto a moficar o email
	 * @param email indica o novo email
	 * @exception ContactNotExistException exceção caso o contacto não exista
	 */
	void setEmail(String name, String email) throws ContactNotExistException;

	/**
	 * Devolve um iterador para os contactos
	 * @return um iterador para os contactos
	 * @exception ContactBookEmptyException exceção caso o livro de contáctos não tenha nenhum contacto
	 */
	Iterator<Contact> listContacts() throws ContactBookEmptyException;
	
}

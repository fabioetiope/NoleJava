package com.comunenapoli.progetto.businessLogic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import com.comunenapoli.progetto.model.Ruolo;
import com.comunenapoli.progetto.model.Utente;
import com.comunenapoli.progetto.utils.Costanti;
import com.comunenapoli.progetto.utils.DataUtils;


public class BusinessLogicUtente {
	
	private UtenteDao utenteDao = null;
	private EntityManager entityManager = null;
	
	public BusinessLogicUtente(UtenteDao utenteDao, EntityManager entityManager) {
		this.utenteDao = utenteDao;
		this.entityManager = entityManager;
	}
	
	public BusinessLogicUtente(EntityManager entityManager, UtenteDao utenteDao) {
		this.utenteDao = utenteDao;
		this.entityManager = entityManager;
	}
	
	public Integer getRuolo(String user,String passw) {
		Utente utente = login(user, passw);
		Integer idRuolo = 0;
		
		if (utente != null) {
			idRuolo = checkRuolo(utente.getIdUtente());
		}
		
		return idRuolo;
		
	}
	
	public void create(Utente utente) {
		entityManager.getTransaction().begin();
		try {
			utenteDao.create(utente);
			entityManager.getTransaction().commit();
		}
		catch(Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}
	
	public Utente login(String username, String password) {
		entityManager.getTransaction().begin();
		Utente utente = null;
		try {
			List<Utente> listaUtente = utenteDao.findByUsernameAndPassword(username, password);
			boolean checkLista = listaUtente==null || listaUtente.isEmpty() || listaUtente.size()>1;
			if (!checkLista) {
				utente = listaUtente.get(0);
				String usernameDb = utente.getUsername();
				String passwordDb = utente.getPassword();
				boolean checkUserPass = usernameDb.equalsIgnoreCase(username) && passwordDb.equals(password);
				if (!checkUserPass) {
					utente = null;
				}
			}	
			entityManager.getTransaction().commit();
		}
		catch(Exception e){
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
		return utente;
	}
	
	public Integer checkRuolo(Integer idUtente) {
		Integer idRuolo = 0;
		idRuolo = utenteDao.findRuoloByIdUtente(idUtente);
		return idRuolo;
	}
	
	public boolean isNuovoUtente(Utente utente) {
		if (utente != null) {
			if (! utenteDao.checkUsername(utente.getUsername())) {
				create(utente);
				return true;
			}
		}
		return false;
	}
	
	public boolean isVerificato(Integer idUtente) {
		boolean checkVerifica = false;
		if (idUtente!=null) {
			checkVerifica = utenteDao.checkVerificaById(idUtente);
		}
		return checkVerifica;
	}
	
	public boolean verificaUtente(Utente utente, boolean haAccettato) {
		if (utente!=null) {
			Integer idUtente = utente.getIdUtente();
			if (!isVerificato(idUtente) && haAccettato) {
					utente.setIsVerificato(true);
					//utenteDao.updateVerifica(utente);
					update(utente);
					return true;
			}
		}
		return false;

	}
	

	public void update(Utente utente) {
		entityManager.getTransaction().begin();
		try {
			utenteDao.update(utente);
			entityManager.getTransaction().commit();
		}
		catch(Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}
	
	public Integer updateDatiPersonali(Utente utente, String nome, String cognome, String username, String password) {
		int counter = 0; 
		
		if (username != null && !username.isEmpty() && !username.equals(utente.getUsername())) {
			utente.setUsername(username);
			counter++;
		}
		if (password != null && !password.isEmpty() && !password.equals(utente.getPassword())) {
			utente.setPassword(password);
			counter++;
		}
		if (nome != null && !nome.isEmpty() && !nome.equals(utente.getNome())) {
			utente.setNome(nome);
			counter++;
		}
		if (cognome != null && !cognome.isEmpty() && !cognome.equals(utente.getCognome())) {
			utente.setCognome(cognome);
			counter++;
		}
		if (counter>0) {
			update(utente);
		}
		return counter;
	}
	
	public boolean updateRuolo(Utente utente, Integer idRuolo) {
		Integer idRuoloStaff = Costanti.ID_RUOLO_STAFF;
		String ruoloStaff = Costanti.RUOLO_STAFF;
		boolean checkPromozione = idRuolo == idRuoloStaff;
		if (checkPromozione) {
			Ruolo ruolo = new Ruolo();
			ruolo.setIdRuolo(idRuolo);
			ruolo.setNomeRuolo(ruoloStaff);
			utente.setRuoloUtente(ruolo);
			update(utente);
			return true;
		}
		return false;
	}
	
	
	public void deleteUtente(Integer id) {
		entityManager.getTransaction().begin();
		try {
			Utente utente = utenteDao.findUtentebyId(id);
			if (utente != null) {
				utenteDao.delete(utente);
				entityManager.getTransaction().commit();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}
	
	

	
	public Integer registrazione (Utente utente) throws Exception {
		if (DataUtils.dataNascita(utente.getDataNascita()) && !utenteDao.checkUsername(utente.getUsername())) {
			create(utente);
			return Costanti.REGISTRAZIONE_VALIDA;
		} else if (! DataUtils.dataNascita(utente.getDataNascita())){
			return Costanti.REGISTRAZIONE_FALLITA_ETA;
		} else {
			return Costanti.REGISTRAZIONE_FALLITA_UTENTE_GIA_ESISTENTE;
		}
		
	}
	
	public boolean isEmailValid (String email) {
		String chiocciola = "@";
		String dot =".";
		
		if(email.contains(chiocciola) && email.contains(dot)) {
			return true;
		}
		
		return false;
	}
	
	public List<Utente> listaUtentiNonVerificati(){
		return utenteDao.findByIsVerificato();
	}
	
	
	public Utente getUtenteById(Integer idUtente) {
		return utenteDao.findUtentebyId(idUtente);
	}
	
	public List<Utente> getListaUtenti(){
		List<Utente> utenti = utenteDao.retrieve();
		return utenti;
	}
	
	
	public Utente getUtenteByUsername(String username) {
		return utenteDao.findUtenteByUsername(username);
	}
	
	
	
	
}

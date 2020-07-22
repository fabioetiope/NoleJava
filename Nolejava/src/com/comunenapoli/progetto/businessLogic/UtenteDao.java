package com.comunenapoli.progetto.businessLogic;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.comunenapoli.progetto.model.Ruolo;
import com.comunenapoli.progetto.model.Utente;

public class UtenteDao implements DaoInterface<Utente> {

	private EntityManager manager = null;
	
	public UtenteDao()
	{
		this(null);
	}
	
	public UtenteDao(EntityManager entityManager)
	{
		setManager(entityManager);
	}

	public EntityManager getManager() {
		return manager;
	}

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}
	
	@Override
	public void create(Utente utente) {
		manager.persist(utente); 
	}

	@Override
	public List<Utente> retrieve() {
		List<Utente> utenti = manager.createQuery("from Utente",Utente.class).getResultList();
		return utenti;
	}

	@Override
	public void update(Utente utente) {
		manager.persist(utente);
	}

	@Override
	public void delete(Utente utente) {
		manager.remove(utente);
	}
	
	public List<Utente> findByUsernameAndPassword(String username,String password){
		return manager.createQuery("select u from Utente u where u.username = :x and u.password = :y",Utente.class).
				setParameter("x",username).setParameter("y",password).getResultList();
	}
	
	public List<Utente> findByRuolo(Ruolo ruolo){
		return manager.createQuery("select u from Utente u where u.ruolo = :x ",Utente.class).
				setParameter("x",ruolo).getResultList();
	}
	
	public Integer findRuoloByIdUtente(Integer idUtente) {
		Utente utente = manager.createQuery("select u from Utente u where u.idUtente = :x ",Utente.class).
				setParameter("x",idUtente).getResultList().stream().findFirst().orElse(null);
		return utente.getRuoloUtente().getIdRuolo();
	}
	
	/* Controlla se l'utente è presente nel sistema, se è presente ritorna true altrimenti false */
	public boolean checkUsername(String username){
		TypedQuery<Utente> query = manager.createQuery("select u from Utente u where u.username = :x",Utente.class);
		Utente utente = query.setParameter("x",username).getResultList().stream().findFirst().orElse(null);
		boolean checkUtente = utente!=null;
		return checkUtente;
	}
	
	public boolean checkVerificaById(Integer idUtente) {
		Utente utente = manager.createQuery("select u from Utente u where u.idUtente = :x ",Utente.class).
				setParameter("x",idUtente).getResultList().stream().findFirst().orElse(null);
		return utente.getIsVerificato();
	}

	public Utente findUtentebyId(Integer id) {
		Utente utente = manager.createQuery("select u from Utente u where u.idUtente = :x ",Utente.class).
				setParameter("x",id).getResultList().stream().findFirst().orElse(null);
		return utente;
	}
	
	public List<Utente> findByIsVerificato(){
		return manager.createQuery("select u from Utente u where u.isVerificato = 0",Utente.class).getResultList();
	}
	
	
	public Utente findUtenteByUsername(String username) {
		return manager.createQuery("select u from Utente u where u.username = :x ",Utente.class).
				setParameter("x",username).getResultList().stream().findFirst().orElse(null);
	}
	
	
	
//	public void updateVerifica(Utente utente) {
//		boolean isVerificato = utente.getIsVerificato();
//		Integer idUtente = utente.getIdUtente();
//		manager.createQuery("update Utente u set u.isVerificato =: y where u.idUtente= :x").setParameter("y",isVerificato).setParameter("x", idUtente).executeUpdate();
//	}
	
	
		
}
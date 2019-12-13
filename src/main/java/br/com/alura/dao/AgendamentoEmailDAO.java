package br.com.alura.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.alura.entity.AgendamentoEmail;

@Stateless
public class AgendamentoEmailDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	public List<AgendamentoEmail> listarAgendamentosEmail() {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<AgendamentoEmail> criteriaQuery = criteriaBuilder.createQuery(AgendamentoEmail.class);
		Root<AgendamentoEmail> root = criteriaQuery.from(AgendamentoEmail.class);
		criteriaQuery.select(root);
		
		return entityManager.createQuery(criteriaQuery).getResultList();
	}
	
	public void salvarAgendamentoEmail(AgendamentoEmail agendamentoEmail) {
		entityManager.persist(agendamentoEmail);
	}
	
	public List<AgendamentoEmail> listarAgendamentosEmailPorEmail(String email) {
		
		TypedQuery<AgendamentoEmail> query = entityManager.createQuery(
				"select a from AgendamentoEmail a where a.email = :email and a.enviado = false", 
				AgendamentoEmail.class);
		query.setParameter("email", email);
		
		return query.getResultList();
	}
	
	public List<AgendamentoEmail> listarAgendamentosEmailNaoEnviados() {
		
		TypedQuery<AgendamentoEmail> query = entityManager.createQuery(
				"select a from AgendamentoEmail a where a.enviado = false", 
				AgendamentoEmail.class);
		
		return query.getResultList();
	}
	
	public void atualizarAgendamentoEmail(AgendamentoEmail agendamentoEmail) {
		entityManager.merge(agendamentoEmail);
	}
	
}

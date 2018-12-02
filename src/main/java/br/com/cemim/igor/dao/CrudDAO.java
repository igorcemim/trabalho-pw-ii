package br.com.cemim.igor.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import br.com.cemim.igor.exception.ErroSistema;

import java.util.List;

import javax.persistence.EntityManager;

abstract public class CrudDAO<T> implements DAO<T> {

    private EntityManagerFactory emf;

    protected EntityManager em;

    public CrudDAO() {
        emf = Persistence.createEntityManagerFactory("trabalho-pwii");
        em = emf.createEntityManager();
    }

    protected EntityManager getEm()  {
        return emf.createEntityManager();
    }

    protected abstract Class<T> getEntidade();

    public T salvar(T elemento) throws ErroSistema {
        try {
            em = getEm();
            em.getTransaction().begin();
            em.merge(elemento);
            em.getTransaction().commit();
            return elemento;
        } catch (Exception ex) {
            throw new ErroSistema("Erro ao salvar o registro.", ex);
        } finally {
            em.close();
        }
    }

    public void apagar(T elemento) throws ErroSistema {
        try {
            em = getEm();
            em.getTransaction().begin();
            elemento = em.merge(elemento);
            em.remove(elemento);
            em.getTransaction().commit();
        } catch(Exception ex) {
            throw new ErroSistema("Erro ao apagar o registro.", ex);
        } finally {
            em.close();
        }
    }

    public List<T> buscar() throws ErroSistema {
        try {
            em = getEm();
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<T> criteria = builder.createQuery(getEntidade());
            criteria.from(getEntidade());
            return em.createQuery(criteria).getResultList();
        } catch (Exception ex) {
            throw new ErroSistema("Erro ao buscar os registros.", ex);
        }
    }

    public T buscarPorCodigo(int codigo) throws ErroSistema {
        try {
            em = getEm();
            return em.find(getEntidade(), codigo);
        } catch (Exception ex) {
            throw new ErroSistema("Erro ao buscar o registro.", ex);
        }
    }
}

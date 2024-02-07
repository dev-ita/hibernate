package br.com.hibernate.dao;

import br.com.hibernate.util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
public class GenericDao<E> {
    private EntityManager entityManager = HibernateUtil.getEntityManager();
    public void salvar(E entity) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(entity);
        entityTransaction.commit();
    }

    public E updateMerge(E entity) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        E entidadeSalva = entityManager.merge(entity);
        entityTransaction.commit();
        return entidadeSalva;
    }

    public E consulta(E entidade) {
        Object id = HibernateUtil.getPrimaryKey(entidade);
        return (E) entityManager.find(entidade.getClass(), id);
    }

    public E consulta(Long id, Class<E> entidade) {
        return entityManager.find(entidade, id);
    }

    public void deletarPorId(E entidade) {
        Object id = HibernateUtil.getPrimaryKey(entidade);
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        System.out.println(entidade.getClass().getSimpleName());
        entityManager.createNativeQuery(
                "delete from " + entidade.getClass().getSimpleName().toLowerCase() + " where id = " + id).executeUpdate();
        entityTransaction.commit();
    }

    public List<E> listar (Class<E> entidade) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        List<E> lista = entityManager.createQuery("from " + entidade.getName()).getResultList();
        entityTransaction.commit();
        return lista;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}

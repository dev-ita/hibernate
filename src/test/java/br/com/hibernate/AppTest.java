package br.com.hibernate;

import br.com.hibernate.dao.GenericDao;
import br.com.hibernate.model.UsuarioPessoa;
import org.junit.Test;
import java.util.List;

public class AppTest {
    @Test
    public void testeHibernateUtil() {
        UsuarioPessoa usuario = new UsuarioPessoa("Ítalo", "Oliveira", "italo.ods@hotmail.com", "italo1071", "isabele123");
        GenericDao<UsuarioPessoa> genericDao = new GenericDao<>();
        genericDao.salvar(usuario);
        System.out.println(usuario.getClass());
    }

    @Test
    public void testeConsulta() {
        GenericDao<UsuarioPessoa> daoGeneric = new GenericDao<>();
        UsuarioPessoa usuarioPessoa = new UsuarioPessoa();
        usuarioPessoa.setId(3L);
        usuarioPessoa = daoGeneric.consulta(usuarioPessoa);
        System.out.println(usuarioPessoa);

        System.out.println(daoGeneric.consulta(1L, UsuarioPessoa.class));
    }

    @Test
    public void testeUpdate() {
        GenericDao<UsuarioPessoa> daoGeneric = new GenericDao<>();
        UsuarioPessoa usuario = daoGeneric.consulta(3L, UsuarioPessoa.class);
        usuario.setSenha("Senha alterada");
        usuario = daoGeneric.updateMerge(usuario);
        System.out.println(usuario);
        System.out.println(UsuarioPessoa.class);
    }

    @Test
    public void testeDelete() {
        GenericDao<UsuarioPessoa> daoGeneric = new GenericDao<UsuarioPessoa>();
        UsuarioPessoa usuario = daoGeneric.consulta(4L, UsuarioPessoa.class);
        daoGeneric.deletarPorId(usuario);
        System.out.println(usuario);
    }

    @Test
    public void testeListar() {
        GenericDao<UsuarioPessoa> daoGeneric = new GenericDao<>();
        List<UsuarioPessoa> listaUsuariosList = daoGeneric.listar(UsuarioPessoa.class);
        listaUsuariosList.forEach(System.out::println);
    }

    @Test
    public void testeQueryList() {
        GenericDao<UsuarioPessoa> genericDao = new GenericDao<>();
        List list = genericDao.getEntityManager().createQuery("from " + UsuarioPessoa.class.getSimpleName()).getResultList();
        list.forEach(System.out::println);
    }

    @Test
    public void testeQueryListMaxResult() {
        GenericDao<UsuarioPessoa> genericDao = new GenericDao<>();
        List list = genericDao.getEntityManager().createQuery("from " + UsuarioPessoa.class.getSimpleName() + " order by nome")
                .setMaxResults(4)
                .getResultList();
        list.forEach(System.out::println);
    }

    @Test
    public void testeQueryListParameter() {
        GenericDao<UsuarioPessoa> genericDao = new GenericDao<>();
        List<UsuarioPessoa> list = genericDao.getEntityManager().createQuery("from UsuarioPessoa where nome = :nome or sobrenome = :sobrenome")
                .setParameter("nome", "Isabele")
                .setParameter("sobrenome", "Oliveira")
                .getResultList();
        list.forEach(System.out::println);
    }

    @Test
    public void testeQuerySomaId() {
        GenericDao<UsuarioPessoa> genericDao = new GenericDao<>();
        Long somaIds = (Long) genericDao.getEntityManager().createQuery("select sum(u.id) from UsuarioPessoa u").getSingleResult();
        System.out.println("ids somados = " + somaIds);
    }

    @Test
    public void testeNamedQueries() {
        GenericDao<UsuarioPessoa> genericDao = new GenericDao<>();
        List<UsuarioPessoa> usuarioPessoasList = genericDao.getEntityManager().createNamedQuery("UsuarioPessoa.findAll").getResultList();
        usuarioPessoasList.forEach(System.out::println);

        Long somaIds = (Long) genericDao.getEntityManager().createNamedQuery("UsuarioPessoa.sumIds").getSingleResult();
        System.out.println("Resultado da soma dos ids: " + somaIds);

        UsuarioPessoa buscaPorNome = (UsuarioPessoa) genericDao.getEntityManager().createNamedQuery("UsuarioPessoa.buscaPorNome").setParameter("nome", "Isabele").getSingleResult();
        System.out.println(buscaPorNome);
    }
}
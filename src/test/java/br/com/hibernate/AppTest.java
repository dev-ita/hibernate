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
        listaUsuariosList.forEach((element) -> {
            System.out.println(element);
        });
    }
}
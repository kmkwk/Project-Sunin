package com.ssafy.sunin.repository;

import com.ssafy.sunin.domain.Test;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TestRepository {

    private final EntityManager em;

    public List<Test> findAll() {
        return em.createQuery("select t from Test t", Test.class).getResultList();
    }

}

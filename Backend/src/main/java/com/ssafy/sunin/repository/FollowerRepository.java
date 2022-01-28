package com.ssafy.sunin.repository;

import com.ssafy.sunin.domain.Follower;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowerRepository extends JpaRepository<Follower,Long>, FollowerRepositoryCustom {
}

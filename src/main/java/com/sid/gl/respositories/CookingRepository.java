package com.sid.gl.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CookingRepository<T,K> extends JpaRepository<T, K> {
   T findByName(String name);
}

package com.venilry.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.venilry.web.pojo.Article;
import com.venilry.web.pojo.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	@Query(value = "select name from role where id = :id", nativeQuery = true)
	String findNameById(Integer id);
}

package com.venilry.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.venilry.web.pojo.Role;
import com.venilry.web.pojo.User_role;

public interface User_roleRepository extends JpaRepository<User_role, Integer> {
	@Query(value = "select role_id from user_role where user_id = :user_id", nativeQuery = true)
	List<Integer> findAllRoleIdByUserId(Integer user_id);
}

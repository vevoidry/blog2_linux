package com.venilry.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.venilry.web.pojo.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {

}

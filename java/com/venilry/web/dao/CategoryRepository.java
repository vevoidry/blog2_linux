package com.venilry.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.venilry.web.pojo.Article;
import com.venilry.web.pojo.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}

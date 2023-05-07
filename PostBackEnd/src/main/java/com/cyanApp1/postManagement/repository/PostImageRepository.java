package com.cyanApp1.postManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.cyanApp1.postManagement.model.PostImages;

public interface PostImageRepository extends JpaRepository<PostImages, Integer> {

}

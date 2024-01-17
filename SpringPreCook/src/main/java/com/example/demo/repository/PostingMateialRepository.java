package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.PostingMaterial;

public interface PostingMateialRepository extends JpaRepository<PostingMaterial, Integer> {

}

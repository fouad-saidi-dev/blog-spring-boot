package com.blog.repositories;

import com.blog.entities.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FIleRepository extends JpaRepository<File,Long> {
}

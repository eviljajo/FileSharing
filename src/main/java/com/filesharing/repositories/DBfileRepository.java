package com.filesharing.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.filesharing.model.DBfile;

@Repository
public interface DBfileRepository extends JpaRepository<DBfile, String>{

}

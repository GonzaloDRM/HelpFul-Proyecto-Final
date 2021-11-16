package com.helpfull.egg.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.helpfull.egg.entities.Match;

@Repository
public interface MatchRepository extends JpaRepository<Match,String> {

}

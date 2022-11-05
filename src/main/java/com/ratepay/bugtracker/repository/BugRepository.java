package com.ratepay.bugtracker.repository;

import com.ratepay.bugtracker.model.Bug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BugRepository extends JpaRepository<Bug, String>, JpaSpecificationExecutor<Bug> {
}

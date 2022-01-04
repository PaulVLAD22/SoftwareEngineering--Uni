package com.university.ilock.Repository;

import com.university.ilock.model.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface AuditRepository extends JpaRepository<Audit,Long> {
    @Query(value = "select * from audit order by audit.timestamp desc limit 5",nativeQuery = true)
    List<Audit> getLastFive();
}

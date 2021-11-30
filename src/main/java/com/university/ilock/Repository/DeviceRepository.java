package com.university.ilock.Repository;

import com.university.ilock.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device,Long> {
    Device getById(long id);
}

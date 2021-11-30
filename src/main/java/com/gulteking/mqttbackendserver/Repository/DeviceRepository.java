package com.gulteking.mqttbackendserver.Repository;

import com.gulteking.mqttbackendserver.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device,Long> {
    Device getById(long id);
}

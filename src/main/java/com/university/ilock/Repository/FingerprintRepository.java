package com.university.ilock.Repository;

import com.university.ilock.dtos.FingerprintUnlockDto;
import com.university.ilock.model.Device;
import com.university.ilock.model.Fingerprint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FingerprintRepository extends JpaRepository<Fingerprint,Long> {
    Fingerprint getById(long id);
    List<Fingerprint> getFingerprintsByDeviceId(long deviceId);
}

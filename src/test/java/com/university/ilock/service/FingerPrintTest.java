package com.university.ilock.service;

import com.machinezoo.sourceafis.FingerprintImage;
import com.machinezoo.sourceafis.FingerprintImageOptions;
import com.machinezoo.sourceafis.FingerprintTemplate;
import com.university.ilock.Repository.DeviceRepository;
import com.university.ilock.dtos.FingerprintUnlockDto;
import com.university.ilock.model.Device;
import com.university.ilock.model.Fingerprint;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FingerPrintTest {
    @Mock
    private DeviceRepository deviceRepository;
    @Mock
    private DeviceService deviceService;
    @InjectMocks
    private FingerprintService fingerprintService;


    long deviceId;
    String fp1;
    String fp2;

    @Before
    public void setUp() {

        deviceId = 1;

        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        String basepath = "src/test/resources/fingerprints/";
        byte[] fileContent = new byte[0];
        try {
            fileContent = FileUtils.readFileToByteArray(new File(basepath + "candidate.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String encodedString = Base64.getEncoder().encodeToString(fileContent);

        try {
            fileContent = FileUtils.readFileToByteArray(new File(basepath + "candidate2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String encodedString2 = Base64.getEncoder().encodeToString(fileContent);

        fp1 = encodedString;
        fp2 = encodedString2;
    }

    @Test
    public void test_isEqual() {
        ArrayList<Fingerprint> fingerprints = new ArrayList<Fingerprint>();
        fingerprints.add(Fingerprint.builder().content(fp1).build());

        when(deviceRepository.getById(deviceId)).thenReturn(
                Device.builder().fingerprints(fingerprints).build()
        );// when "method" is called then return "value"


        FingerprintUnlockDto fpunlock = FingerprintUnlockDto.builder().id(deviceId).fingerprint(fp1).build();
        try {
            assertTrue(fingerprintService.Validate(fpunlock));
        } catch (IOException e) {
            assertTrue(false);
        }


    }

    @Test
    public void test_isNotEqual() {
        ArrayList<Fingerprint> fingerprints = new ArrayList<Fingerprint>();
        fingerprints.add(Fingerprint.builder().content(fp1).build());

        when(deviceRepository.getById(deviceId)).thenReturn(
                Device.builder().fingerprints(fingerprints).build()
        );// when "method" is called then return "value"


        FingerprintUnlockDto fpunlock = FingerprintUnlockDto.builder().id(deviceId).fingerprint(fp2).build();
        try {
            assertTrue(!fingerprintService.Validate(fpunlock));
        } catch (IOException e) {
            assertTrue(true);
        }

    }
}

package com.university.ilock.service;

import com.university.ilock.Repository.*;
import com.university.ilock.model.*;
import org.junit.*;
import org.junit.runner.*;
import org.mockito.*;
import org.mockito.junit.*;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PINServiceTest {
    @Mock
    private DeviceRepository deviceRepository;
    @InjectMocks
    private PINService pinService;

    long deviceId;
    String pin;

    @Before
    public void setUp() {
        deviceId = 1;
        pin = "141414";
    }

    @Test
    public void test_validate_141414() {
        when(deviceRepository.getById(deviceId)).thenReturn(
                Device.builder().pin(pin).build()
        );// when "method" is called then return "value"
        assertTrue(pinService.validatePin(deviceId, pin));
    }

    @Test
    public void test_change_pin(){
        Device device = new Device();
        when(deviceRepository.getById(deviceId)).thenReturn(
                device
        );
        pinService.updatePINForDevice(deviceId,pin);
        assertTrue(pinService.validatePin(deviceId,pin));
    }
}

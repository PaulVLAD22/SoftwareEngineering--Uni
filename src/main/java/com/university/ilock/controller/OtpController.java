package com.university.ilock.controller;

import com.university.ilock.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class OtpController {

    @Autowired
    public OtpService otpService;


    @GetMapping(value = "/generateOtp")
    public Integer generateOtp(@RequestParam long deviceId) {
        int otp = otpService.generateOTP(deviceId);
        System.out.println(otp);
        //trimite email cu otp
        return otp;
    }

    @GetMapping(value="/valideOtp")
    public String validateOtp(@RequestParam long deviceId,
                              @RequestParam int otpnum) {

        final String SUCCESS = "Entered Otp is valid";

        final String FAIL = "Entered Otp is NOT valid. Please Retry!";

        if (otpnum >= 0) {
            int serverOtp = otpService.getOtp(deviceId);

            if (serverOtp > 0) {
                if (otpnum == serverOtp) {
                    otpService.clearOTP(deviceId);
                    return ("Entered Otp is valid");
                } else {
                    return SUCCESS;
                }
            } else {
                return FAIL;
            }
        } else {
            return FAIL;
        }
    }
}


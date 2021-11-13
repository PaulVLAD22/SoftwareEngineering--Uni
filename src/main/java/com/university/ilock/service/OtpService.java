package com.university.ilock.service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.google.common.cache.LoadingCache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;

@Service
public class OtpService {

    //cache based on username and OPT MAX 8
    private static final Integer EXPIRE_MINS = 5;

    private LoadingCache<Long, Integer> otpCache;

    public OtpService(){
        super();
        otpCache = CacheBuilder.newBuilder().
                expireAfterWrite(EXPIRE_MINS, TimeUnit.MINUTES).build(new CacheLoader<Long, Integer>() {
                    @Override
                    public Integer load(Long aLong) {
                        return 0;
                    }
                });
    }

    //This method is used to push the opt number against Key. Rewrite the OTP if it exists
    //Using user id  as key
    public int generateOTP(long deviceId){

        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        otpCache.put(deviceId, otp);
        return otp;
    }

    //This method is used to return the OPT number against Key->Key values is username
    public int getOtp(long deviceId){
        try{
            return otpCache.get(deviceId);
        }catch (Exception e){
            return 0;
        }
    }

    //This method is used to clear the OTP catched already
    public void clearOTP(long deviceId){
        otpCache.invalidate(deviceId);
    }
}

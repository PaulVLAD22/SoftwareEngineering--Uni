package com.university.ilock.service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.google.common.cache.LoadingCache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;

@Service
public class OtpService {

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

    public int generateOTP(long deviceId){

        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        otpCache.put(deviceId, otp);
        return otp;
    }

    public int getOtp(long deviceId){
        try{
            return otpCache.get(deviceId);
        }catch (Exception e){
            return 0;
        }
    }

    public void clearOTP(long deviceId){
        otpCache.invalidate(deviceId);
    }
}

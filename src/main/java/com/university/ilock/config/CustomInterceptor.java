package com.university.ilock.config;

import com.university.ilock.service.DeviceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.io.IOException;

public class CustomInterceptor implements HandlerInterceptor {
    private final DeviceService deviceService;

    public CustomInterceptor(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws IOException {
//
//        long deviceId = Long.parseLong(request.getParameter("deviceId"));
//
//        if (!deviceService.validateDeviceId(deviceId)) {
//            response.sendError(HttpServletResponse.SC_NOT_FOUND, "There is no device with device id " + deviceId);
//
//            return false;
//        }
        return true;
    }
}

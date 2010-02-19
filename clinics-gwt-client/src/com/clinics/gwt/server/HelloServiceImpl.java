package com.clinics.gwt.server;

import org.springframework.stereotype.Service;

import com.clinics.gwt.shared.HelloService;

@Service("hello")
public class HelloServiceImpl implements HelloService {

    public String sayHello() {
        return "Hello stranger!";
    }

}

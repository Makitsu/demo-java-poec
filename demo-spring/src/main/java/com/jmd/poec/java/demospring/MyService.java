package com.jmd.poec.java.demospring;

import com.jmd.poec.java.demospring.sub.MySubService;
import com.jmd.poec.java.demospring.sub.OtherComponent;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    private final MySubService mySubService;

    private final OtherComponent otherComponent;

    public MyService(MySubService mySubService, OtherComponent otherComponent) {
        this.mySubService = mySubService;
        this.otherComponent = otherComponent;
    }

}

package com.olga.tests;

import com.olga.app.Application;
import org.junit.After;
import org.junit.Before;

/**
 * Created by User on 15.05.2018.
 */
public class TestBasePO {
    public static ThreadLocal<Application> tlApp = new ThreadLocal<>();
    public Application app;

    @Before
    public void start() {
        if (tlApp.get() != null) {
            app = tlApp.get();
            return;
        }

        app = new Application();
        tlApp.set(app);

        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> { app.quit(); app = null; }));
    }
}

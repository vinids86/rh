package com.xerpa.acme;

import com.xerpa.acme.utils.Load;
import org.junit.Before;

public abstract class BaseTest {

    @Before
    public void setUpTest() {
        final Load load = new Load();
        String configJson = "config_test.json";
        String timeclockEntriesJson = "timeclock_entries_test.json";
        load.initializeSingleton(configJson, timeclockEntriesJson);
        this.setUp();
    }

    public abstract void setUp();
}

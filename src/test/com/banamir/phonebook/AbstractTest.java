package com.banamir.phonebook;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
public class AbstractTest extends TestCase {

    @Before
    @Override
    public void setUp() throws Exception {

        super.setUp();

        MockitoAnnotations.initMocks(this);
    }


}

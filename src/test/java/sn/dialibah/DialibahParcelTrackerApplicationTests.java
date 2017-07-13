package sn.dialibah;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DialibahParcelTrackerApplicationTests {

	@Test
	public void contextLoads() {
		assertNotNull("fake test", new Object());
	}

}

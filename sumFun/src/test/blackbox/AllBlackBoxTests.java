package blackbox;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ GetMinutesTest.class, GetSecondsTest.class, UpdateTimeTest.class })
public class AllBlackBoxTests {

}

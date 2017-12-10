package whitebox;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestAddScore.class, TestUpdateTime.class })
public class AllWhiteBoxTests {

}

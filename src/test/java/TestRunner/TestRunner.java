package TestRunner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features=".//Features/",
        glue="stepDefinitions",
        dryRun = false,
      //  monochrome=true,
      //  tags= {"@sanity"},
        plugin= {"pretty","html:test-output"}
)

public class TestRunner {
}

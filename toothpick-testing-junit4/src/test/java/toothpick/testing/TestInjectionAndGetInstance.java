package toothpick.testing;

import javax.inject.Inject;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import toothpick.Toothpick;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class TestInjectionAndGetInstance {
  @Rule public ToothPickRule toothPickRule = new ToothPickRule(this, "Foo");
  EntryPoint entryPoint;

  @After
  public void tearDown() throws Exception {
    //needs to be performed after test execution
    //not before as rule are initialized before @Before
    Toothpick.reset();
  }

  @Test
  public void testGetInstance() throws Exception {
    //GIVEN
    //WHEN
    entryPoint = toothPickRule.getInstance(EntryPoint.class);
    //THEN
    assertThat(entryPoint, notNullValue());
    assertThat(entryPoint.dependency, notNullValue());
  }

  @Test
  public void testInject() throws Exception {
    //GIVEN
    EntryPoint entryPoint = new EntryPoint();

    //WHEN
    toothPickRule.inject(entryPoint);

    //THEN
    assertThat(entryPoint, notNullValue());
    assertThat(entryPoint.dependency, notNullValue());
  }

  static class EntryPoint {
    @Inject Dependency dependency;
  }

  static class Dependency {
    @Inject Dependency() {
    }
  }
}

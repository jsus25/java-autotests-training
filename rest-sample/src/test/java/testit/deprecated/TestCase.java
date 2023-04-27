package testit.deprecated;

import java.util.Objects;

public class TestCase {

  private int globalId;
  private String name;

  public int getGlobalId() {
    return globalId;
  }

  public TestCase withId(int globalId) {
    this.globalId = globalId;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TestCase testCase = (TestCase) o;
    return globalId == testCase.globalId && Objects.equals(name, testCase.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(globalId, name);
  }


  public TestCase withName(String name) {
    this.name = name;
    return this;
  }
}

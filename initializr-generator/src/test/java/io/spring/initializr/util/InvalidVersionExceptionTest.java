package io.spring.initializr.util;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class InvalidVersionExceptionTest {

  @Test
  public void testInvalidVersionException() {
    InvalidVersionException invalidVersionException = new InvalidVersionException();
    assertThat(invalidVersionException, notNullValue());
  }

  @Test
  public void testInvalidVersionExceptionMessage() {
    InvalidVersionException invalidVersionException = new InvalidVersionException("Test");
    assertThat(invalidVersionException.getMessage(), equalTo("Test"));
  }

  @Test
  public void testInvalidVersionExceptionThrowable() {
    InvalidVersionException invalidVersionException =
        new InvalidVersionException(new IllegalStateException("CauseException"));
    assertThat(invalidVersionException.getCause(), isA(Throwable.class));
  }

  @Test
  public void testInvalidVersionExceptionMessageAndThrowable() {
    InvalidVersionException invalidVersionException =
        new InvalidVersionException("Test", new IllegalStateException("CauseException"));
    assertThat(invalidVersionException.getMessage(), equalTo("Test"));
    assertThat(invalidVersionException.getCause(), isA(Throwable.class));
  }
}

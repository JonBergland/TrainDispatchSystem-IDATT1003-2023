package edu.ntnu.stud;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Input-class")
class TestInput {
  Input input = new Input();

  @Nested
  @DisplayName("Test of tryInt")
  class inputTryIntMethod {

    @Test
    @DisplayName("Positive test of tryInt")
    void positiveTryInt() {
      String number = "1";
      assertEquals(Integer.parseInt(number), input.tryInt(number, 0));
    }

    @Test
    @DisplayName("Test of nonInteger in tryInt")
    void nonIntegerTryInt() {
      String nonInteger = "nonInteger";
      int dummyValue = 0;
      assertEquals(dummyValue, input.tryInt(nonInteger, dummyValue));
    }
  }
}

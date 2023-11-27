package edu.ntnu.stud.Exceptions;

public class TableAddException extends Exception{
  public TableAddException(String errorMessage) {
    super(errorMessage);
  }
  public TableAddException() {
    super("Something wrong happened during the creation of the train-departure");
  }
}


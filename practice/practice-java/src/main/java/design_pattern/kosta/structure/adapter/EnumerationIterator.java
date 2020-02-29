package design_pattern.kosta.structure.adapter;

import java.util.Enumeration;
import java.util.Iterator;

public class EnumerationIterator implements AIterator {
  // TODO

  Enumeration enumber; // adaptee  / old code


  @Override
  public boolean hasNext() {
    return enumber.hasMoreElements();
  }

  @Override
  public Object next() {
    return enumber.nextElement();
  }

  @Override
  public void remove() {
    throw new UnsupportedOperationException("지원안해 !");
  }

}

package com.uonagent.MyArrayList;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyArrayList<E> implements List<E> {
  private Object[] array;
  private static Object[] defaultArray = {};
  private int size;

  private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

  public MyArrayList() {
    array = new Object[10];
  }

  public MyArrayList(int capacity) {
    if (capacity == 0) {
      array = defaultArray;
    } else if (capacity > 0) {
      array = new Object[capacity];
    } else {
      throw new IllegalArgumentException("Illegal capacity");
    }
  }

  public MyArrayList(Object[] data) {
    array = new Object[data.length];
    System.arraycopy(data, 0, array, 0, data.length);
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public boolean contains(Object o) {
    return indexOf(o) != -1;
  }

  @Override
  public Iterator<E> iterator() {
    return null;
  }

  @Override
  public Object[] toArray() {
    return array.clone();
  }

  @Override
  public <T> T[] toArray(T[] a) {
    return null;
  }

  @Override
  public boolean add(E e) {
    checkCapacityAndReallocate(size + 1);
    array[size++] = e;
    return true;
  }

  @Override
  public boolean remove(Object o) {
    if (o == null) {
      for (int i = 0; i < size; ++i) {
        if (array[i] == null) {
          removeByIndex(i);
          return true;
        }
      }
    } else {
      for (int i = 0; i < size; ++i) {
        if (array[i].equals(o)) {
          removeByIndex(i);
          return true;
        }
      }
    }
    return false;
  }

  private void removeByIndex(int index) {
    checkRange(index);
    array[index] = null;
    size--;
    makeItTogether(index);
  }

  private void makeItTogether(int index) {
    if (index != size - 1) {
      Object[] together = new Object[size];
      if (index == 0) {
        System.arraycopy(array, 1, together, 0, size);
        array = together;
      } else {
        System.arraycopy(array, 0, together, 0, index);
        System.arraycopy(array, index + 1, together, index, size - index);
        array = together;
      }
    }
  }

  @Override
  public boolean containsAll(Collection<?> c) {
    return false;
  }

  @Override
  public boolean addAll(Collection<? extends E> c) {
    return true;
  }

  @Override
  public boolean addAll(int index, Collection<? extends E> c) {
    return false;
  }

  @Override
  public boolean removeAll(Collection<?> c) {
    return false;
  }

  @Override
  public boolean retainAll(Collection<?> c) {
    return false;
  }

  @Override
  public void clear() {
    for (int i = 0; i < size; ++i) {
      array[i] = null;
    }
    size = 0;
  }

  @Override
  public E get(int index) {
    checkRange(index);
    return getByIndex(index);
  }

  @Override
  public E set(int index, E element) {
    checkRange(index);
    E r = getByIndex(index);
    array[index] = element;
    return r;
  }

  @Override
  public void add(int index, E element) {

  }

  @Override
  public E remove(int index) {
    checkRange(index);
    E r = getByIndex(index);
    removeByIndex(index);
    return r;
  }

  @Override
  public int indexOf(Object o) {
    for (int i = 0; i < size; ++i) {
      if (array[i].equals(o)) {
        return i;
      }
    }
    return -1;
  }

  @SuppressWarnings("unchecked")
  private E getByIndex (int index) {
    return (E) array[index];
  }

  @Override
  public int lastIndexOf(Object o) {
    return 0;
  }

  @Override
  public ListIterator<E> listIterator() {
    return null;
  }

  @Override
  public ListIterator<E> listIterator(int index) {
    return null;
  }

  @Override
  public List<E> subList(int fromIndex, int toIndex) {
    return null;
  }

  private void reallocate() {
    int newCapacity = (int) Math.min(array.length * 3 / 2 + 1, (long) MAX_ARRAY_SIZE);
    Object[] biggerArray = new Object[newCapacity];
    System.arraycopy(array, 0, biggerArray, 0, size);
    array = biggerArray;
  }

  private void checkCapacityAndReallocate(int capacity) {
    if (capacity <= MAX_ARRAY_SIZE) {
      reallocate();
    } else {
      throw new OutOfMemoryError();
    }
  }

  private void checkRange(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException();
    }
  }

  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder("");
    stringBuilder.append('[');
    for (int i = 0; i < size; ++i) {
      stringBuilder.append(getByIndex(i).toString());
      if (i != size - 1) {
        stringBuilder.append(", ");
      }
    }
    stringBuilder.append(']');
    return stringBuilder.toString();
  }
}

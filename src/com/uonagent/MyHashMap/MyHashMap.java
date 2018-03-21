package com.uonagent.MyHashMap;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class MyHashMap<K, V> implements Map<K, V> {
  private int size;

  private final int TABLE_CAPACITY;

  private Object[] hashTable;

  private static class Node<K, V> {
    final K key;
    V value;
    Node<K, V> next;

    Node(K key, V value, Node<K, V> next) {
      this.key = key;
      this.value = value;
    }

    @Override
    public String toString() {
      return key.toString() + "=" + value.toString();
    }
  }

  public MyHashMap() {
    TABLE_CAPACITY = 8196;
    hashTable = new Object[TABLE_CAPACITY];
  }

  public MyHashMap(int initialCapacity) {
    TABLE_CAPACITY = initialCapacity;
    hashTable = new Object[TABLE_CAPACITY];
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
  @SuppressWarnings("unchecked")
  public boolean containsKey(Object key) {
    Node<K, V> current = (Node<K, V>) hashTable[hashCode(key.hashCode())];
    while (current != null) {
      if (key.equals(current.key)) {
        return true;
      }
      current = current.next;
    }
    return false;
  }

  @Override
  @SuppressWarnings("unchecked")
  public boolean containsValue(Object value) {
    for (int i = 0; i < TABLE_CAPACITY; ++i) {
      if (hashTable[i] != null) {
        Node<K, V> current = (Node<K, V>) hashTable[i];
        while (current != null) {
          if (value.equals(current.value)) {
            return true;
          }
          current = current.next;
        }
      }
    }
    return false;
  }

  @Override
  @SuppressWarnings("unchecked")
  public V get(Object key) {
    Node<K, V> current = (Node<K, V>) hashTable[hashCode(key.hashCode())];
    V r = null;
    do {
      if (key.equals(current.key)) {
        r = current.value;
        break;
      }
    } while (current.next != null);
    return r;
  }

  @Override
  @SuppressWarnings("unchecked")
  public V put(K key, V value) {
    int index = hashCode(key.hashCode());
    if (hashTable[index] == null) {
      hashTable[index] = new Node<>(key, value, null);
      size++;
      return null;
    }
    Node<K, V> current = (Node<K, V>) hashTable[index];
    Node<K, V> previous = null;
    while (true) {
      if (key.equals(current.key)) {
        V r = current.value;
        current.value = value;
        return r;
      }
      previous = current;
      current = current.next;
      if (current == null) {
        current = new Node<>(key, value, null);
        previous.next = current;
        size++;
        return null;
      }
    }
  }

  @Override
  @SuppressWarnings("unchecked")
  public V remove(Object key) {
    int index = hashCode(key.hashCode());
    Node<K, V> current = (Node<K, V>) hashTable[index];
    if (key.equals(current.key)) {
      V r = current.value;
      hashTable[index] = current.next;
      size--;
      return r;
    }
    Node<K, V> previous = current;
    current = current.next;
    while (current != null) {
      if (key.equals(current.key)) {
        V r = current.value;
        previous.next = current.next;
        size--;
        return r;
      }
      current = current.next;
    }
    return null;
  }

  @Override
  public void putAll(Map<? extends K, ? extends V> m) {

  }

  @Override
  public void clear() {

  }

  @Override
  public Set<K> keySet() {
    return null;
  }

  @Override
  public Collection<V> values() {
    return null;
  }

  @Override
  public Set<Entry<K, V>> entrySet() {
    return null;
  }

  private int hashCode(int hash) {
    return Math.abs(hash % TABLE_CAPACITY);
  }
}

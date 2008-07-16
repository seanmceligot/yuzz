package org.snuvy;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

public class OrderedMap<K, V> {
  private TreeMap<K, V> _map;

  private LinkedList<K> _order;

  private List<K> _readonly;

  public OrderedMap() {
    _map = new TreeMap<K, V>();
    _order = new LinkedList<K>();
    _readonly = Collections.unmodifiableList(_order);
  }

  public void put(K key, V value) {
    _map.put(key, value);
    _order.add(key);
  }
  public V first() {
    return _map.get(_order.get(0));
  }

  public Iterator<K> keys() {
    return _readonly.iterator();
  }
  public V get(K key) {
    return _map.get(key);
  }

}

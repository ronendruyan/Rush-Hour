package RushHour;

//class for an object with a key and a value
public class Pair<K, V> {
	private K _key;
	private V _value;

	// constructor
	public Pair(K key, V value) {
		this._key = key;
		this._value = value;
	}

	// setters & getters
	public K getKey() {
		return _key;
	}

	public V getValue() {
		return _value;
	}

	public void setKey(K key) {
		this._key = key;
	}
}
package ru.job4j.last;

import java.util.*;

/**
 * @author Yury Matskevich
 * @since 0.1
 */
public class TrieMap {
	private HashMap<Character, Node> roots = new HashMap<>();

	public Set<Integer> getPosition(String string) {
		Node cur = search(string);
		return cur == null || cur.positions.isEmpty() ? null : cur.positions;
	}

	public void insert(String string, int position) {
		char first = string.charAt(0);
		if (!roots.containsKey(first)) {
			roots.put(first, new Node());
		}
		if (string.length() == 1 & roots.containsKey(first)) {
			roots.get(first).positions.add(position);
			return;
		}
		insertWord(string.substring(1), roots.get(string.charAt(0)), position);
	}

	private void insertWord(String string, Node node, int position) {
		final Node nextChild;
		if (node.children.containsKey(string.charAt(0))) {
			nextChild = node.children.get(string.charAt(0));
		} else {
			nextChild = new Node();
			node.children.put(string.charAt(0), nextChild);
		}
		if (string.length() == 1) {
			nextChild.positions.add(position);
		} else {
			insertWord(string.substring(1), nextChild, position);
		}
	}

	private Node search(String string) {
		char first = string.charAt(0);
		if (string.length() == 1 & roots.containsKey(first)) {
			return roots.get(first);
		}
		if (!roots.containsKey(first)) {
			return null;
		}
		return searchFor(string.substring(1), roots.get(first));
	}

	private Node searchFor(String string, Node node) {
		if (string.length() == 0 | node == null) {
			return node;
		}
		char first = string.charAt(0);
		return searchFor(string.substring(1), node.children.get(first));
	}

	private class Node {
		private Set<Integer> positions = new TreeSet<>();
		private HashMap<Character, Node> children = new HashMap<>();
	}
}
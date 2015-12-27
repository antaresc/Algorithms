package main.com.acscooter.datastructures;

import java.util.HashMap;

/** Tries are tree datastructures that efficiently store strings. Each node
 *  represents a letter and traversal of the tree starting at the root and
 *  ending at a specially marked terminal node determines a word.
 *
 *  The root node of the Trie is a sentinel with the ascii character null.
 *
 *  @author Antares Chen
 *  @since  2015-12-26
 */
public class Trie<Value> implements SearchTree<String, Value> {

    /** The root of the trie. */
    private TrieNode _root;

    /** Creates an empty Trie. */
    public Trie() {
        _root = new Trie((char) 0, "");
    }

    @Override
    public Value find(String key) {
        TrieNode result = find(key, _root);
        if (key.equals(result._key)) {
            return key._value;
        }
        return null;
    }

    /** Returns the node that best matches KEY starting at NODE. */
    private TrieNode find(String key, TrieNode node) {
        if (!key.equals("")) {
            Character letter = key.charAt(0);
            TrieNode next = node.get(letter);

            if (next != null) {
                return find(key.substring(1), next);
            }
        }
        return node;
    }

    @Override
    public Value insert(String key, Value value) {
        if (key.equals("")) {
            throw new IllegalArgumentException("Cannot insert an empty String");
        }

        TrieNode result = find(key, _root);

        if (key.equals(result._key)) {
            Value prev = result._value;
            result._value = value;
            return prev;
        } else {
            return insert(result._key.length(), key, value, result);
        }
    }

    /** Inserts and associates VALUE to KEY into the trie starting at NODE.
     *  Returns the value previously associated with KEY. */
    private Value insert(int index, String key, Value value, TrieNode node) {
        for (i = index; i < key.length(); i += 1) {
            Character letter = key.charAt(i);
            TrieNode next = new TrieNode(letter, key.substring(0, i + 1));
            node.add(letter, next);
            node = next;
        }
        return node.put(value);
    }

    @Override
    public Value remove(Key key) {
        Value result = find(key);
        if (result != null) {
            TrieNode split = splitPoint(key, _root);
            Character letter = key.charAt(split._key.length());
            split.remove(letter);
        }
        return result;
    }

    /** Returnes the most recent ancestor TrieNode for KEY starting at NODE with
     *  more than one child. */
    private TrieNode remove(String key, TrieNode node) {
        TrieNode split = _root;

        for (int i = 0; i < key.length() && node != null; i += 1) {
            if (node.children() > 1) {
                split = node;
            }
            node = node.get(key.charAt(i));
        }

        return split;
    }

    @Override
    public Value findMin() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Value findMax() {
        throw new UnsupportedOperationException();
    }

    /** A helper class that defines each node in the trie. */
    private static class TrieNode {

        /** The value held by this Trie. */
        private Character _letter;
        /** The string held by this Trie thus far. */
        private String _word;
        /** The value associated with the word. This is null until a value is
         *  assigned by Trie insert. */
        private Value _value;
        /** All of the node's children mapping letter to a Trie. */
        private HashMap<Character, Trie> _children;

        /** Initializes the Trie to hold LETTER and the resulting word to
         *  hold WORD. */
        Trie(char letter, String word) {
            _letter = letter;
            _word = word;
            _value = null;
            _children = new HashMap<Character, TrieNode>();
        }

        /** Returns the number of children this TrieNode possesses. */
        int children() {
            return _children.keySet().size();
        }

        /** Returns the node connected to the current node by LETTER. */
        Trie get(char letter) {
            return _children.get(letter);
        }

        /** Returns the node mapped to LETTER and maps LETTER to NODE. */
        Trie add(char letter, TrieNode node) {
            return _children.put(letter, node);
        }

        Value put(Value value) {
            Value prev = _value;
            _value = value;
            return prev;
        }

        /** Returns the node associated with LETTER and removes LETTER from
         *  _children. */
        TrieNode remove(char letter) {
            return _children.remove(letter);
        }

        /** For debugging purpose. */
        @Override
        public String toString() {
            return "Letter (" + _letter + ") Word (" + _word + ") Children"
                + _children.keySet();
        }
    }

}

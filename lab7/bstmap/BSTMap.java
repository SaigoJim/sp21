package bstmap;

import java.util.*;

public class BSTMap<K extends Comparable, V> implements Map61B<K, V>{
    private BSTnode root;
    private int size;

    private class BSTnode {
        K key;
        V value;
        BSTnode left;
        BSTnode right;

        public BSTnode(K key, V value, BSTnode left, BSTnode right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    public BSTMap() {
        root = null;
        size = 0;
    }

    private BSTnode insert(BSTnode tree, K key, V value) {
        if (tree == null) {
            size += 1;
            return new BSTnode(key, value, null, null);
        } else if (key.compareTo((Object) tree.key) < 0) {
            tree.left = insert(tree.left, key, value);
        } else if (key.compareTo((Object) tree.key) > 0) {
            tree.right = insert(tree.right, key, value);
        }
        return tree;
    }

    private BSTnode search(BSTnode T, K key) {
        if (T == null || T.key.equals(key)) {
            return T;
        } else if (key.compareTo((Object) T.key) < 0) {
            return search(T.left, key);
        }
        return search(T.right, key);
    }

    private BSTnode delete(BSTnode T, K key) {
        if (T == null) {
            return null;
        }

        if (key.compareTo((Object) T.key) < 0) {
            T.left = delete(T.left, key);
        } else if (key.compareTo((Object) T.key) > 0) {
            T.right = delete(T.right, key);
        } else if(key.equals((Object) T.key)) {
            size -= 1;
            if (T.left == null) {
                T = T.right;
            } else if (T.right == null) {
                T = T.left;
            } else {
                T.left = substituteLargest(T.left, T);
            }
        }

        return T;
    }

    private BSTnode substituteLargest(BSTnode T, BSTnode Node) {
        if (T.right == null) {
            Node.key = T.key;
            Node.value = T.value;
            return T.left;
        }
        T.right = substituteLargest(T.right, Node);
        return T;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        return search(root, key) != null;
    }

    @Override
    public V get(K key) {
        BSTnode returnNode = search(root, key);
        if (returnNode == null) {
            return null;
        }
        return returnNode.value;
    }


    @Override
    public int size() {
        return size;
    }


    @Override
    public void put(K key, V value) {
        if (root == null) {
            root = insert(root, key, value);
            return;
        }
        insert(root, key, value);
    }

    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        ArrayList<K> orderedList = new ArrayList<>();
        iterateThroughBST(root, orderedList);
        for (K key : orderedList) {
            set.add(key);
        }
        return set;
    }

    @Override
    public V remove(K key) {
        BSTnode removedNode = search(root, key);
        if (removedNode == null) {
            return null;
        }
        V returnVal = removedNode.value;
        root = delete(root, key);
        return returnVal;
    }

    @Override
    public V remove(K key, V value) {
        BSTnode removedNode = search(root, key);
        if (removedNode == null) {
            return null;
        }
        V returnVal = removedNode.value;
        if (returnVal.equals(value)) {
            root = delete(root, key);
            return returnVal;
        }
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        ArrayList<K> orderedList = new ArrayList<>();
        iterateThroughBST(root, orderedList);
        return orderedList.iterator();
    }

    private void iterateThroughBST(BSTnode T, List<K> list) {
        if (T == null) {
            return;
        }
        iterateThroughBST(T.left, list);
        list.add(T.key);
        iterateThroughBST(T.right, list);
    }
}

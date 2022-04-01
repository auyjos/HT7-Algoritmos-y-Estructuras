package src;

/* 
Esta es la clase que controla los árboles
 */

import java.util.Iterator;
import java.util.Stack;

public class BinarySearchTree<T extends java.lang.Comparable<T>> {

    /** 
      *Se crea el Nodo en una clase privada
      */
    
    private static class BinaryNode<T> {
      private T item;
      private BinaryNode<T> left;
      private BinaryNode<T> right;
      private Traduccion traduccion;
    
    
    /** 
     * Se crea un constructor que contruye nodos
     * Se almacenan tambien valores al nodo
      */
      private BinaryNode(T value, Traduccion trans) {
        item = value;
        left = null;
        right = null;
        traduccion = trans;
      }
    
    
    /** 
     * Contructor que arma el nodo pero con un especifico sub arbol
     * value es el valor que se va a almacenar en el arbol
      */
      private BinaryNode(T value, Traduccion trans, BinaryNode<T> l, BinaryNode<T> r) {
        item = value;
        left = l;
        right = r;
        traduccion = trans;
      }
    }

    protected BinaryNode<T> root = null; // si el árbol está vacío

    /* contruye un árbol vacío
     */
    public BinarySearchTree() {
	super();
    }

    /*Construye un árbol con un solo elemento
     * value es el único elemento que se le da al árbol
     */
    public BinarySearchTree(T value , Traduccion trans) {
	super();
	root = new BinaryNode<T>(value, trans);
	
    }

    /* contruye un árbol con un nodo dado como raíz
     * newRoot es la nuev raiz para el árbol
     */
    public BinarySearchTree(BinaryNode<T> newRoot) {
	super();
	root = newRoot;
    }

    /* Encuentra el valor en un árbol
     * Key es el valor del nodo deseado
     * retorna el valor o null si no lo encuentra
     */
    public T get(T key) {
	BinaryNode<T> node = root;
	while (node != null) {
	    if (key.compareTo(node.item) == 0) {
		return node.item;
	    }
	    if (key.compareTo(node.item) < 0) {
		node = node.left;
	    } else {
		node = node.right;
	    }
	}
	return null;
    }
    
    /* Encuentra la traduccion en el arbol
     * Key es el valor del nodo que buscamos
     * retorna el valor o null si no lo encuentra
     */
    public Traduccion getTraduccion(T key) {
	BinaryNode<T> node = root;
	while (node != null) {
	    if (key.compareTo(node.item) == 0) {
		return node.traduccion;
	    }
	    if (key.compareTo(node.item) < 0) {
		node = node.left;
	    } else {
		node = node.right;
	    }
	}
	return null;
    }

    /* Añadir valor a el árbol y lo remplaza con uno ya existente si es necesario
     */
    public void add(T value, Traduccion trans) {
	root = add(value, trans, root);
    }

    /* Añadir valor a el árbol y lo remplaza con uno ya existente si es necesario
     * node es la raiz del subarbol que se desea insertar
     * regresa el subarbol con el nodo insertado
     */
    protected BinaryNode<T> add(T value, Traduccion trans, BinaryNode<T> node) {
	if (node == null) {
	    return new BinaryNode<T>(value, trans );
	}
	if (value.compareTo(node.item) == 0) {
	    // Remplaza el valor en este nodo con un nuevo valor
	    node.item = value;
	    //regresa nuevo nodo BinaryNode<T>(value, node.left, node.right);
	} else {
	    if (value.compareTo(node.item) < 0) {	// añadir al subarbol a la izquierda
		node.left = add(value, trans, node.left);
	    } else {		// añadir al subarbol a la derecha
		node.right = add(value, trans, node.right);
	    }
	}
	return node;
    }


    protected T getRightmost(BinaryNode<T> node) {
	assert(node != null);
	BinaryNode<T> right = node.right;
	if (right == null) {
	    return node.item;
	} else {
	    return getRightmost(right);
	}
    }

    /* iterator, atraviesa el arbol en orden */
    public Iterator<T> iterator() {
	return new TreeIterator(root);
    }

    /* pre-orden */
    public Iterator<T> preIterator() {
	return new TreeIterator(root, true);
    }

    /* pos-orden */
    public Iterator<T> postIterator() {
	return new TreeIterator(root, false);
    }

    /*
     *Clase que recorre sobre los arboles
     */
    
    private class TreeIterator implements Iterator<T> {
        
        protected BinaryNode<T> root = null;
        protected Stack<BinaryNode<T>> visiting = new Stack<BinaryNode<T>>();
        protected Stack<Boolean> visitingRightChild = new Stack<Boolean>();
        boolean preorder = false;
        boolean inorder = true;
        boolean postorder = false;
    
        /* constructor para buscar en orden
         * root es la raiz del arbol
         */
        public TreeIterator(BinaryNode<T> root) {
            this.root = root;
            visiting = new Stack<BinaryNode<T>>();
            visitingRightChild = new Stack<Boolean>();
            preorder = false;
            inorder = true;
            postorder = false;
        }
    
        /* constructor para buscar en pre orden o post orden
         * inPreorder true si es pre-orden, false si es post-orden
         */
        public TreeIterator(BinaryNode<T> root, boolean inPreorder) {
            this.root = root;
            visiting = new Stack<BinaryNode<T>>();
            visitingRightChild = new Stack<Boolean>();
            preorder = inPreorder;
            inorder = false;
            postorder = ! preorder;
        }
    
        public boolean hasNext() {
            return (root != null);
        }
    
        public T next() {
            if (! hasNext()) {
                throw new java.util.NoSuchElementException("no more elements");
            }
            if (preorder) {
                return preorderNext();
            } else if (inorder) {
                return inorderNext();
            } else if (postorder) {
                return postorderNext();
            } else {
                assert(false);
                return null;
            }
        }
    
        // regresa el nodo en la cima del Stack y empuja el siguiente nodo
        private T preorderNext() {
            if (visiting.empty()) {	// at beginning of iterator
                visiting.push(root);
            }
            BinaryNode<T> node = visiting.pop();
            T result = node.item;
            // need to visit the left subtree first, then the right
            // since a stack is a LIFO, push the right subtree first, then
            // the left.  Only push non-null trees
            if (node.right != null) {
                visiting.push(node.right);
            }
            if (node.left != null) {
                visiting.push(node.left);
            }
            // may not have pushed anything.  If so, we are at the end
            if (visiting.empty()) { // no more nodes to visit
                root = null;
            }
            return node.item;
        }
    
        /* Enucentra el nodo que este mas a la izquierda y empuja todos los nodos que esten intermedios en el Stack
         */
        private void pushLeftmostNode(BinaryNode<T> node) {
            // Encuentra el nodo
            if (node != null) {
                visiting.push(node); // empuja el nodo
                pushLeftmostNode(node.left); // sigue al siguiente nodo a la izquierda
            }
        }
    
        /* return the leftmost node that has not yet been visited
         * that node is normally on top of the stack
         * inorder traversal doesn't use the visitingRightChild stack
         */
        private T inorderNext() {
            if (visiting.empty()) {	// at beginning of iterator
                // find the leftmost node, pushing all the intermediate nodes
                // onto the visiting stack
                pushLeftmostNode(root);
            } // now the leftmost unvisited node is on top of the visiting stack
            BinaryNode<T> node = visiting.pop();
            T result = node.item; // this is the value to return
            // if the node has a right child, its leftmost node is next
            if (node.right != null) {
                BinaryNode<T> right = node.right;
                // find the leftmost node of the right child
                pushLeftmostNode (right);
                // note "node" has been replaced on the stack by its right child
            } // else: no right subtree, go back up the stack
              // next node on stack will be next returned
            if (visiting.empty()) { // no next node left
                root = null;
            }
            return result;
        }
    
        /* find the leftmost node from this root, pushing all the
         * intermediate nodes onto the visiting stack
         * and also stating that each is a left child of its parent
         * @param	node the root of the subtree for which we
         *		are trying to reach the leftmost node
         * @changes	visiting takes all nodes between node and the leftmost
         */
        private void pushLeftmostNodeRecord(BinaryNode<T> node) {
            // find the leftmost node
            if (node != null) {
                visiting.push(node); // push this node
                visitingRightChild.push(false); // record that it is on the left
                pushLeftmostNodeRecord(node.left); // continue looping
            }
        }
    
        // 
        private T postorderNext() {
            if (visiting.empty()) {	// at beginning of iterator
                // find the leftmost node, pushing all the intermediate nodes
                // onto the visiting stack
                pushLeftmostNodeRecord(root);
            } // the node on top of the visiting stack is the next one to be
              // visited, unless it has a right subtree
            if ((visiting.peek().right == null) || // no right subtree, or
                (visitingRightChild.peek())) { // right subtree already visited
                // already visited right child, time to visit the node on top
                T result = visiting.pop().item;
                visitingRightChild.pop();
                if (visiting.empty()) {
            	root = null;
                }
                return result;
            } else { // now visit this node's right subtree
                // pop false and push true for visiting right child
                if (visitingRightChild.pop()) {
            	assert(false);
                }
                visitingRightChild.push(true);
                // now push everything down to the leftmost node
                // in the right subtree
                BinaryNode<T> right = visiting.peek().right;
                assert(right != null);
                pushLeftmostNodeRecord(right);
                // use recursive call to visit that node
                return postorderNext();
            }
        }
    
        

    }
}


package structure.tree;

/**
 * Created by boyce on 2014/8/4.
 */
public interface ITree<T extends Comparable> {

    public int deep();

    public T findMax();

    public T findMin();

    public boolean contains(T t);

    public boolean isEmpty();

    public void insert(T element);

    public void remove(T element);

    public String LDR();

    public String LRD();

    public String DLR();

    public void display();

    /**
     * tree node interface
     * @param <T>
     */
    static class Node<T extends Comparable> {
        protected T element;
        protected Node leftNode;
        protected Node rightNode;
        protected int height;

        public Node(T element, Node leftNode, Node rightNode) {
            this.element = element;
            this.leftNode = leftNode;
            this.rightNode = rightNode;
        }

        public Node(T element) {
            this(element, null, null);
        }

        /**
         * 是否是叶节点
         * @return
         */
        public boolean isLeaf() {
            return leftNode == null && rightNode == null;
        }

        /**
         * 是否满节点
         * @return
         */
        public boolean isFullLeaf() {
            return !isLeaf() && !onlyLeftChild() && !onlyRightChild();
        }

        public boolean onlyLeftChild() {
            return leftNode != null && rightNode == null;
        }

        public boolean onlyRightChild() {
            return leftNode == null && rightNode != null;
        }

    }
}

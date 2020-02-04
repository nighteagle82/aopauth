package cn.ne.aopauth.utils.link;

public class LinkImple<T> implements Link<T> {

    // ================ 内部类，链表功能实现 ================
    private class Node<T> {

        private T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
        }

    }

    // ================ 外部表，链表中的方法实现 ================
    private Node<T> root;              // 根节点
    private int count;                  // 统计个数
    private Node<T> lastNode;           // 保存最后一个节点

    @Override
    public void add(T data) {
        if (data == null) {
            return;
        }
        Node<T> newNode = new Node<>(data);
        if (this.root == null) {
            this.root = newNode;            // 保存节点引用
            this.lastNode = newNode;        // 保存最后一个节点
        } else {
            this.lastNode.next = newNode;   // 保存新节点
            this.lastNode = newNode;        // 改变节点
        }
        this.count++;
    }

    @Override
    public int size() {
        return this.count;                  // 返回元素个数
    }

    @Override
    public boolean isEmpty() {
        return this.count == 0;             // 元素为0即为空
    }

    @Override
    public Object[] toArray() {
        if (this.count == 0) {
            return null;
        }
        Object[] retData = new Object[this.count];
        int foot = 0;   // 定义脚标
        Node<T> node = this.root;
        while (node != null) {
            retData[foot++] = node.data;
            node = node.next;
        }
        return retData;
    }

    @Override
    public T get(int index) {
        if (index >= this.count) {
            return null;
        }
        int foot = 0;
        Node<T> node = this.root;
        while (node != null) {
            if (index == foot++) {    // 索引匹配时返回数据
                return node.data;
            }
            node = node.next;   // 修改为下一次取值所需的节点。
        }
        return null;
    }

    @Override
    public T set(int index, T data) {
        if (index >= this.count) {
            return null;
        }
        T retData = null;
        int foot = 0;
        Node<T> node = this.root;
        while (node != null) {
            if (index == foot++) {    // 索引匹配时返回数据
//                retData = node.data;  // 返回更新前的结果
                node.data = data;     // 更新数据
                retData = node.data;  // 返回更新后的结果
            }
            node = node.next;   // 修改为下一次取值所需的节点。
        }
        return retData;
    }

    @Override
    public boolean contains(T data) {
        Node<T> node = this.root;
        if (data == null || node == null) {
            return false;
        }
        while (node != null) {
            if (node.data.equals(data)){
                return true;
            }
            node = node.next;   // 不存在时，节点往后移动，再循环判断
        }
        return false;
    }

    @Override
    public T remove(T data) {
        if (!this.contains(data)){ // 存在才删除,否则返回空
            return null;
        }
        T retData = null;
        if (this.root.data.equals(data)) {  // 判断是否为根节点的数据
            retData = this.root.data;   // 取得删除的数据
            this.root = this.root.next; // 如果是根节点，则将第二个节点设为根节点
        } else {
            Node<T> parentNode = this.root;         // 取得父节点
            Node<T> currentNode = this.root.next;   // 取得当前节点
            while (currentNode != null) {           // 从当前节点开始遍历
                if (currentNode.data.equals(data)){ // 如果当前节点的数据即为要删除的数据。
                    retData = currentNode.data;     // 取得要删除的数据
                    parentNode.next = currentNode.next; // 父节点的下一个即为当前节点的下一个。
                }
                parentNode = currentNode;       // 将父节点设为当前删除的节点的父节点
                currentNode = currentNode.next; // 当前节点后移一位
            }
        }
        this.count--;   // 总个数减1.
        return retData;
    }

    @Override
    public void clear() {
        if (this.root != null) {
            this.root = null;   // 清空数据。
            this.count = 0;     // 复位索引计数器。
            System.gc();        // 清空垃圾。
        }
    }


}

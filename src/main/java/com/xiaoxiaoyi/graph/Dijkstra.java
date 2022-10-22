package com.xiaoxiaoyi.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author xiaoxiaoyi
 * dijkstra
 */
public class Dijkstra {

    /**
     * 最短路径算法
     *
     * @param fromNode 出发节点
     * @return 出发节点到每个节点的最小值
     */
    public static Map<Node, Integer> dijkstra(Node fromNode) {
        // 1. 初始化distanceMap
        // key: to的节点
        // value: 最短距离
        // 如果distanceMap中不包含to节点，则意味着到to节点的距离为正无穷
        Map<Node, Integer> distanceMap = new HashMap<>(1);
        // 初始节点到自己的距离为0
        distanceMap.put(fromNode, 0);
        // 记录已经使用过的节点
        Set<Node> selectedNodes = new HashSet<>();
        // 选出最小距离的节点
        Node minNode = getMinDistanceAndUnSelectedNodes(distanceMap, selectedNodes);
        // 当最小节点不为空，即还有点未被选择过
        while (minNode != null) {
            // 取出最小距离
            Integer distance = distanceMap.get(minNode);
            // 遍历其所有发散边
            for (Edge edge : minNode.edges) {
                // 获取到当前边的to节点
                Node toNode = edge.to;
                // 判断距离表中是否包含to节点
                if (!distanceMap.containsKey(toNode)) {
                    // 如果不包含则直接更新to节点的最小距离为当前节点的距离 + 当前这条边的权重
                    distanceMap.put(toNode, distance + edge.weight);
                }
                // 距离表更新to节点的距离为自己的距离和当前节点的距离+这条边其中的最小值
                distanceMap.put(toNode, Math.min(distanceMap.get(toNode),
                        distance + edge.weight));
            }
            // 标记该节点已经选中过
            selectedNodes.add(minNode);
            // 更新最小距离节点
            minNode = getMinDistanceAndUnSelectedNodes(distanceMap, selectedNodes);
        }
        return distanceMap;
    }

    /**
     * 从距离表中选出距离最小的而且未被选择过的点
     *
     * @param distanceMap   距离表
     * @param selectedNodes 被选择过的点集
     * @return 符合条件的节点
     */
    public static Node getMinDistanceAndUnSelectedNodes(Map<Node, Integer> distanceMap, Set<Node> selectedNodes) {
        // 要返回的节点
        Node minNode = null;
        // 记录map中的最短距离
        Integer minDistance = Integer.MAX_VALUE;
        // 从map中取出节点
        for (Map.Entry<Node, Integer> entry : distanceMap.entrySet()) {
            Node node = entry.getKey();
            Integer distance = entry.getValue();
            // 如果节点未被访问过且距离小于当前的最小距离
            if (!selectedNodes.contains(node) && distance < minDistance) {
                // 更新最小节点为当前节点
                minNode = node;
                // 更新最小距离
                minDistance = distance;
            }
        }
        return minNode;
    }

    /**
     * 改进后的dijkstra算法
     *
     * @param fromNode 起点
     * @param size     图的结点个数
     * @return 起点到其他所有节点的最小距离
     */
    public static Map<Node, Integer> improvedDijkstra(Node fromNode, int size) {
        // 准备size个大小的小顶堆
        NodeHeap nodeHeap = new NodeHeap(size);
        // 小顶堆添加或者更新或者不更新一个节点和到它的最小距离
        nodeHeap.addOrUpdateOrIgnore(fromNode, 0);
        Map<Node, Integer> result = new HashMap<>();
        while (!nodeHeap.isEmpty()) {
            // 弹出小顶堆的元素，即当前距离最小的节点
            NodeRecord record = nodeHeap.pop();
            // 取出当前节点
            Node cur = record.node;
            // 取出其距离
            int distance = record.distance;
            for (Edge edge : cur.edges) {
                // 插入小顶堆
                nodeHeap.addOrUpdateOrIgnore(edge.to, edge.weight + distance);
            }
            // 将当前节点的距离存入结果
            result.put(cur, distance);
        }
        return result;
    }

    /**
     * 存储节点和其距离
     */
    public static class NodeRecord {
        Node node;
        int distance;

        NodeRecord(Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    public static class NodeHeap {

        // 堆中的所有元素放到数组中
        public final Node[] nodes;
        // 存储节点在堆上对应的位置 值为该节点在nodes中的下标
        public final Map<Node, Integer> heapIndexMap;
        // 存储节点到起点的最小距离
        public final Map<Node, Integer> distanceMap;
        // 堆上一共有多少个节点
        public int size;

        public NodeHeap(int size) {
            // 初始化堆大小为0
            this.size = 0;
            // 存储所有节点的数组
            nodes = new Node[size];
            // 存储所有节点在堆上的位置
            heapIndexMap = new HashMap<>();
            // 存储所有节点的距离
            distanceMap = new HashMap<>();
        }

        public boolean isEmpty() {
            return size == 0;
        }

        /**
         * 判断节点是否进过堆
         *
         * @param node 节点
         * @return 是否进过堆
         */
        public boolean entered(Node node) {
            // indexMap中包含这个节点，说明它肯定进过堆
            return heapIndexMap.containsKey(node);
        }

        /**
         * 判断一个节点是否真正在堆上
         *
         * @param node 节点
         * @return 堆上是否包含该节点
         */
        public boolean contains(Node node) {
            // 节点从堆中弹出后，节点在堆中的下标变为-1，如果堆现在真正包含它，那么其下标就不为-1
            return entered(node) && heapIndexMap.get(node) != -1;
        }

        /**
         * 交换堆上index1和index2上的节点
         *
         * @param index1 节点1在堆上的位置
         * @param index2 节点2在堆上的位置
         */
        public void swap(int index1, int index2) {
            // 更新2个节点在堆上的位置
            heapIndexMap.put(nodes[index1], index2);
            heapIndexMap.put(nodes[index2], index1);
            // 交换nodes中对应的节点
            Node tmp = nodes[index1];
            nodes[index1] = nodes[index2];
            nodes[index2] = tmp;
        }

        /**
         * 从index开始向上调整
         *
         * @param index 位置
         */
        public void heapInsert(int index) {
            // 对比当前位置和父节点的值，如果大于父节点则交换
            while (distanceMap.get(nodes[index]) > distanceMap.get(nodes[(index - 1) / 2])) {
                // 交换当前位置和父元素位置上的节点
                swap(index, (index - 1) / 2);
                // 更新当前位置，以进行下一轮比较
                index = (index - 1) / 2;
            }
        }

        /**
         * 从当前节点开始向下调整堆(保证小顶堆)
         *
         * @param index 当前节点的下标
         * @param size  堆大小
         */
        public void heapIfy(int index, int size) {
            // 当前节点左孩子的下标
            int left = index / 2 + 1;
            // 保证下标不越界
            while (left < size) {
                // 比较左右两个孩子的大小，获取最小孩子的下标
                int smallest = distanceMap.get(nodes[left]) < distanceMap.get(nodes[left + 1]) ?
                        left : left + 1;
                // 左右孩子中最小的那个和父节点进行比较，获取最小的下标
                smallest = distanceMap.get(nodes[index]) < distanceMap.get(nodes[smallest]) ?
                        index : smallest;
                // 如果父节点已经最小就直接退出循环
                if (smallest == index) {
                    break;
                }
                // 否则交换父节点和较小的孩子结点
                swap(index, smallest);
                // index继续指向最小的节点
                index = smallest;
                // 更新最小节点的左孩子下标，循环下一轮
                left = (smallest << 1) + 1;
            }
        }

        /**
         * 堆顶弹出一个节点
         *
         * @return 节点信息
         */
        public NodeRecord pop() {
            // 返回的堆顶元素
            NodeRecord nodeRecord = new NodeRecord(nodes[0], distanceMap.get(nodes[0]));
            // 将堆顶元素和堆的最后一个元素交换
            swap(0, size - 1);
            // 更新弹出节点的下标为-1
            heapIndexMap.put(nodes[size - 1], -1);
            // 删除弹出节点的距离
            distanceMap.remove(nodes[size - 1]);
            // 删除这个节点(断连)
            nodes[size - 1] = null;
            // 从现在的根节点处开始向下调整堆
            heapIfy(0, --size);
            return nodeRecord;
        }

        /**
         * 新增一个节点的距离信息
         *
         * @param node 节点
         * @param distance 距离
         */
        public void addOrUpdateOrIgnore(Node node, int distance) {
            if (contains(node)) {
                // 如果节点真的在堆中，则更新它的距离
                distanceMap.put(node, Math.min(distanceMap.get(node), distance));
                // 从当前节点所在的位置开始向上调整
                heapInsert(heapIndexMap.get(node));
            } else if (!entered(node)) {
                // 如果这个节点从未进过堆，则进行初始化
                nodes[size] = node;
                // 记录当前节点和它的下标
                heapIndexMap.put(node, size);
                // 记录当前节点和它的距离
                distanceMap.put(node, distance);
                // 从当前节点的位置开始向上调整堆，并且堆的大小 + 1
                heapInsert(size++);
            }
            // 如果进过堆但是不在堆上，则说明也不做
        }
    }

}

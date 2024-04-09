public class HuffmanNode implements Comparable<HuffmanNode>{
    int frequency;
    char character;
    HuffmanNode left, right;

    public HuffmanNode(int frequency, char character) {
        this.frequency = frequency;
        this.character = character;
        left = right = null;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }

    public int compareTo(HuffmanNode node) {
        return this.frequency - node.frequency;
    }
}

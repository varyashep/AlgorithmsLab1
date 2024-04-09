public class LZ77Tuple {
    public int offset;
    public int length;
    public char nextChar;

    public LZ77Tuple(int offset, int length, char nextChar) {
        this.offset = offset;
        this.length = length;
        this.nextChar = nextChar;
    }

    @Override
    public String toString() {
        return "<" + offset + ", " + length + ", " + nextChar + ">";
    }
}

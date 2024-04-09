public class Tuple implements Comparable<Tuple>{

    Integer originalIndex;  // ������ ������������ ������ ��������
    Integer firstHalf;      // ������ ���� ������ �������� ��������
    Integer secondHalf;     // ������ ���� ������ �������� ��������


    @Override
    public int compareTo(Tuple tuple) {
        return this.firstHalf.equals(tuple.firstHalf)
                ? this.secondHalf.compareTo(tuple.secondHalf)
                : this.firstHalf.compareTo(tuple.firstHalf);
    }

    @Override
    public String toString() {
        return "Tuple{" +
                "originalIndex=" + originalIndex +
                ", firstHalf=" + firstHalf +
                ", secondHalf=" + secondHalf +
                '}';
    }
}

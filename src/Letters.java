public class Letters implements Comparable<Letters>{

    char letter;
    int number;

    public Letters(char letter, int number) {
        this.letter = letter;
        this.number = number;
    }

    @Override
    public int compareTo(Letters other) {
        // Сравниваем по значению letter
        return Character.compare(this.letter, other.letter);
    }
}

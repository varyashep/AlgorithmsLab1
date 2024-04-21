public class BWTObject implements Comparable<BWTObject> {
    Integer num;
    Character character;

    public BWTObject(int num, char character) {
        this.num = num;
        this.character = character;
    }

    @Override
    public int compareTo(BWTObject o) {
        return (((int) this.character) - ((int) o.getCharacter()));
    }

    @Override
    public String toString() {
        return "BWTObject{" +
                "num=" + num +
                ", character=" + character +
                '}';
    }

    public Integer getNum() {
        return num;
    }

    public Character getCharacter() {
        return character;
    }
}

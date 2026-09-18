package linkedlist.pojo;

public class SpecialNode {
    public int value;
    public SpecialNode next;
    public SpecialNode randomPointer;
    public SpecialNode(int value) {
        this.value = value;
    }

    public SpecialNode(int value, SpecialNode next, SpecialNode randomPointer) {
        this.value = value;
        this.next = next;
        this.randomPointer = randomPointer;
    }
}

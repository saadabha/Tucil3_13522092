package src;

public class node {
    public String word;
    public node parent;
    public int cost;

    public node(String word) {
        this.word = word;
        this.parent = null;
        this.cost = 0;
    }

    public node(String word, int cost) {
        this.word = word;
        this.parent = null;
        this.cost = cost;
    }

    public node(String word, node parent, int cost) {
        this.word = word;
        this.parent = parent;
        this.cost = cost;
    }

    public int distancefromroot() {
        int count = 0;
        node current = this;
        while (current.parent != null) {
            count++;
            current = current.parent;
        }
        return count;
    }
}


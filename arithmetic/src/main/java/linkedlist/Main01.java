import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main01 { // 洛谷平台统一的提交规范
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int studentCount = in.nextInt();
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < studentCount; i++) {
            String name = in.next();
            int averageScore = in.nextInt();
            int commentScore = in.nextInt();
            char cadre = in.next().charAt(0);
            char west = in.next().charAt(0);
            int paperCount = in.nextInt();
            int bonus = calculate(averageScore,commentScore,cadre,west,paperCount);
            students.add(new Student(name,averageScore,commentScore,cadre,west,paperCount,bonus));
        }

        Student biggest = students.get(0);
        int totalBonus = 0;
        for (Student student : students) {
            totalBonus += student.getBonus();
            if (student.getBonus() > biggest.getBonus()) {
                biggest = student;
            }
        }

        System.out.println(biggest.getName());
        System.out.println(biggest.getBonus());
        System.out.println(totalBonus);

    }

    public static int calculate(int averageScore,int commentScore,char cadre,char west,int paperCount){
        int bonus = 0;
        if (averageScore > 80 && paperCount >= 1) bonus += 8000;
        if (averageScore > 85 && commentScore > 80)  bonus += 4000;
        if (averageScore > 90) bonus += 2000;
        if (averageScore > 85 && west == 'Y') bonus += 1000;
        if (commentScore > 80 && cadre == 'Y') bonus += 850;
        return bonus;
    }
}

class Student{
    private String name;
    private int averageScore;
    private int commentScore;
    private char cadre;
    private char west;
    private int paperCount;
    private int bonus;
    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", averageScore=" + averageScore +
                ", commentScore=" + commentScore +
                ", cadre=" + cadre +
                ", west=" + west +
                ", paperCount=" + paperCount +
                '}';
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public Student(String name, int averageScore, int commentScore, char cadre, char west, int paperCount, int bonus) {
        this.name = name;
        this.averageScore = averageScore;
        this.commentScore = commentScore;
        this.cadre = cadre;
        this.west = west;
        this.paperCount = paperCount;
        this.bonus = bonus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(int averageScore) {
        this.averageScore = averageScore;
    }

    public int getCommentScore() {
        return commentScore;
    }

    public void setCommentScore(int commentScore) {
        this.commentScore = commentScore;
    }

    public char getCadre() {
        return cadre;
    }

    public void setCadre(char cadre) {
        this.cadre = cadre;
    }

    public char getWest() {
        return west;
    }

    public void setWest(char west) {
        this.west = west;
    }

    public int getPaperCount() {
        return paperCount;
    }

    public void setPaperCount(int paperCount) {
        this.paperCount = paperCount;
    }
}

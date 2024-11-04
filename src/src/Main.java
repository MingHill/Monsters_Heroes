public class Main {
    public static void main(String[] args) {
        Party party = new Party(2);
        GameMap map = new GameMap(8, party);
        System.out.print(map);
    }
}
import java.util.*;
import java.io.*;
import java.text.*;
public class CompetitionMap {
    private Map<String, List<SportsMen>> competitionMap;

    public CompetitionMap() {
        this.competitionMap = new HashMap<>();
    }

    public void setCompetitionMap(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(" ");
            String name = parts[0].trim();
            String surname = parts[1].trim();
            String diploma = parts[2].trim();
            String competition = parts[3].trim();
            String category = parts[4].trim();

            SportsMen sportsman = new SportsMen(name, surname, diploma, category);

            if (!competitionMap.containsKey(competition)) {
                competitionMap.put(competition, new ArrayList<>());
            }
            competitionMap.get(competition).add(sportsman);
        }

        for (List<SportsMen> athletes : competitionMap.values()) {
            athletes.sort(Comparator.comparing(SportsMen::getDiploma));
        }

        reader.close();
    }

    public void printCompetitionMap() {

        for (Map.Entry<String, List<SportsMen>> competitionEntry : competitionMap.entrySet()) {
            String competition = competitionEntry.getKey();
            System.out.println("Соревнование: " + competition);
            List<SportsMen> athletes = competitionEntry.getValue();

            for (SportsMen sportsman : athletes) {
                System.out.println("  " + sportsman);
            }
            System.out.println();
        }

    }

    public void writeMapToFile(String filename) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));

        for (Map.Entry<String, List<SportsMen>> competitionEntry : competitionMap.entrySet()) {
            String competition = competitionEntry.getKey();
            writer.write("Соревнование: " + competition + "\n");
            List<SportsMen> athletes = competitionEntry.getValue();

            for (SportsMen sportsman : athletes) {
                writer.write("  " + sportsman + "\n");
            }
            writer.write("\n");
        }

        writer.close();
    }
}
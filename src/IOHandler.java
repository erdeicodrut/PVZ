import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Objects;

class IOHandler {

    public static void main(String[] args) {
        loadInfo();
    }

    public static ArrayList<String> toSpawn;

    public static void loadInfo()
    {
        toSpawn = new ArrayList<>();
//        File file = new File((new File("src/enemies.txt").getAbsolutePath()));
//        int h = 0;
//        while (true)
//        {
//            try
//            {
//                int i = 0;
//
//                BufferedReader info = new BufferedReader(new FileReader(file));
//
//                String[] lineInfo = info.readLine().split(" ");
//
//                String[] gotIt;
//
//                try
//                {
//                    gotIt = lineInfo[h++].split(",");
//                }
//
//                catch (Exception e) {
//                    break;
//                }
//
//                while (true)
//                {
//                    try
//                    {
//                        String[] infoAbEnemies = gotIt[i++].split(":");
//
//                        String zombieClass = infoAbEnemies[0];
//                        int n = Integer.parseInt(infoAbEnemies[1]);
//
//                        for (int num = 0; num < (n + 1) * Globals.spawnTime / 2; num++) {
//                            toSpawn.add(zombieClass);
//                        }
//                    }
//                    catch (Exception e)
//                    {
//                        break;
//                    }
//                }
//            }
//            catch (FileNotFoundException e)
//            {
//                System.out.println("File not found");
//                e.printStackTrace();
//                return;
//            }
//            catch (IOException e)
//            {
//                e.printStackTrace();
//                return;
//            }
//        }
    }
}
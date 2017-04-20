import java.io.*;
import java.util.ArrayList;

class IOHandler {

    public static void main(String[] args) {
        loadInfo();
    }

    static ArrayList<ArrayList<Enemy>> enemies = new ArrayList<>();

    public static void loadInfo()
    {
        File file = new File("D:\\Programming\\PVZ\\src\\enemies.txt");
        int h = 0;
        while (true)
        {
            try
            {
                int i = 0;

                ArrayList<ArrayList<ArrayList<Enemy>>> wave = new ArrayList<>();

                BufferedReader info = new BufferedReader(new FileReader(file));

                String[] lineInfo = info.readLine().split(" ");

                String[] gotIt;

                try
                {
                    gotIt = lineInfo[h++].split(",");
                }

                catch (Exception e) {
                    break;
                }

                while (true)
                {
                    try
                    {
                        String[] infoAbEnemies = gotIt[i++].split(":");

                        String c = infoAbEnemies[0];
                        int n = Integer.parseInt(infoAbEnemies[1]);

                    }
                    catch (Exception e)
                    {
                        wave.add(enemies);
                        break;
                    }
                }
            }
            catch (FileNotFoundException e)
            {
                System.out.println("2");
                e.printStackTrace();
                return;
            }
            catch (IOException e)
            {
                e.printStackTrace();
                return;
            }
        }
    }


    class Enemy {

        private String name;
        private int howMany;

        public Enemy(String obj, int num) {
            name = obj;
            howMany = num;
        }
    }
}
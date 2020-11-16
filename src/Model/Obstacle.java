package Model;

import java.util.ArrayList;

public class Obstacle {

	ArrayList<Block> blocks = new ArrayList<>();

	public Obstacle(Field f) {
		ArrayList<ArrayList<Integer>> newCords = generateObs(f);
		
		for (int y = 0; y < newCords.size(); y++) {
			if(!(newCords.get(y).get(0) < 34 && newCords.get(y).get(0) > 23 && newCords.get(y).get(1) == 17)) {
				Block b = new Block(newCords.get(y).get(0), newCords.get(y).get(1), null, f);
			blocks.add(b);
			}
			

		}
	}

	public ArrayList<ArrayList<Integer>> generateObs(Field f) {

		ArrayList<ArrayList<Integer>> obsCord = new ArrayList<>();

		int[] randomCords;
		
		// 2 times (1 series vertical and 1 series horizontal)
		for (int z = 0; z < 2; z++) { 
			for (int a = 1; a <= 4; a++) { // 4 lines
				int[] maxs = { (f.getW() - 1), (f.getH() - 1) };
				randomCords = new int[2];
				for (int b = 0; b < 2; b++) {
					int max = ((maxs[b] - 10) / 2);
					int min = 0;
					int range = max - min + 1;
					randomCords[b] = (int) (Math.random() * range) + min;
					if (a == 2 && b == 0)
						randomCords[b] += (60 / 2);
					else if (a == 3 && b == 1)
						randomCords[b] += (24 / 2);
					else if (a == 4 && b == 0)
						randomCords[b] += (60 / 2);
					else if (a == 4 && b == 1)
						randomCords[b] += (24 / 2);
				}
				// random length between 5 and 10
				for (int c = 0; c <= ((int) (Math.random() * (10 - 5 + 1)) + 5); c++) { 
					
					ArrayList<Integer> cords = new ArrayList<>();
					cords.add(randomCords[0]);
					cords.add(randomCords[1]);
					randomCords[z] = randomCords[z] + 1;
					obsCord.add(cords);
				}
			}
		}
		return obsCord;
	}
}

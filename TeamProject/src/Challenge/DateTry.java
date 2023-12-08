package Challenge;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import shop.Closet;
import shop.Item;

public class DateTry {
    private Closet closet;
    private Map<String, Integer> combinationScoreMap; // 조합과 점수 매핑

    public DateTry(Closet closet) {
        this.closet = closet;
        this.combinationScoreMap = new HashMap<>();
        initializeScoreMap(); // 점수 맵 초기화
    }

    private void initializeScoreMap() {
        // 64가지 조합에 대한 점수를 여기서 초기화
    	combinationScoreMap.put("옷 1-악세서리 1", 2);
        combinationScoreMap.put("옷 1-악세서리 2", 40);
        combinationScoreMap.put("옷 1-악세서리 3", 24);
        combinationScoreMap.put("옷 1-악세서리 4", 58);
        combinationScoreMap.put("옷 1-악세서리 5", 42);
        combinationScoreMap.put("옷 1-악세서리 6", 60);
        combinationScoreMap.put("옷 1-악세서리 7", 22);
        combinationScoreMap.put("옷 1-악세서리 8", 38);
        combinationScoreMap.put("옷 2-악세서리 1", 26);
        combinationScoreMap.put("옷 2-악세서리 2", 98);
        combinationScoreMap.put("옷 2-악세서리 3", 10);
        combinationScoreMap.put("옷 2-악세서리 4", 32);
        combinationScoreMap.put("옷 2-악세서리 5", 6);
        combinationScoreMap.put("옷 2-악세서리 6", 36);
        combinationScoreMap.put("옷 2-악세서리 7", 20);
        combinationScoreMap.put("옷 2-악세서리 8", 56);
        combinationScoreMap.put("옷 3-악세서리 1", 42);
        combinationScoreMap.put("옷 3-악세서리 2", 62);
        combinationScoreMap.put("옷 3-악세서리 3", 46);
        combinationScoreMap.put("옷 3-악세서리 4", 18);
        combinationScoreMap.put("옷 3-악세서리 5", 50);
        combinationScoreMap.put("옷 3-악세서리 6", 74);
        combinationScoreMap.put("옷 3-악세서리 7", 54);
        combinationScoreMap.put("옷 3-악세서리 8", 4);
        combinationScoreMap.put("옷 4-악세서리 1", 58);
        combinationScoreMap.put("옷 4-악세서리 2", 60);
        combinationScoreMap.put("옷 4-악세서리 3", 64);
        combinationScoreMap.put("옷 4-악세서리 4", 54);
        combinationScoreMap.put("옷 4-악세서리 5", 8);
        combinationScoreMap.put("옷 4-악세서리 6", 68);
        combinationScoreMap.put("옷 4-악세서리 7", 70);
        combinationScoreMap.put("옷 4-악세서리 8", 76);
        combinationScoreMap.put("옷 5-악세서리 1", 66);
        combinationScoreMap.put("옷 5-악세서리 2", 36);
        combinationScoreMap.put("옷 5-악세서리 3", 44);
        combinationScoreMap.put("옷 5-악세서리 4", 26);
        combinationScoreMap.put("옷 5-악세서리 5", 82);
        combinationScoreMap.put("옷 5-악세서리 6", 78);
        combinationScoreMap.put("옷 5-악세서리 7", 86);
        combinationScoreMap.put("옷 5-악세서리 8", 52);
        combinationScoreMap.put("옷 6-악세서리 1", 12);
        combinationScoreMap.put("옷 6-악세서리 2", 34);
        combinationScoreMap.put("옷 6-악세서리 3", 94);
        combinationScoreMap.put("옷 6-악세서리 4", 46);
        combinationScoreMap.put("옷 6-악세서리 5", 28);
        combinationScoreMap.put("옷 6-악세서리 6", 80);
        combinationScoreMap.put("옷 6-악세서리 7", 68);
        combinationScoreMap.put("옷 6-악세서리 8", 88);
        combinationScoreMap.put("옷 7-악세서리 1", 94);
        combinationScoreMap.put("옷 7-악세서리 2", 48);
        combinationScoreMap.put("옷 7-악세서리 3", 74);
        combinationScoreMap.put("옷 7-악세서리 4", 14);
        combinationScoreMap.put("옷 7-악세서리 5", 86);
        combinationScoreMap.put("옷 7-악세서리 6", 84);
        combinationScoreMap.put("옷 7-악세서리 7", 32);
        combinationScoreMap.put("옷 7-악세서리 8", 92);
        combinationScoreMap.put("옷 8-악세서리 1", 70);
        combinationScoreMap.put("옷 8-악세서리 2", 96);
        combinationScoreMap.put("옷 8-악세서리 3", 30);
        combinationScoreMap.put("옷 8-악세서리 4", 82);
        combinationScoreMap.put("옷 8-악세서리 5", 90);
        combinationScoreMap.put("옷 8-악세서리 6", 50);
        combinationScoreMap.put("옷 8-악세서리 7", 16);
        combinationScoreMap.put("옷 8-악세서리 8", 72);
    }

    public int calculateScore() {
        List<Item> wornItems = closet.getWornItems();
        String combinationKey = generateCombinationKey(wornItems);
        return combinationScoreMap.getOrDefault(combinationKey, 0);
    }

    private String generateCombinationKey(List<Item> items) {
        String clothesKey = "", accessoryKey = "";
        for (Item item : items) {
            if ("옷".equals(item.getCategory()) && item.isWorn()) {
                clothesKey = item.getName();
            } else if ("악세서리".equals(item.getCategory()) && item.isWorn()) {
                accessoryKey = item.getName();
            }
        }
        String combinationKey = clothesKey + "-" + accessoryKey;
        return combinationKey;
    }

    
    public String getFeedback(int score) {
        if (score <= 25) {
            return "너무 싫어!!";
        } else if (score <= 50) {
            return "별로야!";
        } else if (score <= 75) {
            return "음... 괜찮묘!";
        } else {
            return "나랑 사귀장!";
        }
    }
}

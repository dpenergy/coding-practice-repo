import java.util.*;

public class Main02 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int line = in.nextInt();
        int row  = in.nextInt();
        int linePassageCount = in.nextInt();
        int rowPassageCount =  in.nextInt();
        int naughtyGroupCount = in.nextInt();

        ArrayList<NaughtyGroup>  naughtyGroupsList = new ArrayList<>();
        HashMap<Integer,Integer> linePassageNeedsMap = new HashMap<>();
        HashMap<Integer,Integer> rowPassageNeedsMap = new HashMap<>();
        int[] resultRowPassage = new int[rowPassageCount];
        int[] resultLinePassage = new int[linePassageCount];

        for (int i = 0; i < naughtyGroupCount; i++) {
            int x1 = in.nextInt();
            int y1 = in.nextInt();
            int x2 = in.nextInt();
            int y2 = in.nextInt();

            // 数据已经确保准确性，不用手动验证了数据的有效性了

            String isolateWay = null;
            int isolatePlace = -1;
            if(y1 == y2) {
                isolateWay = "line";
                isolatePlace = Math.min(x1, x2);
                if(linePassageNeedsMap.containsKey(isolatePlace)) {
                    Integer needsCount = linePassageNeedsMap.get(isolatePlace);
                    needsCount++;   // 基本类型的包装类是不可变对象，需要重新put
                    linePassageNeedsMap.put(isolatePlace, needsCount);
                } else {
                    linePassageNeedsMap.put(isolatePlace,1);
                }
            } else if(x1 == x2) {
                isolateWay = "row";
                isolatePlace = Math.min(y1, y2);
                if(rowPassageNeedsMap.containsKey(isolatePlace)) {
                    Integer needsCount = rowPassageNeedsMap.get(isolatePlace);
                    needsCount++;
                    rowPassageNeedsMap.put(isolatePlace, needsCount);
                } else {
                    rowPassageNeedsMap.put(isolatePlace,1);
                }
            }

            naughtyGroupsList.add(new NaughtyGroup(x1, x2, y1, y2, isolateWay, isolatePlace));
        }

//        // 打印一下统计结果
//
//        System.out.println();
//        System.out.println("=== Passage Count ===");
//        System.out.println("line:"+linePassageCount);
//        System.out.println("row:"+rowPassageCount);
//
//        int lineIsolateNeedCount = 0;
//        int rowIsolateNeedCount = 0;
//        for (NaughtyGroup naughtyGroup : naughtyGroupsList) {
//            if("row".equals(naughtyGroup.getIsolateWay())) {
//                lineIsolateNeedCount++;
//            }
//
//            if("line".equals(naughtyGroup.getIsolateWay())) {
//                rowIsolateNeedCount++;
//            }
//        }
//
//        System.out.println();
//        System.out.println("=== Passage Needs ===");
//        System.out.println("line:"+lineIsolateNeedCount);
//        System.out.println("row:"+rowIsolateNeedCount);
//
//        System.out.println();
//        System.out.println("Map");
//        Set<Integer> keySet = linePassageNeedsMap.keySet();
//        keySet.forEach(key -> System.out.println("isolate line "+key+" : "+linePassageNeedsMap.get(key)));
//        System.out.println(linePassageNeedsMap);
//        System.out.println(rowPassageNeedsMap);
//
//        System.out.println();
//        System.out.println("=== Details ===");
//        for (NaughtyGroup naughtyGroup : naughtyGroupsList) {
//            System.out.println(naughtyGroup);
//        }


        // 按照贪心算法确定结果（先完成再优化）
        // 1. 分配完有限的竖向通道
        int maxNeeds = 0;
        int maxNeedsKey = 0;

        for (int i = 0; i < linePassageCount; i++) { // 总共只有linePassageCount条竖向通道分配完即可
            // linePassageNeedsMap会在过程中改变，所以lineKeySet也应该随着变化
            Set<Integer> lineKeySet = linePassageNeedsMap.keySet();
            for (Integer key : lineKeySet) {
                Integer value = linePassageNeedsMap.get(key);
                if(value > maxNeeds) {
                    maxNeeds = value;
                    maxNeedsKey = key;
                }
            }
            linePassageNeedsMap.remove(maxNeedsKey);    // 已经处理完的需求就不再需要了
            resultLinePassage[i] = maxNeedsKey;

            maxNeeds = 0;
            maxNeedsKey = 0;
        }

        for (int i = 0; i < rowPassageCount; i++) {
            Set<Integer> rowKeySet = rowPassageNeedsMap.keySet();
            for (Integer key : rowKeySet) {
                Integer value = rowPassageNeedsMap.get(key);
                if(value > maxNeeds) {
                    maxNeeds = value;
                    maxNeedsKey = key;
                }
            }

            rowPassageNeedsMap.remove(maxNeedsKey);    // 已经处理完的需求就不再需要了
            resultRowPassage[i] = maxNeedsKey;

            maxNeeds = 0;
            maxNeedsKey = 0;
        }

        // 最终的结果输出还必须是有序排列的
        Arrays.sort(resultLinePassage);
        Arrays.sort(resultRowPassage);

        System.out.println(Arrays.toString(resultLinePassage).replace("[","").replace(",","").replace("]",""));
        System.out.println(Arrays.toString(resultRowPassage).replace("[","").replace(",","").replace("]",""));
    }
}

class NaughtyGroup {
    private int x1;
    private int x2;
    private int y1;
    private int y2;
    private String isolateWay;
    private int isolatePlace;

    public NaughtyGroup(int x1, int x2, int y1, int y2, String isolateWay, int isolatePlace) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.isolateWay = isolateWay;
        this.isolatePlace = isolatePlace;
    }

    @Override
    public String toString() {
        return "NaughtyGroup{" +
                "x1=" + x1 +
                ", x2=" + x2 +
                ", y1=" + y1 +
                ", y2=" + y2 +
                ", isolateWay='" + isolateWay + '\'' +
                ", isolatePlace=" + isolatePlace +
                '}';
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public String getIsolateWay() {
        return isolateWay;
    }

    public void setIsolateWay(String isolateWay) {
        this.isolateWay = isolateWay;
    }

    public int getIsolatePlace() {
        return isolatePlace;
    }

    public void setIsolatePlace(int isolatePlace) {
        this.isolatePlace = isolatePlace;
    }
}
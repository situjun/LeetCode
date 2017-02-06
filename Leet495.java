public class Solution {
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        if(timeSeries.length == 0) return 0;
        if(timeSeries.length == 1) return duration;
        int result = 0;
        for(int i = 1;i<=timeSeries.length-1;i++){
            if(timeSeries[i] - timeSeries[i-1] <= duration) result += timeSeries[i] - timeSeries[i-1];
            else result += duration;
        }
        result += duration;
        return result;
    }
}
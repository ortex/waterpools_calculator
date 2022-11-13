package org.example;

public class WaterPoolsCalculator {

    public static final int MAX_NUMBER_OF_POSITIONS = 32000;
    public static final int MAX_HEIGHT = 32000;

    long calculateWaterAmount(int[] landscape) {
        validate(landscape);
        int leftMax = 0;
        int rightMax = 0;
        int[] leftMaxLevels = new int[landscape.length];
        int[] rightMaxLevels = new int[landscape.length];

        for (int i = 0; i < landscape.length; i++) {
            leftMax = Math.max(leftMax, landscape[i]);
            leftMaxLevels[i] = leftMax;

            rightMax = Math.max(rightMax, landscape[landscape.length - i - 1]);
            rightMaxLevels[landscape.length - i - 1] = rightMax;
        }
        long result = 0;
        for (int i = 0; i < landscape.length; i++) {
            result += Math.min(leftMaxLevels[i], rightMaxLevels[i]) - landscape[i];
        }
        return result;
    }

    private void validate(final int[] landscape) {
        if (landscape.length > MAX_NUMBER_OF_POSITIONS) {
            String message = "Invalid number of landscape positions: %s. Maximum is: %s"
                    .formatted(landscape.length, MAX_NUMBER_OF_POSITIONS);
            throw new IllegalArgumentException(message);
        }
        for (int i = 0; i < landscape.length; i++) {
            int height = landscape[i];
            if (height > MAX_HEIGHT) {
                String message = "Invalid height in position %s: %s. Maximum height is: %s"
                        .formatted(i, height, MAX_HEIGHT);
                throw new IllegalArgumentException(message);
            }
        }
    }
}

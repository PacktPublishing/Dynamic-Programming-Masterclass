package com.company.lcspattern;

public class EditDistance {
    public static void main(String[] args) {
        String s1 = "goldens";
        String s2 = "glides";

        int editDistance = calculateEditDistance(s1, s2);

        System.out.println("Edit Distance for the two strings = " + editDistance);
    }

    // assuming s1 and s2 are not null
    private static int calculateEditDistance(String s1, String s2) {
        int length1 = s1.length();
        int length2 = s2.length();

        // + 1 for the empty string at the end
        int[][] ed = new int[length1 + 1][length2 + 1];

        // for two empty strings are equal already and we don't need any extra operation
        ed[length1][length2] = 0;

        // when s1 is an empty string, there is only one choice to make the two strings equal and that is to delete all characters of s2
        for (int j = length2 - 1; j >= 0; j--) {
            ed[length1][j] = 1 + ed[length1][j + 1];
        }

        // when s2 is an empty string, there is only one choice to make the two strings equal and that is to delete all characters of s1
        for (int i = length1 - 1; i >= 0; i--) {
            ed[i][length2] = 1 + ed[i + 1][length2];
        }

        // fill the values using the recurrence relation
        for (int i = length1 - 1; i >= 0; i--) {
            for (int j = length2 - 1; j >= 0; j--) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    ed[i][j] = ed[i + 1][j + 1];
                }
                else {
                    ed[i][j] = 1 + Math.min(ed[i + 1][j], Math.min(ed[i + 1][j + 1], ed[i][j + 1]));
                }
            }
        }

        for (int i = 0; i < ed.length; i++) {
            for (int j = 0; j < ed[0].length; j++) {
                System.out.print(ed[i][j] + " ");
            }
            System.out.println();
        }

        return ed[0][0];

    }
}

package com.wa.leetcode;

/**
 * ToGoatLatin
 * https://leetcode-cn.com/problems/goat-latin/
 * 2022-04-21 08:52
 *
 * @author wuao
 */
public class ToGoatLatin {
    public static void main(String[] args) {
        String sentence = "I speak Goat Latin";
        String sentence2 = "The quick brown fox jumped over the lazy dog";

        System.out.println(toGoatLatin(sentence));
        System.out.println(toGoatLatin(sentence2));

    }

    private static String toGoatLatin(String sentence) {
        StringBuilder sb = new StringBuilder();
        int start = 0, wordIndex = 0;
        while (start < sentence.length()) {
            int index = start;
            while (index < sentence.length() && sentence.charAt(index) != ' ') {
                index++;
            }

            wordIndex++;

            StringBuilder word = new StringBuilder(sentence.substring(start, index));
            char originChar = word.substring(0, 1).charAt(0);
            char firstChar = word.substring(0, 1).toLowerCase().charAt(0);
            if (firstChar == 'a' || firstChar == 'e' || firstChar == 'i' || firstChar == 'o' || firstChar == 'u') {
                word.append("ma");
            } else {
                word = new StringBuilder(word.substring(1) + originChar + "ma");
            }

            for (int i = 0; i < wordIndex; i++) {
                word.append("a");
            }

            sb.append(word);
            sb.append(" ");
            start = index + 1;
        }

        return sb.substring(0, sb.length() - 1);
    }
}

package com.booleanuk;


import java.util.*;

public class Scrabble {
    private final String word;

    public Scrabble(String word) {
        this.word = word;
    }

    public int score() {

        if(this.word == null || this.word.isEmpty() || this.word.isBlank() || this.word.equals("\n\r\t\b\f"))
            return 0;

        //Tildeler poeng til vær bokstav
        List<Character> group1 =List.of('A','E','I','U','O','U','L','N','R','S','T');
        List<Character>group2 = List.of('D', 'G');
        List<Character> group3 =List.of('B', 'C', 'M', 'P');
        List<Character> group4 =List.of('F', 'H', 'V', 'W', 'Y');
        List<Character> group5 =  List.of('K');
        List<Character> group8 = List.of('J', 'X');
        List<Character> group10 =List.of('Q', 'Z');
        List<Character> group11 =List.of('{', '}','[',']');
        List<Character> group12 =List.of('!','|','#');

        HashMap<Character,Integer> map = new HashMap<>();
        group1.forEach(x-> map.put(x,1));
        group2.forEach(x-> map.put(x,2));
        group3.forEach(x-> map.put(x,3));
        group4.forEach(x-> map.put(x,4));
        group5.forEach(x-> map.put(x,5));
        group8.forEach(x-> map.put(x,8));
        group10.forEach(x-> map.put(x,10));

        int count = 0;
        //Deler opp word i bokstaver
       char[] bokstaver = this.word.toUpperCase().toCharArray();
       HashMap<Character,Integer> bracketMap = new HashMap<>();

        for (int i = 0; i<bokstaver.length; i++) {
            if (group11.contains(bokstaver[i])) {
                count++;
                bracketMap.put(bokstaver[i],i);
            }
            if (group12.contains(bokstaver[i]))
                return 0;
        }

        //If brackets aren't matching
        if(count%2 != 0) return 0;
        //If positioning of brackets are wrong
        try {
            if(bracketMap.get('{')!= 0 && bracketMap.get('}')-bracketMap.get('{')!=2)
                return 0;
            if (bracketMap.get('}') < bracketMap.get('{') || bracketMap.get(']') < bracketMap.get('['))
                return 0;
        }catch (NullPointerException ignored){}

        boolean doubleStart = false, tripleStart = false, doubleEnd = false, tripleEnd = false;
        //Beregner poeng basert på bokstav
        int sum = 0;
        for (char c : bokstaver) {

            if ((tripleStart && doubleEnd))
                return 0;

            if (c == '{') {
                doubleStart = true;
                doubleEnd = false;
            }
            if (c == '}') {
                doubleEnd = true;
                doubleStart = false;
            }
            if (c == '[') {
                tripleStart = true;
                tripleEnd = false;
            }
            if (c == ']') {
                tripleEnd = true;
                tripleStart = false;
            }
            if (doubleStart && !group11.contains(c)) {
                map.replace(c, map.get(c) * 2);
            }
            if (tripleStart && !group11.contains(c)) {
                map.replace(c, map.get(c) * 3);
            }
            if (!group11.contains(c))
                sum += map.get(c);
        }
        return sum;
    }
}

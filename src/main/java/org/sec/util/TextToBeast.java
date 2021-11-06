package org.sec.util;

public class TextToBeast {
    private static final char[] bd = {'嗷', '呜', '啊', '~'};

    public static void main(String[] args){
        String str = "0|1|2|3|4|5|6|7|8|9";
        String enc = ToBeast(str);
        System.out.println(enc);
        System.out.println(FromBeast(enc));
    }

    private static String ToBeast(String str){
        char[] UBytes = str.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for(char c : UBytes){
            StringBuilder hexB = new StringBuilder(Integer.toHexString(c));
            while (hexB.length() < 4){
                hexB.insert(0, "0");
            }
            stringBuilder.append(hexB);
        }
        char[] tfArr = stringBuilder.toString().toCharArray();
        StringBuilder beast = new StringBuilder();
        for(int i = 0;i < tfArr.length;i++){
            int k = Integer.valueOf(String.valueOf(tfArr[i]), 16) + (i % 16);
            if(k >= 16){
                k -= 16;
            }
            beast.append(bd[k / 4]).append(bd[k % 4]);
        }
        return "" + bd[3] + bd[1] + bd[0] + beast + bd[2];
    }

    private static String FromBeast(String str){
        str = str.substring(3, str.length()-1);
        char[] bfArr = str.toCharArray();
        StringBuilder bf = new StringBuilder();
        for(int i = 0;i <= bfArr.length - 2;i += 2){
            int pos1 = 0;
            int pos2 = 0;
            char c = bfArr[i];
            while (pos1 <= 3 && c != bd[pos1]){
                pos1++;
            }
            char c2 = bfArr[i+1];
            while (pos2 <= 3 && c2 != bd[pos2]){
                pos2++;
            }
            int k = ((pos1 * 4) + pos2) - ((i / 2) % 16);
            if(k < 0){
                k += 16;
            }
            bf.append(Integer.toHexString(k));
        }
        str = bf.toString();
        StringBuilder stringBuffer = new StringBuilder();
        int start = 0;

        for(int end = 4;end <= str.length();end += 4){
            stringBuffer.append((char) Integer.parseInt(str.substring(start, end), 16));
            start += 4;
        }
        return stringBuffer.toString();
    }

}
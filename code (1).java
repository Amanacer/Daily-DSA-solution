class Solution {
    public int minMaxDifference(int num) {
        String str1 = Integer.toString(num);
        String str2 = str1;

        int idx = 0;
        for(int i = 0; i < str1.length(); i++) {
            if(str1.charAt(i) != '9') {
                idx = i;
                break;
            }
        }

        if(idx < str1.length()) {
            str1 = str1.replace(str1.charAt(idx), '9');
        }

        str2 = str2.replace(str2.charAt(0), '0');

        return Integer.parseInt(str1) - Integer.parseInt(str2);
    }
}
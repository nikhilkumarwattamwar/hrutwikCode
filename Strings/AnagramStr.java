public class AnagramStr {
    public static void main(String[] args) {
        String a = "listen";
        String b = "silent";
        int[] arr = new int[256];
        if (a.length()==b.length()) {
            for (int i = 0; i < a.length(); i++) {
                arr[a.charAt(i)] += 1;
            }

            for (int i = 0; i < b.length(); i++) {
                arr[b.charAt(i)] -= 1;
            }

            for (int i = 0; i < 256; i++) {
                if (arr[i] != 0) {
                    System.out.println("String is not anagram");
                }
            }
            System.out.println("String is a anagram");
        }
        else {
            System.out.println("String is not anagram");
        }
    }
}

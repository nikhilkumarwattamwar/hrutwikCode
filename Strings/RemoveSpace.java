public class RemoveSpace {
    public static void main(String[] args) {
        String s = "hello this is java";
//        String s1=s.replaceAll("\\s","");
//        System.out.println(s1);
        String[] arr = s.split("\\s");
        String result = "";
        for (int i = 0; i < arr.length; i++) {
            result += arr[i];
        }
        System.out.println(result);
        System.out.println("---X---");

        String s1= "hello this is python";
        StringBuilder sb = new StringBuilder();
        for (char c : s1.toCharArray()) {
            if (c != ' ') {
                sb = sb.append(c);
            }
        }
        System.out.println(sb);
    }
}

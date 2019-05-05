import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Main {
    public static void main(String[] args) {
        String cu = "cu2 cu";
        String pattern = "(_|[A-z])(\\w+)";

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(cu);
        System.out.println(m.matches());
    }
}

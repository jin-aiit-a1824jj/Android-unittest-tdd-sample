package a1824jj.jp.ac.aiit.androidunittesttdd_sample;

public class StringReverser {

    public String reverse(String string){
        StringBuilder sb = new StringBuilder();
        for(int i = string.length() ; i > 0; i--){
            sb.append(string.charAt(i));
        }
        return sb.toString();
    }

}

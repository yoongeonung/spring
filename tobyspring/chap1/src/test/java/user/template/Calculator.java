package user.template;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Calculator {
    public Integer calcSum(String path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        Integer sum = 0;
        String line = null;
        while ((line = br.readLine()) != null) {
            sum += Integer.parseInt(line);
        }
        br.close(); // 한번 연 파일은 반드시 닫아준다.
        return sum;
    }
}

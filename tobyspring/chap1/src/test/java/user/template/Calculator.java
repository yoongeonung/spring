package user.template;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Calculator {
    public Integer calcSum(String path) throws IOException {
//        BufferedReaderCallback sumCallback = new BufferedReaderCallback() {
//            @Override
//            public Integer doSomethingWithReader(BufferedReader br) throws IOException {
//                Integer sum = 0;
//                String line = null;
//                while ((line = br.readLine()) != null) {
//                    sum += Integer.parseInt(line);
//                }
//                return sum;
//            }
//        };
//        return fileReadTemplate(path, sumCallback);

        LineCallback<Integer> callback = new LineCallback<Integer>() {
            @Override
            public Integer doSomethingWithLine(String line, Integer val) {
                return val + Integer.valueOf(line);
            }
        };

        return lineReadTemplate(path, callback, 0);
    }

    // temlplate
    public Integer fileReadTemplate(String filePath, BufferedReaderCallback callback) throws IOException {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filePath));
            return callback.doSomethingWithReader(br);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            if (br != null) {
                try {
                    br.close(); // 한번 연 파일은 반드시 닫아준다.
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    // 조금더 추가한 template
    public <T> T lineReadTemplate(String filePath, LineCallback<T> callback, T initVal) throws IOException {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filePath));
            String line = null;
            T res = initVal;
            while ((line = br.readLine()) != null) {
                res = callback.doSomethingWithLine(line, res);
            }
            return res;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            if (br != null) {
                try {
                    br.close(); // 한번 연 파일은 반드시 닫아준다.
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public Integer calMultiply(String numFilePath) throws IOException {
          // template 1
//        BufferedReaderCallback multipluCallback = new BufferedReaderCallback() {
//            @Override
//            public Integer doSomethingWithReader(BufferedReader br) throws IOException {
//                Integer multiply = 1;
//                String line = null;
//                while ((line = br.readLine()) != null) {
//                    multiply *= Integer.parseInt(line);
//                }
//                return multiply;
//            }
//        };
//        return fileReadTemplate(numFilePath, multipluCallback);

        // template 2
        LineCallback<Integer> callback = new LineCallback<Integer>() {
            @Override
            public Integer doSomethingWithLine(String line, Integer val) {
                return val * Integer.valueOf(line);
            }
        };
        return lineReadTemplate(numFilePath, callback, 1);
    }

    public String concatenate(String path) throws IOException {
        LineCallback<String> concatCallback = new LineCallback<String>() {
            @Override
            public String doSomethingWithLine(String line, String val) {
                return val + line;
            }
        };

        return lineReadTemplate(path, concatCallback, "");
    }
}

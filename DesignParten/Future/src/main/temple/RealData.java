package main.temple;

/**
 * 实际关心的数据
 *
 * @author: haoliu on 16/04/2018 10:50
 */
public class RealData implements IData<String> {
    private String content;

    public RealData(final int count, final char c) {
        System.out.println("   making RealData(" + count + "," + c + ") BEGIN");
        char[] cs = new char[count];
        for (int i = 0; i < count; i++) {
            cs[i] = c;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        this.content = String.valueOf(cs);
        System.out.println("   making RealData(" + count + "," + c + ") END");
    }

    @Override
    public String getContent() {
        return content;
    }
}

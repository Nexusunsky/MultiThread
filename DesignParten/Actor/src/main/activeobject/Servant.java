package main.activeobject;

import java.math.BigInteger;

import main.future.RealResult;
import main.future.Result;

/**
 * 实际实现ActiveObject功能
 *
 * @author: haoliu on 19/04/2018 14:13
 */
class Servant implements ActiveObject {

    @Override
    public String makeContent(final String source) {
        final char[] cs = source.toCharArray();
        StringBuilder builder = new StringBuilder();
        for (final char c : cs) {
            builder.append(" [ ").append(c).append(" ] ");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("Servant [ makeContent ] interrupted." + e);
            }
        }
        return builder.toString();
    }

    @Override
    public void displayContent(final String source) {
        final char[] sources = source.toCharArray();
        int count = 0;
        for (char c : sources) {
            try {
                count++;
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("Servant [ displayContent ] interrupted." + e);
            } finally {
                System.out.println(Thread.currentThread().getName() + " display :" + c + " ,by " + count + " time");
            }
        }
    }

    @Override
    public Result<String> add(final String x, final String y) {
        String result;
        try {
            BigInteger bigX = new BigInteger(x);
            BigInteger bigY = new BigInteger(y);
            BigInteger bigZ = bigX.add(bigY);
            result = bigZ.toString();
        } catch (NumberFormatException e) {
            System.out.println("Servant [ add failed.] " + e);
            result = null;
        }
        return new RealResult<>(result);
    }

}

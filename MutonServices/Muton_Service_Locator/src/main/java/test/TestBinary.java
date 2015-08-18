package test;

import java.math.BigInteger;

/**
 * Created by rohanw on 8/6/2015.
 */
public class TestBinary {
    public static void main(String[] args) {
        /*
        Restrictions
        (position)  (restriction)
        1	        Buy
        2	        Sell
        3	        Cash Transfer
        4	        Withdraw
        5	        Online Trading
         */
        //Mask(Can be cash account, pp, symbol etc)  - set the related bit to 0 if restriction check required.
        //for none
        String mask  = "1111111111111111";
        //for buy restriction check mask
         mask  = "0111111111111111";
        //for sell restriction check mask
        //mask  = "1011111111111111";
        //for buy and sell restriction check mask
        //mask  = "0011111111111111";
        //for transfer restriction check mask
        mask  = "1101111111111111";
        //for withdraw restriction check mask
        mask  = "1110111111111111";
        //for transfer and withdraw restriction check mask
        mask  = "1100111111111111";
        //for buy/sell/transfer and withdraw restriction check mask
        mask  = "0000111111111111";

        //value(Can be cash account, pp, symbol etc) - set the related bit to 0 if restricted.
        //nothing restricted
        String value = "1111111111111111";
        //buy restricted
        value = "0111111111111111";
        //sell restricted
        value = "1011111111111111";
        //buy/sell restricted
        value = "0011111111111111";
        //transfer restricted
        value = "1101111111111111";
        //withdraw restricted
        value = "1110111111111111";
        //transfer/withdraw restricted
        value = "1100111111111111";
        //all 4 types restricted restricted
        value = "1100111111111111";

        short s = 10001;
        short maskValue = new BigInteger(mask, 2).shortValue();
        System.out.println(maskValue);
        short actualValue = new BigInteger(value, 2).shortValue();
        System.out.println(actualValue);

        if((short)-1 == (maskValue | actualValue)){
            System.out.println("Valid");
        } else {
            System.out.println("Invalid");
        }
    }
}

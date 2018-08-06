package sample;

public class ShiftCipher {

    private char[] array;

    public String encrypt(String text, int key){

        array = text.toCharArray();

        key%=94;
        for(int i=0; i<text.length(); i++){
            array[i]+=key;
            if(array[i]>126 || array[i]<32) array[i]-=94;
        }
        text=String.valueOf(array);

        return text;
    }

    public String decrypt(String text, int key){

        array = text.toCharArray();

        key%=94;
        for(int i=0; i<text.length(); i++){
            array[i]-=key;
            if(array[i]<32 || array[i]>126) array[i]+=94;
        }
        text=String.valueOf(array);

        return text;
    }

    private int calcKeyVal(String text){
        int i = 0;
        int keyVal = 0;

        while( i < text.length()) {
            keyVal += text.charAt(i++);
        }

        return keyVal;
    }

    public int getKeyValue(String key){

        int keyVal;

        try{
            keyVal = Integer.parseInt(key);
        } catch(NumberFormatException e){
            keyVal = calcKeyVal(key);
        }

        return keyVal;
    }
}
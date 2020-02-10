package imgpro;

public class gapAngleVelocity {

    public String GetVelocity(String angleEnc) {

        //angleEnc a1 a2 a3 # a4 a5 a6 
        String velocity = "";
        String[] parts = angleEnc.split(" # ");

        for (int j = 0; j < parts.length; j++) {
            String[] enc0 = parts[j].split(" ");
            if (enc0.length <= 5) {
                return velocity;
            }
            for (int i = 0; i < enc0.length - 1; i++) {
                if (i == enc0.length - 6) {
                    velocity = velocity + (Double.parseDouble(enc0[i]) - Double.parseDouble(enc0[0])) + " ";
                } else if (i == enc0.length - 5) {
                    velocity = velocity + (Double.parseDouble(enc0[i]) - Double.parseDouble(enc0[1])) + " ";
                } else if (i == enc0.length - 4) {
                    velocity = velocity + (Double.parseDouble(enc0[i]) - Double.parseDouble(enc0[2])) + " ";
                } else if (i == enc0.length - 3) {
                    velocity = velocity + (Double.parseDouble(enc0[i]) - Double.parseDouble(enc0[3])) + " ";
                } else if (i == enc0.length - 2) {
                    velocity = velocity + (Double.parseDouble(enc0[i]) - Double.parseDouble(enc0[5])) + " ";
                } else {
                    velocity = velocity + (Double.parseDouble(enc0[i]) - Double.parseDouble(enc0[i + 5])) + " ";
                }
            }
            velocity = velocity + "# ";
        }
        return velocity;
    }
}

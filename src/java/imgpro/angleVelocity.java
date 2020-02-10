package imgpro;

public class angleVelocity {

    public String GetVelocity(String angleEnc) {
        //angleEnc a1 a2 a3 # a4 a5 a6 
        String velocity = "";
        String[] parts = angleEnc.split(" # ");
        for (int j = 0; j < parts.length; j++) {
            String[] enc0 = parts[j].split(" ");
            if (enc0.length == 1) {
                velocity = "0 ";
            } else {
                for (int i = 0; i < enc0.length - 1; i++) {
                    velocity = velocity + (Double.parseDouble(enc0[i]) - Double.parseDouble(enc0[i + 1])) + " ";
                }
            }
            velocity = velocity + "# ";
        }
        return velocity;
    }
}

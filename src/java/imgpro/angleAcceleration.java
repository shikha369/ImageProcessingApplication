package imgpro;

public class angleAcceleration {

    public String GetAcceleration(String angleEnc) {
        String acceleration = "";
        String[] parts = angleEnc.split(" # ");
        for (int j = 0; j < parts.length; j++) {
            String[] enc0 = parts[j].split(" ");
            if (enc0.length == 1) {
                acceleration = "0 ";
            }
            else{
            for (int i = 0; i < enc0.length - 1; i++) {
                acceleration = acceleration + (Double.parseDouble(enc0[i]) - Double.parseDouble(enc0[i + 1])) + " ";
            }
            acceleration = acceleration + "# ";
        }}

        return acceleration;
    }
}

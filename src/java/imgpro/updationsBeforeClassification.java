package imgpro;

import java.io.IOException;
import java.util.ArrayList;

public class updationsBeforeClassification {

    public ArrayList<ComponentInfo> updateCharList(ArrayList<ComponentInfo> charList, ArrayList<Updates> updata, String root, String rand) throws IOException {
        String cname;
        String classL;
        ComponentInfo cinfo = new ComponentInfo();
        for (int i = 0; i < updata.size(); i++) {
            cname = updata.get(i).ComponentName;
            classL = updata.get(i).CorrectTo;
            for (ComponentInfo c : charList) {
                if (c.name != null && c.name.contains(cname)) {
                    cinfo = c;
                }
            }

            //charList.remove(cinfo);
            cinfo.CharClass = classL;
            cinfo.LabelFromFileName=classL;
            //charList.add(cinfo);
        }
        /*write updated features*/
        TestFeaturesCsv tfc = new TestFeaturesCsv();
        tfc.writeCSV(charList, root, rand, 1); //1 denotes invokation after correction
        return charList;
    }
}

package misc;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Vector;

public class HtmlUtils {

    public String getTableHead(String align, int border) {

        String tableHeader = null;
        tableHeader = "<TABLE align=" + align + " border=" + border + ">";
        return tableHeader;

    }

    public String getTH(String align, String value) {
        String THCell = null;
        THCell = "<TH align=" + align + "> " + value + " </TH>";
        return THCell;
    }

    public String getTableContents(String align, Vector values,
                                   int elementCounter) throws IOException {

        StringWriter Cells = new StringWriter();
        String contents = new String();
        int vsize = values.size();

        Cells.write("<TR>");

        for (int i = 0; i < vsize; i++) {
            String value = values.elementAt(i).toString();

            if (i != 0) {
                if (i >= elementCounter) {

                    if (i % elementCounter == 0) {
                        Cells.write("</TR>\n\n<TR>");
                    }
                }
            }

            Cells.write("<TD align=" + align + "> " + value + " </TD> \n");
        }

        Cells.write("</TR>");

        contents = Cells.toString();
        Cells.flush();
        Cells.close();

        return contents;
    }

}

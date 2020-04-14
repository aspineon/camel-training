package pl.training.camel.mooduletwo;

import org.apache.camel.RecipientList;
import org.apache.camel.language.xpath.XPath;

public class Recipients {

    @RecipientList
    public String[] recipients(@XPath("/order/@premium") String premium) {
        if (premium.equals("true")) {
            return new String[] {"jms:accounting", "jms:hr"};
        } else {
            return new String[] {"jms:accounting"};
        }
    }

    public String accounting() {
        return "jms:accounting,jms:hr";
    }

}

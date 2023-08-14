

package tests.w3c;

import org.w3c.dom.Element;

import org.custommonkey.xmlunit.Difference;
import org.custommonkey.xmlunit.DifferenceConstants;
import org.custommonkey.xmlunit.IgnoreTextAndAttributeValuesDifferenceListener;

class SchemaAttrDiff extends IgnoreTextAndAttributeValuesDifferenceListener {

    public int differenceFound(Difference difference) {

        if (difference.getId() == DifferenceConstants.ELEMENT_NUM_ATTRIBUTES.getId()) {
            // control and test have to be elements
            // check if they are schema elements .. they only
            // seem to have the added attributeFormDefault and
            // elementFormDefault attributes
            // so shldnt have more than 2 attributes difference
            Element actualEl = (Element)difference.getControlNodeDetail().getNode();

            if (actualEl.getLocalName().equals("schema")) {

                int expectedAttrs = Integer.parseInt(difference.getControlNodeDetail().getValue());
                int actualAttrs = Integer.parseInt(difference.getTestNodeDetail().getValue());
                if (Math.abs(actualAttrs - expectedAttrs) <= 2) {
                    return RETURN_IGNORE_DIFFERENCE_NODES_SIMILAR;
                }
            }
        } else if (difference.getId() == DifferenceConstants.ATTR_NAME_NOT_FOUND_ID) {
            // sometimes the serializer throws in a few extra attributes...
            Element actualEl = (Element)difference.getControlNodeDetail().getNode();

            if (actualEl.getLocalName().equals("schema")) {
                return RETURN_IGNORE_DIFFERENCE_NODES_SIMILAR;
            }
        }

        return super.differenceFound(difference);
    }
}

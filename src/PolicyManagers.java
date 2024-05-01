import java.io.IOException;
import java.time.LocalDate;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.json.simple.JSONObject;

public class PolicyManagers {
	public static String compareCount(Policy LP, Policy PP) {
		String label = "";
		int pp = Integer.parseInt(PP.getRightOperand());
		int lp = Integer.parseInt(LP.getRightOperand());
		if ((LP.getOperator().equals("gt") || LP.getOperator().equals("gteq"))
				&& (PP.getOperator().equals("gt") || PP.getOperator().equals("gteq"))) {
			if (lp == pp) {
				label = "Neutral";
			} else if (pp > lp) {
				label = "Opposing";
			} else if (pp < lp) {
				label = "Supportive";
			}
		} else if ((LP.getOperator().equals("lt") || LP.getOperator().equals("lteq"))
				&& (PP.getOperator().equals("lt") || PP.getOperator().equals("lteq"))) {
			if (lp == pp) {
				label = "Neutral";
			} else if (pp > lp) {
				label = "Supportive";
			} else if (pp < lp) {
				label = "Opposing";
			}
		} else if (LP.getOperator().equals("ep") && (PP.getOperator().equals("eq"))) {

			if (pp == lp)
				label = "Neutral";
			else
				label = "Cannot be labelled";
		} else if ((LP.getOperator().equals("gt")) && (PP.getOperator().equals("eq"))) {

			if (pp > lp)
				label = "Opposing";
			else
				label = "Cannot be labelled";
		} else if (LP.getOperator().equals("gteq") && (PP.getOperator().equals("eq"))) {
			if (pp >= lp)
				label = "Opposing";
			else
				label = "Cannot be labelled";
		} else if ((PP.getOperator().equals("gt")) && (LP.getOperator().equals("eq"))) {

			if (pp < lp)
				label = "Supportive";
			else
				label = "Cannot be labelled";
		} else if (PP.getOperator().equals("gteq") && (LP.getOperator().equals("eq"))) {
			if (pp <= lp)
				label = "Supportive";
			else
				label = "Cannot be labelled";

		} else if ((LP.getOperator().equals("lt")) && (PP.getOperator().equals("eq"))) {

			if (pp < lp)

				label = "Opposing";
			else
				label = "Cannot be labelled";
		} else if (LP.getOperator().equals("lteq") && (PP.getOperator().equals("eq"))) {

			if (pp <= lp)
				label = "Opposing";
			else
				label = "Cannot be labelled";

		} else if ((PP.getOperator().equals("lt")) && (LP.getOperator().equals("eq"))) {

			if (pp > lp)
				label = "Supportive";
			else
				label = "Cannot be labelled";
		} else if (PP.getOperator().equals("lteq") && (LP.getOperator().equals("eq"))) {

			if (pp >= lp)
				label = "Supportive";
			else
				label = "Cannot be labelled";
		} else if (LP.getOperator().equals("nep") && (PP.getOperator().equals("neq"))) {

			label = "Neutral";
		} else if (LP.getOperator().equals("gteq") && PP.getOperator().equals("lteq")) {

			if (pp >= lp)
				label = "Partially supportive of/opposing to";
			else
				label = "Cannot be labelled";

		} else if ((LP.getOperator().equals("gt") || LP.getOperator().equals("gteq"))
				&& (PP.getOperator().equals("lt") || PP.getOperator().equals("lteq"))) {

			if (pp > lp)
				label = "Partially supportive of/opposing to";
			else
				label = "Cannot be labelled";

		} else if (LP.getOperator().equals("lteq") && PP.getOperator().equals("gteq")) {
			if (pp <= lp)
				label = "Partially supportive of/opposing to";
			else
				label = "Cannot be labelled";
		} else if ((LP.getOperator().equals("lt") || LP.getOperator().equals("lteq"))
				&& (PP.getOperator().equals("gt") || PP.getOperator().equals("gteq"))) {
			if (pp < lp)
				label = "Partially supportive of/opposing to";
			else
				label = "Cannot be labelled";
		} else if ((LP.getOperator().equals("eq") && PP.getOperator().equals("neq"))) {
			if (pp != lp)
				label = "Supportive";
			else
				label = "Cannot be labelled";
		} else if ((PP.getOperator().equals("eq") && LP.getOperator().equals("neq"))) {
			if (pp != lp)
				label = "Opposing";
			else
				label = "Cannot be labelled";
		}
		return label;
	}

	public static String comparePayAmount(Policy LP, Policy PP) {
		String label = "";
		int pp = Integer.parseInt(PP.getRightOperand());
		int lp = Integer.parseInt(LP.getRightOperand());
		if ((LP.getOperator().equals("gt") || LP.getOperator().equals("gteq"))
				&& (PP.getOperator().equals("gt") || PP.getOperator().equals("gteq"))) {
			if (lp == pp) {
				label = "Neutral";
			} else if (pp > lp) {
				label = "Opposing";
			} else if (pp < lp) {
				label = "Supportive";
			}
		} else if ((LP.getOperator().equals("lt") || LP.getOperator().equals("lteq"))
				&& (PP.getOperator().equals("lt") || PP.getOperator().equals("lteq"))) {
			if (lp == pp) {
				label = "Neutral";
			} else if (pp > lp) {
				label = "Supportive";
			} else if (pp < lp) {
				label = "Opposing";
			}
		} else if (LP.getOperator().equals("ep") && (PP.getOperator().equals("eq"))) {

			if (pp == lp)
				label = "Neutral";
			else
				label = "Cannot be labelled";
		} else if ((LP.getOperator().equals("gt")) && (PP.getOperator().equals("eq"))) {

			if (pp > lp)
				label = "Opposing";
			else
				label = "Cannot be labelled";
		} else if (LP.getOperator().equals("gteq") && (PP.getOperator().equals("eq"))) {
			if (pp >= lp)
				label = "Opposing";
			else
				label = "Cannot be labelled";
		} else if ((PP.getOperator().equals("gt")) && (LP.getOperator().equals("eq"))) {

			if (pp < lp)
				label = "Supportive";
			else
				label = "Cannot be labelled";
		} else if (PP.getOperator().equals("gteq") && (LP.getOperator().equals("eq"))) {
			if (pp <= lp)
				label = "Supportive";
			else
				label = "Cannot be labelled";

		} else if ((LP.getOperator().equals("lt")) && (PP.getOperator().equals("eq"))) {

			if (pp < lp)

				label = "Opposing";
			else
				label = "Cannot be labelled";
		} else if (LP.getOperator().equals("lteq") && (PP.getOperator().equals("eq"))) {

			if (pp <= lp)
				label = "Opposing";
			else
				label = "Cannot be labelled";

		} else if ((PP.getOperator().equals("lt")) && (LP.getOperator().equals("eq"))) {

			if (pp > lp)
				label = "Supportive";
			else
				label = "Cannot be labelled";
		} else if (PP.getOperator().equals("lteq") && (LP.getOperator().equals("eq"))) {

			if (pp >= lp)
				label = "Supportive";
			else
				label = "Cannot be labelled";
		} else if (LP.getOperator().equals("nep") && (PP.getOperator().equals("neq"))) {

			label = "Neutral";
		} else if (LP.getOperator().equals("gteq") && PP.getOperator().equals("lteq")) {

			if (pp >= lp)
				label = "Partially supportive of/opposing to";
			else
				label = "Cannot be labelled";

		} else if ((LP.getOperator().equals("gt") || LP.getOperator().equals("gteq"))
				&& (PP.getOperator().equals("lt") || PP.getOperator().equals("lteq"))) {

			if (pp > lp)
				label = "Partially supportive of/opposing to";
			else
				label = "Cannot be labelled";

		} else if (LP.getOperator().equals("lteq") && PP.getOperator().equals("gteq")) {
			if (pp <= lp)
				label = "Partially supportive of/opposing to";
			else
				label = "Cannot be labelled";
		} else if ((LP.getOperator().equals("lt") || LP.getOperator().equals("lteq"))
				&& (PP.getOperator().equals("gt") || PP.getOperator().equals("gteq"))) {
			if (pp < lp)
				label = "Partially supportive of/opposing to";
			else
				label = "Cannot be labelled";
		} else if ((LP.getOperator().equals("eq") && PP.getOperator().equals("neq"))) {
			if (pp != lp)
				label = "Supportive";
			else
				label = "Cannot be labelled";
		} else if ((PP.getOperator().equals("eq") && LP.getOperator().equals("neq"))) {
			if (pp != lp)
				label = "Opposing";
			else
				label = "Cannot be labelled";
		}
		return label;
	}

	public static String comparedateTime(Policy LP, Policy PP) throws DatatypeConfigurationException {
		String label = "";
		XMLGregorianCalendar calPP = DatatypeFactory.newInstance().newXMLGregorianCalendar(PP.getRightOperand());
		LocalDate pp = LocalDate.of(calPP.getYear(), calPP.getMonth(), calPP.getDay());
		XMLGregorianCalendar calLP = DatatypeFactory.newInstance().newXMLGregorianCalendar(LP.getRightOperand());
		LocalDate lp = LocalDate.of(calLP.getYear(), calLP.getMonth(), calLP.getDay());

		if ((LP.getOperator().equals("gt") || LP.getOperator().equals("gteq"))
				&& (PP.getOperator().equals("gt") || PP.getOperator().equals("gteq"))) {
			if (lp.isEqual(pp)) {
				label = "Neutral";
			} else if (pp.isAfter(lp)) {
				label = "Opposing";
			} else if (pp.isBefore(lp)) {
				label = "Supportive";
			}
		} else if ((LP.getOperator().equals("lt") || LP.getOperator().equals("lteq"))
				&& (PP.getOperator().equals("lt") || PP.getOperator().equals("lteq"))) {
			if (lp.isEqual(pp)) {
				label = "Neutral";
			} else if (pp.isAfter(lp)) {
				label = "Supportive";
			} else if (pp.isBefore(lp)) {
				label = "Opposing";
			}
		} else if (LP.getOperator().equals("ep") && (PP.getOperator().equals("eq"))) {

			if (pp.isEqual(lp))
				label = "Neutral";
			else
				label = "Cannot be labelled";
		}

		else if ((LP.getOperator().equals("gt") && (PP.getOperator().equals("eq")))) {
			if (pp.isAfter(lp))
				label = "Opposing";
			else
				label = "Cannot be labelled";
		} else if (LP.getOperator().equals("gteq") && (PP.getOperator().equals("eq"))) {
			if (pp.isAfter(lp) || pp.isEqual(lp))
				label = "Opposing";
			else
				label = "Cannot be labelled";
		}

		else if ((PP.getOperator().equals("gt") && (LP.getOperator().equals("eq")))) {

			if (pp.isBefore(lp))
				label = "Supportive";
			else
				label = "Cannot be labelled";
		} else if (PP.getOperator().equals("gteq") && (LP.getOperator().equals("eq"))) {
			if (pp.isBefore(lp) || pp.isEqual(lp))
				label = "Supportive";
			else
				label = "Cannot be labelled";

		}

		else if ((LP.getOperator().equals("lt") && (PP.getOperator().equals("eq")))) {

			if (pp.isBefore(lp))
				label = "Opposing";
			else
				label = "Cannot be labelled";
		} else if (LP.getOperator().equals("lteq") && (PP.getOperator().equals("eq"))) {
			if (pp.isBefore(lp) || pp.isEqual(lp))
				label = "Opposing";
			else
				label = "Cannot be labelled";
		}

		else if ((PP.getOperator().equals("lt") && (LP.getOperator().equals("eq")))) {

			if (pp.isAfter(lp))
				label = "Supportive";
			else
				label = "Cannot be labelled";
		} else if (PP.getOperator().equals("lteq") && (LP.getOperator().equals("eq"))) {
			if (pp.isAfter(lp) || pp.isEqual(lp))
				label = "Supportive";
			else
				label = "Cannot be labelled";
		} else if (LP.getOperator().equals("nep") && (PP.getOperator().equals("neq"))) {

			label = "Neutral";
		} else if (LP.getOperator().equals("gteq") && PP.getOperator().equals("lteq")) {

			if (pp.isAfter(lp) || pp.isEqual(lp))
				label = "Partially supportive of/opposing to";
			else
				label = "Cannot be labelled";

		} else if ((LP.getOperator().equals("gt") || LP.getOperator().equals("gteq"))
				&& (PP.getOperator().equals("lt") || PP.getOperator().equals("lteq"))) {

			if (pp.isAfter(lp))
				label = "Partially supportive of/opposing to";
			else
				label = "Cannot be labelled";
		} else if (LP.getOperator().equals("lteq") && PP.getOperator().equals("gteq")) {

			if (pp.isBefore(lp) || pp.isEqual(lp))
				label = "Partially supportive of/opposing to";
			else
				label = "Cannot be labelled";
		} else if ((LP.getOperator().equals("lt") || LP.getOperator().equals("lteq"))
				&& (PP.getOperator().equals("gt") || PP.getOperator().equals("gteq"))) {

			if (pp.isBefore(lp))
				label = "Partially supportive of/opposing to";
			else
				label = "Cannot be labelled";
		} else if ((LP.getOperator().equals("eq") && PP.getOperator().equals("neq"))) {

			if (!pp.isEqual(lp))
				label = "Supportive";
			else
				label = "Cannot be labelled";
		} else if ((PP.getOperator().equals("eq") && LP.getOperator().equals("neq"))) {

			if (!pp.isEqual(lp))
				label = "Opposing";
			else
				label = "Cannot be labelled";
		}

		return label;
	}

	public static String compareInterval(Policy LP1, Policy LP2, Policy PP1, Policy PP2)
			throws InvalidFormatException, IOException {
		String label = "";

		DateTime blp = DateTime.parse(LP1.getRightOperand());
		DateTime elp = DateTime.parse(LP2.getRightOperand());
		DateTime bpp = DateTime.parse(PP1.getRightOperand());
		DateTime epp = DateTime.parse(PP2.getRightOperand());
		Interval lp = new Interval(blp, elp);
		Interval pp = new Interval(bpp, epp);
		if (lp.equals(pp)) {
			label = "Neutral";

		} else if (elp.isBefore(bpp) || elp.isEqual(bpp) || epp.isBefore(blp) || epp.isEqual(blp)) {
			label = "Cannot be labelled";
		} else if (pp.contains(lp)) {
			label = "Supportive";
		} else if (lp.contains(pp)) {
			label = "Opposing";
		} else if (blp.isEqual(bpp) && elp.isBefore(epp)) {
			label = "Supportive";
		} else if (blp.isEqual(bpp) && epp.isBefore(elp)) {
			label = "Opposing";
		} else if (elp.isEqual(epp) && blp.isAfter(bpp)) {
			label = "Supportive";
		} else if (elp.isEqual(epp) && blp.isBefore(bpp)) {
			label = "Opposing";
		} else if (pp.overlaps(lp) || lp.overlaps(pp)) {
			label = "Partially supportive of/opposing to";
		}

		return label;
	}

	public static String compareSpatial(Policy LP, Policy PP) throws InvalidFormatException, IOException, org.json.simple.parser.ParseException{
		String label = "";

		JSONObject dataLP = GeoNamesAPI.getGeodata(LP.getRightOperand());
		JSONObject dataPP = GeoNamesAPI.getGeodata(PP.getRightOperand());

		boolean iscountryLP = dataLP.get("fcodeName").equals("independent political entity") ? true:false;
		boolean iscountryPP = dataPP.get("fcodeName").equals("independent political entity") ? true:false;
		if (LP.getRightOperand().equals(PP.getRightOperand())) {
			label = "Neutral";
		} else if ((iscountryLP) && (iscountryPP))
			label = "Cannot be labelled";
		else if (iscountryLP) {
			if (dataPP.get("countryName").equals(LP.getRightOperand())) { // Same Country
				label = "Opposing";
			} else
				label = "Cannot be labelled";
		} else if (iscountryPP) {

			if (dataLP.get("countryName").equals(PP.getRightOperand())) { // Same Country
				label = "Supportive";
			} else
				label = "Cannot be labelled";
		} else {

			if (dataLP.get("name").equals(dataPP.get("name"))) { // Same City
				label = "Neutral";
			} else {
				label = "Cannot be labelled";
			}
		}
		return label;
	}

}

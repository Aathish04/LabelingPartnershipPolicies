import java.io.IOException;
import java.util.LinkedList;
// import java.util.Scanner;

import javax.xml.datatype.DatatypeConfigurationException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class Manager {
	private static LinkedList<Policy> PartnershipPolicies = new LinkedList<>();
	private static LinkedList<Policy> LocalPolicies = new LinkedList<>();

	public static void main(String[] args) throws InvalidFormatException, IOException, DatatypeConfigurationException {
		// Scanner input = new Scanner(System.in);
		// System.out.println("Enter the path of your Local Policies file");
		// String Lpath = input.nextLine();
		// System.out.println("Enter the path of your Partnership Policies file");
		// String Ppath = input.nextLine();

		String Lpath = "odrl/LocalPolicies.json";
		LocalPolicies = PolicyParser.ParsePolicies(Lpath);
		String Ppath = "odrl/PartnershipPolicies.json";
		PartnershipPolicies = PolicyParser.ParsePolicies(Ppath);
		for (int i = 0; i < PartnershipPolicies.size(); i++) {
			if (!PartnershipPolicies.get(i).getLeftOperand().equals("dateTime")) {
				for (int j = 0; j < LocalPolicies.size(); j++) {
					comapare(LocalPolicies.get(j), PartnershipPolicies.get(i));

				}
			} else if (i == PartnershipPolicies.size() - 1) {
				for (int j = 0; j < LocalPolicies.size(); j++) {
					comapare(LocalPolicies.get(j), PartnershipPolicies.get(i));
				}
			} else if (!PartnershipPolicies.get(i + 1).getLeftOperand().equals("dateTime")) {
				for (int j = 0; j < LocalPolicies.size(); j++) {
					comapare(LocalPolicies.get(j), PartnershipPolicies.get(i));
				}
			} else
				for (int j = 0; j < LocalPolicies.size(); j++) {
					if (LocalPolicies.get(j).getLeftOperand().equals("dateTime")
							&& LocalPolicies.get(j + 1).getLeftOperand().equals("dateTime")) {
						comapare(LocalPolicies.get(j), LocalPolicies.get(j + 1), PartnershipPolicies.get(i),
								PartnershipPolicies.get(i + 1));
						i++;
						break;
					}
				}
		}

	}

	private static void comapare(Policy LP1, Policy LP2, Policy PP1, Policy PP2) {

		String Conflict = "";
		try {
			Conflict = PolicyManagers.compareInterval(LP1, LP2, PP1, PP2);

			System.out.println("Local Policy : " + LP1.toString());
			System.out.println(LP2.toshortString());
			System.out.println("Partnership Policy : " + PP1.toString());
			System.out.println(PP2.toshortString());
			System.err.println(Conflict);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void comapare(Policy LP, Policy PP)
			throws InvalidFormatException, IOException, DatatypeConfigurationException {
		String Conflict = " ";
		if ((PP.getTarget().equals(LP.getTarget())) && (PP.getLeftOperand().equals(LP.getLeftOperand()))) {
			if (PP.getLeftOperand().equals("count")) {
				Conflict = PolicyManagers.compareCount(LP, PP);
				System.out.println("Local Policy : " + LP.toString());
				System.out.println("Partnership Policy : " + PP.toString());
				System.err.println(Conflict);

			} else if (PP.getLeftOperand().equals("payAmount")) {
				Conflict = PolicyManagers.comparePayAmount(LP, PP);
				System.out.println("Local Policy : " + LP.toString());
				System.out.println("Partnership Policy : " + PP.toString());
				System.err.println(Conflict);

			} else if (PP.getLeftOperand().equals("spatial")) {
				Conflict = PolicyManagers.compareSpatial(LP, PP);
				System.out.println("Local Policy : " + LP.toString());
				System.out.println("Partnership Policy : " + PP.toString());
				System.err.println(Conflict);

			} else if (PP.getLeftOperand().equals("dateTime")) {
				Conflict = PolicyManagers.comparedateTime(LP, PP);
				System.out.println("Local Policy : " + LP.toString());
				System.out.println("Partnership Policy : " + PP.toString());
				System.err.println(Conflict);

			}

		}

	}
}

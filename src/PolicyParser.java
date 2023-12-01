import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class PolicyParser {
	public static LinkedList<Policy> ParsePolicies(String path) {
		LinkedList<Policy> Policies = new LinkedList<>();
		try {
			JSONParser jsonParser = new JSONParser();
			JSONArray obja = (JSONArray) jsonParser.parse(new FileReader(path));
			for (int g = 0; g < obja.size(); g++) {
				JSONObject Policy = (JSONObject) obja.get(g);
				JSONArray permission = (JSONArray) Policy.get("permission");
				for (int i = 0; i < permission.size(); ++i) {
					JSONObject ob = (JSONObject) permission.get(i);
					String target = (String) ob.get("target");
					JSONArray duties = (JSONArray) ob.get("duty");
					for (int k = 0; k < duties.size(); ++k) {
						JSONObject duty = (JSONObject) duties.get(k);
						JSONArray actions = (JSONArray) duty.get("action");
						for (int l = 0; l < actions.size(); ++l) {
							JSONObject action = (JSONObject) actions.get(l);
							JSONObject act1 = (JSONObject) action.get("rdf:value");
							String act = (String) act1.get("@id");
							JSONArray refinements = (JSONArray) action.get("refinement");
							for (int j = 0; j < refinements.size(); ++j) {
								JSONObject ref = (JSONObject) refinements.get(j);
								String leftOperand = (String) ref.get("leftOperand");
								String operator = (String) ref.get("operator");
								String rightOperand = (String) ref.get("rightOperand");
								Policy p = new Policy(target, act, leftOperand, operator, rightOperand);
								Policies.add(p);
							}
						}
					}
				}

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return Policies;
	}

	public static LinkedList<Policy> ParsePolicy(String path) {
		JSONParser jsonParser = new JSONParser();
		LinkedList<Policy> Policies = new LinkedList<>();
		try (FileReader reader = new FileReader(path)) {
			Object obj = jsonParser.parse(reader);
			JSONObject P = (JSONObject) obj;
			JSONArray PoliciesList = (JSONArray) P.get("policies");
			for (int s = 0; s < PoliciesList.size(); ++s) {
				JSONObject Poli = (JSONObject) PoliciesList.get(s);
				JSONArray Polic = (JSONArray) Poli.get("policy");
				for (int m = 0; m < Polic.size(); ++m) {
					JSONObject Policy = (JSONObject) Polic.get(m);
					JSONArray permission = (JSONArray) Policy.get("permission");
					for (int i = 0; i < permission.size(); ++i) {
						JSONObject ob = (JSONObject) permission.get(i);
						String target = (String) ob.get("target");
						JSONArray duties = (JSONArray) ob.get("duty");
						for (int k = 0; k < duties.size(); ++k) {
							JSONObject duty = (JSONObject) duties.get(k);
							JSONArray actions = (JSONArray) duty.get("action");
							for (int l = 0; l < actions.size(); ++l) {
								JSONObject action = (JSONObject) actions.get(l);
								JSONObject act1 = (JSONObject) action.get("rdf:value");
								String act = (String) act1.get("@id");
								JSONArray refinements = (JSONArray) action.get("refinement");
								for (int j = 0; j < refinements.size(); ++j) {
									JSONObject ref = (JSONObject) refinements.get(j);
									String leftOperand = (String) ref.get("leftOperand");
									String operator = (String) ref.get("operator");
									String rightOperand = (String) ref.get("rightOperand");
									Policy p = new Policy(target, act, leftOperand, operator, rightOperand);
									Policies.add(p);
								}
							}
						}
					}

				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return Policies;
	}
}

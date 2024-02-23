import java.util.ArrayList;

public class BackWardChaining {
    //coose rule
    public Rule choose_rule(String goal, Rule[] rules){
        int i = 0;
        while(i<rules.length){
            if (rules[i].consequences.contains(goal)){
                return rules[i];
            }
            i++;
        }
        return null;
    }

    //remove the used one
    public void remove_rule(Rule[] rules, Rule rule){
        int toRemove = -1;
        int i = 0;

        while (i < rules.length && toRemove == -1){
            if (rules[i].equals(rule)){
                toRemove = i;
            }
            i++;
        }

        if (toRemove != -1){
            for (int j = toRemove; j<rules.length-1; j++){
                rules[j] = rules[j+1];
            }
            rules[rules.length-1] = null;
        }
    }

    public boolean prove_purpose(String goal, ArrayList<String> fact_base, Rule[] rule_base){
        boolean result = false;
        Rule rule;
        if (fact_base.contains(goal)){
            result = true;
        }
        else {
            Rule[] rules = new Rule[9];
            for (int i = 0; i < rule_base.length; i++){
                rules[i] = rule_base[i];
            }
            while(rules.length != 0 && !result){
                rule = choose_rule(goal, rules);

                if (rule != null){
                    System.out.println("rule used: "+rule.index);
                    remove_rule(rules, rule);
                    if(rule.consequences.contains(goal)){
                        result = back_ward_chaining(rule_base, rule.premisses,fact_base);
                        if (result){
                            fact_base.add(goal);
                        }
                    }
                }
            }
        }
        return result;
    }

    public boolean back_ward_chaining(Rule[] ruleBase, ArrayList<String> fact_purpose, ArrayList<String> fact_base){
        if(fact_purpose.isEmpty()){
            return true;
        } else if (prove_purpose(fact_purpose.get(0), fact_base, ruleBase)) {
                fact_purpose.remove(0);
                return true;
        }
        else {
            return false;
        }

    }
}

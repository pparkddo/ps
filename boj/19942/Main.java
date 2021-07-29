import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    private static final int NOT_SATISFIED = Integer.MAX_VALUE;
    private static int minimumPrice = NOT_SATISFIED;
    private static List<Food> result = List.of();

    private static String solution(int n, Ingredient ingredient, List<Food> foods) {
        combinations(0, 0, foods, new int[n], ingredient);
        return minimumPrice == NOT_SATISFIED ? "-1" : getAnswer(result);
    }

    private static String getAnswer(List<Food> result) {
        StringBuilder answer = new StringBuilder();
        answer.append(getPriceSum(result)).append("\n");
        answer.append(result.stream().map(each -> String.valueOf(each.id)).collect(Collectors.joining(" ")));
        return answer.toString();
    }

    private static void combinations(int start, int depth, List<Food> foods, int[] values, Ingredient ingredient) {
        List<Food> selected = getSelectedFoods(depth, foods, values);
        if (canSatisfy(selected, ingredient)) {
            int priceSum = getPriceSum(selected);
            if (priceSum < minimumPrice) {
                minimumPrice = priceSum;
                result = selected;
            }
            return;
        }
        if (depth == foods.size()) {
            return;
        }
        for (int index = start; index < foods.size(); index++) {
            values[depth] = index;
            combinations(index+1, depth+1, foods, values, ingredient);
        }
    }

    private static int getPriceSum(List<Food> foods) {
        return foods.stream().map(each -> each.price).mapToInt(Integer::intValue).sum();
    }

    private static List<Food> getSelectedFoods(int depth, List<Food> foods, int[] values) {
        List<Food> selected = new ArrayList<>();
        for (int index = 0; index < depth; index++) {
            selected.add(foods.get(values[index]));
        }
        return selected;
    }

    private static boolean canSatisfy(List<Food> selected, Ingredient ingredient) {
        Ingredient sum = new Ingredient();
        for (Food food : selected) {
            sum.p += food.ingredient.p;
            sum.f += food.ingredient.f;
            sum.s += food.ingredient.s;
            sum.v += food.ingredient.v;
        }
        return sum.satisfy(ingredient);
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        Ingredient ingredient = parseIngredient(in.readLine().split(" "));
        List<Food> foods = new ArrayList<>();
        for (int index = 0; index < n; index++) {
            foods.add(parseFood(index+1, in.readLine().split(" ")));
        }
        in.close();
        System.out.println(solution(n, ingredient, foods));
    }

    private static Food parseFood(int id, String[] values) {
        return new Food(id, parseIngredient(values), Integer.parseInt(values[4]));
    }

    private static Ingredient parseIngredient(String[] values) {
        int p = Integer.parseInt(values[0]);
        int f = Integer.parseInt(values[1]);
        int s = Integer.parseInt(values[2]);
        int v = Integer.parseInt(values[3]);
        return new Ingredient(p, f, s, v);
    }
}

class Ingredient {

    int p;
    int f;
    int s;
    int v;

    Ingredient() {}

    Ingredient(int p, int f, int s, int v) {
        this.p = p;
        this.f = f;
        this.s = s;
        this.v = v;
    }

    public boolean satisfy(Ingredient target) {
        if (p < target.p) {
            return false;
        }
        if (f < target.f) {
            return false;
        }
        if (s < target.s) {
            return false;
        }
        if (v < target.v) {
            return false;
        }
        return true;
    }
}

class Food {

    int id;
    Ingredient ingredient;
    int price;

    Food(int id, Ingredient ingredient, int price) {
        this.id = id;
        this.ingredient = ingredient;
        this.price = price;
    }
}